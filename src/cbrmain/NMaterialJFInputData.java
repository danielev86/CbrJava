package cbrmain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cbrdatabase.CbrData;
import cbrimport.ImportData;
import cbrmodel.Instance;
import cbrmodel.RequiredInput;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
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

public class NMaterialJFInputData extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaterial;
	private JTextField txtTest;
	private JTextField txtFrame;
	private JTextField txtSurfaceV;
	private JTextField txtDepth;
	private JTextField txtStrain;
	private JTextField txtHarmonic;
	private JTextField txtFrequency;
	private JTextField txtSurfaceD;
	private JTextField txtPoissons;
	private Instance instance;
	private ImportData load;
	private CbrData cbr;
	private List<String> tmp;
	private String[] tips;
	private JComboBox cbxTip;
	private JComboBox cmbStage;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public NMaterialJFInputData(String material, String format) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				tmp=cbr.retrieveAllTips();
				tips = new String[tmp.size()];
				tips=tmp.toArray(tips);
				cbxTip.removeAllItems();
				for(int i=0;i<tips.length;i++){
					cbxTip.addItem((String) tips[i]);
				}
				
			}
		});
		this.setResizable(false);
		cbr = new CbrData();
		load = new ImportData();
		setTitle("Import new material");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(10, 11, 170, 14);
		contentPane.add(lblMaterial);
		
		JLabel lblTip = new JLabel("Tip");
		lblTip.setBounds(10, 36, 170, 14);
		contentPane.add(lblTip);
		
		JLabel lblTestNumber = new JLabel("Test number");
		lblTestNumber.setBounds(10, 61, 170, 14);
		contentPane.add(lblTestNumber);
		
		JLabel lblStage = new JLabel("Stage");
		lblStage.setBounds(10, 86, 170, 14);
		contentPane.add(lblStage);
		
		JLabel lblFrame = new JLabel("Frame");
		lblFrame.setBounds(10, 111, 170, 14);
		contentPane.add(lblFrame);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 141, 384, 2);
		contentPane.add(separator);
		
		JLabel lblRequiredInput = new JLabel("REQUIRED INPUT");
		lblRequiredInput.setBounds(10, 154, 170, 14);
		contentPane.add(lblRequiredInput);
		
		JLabel lblSurfaceVelocity = new JLabel("Surface approach velocity (nm/s)");
		lblSurfaceVelocity.setBounds(10, 192, 170, 14);
		contentPane.add(lblSurfaceVelocity);
		
		JLabel lblDepthLimit = new JLabel("Depth limit (nm)");
		lblDepthLimit.setBounds(10, 217, 170, 14);
		contentPane.add(lblDepthLimit);
		
		JLabel lblStrainRateTarget = new JLabel("Strain rate target (1/s)");
		lblStrainRateTarget.setBounds(10, 242, 170, 14);
		contentPane.add(lblStrainRateTarget);
		
		JLabel lblHarmonicDisplacement = new JLabel("Harmonic displacement (nm)");
		lblHarmonicDisplacement.setBounds(10, 267, 170, 14);
		contentPane.add(lblHarmonicDisplacement);
		
		JLabel lblFrequencyTarget = new JLabel("Frequency target (Hz)");
		lblFrequencyTarget.setBounds(10, 292, 170, 14);
		contentPane.add(lblFrequencyTarget);
		
		JLabel lblSurfaceDistance = new JLabel("Surface approach distance (nm)");
		lblSurfaceDistance.setBounds(10, 318, 170, 14);
		contentPane.add(lblSurfaceDistance);
		
		JLabel lblPoissonsRate = new JLabel("Poisson's ratio");
		lblPoissonsRate.setBounds(10, 342, 170, 14);
		contentPane.add(lblPoissonsRate);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 367, 384, 2);
		contentPane.add(separator_1);
		
		JLabel lblImportLoadFrom = new JLabel("Import P-h curve file");
		lblImportLoadFrom.setBounds(10, 416, 236, 14);
		contentPane.add(lblImportLoadFrom);
		
		txtMaterial = new JTextField();
		txtMaterial.setBounds(288, 8, 86, 20);
		contentPane.add(txtMaterial);
		txtMaterial.setColumns(10);
		txtMaterial.setText(material);
		
		txtTest = new JTextField();
		txtTest.setText("");
		txtTest.setBounds(288, 58, 86, 20);
		contentPane.add(txtTest);
		txtTest.setColumns(10);
		
		txtFrame = new JTextField();
		txtFrame.setBounds(288, 108, 86, 20);
		contentPane.add(txtFrame);
		txtFrame.setColumns(10);
		txtFrame.setText("Agilent Nano Indenter G200");
		
		txtSurfaceV = new JTextField();
		txtSurfaceV.setBounds(288, 189, 86, 20);
		contentPane.add(txtSurfaceV);
		txtSurfaceV.setColumns(10);
		txtSurfaceV.setText("10");
		
		txtDepth = new JTextField();
		txtDepth.setText("");
		txtDepth.setBounds(288, 214, 86, 20);
		contentPane.add(txtDepth);
		txtDepth.setColumns(10);
		txtDepth.setText("1000");
		
		txtStrain = new JTextField();
		txtStrain.setText("");
		txtStrain.setBounds(288, 239, 86, 20);
		contentPane.add(txtStrain);
		txtStrain.setColumns(10);
		txtStrain.setText("0.05");

		txtHarmonic = new JTextField();
		txtHarmonic.setText("");
		txtHarmonic.setBounds(288, 264, 86, 20);
		contentPane.add(txtHarmonic);
		txtHarmonic.setColumns(10);
		txtHarmonic.setText("2.0");
		
		txtFrequency = new JTextField();
		txtFrequency.setBounds(288, 289, 86, 20);
		contentPane.add(txtFrequency);
		txtFrequency.setColumns(10);
		txtFrequency.setText("45");
		
		txtSurfaceD = new JTextField();
		txtSurfaceD.setBounds(288, 315, 86, 20);
		contentPane.add(txtSurfaceD);
		txtSurfaceD.setColumns(10);
		txtSurfaceD.setText("1000");
		
		txtPoissons = new JTextField();
		txtPoissons.setBounds(288, 339, 86, 20);
		contentPane.add(txtPoissons);
		txtPoissons.setColumns(10);
		txtPoissons.setText("0.180");
		
		JButton btnLoadCsv = new JButton("Load csv ");
		btnLoadCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtTest.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Errore: Compila tutti i campi");
					
				}else{
					instance = new Instance(0,txtMaterial.getText(),(String) cbxTip.getSelectedItem(),1,Integer.parseInt(txtTest.getText()),cbr.retrieveIdByStage((String)cmbStage.getSelectedItem()),txtFrame.getText());
					instance.setInput(new RequiredInput(Double.valueOf(txtSurfaceV.getText()),
							Double.valueOf(txtDepth.getText()),
							Double.valueOf(txtStrain.getText()),
							Double.valueOf(txtHarmonic.getText()),
							Double.valueOf(txtFrequency.getText()),
							Double.valueOf(txtSurfaceD.getText()),
							Double.valueOf(txtPoissons.getText())));
					JFileChooser fileChooser = new JFileChooser();
			        int returnValue = fileChooser.showOpenDialog(null);
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			        	File selectedFile = fileChooser.getSelectedFile();
			        	try {
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
		btnLoadCsv.setBounds(285, 412, 89, 23);
		contentPane.add(btnLoadCsv);
		
		JButton btnRetrieve = new JButton("Retrieve");
		btnRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							NMRetrieveJF frame = new NMRetrieveJF(instance,material,format);
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Errore: Compila tutti i campi o carica correttamente i file");
						}
					}
				});

			}
		});
		btnRetrieve.setBounds(288, 510, 89, 23);
		contentPane.add(btnRetrieve);
		
		JLabel lblImportStiffnessSquare = new JLabel("Import SSO-h curve file");
		lblImportStiffnessSquare.setBounds(10, 441, 236, 14);
		contentPane.add(lblImportStiffnessSquare);
		
		JButton btnStiffness = new JButton("Load csv");
		btnStiffness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTest.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Errore: Compila tutti i campi");
				}else{
					JFileChooser fileChooser = new JFileChooser();
			        int returnValue = fileChooser.showOpenDialog(null);
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			        	File selectedFile = fileChooser.getSelectedFile();
			        	try {
							instance.setSsocurve(load.load_data_csv_SSOCurve(selectedFile.getPath()));;
						} catch (NumberFormatException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        	JOptionPane.showMessageDialog(null, selectedFile.getName()+" importing complete");
			        	
				}
					
				}
				
			}
		});
		btnStiffness.setBounds(285, 437, 89, 23);
		contentPane.add(btnStiffness);
		
		cbxTip = new JComboBox();
		cbxTip.setBounds(223, 33, 151, 20);
		contentPane.add(cbxTip);
		
		String[] stage = {"Large Table", "Nano Vision Stage"};
		cmbStage = new JComboBox(stage);
		cmbStage.setBounds(223, 83, 151, 20);
		contentPane.add(cmbStage);
		
		JLabel lblExperimentType = new JLabel("Experiment type");
		lblExperimentType.setBounds(10, 391, 170, 14);
		contentPane.add(lblExperimentType);
		
		String array[] = {"DCM","XP"};
		JComboBox comboBox = new JComboBox(array);
		comboBox.setBounds(288, 388, 86, 20);
		contentPane.add(comboBox);
	}
}
