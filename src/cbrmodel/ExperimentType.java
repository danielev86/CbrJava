package cbrmodel;

public class ExperimentType {
	
	private String experiment;
	private int id_instance;
	
	
	public ExperimentType() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ExperimentType(String experiment, int id_instance) {
		super();
		this.experiment = experiment;
		this.id_instance = id_instance;
	}


	public String getExperiment() {
		return experiment;
	}


	public void setExperiment(String experiment) {
		this.experiment = experiment;
	}


	public int getId_instance() {
		return id_instance;
	}


	public void setId_instance(int id_instance) {
		this.id_instance = id_instance;
	}
	
	
	
	
	

}
