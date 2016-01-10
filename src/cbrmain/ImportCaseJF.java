package cbrmain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cbrdatabase.CbrData;
import cbrimport.ImportData;
import cbrmodel.CoefficientsTip;
import cbrmodel.Instance;
import cbrmodel.RequiredInput;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ImportCaseJF extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaterial;
	private JTextField txtTestNumber;
	private JTextField txtFrame;
	private JTextField txtSurfaceV;
	private JTextField txtDepth;
	private JTextField txtStrain;
	private JTextField txtHarmonic;
	private JTextField txtFrequency;
	private JTextField txtSurfaceD;
	private JTextField txtPoissons;
	private CbrData cbr;
	private Instance instance;
	private ImportData load;
	private JButton btnSaveCase;
	private JComboBox comboBox;
	private List<String> tmp;
	private String[] tips;
	int tip_id;
	private JComboBox cmbStage,comboBox_1,comboBox_2;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ImportCaseJF() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				tmp=cbr.retrieveAllTips();
				tips = new String[tmp.size()];
				tips=tmp.toArray(tips);
				comboBox.removeAllItems();
				for(int i=0;i<tips.length;i++){
					comboBox.addItem((String) tips[i]);
				}
				
			}
		});
		String[] formats = {"bulk","film"};
		String[] exps = {"CDM","XP"};
		this.setResizable(false);
		cbr = new CbrData();
		load = new ImportData();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		setTitle("Import Case In Casebase");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(10, 11, 100, 14);
		contentPane.add(lblMaterial);
		
		JLabel label = new JLabel("Tip");
		label.setBounds(10, 36, 46, 14);
		contentPane.add(label);
		
		JLabel lblTestNumber = new JLabel("Test number");
		lblTestNumber.setBounds(10, 61, 100, 14);
		contentPane.add(lblTestNumber);
		
		JLabel lblStage = new JLabel("Stage");
		lblStage.setBounds(10, 86, 100, 14);
		contentPane.add(lblStage);
		
		JLabel lblFrame = new JLabel("Frame");
		lblFrame.setBounds(10, 111, 100, 14);
		contentPane.add(lblFrame);
		
		txtMaterial = new JTextField();
		txtMaterial.setBounds(376, 8, 86, 20);
		contentPane.add(txtMaterial);
		txtMaterial.setColumns(10);
		
		txtTestNumber = new JTextField();
		txtTestNumber.setBounds(376, 58, 86, 20);
		contentPane.add(txtTestNumber);
		txtTestNumber.setColumns(10);
		
		txtFrame = new JTextField();
		txtFrame.setBounds(376, 108, 86, 20);
		contentPane.add(txtFrame);
		txtFrame.setColumns(10);
		txtFrame.setText("Agilent Nano Indenter G200");
		
		
		JLabel lblRequiredInput = new JLabel("REQUIRED INPUT");
		lblRequiredInput.setBounds(10, 190, 156, 14);
		contentPane.add(lblRequiredInput);
		
		JLabel lblSurfaceVelocity = new JLabel("Surface velocity (nm/s)");
		lblSurfaceVelocity.setBounds(10, 215, 156, 14);
		contentPane.add(lblSurfaceVelocity);
		
		JLabel lblDepthLimit = new JLabel("Depth limit (nm)");
		lblDepthLimit.setBounds(10, 240, 156, 14);
		contentPane.add(lblDepthLimit);
		
		JLabel lblStrainRateTarget = new JLabel("Strain rate target (1/s)");
		lblStrainRateTarget.setBounds(10, 265, 156, 14);
		contentPane.add(lblStrainRateTarget);
		
		JLabel lblHarmonicDisplacement = new JLabel("Harmonic displacement (nm)");
		lblHarmonicDisplacement.setBounds(10, 290, 252, 14);
		contentPane.add(lblHarmonicDisplacement);
		
		JLabel lblFrequencyTarget = new JLabel("Frequency target (Hz)");
		lblFrequencyTarget.setBounds(10, 315, 156, 14);
		contentPane.add(lblFrequencyTarget);
		
		JLabel lblSurfaceDistancel = new JLabel("Surface distance (nm)");
		lblSurfaceDistancel.setBounds(10, 340, 156, 14);
		contentPane.add(lblSurfaceDistancel);
		
		JLabel lblPoissonsRate = new JLabel("Poissons rate");
		lblPoissonsRate.setBounds(10, 365, 156, 14);
		contentPane.add(lblPoissonsRate);
		
		txtSurfaceV = new JTextField();
		txtSurfaceV.setBounds(376, 212, 86, 20);
		contentPane.add(txtSurfaceV);
		txtSurfaceV.setColumns(10);
		txtSurfaceV.setText("10");
		
		txtDepth = new JTextField();
		txtDepth.setBounds(376, 237, 86, 20);
		contentPane.add(txtDepth);
		txtDepth.setColumns(10);
		txtDepth.setText("1000");
		
		txtStrain = new JTextField();
		txtStrain.setText("");
		txtStrain.setBounds(376, 262, 86, 20);
		contentPane.add(txtStrain);
		txtStrain.setColumns(10);
		txtStrain.setText("0.05");
		
		txtHarmonic = new JTextField();
		txtHarmonic.setBounds(376, 287, 86, 20);
		contentPane.add(txtHarmonic);
		txtHarmonic.setColumns(10);
		txtHarmonic.setText("2.0");
		
		txtFrequency = new JTextField();
		txtFrequency.setBounds(376, 312, 86, 20);
		contentPane.add(txtFrequency);
		txtFrequency.setColumns(10);
		txtFrequency.setText("45");
		
		txtSurfaceD = new JTextField();
		txtSurfaceD.setBounds(376, 337, 86, 20);
		contentPane.add(txtSurfaceD);
		txtSurfaceD.setColumns(10);
		txtSurfaceD.setText("1000");
		
		txtPoissons = new JTextField();
		txtPoissons.setBounds(376, 362, 86, 20);
		contentPane.add(txtPoissons);
		txtPoissons.setColumns(10);
		txtPoissons.setText("0.180");
		
		
		
		
		JLabel lblImportCurveFrom = new JLabel("Import P-h curve");
		lblImportCurveFrom.setBounds(10, 417, 156, 14);
		contentPane.add(lblImportCurveFrom);
		
		JLabel lblImportl = new JLabel("Import SSO-h curve");
		lblImportl.setBounds(10, 442, 252, 14);
		contentPane.add(lblImportl);
		
		JButton btnLoadCurve = new JButton("Load P-h curve");
		btnLoadCurve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtMaterial.getText().isEmpty() || txtTestNumber.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Errore: Compila tutti i campi vuoti");
				}else{
					JFileChooser fileChooser = new JFileChooser();
			        int returnValue = fileChooser.showOpenDialog(null);
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			        	File selectedFile = fileChooser.getSelectedFile();
			        	try {
			        		instance = new Instance(cbr.getNewId(),txtMaterial.getText(),(String) comboBox.getSelectedItem(),1,Integer.parseInt(txtTestNumber.getText()),cbr.retrieveIdByStage((String)cmbStage.getSelectedItem()),txtFrame.getText());
							instance.setCurve(load.load_data_csv_curve_markers(selectedFile.getPath()));
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	JOptionPane.showMessageDialog(null, selectedFile.getName()+" importing complete");
			        	if(instance.getCurve().size()>0) instance.setCsm(1);
			        	else instance.setCsm(0);
			        	
			        }
					
				}
				
			}
		});
		btnLoadCurve.setBounds(306, 413, 156, 23);
		contentPane.add(btnLoadCurve);
		
		JButton btnLoadSsol = new JButton("Load SSO-h curve");
		btnLoadSsol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtMaterial.getText().isEmpty() || txtTestNumber.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Errore: Compila tutti i campi vuoti");
				}else{
					
					JFileChooser fileChooser = new JFileChooser();
			        int returnValue = fileChooser.showOpenDialog(null);
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			        	File selectedFile = fileChooser.getSelectedFile();
			        	try {
							instance.setSsocurve(load.load_data_csv_SSOCurve(selectedFile.getPath()));
						} catch (NumberFormatException | IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
			        	JOptionPane.showMessageDialog(null, selectedFile.getName()+" importing complete");
			        	
			        	
			        }
					
				}
				
			}
		});
		btnLoadSsol.setBounds(306, 438, 156, 23);
		contentPane.add(btnLoadSsol);
		
		btnSaveCase = new JButton("Save");
		btnSaveCase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instance.setInput(new RequiredInput(
						Double.valueOf(txtSurfaceV.getText()),
						Double.valueOf(txtDepth.getText()),
						Double.valueOf(txtStrain.getText()),
						Double.valueOf(txtHarmonic.getText()),
						Double.valueOf(txtFrequency.getText()),
						Double.valueOf(txtSurfaceD.getText()),
						Double.valueOf(txtPoissons.getText())));
				btnSaveCase.setVisible(true);
				cbr.addInstance(instance, (String) comboBox.getSelectedItem());
				cbr.addFormat((String) comboBox_1.getSelectedItem(), instance.getId());
				cbr.addDescriptions("no description", instance.getId());
				cbr.addExperminent((String) comboBox_2.getSelectedItem(), instance.getId());
				dispose();

			}
		});
		btnSaveCase.setBounds(306, 521, 156, 23);
		contentPane.add(btnSaveCase);
		
		
		comboBox = new JComboBox();
		comboBox.setBounds(306, 33, 156, 20);
		contentPane.add(comboBox);
		String[] stage = {"Large Table", "Nano Vision Stage"};
		cmbStage = new JComboBox(stage);
		cmbStage.setBounds(306, 83, 156, 20);
		contentPane.add(cmbStage);
		
		JButton btnLoadCoefficientTip = new JButton("Load Coefficient Tip");
		btnLoadCoefficientTip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtMaterial.getText().isEmpty() || txtTestNumber.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Errore: Compila tutti i campi vuoti");
				}else{
					JFileChooser fileChooser = new JFileChooser();
			        int returnValue = fileChooser.showOpenDialog(null);
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			        	File selectedFile = fileChooser.getSelectedFile();
			        	try {
							List<Double> list = (load.load_data_coefficient_tips(selectedFile.getPath()));
							CoefficientsTip coeff = new CoefficientsTip(list.get(0),
									list.get(1),
									list.get(2),
									list.get(3),
									list.get(4),
									list.get(5),
									list.get(6),
									list.get(7),
									list.get(8));
							instance.setCoefficientsTip(coeff);
							
							
						} catch (NumberFormatException | IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
			        	JOptionPane.showMessageDialog(null, selectedFile.getName()+" importing complete");
			        	btnSaveCase.setVisible(true);
			     
					
				}
				   	
		        }
			}
		});
		btnLoadCoefficientTip.setBounds(306, 463, 156, 23);
		contentPane.add(btnLoadCoefficientTip);
		
		JLabel lblImportCoefficientsTip = new JLabel("Import coefficients tip");
		lblImportCoefficientsTip.setBounds(10, 467, 156, 14);
		contentPane.add(lblImportCoefficientsTip);
		
		JLabel lblFormat = new JLabel("Format");
		lblFormat.setBounds(10, 136, 252, 14);
		contentPane.add(lblFormat);
		
		comboBox_1 = new JComboBox(formats);
		comboBox_1.setBounds(306, 133, 156, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblExperimentType = new JLabel("Experiment type");
		lblExperimentType.setBounds(10, 161, 252, 14);
		contentPane.add(lblExperimentType);
		
		comboBox_2 = new JComboBox(exps);
		comboBox_2.setBounds(306, 158, 156, 20);
		contentPane.add(comboBox_2);
		btnSaveCase.setVisible(false);
	}
}
