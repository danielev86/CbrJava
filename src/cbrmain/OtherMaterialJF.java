package cbrmain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cbrmodel.Instance;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OtherMaterialJF extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewM;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public OtherMaterialJF() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRetrieveANew = new JLabel("Retrieve a new material");
		lblRetrieveANew.setBounds(10, 36, 223, 14);
		contentPane.add(lblRetrieveANew);
		
		txtNewM = new JTextField();
		txtNewM.setBounds(293, 33, 131, 20);
		contentPane.add(txtNewM);
		txtNewM.setColumns(10);
		
		JButton btnRetrieve = new JButton("Retrieve");
		btnRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNewM.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Errore: Compila il campo");
				}else{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Instance instance = new Instance();
								SilicaTestImportJF frame = new SilicaTestImportJF(txtNewM.getText());
								frame.setVisible(true);
								dispose();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					
				}
				
			}
		});
		btnRetrieve.setBounds(293, 78, 131, 23);
		contentPane.add(btnRetrieve);
	}

}
