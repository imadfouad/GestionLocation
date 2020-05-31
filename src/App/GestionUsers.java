package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GestionUsers extends JFrame {

	private JPanel contentPane;
	
	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	private JTable table;
	private JTextField usernamefield;
	private JTextField passwordfield;
	private String id = null ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionUsers frame = new GestionUsers();
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
	public GestionUsers() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cnx =  ConnexionMysql.ConnexionDb();
		
		JLabel lblNewLabel_4 = new JLabel("Liste des Utilisateurs :");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(508, 98, 151, 18);
		contentPane.add(lblNewLabel_4);
		
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
		lblNewLabel_3.setBounds(10, 87, 46, 35);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable();
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (2).png"));
		lblNewLabel_2.setBounds(936, 82, 43, 35);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("Username :");
		lblNewLabel_5.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(138, 193, 81, 18);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(" Password :");
		lblNewLabel_6.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(136, 227, 69, 18);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Etat :");
		lblNewLabel_7.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(173, 261, 46, 21);
		contentPane.add(lblNewLabel_7);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"S\u00E9lectionnez", "Suspendu", "Actif"}));
		comboBox.setBounds(216, 257, 137, 22);
		contentPane.add(comboBox);
		
		usernamefield = new JTextField();
		usernamefield.setBounds(216, 186, 137, 26);
		contentPane.add(usernamefield);
		usernamefield.setColumns(10);
		
		passwordfield = new JTextField();
		passwordfield.setBounds(216, 220, 137, 26);
		contentPane.add(passwordfield);
		passwordfield.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = usernamefield.getText().toString();
				String pass = passwordfield.getText().toString();
				String etat = comboBox.getSelectedItem().toString();
				
				String sql = "insert into utilisateurs (username , password ,etat) values  (? , ? , ?) ";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, name);
					prepared.setString(2, pass);
					prepared.setString(3, etat);
					
					if(!name.equals("") && !pass.equals("") && !etat.equals("Sélectionnez")) {
						prepared.execute();
						UpdateTable();
						JOptionPane.showMessageDialog(null, "Utilisateur Ajouté");
						
						usernamefield.setText("");
						passwordfield.setText("");
						comboBox.setSelectedItem("Sélectionnez");
					}
					else {
						JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\t\u00E9l\u00E9chargement (3).jpg"));
		btnNewButton.setBounds(133, 322, 60, 60);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "delete from utilisateurs where id_user = '"+id+"'";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Utilisateur Supprimé");
					UpdateTable();
					
					usernamefield.setText("");
					passwordfield.setText("");
					comboBox.setSelectedItem("Sélectionnez");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\icono-de-eliminar-png-5.png"));
		btnNewButton_2.setBounds(273, 322, 60, 60);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(507, 120, 467, 330);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				
				 id = table.getModel().getValueAt(ligne, 0).toString();
				String name = table.getModel().getValueAt(ligne, 1).toString();
				String pass = table.getModel().getValueAt(ligne, 2).toString();
				String etat = table.getModel().getValueAt(ligne, 3).toString();
				
				usernamefield.setText(name);
				passwordfield.setText(pass);
				comboBox.setSelectedItem(etat);
		
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = usernamefield.getText().toString();
				String pass = passwordfield.getText().toString();
				String etat = comboBox.getSelectedItem().toString();
				
				String sql = "update utilisateurs set username = ? , password = ? ,etat = ? where id_user = '"+id+"'";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, name);
					prepared.setString(2, pass);
					prepared.setString(3, etat);
					if(!etat.equals("Sélectionnez") && !name.equals("") && !pass.equals("")) {
						prepared.execute();
						UpdateTable();
						
						JOptionPane.showMessageDialog(null, "Utilisateur Modifié");
						
						usernamefield.setText("");
						passwordfield.setText("");
						comboBox.setSelectedItem("Sélectionnez");
					}else {
						JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				//prepared.setString(1, x);
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\edit_male_user_256.png"));
		btnNewButton_1.setBounds(203, 322, 60, 60);
		contentPane.add(btnNewButton_1);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\Titre.jpg"));
		lblNewLabel_1.setBounds(0, 0, 1000, 80);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\content-e1472587975551.jpg"));
		lblNewLabel.setBounds(0, 0, 979, 471);
		contentPane.add(lblNewLabel);
		
		
	}
	public void UpdateTable()
	{
		String sql = "select * from utilisateurs";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
