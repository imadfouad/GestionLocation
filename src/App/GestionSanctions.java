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
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class GestionSanctions extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String n_sanction;
	private JComboBox comboBox ;
	
	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	private JTextField montantfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionSanctions frame = new GestionSanctions();
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
	public GestionSanctions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		cnx =  ConnexionMysql.ConnexionDb();
		
		
		JLabel lblNewLabel_19 = new JLabel("");
		lblNewLabel_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable();
			}
		});
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		lblNewLabel_19.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (2).png"));
		lblNewLabel_19.setBounds(936, 82, 43, 35);
		contentPane.add(lblNewLabel_19);
		
		
		
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
		
		JLabel lblNewLabel_2 = new JLabel("Montant :");
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(122, 214, 74, 18);
		contentPane.add(lblNewLabel_2);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String contrat = comboBox.getSelectedItem().toString();
				String montant = montantfield.getText().toString();
				
				String sql = "insert into sanctions (contrat , montant) values  (? , ? ) ";
				try {
					prepared = cnx.prepareStatement(sql);
					
					prepared.setString(1, contrat);
					prepared.setString(2, montant);
					
					if(!montant.equals("")) {
						prepared.execute();
						UpdateTable();
						JOptionPane.showMessageDialog(null, "Sanction Ajoutée");
						
						montantfield.setText("");
					}else
						JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("Contrat :");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(122, 176, 57, 18);
		contentPane.add(lblNewLabel_4);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(195, 171, 151, 22);
		contentPane.add(comboBox);
		
		remplirCombobox();
		
		button.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\add-circle-green-512.png"));
		button.setBounds(119, 328, 60, 60);
		contentPane.add(button);
		
		montantfield = new JTextField();
		montantfield.setBounds(195, 207, 151, 26);
		contentPane.add(montantfield);
		montantfield.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez la sanction");
				}
				else
				{
					String n_sanction = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "update sanctions set contrat = ? ,  montant = ?  where n_sanction='"+n_sanction+"'" ;
					try {
						prepared = cnx.prepareStatement(sql);
						
						prepared.setString(1, comboBox.getSelectedItem().toString() );
						prepared.setString(2, montantfield.getText().toString() );
						
						
						if(!montantfield.getText().toString().equals(""))
						{
							prepared.execute();
							JOptionPane.showMessageDialog(null, "Sanction Modifiée");
							UpdateTable();
							
							montantfield.setText("");
							
						}
						else
							JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\images (1).png"));
		btnNewButton.setBounds(207, 328, 60, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez la sanction");
				}
				else
				{
					String n_sanction = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "delete from sanctions where n_sanction = ?";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, n_sanction);
						prepared.execute();
						
						JOptionPane.showMessageDialog(null, "Sanction Supprimée");
						UpdateTable();
						
						montantfield.setText("");
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\150_check_no_delete_error_remove-512.png"));
		btnNewButton_1.setBounds(297, 328, 60, 60);
		contentPane.add(btnNewButton_1);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(507, 120, 467, 330);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				 n_sanction = table.getModel().getValueAt(ligne, 0).toString();
				 String contrat = table.getModel().getValueAt(ligne, 1).toString();
				String montant = table.getModel().getValueAt(ligne, 2).toString();
				
				comboBox.setSelectedItem(contrat);
				montantfield.setText(montant);			
					
			}
		});
		scrollPane.setViewportView(table);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\Titre.jpg"));
		lblNewLabel_1.setBounds(0, 0, 984, 80);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\content-e1472587975551.jpg"));
		lblNewLabel.setBounds(0, 0, 979, 456);
		contentPane.add(lblNewLabel);
		
		
	}
	
	
	public void UpdateTable()
	{
		String sql = "select * from sanctions";
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
				String parking = resultat.getString("n_contrat").toString();
				
				comboBox.addItem(parking);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
