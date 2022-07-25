package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.ServiceResponse;
import entities.User;
import utils.MyConstants;

public class UserService {
	DepartmentService deptSer = new DepartmentService();

	// Login the user
	public ServiceResponse login(String uEmailId, String uPassword) {
		User user = new User();
		ServiceResponse serRes = new ServiceResponse();
		SqlConnection sqlConn = new SqlConnection();
		try {
			String query = "SELECT * FROM " + MyConstants.userDb + " WHERE uEmailId = ? AND uPassword = ?;";

			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setString(1, uEmailId);
			preparedStatement.setString(2, String.valueOf((uPassword).hashCode()));

			ResultSet rs = preparedStatement.executeQuery();
			boolean available = rs.next();

			if (available) {
				user = getUserById(rs.getInt("uId"));
				user.setuDepartmentId(rs.getInt("dId"));
				user.setValid(true);
				user.setRole("user");

				serRes.setSuccess(true);
				serRes.setMessage("Sign in successfull!");
				serRes.setObj(user);
			} else {
				serRes.setSuccess(false);
				serRes.setMessage("Sign in unsuccessfull!");
				serRes.setMsgDesc("Please enter correct email id and password");
			}

			rs.close();
			preparedStatement.close();

			return serRes;
		} catch (Exception e) {
			System.out.println(e.toString());
			serRes.setSuccess(false);
			serRes.setMessage("Error while sign in");
			serRes.setMsgDesc(e.getMessage());
			return serRes;
		} finally {
			sqlConn.closeConnection();
		}
	}

	// Register a new use
	public ServiceResponse register(User user) {
		SqlConnection sqlConn = new SqlConnection();
		try {
			String query = "INSERT INTO " + MyConstants.userDb
					+ "(uName, dId, uGender, uMobileNo, uEmailId, uPassword) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setString(1, user.getuName());
			preparedStatement.setInt(2, user.getuDepartmentId());
			preparedStatement.setString(3, user.getuGender());
			preparedStatement.setString(4, user.getuMobileNo());
			preparedStatement.setString(5, user.getuEmailId());
			preparedStatement.setString(6, user.getuPassword());

			preparedStatement.executeUpdate();
			preparedStatement.close();
			return new ServiceResponse(true, "Registration successfull!", "Please sign in to your account");
		} catch (Exception e) {
			System.out.println(e.toString());
			return new ServiceResponse(false, "Registration unsuccessfull", e.getMessage());
		} finally {
			sqlConn.closeConnection();
		}
	}

	// get list of all users
	public List<User> getAllUsers() {
		SqlConnection sqlConn = new SqlConnection();
		List<User> usersList = new ArrayList<User>();
		try {
			String query = "SELECT * FROM " + MyConstants.userDb + ";";

			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				User user = new User();

				user.setuId(rs.getInt("uId"));
				user.setuName(rs.getString("uName"));
				user.setuEmailId(rs.getString("uEmailId"));
				user.setuMobileNo(rs.getString("uMobileNo"));

				usersList.add(user);
			}
			rs.close();
			preparedStatement.close();
			return usersList;
		} catch (Exception e) {
			System.out.println(e.toString());
			return usersList;
		} finally {
			sqlConn.closeConnection();
		}
	}

	// get user by id
	public User getUserById(int id) {
		SqlConnection sqlConn = new SqlConnection();
		User user = new User();
		try {
			String query = "SELECT * FROM " + MyConstants.userDb + " WHERE uId = ?;";

			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				user.setuId(rs.getInt("uId"));
				user.setuName(rs.getString("uName"));
				user.setuEmailId(rs.getString("uEmailId"));
				user.setuMobileNo(rs.getString("uMobileNo"));
				user.setuDob(String.valueOf(rs.getDate("uDob")));
				user.setuLocation(rs.getString("uLocation"));
				user.setuGender(rs.getString("uGender"));
				user.setuAbout(rs.getString("uAbout"));

				user.setuDepartmentId(rs.getInt("dId"));

			}

			rs.close();
			preparedStatement.close();
			return user;
		} catch (Exception e) {
			System.out.println("Error @getUserById: " + e.toString());
			return user;
		} finally {
			sqlConn.closeConnection();
		}
	}

	// To apply user to a job
	public ServiceResponse userApplyToJob(int uid, int jid) {
		SqlConnection sqlConn = new SqlConnection();
		try {
			String query = "INSERT INTO " + MyConstants.applicantDb + "(jId, uId, aAppliedAt) VALUES (?, ?, now())";

			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setInt(1, jid);
			preparedStatement.setInt(2, uid);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			return new ServiceResponse(true, "Successfull", "You have successfully applied to job");
		} catch (Exception e) {
			System.out.println("Error @userApplyToJob: " + e.toString());
			return new ServiceResponse(false, "Error while applying to job. ", e.getMessage());
		} finally {
			sqlConn.closeConnection();
		}
	}

	// For updating user profile
	public ServiceResponse updateUserDetails(User user) {
		SqlConnection sqlConn = new SqlConnection();
		ServiceResponse serRes = new ServiceResponse();
		try {
			String query = "UPDATE " + MyConstants.userDb
					+ " SET uName=?, uEmailId=?, uMobileNo=?, uDob=?, uLocation=?, uGender=?, uAbout=?, dId=? WHERE uId = ?;";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setString(1, user.getuName());
			preparedStatement.setString(2, user.getuEmailId());
			preparedStatement.setString(3, user.getuMobileNo());
			preparedStatement.setDate(4, Date.valueOf(user.getuDob()));
			preparedStatement.setString(5, user.getuLocation());
			preparedStatement.setString(6, user.getuGender());
			preparedStatement.setString(7, user.getuAbout());
			preparedStatement.setInt(8, user.getuDepartmentId());
			preparedStatement.setInt(9, user.getuId());
			preparedStatement.executeUpdate();
			preparedStatement.close();

			serRes.setSuccess(true);
			serRes.setMessage("Update successfull");
			serRes.setMsgDesc("Your profile has been successfully updated");
			serRes.setObj(user);

			return serRes;
		} catch (Exception e) {
			System.out.println(e.toString());

			serRes.setSuccess(true);
			serRes.setMessage("Update unsuccessfull");
			serRes.setMsgDesc(e.getMessage());

			return serRes;
		} finally {
			sqlConn.closeConnection();
		}
	}
}
