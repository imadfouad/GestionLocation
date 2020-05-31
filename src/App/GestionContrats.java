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
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionContrats extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String n_contrat;
	private JComboBox comboBox_1;
	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	private JTextField codefield;
	private JTextField findfield;
	private JComboBox comboBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionContrats frame = new GestionContrats();
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
	public GestionContrats() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cnx =  ConnexionMysql.ConnexionDb();
		
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
		
		JLabel lblNewLabel_5 = new JLabel("Rechercher par code_contrat :");
		lblNewLabel_5.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(537, 87, 178, 22);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel("Code_Contrat :");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(87, 165, 90, 18);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (3) (1).png"));
		lblNewLabel_3.setBounds(10, 87, 46, 35);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable();
			}
		});
		contentPane.setLayout(null);
		
		lblNewLabel_9.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (2).png"));
		lblNewLabel_9.setBounds(936, 82, 43, 35);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel = new JLabel("Date du contrat :");
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel.setBounds(83, 245, 90, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Date d'\u00E9ch\u00E9ance :");
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(79, 283, 94, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_10 = new JLabel("Client :");
		lblNewLabel_10.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_10.setBounds(131, 135, 46, 19);
		contentPane.add(lblNewLabel_10);
		 
		 JLabel lblNewLabel_6 = new JLabel("V\u00E9hicule :");
		 lblNewLabel_6.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		 lblNewLabel_6.setBounds(117, 205, 60, 22);
		 contentPane.add(lblNewLabel_6);
		 
		 codefield = new JTextField();
		 codefield.setBounds(179, 164, 167, 22);
		 contentPane.add(codefield);
		 codefield.setColumns(10);
		
		 comboBox_1 = new JComboBox();
		comboBox_1.setBounds(179, 130, 167, 22);
		contentPane.add(comboBox_1);
		
		remplirCombobox();
		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(179, 236, 169, 27);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		
		JDateChooser dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(179, 274, 168, 27);
		contentPane.add(dateChooser1);
		dateChooser1.setDateFormatString("dd/MM/yyyy");
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String code_contrat = codefield.getText().toString();
				String client = comboBox_1.getSelectedItem().toString();
				String vehicule = comboBox.getSelectedItem().toString();
				String date_contrat = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				String date_echeance = ((JTextField)dateChooser1.getDateEditor().getUiComponent()).getText();
				
				String sql = "insert into contrats (n_contrat , client, véhicule , date_contrat , date_echeance) values  (?,?,?,? , ?) ";
				try {
					prepared = cnx.prepareStatement(sql);
					
					prepared.setString(1, code_contrat);
					prepared.setString(2, client);
					prepared.setString(3, vehicule);
					prepared.setString(4, date_contrat);
					prepared.setString(5, date_echeance);
					
					if(!code_contrat.equals("")) {
					prepared.execute();
					UpdateTable();
					JOptionPane.showMessageDialog(null, "Contrat Ajouté");
					}
					else
						JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		 comboBox = new JComboBox();
		comboBox.setBounds(179, 203, 167, 22);
		contentPane.add(comboBox);
		
		remplirCombobox1();
		
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\Add_document_contract_agreement_notic_file_plus-512.png"));
		btnNewButton.setBounds(135, 325, 60, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez le contrat");
				}
				else
				{
					String n_contrat = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "update contrats set n_contrat = ? , client = ? , véhicule = ? , date_contrat =? , date_echeance = ? where n_contrat='"+n_contrat+"'" ;
					try {
						prepared = cnx.prepareStatement(sql);
						
						prepared.setString(1, codefield.getText().toString());
						prepared.setString(2, comboBox_1.getSelectedItem().toString());		
						prepared.setString(3, comboBox.getSelectedItem().toString());
						prepared.setString(4, ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
						prepared.setString(5, ((JTextField)dateChooser1.getDateEditor().getUiComponent()).getText());
						
						
						if(!codefield.getText().toString().equals("")) {
							prepared.execute();
							JOptionPane.showMessageDialog(null, "Contrat Modifié");
							UpdateTable();
						}else {
							JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
						}
						
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\unnamed (2).png"));
		btnNewButton_1.setBounds(218, 325, 60, 60);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez le contrat");
				}
				else
				{
					String n_contrat = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "delete from contrats where n_contrat = ?";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, n_contrat);
						prepared.execute();
						
						JOptionPane.showMessageDialog(null, "Contrat Supprimé");
						UpdateTable();
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\istockphoto-686103702-1024x1024.jpg"));
		btnNewButton_2.setBounds(301, 325, 60, 60);
		contentPane.add(btnNewButton_2);
		
		JButton btnok = new JButton("OK");
		btnok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String code = findfield.getText().toString();
				if(!code.equals("")) {
				FindTable(code);
				}else
					JOptionPane.showMessageDialog(null, "Saisissez le code du contrat !");
				
				
			}
		});
		btnok.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnok.setBounds(831, 87, 54, 23);
		contentPane.add(btnok);
		
		findfield = new JTextField();
		findfield.setBounds(708, 87, 113, 20);
		contentPane.add(findfield);
		findfield.setColumns(10);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(507, 120, 467, 330);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				 String code_contrat = table.getModel().getValueAt(ligne, 0).toString();
				 String client = table.getModel().getValueAt(ligne, 1).toString();
				 String vehicule = table.getModel().getValueAt(ligne, 2).toString();
				 String date_contrat = table.getModel().getValueAt(ligne, 3).toString();
				 String date_echeance = table.getModel().getValueAt(ligne, 4).toString();
				
				
				 Date date2=null;
					try {
						date2 = new SimpleDateFormat("dd/MM/yyyy").parse(date_contrat);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    					/////////////////////////////////////////////////////////
					dateChooser.setDate(date2);
					
					
					Date date3=null;
					try {
						date3 = new SimpleDateFormat("dd/MM/yyyy").parse(date_echeance);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    					/////////////////////////////////////////////////////////
					dateChooser1.setDate(date3);
					comboBox.setSelectedItem(vehicule);
					codefield.setText(code_contrat);
					comboBox_1.setSelectedItem(client);
			}
		});
		scrollPane.setViewportView(table);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\Titre.jpg"));
		lblNewLabel_1.setBounds(0, 0, 984, 80);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_0 = new JLabel("");
		lblNewLabel_0.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\content-e1472587975551.jpg"));
		lblNewLabel_0.setBounds(5, 5, 974, 451);
		contentPane.add(lblNewLabel_0);
		
		
		
	}
	
	
	
	
	public void UpdateTable()
	{
		String sql = "select * from contrats";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void FindTable(String code)
	{
		String sql = "select * from contrats where n_contrat='"+code+"'";
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
				
				comboBox.addItem(matricule);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}


}
