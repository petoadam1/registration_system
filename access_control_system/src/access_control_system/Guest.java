package access_control_system;

public class Guest {

	private String name;
	private String rfid;
	private int phone;
	private String validity;
	private String role;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getYear() {
		return validity.substring(0, 4);
	}
	public String getMonth() {
		return validity.substring(5, 7);
	}
	public String getDay() {
		return validity.substring(8, 10);
	}
	
	public Guest(String name, String rfid, int phone, String validity, String role) {
		super();
		this.name = name;
		this.rfid = rfid;
		this.phone = phone;
		this.validity = validity;
		this.role = role;
	}
	@Override
	public String toString() {
		return "Guest [name=" + name + ", rfid=" + rfid + ", phone=" + phone + ", validity=" + validity + ", role="
				+ role + "]";
	}
	public Guest() {
		super();
	}
	
	
	
}
