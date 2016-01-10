package cbrmodel;

public class Format {
	private String format;
	private int id;
	
	public Format(){
		
	}
	public Format(String format){
		this.format=format;
	}
	public Format(String format,int id){
		this.format=format;
		this.id=id;
		
	}
	
	
	public String getFormat(){
		return format;
	}
	
	
	public int getId(){
		return id;
	}
	
	
	public void setFormat(String format){
		this.format=format;
	}
	
	public void setId(int id){
		this.id=id;
	}

}
