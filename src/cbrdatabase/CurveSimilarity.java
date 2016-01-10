package cbrdatabase;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Iterator;
import java.util.List;



import cbrmodel.CurvePoint;
import cbrmodel.Instance;
import cbrmodel.Point;
import flanagan.interpolation.LinearInterpolation;

public class CurveSimilarity {
	
	public CurveSimilarity(){
		
	}
	
	/*Dato la curva load/displacement di input e quella recuperata dal caso
	 * si calcola il valore di similarità
	 */
	
	public double evaluateSimilarity(Instance input, Instance output){
		double x=0;
		double y=0;
		double z=0;
		double a=1; 
		double b=1;
		double c=1;
		double sim=0;
		x = calculateX(input,output);
		y = calculateY(input,output);
		z = calculateZ(input,output);
		sim = ((x*a)+(y*b)+(z*c))/(a+b+c);
		BigDecimal tmp = new BigDecimal(sim);
		tmp = tmp.round(new MathContext(5));
		return tmp.doubleValue();
	
		
	}
	
	
	
	public double calculateX(Instance input,Instance output){
		double x=0;
		int unloadII1 = input.positionMarker(input.getCurve(), "Unloading");
		int unloadOI2 = output.positionMarker(output.getCurve(), "Unloading");
		double inputDD = input.getCurve().get(unloadII1).getDisplacementsurface()/input.getCurve().get(input.getCurve().size()-1).getDisplacementsurface();
		double outputDD = (output.getCurve().get(unloadOI2).getDisplacementsurface()/output.getCurve().get(output.getCurve().size()-1).getDisplacementsurface());
		if(inputDD<=outputDD)
			x=inputDD/outputDD;
		else x=outputDD/inputDD;
		BigDecimal tmp = new BigDecimal(x);
		tmp = tmp.round(new MathContext(5));
		return tmp.doubleValue();
	}
	
	
	public double calculateY(Instance input, Instance output){
		double y=0;
		double slopeI = input.getSlope();
		double slopeO = output.getSlope();
		if(slopeI<=slopeO)
			y=slopeI/slopeO;
		else y=slopeO/slopeI;
		BigDecimal tmp = new BigDecimal(y);
		tmp = tmp.round(new MathContext(5));
		return tmp.doubleValue();
	
	}
	
	public double calculateZ(Instance input, Instance output){
		double z=0;
	
		
		double num1=0;
		double den11=0;
		double den12=0;
		double num2=0;
		double den21=0;
		double den22=0;
		Iterator<CurvePoint> i1 = input.getCurve().iterator();
		Iterator<CurvePoint> i2 = output.getCurve().iterator();
		
		while((i1.hasNext()) && (i2.hasNext())){
			CurvePoint i = i1.next();
			CurvePoint curve = i2.next();
			
			num1+= (i.getDisplacementsurface()*curve.getDisplacementsurface());
			num2+= (i.getLoadonsample()*curve.getLoadonsample());
			den11+=Math.pow(i.getDisplacementsurface(), 2);
			den21+=Math.pow(i.getLoadonsample(), 2);
			den12+=Math.pow(curve.getDisplacementsurface(), 2);
			den22+=Math.pow(curve.getLoadonsample(), 2);
		}
		z=((num1/(Math.pow(den11, (double)1/2)*Math.pow(den12, (double)1/2)))+(num2/(Math.pow(den21, (double)1/2)*Math.pow(den22, (double)1/2))))/2;
		
		BigDecimal tmp = new BigDecimal(z);
		tmp = tmp.round(new MathContext(5));
		return tmp.doubleValue();
	
	}
	
	
	
	public double evaluateSimilarity(List<CurvePoint> input, List<CurvePoint> caso){
		double sim = 0;
		double num1=0;
		double den11=0;
		double den12=0;
		double num2=0;
		double den21=0;
		double den22=0;
		
		if(input.size()<=caso.size()){
			Iterator<CurvePoint> i1 = input.iterator();
			Iterator<CurvePoint> i2 = caso.iterator();
			
			while((i1.hasNext()) && (i2.hasNext())){
				CurvePoint i = i1.next();
				CurvePoint c = i2.next();
				
				num1+= (i.getDisplacementsurface()*c.getDisplacementsurface());
				num2+= (i.getLoadonsample()*c.getLoadonsample());
				den11+=Math.pow(i.getDisplacementsurface(), 2);
				den21+=Math.pow(i.getLoadonsample(), 2);
				den12+=Math.pow(c.getDisplacementsurface(), 2);
				den22+=Math.pow(c.getLoadonsample(), 2);
			}
			sim=((num1/(Math.pow(den11, (double)1/2)*Math.pow(den12, (double)1/2)))+(num2/(Math.pow(den21, (double)1/2)*Math.pow(den22, (double)1/2))))/2;

			
		}else sim=0;
		
		return sim;
	}
	
	/*Data una lista di casi recuperati dalla casebase si restituisce
	 * quello con similarità massima
	 */
	public Instance maxSimilarity(List<Instance> list){
		Instance inst = null;
		double max=0;
		for(Instance it:list){
			if(max<=it.getSimilarity()){
				inst=it;
				max= it.getSimilarity();
			}
		}
		if(max==0) return list.get(0);
		else return inst;
		
	}

}
