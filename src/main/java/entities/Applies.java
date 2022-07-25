package entities;

public class Applies {
	private String appliedAt;
	private Job job;
	private User user;

	public Applies() {
		super();
	}

	@Override
	public String toString() {
		return "Applies [appliedAt=" + appliedAt + ", job=" + job + ", user=" + user + "]";
	}

	public String getAppliedAt() {
		return appliedAt;
	}

	public void setAppliedAt(String appliedAt) {
		this.appliedAt = appliedAt;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
