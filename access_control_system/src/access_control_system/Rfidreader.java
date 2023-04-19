package access_control_system;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import javax.swing.SwingConstants;

public class Rfidreader extends JFrame {

	private JPanel contentPane;
	private JTextField textRfid;
	private JLabel lblAnswer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rfidreader frame = new Rfidreader();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Rfidreader() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAnswer = new JLabel("");
		lblAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnswer.setBounds(10, 97, 264, 53);
		contentPane.add(lblAnswer);
		
		textRfid = new JTextField();
		textRfid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					String text = textRfid.getText();
				    //JOptionPane.showMessageDialog(null , text);
					
					
				    System.out.println(text + " is trying to enter...");
				    try {
						String check = "select name, validity, role from guests where rfid = ?";
			            Connection connection = 
			            		(Connection) DriverManager.getConnection("jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			            				"root", "1234");
			            PreparedStatement st = (PreparedStatement) connection.prepareStatement(check);
			            st.setString(1, text);
			            ResultSet rs = st.executeQuery();
			            if (rs.next()) {
			            	String name = rs.getString(1);
			            	String validity = rs.getString(2);
			            	String role = rs.getString(3);
			            	
			            	if (enterCheck(name, validity)) {
			            		
			            		if(inOpeningHours(role, lblAnswer)) {
			            			lblAnswer.setText("Welcome " + name);
			            			//JOptionPane.showMessageDialog(contentPane, "Welcome " + name);
				            		sendEnterTimeToDB();
			            		}
			            		textRfid.setText("");
			            		
			            	} else {
			            		lblAnswer.setText(name + " pass has expired at " + validity);
			            		//JOptionPane.showMessageDialog(contentPane, name + " pass has expired at " + validity);
			            		//System.out.println(name + " pass has expired at " + validity);
			            		textRfid.setText("");
			            	}
			            } else {
			            	lblAnswer.setText("DB doesn't contain this RFID: " + textRfid.getText());
			            	//JOptionPane.showMessageDialog(contentPane, "Database doesn't contain this RFID: " + textRfid.getText());
			            	//System.out.println("Database doesn't contain this RFID: " + textRfid.getText());
			            	//JOptionPane.showMessageDialog(contentPane, "Database doesn't contain this RFID: " + textField.getText());
			            	textRfid.setText("");
			            }
			        } catch (SQLException sqlException) {
			            sqlException.printStackTrace();
			            lblAnswer.setText("Database exception! Pls check the database connection!");
			            //JOptionPane.showMessageDialog(contentPane, "Database exception! Pls check the database connection!");
			        } catch (Exception f) {
			        	System.out.println(f);
			        }
				   }
			}
		});
		textRfid.setBounds(76, 66, 115, 20);
		contentPane.add(textRfid);
		textRfid.setColumns(10);

		
		JLabel lblScan = new JLabel("Scan your RFID:");
		lblScan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblScan.setBounds(76, 23, 138, 41);
		contentPane.add(lblScan);
		
		
	}
	
	public boolean inOpeningHours(String role, JLabel lblAnswer) {
		try {
			
			LocalDateTime time = LocalDateTime.now();
			Date localdate = new Date();
			//getDay = 0=sunday, 1=monday, 2=tuesday... 6=saturday
			int localday = localdate.getDay();
			int localhour = time.getHour();
			int localminute = time.getMinute();
			
			Connection connection = 
	        		(Connection) DriverManager.getConnection("jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	        				"root", "1234");
			String select = "select from_value, to_value from openinghours where day = ? and role = ?";
			PreparedStatement st = (PreparedStatement) connection.prepareStatement(select);
			System.out.println(localday);
			if (localday == 0) {
				st.setString(1, "sunday");
			} else if (localday == 1) {
				st.setString(1, "monday");
			} else if (localday == 2) {
				st.setString(1, "tuesday");
			} else if (localday == 3) {
				st.setString(1, "wednesday");
			} else if (localday == 4) {
				st.setString(1, "thursday");
			} else if (localday == 5) {
				st.setString(1, "friday");
			} else { 
				// local day == 6
				st.setString(1, "saturday");
			}
			st.setString(2, role);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
            	String from = rs.getString(1);
            	String to = rs.getString(2);
            	String hourFrom = from.substring(0, 2);
				String minuteFrom = from.substring(3, 5);
				String hourTo = to.substring(0, 2);
				String minuteTo = to.substring(3, 5);
				int hourF = Integer.parseInt(hourFrom);
				int minuteF = Integer.parseInt(minuteFrom);
				int hourT = Integer.parseInt(hourTo);
				int minuteT = Integer.parseInt(minuteTo);
				if((localhour > hourF && localhour < hourT) || (hourF != hourT && localhour == hourF && localminute > minuteF) || (hourF != hourT && localhour == hourT && localminute < minuteT) || hourT == hourF && localminute > minuteF && localminute < minuteT) {
					return true;
				} else {
					//JOptionPane.showMessageDialog(contentPane, "The gym is closed! The opening hours are then: " + hourFrom + ":" + minuteFrom + "-" + hourTo + ":" + minuteTo);
					lblAnswer.setText("<html>The gym is closed! <br> The opening hours are then: " + hourFrom + ":" + minuteFrom + "-" + hourTo + ":" + minuteTo + "<html>");
					//System.out.println("The gym is closed! The opening hours are then: " + hourFrom + ":" + minuteFrom + "-" + hourTo + ":" + minuteTo);
					return false;
				}
            }
		
		} catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(contentPane, "Database exception! Pls check the database connection!");
        } catch (Exception f) {
        	System.out.println(f);
        }
		return false;
	}
	public void sendEnterTimeToDB() {
		try {
			
    		Date date = new Date();
    		/*
    		String actual_date = date.toString();
        	int actual_day = date.getDate();
        	int actual_month = date.getMonth();
        	int acutal_year = Integer.parseInt(actual_date.substring(25, 29));
        	actual_month++;
        	*/
        	
    		int actual_hour = date.getHours();
        	int count = 0;
        	
        	String select = "select count from mass_distribution where enter_time = ?";
			String update = "update mass_distribution set count = ? where enter_time = ?";
	        Connection connection = 
	        		(Connection) DriverManager.getConnection("jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	        				"root", "1234");
	        
	        PreparedStatement sts = (PreparedStatement) connection.prepareStatement(select);
	        sts.setInt(1, actual_hour);
	        ResultSet rs = sts.executeQuery();
	        while(rs.next()) {
	        	count = rs.getInt(1);
	        }
	        count++;
	        PreparedStatement sti = (PreparedStatement) connection.prepareStatement(update);
	        sti.setInt(1, count);
	        sti.setInt(2, actual_hour);
	        sti.executeUpdate();
	        /*
	        Date date = new Date();
	        int actual_hour = date.getHours();
        	int actual_min = date.getMinutes();
        	int count = 0;
        	
        	
        	String enter_time = actual_hour + ":" + actual_min;
	        String insert = "insert into mass_distribution (enter_time) values (?)";
	        Connection connection = 
	        		(Connection) DriverManager.getConnection("jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	        				"root", "1234");
	        PreparedStatement sti = (PreparedStatement) connection.prepareStatement(insert);
	        sti.setString(1, enter_time);
	        sti.executeUpdate();
	        */
		} catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(contentPane, "Database exception! Pls check the database connection!");
        } catch (Exception f) {
        	System.out.println(f);
        }
		
	}
	public boolean enterCheck(String name, String validity) {
		Date date = new Date();
		
    	String actual_date = date.toString();
    	
    	int actual_day = date.getDate();
    	int actual_month = date.getMonth();
    	actual_month++;
    	int acutal_year = date.getYear();//Integer.parseInt(actual_date.substring(24, 28));
    	acutal_year += 1900;
    	int guest_validity_year = Integer.parseInt(validity.substring(0, 4));
    	int guest_validity_month = Integer.parseInt(validity.substring(5, 7));
    	int guest_validity_day = Integer.parseInt(validity.substring(8, 10));
        if (guest_validity_year > acutal_year) {
    		return true;
    	} else if (guest_validity_year == acutal_year) {
    		if (guest_validity_month > actual_month) {
    			return true;
    		} else if (guest_validity_month == actual_month) {
    			if (guest_validity_day >= actual_day) {
    				return true;
    				} else {return false;}
    		} else {return false;}
    	} else {return false;}
	}
}
