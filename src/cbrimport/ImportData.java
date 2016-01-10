package cbrimport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cbrmodel.CurvePoint;
import cbrmodel.SSOCurvePoint;

public class ImportData {
	
	
	public ImportData(){
	}
	
	/*Data una stringa rappresentate il path di un file, si restituiscono
	 * un insieme di CurvePoint da usare per costruire le curve di Load/Displacement
	 * e di Harmonic contact stiffness/Displacement
	 */
	public List<CurvePoint> load_data_csv_curve_markers(String path) throws NumberFormatException, IOException{
		List<CurvePoint> curve = new ArrayList<CurvePoint>();
		BufferedReader reader  = new BufferedReader(new FileReader(path));
		String line = reader.readLine();
		reader.readLine();
		while((line=reader.readLine())!=null && (!line.contains("END"))){
			
			String[] nextline = line.split(";");
			nextline[1]=nextline[1].replace(",", ".");
			nextline[2]=nextline[2].replace(",", ".");
			nextline[3]=nextline[3].replace(",", ".");
			nextline[4]=nextline[4].replace(",", ".");
			nextline[5]=nextline[5].replace(",", ".");
			nextline[6]=nextline[6].replace(",", ".");
			CurvePoint fcTmp = new CurvePoint(nextline[0],
					Double.valueOf(nextline[1]),
					Double.valueOf(nextline[2]),
					Double.valueOf(nextline[3]),
					Double.valueOf(nextline[4]),
					Double.valueOf(nextline[5]),
					Double.valueOf(nextline[6]));
			curve.add(fcTmp);
			
			
		}
		reader.close();
		
		return curve;
		
	}
	
	
	public List<SSOCurvePoint> load_data_csv_SSOCurve(String path) throws IOException{
		List<SSOCurvePoint> curve = new ArrayList<SSOCurvePoint>();
		BufferedReader reader  = new BufferedReader(new FileReader(path));
		String line = reader.readLine();
		reader.readLine();
		while((line=reader.readLine())!=null && (!line.contains("END"))){
			
			String[] nextline = line.split(";");
			nextline[0]=nextline[0].replace(",", ".");
			nextline[1]=nextline[1].replace(",", ".");
			
			SSOCurvePoint fcTmp = new SSOCurvePoint(Double.valueOf(nextline[0]),
					Double.valueOf(nextline[1])
			);
			curve.add(fcTmp);
			
			
		}
		reader.close();
		
		return curve;
		
	}
	
	public List<Double> load_data_coefficient_tips(String path) throws IOException{
		List<Double> listCoeff = new ArrayList<Double>();
		BufferedReader reader  = new BufferedReader(new FileReader(path));
		String line = reader.readLine();
		reader.readLine();
		while((line=reader.readLine())!=null){
			String[] value=null;
			if(line.contains("m0") || line.contains("m1") || line.contains("m2")
					|| line.contains("m3") || line.contains("m4")  || line.contains("m5")
					|| line.contains("m6")|| line.contains("m7") || line.contains("m8")){
				String[] nextline = line.split("  ");
				String[] atmp = nextline[0].split(": ");
				String btmp = atmp[1];
				value =  btmp.split("\t");
				value[0] = value[0].replace(",", ".");
				listCoeff.add(Double.valueOf(value[0]));

				
			}
			
			
			
		}
		reader.close();
		return listCoeff;
		
	}

}
