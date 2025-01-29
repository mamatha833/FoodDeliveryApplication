package com.tap.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDAO;
import com.tap.model.User;
import com.tap.utility.DBConnection;

public class UserDAOImplementation implements UserDAO{

	private static final String INSERT_USER_QUERY = "INSERT INTO `user`(`name`,`username`,`password`,`email`,`phone`,`address`) VALUES (?,?,?,?,?,?)";
	private static final String GET_USER_QUERY = "SELECT * FROM `user` WHERE `userId` = ?";
	private static final String UPDATE_USER_QUERY = "UPDATE `user` SET `name`= ?,`password` = ? ,`phone` = ? ,`address` = ?  WHERE `userId` = ?";
	private static final String DELETE_USER_QUERY = "DELETE FROM `user` WHERE `userId`= ?";
	private static final String GET_ALL_USERS_QUERY = "SELECT * FROM `user`";
	private static final String GET_USER_BY_EMAIL = "SELECT * FROM `user` WHERE `email` = ?";

	private static User user;
	private static ResultSet resultSet;


	@Override
	public void addUser(User user) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_USER_QUERY)){

			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getUsername());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getEmail());
			prepareStatement.setString(5, user.getPhone());
			prepareStatement.setString(6, user.getAddress());
			

			prepareStatement.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}


	@Override
	public User getUser(int userId) {


		try (Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_QUERY)){


			prepareStatement.setInt(1, userId);

			resultSet = prepareStatement.executeQuery();

			resultSet.next();
			user = extractUser(resultSet);	



		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return user;	
	}


	@Override
	public void updateUser(User user) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_USER_QUERY)){

			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getPhone());
			prepareStatement.setString(4, user.getAddress());
			prepareStatement.setInt(5, user.getUserId());

			prepareStatement.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}



	}


	@Override
	public void deleteUser(int userId) {



		try (Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE_USER_QUERY)){
						

			prepareStatement.setInt(1, userId);

			prepareStatement.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}


	}

	@Override
	public List<User> getAllUsers() {

		ArrayList<User> usersList = new ArrayList<>();

		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement()){


			resultSet= statement.executeQuery(GET_ALL_USERS_QUERY);

			while(resultSet.next()) {

				user = extractUser(resultSet);
				usersList.add(user);
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return usersList;	
	}

	@Override
	public User getUserByEmail(String email) {
		
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_BY_EMAIL)){


			prepareStatement.setString(1, email);

			resultSet = prepareStatement.executeQuery();

			if(resultSet.next()) {
			user = extractUser(resultSet);
			}
			else {
				user = null;
			}



		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return user;	
	}
	
	public boolean updateLastLoginDate(int userId, LocalDateTime lastLoginDate) {
	    String query = "UPDATE `user` SET lastLoginDate = ? WHERE userId = ?";
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	         
	        preparedStatement.setTimestamp(1, java.sql.Timestamp.valueOf(lastLoginDate));
	        preparedStatement.setInt(2, userId);

	        int rowsUpdated = preparedStatement.executeUpdate();
	        return rowsUpdated > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public User extractUser(ResultSet resultSet) throws SQLException {

		int userId = resultSet.getInt("userId");
		String name = resultSet.getString("name");
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		String email = resultSet.getString("email");
		String phone = resultSet.getString("phone");
		String address = resultSet.getString("address");
		String role = resultSet.getString("role");

		user =  new User(userId,name,username,password,email,phone,address,role,null,null);

		return user;

	}

}
