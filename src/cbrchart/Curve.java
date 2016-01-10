package cbrchart;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import cbrmodel.SSOCurvePoint;
import cbrmodel.CurvePoint;
import cbrmodel.Instance;
import cbrmodel.LabelCurve;

public class Curve {
	
	public Curve(){
		
	}
	
	/*Si crea un XYDataset con parametro instance per la visualizzazione
	 * della curva Load/Displacement mediante JFreeChart
	 */
	public XYDataset createCurveLd(Instance instance){
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries s1 = new XYSeries(instance.getMaterial(),false);
		for(CurvePoint it:instance.getCurve()){
			s1.add(it.getDisplacementsurface(),it.getLoadonsample());
		}
		dataset.addSeries(s1);
		return dataset;
	}
	
	/*Si crea un XYDataset e si passano due parametri di tipo Instance 
	 * per costruire la curva Load/Displacement di una curva caricata e 
	 * della curva con max similarità recuperata dalla casebase
	 */
	public XYDataset createCurveLdIO(Instance input,Instance output){
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries s1 = new XYSeries("Input curve",false);
		for(CurvePoint it:input.getCurve()){
			s1.add(it.getDisplacementsurface(), it.getLoadonsample());
		}
		XYSeries s2 = new XYSeries("Case curve",false);
		for(CurvePoint it:output.getCurve()){
			s2.add(it.getDisplacementsurface(), it.getLoadonsample());
		}
		dataset.addSeries(s1);
		dataset.addSeries(s2);
		return dataset;
	}
	
	/*Si crea un XYDataset e si passa un parametro di tipo Instance per costruire
	 * una curva di Harmonic contact stiffness/Displacement
	 */
	public XYDataset createCurveHd(Instance input){
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries s1 = new XYSeries("Case curve",false);
		for(CurvePoint it:input.getCurve()){
			//if(Double.isFinite(it.getHarmoniccontact()))
				s1.add(it.getTimeonsample(), it.getHarmoniccontact());
		}	
		dataset.addSeries(s1);
		return dataset;
		
	}
	/*Si crea un XYDaataset e si passano due parametri di tipo Instance per costruire
	 * la curva di input e la curva del caso recuperato dalla casebase di 
	 * Harmonic contact stiffness/Displacement
	 */
	public XYDataset createCurveHdIO(Instance input, Instance output){
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries s1 = new XYSeries("Input curve",false);
		for(CurvePoint it:input.getCurve()){
			//if(Double.isFinite(it.getHarmoniccontact()))
				s1.add(it.getTimeonsample(), it.getHarmoniccontact());
		}
		XYSeries s2 = new XYSeries("Case curve",false);
		for(CurvePoint it:output.getCurve()){
			//if(Double.isFinite(it.getHarmoniccontact()))
				s2.add(it.getTimeonsample(), it.getHarmoniccontact());
		}
		
		dataset.addSeries(s1);
		dataset.addSeries(s2);
		return dataset;
		
	}
	
	/*Si crea un XYDataset e si passa un parametro di tipo Instance
	 * per costruire una curva Stiffness square over load/Displacement
	 */
	public XYDataset createCurveSSO(Instance input){
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries s1 = new XYSeries("Case curve");
		for(SSOCurvePoint it:input.getSsocurve()){
			if(!Double.isInfinite(it.getDisplacementsurface())   &&  !Double.isInfinite(it.getSsoload()))
			s1.add(it.getDisplacementsurface(), it.getSsoload());
		}
		dataset.addSeries(s1);
		return dataset;
		
	}
	
	
	/*Si crea un XYDataset e si passano due parametri di tipo Instance
	 * per creare la curva di input e quella per il caso recuperato
	 * di Stiffness square over load/displacement
	 */
	public XYDataset createCurveSSOIO(Instance input, Instance output){
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries s1 = new XYSeries("Input curve",false);
		for(SSOCurvePoint it:input.getSsocurve()){
			s1.add(it.getDisplacementsurface(), it.getSsoload());
		}
		XYSeries s2 = new XYSeries("Case curve",false);
		for(SSOCurvePoint it:output.getSsocurve()){
			s2.add(it.getDisplacementsurface(), it.getSsoload());
		}
		dataset.addSeries(s1);
		dataset.addSeries(s2);
		return dataset;
		
	}
	
