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

public class SampleTestImport extends JFrame {

	private JPanel contentPane;
	private ImportData load;
	private CbrData cbr;
	private File selectedFile;
	private Curve curve;
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SampleTestImport frame = new SampleTestImport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 * Create the frame.
	 */
	public SampleTestImport(Instance instance,String format) {
		load = new ImportData();
		cbr = new CbrData();
		curve = new Curve();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Import sample parameter");
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImportSampleParameter = new JLabel("Import P-h curve parameter");
		lblImportSampleParameter.setBounds(10, 31, 301, 14);
		contentPane.add(lblImportSampleParameter);
		
		JLabel lblImportStiffnessSquare = new JLabel("Import SSO-h curve parameter");
		lblImportStiffnessSquare.setBounds(10, 69, 301, 14);
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
		btnLoadCsv.setBounds(321, 27, 89, 23);
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
		btnLoadCsv_1.setBounds(321, 65, 89, 23);
		contentPane.add(btnLoadCsv_1);
		
		JButton btnRetrieve = new JButton("Retrieve");
		btnRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							List<Instance> cases = cbr.retrieveAllInstances();
							CurveSimilarity sim = new CurveSimilarity();
							
							for(Instance it:cases)
								it.setSimilarity(sim.evaluateSimilarity(instance, it));
							Instance maxInstance=sim.maxSimilarity(cases);
							if(maxInstance.getSimilarity()>=0.7  && !instance.verifyPlateuExist()){
								RetrieveSampleJF frame = new RetrieveSampleJF(instance,maxInstance);
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
		btnRetrieve.setBounds(321, 108, 89, 23);
		contentPane.add(btnRetrieve);
	}

}
