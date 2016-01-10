package cbrmodel;

import java.util.ArrayList;
import java.util.List;

public class CurvePoint {
	
	private String marker;
	private Double displacementsurface;
	private Double loadonsample;
	private Double timeonsample;
	private Double harmoniccontact;
	private Double hardness;
	private Double modulus;
	public CurvePoint(){
		
	}
	public CurvePoint(String marker, Double displacementsurface, Double loadonsample, Double timeonsample,
			Double harmoniccontact, Double hardness, Double modulus) {
		super();
		this.marker = marker;
		this.displacementsurface = displacementsurface;
		this.loadonsample = loadonsample;
		this.timeonsample = timeonsample;
		this.harmoniccontact = harmoniccontact;
		this.hardness = hardness;
		this.modulus = modulus;
	}
	public String getMarker() {
		return marker;
	}
	public void setMarker(String marker) {
		this.marker = marker;
	}
	public Double getDisplacementsurface() {
		return displacementsurface;
	}
	public void setDisplacementsurface(Double displacementsurface) {
		this.displacementsurface = displacementsurface;
	}
	public Double getLoadonsample() {
		return loadonsample;
	}
	public void setLoadonsample(Double loadonsample) {
		this.loadonsample = loadonsample;
	}
	public Double getTimeonsample() {
		return timeonsample;
	}
	public void setTimeonsample(Double timeonsample) {
		this.timeonsample = timeonsample;
	}
	public Double getHarmoniccontact() {
		return harmoniccontact;
	}
	public void setHarmoniccontact(Double harmoniccontact) {
		this.harmoniccontact = harmoniccontact;
	}
	public Double getHardness() {
		return hardness;
	}
	public void setHardness(Double hardness) {
		this.hardness = hardness;
	}
	public Double getModulus() {
		return modulus;
	}
	public void setModulus(Double modulus) {
		this.modulus = modulus;
	}
	
	
	
	

}
