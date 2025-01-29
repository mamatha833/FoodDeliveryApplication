package com.tap.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

public class RestaurantDAOImplementation implements RestaurantDAO{

	private static final String INSERT_RESTAURANT_QUERY = "INSERT INTO `restaurant`(`name`,`address`,`phone`,`rating`,`cusineType`,`isActive`,`eta`,`adminUserId`,`imagePath`) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String GET_RESTAURANT_QUERY = "SELECT * FROM `restaurant` WHERE `restaurantId` = ?";
	private static final String UPDATE_RESTAURANT_QUERY = "UPDATE `restaurant` SET `name`= ?, `address` = ? , `phone` = ? , `rating` = ? , `cusineType` = ? , `isActive` = ? , `eta` = ? , `adminUserId` = ? , `imagePath` = ? WHERE `restaurantId` = ?";
	private static final String DELETE_RESTAURANT_QUERY = "DELETE FROM `restaurant` WHERE `restaurantId`= ?";
	private static final String GET_ALL_RESTAURANTS_QUERY = "SELECT * FROM `restaurant`";

	private static Restaurant restaurant;
	private static ResultSet resultSet;

	@Override
	public void addRestaurant(Restaurant restaurant) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_RESTAURANT_QUERY)){

			prepareStatement.setString(1,restaurant.getName());
			prepareStatement.setString(2,restaurant.getAddresss());
			prepareStatement.setString(3,restaurant.getPhone());
			prepareStatement.setFloat(4,restaurant.getRating() );
			prepareStatement.setString(5,restaurant.getCusineType());
			prepareStatement.setBoolean(6,restaurant.getIsActive());
			prepareStatement.setString(7,restaurant.getEta());
			prepareStatement.setInt(8,restaurant.getAdminUserId());
			prepareStatement.setString(9,restaurant.getImagePath());


			prepareStatement.executeUpdate();
			

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}



	@Override
	public Restaurant getRestaurant(int restaurantId) {


		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement =  connection.prepareStatement(GET_RESTAURANT_QUERY)) {

			prepareStatement.setInt(1, restaurantId);

			resultSet = prepareStatement.executeQuery();
			resultSet.next();

			restaurant = extractRestaurant(resultSet);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return restaurant;
	}



	@Override
	public void updateRestaurant(Restaurant restaurant) {
		

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_RESTAURANT_QUERY) ){

			
			prepareStatement.setString(1,restaurant.getName());
			prepareStatement.setString(2,restaurant.getAddresss());
			prepareStatement.setString(3,restaurant.getPhone());
			prepareStatement.setFloat(4,restaurant.getRating() );
			prepareStatement.setString(5,restaurant.getCusineType());
			prepareStatement.setBoolean(6,restaurant.getIsActive());
			prepareStatement.setString(7,restaurant.getEta());
			prepareStatement.setInt(8,restaurant.getAdminUserId());
			prepareStatement.setString(9,restaurant.getImagePath());
			prepareStatement.setInt(10, restaurant.getRestaurantId());
			
			prepareStatement.executeUpdate();
			

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void deleteRestaurant(int restaurantId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE_RESTAURANT_QUERY)){

			prepareStatement.setInt(1, restaurantId);

			prepareStatement.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public List<Restaurant> getAllRestaurants() {

		ArrayList<Restaurant> restaurantList = new ArrayList<>();

		try(Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement()){

			resultSet = statement.executeQuery(GET_ALL_RESTAURANTS_QUERY);
			
			while(resultSet.next()) {

				restaurant = extractRestaurant(resultSet);
				restaurantList.add(restaurant);

			}


		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return restaurantList;
	}



	public Restaurant extractRestaurant(ResultSet resultSet) throws SQLException {

		int restaurantId = resultSet.getInt("restaurantId");
		String name = resultSet.getString("name");
		String address = resultSet.getString("address");
		String phone = resultSet.getString("phone");
		Float rating = resultSet.getFloat("rating");
		String cusineType = resultSet.getString("cusineType");
		boolean isActive = resultSet.getBoolean("isActive");
		String eta = resultSet.getString("eta");
		int adminUserId = resultSet.getInt("adminUserId");
		String imagePath = resultSet.getString("imagePath");


		restaurant=new Restaurant(restaurantId, name, address, phone, rating, cusineType, isActive, eta, adminUserId, imagePath);

		return restaurant;

	}

}
