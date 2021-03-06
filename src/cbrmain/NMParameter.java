package cbrmain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cbrdatabase.CbrData;
import cbrmodel.Instance;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NMParameter extends JFrame {

	private JPanel contentPane;
	private CbrData cbr;
	private JTextField txtSurfaceVI;
	private JTextField txtSurfaceVC;
	private JTextField txtDepthI;
	private JTextField txtDepthC;
	private JTextField txtMaterialI;
	private JTextField txtStrainI;
	private JTextField txtStrainC;
	private JTextField txtHarmonicI;
	private JTextField txtHarmonicC;
	private JTextField txtFrequencyI;
	private JTextField txtFrequencyC;
	private JTextField txtSurfaceDI;
	private JTextField txtSurfaceDC;
	private JTextField txtMaterialC;
	private JTextField txtPoissonI;
	private JTextField txtPoissonC;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblTip;
	private JLabel lblCase_1;
	private JTextField txtTipC;
	private JLabel lblM;
	private JLabel lblM_1;
	private JLabel lblM_2;
	private JLabel lblM_3;
	private JLabel lblM_4;
	private JLabel lblM_5;
	private JLabel lblM_6;
	private JLabel lblM_7;
	private JLabel lblM_8;
	private JTextField txtM0C;
	private JTextField txtM1C;
	private JTextField txtM2C;
	private JTextField txtM3C;
	private JTextField txtM4C;
	private JTextField txtM5C;
	private JTextField txtM6C;
	private JTextField txtM7C;
	private JTextField txtM8C;
	private JLabel lblCase_2;
	private JLabel lblInput_1;
	private JLabel lblAvgModulusgpa;
	private JLabel lblAvgHardnessgpa;
	private JLabel lblDeltaDisplacement;
	private JLabel lblPmaxmn;
	private JLabel lblHmaxnm;
	private JLabel lblHfnm;
	private JLabel lblSnm;
	private JTextField txtAVGMI;
	private JTextField txtAVGMC;
	private JTextField txtAVHI;
	private JTextField txtAVGHC;
	private JTextField txtDDI;
	private JTextField txtDDC;
	private JTextField txtPI;
	private JTextField txtPC;
	private JTextField txtHMI;
	private JTextField txtHMC;
	private JTextField txtHFI;
	private JTextField txtHFC;
	private JTextField txtSI;
	private JTextField txtSC;
	private JButton btnNext;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public NMParameter(Instance input, Instance caso) {
		cbr = new CbrData();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("View Parameter");
		setResizable(false);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInput = new JLabel("INPUT");
		lblInput.setBounds(270, 11, 46, 14);
		contentPane.add(lblInput);
		
		JLabel lblCase = new JLabel("CASE");
		lblCase.setBounds(373, 11, 46, 14);
		contentPane.add(lblCase);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(10, 30, 46, 14);
		contentPane.add(lblMaterial);
		
		JLabel lblSurfaceVelocitynms = new JLabel("Surface approach velocity (nm/s)");
		lblSurfaceVelocitynms.setBounds(10, 55, 207, 14);
		contentPane.add(lblSurfaceVelocitynms);
		
		JLabel lblDepthLimitnm = new JLabel("Depth limit (nm)");
		lblDepthLimitnm.setBounds(10, 80, 207, 14);
		contentPane.add(lblDepthLimitnm);
		
		JLabel lblStrainRateTarget = new JLabel("Strain rate target (1/s)");
		lblStrainRateTarget.setBounds(10, 103, 207, 14);
		contentPane.add(lblStrainRateTarget);
		
		JLabel lblHarmonicDisplacementnm = new JLabel("Harmonic displacement (nm)");
		lblHarmonicDisplacementnm.setBounds(10, 128, 207, 14);
		contentPane.add(lblHarmonicDisplacementnm);
		
		JLabel lblFrequencyTargethz = new JLabel("Frequency target (Hz)");
		lblFrequencyTargethz.setBounds(10, 153, 207, 14);
		contentPane.add(lblFrequencyTargethz);
		
		JLabel lblSurfaceDistancenm = new JLabel("Surface approach distance (nm)");
		lblSurfaceDistancenm.setBounds(10, 178, 207, 14);
		contentPane.add(lblSurfaceDistancenm);
		
		txtSurfaceVI = new JTextField();
		txtSurfaceVI.setBounds(230, 52, 86, 20);
		contentPane.add(txtSurfaceVI);
		txtSurfaceVI.setColumns(10);
		txtSurfaceVI.setText(String.valueOf(input.getInput().getSurfacevelocity()));
		
		txtSurfaceVC = new JTextField();
		txtSurfaceVC.setBounds(333, 52, 86, 20);
		contentPane.add(txtSurfaceVC);
		txtSurfaceVC.setColumns(10);
		txtSurfaceVC.setText(String.valueOf(caso.getInput().getSurfacevelocity()));
		
		txtDepthI = new JTextField();
		txtDepthI.setBounds(230, 77, 86, 20);
		contentPane.add(txtDepthI);
		txtDepthI.setColumns(10);
		txtDepthI.setText(String.valueOf(input.getInput().getDepthlimit()));
		
		txtDepthC = new JTextField();
		txtDepthC.setBounds(333, 77, 86, 20);
		contentPane.add(txtDepthC);
		txtDepthC.setColumns(10);
		txtDepthC.setText(String.valueOf(caso.getInput().getDepthlimit()));
		
		txtMaterialI = new JTextField();
		txtMaterialI.setBounds(230, 27, 86, 20);
		contentPane.add(txtMaterialI);
		txtMaterialI.setColumns(10);
		txtMaterialI.setText(input.getMaterial());
		
		txtStrainI = new JTextField();
		txtStrainI.setBounds(230, 100, 86, 20);
		contentPane.add(txtStrainI);
		txtStrainI.setColumns(10);
		txtStrainI.setText(String.valueOf(input.getInput().getStrainrate()));
		
		txtStrainC = new JTextField();
		txtStrainC.setBounds(333, 100, 86, 20);
		contentPane.add(txtStrainC);
		txtStrainC.setColumns(10);
		txtStrainC.setText(String.valueOf(caso.getInput().getStrainrate()));
		
		txtHarmonicI = new JTextField();
		txtHarmonicI.setText("");
		txtHarmonicI.setBounds(230, 125, 86, 20);
		contentPane.add(txtHarmonicI);
		txtHarmonicI.setColumns(10);
		txtHarmonicI.setText(String.valueOf(input.getInput().getHarmonicdisplacement()));
		
		txtHarmonicC = new JTextField();
		txtHarmonicC.setBounds(333, 125, 86, 20);
		contentPane.add(txtHarmonicC);
		txtHarmonicC.setColumns(10);
		txtHarmonicC.setText(String.valueOf(caso.getInput().getHarmonicdisplacement()));
		
		txtFrequencyI = new JTextField();
		txtFrequencyI.setBounds(230, 150, 86, 20);
		contentPane.add(txtFrequencyI);
		txtFrequencyI.setColumns(10);
		txtFrequencyI.setText(String.valueOf(input.getInput().getFrequencytarget()));
		
		txtFrequencyC = new JTextField();
		txtFrequencyC.setBounds(333, 150, 86, 20);
		contentPane.add(txtFrequencyC);
		txtFrequencyC.setColumns(10);
		txtFrequencyC.setText(String.valueOf(caso.getInput().getFrequencytarget()));
		
		txtSurfaceDI = new JTextField();
		txtSurfaceDI.setText("");
		txtSurfaceDI.setBounds(230, 175, 86, 20);
		contentPane.add(txtSurfaceDI);
		txtSurfaceDI.setColumns(10);
		txtSurfaceDI.setText(String.valueOf(input.getInput().getSurfacedistance()));
		
		txtSurfaceDC = new JTextField();
		txtSurfaceDC.setText("");
		txtSurfaceDC.setBounds(333, 175, 86, 20);
		contentPane.add(txtSurfaceDC);
		txtSurfaceDC.setColumns(10);
		txtSurfaceDC.setText(String.valueOf(caso.getInput().getSurfacedistance()));;
		
		txtMaterialC = new JTextField();
		txtMaterialC.setBounds(333, 27, 86, 20);
		contentPane.add(txtMaterialC);
		txtMaterialC.setColumns(10);
		txtMaterialC.setText(String.valueOf(caso.getMaterial()));
		
		JLabel lblPoissonsRate = new JLabel("Poisson's ratio");
		lblPoissonsRate.setBounds(10, 203, 207, 14);
		contentPane.add(lblPoissonsRate);
		
		txtPoissonI = new JTextField();
		txtPoissonI.setBounds(230, 200, 86, 20);
		contentPane.add(txtPoissonI);
		txtPoissonI.setColumns(10);
		txtPoissonI.setText(String.valueOf(input.getInput().getPoissonsratio()));
		
		txtPoissonC = new JTextField();
		txtPoissonC.setText("");
		txtPoissonC.setBounds(333, 200, 86, 20);
		contentPane.add(txtPoissonC);
		txtPoissonC.setColumns(10);
		txtPoissonC.setText(String.valueOf(caso.getInput().getPoissonsratio()));
		
		separator = new JSeparator();
		separator.setBounds(0, 288, 994, 2);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(497, 0, 0, 257);
		contentPane.add(separator_1);
		
		lblTip = new JLabel("Tip");
		lblTip.setBounds(507, 30, 153, 14);
		contentPane.add(lblTip);
		
		lblCase_1 = new JLabel("CASE");
		lblCase_1.setBounds(664, 11, 46, 14);
		contentPane.add(lblCase_1);
		
		txtTipC = new JTextField();
		txtTipC.setBounds(624, 27, 86, 20);
		contentPane.add(txtTipC);
		txtTipC.setColumns(10);
		txtTipC.setText(cbr.retrieveTipById(Integer.parseInt(caso.getTip())));
		
		lblM = new JLabel("m0");
		lblM.setBounds(507, 55, 46, 14);
		contentPane.add(lblM);
		
		lblM_1 = new JLabel("m1");
		lblM_1.setBounds(507, 80, 46, 14);
		contentPane.add(lblM_1);
		
		lblM_2 = new JLabel("m2");
		lblM_2.setBounds(507, 103, 46, 14);
		contentPane.add(lblM_2);
		
		lblM_3 = new JLabel("m3");
		lblM_3.setBounds(507, 128, 46, 14);
		contentPane.add(lblM_3);
		
		lblM_4 = new JLabel("m4");
		lblM_4.setBounds(507, 153, 46, 14);
		contentPane.add(lblM_4);
		
		lblM_5 = new JLabel("m5");
		lblM_5.setBounds(507, 175, 46, 14);
		contentPane.add(lblM_5);
		
		lblM_6 = new JLabel("m6");
		lblM_6.setBounds(507, 203, 46, 14);
		contentPane.add(lblM_6);
		
		lblM_7 = new JLabel("m7");
		lblM_7.setBounds(507, 228, 46, 14);
		contentPane.add(lblM_7);
		
		lblM_8 = new JLabel("m8");
		lblM_8.setBounds(507, 253, 46, 14);
		contentPane.add(lblM_8);
		
		txtM0C = new JTextField();
		txtM0C.setBounds(624, 52, 86, 20);
		contentPane.add(txtM0C);
		txtM0C.setColumns(10);
		txtM0C.setText(String.valueOf(caso.getCoefficientsTip().getM0()));
		
		txtM1C = new JTextField();
		txtM1C.setText("");
		txtM1C.setBounds(624, 77, 86, 20);
		contentPane.add(txtM1C);
		txtM1C.setColumns(10);
		txtM1C.setText(String.valueOf(caso.getCoefficientsTip().getM1()));
		
		txtM2C = new JTextField();
		txtM2C.setText("");
		txtM2C.setBounds(624, 100, 86, 20);
		contentPane.add(txtM2C);
		txtM2C.setColumns(10);
		txtM2C.setText(String.valueOf(caso.getCoefficientsTip().getM2()));
		
		txtM3C = new JTextField();
		txtM3C.setText("");
		txtM3C.setBounds(624, 125, 86, 20);
		contentPane.add(txtM3C);
		txtM3C.setColumns(10);
		txtM3C.setText(String.valueOf(caso.getCoefficientsTip().getM3()));
		
		txtM4C = new JTextField();
		txtM4C.setText("");
		txtM4C.setBounds(624, 150, 86, 20);
		contentPane.add(txtM4C);
		txtM4C.setColumns(10);
		txtM4C.setText(String.valueOf(caso.getCoefficientsTip().getM4()));
		
		txtM5C = new JTextField();
		txtM5C.setText("");
		txtM5C.setBounds(624, 175, 86, 20);
		contentPane.add(txtM5C);
		txtM5C.setColumns(10);
		txtM5C.setText(String.valueOf(caso.getCoefficientsTip().getM5()));
		
		txtM6C = new JTextField();
		txtM6C.setText("");
		txtM6C.setBounds(624, 200, 86, 20);
		contentPane.add(txtM6C);
		txtM6C.setColumns(10);
		txtM6C.setText(String.valueOf(caso.getCoefficientsTip().getM6()));
		
		txtM7C = new JTextField();
		txtM7C.setBounds(624, 225, 86, 20);
		contentPane.add(txtM7C);
		txtM7C.setColumns(10);
		txtM7C.setText(String.valueOf(caso.getCoefficientsTip().getM7()));
		
		txtM8C = new JTextField();
		txtM8C.setBounds(624, 250, 86, 20);
		contentPane.add(txtM8C);
		txtM8C.setColumns(10);
		txtM8C.setText(String.valueOf(caso.getCoefficientsTip().getM8()));
		
		lblCase_2 = new JLabel("CASE");
		lblCase_2.setBounds(373, 301, 46, 14);
		contentPane.add(lblCase_2);
		
		lblInput_1 = new JLabel("INPUT");
		lblInput_1.setBounds(270, 301, 46, 14);
		contentPane.add(lblInput_1);
		
		lblAvgModulusgpa = new JLabel("Avg modulus (GPa)");
		lblAvgModulusgpa.setBounds(10, 342, 207, 14);
		contentPane.add(lblAvgModulusgpa);
		
		lblAvgHardnessgpa = new JLabel("Avg hardness (GPa)");
		lblAvgHardnessgpa.setBounds(10, 366, 207, 14);
		contentPane.add(lblAvgHardnessgpa);
		
		lblDeltaDisplacement = new JLabel("Delta Displacement");
		lblDeltaDisplacement.setBounds(10, 391, 207, 14);
		contentPane.add(lblDeltaDisplacement);
		
		lblPmaxmn = new JLabel("Pmax (mN)");
		lblPmaxmn.setBounds(10, 416, 207, 14);
		contentPane.add(lblPmaxmn);
		
		lblHmaxnm = new JLabel("hmax (nm)");
		lblHmaxnm.setBounds(10, 441, 207, 14);
		contentPane.add(lblHmaxnm);
		
		lblHfnm = new JLabel("hf (nm)");
		lblHfnm.setBounds(10, 465, 207, 14);
		contentPane.add(lblHfnm);
		
		lblSnm = new JLabel("S (Nm)");
		lblSnm.setBounds(10, 490, 207, 14);
		contentPane.add(lblSnm);
		
		txtAVGMI = new JTextField();
		txtAVGMI.setBounds(230, 336, 86, 20);
		contentPane.add(txtAVGMI);
		txtAVGMI.setColumns(10);
		txtAVGMI.setText(String.valueOf(input.calculateAvgModulus()));
		
		txtAVGMC = new JTextField();
		txtAVGMC.setText("");
		txtAVGMC.setBounds(333, 336, 86, 20);
		contentPane.add(txtAVGMC);
		txtAVGMC.setColumns(10);
		txtAVGMC.setText(String.valueOf(caso.calculateAvgModulus()));
		
		txtAVHI = new JTextField();
		txtAVHI.setText("");
		txtAVHI.setBounds(230, 363, 86, 20);
		contentPane.add(txtAVHI);
		txtAVHI.setColumns(10);
		txtAVHI.setText(String.valueOf(input.calculateAvgHardness()));
		
		txtAVGHC = new JTextField();
		txtAVGHC.setText("");
		txtAVGHC.setBounds(333, 363, 86, 20);
		contentPane.add(txtAVGHC);
		txtAVGHC.setColumns(10);
		txtAVGHC.setText(String.valueOf(caso.calculateAvgHardness()));
		
		txtDDI = new JTextField();
		txtDDI.setText("");
		txtDDI.setBounds(230, 388, 86, 20);
		contentPane.add(txtDDI);
		txtDDI.setColumns(10);
		txtDDI.setText(String.valueOf(input.getDeltaDisplacementSurface()));
		
		txtDDC = new JTextField();
		txtDDC.setText("");
		txtDDC.setBounds(333, 388, 86, 20);
		contentPane.add(txtDDC);
		txtDDC.setColumns(10);
		txtDDC.setText(String.valueOf(caso.getDeltaDisplacementSurface()));
		
		txtPI = new JTextField();
		txtPI.setText("");
		txtPI.setBounds(230, 413, 86, 20);
		contentPane.add(txtPI);
		txtPI.setColumns(10);
		txtPI.setText(String.valueOf(input.getMaxPointCurve().getY()));
		txtPC = new JTextField();
		txtPC.setText("");
		txtPC.setBounds(333, 413, 86, 20);
		contentPane.add(txtPC);
		txtPC.setColumns(10);
		txtPC.setText(String.valueOf(caso.getMaxPointCurve().getY()));
		txtHMI = new JTextField();
		txtHMI.setText("");
		txtHMI.setBounds(230, 438, 86, 20);
		contentPane.add(txtHMI);
		txtHMI.setColumns(10);
		txtHMI.setText(String.valueOf(input.getMaxPointCurve().getX()));
		txtHMC = new JTextField();
		txtHMC.setText("");
		txtHMC.setBounds(333, 438, 86, 20);
		contentPane.add(txtHMC);
		txtHMC.setColumns(10);
		txtHMC.setText(String.valueOf(caso.getMaxPointCurve().getX()));
		txtHFI = new JTextField();
		txtHFI.setText("");
		txtHFI.setBounds(230, 462, 86, 20);
		contentPane.add(txtHFI);
		txtHFI.setColumns(10);
		txtHFI.setText(String.valueOf(input.getHFinal()));
		txtHFC = new JTextField();
		txtHFC.setText("");
		txtHFC.setBounds(333, 462, 86, 20);
		contentPane.add(txtHFC);
		txtHFC.setColumns(10);
		txtHFC.setText(String.valueOf(caso.getHFinal()));
		txtSI = new JTextField();
		txtSI.setText("");
		txtSI.setBounds(230, 487, 86, 20);
		contentPane.add(txtSI);
		txtSI.setColumns(10);
		txtSI.setText(String.valueOf(input.getSlope()));
		txtSC = new JTextField();
		txtSC.setText("");
		txtSC.setBounds(333, 487, 86, 20);
		contentPane.add(txtSC);
		txtSC.setColumns(10);
		txtSC.setText(String.valueOf(caso.getSlope()));
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							DescriptionJF frame = new DescriptionJF();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			
			}
		});
		btnNext.setBounds(898, 611, 86, 23);
		contentPane.add(btnNext);
	}
}
