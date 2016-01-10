package cbrmodel;

public class RequiredInput {
	private Double surfacevelocity;  //value surface velocity
	private Double depthlimit;	//value depth limit
	private Double strainrate;	//value strain rate target
	private Double harmonicdisplacement;	//value harmonic displacement
	private Double frequencytarget;	//value frequency target
	private Double surfacedistance;	//value surface distance
	private Double poissonsratio;	//value poissons ratio
	public RequiredInput(){
		
	}
	public RequiredInput(Double surfacevelocity, Double depthlimit, Double strainrate, Double harmonicdisplacement,
			Double frequencytarget, Double surfacedistance, Double poissonsratio) {
		super();
		this.surfacevelocity = surfacevelocity;
		this.depthlimit = depthlimit;
		this.strainrate = strainrate;
		this.harmonicdisplacement = harmonicdisplacement;
		this.frequencytarget = frequencytarget;
		this.surfacedistance = surfacedistance;
		this.poissonsratio = poissonsratio;
	}
	public Double getSurfacevelocity() {
		return surfacevelocity;
	}
	public void setSurfacevelocity(Double surfacevelocity) {
		this.surfacevelocity = surfacevelocity;
	}
	public Double getDepthlimit() {
		return depthlimit;
	}
	public void setDepthlimit(Double depthlimit) {
		this.depthlimit = depthlimit;
	}
	public Double getStrainrate() {
		return strainrate;
	}
	public void setStrainrate(Double strainrate) {
		this.strainrate = strainrate;
	}
	public Double getHarmonicdisplacement() {
		return harmonicdisplacement;
	}
	public void setHarmonicdisplacement(Double harmonicdisplacement) {
		this.harmonicdisplacement = harmonicdisplacement;
	}
	public Double getFrequencytarget() {
		return frequencytarget;
	}
	public void setFrequencytarget(Double frequencytarget) {
		this.frequencytarget = frequencytarget;
	}
	public Double getSurfacedistance() {
		return surfacedistance;
	}
	public void setSurfacedistance(Double surfacedistance) {
		this.surfacedistance = surfacedistance;
	}
	public Double getPoissonsratio() {
		return poissonsratio;
	}
	public void setPoissonsratio(Double poissonsratio) {
		this.poissonsratio = poissonsratio;
	}
	
	
	
	

}
