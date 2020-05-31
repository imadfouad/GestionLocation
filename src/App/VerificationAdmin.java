package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class VerificationAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField passfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerificationAdmin frame = new VerificationAdmin();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VerificationAdmin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MenuAdministrateur obj = new MenuAdministrateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
				
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (3) (1).png"));
		lblNewLabel_3.setBounds(0, 0, 46, 35);
		contentPane.add(lblNewLabel_3);
		
		
		JLabel lblNewLabel = new JLabel("Mot de passe d'Admin :");
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel.setBounds(66, 37, 130, 18);
		contentPane.add(lblNewLabel);
		
		passfield = new JTextField();
		passfield.setBounds(210, 32, 166, 26);
		contentPane.add(passfield);
		passfield.setColumns(10);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(passfield.getText().toString().equals("123"))
				{
					GestionUsers obj = new GestionUsers();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
					
					dispose();
				}else {
					VerificationAdmin obj1 = new VerificationAdmin();
					obj1.setVisible(true);
					obj1.setLocationRelativeTo(null);
					dispose();
				}
				
			}
		});
		btnNewButton.setBounds(167, 72, 89, 23);
		contentPane.add(btnNewButton);
	}

}
