package entities;


public class User {
	private int uId;
	private String uName;
	private String uEmailId;
	private String uMobileNo;
	private String uDob;
	private String uLocation;
	private String uGender;
	private String uPassword;
	private String uAbout;
	private int uDepartmentId;
	private boolean valid;
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuEmailId() {
		return uEmailId;
	}

	public void setuEmailId(String uEmailId) {
		this.uEmailId = uEmailId;
	}

	public String getuMobileNo() {
		return uMobileNo;
	}

	public void setuMobileNo(String uMobileNo) {
		this.uMobileNo = uMobileNo;
	}

	public String getuDob() {
		return uDob;
	}

	public void setuDob(String uDob) {
		this.uDob = uDob;
	}

	public String getuLocation() {
		return uLocation;
	}

	public void setuLocation(String uLocation) {
		this.uLocation = uLocation;
	}

	public String getuGender() {
		return uGender;
	}

	public void setuGender(String uGender) {
		this.uGender = uGender;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getuAbout() {
		return uAbout;
	}

	public void setuAbout(String uAbout) {
		this.uAbout = uAbout;
	}

	public int getuDepartmentId() {
		return uDepartmentId;
	}

	public void setuDepartmentId(int uDepartmentId) {
		this.uDepartmentId = uDepartmentId;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/*
	 * public List<Job> getJobs() { return jobs; }
	 * 
	 * public void setJobs(List<Job> jobs) { this.jobs = jobs; }
	 */



	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", uName=" + uName + ", uEmailId=" + uEmailId + ", uMobileNo=" + uMobileNo
				+ ", uDob=" + uDob + ", uLocation=" + uLocation + ", uGender=" + uGender + ", uPassword=" + uPassword
				+ ", uAbout=" + uAbout + ", uDepartmentId=" + uDepartmentId + ", valid=" + valid
				+ ", role=" + role + "]";
	}

	

}
