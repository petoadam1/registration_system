package access_control_system;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Passes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textDT;
	private JTextField textMP;
	private JTextField textCP;
	private JTextField textQP;
	private JTextField textHYP;
	private JTextField textYP;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			Passes dialog = new Passes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public Passes() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDailyTicket = new JLabel("Daily ticket");
		lblDailyTicket.setBounds(10, 30, 100, 14);
		contentPanel.add(lblDailyTicket);
		
		JLabel lblChainPass = new JLabel("Monthly pass");
		lblChainPass.setBounds(10, 60, 100, 14);
		contentPanel.add(lblChainPass);
		
		JLabel lblChainPass_1 = new JLabel("Chain pass");
		lblChainPass_1.setBounds(10, 90, 100, 14);
		contentPanel.add(lblChainPass_1);
		
		JLabel lblChainPass_1_1 = new JLabel("Quarterly pass");
		lblChainPass_1_1.setBounds(10, 120, 100, 14);
		contentPanel.add(lblChainPass_1_1);
		
		JLabel lblChainPass_1_1_1 = new JLabel("Half-year pass");
		lblChainPass_1_1_1.setBounds(10, 150, 100, 14);
		contentPanel.add(lblChainPass_1_1_1);
		
		JLabel lblChainPass_1_1_1_1 = new JLabel("Year pass");
		lblChainPass_1_1_1_1.setBounds(10, 180, 100, 14);
		contentPanel.add(lblChainPass_1_1_1_1);
		
		textDT = new JTextField();
		textDT.setBounds(120, 26, 86, 20);
		contentPanel.add(textDT);
		textDT.setColumns(10);
		
		textMP = new JTextField();
		textMP.setColumns(10);
		textMP.setBounds(120, 56, 86, 20);
		contentPanel.add(textMP);
		
		textCP = new JTextField();
		textCP.setColumns(10);
		textCP.setBounds(120, 86, 86, 20);
		contentPanel.add(textCP);
		
		textQP = new JTextField();
		textQP.setColumns(10);
		textQP.setBounds(120, 116, 86, 20);
		contentPanel.add(textQP);
		
		textHYP = new JTextField();
		textHYP.setColumns(10);
		textHYP.setBounds(120, 146, 86, 20);
		contentPanel.add(textHYP);
		
		textYP = new JTextField();
		textYP.setColumns(10);
		textYP.setBounds(120, 176, 86, 20);
		contentPanel.add(textYP);
		
		JLabel lblAPDT = new JLabel("");
		lblAPDT.setBounds(230, 30, 77, 14);
		contentPanel.add(lblAPDT);
		
		JLabel lblAPMP = new JLabel("");
		lblAPMP.setBounds(230, 60, 77, 14);
		contentPanel.add(lblAPMP);
		
		JLabel lblAPCP = new JLabel("");
		lblAPCP.setBounds(230, 90, 77, 14);
		contentPanel.add(lblAPCP);
		
		JLabel lblAPQP = new JLabel("");
		lblAPQP.setBounds(230, 120, 77, 14);
		contentPanel.add(lblAPQP);
		
		JLabel lblAPHYP = new JLabel("");
		lblAPHYP.setBounds(230, 150, 77, 14);
		contentPanel.add(lblAPHYP);
		
		JLabel lblAPYP = new JLabel("");
		lblAPYP.setBounds(230, 180, 77, 14);
		contentPanel.add(lblAPYP);
		
		JLabel lblNewLabel = new JLabel("New price");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(120, 1, 99, 22);
		contentPanel.add(lblNewLabel);
		
		getActualPrices(lblAPDT, lblAPMP, lblAPCP, lblAPQP, lblAPHYP, lblAPYP);
		
		JLabel lblActualPrice = new JLabel("Actual price");
		lblActualPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblActualPrice.setBounds(230, 1, 99, 22);
		contentPanel.add(lblActualPrice);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Update");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String passes[][] = new String[6][3];
						if (!textDT.getText().equals("")) {
							passes[0][0] = "daily_ticket";
							passes[0][1] = textDT.getText();
						}
						if (!textMP.getText().equals("")) {
							passes[1][0] = "monthly_pass";
							passes[1][1] = textMP.getText();
						}
						if (!textCP.getText().equals("")) {
							passes[2][0] = "chain_pass";
							passes[2][1] = textCP.getText();
						}
						if (!textQP.getText().equals("")) {
							passes[3][0] = "quarterly_pass";
							passes[3][1] = textQP.getText();
						}
						if (!textHYP.getText().equals("")) {
							passes[4][0] = "halfyear_pass";
							passes[4][1] = textHYP.getText();
						}
						if (!textYP.getText().equals("")) {
							passes[5][0] = "year_pass";
							passes[5][1] = textYP.getText();
						}
						//updatePrice("year_pass", textYP.getText());
						
						boolean check = false;
						boolean noUpdate = false;
						boolean updateAllowed = false;
						for(int i = 0; i < 6; i++) {
							if (passes[i][0] != null) {
								check = true;
								
								if(isNumeric(passes[i][1])) {
									//System.out.println("numeric" + Integer.parseInt(passes[i][1]) );
									if(Integer.parseInt(passes[i][1]) > 0 && Integer.parseInt(passes[i][1]) < 999999) {
										
										passes[i][2] = "good";
									} else {
										JOptionPane.showMessageDialog(contentPanel, "The price can be between 0-999999!");
										noUpdate = true;
										System.out.println(noUpdate);
									}
								} else {
									JOptionPane.showMessageDialog(contentPanel, passes[i][0] + " not numeric!");
									noUpdate = true;
									System.out.println(noUpdate);
								}
							}
						}
						
						if (check == true) {
							if (noUpdate == false) {
								for (int j = 0; j < 6; j++) {
									if (passes[j][0] != null) {
										if (passes[j][2] != null) {
											updatePrice(passes[j][0], Integer.parseInt(passes[j][1]));
											System.out.println(noUpdate);
											updateAllowed = true;
										}
									}
								}
							}
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Fill least one whole row/line!");
						}
						if (updateAllowed == true) {
							getActualPrices(lblAPDT, lblAPMP, lblAPCP, lblAPQP, lblAPHYP, lblAPYP);
							JOptionPane.showMessageDialog(contentPanel, "Update was successfull!");
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	public void updatePrice(String pass, int price) {
		try {
			
			Connection connection = 
	        		(Connection) DriverManager.getConnection("jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	        				"root", "1234");
			
			
			String update = "update prices set price = ? where pass = ?";
			PreparedStatement st = (PreparedStatement) connection.prepareStatement(update);
			st.setInt(1, price);
			st.setString(2, pass);
			
			st.executeUpdate();
			//JOptionPane.showMessageDialog(contentPanel, ""+ day + " was successfully modified!");
		
		} catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(contentPanel, "Database exception! Pls check the database connection!");
        } catch (Exception f) {
        	System.out.println(f);
        }
	}
	public void getActualPrices(JLabel lblAPDT, JLabel lblAPMP, JLabel lblAPCP, JLabel lblAPQP, JLabel lblAPHYP, JLabel lblAPYP ) {
		try {
			Connection connection = 
	        		(Connection) DriverManager.getConnection("jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	        				"root", "1234");
			String select = "select pass, price from prices";
			PreparedStatement st = (PreparedStatement) connection.prepareStatement(select);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
            	String pass = rs.getString(1);
            	String price = rs.getString(2);
            	if (pass.equals("daily_ticket")) {
            		lblAPDT.setText(price + " Ft");
            	} else if (pass.equals("monthly_pass")) {
            		lblAPMP.setText(price + " Ft");
            	} else if (pass.equals("chain_pass")) {
            		lblAPCP.setText(price + " Ft");
            	} else if (pass.equals("quarterly_pass")) {
            		lblAPQP.setText(price + " Ft");
            	} else if (pass.equals("halfyear_pass")) {
            		lblAPHYP.setText(price + " Ft");
            	} else if (pass.equals("year_pass")) {
            		lblAPYP.setText(price + " Ft");
            	}
            	
            }
		
		} catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(contentPanel, "Database exception! Pls check the database connection!");
        } catch (Exception f) {
        	System.out.println(f);
        }
	}
	public boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	        
	    }
	    try {
	    	Integer d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
