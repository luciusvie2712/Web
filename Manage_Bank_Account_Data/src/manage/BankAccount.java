package manage;

public class BankAccount {
	private String fullName;
	private String phoneNumber;
	private String cccd;
	private String password;
	private String gender;
	private int sodu;
	
	public BankAccount(String fullName, String phoneNumber, String cccd, String password, String gender, int sodu) {
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.cccd = cccd;
		this.password = password;
		this.gender = gender;
		this.sodu = sodu;
	}

	public int getSodu() {
		return sodu;
	}

	public void setSodu(int sodu) {
		this.sodu = sodu;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
