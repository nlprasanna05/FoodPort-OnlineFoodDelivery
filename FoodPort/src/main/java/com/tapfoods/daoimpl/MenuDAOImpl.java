package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.tapfoods.dao.MenuDAO;
import com.tapfoods.dbUtils.DBUtils;
import com.tapfoods.model.Menu;

public class MenuDAOImpl implements MenuDAO{
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private static final String ADD_MENU="insert into menu(`menuId`,`restaurantId`,`menuName`,`price`,`description`,`isAvailable`,`imgPath`)" + "values(?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_MENUITEMS="select * from `menu`";
	private static final String SELECT_SPECIFIC_MENUITEM="select * from `menu` where `menuId`=?";
	private static final String UPDATE_MENUITEM="update `menu` set `restaurantId`=?,`menuName`=?,`price`=?,`description`,`isAvailable`,`imgPath`=? where `menuId`=?";
	private static final String DELETE_MENUITEM="delete from `menu` where `menuId`=?";
	private static final String GET_MENU_ON_RESTAURANTID="select * from `menu` where `restaurantId`=?";
	int status=0;
	ArrayList<Menu> menuList=new ArrayList<Menu>();
	Menu menu;
	
	public MenuDAOImpl()
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
	public int addMenu(Menu m) {
		try
		{
			pstmt=con.prepareStatement(ADD_MENU);
			pstmt.setInt(1, m.getMenuId());
			pstmt.setInt(2, m.getRestaurantId());
			pstmt.setString(3, m.getMenuName());
			pstmt.setFloat(4, m.getPrice());
			pstmt.setString(5, m.getDescription());
			pstmt.setString(6, m.getIsAvailable());
			pstmt.setString(7, m.getImgPath());
			status=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<Menu> getAllMenuItems() {
		try
		{
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(SELECT_ALL_MENUITEMS);
			menuList=extractMenuFromResultSet(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return menuList;
	}

	@Override
	public Menu getSpecificMenuItem(int menuId) {
		try
		{
			pstmt=con.prepareStatement(SELECT_SPECIFIC_MENUITEM);
			pstmt.setInt(1, menuId);
			resultSet=pstmt.executeQuery();
			menuList=extractMenuFromResultSet(resultSet);
			menu=menuList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return menu;
	}
	
	@Override
	public int updateMenuItem(Menu m) {
		try
		{
			pstmt=con.prepareStatement(UPDATE_MENUITEM);
			pstmt.setInt(1, m.getRestaurantId());
			pstmt.setString(2, m.getMenuName());
			pstmt.setFloat(3, m.getPrice());
			pstmt.setString(4, m.getDescription());
			pstmt.setString(5, m.getIsAvailable());
			pstmt.setString(6, m.getImgPath());
			pstmt.setInt(7, m.getMenuId());
			status=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteMenuItem(int menuId) {
		try
		{
			pstmt=con.prepareStatement(DELETE_MENUITEM);
			pstmt.setInt(1, menuId);
			status=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	

	@Override
	public ArrayList<Menu> getMenuOnRestaurantId(int restaurantId) {
		try
		{
			pstmt=con.prepareStatement(GET_MENU_ON_RESTAURANTID);
			pstmt.setInt(1, restaurantId);
			resultSet=pstmt.executeQuery();
			menuList=extractMenuFromResultSet(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return menuList;
	}
	
	public ArrayList<Menu> extractMenuFromResultSet(ResultSet resultSet)
	{
		try
		{
			while(resultSet.next()) 
			{
				menuList.add(new Menu(resultSet.getInt("menuId"),
				resultSet.getInt("restaurantId"),
				resultSet.getString("menuName"),
				resultSet.getFloat("price"),
				resultSet.getString("description"),
				resultSet.getString("isAvailable"),
				resultSet.getString("imgPath")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return menuList;
	}

}
