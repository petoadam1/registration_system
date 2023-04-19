package access_control_system;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Entrance extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Entrance entrance = new Entrance();
					entrance.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Entrance() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSignin = new JLabel("Sign In");
		lblSignin.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSignin.setBounds(166, 40, 108, 32);
		contentPane.add(lblSignin);

		userField = new JTextField();
		userField.setBounds(166, 109, 194, 32);
		contentPane.add(userField);
		userField.setColumns(10);

		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(166, 156, 194, 32);
		contentPane.add(passwordField);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(41, 112, 100, 19);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(41, 165, 100, 19);
		contentPane.add(lblPassword);

		JButton enterButton = new JButton("Enter");
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setUsername(userField.getText());
				user.setPswd(passwordField.getText());
				try {
					
					Connection connection = dbConnection();
					PreparedStatement st = (PreparedStatement) connection
							.prepareStatement("Select username, pswd from users where username=? and pswd=?");

					st.setString(1, user.getUsername());
					st.setString(2, user.getPswd());
					ResultSet rs = st.executeQuery();
					if (rs.next()) {
						dispose();
						Menu menu = new Menu();
						menu.setTitle("Menu");
						menu.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(exitButton, "Wrong username or password");
					}
				} catch (SQLException sqlException) {
					
					JOptionPane.showMessageDialog(exitButton, "Database error: " + sqlException);
					
				}
			}
		});
		enterButton.setBounds(248, 199, 112, 32);
		contentPane.add(enterButton);

		exitButton.setBounds(126, 199, 112, 32);
		contentPane.add(exitButton);
	}
	
	public Connection dbConnection() throws SQLException {
		Connection connection = (Connection) DriverManager.getConnection(
				"jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "1234");
		
		return connection;
	}
}
