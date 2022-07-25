package entities;

public class Job {
	private int jId;
	private String jTitle;
	private String jDescription;
	private String jEleCria;
	private String jEduQual;
	private String jExpReq;
	private String jLocation;
	private String jSalary;
	private String jLastDT;
	private String jCreatedAt;
	private int jDepartmentId;

	public int getjId() {
		return jId;
	}

	public void setjId(int jId) {
		this.jId = jId;
	}

	public String getjTitle() {
		return jTitle;
	}

	public void setjTitle(String jTitle) {
		this.jTitle = jTitle;
	}

	public String getjDescription() {
		return jDescription;
	}

	public void setjDescription(String jDescription) {
		this.jDescription = jDescription;
	}

	public String getjEleCria() {
		return jEleCria;
	}

	public void setjEleCria(String jEleCria) {
		this.jEleCria = jEleCria;
	}

	public String getjEduQual() {
		return jEduQual;
	}

	public void setjEduQual(String jEduQual) {
		this.jEduQual = jEduQual;
	}

	public String getjExpReq() {
		return jExpReq;
	}

	public void setjExpReq(String jExpReq) {
		this.jExpReq = jExpReq;
	}

	public String getjLocation() {
		return jLocation;
	}

	public void setjLocation(String jLocation) {
		this.jLocation = jLocation;
	}

	public String getjSalary() {
		return jSalary;
	}

	public void setjSalary(String jSalary) {
		this.jSalary = jSalary;
	}

	public String getjLastDT() {
		return jLastDT;
	}

	public void setjLastDT(String jLastDT) {
		this.jLastDT = jLastDT;
	}

	public String getjCreatedAt() {
		return jCreatedAt;
	}

	public void setjCreatedAt(String jCreatedAt) {
		this.jCreatedAt = jCreatedAt;
	}

	public int getjDepartmentId() {
		return jDepartmentId;
	}

	public void setjDepartmentId(int jDepartmentId) {
		this.jDepartmentId = jDepartmentId;
	}

	public Job() {
		super();
	}

	@Override
	public String toString() {
		return "Jobs [jId=" + jId + ", jTitle=" + jTitle + ", jDescription=" + jDescription + ", jEleCria=" + jEleCria
				+ ", jEduQual=" + jEduQual + ", jExpReq=" + jExpReq + ", jLocation=" + jLocation + ", jSalary="
				+ jSalary + ", jLastDT=" + jLastDT + ", jCreatedAt=" + jCreatedAt + ", jDepartmentId=" + jDepartmentId
				+ "]";
	}
}
