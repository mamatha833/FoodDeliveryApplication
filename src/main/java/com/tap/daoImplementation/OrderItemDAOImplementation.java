package com.tap.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.utility.DBConnection;

public class OrderItemDAOImplementation implements OrderItemDAO {

	private static final String INSERT_ORDER_ITEM_QUERY = "INSERT INTO `orderItem`(`orderId`,`menuId`,`quantity`,`totalPrice`) VALUES (?,?,?,?)";
	private static final String GET_ORDER_ITEM_QUERY = "SELECT * FROM `orderItem` WHERE `orderItemId` = ?";
	private static final String GET_ORDER_ITEM_BY_ORDERID_QUERY = "SELECT * FROM `orderItem` WHERE `orderId` = ?";
	private static final String UPDATE_ORDER_ITEM_QUERY = "UPDATE `orderItem` SET `orderId` = ? , `menuId` = ? , `quantity` = ? , `totalPrice` = ? WHERE `orderItemId` = ? ";
	private static final String DELETE_ORDER_ITEM_QUERY = "DELETE FROM `orderItem` WHERE `orderItemId`= ?";
	private static final String GET_ALL_ORDER_ITEMS_QUERY = "SELECT * FROM `orderItem`";
	

	private static OrderItem orderItem;
	private static ResultSet resultSet;

	@Override
	public void addOrderItem(OrderItem orderItem) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ORDER_ITEM_QUERY)){

			prepareStatement.setInt(1, orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setInt(3, orderItem.getQuantity());
			prepareStatement.setFloat(4, orderItem.getTotalPrice());

			prepareStatement.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}



	@Override
	public OrderItem getOrderItem(int orderItemId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDER_ITEM_QUERY)){

			prepareStatement.setInt(1, orderItemId);

			resultSet = prepareStatement.executeQuery();

			resultSet.next();

			orderItem = extractOrderItem(resultSet);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return orderItem;
	}
	
	
	@Override
	public List<OrderItem> getOrderItemByOrderId(int orderId) {
		
		ArrayList<OrderItem> orderItemsList = new ArrayList<OrderItem>();
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDER_ITEM_BY_ORDERID_QUERY)){

			prepareStatement.setInt(1, orderId);

			resultSet = prepareStatement.executeQuery();

			while(resultSet.next()) {

				orderItem = extractOrderItem(resultSet);
				orderItemsList.add(orderItem);
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return orderItemsList;
	}




	@Override
	public void updateOrderItem(OrderItem orderItem) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_ORDER_ITEM_QUERY)){

			prepareStatement.setInt(1, orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setInt(3, orderItem.getQuantity());
			prepareStatement.setFloat(4, orderItem.getTotalPrice());
			prepareStatement.setInt(5, orderItem.getOrderItemId());

			prepareStatement.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}



	@Override
	public void deleteOrderItem(int orderItemId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE_ORDER_ITEM_QUERY)){

			prepareStatement.setInt(1,orderItemId);

			prepareStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<OrderItem> getAllOrderItems() {

		ArrayList<OrderItem> orderItemsList = new ArrayList<OrderItem>();

		try(Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement()){

			resultSet = statement.executeQuery(GET_ALL_ORDER_ITEMS_QUERY);
			while(resultSet.next()) {

				orderItem = extractOrderItem(resultSet);
				orderItemsList.add(orderItem);
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return orderItemsList;
	}


	public OrderItem extractOrderItem(ResultSet resultSet) throws SQLException {

		int orderItemId = resultSet.getInt("orderItemId");
		int orderId = resultSet.getInt("orderId");
		int menuId = resultSet.getInt("menuId");
		int quantity = resultSet.getInt("quantity");
		float totalPrice = resultSet.getFloat("totalPrice");

		orderItem = new OrderItem(orderItemId, orderId, menuId, quantity, totalPrice);

		return  orderItem;


	}



	

}
