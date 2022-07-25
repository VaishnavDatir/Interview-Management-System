package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Department;
import entities.ServiceResponse;
import utils.MyConstants;

public class DepartmentService {

	public List<Department> getDeptList() {
		List<Department> deptList = new ArrayList<Department>();
		SqlConnection sqlConn = new SqlConnection();
		try {
			String query = "SELECT * FROM " + MyConstants.departmentDb + ";";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int dId = rs.getInt("dId");
				String dName = rs.getString("dName");

				deptList.add(new Department(dId, dName));
			}
			
			rs.close();
			preparedStatement.close();
			
			return deptList;
		} catch (Exception e) {
			return deptList;
		} finally {
			sqlConn.closeConnection();
		}
	}

	public Department getDepartmentById(int Id) {
		SqlConnection sqlConn = new SqlConnection();
		Department dept = new Department();
		try {
			String query = "SELECT * FROM " + MyConstants.departmentDb + " WHERE dId = ?;";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setInt(1, Id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int dId = rs.getInt("dId");
				String dName = rs.getString("dName");
				dept.setdId(dId);
				dept.setdName(dName);
			}
			
			rs.close();
			preparedStatement.close();
			
			return dept;
		} catch (Exception e) {
			return dept;
		} finally {
			sqlConn.closeConnection();
		}
	}
	
	public ServiceResponse createNewDepartment(String dName) {
		SqlConnection sqlConn = new SqlConnection();
		try {
			String query = "INSERT INTO " + MyConstants.departmentDb + "(dName) VALUES(?);";
			PreparedStatement preparedStatement = sqlConn.getCon().prepareStatement(query);
			preparedStatement.setString(1, dName);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			return new ServiceResponse(true, "Successfull", "New department has been added!");
		} catch (Exception e) {
			System.out.println("Error @createNewDepartment: " + e.toString());
			return new ServiceResponse(false, "Error", e.getMessage());
		}finally {
			sqlConn.closeConnection();
		}
	}
	

}
