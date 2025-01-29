package com.tap.daoImplementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderDAO;
import com.tap.model.Order;
import com.tap.utility.DBConnection;

public class OrderDAOImplementation implements OrderDAO {

	private static final String INSERT_ORDER_QUERY = "INSERT INTO `order`(`userId`,`restaurantId`,`totalAmount`,`address`,`phone`,`paymentMode`) VALUES (?,?,?,?,?,?)";
	private static final String GET_ORDER_QUERY = "SELECT * FROM `order` WHERE `orderId` = ?";
	private static final String GET_ORDER_BY_USERID_QUERY = "SELECT * FROM `order` WHERE `userId` = ?";
	private static final String UPDATE_ORDER_QUERY = "UPDATE `order` SET `userId` = ? ,`restaurantId` = ? , `totalAmount` = ? , `status` = ?,`address` = ?, `phone` = ?, `paymentMode` = ? WHERE `orderId` = ? ";
	private static final String DELETE_ORDER_QUERY = "DELETE FROM `order` WHERE `orderId`= ?";
	private static final String GET_ALL_ORDERS_QUERY = "SELECT * FROM `order`";

	private static Order order;
	private static ResultSet resultSet;

	@Override
	public int addOrder(Order order) {

		int orderId = -1;

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS)){

			prepareStatement.setInt(1,order.getUserId());
			prepareStatement.setInt(2,order.getRestaurantId());
			prepareStatement.setFloat(3, order.getTotalAmount());
			prepareStatement.setString(4,order.getAddress());
			prepareStatement.setString(5, order.getPhone());
			prepareStatement.setString(6, order.getPaymentMode());

			prepareStatement.executeUpdate();

			ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				orderId = generatedKeys.getInt(1); // Retrieve the generated orderId
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return orderId;

	}

	@Override
	public Order getOrder(int orderId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDER_QUERY)){

			prepareStatement.setInt(1, orderId);

			resultSet = prepareStatement.executeQuery();
			resultSet.next();

			order = extractOrder(resultSet);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	
	
	@Override
	public List<Order> getOrderByUserId(int userId) {
		
		ArrayList<Order> ordersList = new ArrayList<Order>(); 

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement( GET_ORDER_BY_USERID_QUERY)){

			prepareStatement.setInt(1, userId);

			resultSet = prepareStatement.executeQuery();
			

			while(resultSet.next()) {

				order = extractOrder(resultSet);
				ordersList.add(order);
			}


		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return  ordersList;
	}



	@Override
	public void updateOrder(Order order) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_ORDER_QUERY)){

			prepareStatement.setInt(1,order.getUserId());
			prepareStatement.setInt(2, order.getRestaurantId());
			prepareStatement.setFloat(3,order.getTotalAmount());
			prepareStatement.setString(4, order.getStatus());
			prepareStatement.setString(5, order.getAddress());
			prepareStatement.setString(6, order.getPhone());
			prepareStatement.setString(7, order.getPaymentMode());
			prepareStatement.setInt(8,order.getOrderId());

			prepareStatement.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}



	@Override
	public void deleteOrder(int orderId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE_ORDER_QUERY)){

			prepareStatement.setInt(1, orderId);

			prepareStatement.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}


	@Override
	public List<Order> getAllOrders() {

		ArrayList<Order> ordersList = new ArrayList<Order>(); 

		try(Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement()){

			resultSet = statement.executeQuery(GET_ALL_ORDERS_QUERY);

			while(resultSet.next()) {

				order = extractOrder(resultSet);
				ordersList.add(order);
			}


		}
		catch(SQLException e) {
			e.printStackTrace();
		}


		return ordersList;
	}

	public Order extractOrder(ResultSet resultSet) throws SQLException{

		int orderId = resultSet.getInt("orderId");
		int userId = resultSet.getInt("userId");
		int restaurantId = resultSet.getInt("restaurantId");
		Date orderDate = resultSet.getDate("orderDate");
		float totalAmount = resultSet.getFloat("totalAmount");
		String status = resultSet.getString("status");
		String address = resultSet.getString("address");
		String phone = resultSet.getString("phone");
		String paymentMode = resultSet.getString("paymentMode");

		order = new Order(orderId, userId, restaurantId, orderDate, totalAmount,status, address, phone, paymentMode);


		return order;
	}

}
