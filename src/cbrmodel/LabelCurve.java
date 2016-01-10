package cbrmodel;

public class LabelCurve {
	private String label;
	private double x;
	private double y;
	public LabelCurve(String label, double x, double y) {
		super();
		this.label = label;
		this.x = x;
		this.y = y;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	
	

}
