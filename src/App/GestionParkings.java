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

import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionParkings extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String n_parking;

	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	private JTextField nomfield;
	private JTextField capfield;
	private JTextField ruefield;
	private JTextField arrfield;
	private JTextField findfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionParkings frame = new GestionParkings();
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
	public GestionParkings() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
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
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Nom parking :");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(90, 150, 94, 18);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Capacit\u00E9 :");
		lblNewLabel_5.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(118, 192, 69, 18);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Rue :");
		lblNewLabel_6.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(143, 231, 46, 18);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Arrondissement :");
		lblNewLabel_7.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(80, 265, 109, 18);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel = new JLabel("Rechercher les v\u00E9hicules\r\n du");
		lblNewLabel.setBounds(566, 82, 171, 18);
		contentPane.add(lblNewLabel);
		
		findfield = new JTextField();
		findfield.setBounds(761, 87, 94, 20);
		contentPane.add(findfield);
		findfield.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("OK");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String code = findfield.getText().toString();
				if(!code.equals("")) {
				FindTable(code);
				}else
					JOptionPane.showMessageDialog(null, "Saisissez le Nom du Parking !");
				
			}
		});
		btnNewButton_3.setBounds(861, 87, 53, 23);
		contentPane.add(btnNewButton_3);
		
		nomfield = new JTextField();
		nomfield.setBounds(197, 146, 156, 26);
		contentPane.add(nomfield);
		nomfield.setColumns(10);
		
		capfield = new JTextField();
		capfield.setBounds(197, 187, 156, 26);
		contentPane.add(capfield);
		capfield.setColumns(10);
		
		ruefield = new JTextField();
		ruefield.setBounds(197, 224, 156, 26);
		contentPane.add(ruefield);
		ruefield.setColumns(10);
		
		arrfield = new JTextField();
		arrfield.setBounds(198, 262, 155, 26);
		contentPane.add(arrfield);
		arrfield.setColumns(10);
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
		contentPane.setLayout(null);
		
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (2).png"));
		lblNewLabel_2.setBounds(936, 82, 43, 35);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez le parking");
				}
				else
				{
					String n_parking = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "delete from parkings where n_parking= ?";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, n_parking);
						prepared.execute();
						
						JOptionPane.showMessageDialog(null, "Parking Supprimé");
						UpdateTable();
						
						nomfield.setText("");
						capfield.setText("");
						ruefield.setText("");
						arrfield.setText("");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
		JLabel lblNewLabel_8 = new JLabel(" parking par nom du parking :");
		lblNewLabel_8.setBounds(570, 98, 167, 14);
		contentPane.add(lblNewLabel_8);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\_VL87yOl_400x400.png"));
		btnNewButton.setBounds(293, 332, 60, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom_park = nomfield.getText().toString();
				String capacite = capfield.getText().toString();
				String rue = ruefield.getText().toString();
				String arrondissement = arrfield.getText().toString();
				
				String sql = "insert into parkings (nom_parking , capacite , rue , arrondissement) values  (? , ? , ? , ?) ";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, nom_park);
					prepared.setString(2, capacite);
					prepared.setString(3, rue);
					prepared.setString(4, arrondissement);
					prepared.execute();
					UpdateTable();
					JOptionPane.showMessageDialog(null, "Parking Ajouté");
					
					nomfield.setText("");
					capfield.setText("");
					ruefield.setText("");
					arrfield.setText("");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\traffic-sign-bstandig-schwechat-paul-bstandig-gesmbh-parking-parking-icon-png-clip-art-thumbnail.png"));
		btnNewButton_1.setBounds(117, 333, 60, 60);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez le parking");
				}
				else
				{
					String n_parking = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "update parkings set nom_parking= ? , capacite=? , rue=? , arrondissement=? where n_parking='"+n_parking+"'" ;
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, nomfield.getText().toString());
						prepared.setString(2, capfield.getText().toString());
						prepared.setString(3, ruefield.getText().toString());
						prepared.setString(4, arrfield.getText().toString());

						if(!nomfield.getText().toString().equals("") && !capfield.getText().toString().equals("") && !ruefield.getText().toString().equals("") && !arrfield.getText().toString().equals(""))
						{
							prepared.execute();
							JOptionPane.showMessageDialog(null, "Parking Modifié");
							UpdateTable();
							
							nomfield.setText("");
							capfield.setText("");
							ruefield.setText("");
							arrfield.setText("");
						}
						else
							JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\769-7694729_parking-parking-icon-yellow.png"));
		btnNewButton_2.setBounds(206, 332, 60, 60);
		contentPane.add(btnNewButton_2);
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(507, 120, 467, 330);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				 n_parking = table.getModel().getValueAt(ligne, 0).toString();
				String nom_parking = table.getModel().getValueAt(ligne, 1).toString();
				String capacite = table.getModel().getValueAt(ligne, 2).toString();
				String rue = table.getModel().getValueAt(ligne, 3).toString();
				String arrondissement = table.getModel().getValueAt(ligne, 4).toString();
				
				
				nomfield.setText(nom_parking);
				capfield.setText(capacite);
				ruefield.setText(rue);
				arrfield.setText(arrondissement);
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\Titre.jpg"));
		lblNewLabel_1.setBounds(0, 0, 984, 80);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_0 = new JLabel("");
		lblNewLabel_0.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\content-e1472587975551.jpg"));
		lblNewLabel_0.setBounds(0, 0, 979, 456);
		contentPane.add(lblNewLabel_0);
		
		
		cnx =  ConnexionMysql.ConnexionDb();
		
	}
	
	public void UpdateTable()
	{
		String sql = "select * from parkings";
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
		String sql = "select * from véhicules where parking='"+code+"'";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
