package cbrmain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cbrchart.Curve;
import cbrdatabase.CbrData;
import cbrdatabase.CurveSimilarity;
import cbrimport.ImportData;
import cbrmodel.Instance;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class SilicaTestImportJF extends JFrame {

	private JPanel contentPane;
	private ImportData load;
	private CbrData cbr;
	private File selectedFile;
	private Curve curve;
	private Instance newinstance;
	private JComboBox comboBox;
	/**
	 * @wbp.parser.constructor
	 */
	public SilicaTestImportJF(Instance instance) {
		load = new ImportData();
		cbr = new CbrData();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setTitle("Import silica test");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImportLoaddisplacementParameter = new JLabel("Import P-h curve parameter");
		lblImportLoaddisplacementParameter.setBounds(10, 27, 311, 14);
		contentPane.add(lblImportLoaddisplacementParameter);
		
		JLabel lblImportStiffnessSquare = new JLabel("Import SSO-h curve parameter");
		lblImportStiffnessSquare.setBounds(10, 62, 311, 14);
		contentPane.add(lblImportStiffnessSquare);
		
		JButton btnLoadCsv = new JButton("Load csv");
		btnLoadCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION){
		        	selectedFile = fileChooser.getSelectedFile();
		        	try {
						instance.setCurve(load.load_data_csv_curve_markers(selectedFile.getPath()));
						JOptionPane.showMessageDialog(null, selectedFile.getName()+" importing complete");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        		
		        }
			}
		});
		btnLoadCsv.setBounds(385, 23, 89, 23);
		contentPane.add(btnLoadCsv);
		
		JButton btnLoadCsv_1 = new JButton("Load csv");
		btnLoadCsv_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION){
		        	selectedFile = fileChooser.getSelectedFile();
		        	try {
						instance.setSsocurve(load.load_data_csv_SSOCurve(selectedFile.getPath()));
						JOptionPane.showMessageDialog(null, selectedFile.getName()+" importing complete");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        		
		        }
			}
		});
		btnLoadCsv_1.setBounds(385, 58, 89, 23);
		contentPane.add(btnLoadCsv_1);
		
		JButton btnRetrieve = new JButton("Retrieve");
		btnRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							List<Instance> cases = cbr.retrieveInstancesByMaterialAndFormat("silica", (String)comboBox.getSelectedItem());
							CurveSimilarity sim = new CurveSimilarity();
							
							for(Instance it:cases)
								it.setSimilarity(sim.evaluateSimilarity(instance, it));
							Instance maxInstance=sim.maxSimilarity(cases);
							if(maxInstance.getSimilarity()>=0.7){ // && !instance.verifyPlateuExist()){
								RetrieveJF frame = new RetrieveJF(instance,maxInstance,true,false,instance.getMaterial(),(String) comboBox.getSelectedItem());
								frame.setVisible(true);
								dispose();
							}else{
								ErrorSimilarityCoefficient frame = new ErrorSimilarityCoefficient(maxInstance);
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
		btnRetrieve.setBounds(385, 165, 89, 23);
		contentPane.add(btnRetrieve);
		
		JLabel lblFormatSample = new JLabel("Format sample");
		lblFormatSample.setBounds(10, 99, 311, 14);
		contentPane.add(lblFormatSample);
		String format[] = {"bulk","film"};
		comboBox = new JComboBox(format);
		comboBox.setBounds(385, 96, 89, 20);
		contentPane.add(comboBox);
	}
	
	public SilicaTestImportJF(String material) {
		load = new ImportData();
		cbr = new CbrData();
		newinstance = new Instance("silica");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setTitle("Import silica test");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImportLoaddisplacementParameter = new JLabel("Import P-h curve parameter");
		lblImportLoaddisplacementParameter.setBounds(10, 27, 311, 14);
		contentPane.add(lblImportLoaddisplacementParameter);
		
		JLabel lblImportStiffnessSquare = new JLabel("Import SSO-h curve parameter");
		lblImportStiffnessSquare.setBounds(10, 62, 311, 14);
		contentPane.add(lblImportStiffnessSquare);
		
		JButton btnLoadCsv = new JButton("Load csv");
		btnLoadCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION){
		        	selectedFile = fileChooser.getSelectedFile();
		        	try {
						newinstance.setCurve(load.load_data_csv_curve_markers(selectedFile.getPath()));
						JOptionPane.showMessageDialog(null, selectedFile.getName()+" importing complete");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        		
		        }
			}
		});
		btnLoadCsv.setBounds(385, 23, 89, 23);
		contentPane.add(btnLoadCsv);
		
		JButton btnLoadCsv_1 = new JButton("Load csv");
		btnLoadCsv_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION){
		        	selectedFile = fileChooser.getSelectedFile();
		        	try {
						newinstance.setSsocurve(load.load_data_csv_SSOCurve(selectedFile.getPath()));
						JOptionPane.showMessageDialog(null, selectedFile.getName()+" importing complete");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        		
		        }
			}
		});
		btnLoadCsv_1.setBounds(385, 58, 89, 23);
		contentPane.add(btnLoadCsv_1);
		
		JButton btnRetrieve = new JButton("Retrieve");
		btnRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							//List<Instance> cases = cbr.retrieveInstancesByMaterial("silica");
							List<Instance> cases = cbr.retrieveInstancesByMaterialAndFormat("silica", (String) comboBox.getSelectedItem());
							CurveSimilarity sim = new CurveSimilarity();
							
							for(Instance it:cases)
								it.setSimilarity(sim.evaluateSimilarity(newinstance, it));
							Instance maxInstance=sim.maxSimilarity(cases);
							if(maxInstance.getSimilarity()>=0.7 ){// && !newinstance.verifyPlateuExist()){
								RetrieveJF frame = new RetrieveJF(newinstance,maxInstance,true,true,material,(String) comboBox.getSelectedItem());
								frame.setVisible(true);
								dispose();
							}else{
								ErrorSimilarityCoefficient frame = new ErrorSimilarityCoefficient(maxInstance);
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
		btnRetrieve.setBounds(385, 165, 89, 23);
		contentPane.add(btnRetrieve);
		
		JLabel lblFormatSample = new JLabel("Format sample");
		lblFormatSample.setBounds(10, 99, 311, 14);
		contentPane.add(lblFormatSample);
		String format[] = {"bulk","film"};
		comboBox = new JComboBox(format);
		comboBox.setBounds(385, 96, 89, 20);
		contentPane.add(comboBox);
	}
}
