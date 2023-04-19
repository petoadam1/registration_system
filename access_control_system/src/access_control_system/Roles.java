package access_control_system;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Roles extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textRole;
	/*private JButton btnDelete;*/

	/**
	 * Create the dialog.
	 */
	public Roles() {
		setBounds(100, 100, 250, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textRole = new JTextField();
		textRole.setBounds(10, 36, 89, 20);
		contentPanel.add(textRole);
		textRole.setColumns(10);
		
		JLabel lblAddRole = new JLabel("Add role");
		lblAddRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddRole.setBounds(24, 11, 67, 14);
		contentPanel.add(lblAddRole);
		
		
		String[] roles = fillComboBox();
		JComboBox comboRoles = new JComboBox(roles);
		int countRoles = comboRoles.getItemCount();
		comboRoles.addItem("default");
		for(String str : roles) {
			if (!str.equals("default")) {
				comboRoles.addItem(str);
			}
		}
		for(int i = 0; i < countRoles; i++) {
			comboRoles.removeItemAt(0);
		}
		comboRoles.setBounds(135, 35, 86, 22);
		contentPanel.add(comboRoles);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean succeeded = addRole(textRole.getText(), comboRoles);
				if (succeeded == true) {
					String[] roles = fillComboBox();
					int countRoles = comboRoles.getItemCount();
					comboRoles.addItem("default");
					for(String str : roles) {
						if (!str.equals("default")) {
							comboRoles.addItem(str);
						}
					}
					for(int i = 0; i < countRoles; i++) {
						comboRoles.removeItemAt(0);
					}
				}
				
			}
		});
		btnAdd.setBounds(10, 67, 89, 23);
		contentPanel.add(btnAdd);
		
		/*JComboBox comboRoles = new JComboBox();
		comboRoles.setBounds(135, 35, 86, 22);
		contentPanel.add(comboRoles);*/
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String role = comboRoles.getSelectedItem().toString();
				boolean succeeded = deleteRole(role);
				if (succeeded == true) {
					String[] roles = fillComboBox();
					int countRoles = comboRoles.getItemCount();
					comboRoles.addItem("default");
					for(String str : roles) {
						if (!str.equals("default")) {
							comboRoles.addItem(str);
						}
					}
					for(int i = 0; i < countRoles; i++) {
						comboRoles.removeItemAt(0);
					}
				}
			}
		});
		btnDelete.setBounds(135, 67, 89, 23);
		contentPanel.add(btnDelete);
		
		JLabel lblDeleteRole = new JLabel("Delete role");
		lblDeleteRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDeleteRole.setBounds(135, 13, 67, 14);
		contentPanel.add(lblDeleteRole);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	public boolean deleteRole(String role) {
		try {
			String delete = "delete from roles where role = ?";
			String delete_in_openinghours = "delete from openinghours where role = ?";
			String select_from_guests = "select count(*) from guests where role = ?";
			
			//JTable_Display_Guests.setModel(new DefaultTableModel());
            Connection connection = 
            		(Connection) DriverManager.getConnection("jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
            				"root", "1234");
            
            if (role.equals("default")) {
            	JOptionPane.showMessageDialog(contentPanel, "You can not delete the default role!");
            	return false;
            } else {
            	PreparedStatement st = (PreparedStatement) connection.prepareStatement(select_from_guests);
            	st.setString(1, role);
            	ResultSet rs = st.executeQuery();
            	if (rs.next()) {
            		String modify_deleted_roles_quests_to_default = "update guests set role = 'default' where role = ?;";
            		st = (PreparedStatement) connection.prepareStatement(modify_deleted_roles_quests_to_default);
            		st.setString(1, role);
            		st.executeUpdate();
            		System.out.println("update done!");
            		connection = 
                    		(Connection) DriverManager.getConnection("jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    				"root", "1234");
            		
            		st = (PreparedStatement) connection.prepareStatement(delete_in_openinghours);
                	st.setString(1,  role);
                	st.execute();
                	
                	st = (PreparedStatement) connection.prepareStatement(delete);
                	st.setString(1, role);
                    st.execute();
                    JOptionPane.showMessageDialog(contentPanel, "The delete was successful!");
                    return true;
            	}
            	else {
            		st = (PreparedStatement) connection.prepareStatement(delete_in_openinghours);
                	st.setString(1,  role);
                	st.execute();
                	
                	st = (PreparedStatement) connection.prepareStatement(delete);
                	st.setString(1, role);
                    st.execute();
                    JOptionPane.showMessageDialog(contentPanel, "The delete was successful!");
                    return true;
            		//JOptionPane.showMessageDialog(contentPanel, "The guests table is empty!");
            		//return false;
            	}
            	
            	
            	
            	
            }
            
            
            
        	
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(contentPanel, "Database exception! Pls check the database connection!");
            return false;
        } catch (Exception f) {
        	System.out.println(f);
        	return false;
        }
	}
	public String[] fillComboBox() {
		try {
			String select = "select role from roles";
			Connection connection = 
	        		(Connection) DriverManager.getConnection("jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
	        				"root", "1234");
			PreparedStatement st = (PreparedStatement) connection.prepareStatement(select);
			ResultSet rs = st.executeQuery();
			ArrayList<String> rolesArray = new ArrayList<>();
			while (rs.next()) {
				rolesArray.add(rs.getString(1));
			}
		
			//int length = rolesArray.size();
			String[] roles = new String[rolesArray.size()];
			for (int i = 0; i < roles.length; i++) {
				roles[i] = rolesArray.get(i);
			}
			//System.out.println(rolesArray.size());
			//String[] dbData = dateFromDb();
			return roles;
			//comboRoles = new JComboBox(roles);
		} catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(contentPanel, "Database exception! Pls check the database connection!");
            return null;
        } catch (Exception f) {
        	System.out.println(f);
        	return null;
        }
	}
	public boolean addRole(String role, JComboBox comboRoles) {
		try {
			//boolean databaseGuestsNumberIsZero = false;
			//String id_select = "select id from roles order by id desc limit 1;";
			if (role.equals("")) {
				JOptionPane.showMessageDialog(contentPanel, "You can not add anonymous/nameless role!");
				return false;
			} else if (role.equals("all")) {
				JOptionPane.showMessageDialog(contentPanel, "You can not add a role with 'all' name!");
				return false;
			}
			else {
				
				String select = "select count(*) from roles where role = ?;";
				String insert = "insert into roles (role) values (?);";
				String select_from_openinghours = "select id from openinghours order by id desc limit 1;";
				String[] insert_to_openinghours = new String[7];
				insert_to_openinghours[0] = "INSERT INTO openinghours (id, day, role) VALUES(?, 'monday', ?);";
				insert_to_openinghours[1] = "INSERT INTO openinghours (id, day, role) VALUES(?, 'tuesday', ?);";
				insert_to_openinghours[2] = "INSERT INTO openinghours (id, day, role) VALUES(?, 'wednesday', ?);";
				insert_to_openinghours[3] = "INSERT INTO openinghours (id, day, role) VALUES(?, 'thursday', ?);";
				insert_to_openinghours[4] = "INSERT INTO openinghours (id, day, role) VALUES(?, 'friday', ?);";
				insert_to_openinghours[5] = "INSERT INTO openinghours (id, day, role) VALUES(?, 'saturday', ?);";
				insert_to_openinghours[6] = "INSERT INTO openinghours (id, day, role) VALUES(?, 'sunday', ?);";
				
				Connection connection = 
		        		(Connection) DriverManager.getConnection("jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		        				"root", "1234");
				PreparedStatement st = (PreparedStatement) connection.prepareStatement(select);
				st.setString(1, role);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					if (rs.getInt(1) == 0) {
						st = (PreparedStatement) connection.prepareStatement(insert);
						st.setString(1, role);
						st.executeUpdate();
						
						
						
						st = (PreparedStatement) connection.prepareStatement(select_from_openinghours);
						rs = st.executeQuery();
						
						while(rs.next()) {
							System.out.println(rs.getInt(1));
							for (int i = 0; i < 7; i++) {
								st = (PreparedStatement) connection.prepareStatement(insert_to_openinghours[i]);
								st.setInt(1, rs.getInt(1)+(i+1));
								st.setString(2, role);
								st.executeUpdate();
							}
						}
						//comboRoles.addItem(role);
						JOptionPane.showMessageDialog(contentPanel, "Add was successfull!");
						return true;
					}
					else {
						JOptionPane.showMessageDialog(contentPanel, "Database contains this role!");
						return false;
					}
				}
				//JOptionPane.showMessageDialog(contentPanel, "Database size is 0");
				return false;
			}
			
			/*ResultSet rs = st.executeQuery();*/
			
			
			/*
			if (databaseGuestsNumberIsZero == false) {
				st = (PreparedStatement) connection.prepareStatement(insert);
				st.setInt(1, 1); 
				st.setString(2, role);
				st.executeUpdate();
			}*/
			
		
	        
		} catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(contentPanel, "Database exception! Pls check the database connection!");
            return false;
        } catch (Exception f) {
        	System.out.println(f);
        	return false;
        }
	}
}
