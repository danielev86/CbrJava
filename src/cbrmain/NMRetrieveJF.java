package cbrmain;

import java.awt.BorderLayout;
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
import cbrdatabase.CbrData;
import cbrdatabase.CurveSimilarity;
import cbrmodel.Instance;
import cbrmodel.LabelCurve;

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

public class NMRetrieveJF extends JFrame {

	private JPanel contentPane;
	private JTextField txtIMaterial;
	private JTextField txtISurfaceV;
	private JTextField txtIDepth;
	private JTextField txtIStrain;
	private JTextField txtIHarmonic;
	private JTextField txtIFrequency;
	private JTextField txtISurfaceD;
	private JTextField txtIPoissons;
	private JTextField txtOSimilarity;
	private JTextField txtOMaterial;
	private JTextField txtOSurfaceV;
	private JTextField txtODepth;
	private JTextField txtOStrain;
	private JTextField txtOHarmonic;
	private JTextField txtOFrequency;
	private JTextField txtOSurfaceD;
	private JTextField txtOPoissons;
	private CbrData cbr;
	private CurveSimilarity sim;
	private List<Instance> instances;
	private Instance maxSimilarity;
	private ChartPanel panel;
	private JFreeChart chart;
	private JInternalFrame internalFrame;
	private Curve curve;
	private JSeparator separator_1;
	private JLabel lblAvgModulusgpa;
	private JTextField txtModulusI;
	private JTextField txtModulusO;
	private JLabel lblAvgHardnessgpa;
	private JTextField txtHardnessI;
	private JTextField txtHardnessO;
	private JLabel lblDeltaDisplacement;
	private JLabel lblPmaxmn;
	private JLabel lblHmaxnm;
	private JLabel lblHfnm;
	private JLabel lblSnm;
	private JTextField txtDeltaC;
	private JTextField txtDeltaI;
	private JTextField txtPMC;
	private JTextField txtPMI;
	private JTextField txtHMC;
	private JTextField txtHMI;
	private JTextField txtHFC;
	private JTextField txtHFI;
	private JTextField txtSMC;
	private JTextField txtSMI;
	private JSeparator separator_2;
	private JSeparator separator;
	private JLabel lblY;
	private JLabel lblZ;
	private JLabel lblX;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	

