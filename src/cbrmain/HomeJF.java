package cbrmain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import cbrchart.Curve;
import cbrdatabase.CbrData;
import cbrimport.ImportData;
import cbrmodel.Instance;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeJF extends JFrame {

	private JPanel contentPane;
	private ImportData load;
	private CbrData cbr;
	private File selectedFile;
	private Curve curve;
	private List<String> tmp;
	private String[] materials;
	private JComboBox cbxMaterial;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeJF frame = new HomeJF();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeJF() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				tmp=cbr.retrieveAllMaterial();
				materials = new String[tmp.size()];
				materials=tmp.toArray(materials);
				cbxMaterial.removeAllItems();
				for(int i=0;i<materials.length;i++){
					cbxMaterial.addItem((String) materials[i]);
				}
				
			}
		});
		cbr = new CbrData();
		load = new ImportData();
		curve = new Curve();
		tmp = cbr.retrieveAllMaterial();
		materials = tmp.toArray(new String[tmp.size()]);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		setTitle("CBR Nanoindenter Beta Version");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnAction = new JMenu("Action");
		menuBar.add(mnAction);
		
		JMenuItem mntmImportCase = new JMenuItem("Import case");
		mntmImportCase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ImportCaseJF frame = new ImportCaseJF();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mnAction.add(mntmImportCase);
		
		JMenuItem mntmRefreshCasebase = new JMenuItem("Refresh casebase");
		mntmRefreshCasebase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tmp=cbr.retrieveAllMaterial();
				materials = new String[tmp.size()];
				materials=tmp.toArray(materials);
				cbxMaterial.removeAllItems();
				for(int i=0;i<materials.length;i++){
					cbxMaterial.addItem((String) materials[i]);
				}
			}
		});
		mnAction.add(mntmRefreshCasebase);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(22, 27, 192, 14);
		contentPane.add(lblMaterial);
		
		JLabel lblRetrieveACase = new JLabel("Retrieve a case ");
		lblRetrieveACase.setBounds(22, 97, 192, 14);
		contentPane.add(lblRetrieveACase);
		
		cbxMaterial = new JComboBox();
		cbxMaterial.setBounds(272, 24, 152, 20);
		contentPane.add(cbxMaterial);
		
		JButton btnRetrieve = new JButton("Retrieve");
		btnRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Instance instance = new Instance((String) cbxMaterial.getSelectedItem());
							SilicaTestImportJF frame = new SilicaTestImportJF(instance);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnRetrieve.setBounds(272, 93, 152, 23);
		contentPane.add(btnRetrieve);
		
		
		JButton btnOtherMaterials = new JButton("Other Materials");
		btnOtherMaterials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							OtherMaterialJF frame = new OtherMaterialJF();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnOtherMaterials.setBounds(272, 55, 152, 23);
		contentPane.add(btnOtherMaterials);
	}
}
