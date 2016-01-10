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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ErrorSimilarityCoefficient extends JFrame {

	private JPanel contentPane;
	private JTextField txtM0;
	private JTextField txtM1;
	private JTextField txtM2;
	private JTextField txtM3;
	private JTextField txtM4;
	private JTextField txtM5;
	private JTextField txtM6;
	private JTextField txtM7;
	private JTextField txtM8;
	private JTextField txtStage1;
	private JTextField txtStage2;
	private JTextField txtStage3;
	private JTextField txtStage4;
	private JTextField txtStage5;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorSimilarityCoefficient frame = new ErrorSimilarityCoefficient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 * Create the frame.
	 */
	public ErrorSimilarityCoefficient(Instance instance) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				if(instance.getStage_obj().getType().contains("Nano")){
					txtStage3.setVisible(false);
					txtStage4.setVisible(false);
					txtStage5.setVisible(false);
					txtStage1.setText("material");
					txtStage2.setText("silica");
				}else{

					txtStage1.setText("material");
					txtStage2.setText("material");
					txtStage3.setText("silica");
					txtStage4.setText("material");
					txtStage5.setText("material");
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVerificaSeCi = new JLabel("1 Verifica se ci sono errori nella posizione dei campioni");
		lblVerificaSeCi.setBounds(10, 11, 366, 14);
		contentPane.add(lblVerificaSeCi);
		
		JLabel lblProvaAdInserire = new JLabel("2 Prova ad inserire questi nuovi coefficienti per la tip");
		lblProvaAdInserire.setBounds(10, 190, 366, 14);
		contentPane.add(lblProvaAdInserire);
		
		JLabel lblM = new JLabel("m0");
		lblM.setBounds(10, 218, 46, 14);
		contentPane.add(lblM);
		
		JLabel lblM_1 = new JLabel("m1");
		lblM_1.setBounds(10, 243, 46, 14);
		contentPane.add(lblM_1);
		
		JLabel lblM_2 = new JLabel("m2");
		lblM_2.setBounds(10, 268, 46, 14);
		contentPane.add(lblM_2);
		
		JLabel lblM_3 = new JLabel("m3");
		lblM_3.setBounds(10, 291, 46, 14);
		contentPane.add(lblM_3);
		
		JLabel lblM_4 = new JLabel("m4");
		lblM_4.setBounds(10, 316, 46, 14);
		contentPane.add(lblM_4);
		
		JLabel lblM_5 = new JLabel("m5");
		lblM_5.setBounds(10, 341, 46, 14);
		contentPane.add(lblM_5);
		
		JLabel lblM_6 = new JLabel("m6");
		lblM_6.setBounds(10, 366, 46, 14);
		contentPane.add(lblM_6);
		
		JLabel lblM_7 = new JLabel("m7");
		lblM_7.setBounds(10, 391, 46, 14);
		contentPane.add(lblM_7);
		
		JLabel lblM_8 = new JLabel("m8");
		lblM_8.setBounds(10, 416, 46, 14);
		contentPane.add(lblM_8);
		
		txtM0 = new JTextField();
		txtM0.setBounds(269, 215, 86, 20);
		contentPane.add(txtM0);
		txtM0.setColumns(10);
		txtM0.setText(String.valueOf(instance.getCoefficientsTip().getM0()));
		txtM1 = new JTextField();
		txtM1.setBounds(269, 240, 86, 20);
		contentPane.add(txtM1);
		txtM1.setColumns(10);
		txtM1.setText(String.valueOf(instance.getCoefficientsTip().getM1()));
		
		txtM2 = new JTextField();
		txtM2.setText("");
		txtM2.setBounds(269, 265, 86, 20);
		contentPane.add(txtM2);
		txtM2.setColumns(10);
		txtM2.setText(String.valueOf(instance.getCoefficientsTip().getM2()));
		txtM3 = new JTextField();
		txtM3.setBounds(269, 288, 86, 20);
		contentPane.add(txtM3);
		txtM3.setColumns(10);
		txtM3.setText(String.valueOf(instance.getCoefficientsTip().getM3()));
		txtM4 = new JTextField();
		txtM4.setBounds(269, 313, 86, 20);
		contentPane.add(txtM4);
		txtM4.setColumns(10);
		txtM4.setText(String.valueOf(instance.getCoefficientsTip().getM4()));
		txtM5 = new JTextField();
		txtM5.setBounds(269, 338, 86, 20);
		contentPane.add(txtM5);
		txtM5.setColumns(10);
		txtM5.setText(String.valueOf(instance.getCoefficientsTip().getM5()));
		txtM6 = new JTextField();
		txtM6.setBounds(269, 363, 86, 20);
		contentPane.add(txtM6);
		txtM6.setColumns(10);
		txtM6.setText(String.valueOf(instance.getCoefficientsTip().getM6()));
		txtM7 = new JTextField();
		txtM7.setBounds(269, 388, 86, 20);
		contentPane.add(txtM7);
		txtM7.setColumns(10);
		txtM7.setText(String.valueOf(instance.getCoefficientsTip().getM7()));
		txtM8 = new JTextField();
		txtM8.setBounds(269, 413, 86, 20);
		contentPane.add(txtM8);
		txtM8.setColumns(10);
		txtM8.setText(String.valueOf(instance.getCoefficientsTip().getM8()));
		
		txtStage1 = new JTextField();
		txtStage1.setEditable(false);
		txtStage1.setBounds(20, 36, 86, 20);
		contentPane.add(txtStage1);
		txtStage1.setColumns(10);
		
		txtStage2 = new JTextField();
		txtStage2.setEditable(false);
		txtStage2.setText("");
		txtStage2.setBounds(269, 36, 86, 20);
		contentPane.add(txtStage2);
		txtStage2.setColumns(10);
		
		txtStage3 = new JTextField();
		txtStage3.setEditable(false);
		txtStage3.setText("");
		txtStage3.setBounds(143, 75, 86, 20);
		contentPane.add(txtStage3);
		txtStage3.setColumns(10);
		
		txtStage4 = new JTextField();
		txtStage4.setEditable(false);
		txtStage4.setText("");
		txtStage4.setBounds(20, 116, 86, 20);
		contentPane.add(txtStage4);
		txtStage4.setColumns(10);
		
		txtStage5 = new JTextField();
		txtStage5.setEditable(false);
		txtStage5.setText("");
		txtStage5.setBounds(269, 116, 86, 20);
		contentPane.add(txtStage5);
		txtStage5.setColumns(10);
	}
}