	public NMRetrieveJF(Instance instance, String value, String format) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				internalFrame.setTitle("P-h curve");
				internalFrame.remove(panel);
				internalFrame.revalidate();
				XYDataset dataset = curve.createCurveLdIO(instance, maxSimilarity);
				chart = ChartFactory.createXYLineChart(
						maxSimilarity.getMaterial(),      // chart title
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
				List<LabelCurve> list = instance.getCurveLabelLD();
				curve.customPropertiesTwoCurve(chart, instance, maxSimilarity);
				curve.addLabelCurve(list, render);
				curve.addLabelCurve(maxSimilarity.getCurveLabelLD(), render);
				

				internalFrame.getContentPane().add(panel);						
				
			}
		});
		this.setResizable(false);
		setTitle("Retrieve case for new material");
		cbr = new CbrData();
		sim = new CurveSimilarity();
		curve = new Curve();
		instances = cbr.retrieveAllInstances();
		//instancesSilica = cbr.retrieveInstancesByMaterial("silica");
		for(Instance it:instances){
			it.setSimilarity(sim.evaluateSimilarity(instance, it));
		}
		/*for(Instance it:instancesSilica){
			it.setSimilarity(sim.evaluateSimilarity(instance.getCurve(), it.getCurve()));
		}*/
	
		String[] array = {"P-h curve", "HCS-time curve", "SSO-h curve"};
		maxSimilarity = sim.maxSimilarity(instances);
		//maxSSimilarity = sim.maxSimilarity(instancesSilica);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		internalFrame = new JInternalFrame("");
		internalFrame.setBounds(431, 0, 833, 620);
		
		contentPane.add(internalFrame);
		panel = new ChartPanel(chart);
		panel.setLayout(new BorderLayout(0, 0));

		internalFrame.getContentPane().add(panel);
		
		JLabel lblSimilarity = new JLabel("Similarity");
		lblSimilarity.setBounds(218, 69, 86, 14);
		contentPane.add(lblSimilarity);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(10, 160, 186, 14);
		contentPane.add(lblMaterial);
			
		JLabel lblInput = new JLabel("INPUT");
		lblInput.setBounds(230, 132, 74, 14);
		contentPane.add(lblInput);
		
		JLabel lblOutput = new JLabel("CASE");
		lblOutput.setBounds(349, 132, 74, 14);
		contentPane.add(lblOutput);
		
		JLabel lblRequiredInput = new JLabel("REQUIRED INPUT");
		lblRequiredInput.setBounds(10, 185, 186, 14);
		contentPane.add(lblRequiredInput);
		
		JLabel lblSurfaceVelocitynms = new JLabel("Surface approach velocity (nm/s)");
		lblSurfaceVelocitynms.setBounds(10, 221, 186, 14);
		contentPane.add(lblSurfaceVelocitynms);
		
		JLabel lblDepthLimitnm = new JLabel("Depth limit (nm)");
		lblDepthLimitnm.setBounds(10, 246, 186, 14);
		contentPane.add(lblDepthLimitnm);
		
		JLabel lblStrainRateTarget = new JLabel("Strain rate target (1/s)");
		lblStrainRateTarget.setBounds(10, 271, 186, 14);
		contentPane.add(lblStrainRateTarget);
		
		JLabel lblHarmonicDisplacementnm = new JLabel("Harmonic displacement (nm)");
		lblHarmonicDisplacementnm.setBounds(10, 296, 186, 14);
		contentPane.add(lblHarmonicDisplacementnm);
		
		JLabel lblFrequencyTargethz = new JLabel("Frequency target (Hz)");
		lblFrequencyTargethz.setBounds(10, 321, 186, 14);
		contentPane.add(lblFrequencyTargethz);
		
		JLabel lblSurfaceDistancenm = new JLabel("Surface approach distance (nm)");
		lblSurfaceDistancenm.setBounds(10, 346, 186, 14);
		contentPane.add(lblSurfaceDistancenm);
		
		JLabel lblPoissonsRate = new JLabel("Poisson's ratio");
		lblPoissonsRate.setBounds(10, 371, 186, 14);
		contentPane.add(lblPoissonsRate);
		
		txtIMaterial = new JTextField();
		txtIMaterial.setBounds(218, 157, 86, 20);
		contentPane.add(txtIMaterial);
		txtIMaterial.setColumns(10);
		txtIMaterial.setText(instance.getMaterial());
		
		txtISurfaceV = new JTextField();
		txtISurfaceV.setText("");
		txtISurfaceV.setBounds(218, 218, 86, 20);
		contentPane.add(txtISurfaceV);
		txtISurfaceV.setColumns(10);
		txtISurfaceV.setText(String.valueOf(instance.getInput().getSurfacevelocity()));
		
		txtIDepth = new JTextField();
		txtIDepth.setBounds(218, 243, 86, 20);
		contentPane.add(txtIDepth);
		txtIDepth.setColumns(10);
		txtIDepth.setText(String.valueOf(instance.getInput().getDepthlimit()));
		
		txtIStrain = new JTextField();
		txtIStrain.setText("");
		txtIStrain.setBounds(218, 268, 86, 20);
		contentPane.add(txtIStrain);
		txtIStrain.setColumns(10);
		txtIStrain.setText(String.valueOf(instance.getInput().getStrainrate()));
		
		txtIHarmonic = new JTextField();
		txtIHarmonic.setText("");
		txtIHarmonic.setBounds(218, 293, 86, 20);
		contentPane.add(txtIHarmonic);
		txtIHarmonic.setColumns(10);
		txtIHarmonic.setText(String.valueOf(instance.getInput().getHarmonicdisplacement()));
		
		txtIFrequency = new JTextField();
		txtIFrequency.setText("");
		txtIFrequency.setBounds(218, 318, 86, 20);
		contentPane.add(txtIFrequency);
		txtIFrequency.setColumns(10);
		txtIFrequency.setText(String.valueOf(instance.getInput().getFrequencytarget()));
		
		txtISurfaceD = new JTextField();
		txtISurfaceD.setText("");
		txtISurfaceD.setBounds(218, 343, 86, 20);
		contentPane.add(txtISurfaceD);
		txtISurfaceD.setColumns(10);
		txtISurfaceD.setText(String.valueOf(instance.getInput().getSurfacedistance()));
		
		txtIPoissons = new JTextField();
		txtIPoissons.setText("");
		txtIPoissons.setBounds(218, 368, 86, 20);
		contentPane.add(txtIPoissons);
		txtIPoissons.setColumns(10);
		txtIPoissons.setText(String.valueOf(instance.getInput().getPoissonsratio()));
		
		txtOSimilarity = new JTextField();
		txtOSimilarity.setBounds(335, 66, 86, 20);
		contentPane.add(txtOSimilarity);
		txtOSimilarity.setColumns(10);
		txtOSimilarity.setText(String.valueOf(maxSimilarity.getSimilarity()));
		
		txtOMaterial = new JTextField();
		txtOMaterial.setText("");
		txtOMaterial.setBounds(335, 157, 86, 20);
		contentPane.add(txtOMaterial);
		txtOMaterial.setColumns(10);
		txtOMaterial.setText(maxSimilarity.getMaterial());
		
		txtOSurfaceV = new JTextField();
		txtOSurfaceV.setBounds(335, 218, 86, 20);
		contentPane.add(txtOSurfaceV);
		txtOSurfaceV.setColumns(10);
		txtOSurfaceV.setText(String.valueOf(maxSimilarity.getInput().getSurfacevelocity()));
		
		txtODepth = new JTextField();
		txtODepth.setText("");
		txtODepth.setBounds(335, 243, 86, 20);
		contentPane.add(txtODepth);
		txtODepth.setColumns(10);
		txtODepth.setText(String.valueOf(maxSimilarity.getInput().getDepthlimit()));
		
		txtOStrain = new JTextField();
		txtOStrain.setBounds(335, 268, 86, 20);
		contentPane.add(txtOStrain);
		txtOStrain.setColumns(10);
		txtOStrain.setText(String.valueOf(maxSimilarity.getInput().getStrainrate()));
		
		txtOHarmonic = new JTextField();
		txtOHarmonic.setText("");
		txtOHarmonic.setBounds(335, 293, 86, 20);
		contentPane.add(txtOHarmonic);
		txtOHarmonic.setColumns(10);
		txtOHarmonic.setText(String.valueOf(maxSimilarity.getInput().getHarmonicdisplacement()));
		
		txtOFrequency = new JTextField();
		txtOFrequency.setText("");
		txtOFrequency.setBounds(335, 318, 86, 20);
		contentPane.add(txtOFrequency);
		txtOFrequency.setColumns(10);
		txtOFrequency.setText(String.valueOf(maxSimilarity.getInput().getFrequencytarget()));
		
		txtOSurfaceD = new JTextField();
		txtOSurfaceD.setText("");
		txtOSurfaceD.setBounds(335, 343, 86, 20);
		contentPane.add(txtOSurfaceD);
		txtOSurfaceD.setColumns(10);
		txtOSurfaceD.setText(String.valueOf(maxSimilarity.getInput().getSurfacedistance()));
		
		txtOPoissons = new JTextField();
		txtOPoissons.setBounds(335, 368, 86, 20);
		contentPane.add(txtOPoissons);
		txtOPoissons.setColumns(10);
		txtOPoissons.setText(String.valueOf(maxSimilarity.getInput().getPoissonsratio()));
		
		JComboBox cbmCurve = new JComboBox(array);
		cbmCurve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tmp = (String) cbmCurve.getSelectedItem();
				if(!tmp.contains("Select")){
					if(tmp.contains("P-h")){
						internalFrame.setTitle(tmp);
						internalFrame.remove(panel);
						internalFrame.revalidate();
						XYDataset dataset = curve.createCurveLdIO(instance, maxSimilarity);
						chart = ChartFactory.createXYLineChart(
								maxSimilarity.getMaterial(),      // chart title
					            "DISPLACEMENT",                      // x axis label
					            "LOAD",                      // y axis label
					            dataset,                  // data
					            PlotOrientation.VERTICAL,
					            true,                     // include legend
					            true,                     // tooltips
					            false                     // urls
					        );
						
						panel = new ChartPanel(chart);
						XYItemRenderer render = chart.getXYPlot().getRenderer();
						panel = new ChartPanel(chart);
						List<LabelCurve> list = instance.getCurveLabelLD();
						curve.customPropertiesTwoCurve(chart, instance, maxSimilarity);
						curve.addLabelCurve(list, render);
						curve.addLabelCurve(maxSimilarity.getCurveLabelLD(), render);
						

						internalFrame.getContentPane().add(panel);						
						
					}else if(tmp.contains("HCS-time")){
						internalFrame.setTitle(tmp);
						internalFrame.remove(panel);
						internalFrame.revalidate();
						XYDataset  dataset = curve.createCurveHdIO(instance, maxSimilarity);
						chart = ChartFactory.createXYLineChart(
								maxSimilarity.getMaterial(),      // chart title
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
						curve.customPropertiesTwoCurveHD(chart, instance, maxSimilarity);
						curve.addLabelCurve(instance.getCurveLabelHD(), render);
						curve.addLabelCurve(maxSimilarity.getCurveLabelHD(), render);
						panel.setDomainZoomable(false);
						panel.setRangeZoomable(false);
						internalFrame.getContentPane().add(panel);						
						
					}else if(tmp.contains("SSO-h")){
						internalFrame.setTitle(tmp);
						internalFrame.remove(panel);
						internalFrame.revalidate();
						XYDataset dataset = curve.createCurveSSOIO(instance, maxSimilarity);
						chart = ChartFactory.createXYLineChart(
								maxSimilarity.getMaterial(),      // chart title
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
						curve.customPropertiesTwoCurveSD(chart, instance, maxSimilarity);
						curve.addLabelCurve(instance.getCurveLabelSD(), render);
						curve.addLabelCurve(maxSimilarity.getCurveLabelSD(), render);
						panel.setDomainZoomable(false);
						panel.setRangeZoomable(false);
						internalFrame.getContentPane().add(panel);					}
					
				}			
					
				
			}
		
		});
		cbmCurve.setBounds(218, 621, 203, 20);
		contentPane.add(cbmCurve);
		
		JButton btnViewParameterTest = new JButton("View parameter test");
		btnViewParameterTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							NMParameter frame = new NMParameter(instance,maxSimilarity);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnViewParameterTest.setBounds(1101, 631, 153, 23);
		contentPane.add(btnViewParameterTest);
		
		
		
		lblAvgModulusgpa = new JLabel("Avg modulus (GPa)");
		lblAvgModulusgpa.setBounds(10, 435, 186, 14);
		contentPane.add(lblAvgModulusgpa);
		
		txtModulusI = new JTextField();
		txtModulusI.setBounds(218, 432, 86, 20);
		contentPane.add(txtModulusI);
		txtModulusI.setColumns(10);
		txtModulusI.setText(String.valueOf(instance.calculateAvgModulus()));
		
		txtModulusO = new JTextField();
		txtModulusO.setBounds(335, 432, 86, 20);
		contentPane.add(txtModulusO);
		txtModulusO.setColumns(10);
		txtModulusO.setText(String.valueOf(maxSimilarity.calculateAvgModulus()));
		
		lblAvgHardnessgpa = new JLabel("Avg hardness (GPa)");
		lblAvgHardnessgpa.setBounds(10, 460, 186, 14);
		contentPane.add(lblAvgHardnessgpa);
		
		txtHardnessI = new JTextField();
		txtHardnessI.setBounds(218, 457, 86, 20);
		contentPane.add(txtHardnessI);
		txtHardnessI.setColumns(10);
		txtHardnessI.setText(String.valueOf(instance.calculateAvgHardness()));
		
		txtHardnessO = new JTextField();
		txtHardnessO.setText("");
		txtHardnessO.setBounds(335, 457, 86, 20);
		contentPane.add(txtHardnessO);
		txtHardnessO.setColumns(10);
		txtHardnessO.setText(String.valueOf(maxSimilarity.calculateAvgHardness()));
		
		lblDeltaDisplacement = new JLabel("Delta Displacement");
		lblDeltaDisplacement.setBounds(10, 485, 186, 14);
		contentPane.add(lblDeltaDisplacement);
		
		lblPmaxmn = new JLabel("P_max (mN)");
		lblPmaxmn.setBounds(10, 510, 186, 14);
		contentPane.add(lblPmaxmn);
		
		lblHmaxnm = new JLabel("h_max (nm)");
		lblHmaxnm.setBounds(10, 535, 186, 14);
		contentPane.add(lblHmaxnm);
		
		lblHfnm = new JLabel("h_f (nm)");
		lblHfnm.setBounds(10, 563, 46, 14);
		contentPane.add(lblHfnm);
		
		lblSnm = new JLabel("S (Nm)");
		lblSnm.setBounds(10, 588, 186, 14);
		contentPane.add(lblSnm);
		
		txtDeltaC = new JTextField();
		txtDeltaC.setBounds(335, 482, 86, 20);
		contentPane.add(txtDeltaC);
		txtDeltaC.setColumns(10);
		txtDeltaC.setText(String.valueOf(maxSimilarity.getDeltaDisplacementSurface()));
		
		txtDeltaI = new JTextField();
		txtDeltaI.setBounds(218, 482, 86, 20);
		contentPane.add(txtDeltaI);
		txtDeltaI.setColumns(10);
		txtDeltaI.setText(String.valueOf(instance.getDeltaDisplacementSurface()));
		
		txtPMC = new JTextField();
		txtPMC.setBounds(335, 507, 86, 20);
		contentPane.add(txtPMC);
		txtPMC.setColumns(10);
		txtPMC.setText(String.valueOf(maxSimilarity.getMaxPointCurve().getY()));
		
		txtPMI = new JTextField();
		txtPMI.setText("");
		txtPMI.setBounds(218, 507, 86, 20);
		contentPane.add(txtPMI);
		txtPMI.setColumns(10);
		txtPMI.setText(String.valueOf(instance.getMaxPointCurve().getY()));
		
		
		txtHMC = new JTextField();
		txtHMC.setText("");
		txtHMC.setBounds(335, 532, 86, 20);
		contentPane.add(txtHMC);
		txtHMC.setColumns(10);
		txtHMC.setText(String.valueOf(maxSimilarity.getMaxPointCurve().getX()));
		
		txtHMI = new JTextField();
		txtHMI.setText("");
		txtHMI.setBounds(218, 532, 86, 20);
		contentPane.add(txtHMI);
		txtHMI.setColumns(10);
		txtHMI.setText(String.valueOf(instance.getMaxPointCurve().getX()));
		
		txtHFC = new JTextField();
		txtHFC.setBounds(335, 560, 86, 20);
		contentPane.add(txtHFC);
		txtHFC.setColumns(10);
		txtHFC.setText(String.valueOf(maxSimilarity.getHFinal()));
		
		txtHFI = new JTextField();
		txtHFI.setText("");
		txtHFI.setBounds(218, 560, 86, 20);
		contentPane.add(txtHFI);
		txtHFI.setColumns(10);
		txtHFI.setText(String.valueOf(instance.getHFinal()));
		
		
		txtSMC = new JTextField();
		txtSMC.setBounds(335, 585, 86, 20);
		contentPane.add(txtSMC);
		txtSMC.setColumns(10);
		txtSMC.setText(String.valueOf(maxSimilarity.getSlope()));
		
		txtSMI = new JTextField();
		txtSMI.setText("");
		txtSMI.setBounds(218, 585, 86, 20);
		contentPane.add(txtSMI);
		txtSMI.setColumns(10);
		txtSMI.setText(String.valueOf(instance.getSlope()));
		
		lblY = new JLabel("Y");
		lblY.setBounds(10, 69, 46, 14);
		contentPane.add(lblY);
		
		lblZ = new JLabel("Z");
		lblZ.setBounds(10, 101, 46, 14);
		contentPane.add(lblZ);
		
		lblX = new JLabel("X");
		lblX.setBounds(10, 37, 46, 14);
		contentPane.add(lblX);
		
		textField = new JTextField();
		textField.setBounds(110, 34, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(String.valueOf(sim.calculateX(instance, maxSimilarity)));
		textField_1 = new JTextField();
		textField_1.setBounds(110, 66, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(String.valueOf(sim.calculateY(instance, maxSimilarity)));
		textField_2 = new JTextField();
		textField_2.setBounds(110, 98, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(String.valueOf(sim.calculateZ(instance, maxSimilarity)));
	
		
	
		internalFrame.setVisible(true);
	}
}
