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

public class GestionFactures extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String n_facture;
	private JComboBox comboBox ;
	
	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	private JTextField montantfield;
	private JTextField numfield;
	private JTextField findfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionFactures frame = new GestionFactures();
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
	public GestionFactures() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cnx =  ConnexionMysql.ConnexionDb();
		
		JLabel lblNewLabel_2 = new JLabel("Montant \u00E0 payer :");
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(66, 219, 104, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Date facture :");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(92, 250, 91, 20);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("N_Facture :");
		lblNewLabel_5.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(92, 141, 78, 18);
		contentPane.add(lblNewLabel_5);
		
		numfield = new JTextField();
		numfield.setBounds(187, 134, 170, 27);
		contentPane.add(numfield);
		numfield.setColumns(10);
		
		montantfield = new JTextField();
		montantfield.setBounds(187, 212, 170, 27);
		contentPane.add(montantfield);
		montantfield.setColumns(10);
		
		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(187, 250, 170, 26);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		
		
		
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
		
		
		
		JLabel lblNewLabel_19 = new JLabel("");
		lblNewLabel_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable();
			}
		});
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Contrat :");
		lblNewLabel_6.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(110, 176, 60, 20);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_19.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (2).png"));
		lblNewLabel_19.setBounds(936, 82, 43, 35);
		contentPane.add(lblNewLabel_19);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String n_facture = numfield.getText().toString();
				String contrat = comboBox.getSelectedItem().toString();
				String montant = montantfield.getText().toString();
				String date_facture = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				String sql = "insert into factures (n_facture , contrat ,date_facture , montant) values  (? ,? , ? , ? ) ";
				try {
					prepared = cnx.prepareStatement(sql);
					
					prepared.setString(1, n_facture);
					prepared.setString(2, contrat);
					prepared.setString(3, montant);
					prepared.setString(4, date_facture);
					
					if(!n_facture.equals("") && !montant.equals("") ) {
					prepared.execute();
					UpdateTable();
					JOptionPane.showMessageDialog(null, "Facture Ajoutée");
					
					montantfield.setText("");
					numfield.setText("");
					}else {
						JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
					}
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		 comboBox = new JComboBox();
		comboBox.setBounds(187, 172, 170, 29);
		contentPane.add(comboBox);
		
		remplirCombobox();

		
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\07-_add-cash-bill-money-finance-512.png"));
		btnNewButton.setBounds(119, 308, 60, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
int ligne = table.getSelectedRow();
				
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez la facture");
				}
				else
				{
					String n_facture = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "update factures set n_facture = ?, contrat = ? , date_facture =? , montant = ?  where n_facture='"+n_facture+"'" ;
					try {
						prepared = cnx.prepareStatement(sql);
						
						prepared.setString(1, numfield.getText().toString() );
						prepared.setString(2, comboBox.getSelectedItem().toString());
						prepared.setString(4, montantfield.getText().toString() );
						prepared.setString(3, ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
						
						
						if(!montantfield.getText().toString().equals(""))
						{
							prepared.execute();
							JOptionPane.showMessageDialog(null, "Facture Modifiée");
							UpdateTable();
							
							montantfield.setText("");
							numfield.setText("");
							
						}
						else
							JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\49-512.png"));
		btnNewButton_1.setBounds(208, 308, 60, 60);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez la facture");
				}
				else
				{
					String n_facture = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "delete from factures where n_facture = ?";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, n_facture);
						prepared.execute();
						
						JOptionPane.showMessageDialog(null, "Facture Supprimée");
						UpdateTable();
						
						numfield.setText("");
						montantfield.setText("");
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
		JLabel lblNewLabel_7 = new JLabel("Rechercher par N_Facture :");
		lblNewLabel_7.setBounds(507, 90, 163, 14);
		contentPane.add(lblNewLabel_7);
		
		findfield = new JTextField();
		findfield.setBounds(671, 87, 136, 20);
		contentPane.add(findfield);
		findfield.setColumns(10);
		
		JButton okbtn = new JButton("OK");
		okbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String code = findfield.getText().toString();
				if(!code.equals("")) {
				FindTable(code);
				}else
					JOptionPane.showMessageDialog(null, "Saisissez le code de la facture !");
				
			}
		});
		okbtn.setBounds(821, 87, 52, 23);
		contentPane.add(okbtn);
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\delete-invoice-1853732-1572349.png"));
		btnNewButton_2.setBounds(297, 308, 60, 60);
		contentPane.add(btnNewButton_2);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(507, 120, 467, 330);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				 n_facture = table.getModel().getValueAt(ligne, 0).toString();
				 String contrat = table.getModel().getValueAt(ligne, 1).toString();
				String montant = table.getModel().getValueAt(ligne, 3).toString();
				String date_facture = table.getModel().getValueAt(ligne, 2).toString();
				
				numfield.setText(n_facture);
				comboBox.setSelectedItem(contrat);
				montantfield.setText(montant);			
				
				Date date2=null;
				try {
					date2 = new SimpleDateFormat("dd/MM/yyyy").parse(date_facture);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    					/////////////////////////////////////////////////////////
				dateChooser.setDate(date2);
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		
		
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
		String sql = "select * from factures order by date_facture desc";
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
		String sql = "select * from contrats";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while(resultat.next())
			{
				String contrat = resultat.getString("n_contrat").toString();
				
				comboBox.addItem(contrat);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void FindTable(String code)
	{
		String sql = "select * from factures where n_facture='"+code+"'";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
