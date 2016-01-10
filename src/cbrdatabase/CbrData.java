package cbrdatabase;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import cbrmodel.CoefficientsTip;
import cbrmodel.CurvePoint;
import cbrmodel.Format;
import cbrmodel.Instance;
import cbrmodel.RequiredInput;
import cbrmodel.SSOCurvePoint;
import cbrmodel.Stage;
import jdk.internal.org.objectweb.asm.Type;

public class CbrData {
	
	
	private Database data;
	public CbrData(){
		data = new Database();
	}
	
	
	/*Restituisce un nuovo id da utilizzare per memorizzare un nuovo caso
	 * nel casebase del cbr
	 */
	public int getNewId(){
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result=null;
		int res=0;
		try{
			stm = conn.prepareStatement("select max(id) as id from instance");
			result = stm.executeQuery();
			if(result.next()){
				res=result.getInt("id");
			}else res=0;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return res+1;
	}
	
	
	/*Aggiunge una nuovo oggetto Instance(caso) nella casebase*/
	public void addInstance(Instance instance, String tip){
		Connection conn = data.getConnection();
		PreparedStatement stm= null;
		int value = retrieveIdTip(tip);
		try{
			stm = conn.prepareStatement("insert into instance(id,material,tip,csm,test_number,stage,frame) values(?,?,?,?,?,?,?)");
			stm.setInt(1, instance.getId());
			stm.setString(2, instance.getMaterial());
			stm.setInt(3, value);
			stm.setInt(4, instance.getCsm());
			stm.setInt(5, instance.getTest_number());
			stm.setInt(6, instance.getStage());
			stm.setString(7, instance.getFrame());
			stm.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		addCurvePoint(instance);
		addSSOCurve(instance);
		addRequiredInput(instance);
		addCoefficientsTip(instance);
		
	}
	
	/*Dato l'id di un oggetto Instance, si aggiungono i punti per la creazione della curva
	 * load/displacement e per quella harmonic contact stiffness/displacement
	 */
	public void addCurvePoint(Instance i){
		for(CurvePoint it:i.getCurve()){
			Connection conn = data.getConnection();
			PreparedStatement stm = null;
			try{
				stm = conn.prepareStatement("insert into curve(marker,displacementsurface,loadsample,timeonsample,harmoniccontact,"
						+ "hardness,modulus,id_instance) values(?,?,?,?,?,?,?,?)");
				stm.setString(1, it.getMarker());
				stm.setDouble(2, it.getDisplacementsurface());
				stm.setDouble(3, it.getLoadonsample());
				stm.setDouble(4, it.getTimeonsample());
				stm.setDouble(5, it.getHarmoniccontact());
				stm.setDouble(6, it.getHardness());
				stm.setDouble(7, it.getModulus());
				stm.setInt(8, i.getId());
				stm.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if(stm!=null)stm.close();
					if(conn!=null)conn.close();
					
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
		}
		
		
		
	}
	
	/*Dato l'id di un oggetto Instance, aggiunge per il caso i punti per la curva Stiffness square over load/displacement*/
	public void addSSOCurve(Instance i){
		for(SSOCurvePoint it:i.getSsocurve()){
			Connection conn = data.getConnection();
			PreparedStatement stm = null;
			try{
				stm = conn.prepareStatement("insert into ssocurve(displacementsurface,ssoload,id_instance) values(?,?,?)");
				stm.setDouble(1, it.getDisplacementsurface());
				stm.setDouble(2, it.getSsoload());
				stm.setInt(3, i.getId());
				stm.executeUpdate();
				
				stm.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if(stm!=null)stm.close();
					if(conn!=null)conn.close();
					
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
		}
	}
	
	/*Dato l'id di un oggetto Instance, aggiunge al caso nella casebase 
	 * i parametri di RequiredInput del test
	 */
	public void addRequiredInput(Instance i){
		RequiredInput in = i.getInput();
		Connection conn=data.getConnection();
		PreparedStatement stm = null;
		try{
			stm = conn.prepareStatement("insert into requiredinput(surfacevelocity,depthlimit,strainrate,harmonicdisplacement,frequencytarget,surfacedistance,poissonsratio,id_instance) values(?,?,?,?,?,?,?,?)");
			stm.setDouble(1, in.getSurfacevelocity());
			stm.setDouble(2, in.getDepthlimit());
			stm.setDouble(3, in.getStrainrate());
			stm.setDouble(4, in.getHarmonicdisplacement());
			stm.setDouble(5, in.getFrequencytarget());
			stm.setDouble(6, in.getSurfacedistance());
			stm.setDouble(7, in.getPoissonsratio());
			stm.setInt(8, i.getId());
			stm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
	
	/*Restituisce una lista di tutti i materiali della casebase*/
	public List<String> retrieveAllMaterial(){
		List<String> list = new ArrayList<String>();
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		try{
			stm = conn.prepareStatement("select distinct material from instance order by material");
			result = stm.executeQuery();
			while(result.next()){
				list.add(result.getString(1));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	
		return list;
	}
	
	
	public void addCoefficientsTip(Instance instance){
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		try{
			
			stm = conn.prepareStatement("insert into tipcoefficients(m0,m1,m2,m3,m4,m5,m6,m7,m8,instance_id)"
					+ "values(?,?,?,?,?,?,?,?,?,?)");
			stm.setDouble(1, instance.getCoefficientsTip().getM0());
			stm.setDouble(2, instance.getCoefficientsTip().getM1());
			stm.setDouble(3, instance.getCoefficientsTip().getM2());
			stm.setDouble(4, instance.getCoefficientsTip().getM3());
			stm.setDouble(5, instance.getCoefficientsTip().getM4());
			stm.setDouble(6, instance.getCoefficientsTip().getM5());
			stm.setDouble(7, instance.getCoefficientsTip().getM6());
			stm.setDouble(8, instance.getCoefficientsTip().getM7());
			stm.setDouble(9, instance.getCoefficientsTip().getM8());
			stm.setInt(10, instance.getId());
			stm.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void addDescriptions(String desc, int id){
		Connection conn = data.getConnection();
		PreparedStatement stm=null;
		
		try{
			stm = conn.prepareStatement("insert into descriptions(description,id_instance) values(?,?)");
			stm.setString(1, desc);
			stm.setInt(2, id);
			stm.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
	}
	
	
	public void addFormat(String format, int id){
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		
		try{
			stm = conn.prepareStatement("insert into format(format,id_instance) values(?,?)");
			stm.setString(1, format);
			stm.setInt(2, id);
			stm.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	
	public void addExperminent(String exp, int id){
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		try{
			stm = conn.prepareStatement("insert into experimenttype(experiment,id_instance) values(?,?)");
			stm.setString(1, exp);
			stm.setInt(2, id);
			stm.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	/*Dato il materiale passato come parametro, si restituisce una lista di oggetti Instance
	 * presenti nella casebase
	 */
	public List<Instance> retrieveInstancesByMaterial(String material){
		List<Instance> list = new ArrayList<Instance>();
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		try{
			stm = conn.prepareStatement("select id,material, tip, csm, test_number, stage, frame from instance where material=?");
			stm.setString(1, material);
			result = stm.executeQuery();
			while(result.next()){
				Instance tmp = new Instance();
				tmp.setId(result.getInt(1));
				tmp.setMaterial(result.getString(2));
				tmp.setTip(result.getString(3));
				tmp.setCsm(result.getInt(4));
				tmp.setTest_number(result.getInt(5));
				tmp.setStage(result.getInt(6));
				tmp.setFrame(result.getString(7));
				list.add(tmp);
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		for(Instance it:list){
			it.setCurve(this.retrieveCurvePoint(it.getId()));
			it.setSsocurve(this.retrieveSSOCurveById(it.getId()));
			it.setInput(this.retrieveRequiredInputById(it.getId()));
			it.setCoefficientsTip(this.retrieveCoefficientsTip(it.getId()));
			it.setStage_obj(this.retrieveStageById(it.getStage()));
		}
		return list;
	}
	
	
	public List<Instance> retrieveInstancesByMaterialAndFormat(String material,String format){
		List<Instance> list = new ArrayList<Instance>();
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		try{
			stm = conn.prepareStatement("select id,material, tip, csm, test_number, stage, frame,format from instance i, format f where (i.id=f.id_instance) and material=?");
			stm.setString(1, material);
			result = stm.executeQuery();
			while(result.next()){
				Instance tmp = new Instance();
				tmp.setId(result.getInt(1));
				tmp.setMaterial(result.getString(2));
				tmp.setTip(result.getString(3));
				tmp.setCsm(result.getInt(4));
				tmp.setTest_number(result.getInt(5));
				tmp.setStage(result.getInt(6));
				tmp.setFrame(result.getString(7));
				tmp.setFormat(new Format(result.getString(8),result.getInt(1)));
				list.add(tmp);
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		for(Instance it:list){
			it.setCurve(this.retrieveCurvePoint(it.getId()));
			it.setSsocurve(this.retrieveSSOCurveById(it.getId()));
			it.setInput(this.retrieveRequiredInputById(it.getId()));
			it.setCoefficientsTip(this.retrieveCoefficientsTip(it.getId()));
			it.setStage_obj(this.retrieveStageById(it.getStage()));
		}
		return list;
	}
	
	/*Si restituiscono tutti gli oggetti Instance presenti nella casebase*/
	public List<Instance>  retrieveAllInstances(){
		List<Instance> list = new ArrayList<Instance>();
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		try{
			stm = conn.prepareStatement("select id,material, tip, csm, test_number, stage, frame from instance");
			result = stm.executeQuery();
			while(result.next()){
				Instance tmp = new Instance();
				tmp.setId(result.getInt(1));
				tmp.setMaterial(result.getString(2));
				tmp.setTip(result.getString(3));
				tmp.setCsm(result.getInt(4));
				tmp.setTest_number(result.getInt(5));
				tmp.setStage(result.getInt(6));
				tmp.setFrame(result.getString(7));
				list.add(tmp);
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		for(Instance it:list){
			it.setCurve(this.retrieveCurvePoint(it.getId()));
			it.setSsocurve(this.retrieveSSOCurveById(it.getId()));
			it.setInput(this.retrieveRequiredInputById(it.getId()));
			it.setCoefficientsTip(this.retrieveCoefficientsTip(it.getId()));
			it.setStage_obj(this.retrieveStageById(it.getStage()));
		}
		return list;
	}
	
	/*Dato l'input di un oggetto Instance, restituisce una lista contenete
	 * i punti per costruire le curve Load/Displacement e Harmonic contact stiffness/Displacement
	 */
	public List<CurvePoint> retrieveCurvePoint(int id){
		List<CurvePoint> curve = new ArrayList<CurvePoint>();
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		try{
			stm = conn.prepareStatement("select marker,displacementsurface,loadsample,timeonsample,harmoniccontact,hardness,modulus from curve where id_instance=? ");
			stm.setInt(1, id);
			result = stm.executeQuery();
			while(result.next()){
				CurvePoint tmp = new CurvePoint();
				tmp.setMarker(result.getString(1));
				tmp.setDisplacementsurface(result.getDouble(2));
				tmp.setLoadonsample(result.getDouble(3));
				tmp.setTimeonsample(result.getDouble(4));
				tmp.setHarmoniccontact(result.getDouble(5));
				tmp.setHardness(result.getDouble(6));
				tmp.setModulus(result.getDouble(7));
				curve.add(tmp);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return curve;
	}
	
	/*Dato l'id di un oggetto Instance, restituisce i punti della curva
	 * per la Stiffness Square Over Load/Displacement
	 */
	public List<SSOCurvePoint> retrieveSSOCurveById(int id){
		List<SSOCurvePoint> list = new ArrayList<SSOCurvePoint>();
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result=null;
		try{
			stm = conn.prepareStatement("select displacementsurface, ssoload from ssocurve where id_instance=?");
			stm.setInt(1, id);
			result = stm.executeQuery();
			while(result.next()){
				SSOCurvePoint tmp = new SSOCurvePoint();
				tmp.setDisplacementsurface(result.getDouble(1));
				tmp.setSsoload(result.getDouble(2));
				list.add(tmp);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		return list;
	}
	/*Dato l'id dell'oggetto Instance, restituisce per un instance
	 * i parametri di RequiredInput
	 */
	public RequiredInput retrieveRequiredInputById(int id){
		RequiredInput input = new RequiredInput();
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		try{
			stm = conn.prepareStatement("select surfacevelocity,depthlimit,strainrate,harmonicdisplacement, frequencytarget,surfacedistance, poissonsratio from requiredinput where id_instance=?");
			stm.setInt(1, id);
			result = stm.executeQuery();
			if(result.next()){
				input.setSurfacevelocity(result.getDouble(1));
				input.setDepthlimit(result.getDouble(2));
				input.setStrainrate(result.getDouble(3));
				input.setHarmonicdisplacement(result.getDouble(4));
				input.setFrequencytarget(result.getDouble(5));
				input.setSurfacedistance(result.getDouble(6));
				input.setPoissonsratio(result.getDouble(7));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		return input;
		
	}
	
	
	public List<String> retrieveAllTips(){
		Connection conn=data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		List<String> list = new ArrayList<String>();
		try{
			stm=conn.prepareStatement("select type from tip order by type");
			result = stm.executeQuery();
			while(result.next()){
				String tmp = result.getString(1);
				list.add(tmp);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		return list;
	}
	
	public int retrieveIdTip(String tip){
		Connection conn=data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		int id=0;
		try{
			stm=conn.prepareStatement("select id from tip where type=?");
			stm.setString(1, tip);
			result = stm.executeQuery();
			result.next();
			id=result.getInt(1);
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		return id;
	}
	
	public String retrieveTipById(int id){
		Connection conn=data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		String type=null;
		try{
			stm=conn.prepareStatement("select type from tip where id=?");
			stm.setInt(1, id);
			result = stm.executeQuery();
			result.next();
			type=result.getString(1);
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		return type;
	}
	
	
	
	public CoefficientsTip retrieveCoefficientsTip(int id){
		CoefficientsTip coeff = new CoefficientsTip();
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		try{
			stm = conn.prepareStatement("select m0,m1,m2,m3,m4,m5,m6,m7,m8 from tipcoefficients where instance_id=?");
			stm.setInt(1, id);
			result = stm.executeQuery();
			if(result.next()){
				coeff.setM0(result.getDouble(1));
				coeff.setM1(result.getDouble(2));
				coeff.setM2(result.getDouble(3));
				coeff.setM3(result.getDouble(4));
				coeff.setM4(result.getDouble(5));
				coeff.setM5(result.getDouble(6));
				coeff.setM6(result.getDouble(7));
				coeff.setM7(result.getDouble(8));
				coeff.setM8(result.getDouble(9));
			}
			
		}catch(SQLException e){
			
		}finally{
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				
			}
		}
		return coeff;
		
	}
	
	public int retrieveIdByStage(String stage){
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		int id = 0;
		try{
			stm = conn.prepareStatement("select id from stage where type=?");
			stm.setString(1, stage);
			result = stm.executeQuery();
			if(result.next())
				id = result.getInt(1);
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally{
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
		
		return id;
		
	}
	
	public Stage retrieveStageById(int id){
		Stage stage=null;
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		
		try{
			stm = conn.prepareStatement("select id, type from stage where id=?");
			stm.setInt(1, id);
			result = stm.executeQuery();
			if(result.next())
				stage = new Stage(result.getInt(1),result.getString(2));
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(result!=null)result.close();
				if(stm!=null)result.close();
				if(conn!=null)result.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return stage;
		
	}
	
	
	public String retrieveDescriptionById(int id){
		String ris = null;
		
		Connection conn  = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		
		try{
			stm = conn.prepareStatement("select description from descriptions where id_instance=?");
			stm.setInt(1, id);
			result = stm.executeQuery();
			if(result.next()){
				ris = result.getString(1);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(result!=null)result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return ris;
	}
	
	
	public String retrieveFormatById(int id){
		Connection conn = data.getConnection();
		PreparedStatement stm = null;
		ResultSet result = null;
		String ris = null;
		try{
			
			stm = conn.prepareStatement("select format from format where id_instance=?");
			stm.setInt(1, id);
			result = stm.executeQuery();
			if(result.next())
				ris = result.getString(1);
				
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(result!=null) result.close();
				if(stm!=null)stm.close();
				if(conn!=null)conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return ris;
	}

	
	
	
}
