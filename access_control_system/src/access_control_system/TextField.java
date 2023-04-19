package access_control_system;

public class TextField {

	private String name;
	private String rfid;
	private String phone;
	private String year;
	private String month;
	private String day;
	
	public TextField() {
		super();
	}
	public TextField(String name, String rfid, String phone, String year, String month, String day) {
		super();
		this.name = name;
		this.rfid = rfid;
		this.phone = phone;
		this.year = year;
		this.month = month;
		this.day = day;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
}
