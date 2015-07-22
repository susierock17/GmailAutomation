package utils;

public class User {
	
	private String username;
	private String password;
	
	/*private final String USERNAME1 = "user1automationtest@gmail.com";
	private final String PASSWORD1 = "iamuser1";
	private final String USERNAME2 = "user2automationtest@gmail.com";
	private final String PASSWORD2 = "iamuser2";
	private final String USERNAME3 = "user3automationtest@gmail.com";
	private final String PASSWORD3 = "iamuser3";*/
	
	public User(String username,String password) {
		this.username=username;
		this.password=password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