	public void customProperties(JFreeChart chart, Instance instance){
		chart.getXYPlot().getRangeAxis().setRange(-10,instance.maxCurvePoint()+5.0);
		chart.getXYPlot().setBackgroundPaint(Color.white);
		chart.getXYPlot().setRangeGridlinePaint(Color.BLACK);
		chart.getXYPlot().setDomainGridlinePaint(Color.black);
		chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.GRAY);
	}
	
	public void customPropertiesHD(JFreeChart chart, Instance instance){
		chart.getXYPlot().getRangeAxis().setRange(-10,instance.maxCurvePointHD()+5.0);
		chart.getXYPlot().setBackgroundPaint(Color.white);
		chart.getXYPlot().setRangeGridlinePaint(Color.BLACK);
		chart.getXYPlot().setDomainGridlinePaint(Color.black);
		chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.GRAY);
	}
	
	public void customPropertiesSD(JFreeChart chart, Instance instance){
		chart.getXYPlot().getRangeAxis().setRange(-10,instance.maxCurvePointSD()+5.0);
		chart.getXYPlot().setBackgroundPaint(Color.white);
		chart.getXYPlot().setRangeGridlinePaint(Color.BLACK);
		chart.getXYPlot().setDomainGridlinePaint(Color.black);
		chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.GRAY);
	}
	
	
	public void customPropertiesTwoCurve(JFreeChart chart, Instance input, Instance output){
		if(input.maxCurvePoint()>=output.maxCurvePoint())
			chart.getXYPlot().getRangeAxis().setRange(-10,input.maxCurvePoint()+5.0);
		else
			chart.getXYPlot().getRangeAxis().setRange(-10,output.maxCurvePoint()+5.0);
		chart.getXYPlot().setBackgroundPaint(Color.white);
		chart.getXYPlot().setRangeGridlinePaint(Color.BLACK);
		chart.getXYPlot().setDomainGridlinePaint(Color.BLACK);
		chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.GRAY);


	}
	
	public void customPropertiesTwoCurveHD(JFreeChart chart, Instance input, Instance output){
		if(input.maxCurvePointHD()>=output.maxCurvePointHD())
			chart.getXYPlot().getRangeAxis().setRange(-10,input.maxCurvePointHD()+5.0);
		else
			chart.getXYPlot().getRangeAxis().setRange(-10,output.maxCurvePointHD()+5.0);
		chart.getXYPlot().setBackgroundPaint(Color.white);
		chart.getXYPlot().setRangeGridlinePaint(Color.BLACK);
		chart.getXYPlot().setDomainGridlinePaint(Color.BLACK);
		chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.GRAY);


	}
	
	public void customPropertiesTwoCurveSD(JFreeChart chart, Instance input, Instance output){
		if(input.maxCurvePointSD()>=output.maxCurvePointSD())
			chart.getXYPlot().getRangeAxis().setRange(-10,input.maxCurvePointSD()+5.0);
		else
			chart.getXYPlot().getRangeAxis().setRange(-10,output.maxCurvePointSD()+5.0);
		chart.getXYPlot().setBackgroundPaint(Color.white);
		chart.getXYPlot().setRangeGridlinePaint(Color.BLACK);
		chart.getXYPlot().setDomainGridlinePaint(Color.BLACK);
		chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.GRAY);


	}


	public void addLabelCurve(List<LabelCurve> list, XYItemRenderer render){
		for(LabelCurve it:list){
			XYTextAnnotation ann = new XYTextAnnotation(it.getLabel(),it.getX(),it.getY());
			ann.setFont(new Font("Serif",Font.BOLD,15));
			if(it.getLabel().contains("S")){
				ann.setPaint(Color.red);
			}
			if(it.getLabel().contains("M")  || it.getLabel().contains("N")){
				ann.setPaint(new Color(0,153,0));
			}
			if(it.getLabel().contains("L")){
				ann.setPaint(Color.BLUE);
			}
			if(it.getLabel().contains("U")){
				ann.setPaint(new Color(0,0,0));
			}
			render.addAnnotation(ann);	
		}
		
	}
	
	

}
