package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class GestionVehicules extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	private JTextField matriculefield;
	private JTextField marquefield;
	private JTextField typefield;
	private JTextField carbfield;
	private JTextField compteurfield;
	private JTextField datefield;
	private String matricule;
	private JComboBox comboBox;
	private JTextField findfield;
		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionVehicules frame = new GestionVehicules();
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
	public GestionVehicules() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		cnx =  ConnexionMysql.ConnexionDb();
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable();
			}
		});
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Num Immatriculation :");
		lblNewLabel_8.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(63, 126, 141, 18);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(" Marque :");
		lblNewLabel_9.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_9.setBounds(128, 190, 72, 18);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_7 = new JLabel("Type :");
		lblNewLabel_7.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(148, 219, 52, 18);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_6 = new JLabel("Carburant :");
		lblNewLabel_6.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(122, 254, 81, 18);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel("Compteur KM :");
		lblNewLabel_5.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(96, 286, 107, 18);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel("Date de mise en circulation :");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(31, 317, 172, 18);
		contentPane.add(lblNewLabel_4);
		
		matriculefield = new JTextField();
		matriculefield.setBounds(207, 122, 141, 20);
		contentPane.add(matriculefield);
		matriculefield.setColumns(10);
		
		marquefield = new JTextField();
		marquefield.setBounds(207, 188, 141, 20);
		contentPane.add(marquefield);
		marquefield.setColumns(10);
		
		typefield = new JTextField();
		typefield.setBounds(207, 219, 141, 20);
		contentPane.add(typefield);
		typefield.setColumns(10);
		
		carbfield = new JTextField();
		carbfield.setBounds(207, 250, 141, 20);
		contentPane.add(carbfield);
		carbfield.setColumns(10);
		
		compteurfield = new JTextField();
		compteurfield.setBounds(207, 282, 141, 20);
		contentPane.add(compteurfield);
		compteurfield.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(207, 315, 141, 20);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		
		
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (2).png"));
		lblNewLabel_2.setBounds(936, 82, 43, 35);
		contentPane.add(lblNewLabel_2);
		
		
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
		
		JLabel lblNewLabel_10 = new JLabel("Parking :");
		lblNewLabel_10.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_10.setBounds(138, 155, 62, 24);
		contentPane.add(lblNewLabel_10);
		 
		 JLabel lblNewLabel_11 = new JLabel("Rechercher par N_Immatriculation :");
		 lblNewLabel_11.setBounds(507, 91, 218, 14);
		 contentPane.add(lblNewLabel_11);
		 
		 findfield = new JTextField();
		 findfield.setBounds(721, 89, 107, 20);
		 contentPane.add(findfield);
		 findfield.setColumns(10);
		 
		 JButton okbtn = new JButton("OK");
		 okbtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		

				String code = findfield.getText().toString();
				if(!code.equals("")) {
				FindTable(code);
				}else
					JOptionPane.showMessageDialog(null, "Saisissez le code d'immatriculation !");
		 		
		 	}
		 });
		 okbtn.setBounds(834, 87, 52, 23);
		 contentPane.add(okbtn);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(207, 155, 141, 22);
		contentPane.add(comboBox);
		
		remplirCombobox();
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(507, 120, 467, 330);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				 matricule = table.getModel().getValueAt(ligne, 0).toString();
				String parking = table.getModel().getValueAt(ligne, 1).toString();
				String marque = table.getModel().getValueAt(ligne, 2).toString();
				String type = table.getModel().getValueAt(ligne, 3).toString();
				String carburant = table.getModel().getValueAt(ligne, 4).toString();
				String compteur = table.getModel().getValueAt(ligne, 5).toString();
				String date_cirucle = table.getModel().getValueAt(ligne, 6).toString();
				
				matriculefield.setText(marque);
				comboBox.setSelectedItem(parking);
				marquefield.setText(marque);
				typefield.setText(type);
				carbfield.setText(carburant);
				compteurfield.setText(compteur);
				
				
				
				Date date2=null;
				try {
					date2 = new SimpleDateFormat("dd/MM/yyyy").parse(date_cirucle);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    					/////////////////////////////////////////////////////////
				dateChooser.setDate(date2);
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez le véhicule");
				}
				else
				{
					String id = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "delete from véhicules where matricule = ?";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, id);
						prepared.execute();
						
						JOptionPane.showMessageDialog(null, "véhicule Supprimé");
						UpdateTable();
						
						matriculefield.setText("");
						marquefield.setText("");
						typefield.setText("");
						carbfield.setText("");
						compteurfield.setText("");
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\Remove_Car-512.png"));
		btnNewButton.setBounds(288, 363, 62, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String matricule = matriculefield.getText().toString();
				String parking = comboBox.getSelectedItem().toString();
				String marque = marquefield.getText().toString();
				String type = typefield.getText().toString();
				String carburant = carbfield.getText().toString();
				String compteur = compteurfield.getText().toString();
				String date_circule = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				String sql = "insert into véhicules (matricule, parking , marque , type , carburant , CompteurKm , date_circulation) values  (? , ? , ? , ? , ? , ? , ?) ";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, matricule);
					prepared.setString(2, parking);
					prepared.setString(3, marque);
					prepared.setString(4, type);
					prepared.setString(5, carburant);
					prepared.setString(6, compteur);
					prepared.setString(7, date_circule);
					
					if(!matricule.equals("") && !marque.equals("") && !type.equals("") && !carburant.equals("") && !compteur.equals("")) {
					
						prepared.execute();
						UpdateTable();
						JOptionPane.showMessageDialog(null, "Véhicule Ajouté");
						
						matriculefield.setText("");
						marquefield.setText("");
						typefield.setText("");
						carbfield.setText("");
						compteurfield.setText("");
					}else
						JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs !");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\car_2-add-512.png"));
		btnNewButton_1.setBounds(96, 362, 62, 60);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez le véhicule");
				}
				else
				{
					String matricule = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "update véhicules set matricule = ?, parking = ? , marque= ? , type=? , carburant=? ,CompteurKm = ? , date_circulation =? where matricule='"+matricule+"'" ;
					try {
						prepared = cnx.prepareStatement(sql);
						
						prepared.setString(1, matriculefield.getText().toString() );
						prepared.setString(2,comboBox.getSelectedItem().toString());
						prepared.setString(3, marquefield.getText().toString() );
						prepared.setString(4, typefield.getText().toString() );
						prepared.setString(5, carbfield.getText().toString() );
						prepared.setString(6, compteurfield.getText().toString() );	
						prepared.setString(7, ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
						
						
						if(!matriculefield.getText().toString().equals("") && !marquefield.getText().toString().equals("") && !typefield.getText().toString().equals("") && !carbfield.getText().toString().equals("") && !compteurfield.getText().toString().equals("") )
						{
							prepared.execute();
							JOptionPane.showMessageDialog(null, "Véhicule Modifié");
							UpdateTable();
							
							matriculefield.setText("");
							marquefield.setText("");
							typefield.setText("");
							carbfield.setText("");
							compteurfield.setText("");
							
						}
						else
							JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\car_2-edit-512.png"));
		btnNewButton_2.setBounds(193, 363, 62, 60);
		contentPane.add(btnNewButton_2);
		
		
		
		
		
		
		
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\Titre.jpg"));
		lblNewLabel_1.setBounds(0, 0, 984, 80);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\content-e1472587975551.jpg"));
		lblNewLabel.setBounds(0, 0, 979, 456);
		contentPane.add(lblNewLabel);
		
		
		
	}
	
	public void UpdateTable()
	{
		String sql = "select * from véhicules";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void remplirCombobox()
	{
		String sql = "select * from parkings";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while(resultat.next())
			{
				String parking = resultat.getString("nom_parking").toString();
				
				comboBox.addItem(parking);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void FindTable(String code)
	{
		String sql = "select * from véhicules where matricule='"+code+"'";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
