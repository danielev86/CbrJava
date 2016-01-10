package cbrmodel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import flanagan.interpolation.LinearInterpolation;

public class Instance {
	
	private Integer id;
	private String material;
	private String tip;
	private Integer csm;
	private Integer test_number;
	private Integer stage;
	private String frame;
	private Double similarity;
	private List<CurvePoint> curve;
	private List<SSOCurvePoint> ssocurve;
	private RequiredInput input;
	private CoefficientsTip coefficientsTip;
	private Stage stage_obj;
	private Descriptions description;
	private Format format;
	public Instance(){
		
	}
	public Instance(String material){
		this.material=material;
	}

	public Instance(Integer id, String material, String tip, Integer csm, Integer test_number, Integer stage,
			String frame) {
		super();
		this.id = id;
		this.material = material;
		this.tip = tip;
		this.csm = csm;
		this.test_number = test_number;
		this.stage = stage;
		this.frame = frame;
	}
	
	

	public Format getFormat() {
		return format;
	}
	public void setFormat(Format format) {
		this.format = format;
	}
	public Descriptions getDescription() {
		return description;
	}
	public void setDescription(Descriptions description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Integer getCsm() {
		return csm;
	}

	public void setCsm(Integer csm) {
		this.csm = csm;
	}

	public Integer getTest_number() {
		return test_number;
	}

	public void setTest_number(Integer test_number) {
		this.test_number = test_number;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public String getFrame() {
		return frame;
	}

	public void setFrame(String frame) {
		this.frame = frame;
	}

	public Double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(Double similarity) {
		this.similarity = similarity;
	}

	public List<CurvePoint> getCurve() {
		return curve;
	}

	public void setCurve(List<CurvePoint> curve) {
		this.curve = curve;
	}

	public List<SSOCurvePoint> getSsocurve() {
		return ssocurve;
	}

	public void setSsocurve(List<SSOCurvePoint> ssocurve) {
		this.ssocurve = ssocurve;
	}

	public RequiredInput getInput() {
		return input;
	}

	public void setInput(RequiredInput input) {
		this.input = input;
	}
	
	
	public CoefficientsTip getCoefficientsTip() {
		return coefficientsTip;
	}
	
	public void setCoefficientsTip(CoefficientsTip coefficientsTip) {
		this.coefficientsTip = coefficientsTip;
	}
	
	
	public Stage getStage_obj() {
		return stage_obj;
	}
	public void setStage_obj(Stage stage_obj) {
		this.stage_obj = stage_obj;
	}
	public double maxCurvePoint(){
		double max = 0;
		for(CurvePoint it:this.curve){
			if(max<=it.getLoadonsample()){
				max=it.getLoadonsample();
			}
		}
		return max;
	}
	
	public List<LabelCurve> getCurveLabelLD(){
		List<LabelCurve> labels = new ArrayList<LabelCurve>();
		int i=1;
		for(CurvePoint it:this.curve){
			if(it.getMarker().contains("Surface")){
				labels.add(new LabelCurve("S",it.getDisplacementsurface(),it.getLoadonsample()));
			}else if(it.getMarker().contains("Min")){
				labels.add(new LabelCurve("M",it.getDisplacementsurface(),it.getLoadonsample()));
			}else if(it.getMarker().contains("Max")){
				labels.add(new LabelCurve("N",it.getDisplacementsurface(),it.getLoadonsample()));

			}else if(it.getMarker().contains("End")){
				labels.add(new LabelCurve("L",it.getDisplacementsurface(),it.getLoadonsample()));

			}else if(it.getMarker().contains("Unloading")){
				labels.add(new LabelCurve("U",it.getDisplacementsurface(),it.getLoadonsample()));

			}
		}
		return labels;
		
	}
	
	public double maxCurvePointHD(){
		double max = 0;
		for(CurvePoint it:this.curve){
			if((max<=it.getHarmoniccontact()) &&(Double.isFinite(it.getHarmoniccontact()))){
				max=it.getHarmoniccontact();
			}
		}
		return max;
	}
	
	public List<LabelCurve> getCurveLabelHD(){
		List<LabelCurve> list = new ArrayList<LabelCurve>();
		List<LabelCurve> tmp = this.getCurveLabelLD();
		for(CurvePoint it1:this.curve){
			for(LabelCurve it2:tmp){
				if(it1.getDisplacementsurface()==it2.getX())
					list.add(new LabelCurve(it2.getLabel(),it1.getTimeonsample(),it1.getHarmoniccontact()));
			}
		}
		
		return list;
	}
	
	public double maxCurvePointSD(){
		double max = 0;
		for(SSOCurvePoint it:this.ssocurve){
			if((max<=it.getSsoload()) &&(Double.isFinite(it.getSsoload()))){
				max=it.getSsoload();
			}
		}
		return max;
	}
	
	public List<LabelCurve> getCurveLabelSD(){
		List<LabelCurve> list = new ArrayList<LabelCurve>();
		List<LabelCurve> tmp = this.getCurveLabelLD();
		for(SSOCurvePoint it1:this.ssocurve){
			for(LabelCurve it2:tmp){
				if(it1.getDisplacementsurface()==it2.getX())
					list.add(new LabelCurve(it2.getLabel(),it1.getDisplacementsurface(),it1.getSsoload()));
			}
		}
		
		return list;
	}
	
	public int positionMarker(List<CurvePoint> curve,String val){
		int res=0;
		for(CurvePoint it:curve){
			if(it.getMarker().contains(val)){
				return res+1;
			}else res++;
		}
		return 0;
		
		
	}
	
	
	public Double calculateAvgModulus(){
		Double ris=0.0;
		int i=0;
		int posInit=positionMarker(this.curve,"Min");
		int posFin=positionMarker(this.curve,"Max");
		for(int j=posInit;j<=posFin;j++){
			ris+=this.curve.get(j).getModulus();
			i++;
		}
		
		BigDecimal tmp = new BigDecimal(ris/i);
		tmp = tmp.round(new MathContext(5));
		return  tmp.doubleValue();
	}
	
	public Double calculateAvgHardness(){
		Double ris=0.0;
		int i=0;
		int posInit=positionMarker(this.curve,"Min");
		int posFin=positionMarker(this.curve,"Max");
		for(int j=posInit;j<=posFin;j++){
			ris+=this.curve.get(j).getHardness();
			i++;
		}
		
		
		BigDecimal tmp = new BigDecimal(ris/i);
		tmp = tmp.round(new MathContext(5));
		return  tmp.doubleValue();
	
	}
	
	public Point getPointHalfCurve(){
		int posInit=positionMarker(this.curve,"Unloading");
		int posFin=(((this.curve.size()-posInit)/2)+posInit);
		return new Point(this.curve.get(posFin).getDisplacementsurface(),this.curve.get(posFin).getLoadonsample());		
	}
	
	
	public double getHFinal(){
		BigDecimal tmp = new BigDecimal(this.curve.get(this.curve.size()-1).getDisplacementsurface());
		tmp = tmp.round(new MathContext(5));
		return  tmp.doubleValue();
	}
	
	public double getSlope(){
		double[] ris = null;
		int posInit=positionMarker(this.curve,"Unloading");
		int posFin=(((this.curve.size()-1-posInit)/2)+posInit);
		SimpleRegression reg = new SimpleRegression();
		for(int i=posInit;i<posFin;i++){
			
			reg.addData(this.curve.get(i).getDisplacementsurface(), this.curve.get(i).getLoadonsample());
		
		}
		BigDecimal tmp = new BigDecimal(reg.getSlope());
		tmp = tmp.round(new MathContext(5));
		return  tmp.doubleValue();
//		
		
	}
	
	public double getDeltaDisplacementSurface(){
		int unloadII1 = positionMarker(this.curve, "Unloading");
		int unloadIF1 = this.curve.size()-1;
		BigDecimal tmp = new BigDecimal(this.curve.get(unloadII1).getDisplacementsurface()/this.curve.get(unloadIF1).getDisplacementsurface());
		tmp = tmp.round(new MathContext(5));
		return  tmp.doubleValue();
//		
	}
	
	public Point getMaxPoint(){
		double max = 0;
		Point ris = null;
		double tmp=0.0;
		int i=0;
		int posInit=positionMarker(this.curve,"Unloading");
		int posFin=(((this.curve.size()-1-posInit)/2)+posInit);
		for(int j=posInit;j<posFin;j++){
			tmp = this.curve.get(j).getDisplacementsurface();
			if(max<=tmp){
				max=tmp;
				ris = new Point(this.curve.get(j).getDisplacementsurface(),this.curve.get(j).getLoadonsample());
			}
		
		}
		
		return ris;
	}
	
	public Point getMaxPointCurve(){
		double max = 0;
		Point ris = null;
		double tmp=0.0;
		int i=0;
		int posInit=positionMarker(this.curve,"Unloading");
		int posFin=(((this.curve.size()-1)));
		for(int j=posInit;j<posFin;j++){
			tmp = this.curve.get(j).getDisplacementsurface();
			if(max<=tmp){
				max=tmp;
				ris = new Point(this.curve.get(j).getDisplacementsurface(),this.curve.get(j).getLoadonsample());
			}
		
		}
		
		return ris;
	}
	
	
	public List<CurvePoint> getLoadingCurve(){
		int posInit=0;
		int posFin = positionMarker(this.curve,"End");
		List<CurvePoint> loadCurve= new ArrayList<CurvePoint>();
		for(posInit=0;posInit<posFin;posInit++){
			
			CurvePoint tmp = this.curve.get(posInit);
			loadCurve.add(tmp);
			
			
		}
		
		return loadCurve;
	}
	
	
	public double calculateDeltaPOnDeltaD(List<CurvePoint> list){
		SimpleRegression reg = new SimpleRegression();
		for(int i=0;i<list.size()-1;i++){
			reg.addData(list.get(i).getDisplacementsurface(), list.get(i).getLoadonsample());
		}
		BigDecimal tmp = new BigDecimal(reg.getSlope());
		tmp = tmp.round(new MathContext(5));
		return tmp.doubleValue();
	}
	
	public boolean verifyPlateuExist(){
		double tmp = 0;
		int pos_actual=0;
		List<CurvePoint> loadHalf = getLoadingCurve();
		int numberChunk = (int) Math.ceil(loadHalf.size()/10);
		for(int i=0;i<10;i++){
			if(i<3){
				
				pos_actual = numberChunk+1;
				System.out.println(i+" "+tmp);
				//if(tmp<0.2) return true;
				
			}if(i==9){
				tmp = calculateDeltaPOnDeltaD(loadHalf.subList(pos_actual,loadHalf.size()-1));
				System.out.println(i+" "+tmp);
				if(tmp<0.0009) return true;
			}if(i>3){
				tmp = calculateDeltaPOnDeltaD(loadHalf.subList(pos_actual,pos_actual+numberChunk));
				System.out.println(i+" "+tmp);
				if(tmp<0.0009) return true;
				pos_actual = pos_actual+numberChunk;
			}
		}
		return false;
	}
	
	
	
	
	
	public double slope(){
		return (getMaxPoint().getY()-getPointHalfCurve().getY())/(getMaxPoint().getX()-getPointHalfCurve().getX());
	}
	
	
	
	
	
	
	
	

}
