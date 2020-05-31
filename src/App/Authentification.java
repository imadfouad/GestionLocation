package App;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import Application.ConnexionMysql;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Authentification {
	
	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	
	
	private JFrame frame;
	public JTextField usernamefield;
	public JPasswordField passfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel_2 = new JLabel("Username :");
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(348, 169, 78, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password :");
		lblNewLabel_3.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(353, 205, 64, 18);
		frame.getContentPane().add(lblNewLabel_3);
		
		usernamefield = new JTextField();
		usernamefield.setBounds(423, 162, 172, 26);
		frame.getContentPane().add(usernamefield);
		usernamefield.setColumns(10);
		
		JButton btnNewButton = new JButton("Se Connecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = usernamefield.getText().toString();
				String pass = passfield.getText().toString();
				boolean connexion = false;
				boolean suspendu = false;
				String sql = "select * from utilisateurs";
				try {
					if(!name.equals("") && !pass.equals(""))
					{
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						
						while(resultat.next())
						{
							String name1 = resultat.getString("username");
							String pass1 = resultat.getString("password");
							String etat1 = resultat.getString("etat");
							
							if(name1.equals(name) && pass1.equals(pass) && etat1.equals("Actif"))
								{
									connexion=true;
									break;
								}
							else if(name1.equals(name) && pass1.equals(pass) && etat1.equals("Suspendu")) {
								suspendu = true ;
								break;
							}
						}
						if(connexion)
						{
							JOptionPane.showMessageDialog(null, "Connexion Réussie");
							MenuAdministrateur obj = new MenuAdministrateur();
							obj.setVisible(true);
							obj.setLocationRelativeTo(null);
							frame.dispose();
						}
						else if(suspendu)
						{
							JOptionPane.showMessageDialog(null, "Compte suspendu , Veuillez contacter l'admin !");
						}
						
						else
							JOptionPane.showMessageDialog(null, "Connecion Echouée");
					}
					else
						JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		
		passfield = new JPasswordField();
		passfield.setBounds(423, 197, 172, 26);
		frame.getContentPane().add(passfield);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(446, 240, 123, 31);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\Titre.jpg"));
		lblNewLabel_1.setBounds(0, 0, 1000, 80);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\content-e1472587975551.jpg"));
		lblNewLabel.setBounds(0, 0, 984, 461);
		frame.getContentPane().add(lblNewLabel);
		
		cnx =  ConnexionMysql.ConnexionDb();
		
	}
}
