package access_control_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DatabaseMethods {

	public void addGuest(Guest guest, JLabel lblResultCount) {
		try {

			boolean databaseGuestsNumberIsZero = false;
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

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			JOptionPane.showMessageDialog(null, "Database exception! Pls check the database connection!");
		} catch (Exception f) {
			System.out.println(f);
		} finally {
			lblResultCount.setText("The addition was successful!");
		}
	}
}
