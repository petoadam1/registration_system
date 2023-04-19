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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class OpeningHours extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textMondayFrom;
	private JTextField textMondayTo;
	private JTextField textTuesdayTo;
	private JTextField textTuesdayFrom;
	private JTextField textWednesdayTo;
	private JTextField textWednesdayFrom;
	private JTextField textThursdayTo;
	private JTextField textThursdayFrom;
	private JTextField textFridayTo;
	private JTextField textFridayFrom;
	private JTextField textSaturdayTo;
	private JTextField textSaturdayFrom;
	private JTextField textSundayTo;
	private JTextField textSundayFrom;
	protected JLabel lblAMoF;
	protected JLabel lblAMoT;
	protected JLabel lblATuF;
	protected JLabel lblATuT;
	protected JLabel lblAWeF;
	protected JLabel lblAWeT;
	protected JLabel lblAThF;
	protected JLabel lblAThT;
	protected JLabel lblAFrF;
	protected JLabel lblAFrT;
	protected JLabel lblASaF;
	protected JLabel lblASaT;
	protected JLabel lblASuF;
	protected JLabel lblASuT;
	protected JComboBox comboRoles;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
			OpeningHours dialog = new OpeningHours();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Create the dialog.
	 */
	public OpeningHours() {
		setBounds(100, 100, 450, 300);
		Roles roles = new Roles();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblMonday = new JLabel("Monday:");
		lblMonday.setBounds(10, 28, 73, 14);
		contentPanel.add(lblMonday);
		
		JLabel lblTuesday = new JLabel("Tuesday:");
		lblTuesday.setBounds(10, 56, 73, 14);
		contentPanel.add(lblTuesday);
		
		JLabel lblWednesday = new JLabel("Wednesday:");
		lblWednesday.setBounds(10, 84, 73, 14);
		contentPanel.add(lblWednesday);
		
		JLabel lblThursday = new JLabel("Thursday:");
		lblThursday.setBounds(10, 112, 73, 14);
		contentPanel.add(lblThursday);
		
		JLabel lblFriday = new JLabel("Friday:");
		lblFriday.setBounds(10, 140, 73, 14);
		contentPanel.add(lblFriday);
		
		JLabel lblSaturday = new JLabel("Saturday:");
		lblSaturday.setBounds(10, 168, 73, 14);
		contentPanel.add(lblSaturday);
		
		JLabel lblSunday = new JLabel("Sunday:");
		lblSunday.setBounds(10, 196, 73, 14);
		contentPanel.add(lblSunday);
		
		textMondayFrom = new JTextField();
		textMondayFrom.setBounds(118, 28, 73, 20);
		contentPanel.add(textMondayFrom);
		textMondayFrom.setColumns(10);
		
		JLabel lblFrom = new JLabel("from");
		lblFrom.setBounds(86, 28, 33, 14);
		contentPanel.add(lblFrom);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(199, 28, 49, 14);
		contentPanel.add(lblTo);
		
		textMondayTo = new JTextField();
		textMondayTo.setColumns(10);
		textMondayTo.setBounds(219, 28, 73, 20);
		contentPanel.add(textMondayTo);
		
		JLabel lblTo_1 = new JLabel("to");
		lblTo_1.setBounds(199, 56, 49, 14);
		contentPanel.add(lblTo_1);
		
		textTuesdayTo = new JTextField();
		textTuesdayTo.setColumns(10);
		textTuesdayTo.setBounds(219, 56, 73, 20);
		contentPanel.add(textTuesdayTo);
		
		textTuesdayFrom = new JTextField();
		textTuesdayFrom.setColumns(10);
		textTuesdayFrom.setBounds(118, 56, 73, 20);
		contentPanel.add(textTuesdayFrom);
		
		JLabel lblFrom_1 = new JLabel("from");
		lblFrom_1.setBounds(86, 56, 33, 14);
		contentPanel.add(lblFrom_1);
		
		JLabel lblTo_2 = new JLabel("to");
		lblTo_2.setBounds(199, 84, 49, 14);
		contentPanel.add(lblTo_2);
		
		textWednesdayTo = new JTextField();
		textWednesdayTo.setColumns(10);
		textWednesdayTo.setBounds(219, 84, 73, 20);
		contentPanel.add(textWednesdayTo);
		
		textWednesdayFrom = new JTextField();
		textWednesdayFrom.setColumns(10);
		textWednesdayFrom.setBounds(118, 84, 73, 20);
		contentPanel.add(textWednesdayFrom);
		
		JLabel lblFrom_2 = new JLabel("from");
		lblFrom_2.setBounds(86, 84, 33, 14);
		contentPanel.add(lblFrom_2);
		
		JLabel lblTo_3 = new JLabel("to");
		lblTo_3.setBounds(199, 112, 49, 14);
		contentPanel.add(lblTo_3);
		
		textThursdayTo = new JTextField();
		textThursdayTo.setColumns(10);
		textThursdayTo.setBounds(219, 112, 73, 20);
		contentPanel.add(textThursdayTo);
		
		textThursdayFrom = new JTextField();
		textThursdayFrom.setColumns(10);
		textThursdayFrom.setBounds(118, 112, 73, 20);
		contentPanel.add(textThursdayFrom);
		
		JLabel lblFrom_3 = new JLabel("from");
		lblFrom_3.setBounds(86, 112, 33, 14);
		contentPanel.add(lblFrom_3);
		
		JLabel lblTo_4 = new JLabel("to");
		lblTo_4.setBounds(199, 140, 49, 14);
		contentPanel.add(lblTo_4);
		
		textFridayTo = new JTextField();
		textFridayTo.setColumns(10);
		textFridayTo.setBounds(219, 140, 73, 20);
		contentPanel.add(textFridayTo);
		
		textFridayFrom = new JTextField();
		textFridayFrom.setColumns(10);
		textFridayFrom.setBounds(118, 140, 73, 20);
		contentPanel.add(textFridayFrom);
		
		JLabel lblFrom_4 = new JLabel("from");
		lblFrom_4.setBounds(86, 140, 33, 14);
		contentPanel.add(lblFrom_4);
		
		JLabel lblTo_5 = new JLabel("to");
		lblTo_5.setBounds(199, 168, 49, 14);
		contentPanel.add(lblTo_5);
		
		textSaturdayTo = new JTextField();
		textSaturdayTo.setColumns(10);
		textSaturdayTo.setBounds(219, 168, 73, 20);
		contentPanel.add(textSaturdayTo);
		
		textSaturdayFrom = new JTextField();
		textSaturdayFrom.setColumns(10);
		textSaturdayFrom.setBounds(118, 168, 73, 20);
		contentPanel.add(textSaturdayFrom);
		
		JLabel lblFrom_5 = new JLabel("from");
		lblFrom_5.setBounds(86, 168, 33, 14);
		contentPanel.add(lblFrom_5);
		
		JLabel lblTo_6 = new JLabel("to");
		lblTo_6.setBounds(199, 196, 49, 14);
		contentPanel.add(lblTo_6);
		
		textSundayTo = new JTextField();
		textSundayTo.setColumns(10);
		textSundayTo.setBounds(219, 196, 73, 20);
		contentPanel.add(textSundayTo);
		
		textSundayFrom = new JTextField();
		textSundayFrom.setColumns(10);
		textSundayFrom.setBounds(118, 196, 73, 20);
		contentPanel.add(textSundayFrom);
		
		JLabel lblFrom_6 = new JLabel("from");
		lblFrom_6.setBounds(86, 196, 33, 14);
		contentPanel.add(lblFrom_6);
		
		JLabel lblOpeningHours = new JLabel("Opening Hours");
		lblOpeningHours.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOpeningHours.setBounds(115, 0, 177, 27);
		contentPanel.add(lblOpeningHours);
		
		lblAMoF = new JLabel("17:00");
		lblAMoF.setBounds(307, 28, 33, 14);
		contentPanel.add(lblAMoF);
		
		lblAMoT = new JLabel("19:00");
		lblAMoT.setBounds(360, 28, 49, 14);
		contentPanel.add(lblAMoT);
		
		JLabel lblLine = new JLabel("-");
		lblLine.setBounds(350, 28, 20, 14);
		contentPanel.add(lblLine);
		
		JLabel lblLine_1 = new JLabel("-");
		lblLine_1.setBounds(350, 56, 20, 14);
		contentPanel.add(lblLine_1);
		
		JLabel lblLine_2 = new JLabel("-");
		lblLine_2.setBounds(350, 84, 20, 14);
		contentPanel.add(lblLine_2);
		
		JLabel lblLine_3 = new JLabel("-");
		lblLine_3.setBounds(350, 112, 20, 14);
		contentPanel.add(lblLine_3);
		
		JLabel lblLine_4 = new JLabel("-");
		lblLine_4.setBounds(350, 140, 20, 14);
		contentPanel.add(lblLine_4);
		
		JLabel lblLine_5 = new JLabel("-");
		lblLine_5.setBounds(350, 168, 20, 14);
		contentPanel.add(lblLine_5);
		
		JLabel lblLine_6 = new JLabel("-");
		lblLine_6.setBounds(350, 196, 20, 14);
		contentPanel.add(lblLine_6);
		
		lblATuF = new JLabel("17:00");
		lblATuF.setBounds(307, 56, 33, 14);
		contentPanel.add(lblATuF);
		
		lblAWeF = new JLabel("17:00");
		lblAWeF.setBounds(307, 84, 33, 14);
		contentPanel.add(lblAWeF);
		
		lblAThF = new JLabel("17:00");
		lblAThF.setBounds(307, 112, 33, 14);
		contentPanel.add(lblAThF);
		
		lblAFrF = new JLabel("17:00");
		lblAFrF.setBounds(307, 140, 33, 14);
		contentPanel.add(lblAFrF);
		
		lblASaF = new JLabel("17:00");
		lblASaF.setBounds(307, 168, 33, 14);
		contentPanel.add(lblASaF);
		
		lblASuF = new JLabel("17:00");
		lblASuF.setBounds(307, 196, 33, 14);
		contentPanel.add(lblASuF);
		
		lblATuT = new JLabel("19:00");
		lblATuT.setBounds(360, 56, 49, 14);
		contentPanel.add(lblATuT);
		
		lblAWeT = new JLabel("19:00");
		lblAWeT.setBounds(360, 84, 49, 14);
		contentPanel.add(lblAWeT);
		
		lblAThT = new JLabel("19:00");
		lblAThT.setBounds(360, 112, 49, 14);
		contentPanel.add(lblAThT);
		
		lblAFrT = new JLabel("19:00");
		lblAFrT.setBounds(360, 140, 49, 14);
		contentPanel.add(lblAFrT);
		
		lblASaT = new JLabel("19:00");
		lblASaT.setBounds(360, 168, 49, 14);
		contentPanel.add(lblASaT);
		
		lblASuT = new JLabel("19:00");
		lblASuT.setBounds(360, 196, 49, 14);
		contentPanel.add(lblASuT);
		
		
		
		JLabel lblActualOh = new JLabel("Actual OH");
		lblActualOh.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblActualOh.setBounds(321, 4, 105, 20);
		contentPanel.add(lblActualOh);
		
		comboRoles = new JComboBox();
		setActualOH("default", lblAMoF, lblAMoT, lblATuF, lblATuT, lblAWeF, lblAWeT, lblAThF, lblAThT, lblAFrF, lblAFrT, lblASaF, lblASaT, lblASuF, lblASuT);
		String[] roles2 = roles.fillComboBox();
		int countRoles = comboRoles.getItemCount();
		comboRoles.addItem("default");
		for(String str : roles2) {
			if (!str.equals("default")) {
				comboRoles.addItem(str);
			}
		}
		for(int i = 0; i < countRoles; i++) {
			comboRoles.removeItemAt(i);
		}
		
		//comboRoles.removeAllItems();
		//setActualOH(role, lblAMoF, lblAMoT, lblATuF, lblATuT, lblAWeF, lblAWeT, lblAThF, lblAThT, lblAFrF, lblAFrT, lblASaF, lblASaT, lblASuF, lblASuT);
		
		System.out.println(comboRoles.getSelectedItem().toString());
		//setComboBox("default", roles, lblAMoF, lblAMoT, lblATuF, lblATuT, lblAWeF, lblAWeT, lblAThF, lblAThT, lblAFrF, lblAFrT, lblASaF, lblASaT, lblASuF, lblASuT);
		/*
		String[] roles2 = roles.fillComboBox();
		comboRoles.removeAllItems();
		setActualOH("default", lblAMoF, lblAMoT, lblATuF, lblATuT, lblAWeF, lblAWeT, lblAThF, lblAThT, lblAFrF, lblAFrT, lblASaF, lblASaT, lblASuF, lblASuT);
		comboRoles.addItem("default");
		for(String str : roles2) {
			if (!str.equals("default")) {
				comboRoles.addItem(str);
			}
		}*/
		
		
		/*String selectedItem = comboRoles.getSelectedItem().toString();
		System.out.println(selectedItem);*/
		
		/*comboRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = comboRoles.getSelectedItem().toString();
				setActualOH(selectedItem, lblAMoF, lblAMoT, lblATuF, lblATuT, lblAWeF, lblAWeT, lblAThF, lblAThT, lblAFrF, lblAFrT, lblASaF, lblASaT, lblASuF, lblASuT);
			}
		});*/
		
		comboRoles.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
                String selectedItem = comboRoles.getSelectedItem().toString();
				setActualOH(selectedItem, lblAMoF, lblAMoT, lblATuF, lblATuT, lblAWeF, lblAWeT, lblAThF, lblAThT, lblAFrF, lblAFrT, lblASaF, lblASaT, lblASuF, lblASuT);
			}
		});
		comboRoles.setBounds(10, 5, 95, 22);
		contentPanel.add(comboRoles);
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Update");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//a day tömb első indexe mondja meg, hogy hanyadik nap, a második pedig, hogy a nap neve, a from, vagy a to érték kell belőle, vagy a 4. ami, hogy átment-e az ellenőrzésen
						String day[][] = new String[7][4];
						if (!textMondayFrom.getText().equals("") || !textMondayTo.getText().equals("")) {
							day[0][0] = "monday";
							day[0][1] = textMondayFrom.getText();
							day[0][2] = textMondayTo.getText();
						}
						if (!textTuesdayFrom.getText().equals("") || !textTuesdayTo.getText().equals("")){
							day[1][0] = "tuesday";
							day[1][1] = textTuesdayFrom.getText();
							day[1][2] = textTuesdayTo.getText();
						} 
						if (!textWednesdayFrom.getText().equals("") || !textWednesdayTo.getText().equals("")){
							day[2][0] = "wednesday";
							day[2][1] = textWednesdayFrom.getText();
							day[2][2] = textWednesdayTo.getText();
						}
						if (!textThursdayFrom.getText().equals("") || !textThursdayTo.getText().equals("")){
							day[3][0] = "thursday";
							day[3][1] = textThursdayFrom.getText();
							day[3][2] = textThursdayTo.getText();
						}
						if (!textFridayFrom.getText().equals("") || !textFridayTo.getText().equals("")){
							day[4][0] = "friday";
							day[4][1] = textFridayFrom.getText();
							day[4][2] = textFridayTo.getText();
						}
						if (!textSaturdayFrom.getText().equals("") || !textSaturdayTo.getText().equals("")){
							day[5][0] = "saturday";
							day[5][1] = textSaturdayFrom.getText();
							day[5][2] = textSaturdayTo.getText();
						}
						if (!textSundayFrom.getText().equals("") || !textSundayTo.getText().equals("")){
							day[6][0] = "sunday";
							day[6][1] = textSundayFrom.getText();
							day[6][2] = textSundayTo.getText();
						} 
						
						boolean check = false;
						boolean noUpdate = false;
						boolean updateAllowed = false;
						for(int i = 0; i < 7; i++) {
							if (day[i][0] != null) {
								check = true;
								//System.out.println("check day: " + day[i][0] + " from: " + day[i][1] + " to: " + day[i][2]);
								if(updateOpeningHoursChecks(day[i][0], day[i][1], day[i][2])) {
									day[i][3] = "good";
								} else {
									noUpdate = true;
								}
							}
						}
						if (check == true) {
							if (noUpdate == false) {
								for (int j = 0; j < 7; j++) {
									if (day[j][0] != null) {
										if (day[j][3] != null) {
											updateOpeningHoursOnTheSpecifiedDay(comboRoles.getSelectedItem().toString(),day[j][0], day[j][1], day[j][2]);
											updateAllowed = true;
										}
									}
								}
							}
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Fill least one whole row/line!");
						}
						if (updateAllowed == true) {
							setActualOH(comboRoles.getSelectedItem().toString(), lblAMoF, lblAMoT, lblATuF, lblATuT, lblAWeF, lblAWeT, lblAThF, lblAThT, lblAFrF, lblAFrT, lblASaF, lblASaT, lblASuF, lblASuT);
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
	public void setComboBox(String role, Roles roles, JLabel lblAMoF, JLabel lblAMoT, JLabel lblATuF, JLabel lblATuT, JLabel lblAWeF, JLabel lblAWeT, JLabel lblAThF, JLabel lblAThT, JLabel lblAFrF, JLabel lblAFrT, JLabel lblASaF, JLabel lblASaT, JLabel lblASuF, JLabel lblASuT) {
		
		String[] roles2 = roles.fillComboBox();
		int countRoles = comboRoles.getItemCount();
		/*
		System.out.println("count: " + countRoles);
		for (int i = 0; i<comboRoles.getItemCount(); i++) {
			System.out.println("roles before add: " + comboRoles.getItemAt(i));
		}*/
		
		comboRoles.addItem("default");
		for(String str : roles2) {
			if (!str.equals("default")) {
				//System.out.println("add: " + str);
				comboRoles.addItem(str);
			}
		}
		/*
		for (int i = 0; i<comboRoles.getItemCount(); i++) {
			System.out.println("roles after add: " + comboRoles.getItemAt(i));
		}*/
		
		for(int i = 0; i < countRoles; i++) {
			//System.out.println("remove: "+ i +". " + comboRoles.getItemAt(0));
			comboRoles.removeItemAt(0);
		}
		/*
		for (int i = 0; i<comboRoles.getItemCount(); i++) {
			System.out.println("roles after removes: " + comboRoles.getItemAt(i));
		}*/
		
		/*
		String[] roles2 = roles.fillComboBox();
		System.out.println("asd1" + comboRoles.getSelectedItem().toString());
		
		comboRoles.removeAllItems();
		System.out.println("asd2");
		//setActualOH(role, lblAMoF, lblAMoT, lblATuF, lblATuT, lblAWeF, lblAWeT, lblAThF, lblAThT, lblAFrF, lblAFrT, lblASaF, lblASaT, lblASuF, lblASuT);
		comboRoles.addItem("default");
		System.out.println("asd3");
		for(String str : roles2) {
			if (!str.equals("default")) {
				comboRoles.addItem(str);
			}
		}
		System.out.println("asd4");*/
	}
	public void setActualOH(String role, JLabel lblAMoF, JLabel lblAMoT, JLabel lblATuF, JLabel lblATuT, JLabel lblAWeF, JLabel lblAWeT, JLabel lblAThF, JLabel lblAThT, JLabel lblAFrF, JLabel lblAFrT, JLabel lblASaF, JLabel lblASaT, JLabel lblASuF, JLabel lblASuT) {
		try {
			Connection connection = 
	        		(Connection) DriverManager.getConnection("jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	        				"root", "1234");
			String select = "select day, from_value, to_value from openinghours where role = ?";
			PreparedStatement st = (PreparedStatement) connection.prepareStatement(select);
			st.setString(1, role);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
            	String day = rs.getString(1);
            	String from = rs.getString(2);
            	String to = rs.getString(3);
            	if (day.equals("monday")) {
            		lblAMoF.setText(from);
            		lblAMoT.setText(to);
            	}
            	if (day.equals("tuesday")) {
            		lblATuF.setText(from);
            		lblATuT.setText(to);
            	}
            	if (day.equals("wednesday")) {
            		lblAWeF.setText(from);
            		lblAWeT.setText(to);
            	}
            	if (day.equals("thursday")) {
            		lblAThF.setText(from);
            		lblAThT.setText(to);
            	}
            	if (day.equals("friday")) {
            		lblAFrF.setText(from);
            		lblAFrT.setText(to);
            	}
            	if (day.equals("saturday")) {
            		lblASaF.setText(from);
            		lblASaT.setText(to);
            	}
            	if (day.equals("sunday")) {
            		lblASuF.setText(from);
            		lblASuT.setText(to);
            	}
            	if (day.equals("monday")) {
            		lblAMoF.setText(from);
            		lblAMoT.setText(to);
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
	public boolean updateOpeningHoursChecks(String day, String from, String to) {
		if (from.length() == 5 && to.length() == 5) {
			if (from.substring(2, 3).equals(":") && to.substring(2, 3).equals(":")) {
				String hourFrom = from.substring(0, 2);
				String minuteFrom = from.substring(3, 5);
				String hourTo = to.substring(0, 2);
				String minuteTo = to.substring(3, 5);
				if (isNumeric(hourFrom) && isNumeric(minuteFrom) && isNumeric(hourTo) && isNumeric(minuteTo)) {
					int hourF = Integer.parseInt(hourFrom);
					int minuteF = Integer.parseInt(minuteFrom);
					int hourT = Integer.parseInt(hourTo);
					int minuteT = Integer.parseInt(minuteTo);
					if (hourF < hourT || ((hourF == hourT) && (minuteF < minuteT))) {
						if (hourF >= 0 && hourF < 24 && hourT >= 0 && hourT < 24 && minuteF >= 0 && minuteF < 60 && minuteT >= 0 && minuteT < 60) {
							return true;
						} else {
							JOptionPane.showMessageDialog(contentPanel, "At " + day + " hour value: 0-23 and minute value: 0-59!");
							return false;
						}
					} else {
						if (hourF == hourT && minuteF == minuteT) {
							JOptionPane.showMessageDialog(contentPanel, "At " + day +" the 'From' equals 'To'!");
						} else {
							JOptionPane.showMessageDialog(contentPanel, "At " + day +" the 'From' bigger than 'To'!");
						}
						
						return false;
					}
				} else {
					JOptionPane.showMessageDialog(contentPanel, "At "+ day +" the 'From' or 'To' is not numeric!");
					return false;
				}
			} else {
				JOptionPane.showMessageDialog(contentPanel, "At " + day + " the text boxes format is HH:MM!");
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(contentPanel, "At " + day + " the text boxes format is HH:MM!");
			return false;
		}
	}
	public void updateOpeningHoursOnTheSpecifiedDay(String role, String day, String from, String to) {
		try {
			
			Connection connection = 
	        		(Connection) DriverManager.getConnection("jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	        				"root", "1234");
			
			
			String update = "update openinghours set from_value = ?, to_value = ? where day = ? and role = ?";
			PreparedStatement st = (PreparedStatement) connection.prepareStatement(update);
			st.setString(1, from);
			st.setString(2, to);
			st.setString(3, day);
			st.setString(4, role);
			
			st.executeUpdate();
			//JOptionPane.showMessageDialog(contentPanel, ""+ day + " was successfully modified!");
		
		} catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(contentPanel, "Database exception! Pls check the database connection!");
        } catch (Exception f) {
        	System.out.println(f);
        }
	}
}
