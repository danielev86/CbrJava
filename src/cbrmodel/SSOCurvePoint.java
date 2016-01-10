package cbrmodel;

public class SSOCurvePoint {
	
	private Double displacementsurface;
	private Double ssoload;
	public SSOCurvePoint(){
		
	}
	
	public SSOCurvePoint(Double displacementsurface, Double ssoload) {
		super();
		this.displacementsurface = displacementsurface;
		this.ssoload = ssoload;
	}
	public Double getDisplacementsurface() {
		return displacementsurface;
	}
	public void setDisplacementsurface(Double displacementsurface) {
		this.displacementsurface = displacementsurface;
	}
	public Double getSsoload() {
		return ssoload;
	}
	public void setSsoload(Double ssoload) {
		this.ssoload = ssoload;
	}
	
	

}
