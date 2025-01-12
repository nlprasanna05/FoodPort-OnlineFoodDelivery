package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.tapfoods.dao.OrderTableDAO;
import com.tapfoods.dbUtils.DBUtils;
import com.tapfoods.model.OrderTable;

public class OrderTableDAOImpl implements OrderTableDAO{
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private static final String ADD_ORDER="insert into ordertable(`restaurantId`,`userId`,`totalAmount`,`status`,`paymentMode`)" + "values(?,?,?,?,?)";
	private static final String SELECT_ALL_ORDERS="select * from `ordertable`";
	private static final String SELECT_SPECIFIC_ORDER="select * from `ordertable` where `orderId`=?";
	private static final String SELECT_MAX_ORDERID = "SELECT MAX(orderId) AS maxOrderId FROM OrderTable";
	int status=0;
	ArrayList<OrderTable> orderList=new ArrayList<OrderTable>();
	OrderTable order;
	
	public OrderTableDAOImpl()
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
	public int addOrder(OrderTable o) {
		try
		{
			pstmt=con.prepareStatement(ADD_ORDER);
			pstmt.setInt(1, o.getRestaurantId());
			pstmt.setInt(2, o.getUserId());
			pstmt.setFloat(3, o.getTotalAmount());
			pstmt.setString(4, o.getStatus());
			pstmt.setString(5, o.getPaymentMode());
			status=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<OrderTable> getAllOrders() {
		try
		{
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(SELECT_ALL_ORDERS);
			orderList=extractOrderFromResultSet(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public OrderTable getSpecificOrder(int orderId) {
		try
		{
			pstmt=con.prepareStatement(SELECT_SPECIFIC_ORDER);
			pstmt.setInt(1, orderId);
			resultSet=pstmt.executeQuery();
			orderList=extractOrderFromResultSet(resultSet);
			order=orderList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return order;
	}
	
	@Override
	public int getMaxOrderId() {
		try
		{
			pstmt=con.prepareStatement(SELECT_MAX_ORDERID);
			resultSet=pstmt.executeQuery();
			if(resultSet.next())
			{
				return resultSet.getInt("maxOrderId");
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    return 0;
	}
	
	public ArrayList<OrderTable> extractOrderFromResultSet(ResultSet resultSet)
	{
		try
		{
			while(resultSet.next())
			{
				orderList.add(new OrderTable(resultSet.getInt("restaurantId"),
				resultSet.getInt("userId"),
				resultSet.getString("orderDate"),
				resultSet.getFloat("totalAmount"),
				resultSet.getString("status"),
				resultSet.getString("paymentMode")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderList;
	}


}
