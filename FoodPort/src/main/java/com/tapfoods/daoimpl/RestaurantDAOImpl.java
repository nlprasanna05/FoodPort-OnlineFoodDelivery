package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.dbUtils.DBUtils;
import com.tapfoods.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO{
	
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private static final String ADD_RESTAURANT="insert into `restaurant`(`restaurantId`,`restaurantName`,`deliveryTime`,`cuisineType`,`address`,`ratings`,`isActive`,`adminId`,`imgPath`)"+"values(?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_RESTAURANTS="select * from `restaurant`";
	private static final String SELECT_SPECIFIC_RESTAURANTS="select * from `restaurant` where `restaurantId`=?";
	private static final String UPDATE_RESTAURANT="update `restaurant` set `restaurantName`=?,`deliveryTime`=?,`cuisineType`=?,`address`=?,`ratings`=?,`isActive`=?,`adminId`=?,`imgPath`=? where `restaurantId`=?";
	private static final String DELETE_RESTAURANT="delete from `restaurant` where `restaurantId`=?";
	int status=0;
	ArrayList<Restaurant> restaurantList=new ArrayList<Restaurant>();
	Restaurant restaurant;
	
	public RestaurantDAOImpl()
	{
		try
		{
			con=DBUtils.myConnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public int addRestaurant(Restaurant r) {
		try
		{
			pstmt=con.prepareStatement(ADD_RESTAURANT);	
			pstmt.setInt(1, r.getRestaurantId());
			pstmt.setString(2, r.getRestaurantName());
			pstmt.setInt(3, r.getDeliveryTime());
			pstmt.setString(4, r.getCuisineType());
			pstmt.setString(5, r.getAddress());
			pstmt.setFloat(6, r.getRatings());
			pstmt.setString(7, r.getIsActive());
			pstmt.setInt(8, r.getAdminId());
			pstmt.setString(9, r.getImgPath());
			status=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<Restaurant> getAllRestaurants() {
		try
		{
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(SELECT_ALL_RESTAURANTS);
			restaurantList=extractAllRestaurantsFromrestaurantList(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return restaurantList;
	}

	@Override
	public Restaurant getSpecificRestaurant(int restaurantId) {
		try
		{
			pstmt=con.prepareStatement(SELECT_SPECIFIC_RESTAURANTS);
			pstmt.setInt(1, restaurantId);
			resultSet=pstmt.executeQuery();
			restaurantList=extractAllRestaurantsFromrestaurantList(resultSet);
			restaurant=restaurantList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return restaurant;
	}

	@Override
	public int updateRestaurant(Restaurant r) {
		try
		{
			pstmt=con.prepareStatement(UPDATE_RESTAURANT);
			pstmt=con.prepareStatement(ADD_RESTAURANT);	
			pstmt.setString(1, r.getRestaurantName());
			pstmt.setInt(2, r.getDeliveryTime());
			pstmt.setString(3, r.getCuisineType());
			pstmt.setString(4, r.getAddress());
			pstmt.setFloat(5, r.getRatings());
			pstmt.setString(6, r.getIsActive());
			pstmt.setInt(7, r.getAdminId());
			pstmt.setString(8, r.getImgPath());
			pstmt.setInt(9, r.getRestaurantId());
			status=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteRestaurant(int restaurantId) {
		try
		{
			pstmt=con.prepareStatement(DELETE_RESTAURANT);
			pstmt.setInt(1, restaurantId);
			status=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<Restaurant> extractAllRestaurantsFromrestaurantList(ResultSet resultSet)
	{
		try
		{
			while(resultSet.next())
			{
				restaurantList.add(new Restaurant(resultSet.getInt("restaurantId"),
				resultSet.getString("restaurantName"),
				resultSet.getInt("deliveryTime"),
				resultSet.getString("cuisineType"),
				resultSet.getString("address"),
				resultSet.getFloat("ratings"),
				resultSet.getString("isActive"),
				resultSet.getInt("adminId"),
				resultSet.getString("imgPath")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return restaurantList;
	}
}
