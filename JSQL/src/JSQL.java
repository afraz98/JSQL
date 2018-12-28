import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class JSQL extends JFrame {

	private JPanel contentPane;
	private JTextField ipText;
	private JTextField portText;
	private JTextField databaseText;
	private JTextField usernameText;
	private JPasswordField passwordText;

	public JSQL() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ipText = new JTextField();
		ipText.setBounds(120, 97, 174, 22);
		contentPane.add(ipText);
		ipText.setColumns(10);
		
		portText = new JTextField();
		portText.setColumns(10);
		portText.setBounds(120, 132, 174, 22);
		contentPane.add(portText);
		
		databaseText = new JTextField();
		databaseText.setColumns(10);
		databaseText.setBounds(120, 167, 174, 22);
		contentPane.add(databaseText);
		
		usernameText = new JTextField();
		usernameText.setColumns(10);
		usernameText.setBounds(120, 310, 174, 22);
		contentPane.add(usernameText);
		
		JLabel lblIP = new JLabel("IP:");
		lblIP.setBounds(33, 100, 56, 16);
		contentPane.add(lblIP);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(33, 135, 56, 16);
		contentPane.add(lblPort);
		
		JLabel lblDatabase = new JLabel("Database:");
		lblDatabase.setBounds(33, 170, 75, 16);
		contentPane.add(lblDatabase);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(33, 313, 75, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(33, 348, 75, 16);
		contentPane.add(lblPassword);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(120, 345, 174, 22);
		contentPane.add(passwordText);
		
		ipText.setText("localhost");
		portText.setText("3306");
		usernameText.setText("root");
		databaseText.setText("dnd3e");
		
		JButton btnStartQuery = new JButton("Connect");
		btnStartQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connect();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnStartQuery.setBounds(120, 380, 174, 25);
		contentPane.add(btnStartQuery);
		setVisible(true);
	}
	
	public void connect() throws Exception {
	    String user, password, ip, port, database, URL; 
		ip = ipText.getText();
		port = portText.getText();
		database = databaseText.getText();
		user = usernameText.getText();
		password = String.valueOf(passwordText.getPassword());
		
		URL = "jdbc:mysql://" + ip + ":" + port + "/" + database;
		sqlDriver x = new sqlDriver(URL, user, password);
		x.connectSQL();
		setVisible(false);
		new JSQLQuery(x); 
	}
	public static void main(String[] args) throws Exception {
		new JSQL();
	}
}

