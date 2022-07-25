package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Applies;
import entities.Job;
import entities.ServiceResponse;
import entities.User;
import utils.MyConstants;

public class JobService {
	DepartmentService deptSer = new DepartmentService();

	public ServiceResponse createNewJob(Job job) {
		SqlConnection sqlConn = new SqlConnection();
		try {
			String query = "INSERT INTO " + MyConstants.jobsDb
					+ "(jTitle, jDescription, jEleCria, jEduQual, jExpReq, jLocation, jSalary, jLastDT, jCreatedAt, dId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, now(), ?)";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setString(1, job.getjTitle());
			preparedStatement.setString(2, job.getjDescription());
			preparedStatement.setString(3, job.getjEleCria());
			preparedStatement.setString(4, job.getjEduQual());
			preparedStatement.setString(5, job.getjExpReq());
			preparedStatement.setString(6, job.getjLocation());
			preparedStatement.setString(7, job.getjSalary());
			preparedStatement.setDate(8, Date.valueOf(job.getjLastDT()));
			preparedStatement.setInt(9, job.getjDepartmentId());
			preparedStatement.executeUpdate();
			preparedStatement.close();

			return new ServiceResponse(true, "Job Created", "New job successfully created");
		} catch (Exception e) {
			System.out.println(e.toString());
			return new ServiceResponse(false, "Job not created", e.getMessage());
		} finally {
			sqlConn.closeConnection();
		}
	}

