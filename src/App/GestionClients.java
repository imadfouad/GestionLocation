package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class GestionClients extends JFrame {

	private JPanel contentPane;
	private JTextField nomfield;
	private JTable table;
	private String s;
	
	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	private JTextField numfield;
	private JTextField addfield;
	private JTextField prenomfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionClients frame = new GestionClients();
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
	public GestionClients() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String prenom = prenomfield.getText().toString();
				String nom = nomfield.getText().toString();
				String num = numfield.getText().toString();
				String add = addfield.getText().toString();
				
				
				String sql = "Insert into clients (prenom,nom,adresse,telephone,image) values (?,?,?,?,?)";
				if(!nom.equals("") && !prenom.equals("") && !num.equals("") && !add.equals(""))
				{
					try {
						InputStream img = new FileInputStream(new File(s));
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, prenom);
						prepared.setString(2, nom);
						prepared.setString(3, add);
						prepared.setString(4, num);
						prepared.setBlob(5, img);
						
						prepared.execute();
						
						JOptionPane.showMessageDialog(null, "Client Enregistré ! ");
						
						prenomfield.setText("");
						nomfield.setText("");
						numfield.setText("");
						addfield.setText("");
						
						UpdateTable();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}else
					JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs !");
				
				
				
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\t\u00E9l\u00E9chargement (3).jpg"));
		btnNewButton.setBounds(101, 390, 60, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = table.getSelectedRow();
				String id = table.getModel().getValueAt(ligne,0).toString();
				
				String prenom = prenomfield.getText().toString();
				String nom = nomfield.getText().toString();
				String num = numfield.getText().toString();
				String add = addfield.getText().toString();
				
				String sql = "Update clients Set prenom=? , nom=? , adresse = ? , telephone=? , image= ? where code_client = '"+id+"'";
				if(!nom.equals("") && !prenom.equals("") && !num.equals("") && !add.equals(""))
				{
					try {
						FileInputStream in = new FileInputStream(new File(s));
						prepared=cnx.prepareStatement(sql);
						
						prepared.setString(1, prenom);
						prepared.setString(2, nom);
						prepared.setString(3, num);
						prepared.setString(4, add);
						prepared.setBlob(5, in);
						
						prepared.execute();
						
						JOptionPane.showMessageDialog(null, "Client Modifié ! ");
						
						prenomfield.setText("");
						nomfield.setText("");
						numfield.setText("");
						addfield.setText("");
						
						UpdateTable();
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}catch (FileNotFoundException e2) {
						e2.printStackTrace();
					}
					
				}else
					JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs !");
				
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\edit_male_user_256.png"));
		btnNewButton_1.setBounds(174, 389, 60, 61);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
					JOptionPane.showMessageDialog(null, "Veuillez choisir le client");
				else
				{
					String id = table.getModel().getValueAt(ligne,0).toString();
					String sql = "Delete from clients where code_client = '"+id+"' ";
					try {
						prepared= cnx.prepareStatement(sql);
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Client Supprimé !");
						UpdateTable();
						prenomfield.setText("");
						nomfield.setText("");
						numfield.setText("");
						addfield.setText("");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\icono-de-eliminar-png-5.png"));
		btnNewButton_2.setBounds(244, 390, 60, 60);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("Nom :");
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(46, 226, 77, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_20 = new JLabel("");
		lblNewLabel_20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MenuAdministrateur obj = new MenuAdministrateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
				
			}
		});
		
		JLabel lblNewLabel_8 = new JLabel("Prenom :");
		lblNewLabel_8.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(46, 165, 77, 20);
		contentPane.add(lblNewLabel_8);
		lblNewLabel_20.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\output-onlinepngtools (3) (1).png"));
		lblNewLabel_20.setBounds(10, 87, 46, 35);
		contentPane.add(lblNewLabel_20);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable();	
				
			}
		});
		
		JLabel lblNewLabel_6 = new JLabel("Num Tel :");
		lblNewLabel_6.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(46, 287, 77, 20);
		contentPane.add(lblNewLabel_6);
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\output-onlinepngtools (2).png"));
		lblNewLabel_4.setBounds(938, 82, 46, 35);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Liste des Clients :");
		lblNewLabel_5.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(486, 91, 94, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("Adresse :");
		lblNewLabel_7.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(46, 338, 77, 20);
		contentPane.add(lblNewLabel_7);
		
		JPanel panel = new JPanel();
		panel.setBounds(295, 160, 113, 107);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 1));
		
		JLabel labimage = new JLabel("");
		panel.add(labimage);
		
		JButton btnparcourir = new JButton("Parcourir");
		btnparcourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE", "jpg" , "png","gif");
				fileChooser.addChoosableFileFilter(filter);
				int result = fileChooser.showSaveDialog(null);     //=0 si kolchi mzyan
				
				if(result == JFileChooser.APPROVE_OPTION)
				{
					File selectedfile = fileChooser.getSelectedFile();
					String path = selectedfile.getAbsolutePath();
					ImageIcon myImage = new ImageIcon(path);
					java.awt.Image img = myImage.getImage();
					java.awt.Image newImage = img.getScaledInstance(labimage.getWidth(), labimage.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon finalimg = new ImageIcon(newImage);
					labimage.setIcon(finalimg);
					s=path;
				}
				else
				{
					if(result == JFileChooser.CANCEL_OPTION)
						JOptionPane.showMessageDialog(null, "Choisissez l'image");
				}
				
			}
		});
		btnparcourir.setBounds(295, 280, 113, 26);
		contentPane.add(btnparcourir);
		
		nomfield = new JTextField();
		nomfield.setBounds(133, 219, 146, 29);
		contentPane.add(nomfield);
		nomfield.setColumns(10);
		
		numfield = new JTextField();
		numfield.setBounds(133, 278, 146, 29);
		contentPane.add(numfield);
		numfield.setColumns(10);
		
		prenomfield = new JTextField();
		prenomfield.setBounds(133, 160, 146, 29);
		contentPane.add(prenomfield);
		prenomfield.setColumns(10);
		
		addfield = new JTextField();
		addfield.setBounds(133, 331, 275, 29);
		contentPane.add(addfield);
		addfield.setColumns(10);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(486, 118, 488, 332);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
int ligne = table.getSelectedRow();
				
				String id = table.getModel().getValueAt(ligne,0).toString();
				
				String sql = "select * from clients where code_client = '"+id+"'";
				
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					if(resultat.next())
					{
						prenomfield.setText(resultat.getString("prenom"));
						nomfield.setText(resultat.getString("nom"));
						numfield.setText(resultat.getString("telephone"));
						addfield.setText(resultat.getString("adresse"));
						
						byte[] img = resultat.getBytes("image");
						ImageIcon image = new ImageIcon(img);
						java.awt.Image im = image.getImage();
						java.awt.Image myimage = im.getScaledInstance(labimage.getWidth(), labimage.getHeight() , java.awt.Image.SCALE_SMOOTH);
						ImageIcon imgg = new ImageIcon(myimage);
						labimage.setIcon(imgg);
						
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\Titre.jpg"));
		lblNewLabel_1.setBounds(0, 0, 984, 80);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion location\\content-e1472587975551.jpg"));
		lblNewLabel.setBounds(0, 0, 984, 461);
		contentPane.add(lblNewLabel);
		
		cnx =  ConnexionMysql.ConnexionDb();
	}
	
	public void UpdateTable() {
		String sql = "select * from clients order by prenom";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			
			
		} catch (SQLException z) {
			
			z.printStackTrace();
		}
	}
}
