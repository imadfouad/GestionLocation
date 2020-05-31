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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GestionReservations extends JFrame {

	private JPanel contentPane;
	private JTable table;

	private String code_reservation;
	private JComboBox comboBox_1;
	
	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	private JTextField codefield;
	private JTable table_1;
	private JTable table_2;
	private JTextField findfield;
	private JComboBox comboBox_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionReservations frame = new GestionReservations();
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
	public GestionReservations() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 642);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cnx =  ConnexionMysql.ConnexionDb();
		
		JLabel lblNewLabel_4 = new JLabel("Code_reservation :");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(51, 180, 119, 21);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("Date_reservation :");
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel.setBounds(51, 221, 104, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Date_depart :");
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(76, 262, 94, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date_retour :");
		lblNewLabel_3.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(77, 300, 78, 18);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Etat :");
		lblNewLabel_5.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(124, 336, 46, 19);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Liste des r\u00E9servations valides :");
		lblNewLabel_6.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(508, 156, 184, 19);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Liste des r\u00E9servations non valides :");
		lblNewLabel_7.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(508, 305, 201, 18);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Liste des r\u00E9servations annul\u00E9es :");
		lblNewLabel_8.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(508, 457, 195, 21);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_10 = new JLabel("Client :");
		lblNewLabel_10.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_10.setBounds(109, 110, 46, 19);
		contentPane.add(lblNewLabel_10);
		 
		 JLabel lblNewLabel_11 = new JLabel("Rechercher par code_reservation :");
		 lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblNewLabel_11.setBounds(508, 109, 184, 14);
		 contentPane.add(lblNewLabel_11);
		 
		 JLabel lblNewLabel_12 = new JLabel("V\u00E9hicule :");
		 lblNewLabel_12.setBounds(102, 140, 68, 14);
		 contentPane.add(lblNewLabel_12);
		 
		  comboBox_2 = new JComboBox();
		 comboBox_2.setBounds(178, 138, 167, 22);
		 contentPane.add(comboBox_2);
		 
		 remplirCombobox1();
		
		 comboBox_1 = new JComboBox();
		comboBox_1.setBounds(178, 105, 167, 22);
		contentPane.add(comboBox_1);
		
		remplirCombobox();

		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"S\u00E9lectionnez", "valide", "non valide", "annul\u00E9e"}));
		comboBox.setBounds(178, 329, 167, 27);
		contentPane.add(comboBox);
		
		codefield = new JTextField();
		codefield.setBounds(178, 174, 169, 27);
		contentPane.add(codefield);
		codefield.setColumns(10);
		
		findfield = new JTextField();
		findfield.setBounds(688, 103, 140, 27);
		contentPane.add(findfield);
		findfield.setColumns(10);
		
		
		
		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(176, 212, 169, 27);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		
		JDateChooser dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(178, 253, 168, 27);
		contentPane.add(dateChooser1);
		dateChooser1.setDateFormatString("dd/MM/yyyy");
		
		JDateChooser dateChooser2 = new JDateChooser();
		dateChooser2.setBounds(177, 291, 168, 27);
		contentPane.add(dateChooser2);
		dateChooser2.setDateFormatString("dd/MM/yyyy");
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(508, 186, 467, 91);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MenuAdministrateur obj = new MenuAdministrateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
				
			}
		});
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String code = codefield.getText().toString();
				String client = comboBox_1.getSelectedItem().toString();
				String date_reservation = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				String date_depart = ((JTextField)dateChooser1.getDateEditor().getUiComponent()).getText();
				String date_retour = ((JTextField)dateChooser2.getDateEditor().getUiComponent()).getText();
				String etat = comboBox.getSelectedItem().toString();
				
				String sql = "insert into reservations (code_reservation, client ,date_reservation , date_depart , date_retour ,etat) values  (? , ? , ? , ? , ? , ?) ";
				try {
					prepared = cnx.prepareStatement(sql);
					
					prepared.setString(1, code);
					prepared.setString(2, client);
					prepared.setString(3, date_reservation);
					prepared.setString(4, date_depart);
					prepared.setString(5, date_retour);
					prepared.setString(6, etat);
					
					if(!etat.equals("Sélectionnez") && !code.equals("")) {
						prepared.execute();
						UpdateTable();
						UpdateTable1();
						UpdateTable2();
						JOptionPane.showMessageDialog(null, "Réservation Ajoutée");
						codefield.setText("");
						comboBox.setSelectedItem("Sélectionnez");
					}else {
						JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
					}
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(508, 334, 466, 98);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table_1.getSelectedRow();
				 code_reservation = table_1.getModel().getValueAt(ligne, 0).toString();
				 String etat = table_1.getModel().getValueAt(ligne, 5).toString();
				String client = table_1.getModel().getValueAt(ligne, 1).toString();
				 String date_reservation = table_1.getModel().getValueAt(ligne, 2).toString();
				 String date_depart = table_1.getModel().getValueAt(ligne, 3).toString();
				 String date_retour = table_1.getModel().getValueAt(ligne, 4).toString();
				
				codefield.setText(code_reservation);
				comboBox.setSelectedItem(etat);
				
				 Date date2=null;
					try {
						date2 = new SimpleDateFormat("dd/MM/yyyy").parse(date_reservation);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    					/////////////////////////////////////////////////////////
					dateChooser.setDate(date2);
					
					
					Date date3=null;
					try {
						date3 = new SimpleDateFormat("dd/MM/yyyy").parse(date_depart);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    					/////////////////////////////////////////////////////////
					dateChooser1.setDate(date3);
					
					Date date4=null;
					try {
						date4 = new SimpleDateFormat("dd/MM/yyyy").parse(date_retour);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    					/////////////////////////////////////////////////////////
					dateChooser2.setDate(date4);
				
				
				
				
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(507, 489, 467, 86);
		contentPane.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table_2.getSelectedRow();
				 code_reservation = table_2.getModel().getValueAt(ligne, 0).toString();
				 String etat = table_2.getModel().getValueAt(ligne, 5).toString();
				String client = table_2.getModel().getValueAt(ligne, 1).toString();
				 String date_reservation = table_2.getModel().getValueAt(ligne, 2).toString();
				 String date_depart = table_2.getModel().getValueAt(ligne, 3).toString();
				 String date_retour = table_2.getModel().getValueAt(ligne, 4).toString();
				
				codefield.setText(code_reservation);
				comboBox.setSelectedItem(etat);
				
				 Date date2=null;
					try {
						date2 = new SimpleDateFormat("dd/MM/yyyy").parse(date_reservation);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    					/////////////////////////////////////////////////////////
					dateChooser.setDate(date2);
					
					
					Date date3=null;
					try {
						date3 = new SimpleDateFormat("dd/MM/yyyy").parse(date_depart);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    					/////////////////////////////////////////////////////////
					dateChooser1.setDate(date3);
					
					Date date4=null;
					try {
						date4 = new SimpleDateFormat("dd/MM/yyyy").parse(date_retour);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    					/////////////////////////////////////////////////////////
					dateChooser2.setDate(date4);
				
				
			}
		});
		scrollPane_2.setViewportView(table_2);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\add-circle-green-512.png"));
		btnNewButton.setBounds(134, 387, 60, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = 9999 ;
				
				if(table.getSelectedRow()==-1 && table_1.getSelectedRow()==-1 && table_2.getSelectedRow()==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez la réservation");
				}
				else {
					if(table.getSelectedRow() != -1) {
						ligne = table.getSelectedRow();
						
						String code_reservation = table.getModel().getValueAt(ligne, 0).toString();
						String client = comboBox_1.getSelectedItem().toString();
						String etat = comboBox.getSelectedItem().toString();

						String sql = "update reservations set code_reservation=?, client = ? ,  date_reservation =? , date_depart = ? , date_retour = ? ,etat=?  where code_reservation='"+code_reservation+"'" ;
						try {
							prepared = cnx.prepareStatement(sql);
							
							prepared.setString(1, codefield.getText().toString());
							prepared.setString(2, client);
							prepared.setString(3, ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
							prepared.setString(4, ((JTextField)dateChooser1.getDateEditor().getUiComponent()).getText());
							prepared.setString(5, ((JTextField)dateChooser2.getDateEditor().getUiComponent()).getText());
							prepared.setString(6, etat);
							
							
							if(!etat.equals("Sélectionnez") && !codefield.getText().toString().equals("")) {
								prepared.execute();
								JOptionPane.showMessageDialog(null, "Réservation Modifiée");
								UpdateTable();
							codefield.setText("");
							comboBox.setSelectedItem("Sélectionnez");
							}else {
								JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
						
					
					if(table_1.getSelectedRow() != -1) {
						ligne = table_1.getSelectedRow();
						
						String code_reservation = table_1.getModel().getValueAt(ligne, 0).toString();
						String client = comboBox_1.getSelectedItem().toString();
						String etat = comboBox.getSelectedItem().toString();

						String sql = "update reservations set code_reservation=?, client = ? ,  date_reservation =? , date_depart = ? , date_retour = ? ,etat=?  where code_reservation='"+code_reservation+"'" ;
						try {
							prepared = cnx.prepareStatement(sql);
							
							prepared.setString(1, codefield.getText().toString());
							prepared.setString(2, client);
							prepared.setString(3, ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
							prepared.setString(4, ((JTextField)dateChooser1.getDateEditor().getUiComponent()).getText());
							prepared.setString(5, ((JTextField)dateChooser2.getDateEditor().getUiComponent()).getText());
							prepared.setString(6, etat);
							
							
							if(!etat.equals("Sélectionnez") && !codefield.getText().toString().equals("")) {
								prepared.execute();
								JOptionPane.showMessageDialog(null, "Réservation Modifiée");
								UpdateTable1();
							codefield.setText("");
							comboBox.setSelectedItem("Sélectionnez");
							}else {
								JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
						
					
					if(table_2.getSelectedRow() != -1) {
						ligne= table_2.getSelectedRow();
						
						
						String code_reservation = table_2.getModel().getValueAt(ligne, 0).toString();
						String client = comboBox_1.getSelectedItem().toString();
						String etat = comboBox.getSelectedItem().toString();

						String sql = "update reservations set code_reservation=?, client = ? ,  date_reservation =? , date_depart = ? , date_retour = ? ,etat=?  where code_reservation='"+code_reservation+"'" ;
						try {
							prepared = cnx.prepareStatement(sql);
							
							prepared.setString(1, codefield.getText().toString());
							prepared.setString(2, client);
							prepared.setString(3, ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
							prepared.setString(4, ((JTextField)dateChooser1.getDateEditor().getUiComponent()).getText());
							prepared.setString(5, ((JTextField)dateChooser2.getDateEditor().getUiComponent()).getText());
							prepared.setString(6, etat);
							
							
							if(!etat.equals("Sélectionnez") && !codefield.getText().toString().equals("")) {
								prepared.execute();
								JOptionPane.showMessageDialog(null, "Réservation Modifiée");
								UpdateTable2();
							codefield.setText("");
							comboBox.setSelectedItem("Sélectionnez");
							}else {
								JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
				}
					
				
				
			}
			}});
		
		JButton okbtn = new JButton("OK");
		okbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String code = findfield.getText().toString();
				if(!code.equals("")) {
					String sql = "select * from reservations where code_reservation='"+code+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						while(resultat.next())
						{
						if(resultat.getString("etat").toString().equals("valide")) {
							table.setModel(DbUtils.resultSetToTableModel(resultat));
							break;
						}
						else if(resultat.getString("etat").toString().equals("non valide")) {
							table_1.setModel(DbUtils.resultSetToTableModel(resultat));
							break;
						}
						else if(resultat.getString("etat").toString().equals("annulée")) {
							table_2.setModel(DbUtils.resultSetToTableModel(resultat));
							break;
						}
						
						}
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					}else
						JOptionPane.showMessageDialog(null, "Saisissez le code de la réservation !");
				
			}
		});
		okbtn.setBounds(848, 105, 53, 23);
		contentPane.add(okbtn);
		
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\images (1).png"));
		btnNewButton_1.setBounds(218, 387, 60, 60);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez la réservation");
				}
				else
				{
					String code_reservation = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "delete from reservations where code_reservation = ?";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, code_reservation);
						prepared.execute();
						
						JOptionPane.showMessageDialog(null, "Réservation Supprimée");
						UpdateTable();
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\150_check_no_delete_error_remove-512.png"));
		btnNewButton_2.setBounds(301, 387, 60, 60);
		contentPane.add(btnNewButton_2);
		lblNewLabel_9.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (3) (1).png"));
		lblNewLabel_9.setBounds(10, 87, 46, 35);
		contentPane.add(lblNewLabel_9);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				 code_reservation = table.getModel().getValueAt(ligne, 0).toString();
				 String client = table_1.getModel().getValueAt(ligne, 1).toString();
				 String etat = table.getModel().getValueAt(ligne, 5).toString();
				
				 String date_reservation = table.getModel().getValueAt(ligne, 2).toString();
				 String date_depart = table.getModel().getValueAt(ligne, 3).toString();
				 String date_retour = table.getModel().getValueAt(ligne, 4).toString();
				
				codefield.setText(code_reservation);
				comboBox.setSelectedItem(etat);
				
				 Date date2=null;
					try {
						date2 = new SimpleDateFormat("dd/MM/yyyy").parse(date_reservation);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    					/////////////////////////////////////////////////////////
					dateChooser.setDate(date2);
					
					
					Date date3=null;
					try {
						date3 = new SimpleDateFormat("dd/MM/yyyy").parse(date_depart);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    					/////////////////////////////////////////////////////////
					dateChooser1.setDate(date3);
					
					Date date4=null;
					try {
						date4 = new SimpleDateFormat("dd/MM/yyyy").parse(date_retour);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    					/////////////////////////////////////////////////////////
					dateChooser2.setDate(date4);
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_19 = new JLabel("");
		lblNewLabel_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable1();
			}
		});
		contentPane.setLayout(null);
		
		lblNewLabel_19.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (2).png"));
		lblNewLabel_19.setBounds(936, 288, 38, 35);
		contentPane.add(lblNewLabel_19);
		
		JLabel lblNewLabel_39 = new JLabel("");
		lblNewLabel_39.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable2();
			}
		});
		contentPane.setLayout(null);
		
		lblNewLabel_39.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (2).png"));
		lblNewLabel_39.setBounds(936, 443, 39, 35);
		contentPane.add(lblNewLabel_39);
		
		JLabel lblNewLabel_29 = new JLabel("");
		lblNewLabel_29.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable();
			}
		});
		contentPane.setLayout(null);
		
		lblNewLabel_29.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (2).png"));
		lblNewLabel_29.setBounds(937, 142, 38, 33);
		contentPane.add(lblNewLabel_29);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\Titre.jpg"));
		lblNewLabel_1.setBounds(0, -4, 984, 80);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_0 = new JLabel("");
		lblNewLabel_0.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\content-e1472587975551.jpg"));
		lblNewLabel_0.setBounds(0, -4, 984, 607);
		contentPane.add(lblNewLabel_0);
		
		
		
	}
	
	//valide
	public void UpdateTable()
	{
		String sql = "select * from reservations where etat = 'valide' order by date_reservation desc";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	//nonvalide
	public void UpdateTable1()
	{
		String sql = "select * from reservations where etat = 'non valide' order by date_reservation desc";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(resultat));
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	//annule
	public void UpdateTable2()
	{
		String sql = "select * from reservations where etat = 'annulée' order by date_reservation desc";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(resultat));
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void remplirCombobox()
	{
		String sql = "select * from clients";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while(resultat.next())
			{
				String prenom = resultat.getString("prenom").toString();
				String nom = resultat.getString("nom").toString();
				
				comboBox_1.addItem(prenom+" "+nom);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void remplirCombobox1()
	{
		String sql = "select * from véhicules";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while(resultat.next())
			{
				String matricule = resultat.getString("matricule").toString();
				
				comboBox_2.addItem(matricule);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	

}
