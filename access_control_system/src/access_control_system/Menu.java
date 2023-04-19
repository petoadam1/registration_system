package access_control_system;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTable JTable_Display_Guests;
	private JTextField textName;
	private JTextField textRfid;
	private JTextField textPhone;
	private JTextField textYear;
	private JTextField textMonth;
	private JTextField textDay;
	private JToggleButton queryToggleButton;
	private JToggleButton addToggleButton;
	private JToggleButton updateToggleButton;
	private JToggleButton deleteToggleButton;
	private JScrollPane scrollPane;
	private JComboBox<String> comboRoles;
	private JButton btnMain;
	private JButton btnPasses;
	private JButton btnOpeningHours;
	private JButton btnRoles;
	private JLabel lblRole;
	private JLabel lblName;
	private JLabel lblRfid;
	private JLabel lblPhone;
	private JLabel lblValidity;
	private JLabel lblYear;
	private JLabel lblMonth;
	private JLabel lblDay;
	JLabel lblResultCount;
	private OpeningHours openinghours;
	private Passes passes;
	private Roles roles;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu menu = new Menu();
					menu.setLocationRelativeTo(null);
					menu.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		openinghours = new OpeningHours();
		passes = new Passes();
		roles = new Roles();

		/* set window */
		openinghours.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		/* create the menu bars */
		queryToggleButton = new JToggleButton("Query");
		queryToggleButton.setSelected(true);
		addToggleButton = new JToggleButton("Add");
		updateToggleButton = new JToggleButton("Update");
		deleteToggleButton = new JToggleButton("Delete");

		/* create and set GUI elements */
		lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRole.setBounds(10, 40, 69, 17);
		contentPane.add(lblRole);

		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(10, 80, 69, 17);

		lblRfid = new JLabel("Rfid:");
		lblRfid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRfid.setBounds(10, 120, 69, 17);

		lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhone.setBounds(10, 160, 69, 17);

		lblValidity = new JLabel("Validity:");
		lblValidity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValidity.setBounds(10, 200, 69, 17);

		lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblYear.setBounds(120, 200, 41, 17);
		contentPane.add(lblYear);

		lblMonth = new JLabel("Month");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMonth.setBounds(192, 200, 41, 17);
		contentPane.add(lblMonth);

		lblDay = new JLabel("Day");
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDay.setBounds(264, 200, 41, 17);
		contentPane.add(lblDay);

		lblResultCount = new JLabel("");
		lblResultCount.setBounds(308, 50, 466, 14);
		contentPane.add(lblResultCount);

		contentPane.add(lblName);
		contentPane.add(lblRfid);
		contentPane.add(lblPhone);
		contentPane.add(lblValidity);

		textName = new JTextField();
		textName.setBounds(71, 77, 217, 24);
		contentPane.add(textName);
		textName.setColumns(10);

		textRfid = new JTextField();
		textRfid.setColumns(10);
		textRfid.setBounds(71, 117, 217, 24);
		contentPane.add(textRfid);

		textPhone = new JTextField();
		textPhone.setColumns(10);
		textPhone.setBounds(71, 157, 217, 24);
		contentPane.add(textPhone);

		textYear = new JTextField();
		textYear.setColumns(10);
		textYear.setBounds(71, 197, 49, 24);
		contentPane.add(textYear);

		textMonth = new JTextField();
		textMonth.setColumns(10);
		textMonth.setBounds(160, 197, 31, 24);
		contentPane.add(textMonth);

		textDay = new JTextField();
		textDay.setColumns(10);
		textDay.setBounds(232, 197, 31, 24);
		contentPane.add(textDay);

		/*
		 * TextField textField = new TextField(textName.getText(), textRfid.getText(),
		 * textPhone.getText(), textYear.getText(), textMonth.getText(),
		 * textDay.getText());
		 */
		/*
		 * textFields[0] = textName; textFields[1] = textRfid; textFields[2] =
		 * textPhone; textFields[3] = textYear; textFields[4] = textMonth; textFields[5]
		 * = textDay;
		 */

		scrollPane = new JScrollPane();
		scrollPane.setBounds(308, 64, 466, 157);
		contentPane.add(scrollPane);

		comboRoles = new JComboBox<>();
		comboRoles.setBounds(71, 37, 217, 22);
		contentPane.add(comboRoles);

		btnMain = new JButton("Query");
		btnPasses = new JButton("Passes");
		btnOpeningHours = new JButton("Opening Hours");
		btnRoles = new JButton("Roles");

		/* set table which is filled with guests */
		JTable_Display_Guests = new JTable();
		JTable_Display_Guests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTable_Display_Guests.addMouseListener(new MouseAdapter() {

			/* when click on a guest in table, then fill in the text boxes */
			@Override
			public void mouseClicked(MouseEvent e) {
				btnMain.setEnabled(true);
				int row = JTable_Display_Guests.getSelectedRow();
				String name = JTable_Display_Guests.getModel().getValueAt(row, 0).toString();
				String rfid = JTable_Display_Guests.getModel().getValueAt(row, 1).toString();
				String phone = JTable_Display_Guests.getModel().getValueAt(row, 2).toString();
				String validity = JTable_Display_Guests.getModel().getValueAt(row, 3).toString();
				String role = JTable_Display_Guests.getModel().getValueAt(row, 4).toString();
				String year = validity.substring(0, 4);
				String month = validity.substring(5, 7);
				String day = validity.substring(8, 10);
				comboRoles.setSelectedItem(role);
				textName.setText(name);
				textRfid.setText(rfid);
				textPhone.setText(phone);
				textYear.setText(year);
				textMonth.setText(month);
				textDay.setText(day);
			}
		});
		scrollPane.setViewportView(JTable_Display_Guests);

		/* fill ComboBox with actual roles to query method */
		String[] roles2 = roles.fillComboBox();
		int countRoles = comboRoles.getItemCount();
		comboRoles.addItem("all");
		comboRoles.addItem("default");
		for (String str : roles2) {
			if (!str.equals("default")) {
				comboRoles.addItem(str);
			}
		}
		for (int i = 0; i < countRoles; i++) {
			comboRoles.removeItemAt(0);
		}

		// when select all on query page, then make empty all TextBox
		comboRoles.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				String selectedItem = comboRoles.getSelectedItem().toString();
				if (selectedItem.equals("all")) {
					textName.setText("");
					textRfid.setText("");
					textPhone.setText("");
					textYear.setText("");
					textMonth.setText("");
					textDay.setText("");
				}
			}
		});

		roles.addComponentListener(new ComponentListener() {
			@Override
			public void componentHidden(ComponentEvent e) {
				// System.out.println("dialog hidden");
				if (queryToggleButton.isSelected()) {

					String[] roles2 = roles.fillComboBox();
					int countRoles = comboRoles.getItemCount();
					comboRoles.addItem("all");
					comboRoles.addItem("default");
					for (String str : roles2) {
						if (!str.equals("default")) {
							comboRoles.addItem(str);
						}
					}
					for (int i = 0; i < countRoles; i++) {
						comboRoles.removeItemAt(0);
					}
				} else {
					String[] roles2 = roles.fillComboBox();
					int countRoles = comboRoles.getItemCount();
					comboRoles.addItem("default");
					for (String str : roles2) {
						if (!str.equals("default")) {
							comboRoles.addItem(str);
						}
					}
					for (int i = 0; i < countRoles; i++) {
						comboRoles.removeItemAt(0);
					}
				}
				getGuestList(lblResultCount);

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// System.out.println("dialog moved");
			}

			@Override
			public void componentResized(ComponentEvent e) {
				// System.out.println("dialog resized");
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// System.out.println("dialog shown");
			}
		});

		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Check that the fields filled well in add page
				if (addToggleButton.isSelected()) {
					if (checkAddPageTextBoxes(textName.getText(), textRfid.getText(), textPhone.getText(),
							textYear.getText(), textMonth.getText(), textDay.getText())) {

						String textDate = textYear.getText() + "-" + textMonth.getText() + "-" + textDay.getText();
						Guest guest = new Guest();

						guest.setName(textName.getText());
						guest.setRfid(textRfid.getText());
						guest.setPhone(Integer.parseInt(textPhone.getText()));
						guest.setValidity(textDate);
						guest.setRole(comboRoles.getSelectedItem().toString());

						addGuest(guest);
						getGuestList(lblResultCount);
					}
				}
				// Check that the fields filled well in update page
				else if (updateToggleButton.isSelected()) {

					int selectedRow = JTable_Display_Guests.getSelectedRow();

					if (isThereARowSelected(selectedRow) && oneFieldFilled(textName.getText(), textRfid.getText(),
							textPhone.getText(), textYear.getText(), textMonth.getText(), textDay.getText())) {

						Guest selectedGuest = setDefaultValueToGuest(JTable_Display_Guests, selectedRow);
						Guest newGuest = setNewGuestValues(selectedGuest, comboRoles, textName.getText(),
								textRfid.getText(), textPhone.getText(), textYear.getText(), textMonth.getText(),
								textDay.getText());

						if (newGuest != null) {
							System.out.println(newGuest.getRfid());
							newUpdateGuest(newGuest, JTable_Display_Guests.getModel().getValueAt(selectedRow, 1).toString());
							getGuestList(lblResultCount);
						}

					}
				}
				// Check that the fields filled well in delete page
				else if (deleteToggleButton.isSelected()) {

					int selectedRow = JTable_Display_Guests.getSelectedRow();

					if (isThereARowSelected(selectedRow)) {
						String rfid = JTable_Display_Guests.getModel().getValueAt(selectedRow, 1).toString();
						deleteGuest(rfid, lblResultCount);
					}
				}
				// Check that the fields filled well in add page
				else if (queryToggleButton.isSelected()) {
					/*
					 * for (int i = 0; i < 6; i++) { if() }
					 */
					if (textName.getText().isEmpty() && textRfid.getText().isEmpty() && textPhone.getText().isEmpty()
							&& textYear.getText().isEmpty() && textMonth.getText().isEmpty()
							&& textDay.getText().isEmpty() && comboRoles.getSelectedItem().toString().equals("all")) {
						getGuestList(lblResultCount);
					} else if (!textName.getText().isEmpty() && textRfid.getText().isEmpty()
							&& textPhone.getText().isEmpty() && textYear.getText().isEmpty()
							&& textMonth.getText().isEmpty() && textDay.getText().isEmpty()
							&& comboRoles.getSelectedItem().toString().equals("all")) {
						getSortedGuestList(textName.getText(), "name", lblResultCount);
					} else if (!textRfid.getText().isEmpty() && textName.getText().isEmpty()
							&& textPhone.getText().isEmpty() && textYear.getText().isEmpty()
							&& textMonth.getText().isEmpty() && textDay.getText().isEmpty()
							&& comboRoles.getSelectedItem().toString().equals("all")) {
						getSortedGuestList(textRfid.getText(), "rfid", lblResultCount);
					} else if (!textPhone.getText().isEmpty() && textRfid.getText().isEmpty()
							&& textName.getText().isEmpty() && textYear.getText().isEmpty()
							&& textMonth.getText().isEmpty() && textDay.getText().isEmpty()
							&& comboRoles.getSelectedItem().toString().equals("all")) {
						getSortedGuestList(textPhone.getText(), "phone", lblResultCount);
					} else if (!textYear.getText().isEmpty() && !textMonth.getText().isEmpty()
							&& !textDay.getText().isEmpty() && textRfid.getText().isEmpty()
							&& textPhone.getText().isEmpty() && textName.getText().isEmpty()
							&& comboRoles.getSelectedItem().toString().equals("all")) {
						String textDate = textYear.getText() + "-" + textMonth.getText() + "-" + textDay.getText();
						getSortedGuestList(textDate, "validity", lblResultCount);
					} else if (!comboRoles.getSelectedItem().toString().equals("all") && textName.getText().isEmpty()
							&& textRfid.getText().isEmpty() && textPhone.getText().isEmpty()
							&& textYear.getText().isEmpty() && textMonth.getText().isEmpty()
							&& textDay.getText().isEmpty()) {
						getSortedGuestList(comboRoles.getSelectedItem().toString(), "role", lblResultCount);
					} else {
						JOptionPane.showMessageDialog(null, "Fill max one field or choose only the role!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "No page selected!");
				}

				// JOptionPane.showMessageDialog(exitButton, "Wrong Username & Password");

			}
		});
		btnMain.setBounds(20, 240, 268, 23);
		contentPane.add(btnMain);

		queryToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (queryToggleButton.isSelected()) {
					btnMain.setText("Query");
					btnMain.setEnabled(true);
					setTextFieldsToEmpty();
					switchBetweenTextEnabledTrueOrFalse(true);
					setComboBox("query");
					setOtherMenusUnselected(1); // 1: query, 2: add, 3: update, 4: delete (which is selected)
					JTable_Display_Guests.clearSelection();
				} else {
					queryToggleButton.setSelected(true);
				}
			}
		});
		queryToggleButton.setBounds(2, 0, 195, 24);
		contentPane.add(queryToggleButton);

		addToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (addToggleButton.isSelected()) {
					btnMain.setText("Add");
					btnMain.setEnabled(true);
					setTextFieldsToEmpty();
					switchBetweenTextEnabledTrueOrFalse(true);
					setComboBox("notQuery");
					setOtherMenusUnselected(2); // 1: query, 2: add, 3: update, 4: delete (which is selected)
					JTable_Display_Guests.clearSelection();
				} else {
					addToggleButton.setSelected(true);
				}
			}
		});
		addToggleButton.setBounds(195, 0, 195, 24);
		contentPane.add(addToggleButton);

		updateToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (updateToggleButton.isSelected()) {
					btnMain.setText("Update");
					btnMain.setEnabled(false);
					setTextFieldsToEmpty();
					switchBetweenTextEnabledTrueOrFalse(true);
					setComboBox("notQuery");
					setOtherMenusUnselected(3); // 1: query, 2: add, 3: update, 4: delete (which is selected)
					JTable_Display_Guests.clearSelection();
				} else {
					updateToggleButton.setSelected(true);
				}
			}
		});
		updateToggleButton.setBounds(390, 0, 195, 24);
		contentPane.add(updateToggleButton);

		deleteToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (deleteToggleButton.isSelected()) {
					btnMain.setText("Delete");
					btnMain.setEnabled(true);
					setTextFieldsToEmpty();
					switchBetweenTextEnabledTrueOrFalse(false);
					setComboBox("notQuery");
					setOtherMenusUnselected(4); // 1: query, 2: add, 3: update, 4: delete (which is selected)
					JTable_Display_Guests.clearSelection();
				} else {
					deleteToggleButton.setSelected(true);
				}
			}
		});
		deleteToggleButton.setBounds(585, 0, 195, 24);
		contentPane.add(deleteToggleButton);

		// Query the datas in table
		getGuestList(lblResultCount);

		btnOpeningHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				openinghours.setLocationRelativeTo(null);
				openinghours.setVisible(true);
				openinghours.setComboBox("default", roles, openinghours.lblAMoF, openinghours.lblAMoT,
						openinghours.lblATuF, openinghours.lblATuT, openinghours.lblAWeF, openinghours.lblAWeT,
						openinghours.lblAThF, openinghours.lblAThT, openinghours.lblAFrF, openinghours.lblAFrT,
						openinghours.lblASaF, openinghours.lblASaT, openinghours.lblASuF, openinghours.lblASuT);
				/*
				 * String[] roles2 = roles.fillComboBox();
				 * 
				 * oh.comboRoles.removeAllItems(); setActualOH("default", lblAMoF, lblAMoT,
				 * lblATuF, lblATuT, lblAWeF, lblAWeT, lblAThF, lblAThT, lblAFrF, lblAFrT,
				 * lblASaF, lblASaT, lblASuF, lblASuT); comboRoles.addItem("default");
				 * for(String str : roles2) { if (!str.equals("default")) {
				 * comboRoles.addItem(str); } }
				 */
				// menu.setVisible(false);

			}
		});
		btnOpeningHours.setBounds(308, 228, 120, 23);
		contentPane.add(btnOpeningHours);

		btnPasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passes.setLocationRelativeTo(null);
				passes.setVisible(true);
			}
		});
		btnPasses.setBounds(437, 228, 120, 23);
		contentPane.add(btnPasses);

		btnRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roles.setLocationRelativeTo(null);
				roles.setVisible(true);
			}
		});
		btnRoles.setBounds(308, 257, 120, 23);
		contentPane.add(btnRoles);

	}

	public boolean checkAddPageTextBoxes(String tname, String trfid, String tphone, String tyear, String tmonth,
			String tday) {
		if (allFieldsFilled(tname, trfid, tphone, tyear, tmonth, tday) && correctRfid(trfid) && correctPhone(tphone)
				&& correctDate(tyear, tmonth, tday) && dbContainRfid(trfid)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean allFieldsFilled(String tname, String trfid, String tphone, String tyear, String tmonth,
			String tday) {
		if (!tname.isEmpty() && !trfid.isEmpty() && !tphone.isEmpty() && !tyear.isEmpty() && !tmonth.isEmpty()
				&& !tday.isEmpty()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Fill all fields!");
			return false;
		}
	}

	public boolean oneFieldFilled(String tname, String trfid, String tphone, String tyear, String tmonth, String tday) {
		if (!tname.isEmpty() || !trfid.isEmpty() || !tphone.isEmpty() || !tyear.isEmpty() || !tmonth.isEmpty()
				|| !tday.isEmpty()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Fill least one field to be modified!");
			return false;
		}
	}

	/*
	 * public boolean checkUpdateable(Guest guest, JComboBox<String> comboRoles,
	 * String tname, String trfid, String tphone, String tyear, String tmonth,
	 * String tday) { //if dont want to change rfid
	 * 
	 * return true; }
	 */

	public boolean isThereARowSelected(int selectedRow) {
		// if selectedRow is -1, then there is nothing select from table. When
		// selectedRow is not -1,
		// then there is one row select from table
		if (selectedRow != -1) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Select one row from table!");
			return false;
		}
	}

	public Guest setDefaultValueToGuest(JTable jTable, int row) {
		Guest guest = new Guest();
		guest.setName(JTable_Display_Guests.getModel().getValueAt(row, 0).toString());
		guest.setRfid(JTable_Display_Guests.getModel().getValueAt(row, 1).toString());
		guest.setPhone(Integer.parseInt(JTable_Display_Guests.getModel().getValueAt(row, 2).toString()));
		guest.setValidity(JTable_Display_Guests.getModel().getValueAt(row, 3).toString());
		guest.setRole(JTable_Display_Guests.getModel().getValueAt(row, 4).toString());
		return guest;
	}

	public Guest setNewGuestValues(Guest selectedGuest, JComboBox<String> comboRoles, String tname, String trfid,
			String tphone, String tyear, String tmonth, String tday) {
		boolean updateable = true;
		if (selectedGuest.getRfid().equals(trfid)) {

			//System.out.println("asdasd");
			if (checkAllRowsAreSame(selectedGuest, comboRoles, tname, trfid, tphone,
					tyear, tmonth, tday)) {
				updateable = false;
			} else {
				if (!tname.isEmpty()) {
					selectedGuest.setName(tname);
				}
				if (!trfid.isEmpty()) {
					if (correctRfid(trfid)) {
						selectedGuest.setRfid(trfid);
					} else {
						updateable = false;
					}
				}
				if (!tphone.isEmpty()) {
					if (correctPhone(tphone)) {
						selectedGuest.setPhone(Integer.parseInt(tphone));
					} else {
						updateable = false;
					}
				}
				if (!tyear.isEmpty()) {
					if (correctDate(tyear, tmonth, tday)) {
						selectedGuest.setValidity(tyear + "-" + tmonth + "-" + tday);
					} else {
						updateable = false;
					}
				}
				if (!comboRoles.getSelectedItem().toString().equals(selectedGuest.getRole())) {
					selectedGuest.setRole(comboRoles.getSelectedItem().toString());
				}
			}
			// if want to change rfid
		} else {

			System.out.println("asdasd");
			
			if (!tname.isEmpty()) {
				selectedGuest.setName(tname);
			}
			if (!trfid.isEmpty()) {
				if (correctRfid(trfid) && dbContainRfid(trfid)) {
					selectedGuest.setRfid(trfid);
					//System.out.println(trfid);
				} else {
					updateable = false;
				}
			}
			if (!tphone.isEmpty()) {
				if (correctPhone(tphone)) {
					selectedGuest.setPhone(Integer.parseInt(tphone));
				} else {
					updateable = false;
				}
			}
			if (!tyear.isEmpty()) {
				if (correctDate(tyear, tmonth, tday)) {
					selectedGuest.setValidity(tyear + "-" + tmonth + "-" + tday);
				} else {
					updateable = false;
				}
			}
			if (!comboRoles.getSelectedItem().toString().equals(selectedGuest.getRole())) {
				selectedGuest.setRole(comboRoles.getSelectedItem().toString());
			}
		}
		if (updateable) {
			return selectedGuest;
		} else {
			return null;
		}
	}

	public boolean checkAllRowsAreSame(Guest guest, JComboBox<String> comboRoles, String tname, String trfid,
			String tphone, String tyear, String tmonth, String tday) {
		if (guest.getName().equals(tname) && String.valueOf(guest.getPhone()).equals(tphone)
				&& tyear.equals(guest.getYear()) && tmonth.equals(guest.getMonth()) && tday.equals(guest.getDay())
				&& guest.getRole().equals(comboRoles.getSelectedItem().toString())) {
			JOptionPane.showMessageDialog(null, "All row is same! What do you want to change?");
			return true;
		} else {
			return false;
		}
	}
	/*
	 * public Guest changeGuestValueToTextIfCorrect(Guest guest, JComboBox<String>
	 * comboRoles, String tname, String trfid, String tphone, String tyear, String
	 * tmonth, String tday) { if (!tname.isEmpty()) guest.setName(tname); if
	 * (!trfid.isEmpty()) { if (correctRfid(trfid)) { guest.setRfid(trfid); } } if
	 * (!tphone.isEmpty()) { if (correctPhone(tphone)) {
	 * guest.setPhone(Integer.parseInt(tphone)); } } if (!tyear.isEmpty()) { if
	 * (correctDate(tyear, tmonth, tday)) { guest.setValidity(tyear + "-" + tmonth +
	 * "-" + tday); } } if
	 * (!comboRoles.getSelectedItem().toString().equals(guest.getRole())) {
	 * guest.setRole(comboRoles.getSelectedItem().toString()); } return guest; }
	 */

	public void setTextFieldsToEmpty() {
		textName.setText("");
		textRfid.setText("");
		textPhone.setText("");
		textYear.setText("");
		textMonth.setText("");
		textDay.setText("");
		lblResultCount.setText("");
	}

	public void switchBetweenTextEnabledTrueOrFalse(boolean permission) {
		textName.setEnabled(permission);
		textRfid.setEnabled(permission);
		textPhone.setEnabled(permission);
		textYear.setEnabled(permission);
		textMonth.setEnabled(permission);
		textDay.setEnabled(permission);
		comboRoles.setEnabled(permission);
	}

	public void setComboBox(String which) {
		if (which.equals("notQuery")) {
			comboRoles.removeItem("all");
			comboRoles.setSelectedItem("default");
		} else if (which.equals("query")) {
			String[] roles2 = roles.fillComboBox();
			int countRoles = comboRoles.getItemCount();
			comboRoles.addItem("all");
			comboRoles.addItem("default");
			for (String str : roles2) {
				if (!str.equals("default")) {
					comboRoles.addItem(str);
				}
			}
			for (int i = 0; i < countRoles; i++) {
				comboRoles.removeItemAt(0);
			}
		}

	}

	public void setOtherMenusUnselected(int which) {
		switch (which) {
		case 1:
			addToggleButton.setSelected(false);
			updateToggleButton.setSelected(false);
			deleteToggleButton.setSelected(false);
			break;
		case 2:
			queryToggleButton.setSelected(false);
			updateToggleButton.setSelected(false);
			deleteToggleButton.setSelected(false);
			break;
		case 3:
			queryToggleButton.setSelected(false);
			addToggleButton.setSelected(false);
			deleteToggleButton.setSelected(false);
			break;
		case 4:
			queryToggleButton.setSelected(false);
			addToggleButton.setSelected(false);
			updateToggleButton.setSelected(false);
			break;

		}
	}

	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public boolean correctRfid(String rfid) {
		if (rfid.length() == 10) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Rfid field requires 10 characters!");
			return false;
		}

	}

	public boolean correctPhone(String phone) {

		if (phone.length() == 9 || phone.length() == 8) {
			if (isNumeric(phone)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Phone field requires numbers!");
				return false;
			}

		} else {
			JOptionPane.showMessageDialog(null, "Phone field requires 8-9 characters!");
			return false;
		}

	}

	public boolean correctDate(String year, String month, String day) {
		if (year.length() == 4 && month.length() == 2 && day.length() == 2) {
			if (isNumeric(year) && isNumeric(month) && isNumeric(day)) {
				if (Integer.parseInt(year) > 2021 && Integer.parseInt(year) < 2100) {
					if (Integer.parseInt(month) > 0 && Integer.parseInt(month) < 13) {

						int month_value = Integer.parseInt(month);

						if (month_value == 4 || month_value == 6 || month_value == 9 || month_value == 11) {
							if (Integer.parseInt(day) > 0 && Integer.parseInt(day) < 31) {
								return true;
							} else {
								JOptionPane.showMessageDialog(null,
										"Validity day field requires a value between 1-30 at " + month_value
												+ " month!");
								return false;
							}

						} else if (month_value == 2) {
							if (Integer.parseInt(year) % 4 == 0) {
								if (Integer.parseInt(day) > 0 && Integer.parseInt(day) < 30) {
									return true;
								} else {
									JOptionPane.showMessageDialog(null,
											"Validity day field requires a value between 1-29 at " + month_value
													+ " month! Because this year is leap year!");
									return false;
								}

							} else {
								if (Integer.parseInt(day) > 0 && Integer.parseInt(day) < 29) {
									return true;
								} else {
									JOptionPane.showMessageDialog(null,
											"Validity day field requires a value between 1-28 at " + month_value
													+ " month!");
									return false;
								}

							}

						}
						/*
						 * if (month_value == 1 || month_value == 3 || month_value == 5 || month_value
						 * == 7 || month_value == 8 || month_value == 10 || month_value == 12) {
						 * 
						 * }
						 */
						else {
							if (Integer.parseInt(day) > 0 && Integer.parseInt(day) < 32) {
								return true;
							} else {
								JOptionPane.showMessageDialog(null,
										"Validity day field requires a value between 1-31 at " + month_value
												+ " month!");
								return false;
							}

						}

					} else {
						JOptionPane.showMessageDialog(null,
								"Validity month field requires a value between 1-12!");
						return false;
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Validity year field requires a value between 2022-2100!");
					return false;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Validity fields requires numberic value!");
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Validity fields requires YYYY / MM / DD characters!");
			return false;
		}
	}

	public boolean dbContainRfid(String rfid) {
		try {
			String contain = "select count(*) from guests where rfid = ?";

			Connection connection = (Connection) DriverManager.getConnection(
					"jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "1234");
			PreparedStatement st = (PreparedStatement) connection.prepareStatement(contain);

			st.setString(1, rfid);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if (rs.getInt(1) == 0) {
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Database contains this RFID!");
					return false;
				}
			}

		} catch (SQLException sqlException) {
			JOptionPane.showMessageDialog(null, "Database exception! Pls check the database connection!");
			sqlException.printStackTrace();
		} catch (Exception f) {
			System.out.println(f);
		}
		return false;

	}

	public void addGuest(Guest guest) {
		try {

			boolean databaseGuestsNumberIsZero = false;
			// int id = 0;
			// String id_select = "select count(*) as guestcount from guests";
			String id_select = "select id from guests order by id desc limit 1;";

			String insert = "insert into guests (id, name, rfid, phone, validity, role) values (?, ?, ?, ?, ?, ?);";
			Connection connection = (Connection) DriverManager.getConnection(
					"jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "1234");
			PreparedStatement st = (PreparedStatement) connection.prepareStatement(id_select);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				// System.out.println("van valami a guestbe");
				databaseGuestsNumberIsZero = true;
				st = (PreparedStatement) connection.prepareStatement(insert);

				st.setInt(1, (rs.getInt(1) + 1));
				st.setString(2, guest.getName());
				st.setString(3, guest.getRfid());
				st.setInt(4, guest.getPhone());
				st.setString(5, guest.getValidity());
				st.setString(6, guest.getRole());
				st.executeUpdate();
			}
			if (databaseGuestsNumberIsZero == false) {
				// System.out.println("nincs semmi a guestbe");
				st = (PreparedStatement) connection.prepareStatement(insert);
				st.setInt(1, 1);
				st.setString(2, guest.getName());
				st.setString(3, guest.getRfid());
				st.setInt(4, guest.getPhone());
				st.setString(5, guest.getValidity());
				st.setString(6, guest.getRole());
				st.executeUpdate();
			}

			//getGuestList(lblResultCount);

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			JOptionPane.showMessageDialog(null, "Database exception! Pls check the database connection!");
		} catch (Exception f) {
			System.out.println(f);
		} finally {
			lblResultCount.setText("The addition was successful!");
		}
	}

	public void newUpdateGuest(Guest guest, String actual_rfid) {
		try {
			//String actual_rfid = guest.getRfid();
			System.out.println(actual_rfid);
			Connection connection = (Connection) DriverManager.getConnection(
					"jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "1234");

			/*
			 * PreparedStatement st = (PreparedStatement)
			 * connection.prepareStatement("select count(*) from guests where name = ?");
			 * st.setString(1, whom);
			 */
			// System.out.println(stCotainWhom);
			/*
			 * ResultSet rs = st.executeQuery(); System.out.println(rs.next());
			 */
			String update = "update guests set name = ?, rfid = ?, phone = ?, validity = ?, role = ? where rfid = ?";
			PreparedStatement st = (PreparedStatement) connection.prepareStatement(update);
			st.setString(1, guest.getName());
			st.setString(2, guest.getRfid());
			st.setInt(3, guest.getPhone());
			st.setString(4, guest.getValidity());
			st.setString(5, guest.getRole());
			st.setString(6, actual_rfid);
			st.executeUpdate();

			// JOptionPane.showMessageDialog(null, "The update was successful!");

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			JOptionPane.showMessageDialog(null, "Database exception! Pls check the database connection!");
		} catch (Exception f) {
			System.out.println(f);
		} finally {
			lblResultCount.setText("The update was successful!");
		}
	}

	public void deleteGuest(String rfid, JLabel lblResultCount) {
		try {
			String delete = "delete from guests where rfid = ?";
			JTable_Display_Guests.setModel(new DefaultTableModel());
			Connection connection = (Connection) DriverManager.getConnection(
					"jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "1234");
			PreparedStatement st = (PreparedStatement) connection.prepareStatement(delete);
			st.setString(1, rfid);
			st.execute();

			getGuestList(lblResultCount);
			// JOptionPane.showMessageDialog(null, "The delete was successful!");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			JOptionPane.showMessageDialog(null, "Database exception! Pls check the database connection!");
		} catch (Exception f) {
			System.out.println(f);
		} finally {
			lblResultCount.setText("The delete was successful!");
		}
	}

	public void getSortedGuestList(String text, String which, JLabel lblResultCount) {
		try {
			String select = null;
			int count = 0;
			if (which.equals("name")) {
				select = "Select name, rfid, phone, validity, role from guests where name = ?";
			} else if (which.equals("rfid")) {
				select = "Select name, rfid, phone, validity, role from guests where rfid = ?";
			} else if (which.equals("phone")) {
				select = "Select name, rfid, phone, validity, role from guests where phone = ?";
			} else if (which.equals("validity")) {
				select = "Select name, rfid, phone, validity, role from guests where validity = ?";
			} else if (which.equals("role")) {
				select = "Select name, rfid, phone, validity, role from guests where role = ?";
			}

			JTable_Display_Guests.setModel(new DefaultTableModel());
			Connection connection = (Connection) DriverManager.getConnection(
					"jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "1234");

			PreparedStatement st = (PreparedStatement) connection.prepareStatement(select);

			st.setString(1, text);

			ResultSet rs = st.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) JTable_Display_Guests.getModel();

			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i = 0; i < cols; i++) {
				colName[i] = rsmd.getColumnName(i + 1);

			}
			model.setColumnIdentifiers(colName);
			String name, rfid, phone, validity, role;

			while (rs.next()) {
				name = rs.getString(1);
				rfid = rs.getString(2);
				phone = rs.getString(3);
				validity = rs.getString(4);
				role = rs.getString(5);
				String[] row = { name, rfid, phone, validity, role };
				model.addRow(row);
				count++;
			}
			lblResultCount.setText("Results: " + count);
			/*
			 * st.close(); connection.close();
			 */

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			JOptionPane.showMessageDialog(null, "Database exception! Pls check the database connection!");
		} catch (Exception f) {
			System.out.println(f);
		}
	}

	public void getGuestList(JLabel lblResultCount) {

		try {
			int count = 0;
			String select = "Select name, rfid, phone, validity, role from guests";

			JTable_Display_Guests.setModel(new DefaultTableModel());
			Connection connection = (Connection) DriverManager.getConnection(
					"jdbc:mysql://@localhost:3306/szakdoga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "1234");
			PreparedStatement st = (PreparedStatement) connection.prepareStatement(select);

			ResultSet rs = st.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) JTable_Display_Guests.getModel();

			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i = 0; i < cols; i++) {
				colName[i] = rsmd.getColumnName(i + 1);

			}
			model.setColumnIdentifiers(colName);
			String name, rfid, phone, validity, role;

			while (rs.next()) {
				name = rs.getString(1);
				rfid = rs.getString(2);
				phone = rs.getString(3);
				validity = rs.getString(4);
				role = rs.getString(5);
				String[] row = { name, rfid, phone, validity, role };
				model.addRow(row);
				count++;
			}
			lblResultCount.setText("Results: " + count);
			/*
			 * st.close(); connection.close();
			 */

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			JOptionPane.showMessageDialog(null, "Database exception! Pls check the database connection!");
		} catch (Exception f) {
			System.out.println(f);
		}
	}
}
