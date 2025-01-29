package com.tap.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.utility.DBConnection;

public  class MenuDAOImplementation implements MenuDAO {

	private static final String INSERT_MENU_QUERY = "INSERT INTO `menu`(`restaurantId`,`itemName`,`description`,`price`,`ratings`,`isAvailable`,`imagePath`) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_MENU_QUERY = "SELECT * FROM `menu` WHERE `menuId` = ?";
	private static final String UPDATE_MENU_QUERY = "UPDATE `menu` SET `restaurantId`= ? ,`itemName` = ? ,`description` = ? , `price` = ? , `ratings` = ? , `isAvailable` = ? , `imagePath` = ? WHERE `menuId` = ?";
	private static final String DELETE_MENU_QUERY = "DELETE FROM `menu` WHERE `menuId`= ?";
	private static final String GET_ALL_MENU_QUERY = "SELECT * FROM `menu`";
	private static final String GET_RESTAURANT_MENU_QUERY = "SELECT * FROM `menu` WHERE `restaurantId` = ?";

	private static Menu menu;
	private static ResultSet resultSet;



	@Override
	public int addMenu(Menu menu) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_MENU_QUERY)){

			prepareStatement.setInt(1,menu.getRestaurantId() );
			prepareStatement.setString(2,menu.getItemName() );
			prepareStatement.setString(3,menu.getDescription() );
			prepareStatement.setFloat(4,menu.getPrice() );
			prepareStatement.setFloat(5,menu.getRatings() );
			prepareStatement.setBoolean(6,menu.getIsAvailable() );
			prepareStatement.setString(7,menu.getImagePath() );

			int i = prepareStatement.executeUpdate();
			return i;


		}
		catch(SQLException e) {
			e.printStackTrace();

		}
		return -1;

	}



	@Override
	public Menu getMenu(int menuId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_MENU_QUERY)){

			prepareStatement.setInt(1, menuId);

			resultSet = prepareStatement.executeQuery();
			resultSet.next();

			menu = 	extractMenu(resultSet);


		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return menu;
	}


	@Override
	public void updateMenu(Menu menu) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_MENU_QUERY)){

			prepareStatement.setInt(1,menu.getRestaurantId());
			prepareStatement.setString(2,menu.getItemName());
			prepareStatement.setString(3,menu.getDescription());
			prepareStatement.setFloat(4,menu.getPrice());
			prepareStatement.setFloat(5,menu.getRatings());
			prepareStatement.setBoolean(6,menu.getIsAvailable());
			prepareStatement.setString(7,menu.getImagePath());
			prepareStatement.setInt(8,menu.getMenuId());

			prepareStatement.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}



	@Override
	public void deleteMenu(int menuId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE_MENU_QUERY)){

			prepareStatement.setInt(1, menuId);
			prepareStatement.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace();

		}

	}


	@Override
	public List<Menu> getAllMenu() {
		ArrayList<Menu> menuList  = new ArrayList<Menu>();
		
		try(Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement()){
		

			resultSet =  statement.executeQuery(GET_ALL_MENU_QUERY);

			while(resultSet.next()) {

				menu = extractMenu(resultSet);

				menuList.add(menu);
			}



		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return menuList;
	}

	public Menu extractMenu(ResultSet resultSet) throws SQLException{


		int menuId = resultSet.getInt("menuId");
		int restaurantId = resultSet.getInt("restaurantId");
		String itemName = resultSet.getString("ItemName");
		String description = resultSet.getString("description");
		float price = resultSet.getFloat("price");
		float ratings = resultSet.getFloat("ratings");
		boolean isAvailable = resultSet.getBoolean("isAvailable");
		String imagePath = resultSet.getString("imagePath");

		menu = new Menu(menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath);

		return menu;

	}



	@Override
	public List<Menu> getMenuListByRestaurantId(int restaurantId) {
		
		ArrayList<Menu> menuList  = new ArrayList<Menu>();
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement( GET_RESTAURANT_MENU_QUERY )){
	

			prepareStatement.setInt(1, restaurantId);
			
			resultSet = prepareStatement.executeQuery();

			while(resultSet.next()) {

				menu = extractMenu(resultSet);
				menuList.add(menu);
			}



		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}

}