	public List<Job> getAllJobs() {
		SqlConnection sqlConn = new SqlConnection();
		List<Job> jobList = new ArrayList<Job>();
		try {
			String query = "SELECT * FROM " + MyConstants.jobsDb + " ORDER BY jCreatedAt DESC;";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				int jId = rs.getInt("jId");
				String jTitle = rs.getString("jTitle");
				String jDescription = rs.getString("jDescription");
				String jEleCria = rs.getString("jEleCria");
				String jEduQual = rs.getString("jEduQual");
				String jExpReq = rs.getString("jExpReq");
				String jLocation = rs.getString("jLocation");
				String jSalary = rs.getString("jSalary");
				String jLastDT = rs.getString("jLastDT");
				String jCreatedAt = rs.getString("jCreatedAt");
				int dId = rs.getInt("dId");

				Job job = new Job();
				job.setjId(jId);
				job.setjTitle(jTitle);
				job.setjDescription(jDescription);
				job.setjEleCria(jEleCria);
				job.setjEduQual(jEduQual);
				job.setjExpReq(jExpReq);
				job.setjLocation(jLocation);
				job.setjSalary(jSalary);
				job.setjLastDT(jLastDT);
				job.setjCreatedAt(jCreatedAt);
				job.setjDepartmentId(Integer.valueOf(dId));

				jobList.add(job);
			}

			rs.close();
			preparedStatement.close();

			return jobList;
		} catch (Exception e) {
			System.out.println(e.toString());
			return jobList;
		} finally {
			sqlConn.closeConnection();
		}
	}

	public Job getJobById(int id) {
		SqlConnection sqlConn = new SqlConnection();
		Job job = new Job();
		try {
			String query = "SELECT * FROM " + MyConstants.jobsDb + " WHERE jId = ?;";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int jId = rs.getInt("jId");
				String jTitle = rs.getString("jTitle");
				String jDescription = rs.getString("jDescription");
				String jEleCria = rs.getString("jEleCria");
				String jEduQual = rs.getString("jEduQual");
				String jExpReq = rs.getString("jExpReq");
				String jLocation = rs.getString("jLocation");
				String jSalary = rs.getString("jSalary");
				String jLastDT = rs.getString("jLastDT");
				String jCreatedAt = rs.getString("jCreatedAt");
				int dId = rs.getInt("dId");

				job.setjId(jId);
				job.setjTitle(jTitle);
				job.setjDescription(jDescription);
				job.setjEleCria(jEleCria);
				job.setjEduQual(jEduQual);
				job.setjExpReq(jExpReq);
				job.setjLocation(jLocation);
				job.setjSalary(jSalary);
				job.setjLastDT(jLastDT);
				job.setjCreatedAt(jCreatedAt);
				job.setjDepartmentId(Integer.valueOf(dId));
			}

			rs.close();
			preparedStatement.close();

			return job;
		} catch (Exception e) {
			System.out.println("Error @getJobById: " + e.toString());
			return job;
		} finally {
			sqlConn.closeConnection();
		}
	}

	public List<Job> getTopNJobs(int limit) {
		SqlConnection sqlConn = new SqlConnection();
		List<Job> jobList = new ArrayList<Job>();
		try {

			String query = "SELECT * FROM " + MyConstants.jobsDb + " ORDER BY jCreatedAt DESC LIMIT ?;";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setInt(1, limit);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				int jId = rs.getInt("jId");
				String jTitle = rs.getString("jTitle");
				String jDescription = rs.getString("jDescription");
				String jEleCria = rs.getString("jEleCria");
				String jEduQual = rs.getString("jEduQual");
				String jExpReq = rs.getString("jExpReq");
				String jLocation = rs.getString("jLocation");
				String jSalary = rs.getString("jSalary");
				String jLastDT = rs.getString("jLastDT");
				String jCreatedAt = rs.getString("jCreatedAt");
				int dId = rs.getInt("dId");

				Job job = new Job();
				job.setjId(jId);
				job.setjTitle(jTitle);
				job.setjDescription(jDescription);
				job.setjEleCria(jEleCria);
				job.setjEduQual(jEduQual);
				job.setjExpReq(jExpReq);
				job.setjLocation(jLocation);
				job.setjSalary(jSalary);
				job.setjLastDT(jLastDT);
				job.setjCreatedAt(jCreatedAt);
				job.setjDepartmentId(Integer.valueOf(dId));

				jobList.add(job);
			}

			rs.close();
			preparedStatement.close();

			return jobList;
		} catch (Exception e) {
			System.out.println(e.toString());
			return jobList;
		} finally {
			sqlConn.closeConnection();
		}
	}

	public List<Job> getActiveJobs() {
		SqlConnection sqlConn = new SqlConnection();
		List<Job> jobList = new ArrayList<Job>();
		try {

			String query = "SELECT * FROM " + MyConstants.jobsDb + " WHERE jLastDt >=now()  ORDER BY jLastDT;";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				int jId = rs.getInt("jId");
				String jTitle = rs.getString("jTitle");
				String jDescription = rs.getString("jDescription");
				String jEleCria = rs.getString("jEleCria");
				String jEduQual = rs.getString("jEduQual");
				String jExpReq = rs.getString("jExpReq");
				String jLocation = rs.getString("jLocation");
				String jSalary = rs.getString("jSalary");
				String jLastDT = rs.getString("jLastDT");
				String jCreatedAt = rs.getString("jCreatedAt");
				int dId = rs.getInt("dId");

				Job job = new Job();
				job.setjId(jId);
				job.setjTitle(jTitle);
				job.setjDescription(jDescription);
				job.setjEleCria(jEleCria);
				job.setjEduQual(jEduQual);
				job.setjExpReq(jExpReq);
				job.setjLocation(jLocation);
				job.setjSalary(jSalary);
				job.setjLastDT(jLastDT);
				job.setjCreatedAt(jCreatedAt);
				job.setjDepartmentId(Integer.valueOf(dId));

				jobList.add(job);
			}

			rs.close();
			preparedStatement.close();

			return jobList;
		} catch (Exception e) {
			System.out.println(e.toString());
			return jobList;
		} finally {
			sqlConn.closeConnection();
		}
	}

	public List<Job> getAllJobsByDept(int dId) {
		SqlConnection sqlConn = new SqlConnection();
		List<Job> jobList = new ArrayList<Job>();
		try {
			String query = "SELECT * FROM " + MyConstants.jobsDb + " WHERE dId = ? ORDER BY jCreatedAt DESC;";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setInt(1, dId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				int jId = rs.getInt("jId");
				String jTitle = rs.getString("jTitle");
				String jDescription = rs.getString("jDescription");
				String jEleCria = rs.getString("jEleCria");
				String jEduQual = rs.getString("jEduQual");
				String jExpReq = rs.getString("jExpReq");
				String jLocation = rs.getString("jLocation");
				String jSalary = rs.getString("jSalary");
				String jLastDT = rs.getDate("jLastDT").toString();
				String jCreatedAt = rs.getDate("jCreatedAt").toString();
				int jdId = rs.getInt("dId");

				Job job = new Job();
				job.setjId(jId);
				job.setjTitle(jTitle);
				job.setjDescription(jDescription);
				job.setjEleCria(jEleCria);
				job.setjEduQual(jEduQual);
				job.setjExpReq(jExpReq);
				job.setjLocation(jLocation);
				job.setjSalary(jSalary);
				job.setjLastDT(jLastDT);
				job.setjCreatedAt(jCreatedAt);
				job.setjDepartmentId(Integer.valueOf(jdId));

				jobList.add(job);
			}

			rs.close();
			preparedStatement.close();

			return jobList;
		} catch (Exception e) {
			System.out.println(e.toString());
			return jobList;
		} finally {
			sqlConn.closeConnection();
		}
	}

	public boolean isUserAppliedToJob(int uId, int jId) {
		SqlConnection sqlConn = new SqlConnection();
		try {
			String query = "SELECT * FROM " + MyConstants.applicantDb + " WHERE jId = ? AND uId = ?;";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setInt(1, jId);
			preparedStatement.setInt(2, uId);
			ResultSet rs = preparedStatement.executeQuery();
			boolean available = rs.next();
			rs.close();
			preparedStatement.close();
			if (available) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("ERROR @isUserAppliedToJob: " + e.toString());
			return false;
		} finally {
			sqlConn.closeConnection();
		}
	}

	public List<Applies> getAppliedUsers(int jId) {
		SqlConnection sqlConn = new SqlConnection();
		List<Applies> appliedUsersList = new ArrayList<Applies>();
		try {
			String query = "SELECT * FROM " + MyConstants.applicantDb
					+ " a JOIN users_db u ON u.uId = a.uId WHERE jId = ? ORDER BY aAppliedAt;";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setInt(1, jId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Applies applies = new Applies();
				applies.setAppliedAt(rs.getDate("aAppliedAt").toString());

				UserService userServ = new UserService();
				User user = userServ.getUserById(rs.getInt("uId"));
				applies.setUser(user);

				appliedUsersList.add(applies);
			}

			rs.close();
			preparedStatement.close();

			return appliedUsersList;
		} catch (Exception e) {
			System.out.println(e.toString());
			return appliedUsersList;
		} finally {
			sqlConn.closeConnection();
		}
	}
}
