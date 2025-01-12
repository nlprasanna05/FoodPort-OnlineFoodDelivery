package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.OrderHistoryDAO;
import com.tapfoods.dbUtils.DBUtils;
import com.tapfoods.model.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO{

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private static final String ADD_ORDERHISTORY="insert into orderhistory(`orderId`,`userId`,`totalAmount`,`status`)" + "values(?,?,?,?)";
	private static final String SELECT_ALL_ORDERSHISTORY="select * from `orderhistory`";
	private static final String SELECT_SPECIFIC_ORDERHISTORY="select * from `orderhistory` where `orderHistoryId`=?";
	private static final String SELECT_SPECIFIC_ORDERHISTORY_ON_USERID="select * from `orderhistory` where `userId`=?";
	int status=0;
	ArrayList<OrderHistory> orderHistoryList=new ArrayList<OrderHistory>();
	OrderHistory orderHistory;
	
	public OrderHistoryDAOImpl()
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
	public int addOrderHistory(OrderHistory oh) {
		try
		{
			pstmt=con.prepareStatement(ADD_ORDERHISTORY);
			pstmt.setInt(1, oh.getOrderId());
			pstmt.setInt(2, oh.getUserId());
			pstmt.setFloat(3, oh.getTotalAmount());
			pstmt.setString(4, oh.getStatus());
			status=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<OrderHistory> getAllOrderHistory() {
		try
		{
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(SELECT_ALL_ORDERSHISTORY);
			orderHistoryList=extractOrderHistoryFromResultSet(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderHistoryList;
	}

	@Override
	public OrderHistory getSpecificOrderHistory(int orderHistoryId) {
		try
		{
			pstmt=con.prepareStatement(SELECT_SPECIFIC_ORDERHISTORY);
			pstmt.setInt(1, orderHistoryId);
			resultSet=pstmt.executeQuery();
			orderHistoryList=extractOrderHistoryFromResultSet(resultSet);
			orderHistory=orderHistoryList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderHistory;
	}
	
	@Override
	public ArrayList<OrderHistory> getOrderHistoryByUserId(int userId) {
		try
		{
			pstmt=con.prepareStatement(SELECT_SPECIFIC_ORDERHISTORY_ON_USERID);
			pstmt.setInt(1, userId);
			resultSet=pstmt.executeQuery();
			orderHistoryList=extractOrderHistoryFromResultSet(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderHistoryList;
	}
	
	public ArrayList<OrderHistory> extractOrderHistoryFromResultSet(ResultSet resultSet)
	{
		try
		{
			while(resultSet.next())
			{
				orderHistoryList.add(new OrderHistory(resultSet.getInt("orderHistoryId"),
						resultSet.getInt("orderId"),
						resultSet.getInt("userId"),
						resultSet.getString("orderDate"),
						resultSet.getFloat("totalAmount"),
						resultSet.getString("status")));
			}
		}
		catch(Exception e)
		{
			
		}
		return orderHistoryList;
	}


}
