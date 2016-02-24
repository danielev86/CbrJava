package cbrmain;

import java.awt.BorderLayout;
import java.awt.Color;

import cbrdatabase.CbrData;
import cbrdatabase.CurveSimilarity;
import cbrmodel.Instance;
import cbrmodel.LabelCurve;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;

import cbrchart.Curve;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RetrieveJF extends JFrame {

	private JPanel contentPane;
	private CbrData cbr;
	private JTextField txtSurfaceV;
	private JTextField txtDepth;
	private JTextField txtStrain;
	private JTextField txtHarmonic;
	private JTextField txtFrequency;
	private JTextField txtSurfaceD;
	private JTextField txtPoissons;
	private JInternalFrame curveFrame;
	private JComboBox cbxCurves;
	private JFreeChart chart;
	private ChartPanel panel;
	private JTextField txtSimilarity;
	private JTextField txtMaterial;
	private String[] array;
	private List<Instance> cases;
	private CurveSimilarity sim;
	private Curve curve;
	private Instance maxInstance;
	private JTextField txtModulus;
	private JTextField txtHardness;
	private JLabel lblImportSampleParameter;
	private JButton btnSample;
	private JTextField txtDeltaC;
	private JTextField txtDeltaI;
	private JTextField txtPC;
	private JTextField txtPI;
	private JTextField txtHMaxC;
	private JTextField txtHMaxI;
	private JTextField txtHFC;
	private JTextField txtHFI;
	private JTextField txtSC;
	private JTextField txtSI;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtZ;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RetrieveJF frame = new RetrieveJF();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public RetrieveJF(Instance input) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				curveFrame.setTitle("P-h curve");
				curveFrame.remove(panel);
				curveFrame.revalidate();
				XYDataset dataset = curve.createCurveLdIO(input, maxInstance);
				chart = ChartFactory.createXYLineChart(
			            maxInstance.getMaterial(),      // chart title
			            "DISPLACEMENT",                      // x axis label
			            "LOAD",                      // y axis label
			            dataset,                  // data
			            PlotOrientation.VERTICAL,
			            true,                     // include legend
			            true,                     // tooltips
			            false                     // urls
			        );
				XYItemRenderer render = chart.getXYPlot().getRenderer();
				panel = new ChartPanel(chart);
				List<LabelCurve> list = input.getCurveLabelLD();
				curve.customPropertiesTwoCurve(chart, input, maxInstance);
				curve.addLabelCurve(list, render);
				curve.addLabelCurve(maxInstance.getCurveLabelLD(), render);
				
			
				curveFrame.getContentPane().add(panel);		
				
			}
		});
		setTitle("Retrieve a case");
		cbr = new CbrData();
		cases = cbr.retrieveInstancesByMaterial(input.getMaterial());
		sim = new CurveSimilarity();
		
		for(Instance it:cases)
			it.setSimilarity(sim.evaluateSimilarity(input, it));
		maxInstance=sim.maxSimilarity(cases);
		curve = new Curve();
		
		
		array = new String[]{"P-h Curve","HCS-time Curve","SSO-h Curve"};
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		curveFrame = new JInternalFrame("");
		curveFrame.setBounds(380, 0, 884, 662);

		contentPane.add(curveFrame);
		
		panel = new ChartPanel(chart);
		panel.setLayout(new BorderLayout(0, 0));

		curveFrame.getContentPane().add(panel);
		
		
		JLabel lblSimilarity = new JLabel("Similarity");
		lblSimilarity.setBounds(10, 34, 149, 14);
		contentPane.add(lblSimilarity);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(10, 59, 149, 14);
		contentPane.add(lblMaterial);
		
		JLabel lblCaseRetrieved = new JLabel("CASE RETRIEVED");
		lblCaseRetrieved.setBounds(10, 9, 149, 14);
		contentPane.add(lblCaseRetrieved);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 84, 379, 2);
		contentPane.add(separator);
		
		JLabel lblRequiredInput = new JLabel("REQUIRED INPUT");
		lblRequiredInput.setBounds(10, 97, 149, 14);
		contentPane.add(lblRequiredInput);
		
		JLabel lblSurfaceVelocity = new JLabel("Surface approach velocity (nm/s)");
		lblSurfaceVelocity.setBounds(10, 122, 176, 14);
		contentPane.add(lblSurfaceVelocity);
		
		JLabel lblDepthLimit = new JLabel("Depth limit (nm)");
		lblDepthLimit.setBounds(10, 147, 176, 14);
		contentPane.add(lblDepthLimit);
		
		JLabel lblStrainRateTarget = new JLabel("Strain rate target (1/s)");
		lblStrainRateTarget.setBounds(10, 172, 176, 14);
		contentPane.add(lblStrainRateTarget);
		
		JLabel lblHarmonicDisplacement = new JLabel("Harmonic displacement (nm)");
		lblHarmonicDisplacement.setBounds(10, 197, 176, 14);
		contentPane.add(lblHarmonicDisplacement);
		
		JLabel lblFrequencyTarget = new JLabel("Frequency target (Hz)");
		lblFrequencyTarget.setBounds(10, 222, 176, 14);
		contentPane.add(lblFrequencyTarget);
		
		JLabel lblSurfaceDistance = new JLabel("Surface approach distance (nm)");
		lblSurfaceDistance.setBounds(10, 247, 176, 14);
		contentPane.add(lblSurfaceDistance);
		
		JLabel lblPoissonsRatio = new JLabel("Poisson's ratio");
		lblPoissonsRatio.setBounds(10, 272, 176, 14);
		contentPane.add(lblPoissonsRatio);
		
		txtSurfaceV = new JTextField();
		txtSurfaceV.setBounds(220, 119, 123, 20);
		contentPane.add(txtSurfaceV);
		txtSurfaceV.setColumns(10);
		txtSurfaceV.setText(String.valueOf(maxInstance.getInput().getSurfacevelocity()));
		
		txtDepth = new JTextField();
		txtDepth.setText("");
		txtDepth.setBounds(220, 144, 123, 20);
		contentPane.add(txtDepth);
		txtDepth.setColumns(10);
		txtDepth.setText(String.valueOf(maxInstance.getInput().getDepthlimit()));
		
		txtStrain = new JTextField();
		txtStrain.setText("");
		txtStrain.setBounds(220, 169, 123, 20);
		contentPane.add(txtStrain);
		txtStrain.setColumns(10);
		txtStrain.setText(String.valueOf(maxInstance.getInput().getStrainrate()));
		
		txtHarmonic = new JTextField();
		txtHarmonic.setText("");
		txtHarmonic.setBounds(220, 194, 123, 20);
		contentPane.add(txtHarmonic);
		txtHarmonic.setColumns(10);
		txtHarmonic.setText(String.valueOf(maxInstance.getInput().getHarmonicdisplacement()));
		
		txtFrequency = new JTextField();
		txtFrequency.setText("");
		txtFrequency.setBounds(220, 219, 123, 20);
		contentPane.add(txtFrequency);
		txtFrequency.setColumns(10);
		txtFrequency.setText(String.valueOf(maxInstance.getInput().getFrequencytarget()));
		
		txtSurfaceD = new JTextField();
		txtSurfaceD.setText("");
		txtSurfaceD.setBounds(220, 244, 123, 20);
		contentPane.add(txtSurfaceD);
		txtSurfaceD.setColumns(10);
		txtSurfaceD.setText(String.valueOf(maxInstance.getInput().getSurfacedistance()));
		
		txtPoissons = new JTextField();
		txtPoissons.setText("");
		txtPoissons.setBounds(220, 269, 123, 20);
		contentPane.add(txtPoissons);
		txtPoissons.setColumns(10);
		txtPoissons.setText(String.valueOf(maxInstance.getInput().getPoissonsratio()));
		
		cbxCurves = new JComboBox(array);
		cbxCurves.setSelectedIndex(0);
		cbxCurves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tmp = (String) cbxCurves.getSelectedItem();
				if(!tmp.contains("Select")){
					if(tmp.contains("P-h")){
						curveFrame.setTitle(tmp);
						curveFrame.remove(panel);
						curveFrame.revalidate();
						XYDataset dataset = curve.createCurveLdIO(input, maxInstance);
						chart = ChartFactory.createXYLineChart(
					            maxInstance.getMaterial(),      // chart title
					            "DISPLACEMENT",                      // x axis label
					            "LOAD",                      // y axis label
					            dataset,                  // data
					            PlotOrientation.VERTICAL,
					            true,                     // include legend
					            true,                     // tooltips
					            false                     // urls
					        );
						XYItemRenderer render = chart.getXYPlot().getRenderer();
						panel = new ChartPanel(chart);
						List<LabelCurve> list = input.getCurveLabelLD();
						curve.customPropertiesTwoCurve(chart, input, maxInstance);
						curve.addLabelCurve(list, render);
						curve.addLabelCurve(maxInstance.getCurveLabelLD(), render);
						
						curveFrame.getContentPane().add(panel);						
						
					}else if(tmp.contains("HCS")){
						curveFrame.setTitle(tmp);
						curveFrame.remove(panel);
						curveFrame.revalidate();
						XYDataset  dataset = curve.createCurveHdIO(input, maxInstance);
						chart = ChartFactory.createXYLineChart(
					            maxInstance.getMaterial(),      // chart title
					            "TIME ON SAMPLE",                      // x axis label
					            "HARMONIC CONTACT STIFFNESS",                      // y axis label
					            dataset,                  // data
					            PlotOrientation.VERTICAL,
					            true,                     // include legend
					            true,                     // tooltips
					            false                     // urls
					        );
						panel = new ChartPanel(chart);
						XYItemRenderer render = chart.getXYPlot().getRenderer();
						curve.customPropertiesTwoCurveHD(chart, input, maxInstance);
						curve.addLabelCurve(input.getCurveLabelHD(), render);
						curve.addLabelCurve(maxInstance.getCurveLabelHD(), render);
						panel.setDomainZoomable(false);
						panel.setRangeZoomable(false);
						curveFrame.getContentPane().add(panel);						
						
					}else if(tmp.contains("SSO")){
						curveFrame.setTitle(tmp);
						curveFrame.remove(panel);
						curveFrame.revalidate();
						XYDataset dataset = curve.createCurveSSOIO(input, maxInstance);
						chart = ChartFactory.createXYLineChart(
					            maxInstance.getMaterial(),      // chart title
					            "DISPLACEMENT",                      // x axis label
					            "STIFFNESS SQUARED OVER LOAD",                      // y axis label
					            dataset,                  // data
					            PlotOrientation.VERTICAL,
					            true,                     // include legend
					            true,                     // tooltips
					            false                     // urls
					        );
						panel = new ChartPanel(chart);
						XYItemRenderer render = chart.getXYPlot().getRenderer();
						curve.customPropertiesTwoCurveSD(chart, input, maxInstance);
						curve.addLabelCurve(input.getCurveLabelSD(), render);
						curve.addLabelCurve(maxInstance.getCurveLabelSD(), render);
						panel.setDomainZoomable(false);
						panel.setRangeZoomable(false);

						curveFrame.getContentPane().add(panel);					
						}
					
				}
				
			}
		});
		cbxCurves.setBounds(167, 481, 176, 20);
		contentPane.add(cbxCurves);
		
		txtSimilarity = new JTextField();
		txtSimilarity.setBounds(220, 31, 123, 20);
		contentPane.add(txtSimilarity);
		txtSimilarity.setColumns(10);
		txtSimilarity.setText(String.valueOf(maxInstance.getSimilarity()));
		
		txtMaterial = new JTextField();
		txtMaterial.setText("");
		txtMaterial.setBounds(220, 56, 123, 20);
		contentPane.add(txtMaterial);
		txtMaterial.setColumns(10);
		txtMaterial.setText(String.valueOf(maxInstance.getMaterial()));
		
		JLabel lblAvgModulusgpa = new JLabel("Avg modulus (GPa)");
		lblAvgModulusgpa.setBounds(11, 320, 148, 14);
		contentPane.add(lblAvgModulusgpa);
		
		JLabel lblAvgHardnessgpa = new JLabel("Avg hardness (GPa)");
		lblAvgHardnessgpa.setBounds(10, 345, 176, 14);
		contentPane.add(lblAvgHardnessgpa);
		
		txtModulus = new JTextField();
		txtModulus.setBounds(220, 317, 123, 20);
		contentPane.add(txtModulus);
		txtModulus.setColumns(10);
		txtModulus.setText(String.valueOf(maxInstance.calculateAvgModulus()));
		
		txtHardness = new JTextField();
		txtHardness.setText("");
		txtHardness.setBounds(220, 342, 123, 20);
		contentPane.add(txtHardness);
		txtHardness.setColumns(10);
		txtHardness.setText(String.valueOf(maxInstance.calculateAvgHardness()));
		curveFrame.setVisible(true);
	}
	/**
	 * @wbp.parser.constructor
	 */
	public RetrieveJF(Instance input, Instance maxInstance, boolean verifica, boolean newm, String material, String format) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				if(!verifica){
					btnSample.setVisible(false);
					lblImportSampleParameter.setVisible(false);
				}
				curveFrame.setTitle("P-h curve");
				curveFrame.remove(panel);
				curveFrame.revalidate();
				XYDataset dataset = curve.createCurveLdIO(input, maxInstance);
				chart = ChartFactory.createXYLineChart(
			            maxInstance.getMaterial(),      // chart title
			            "DISPLACEMENT",                      // x axis label
			            "LOAD",                      // y axis label
			            dataset,                  // data
			            PlotOrientation.VERTICAL,
			            true,                     // include legend
			            true,                     // tooltips
			            false                     // urls
			        );
				XYItemRenderer render = chart.getXYPlot().getRenderer();
				panel = new ChartPanel(chart);
				List<LabelCurve> list = input.getCurveLabelLD();
				curve.customPropertiesTwoCurve(chart, input, maxInstance);
				curve.addLabelCurve(list, render);
				curve.addLabelCurve(maxInstance.getCurveLabelLD(), render);
				
			
				curveFrame.getContentPane().add(panel);		
				
			}
		});
		setTitle("Retrieve a case");
		cbr = new CbrData();
		/*cases = cbr.retrieveInstancesByMaterial(value);
		sim = new CurveSimilarity();
		
		for(Instance it:cases)
			it.setSimilarity(sim.evaluateSimilarity(input, it));
		maxInstance=sim.maxSimilarity(cases);*/
		curve = new Curve();
		
		sim = new CurveSimilarity();
		array = new String[]{"P-h Curve","HCS-time Curve","SSO-h curve"};
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		curveFrame = new JInternalFrame("");
		curveFrame.setBounds(380, 0, 884, 672);

		contentPane.add(curveFrame);
		
		panel = new ChartPanel(chart);
		panel.setLayout(new BorderLayout(0, 0));

		curveFrame.getContentPane().add(panel, BorderLayout.NORTH);
		
		
		JLabel lblSimilarity = new JLabel("Similarity");
		lblSimilarity.setBounds(159, 59, 65, 14);
		contentPane.add(lblSimilarity);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(10, 114, 149, 14);
		contentPane.add(lblMaterial);
		
		JLabel lblCaseRetrieved = new JLabel("CASE RETRIEVED");
		lblCaseRetrieved.setBounds(10, 9, 149, 14);
		contentPane.add(lblCaseRetrieved);
		
		
		JLabel lblRequiredInput = new JLabel("REQUIRED INPUT");
		lblRequiredInput.setBounds(10, 161, 149, 14);
		contentPane.add(lblRequiredInput);
		
		JLabel lblSurfaceVelocity = new JLabel("Surface approach velocity (nm/s)");
		lblSurfaceVelocity.setBounds(10, 186, 176, 14);
		contentPane.add(lblSurfaceVelocity);
		
		JLabel lblDepthLimit = new JLabel("Depth limit (nm)");
		lblDepthLimit.setBounds(10, 211, 176, 14);
		contentPane.add(lblDepthLimit);
		
		JLabel lblStrainRateTarget = new JLabel("Strain rate target (1/s)");
		lblStrainRateTarget.setBounds(10, 236, 176, 14);
		contentPane.add(lblStrainRateTarget);
		
		JLabel lblHarmonicDisplacement = new JLabel("Harmonic displacement (nm)");
		lblHarmonicDisplacement.setBounds(10, 261, 176, 14);
		contentPane.add(lblHarmonicDisplacement);
		
		JLabel lblFrequencyTarget = new JLabel("Frequency target (Hz)");
		lblFrequencyTarget.setBounds(10, 286, 176, 14);
		contentPane.add(lblFrequencyTarget);
		
		JLabel lblSurfaceDistance = new JLabel("Surface approach distance (nm)");
		lblSurfaceDistance.setBounds(10, 311, 176, 14);
		contentPane.add(lblSurfaceDistance);
		
		JLabel lblPoissonsRatio = new JLabel("Poisson's ratio");
		lblPoissonsRatio.setBounds(10, 336, 176, 14);
		contentPane.add(lblPoissonsRatio);
		
		txtSurfaceV = new JTextField();
		txtSurfaceV.setBounds(247, 183, 123, 20);
		contentPane.add(txtSurfaceV);
		txtSurfaceV.setColumns(10);
		txtSurfaceV.setText(String.valueOf(maxInstance.getInput().getSurfacevelocity()));
		
		txtDepth = new JTextField();
		txtDepth.setText("");
		txtDepth.setBounds(247, 208, 123, 20);
		contentPane.add(txtDepth);
		txtDepth.setColumns(10);
		txtDepth.setText(String.valueOf(maxInstance.getInput().getDepthlimit()));
		
		txtStrain = new JTextField();
		txtStrain.setText("");
		txtStrain.setBounds(247, 233, 123, 20);
		contentPane.add(txtStrain);
		txtStrain.setColumns(10);
		txtStrain.setText(String.valueOf(maxInstance.getInput().getStrainrate()));
		
		txtHarmonic = new JTextField();
		txtHarmonic.setText("");
		txtHarmonic.setBounds(247, 258, 123, 20);
		contentPane.add(txtHarmonic);
		txtHarmonic.setColumns(10);
		txtHarmonic.setText(String.valueOf(maxInstance.getInput().getHarmonicdisplacement()));
		
		txtFrequency = new JTextField();
		txtFrequency.setText("");
		txtFrequency.setBounds(247, 283, 123, 20);
		contentPane.add(txtFrequency);
		txtFrequency.setColumns(10);
		txtFrequency.setText(String.valueOf(maxInstance.getInput().getFrequencytarget()));
		
		txtSurfaceD = new JTextField();
		txtSurfaceD.setText("");
		txtSurfaceD.setBounds(247, 308, 123, 20);
		contentPane.add(txtSurfaceD);
		txtSurfaceD.setColumns(10);
		txtSurfaceD.setText(String.valueOf(maxInstance.getInput().getSurfacedistance()));
		
		txtPoissons = new JTextField();
		txtPoissons.setText("");
		txtPoissons.setBounds(247, 333, 123, 20);
		contentPane.add(txtPoissons);
		txtPoissons.setColumns(10);
		txtPoissons.setText(String.valueOf(maxInstance.getInput().getPoissonsratio()));
		
		
		
		
		
		cbxCurves = new JComboBox(array);
		cbxCurves.setSelectedIndex(0);
		cbxCurves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tmp = (String) cbxCurves.getSelectedItem();
				if(!tmp.contains("Select")){
					if(tmp.contains("P-h")){
						curveFrame.setTitle(tmp);
						curveFrame.remove(panel);
						curveFrame.revalidate();
						XYDataset dataset = curve.createCurveLdIO(input, maxInstance);
						chart = ChartFactory.createXYLineChart(
					            maxInstance.getMaterial(),      // chart title
					            "DISPLACEMENT",                      // x axis label
					            "LOAD",                      // y axis label
					            dataset,                  // data
					            PlotOrientation.VERTICAL,
					            true,                     // include legend
					            true,                     // tooltips
					            false                     // urls
					        );
						XYItemRenderer render = chart.getXYPlot().getRenderer();
						panel = new ChartPanel(chart);
						List<LabelCurve> list = input.getCurveLabelLD();
						curve.customPropertiesTwoCurve(chart, input, maxInstance);
						curve.addLabelCurve(list, render);
						curve.addLabelCurve(maxInstance.getCurveLabelLD(), render);
						
						curveFrame.getContentPane().add(panel);						
						
					}else if(tmp.contains("HCS")){
						curveFrame.setTitle(tmp);
						curveFrame.remove(panel);
						curveFrame.revalidate();
						XYDataset  dataset = curve.createCurveHdIO(input, maxInstance);
						chart = ChartFactory.createXYLineChart(
					            maxInstance.getMaterial(),      // chart title
					            "TIME ON SAMPLE",                      // x axis label
					            "HARMONIC CONTACT STIFFNESS",                      // y axis label
					            dataset,                  // data
					            PlotOrientation.VERTICAL,
					            true,                     // include legend
					            true,                     // tooltips
					            false                     // urls
					        );
						panel = new ChartPanel(chart);
						XYItemRenderer render = chart.getXYPlot().getRenderer();
						curve.customPropertiesTwoCurveHD(chart, input, maxInstance);
						curve.addLabelCurve(input.getCurveLabelHD(), render);
						curve.addLabelCurve(maxInstance.getCurveLabelHD(), render);
						panel.setDomainZoomable(false);
						panel.setRangeZoomable(false);
						curveFrame.getContentPane().add(panel);						
						
					}else if(tmp.contains("SSO")){
						curveFrame.setTitle(tmp);
						curveFrame.remove(panel);
						curveFrame.revalidate();
						XYDataset dataset = curve.createCurveSSOIO(input, maxInstance);
						chart = ChartFactory.createXYLineChart(
					            maxInstance.getMaterial(),      // chart title
					            "DISPLACEMENT",                      // x axis label
					            "STIFFNESS SQUARED OVER LOAD",                      // y axis label
					            dataset,                  // data
					            PlotOrientation.VERTICAL,
					            true,                     // include legend
					            true,                     // tooltips
					            false                     // urls
					        );
						panel = new ChartPanel(chart);
						XYItemRenderer render = chart.getXYPlot().getRenderer();
						curve.customPropertiesTwoCurveSD(chart, input, maxInstance);
						curve.addLabelCurve(input.getCurveLabelSD(), render);
						curve.addLabelCurve(maxInstance.getCurveLabelSD(), render);
						panel.setDomainZoomable(false);
						panel.setRangeZoomable(false);

						curveFrame.getContentPane().add(panel);					
						}
					
				}
				
			}
		});
		cbxCurves.setBounds(194, 612, 176, 20);
		contentPane.add(cbxCurves);
		
		txtSimilarity = new JTextField();
		txtSimilarity.setBounds(247, 56, 123, 20);
		contentPane.add(txtSimilarity);
		txtSimilarity.setColumns(10);
		txtSimilarity.setText(String.valueOf(maxInstance.getSimilarity()));
		
		txtMaterial = new JTextField();
		txtMaterial.setText("");
		txtMaterial.setBounds(247, 111, 123, 20);
		contentPane.add(txtMaterial);
		txtMaterial.setColumns(10);
		txtMaterial.setText(String.valueOf(maxInstance.getMaterial()));
		
		
		JLabel lblAvgModulusgpa = new JLabel("Avg modulus (GPa)");
		lblAvgModulusgpa.setBounds(10, 377, 148, 14);
		contentPane.add(lblAvgModulusgpa);
		
		JLabel lblAvgHardnessgpa = new JLabel("Avg hardness (GPa)");
		lblAvgHardnessgpa.setBounds(10, 399, 176, 14);
		contentPane.add(lblAvgHardnessgpa);
		
		txtModulus = new JTextField();
		txtModulus.setBounds(247, 374, 123, 20);
		contentPane.add(txtModulus);
		txtModulus.setColumns(10);
		txtModulus.setText(String.valueOf(maxInstance.calculateAvgModulus()));
		
		txtHardness = new JTextField();
		txtHardness.setText("");
		txtHardness.setBounds(247, 396, 123, 20);
		contentPane.add(txtHardness);
		txtHardness.setColumns(10);
		txtHardness.setText(String.valueOf(maxInstance.calculateAvgHardness()));
		
		btnSample = new JButton("Import sample ");
		btnSample.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							if(newm){
								NMaterialJFInputData frame = new NMaterialJFInputData(material,format);
								frame.setVisible(true);
								dispose();
								
							}else{
								Instance instance = new Instance(input.getMaterial());
								SampleTestImport frame = new SampleTestImport(instance,format);
								frame.setVisible(true);
								dispose();
								
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnSample.setBounds(236, 643, 134, 23);
		contentPane.add(btnSample);
		
		lblImportSampleParameter = new JLabel("Import sample parameter");
		lblImportSampleParameter.setBounds(10, 647, 149, 14);
		contentPane.add(lblImportSampleParameter);
		
		JLabel lblCase = new JLabel("Case");
		lblCase.setBounds(324, 450, 46, 14);
		contentPane.add(lblCase);
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setBounds(179, 450, 46, 14);
		contentPane.add(lblInput);
		
		JLabel lblDeltaDisplacement = new JLabel("Delta Displacement");
		lblDeltaDisplacement.setBounds(10, 478, 123, 14);
		contentPane.add(lblDeltaDisplacement);
		
		JLabel lblPmaxmn = new JLabel("P_max (mN)");
		lblPmaxmn.setBounds(10, 503, 109, 14);
		contentPane.add(lblPmaxmn);
		
		JLabel lblHmax = new JLabel("h_max (nm)");
		lblHmax.setBounds(10, 527, 109, 14);
		contentPane.add(lblHmax);
		
		JLabel lblHfnm = new JLabel("h_f (nm)");
		lblHfnm.setBounds(10, 552, 98, 14);
		contentPane.add(lblHfnm);
		
		JLabel lblSnm = new JLabel("S (Nm)");
		lblSnm.setBounds(10, 577, 109, 14);
		contentPane.add(lblSnm);
		
		txtDeltaC = new JTextField();
		txtDeltaC.setBounds(284, 475, 86, 20);
		contentPane.add(txtDeltaC);
		txtDeltaC.setColumns(10);
		txtDeltaC.setText(String.valueOf(maxInstance.getDeltaDisplacementSurface()));
		
		txtDeltaI = new JTextField();
		txtDeltaI.setText("");
		txtDeltaI.setBounds(141, 475, 86, 20);
		contentPane.add(txtDeltaI);
		txtDeltaI.setColumns(10);
		txtDeltaI.setText(String.valueOf(input.getDeltaDisplacementSurface()));
		
		txtPC = new JTextField();
		txtPC.setBounds(284, 500, 86, 20);
		contentPane.add(txtPC);
		txtPC.setColumns(10);
		txtPC.setText(String.valueOf(maxInstance.getMaxPointCurve().getY()));
		
		txtPI = new JTextField();
		txtPI.setText("");
		txtPI.setBounds(141, 500, 86, 20);
		contentPane.add(txtPI);
		txtPI.setColumns(10);
		txtPI.setText(String.valueOf(input.getMaxPointCurve().getY()));
		
		txtHMaxC = new JTextField();
		txtHMaxC.setText("");
		txtHMaxC.setBounds(284, 524, 86, 20);
		contentPane.add(txtHMaxC);
		txtHMaxC.setColumns(10);
		txtHMaxC.setText(String.valueOf(maxInstance.getMaxPointCurve().getX()));
		
		txtHMaxI = new JTextField();
		txtHMaxI.setText("");
		txtHMaxI.setBounds(141, 524, 86, 20);
		contentPane.add(txtHMaxI);
		txtHMaxI.setColumns(10);
		txtHMaxI.setText(String.valueOf(input.getMaxPointCurve().getX()));
		
		txtHFC = new JTextField();
		txtHFC.setText("");
		txtHFC.setBounds(284, 549, 86, 20);
		contentPane.add(txtHFC);
		txtHFC.setColumns(10);
		txtHFC.setText(String.valueOf(maxInstance.getHFinal()));
		
		txtHFI = new JTextField();
		txtHFI.setText("");
		txtHFI.setBounds(141, 549, 86, 20);
		contentPane.add(txtHFI);
		txtHFI.setColumns(10);
		txtHFI.setText(String.valueOf(input.getHFinal()));
		
		txtSC = new JTextField();
		txtSC.setText("");
		txtSC.setBounds(284, 574, 86, 20);
		contentPane.add(txtSC);
		txtSC.setColumns(10);
		txtSC.setText(String.valueOf(maxInstance.getSlope()));
		
		
		txtSI = new JTextField();
		txtSI.setText("");
		txtSI.setBounds(141, 574, 86, 20);
		contentPane.add(txtSI);
		txtSI.setColumns(10);
		txtSI.setText(String.valueOf(input.getSlope()));
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(10, 34, 25, 14);
		contentPane.add(lblX);
		
		JLabel lblY = new JLabel("Y");
		lblY.setBounds(10, 59, 25, 14);
		contentPane.add(lblY);
		
		JLabel lblZ = new JLabel("Z");
		lblZ.setBounds(10, 84, 25, 14);
		contentPane.add(lblZ);
		
		txtX = new JTextField();
		txtX.setBounds(33, 31, 86, 20);
		contentPane.add(txtX);
		txtX.setColumns(10);
		txtX.setText(String.valueOf(sim.calculateX(input, maxInstance)));
		
		txtY = new JTextField();
		txtY.setText("");
		txtY.setBounds(33, 56, 86, 20);
		contentPane.add(txtY);
		txtY.setColumns(10);
		txtY.setText(String.valueOf(sim.calculateY(input, maxInstance)));
		
		txtZ = new JTextField();
		txtZ.setText("");
		txtZ.setBounds(33, 83, 86, 20);
		contentPane.add(txtZ);
		txtZ.setColumns(10);
		txtZ.setText(String.valueOf(sim.calculateZ(input, maxInstance)));
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 361, 379, 2);
		contentPane.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(0, 437, 379, 2);
		contentPane.add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setBounds(0, 139, 378, 2);
		contentPane.add(separator_3);
		
		curveFrame.setVisible(true);
	}
}
