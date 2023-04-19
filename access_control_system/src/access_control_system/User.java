package access_control_system;

public class User {

	private String username;
	private String pswd;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", pswd=" + pswd + "]";
	}
	public User(String username, String pswd) {
		super();
		this.username = username;
		this.pswd = pswd;
	}
	public User() {
		super();
	}
	
	
	
}
