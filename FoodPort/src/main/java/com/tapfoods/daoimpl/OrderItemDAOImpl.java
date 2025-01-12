package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.OrderItemDAO;
import com.tapfoods.dbUtils.DBUtils;
import com.tapfoods.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO{
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private static final String ADD_ORDER_ITEM="insert into OrderItem(`orderId`,`menuId`,`quantity`,`subTotal`)" + "values(?,?,?,?)";
	private static final String SELECT_ALL_ORDERITEMS="select * from `OrderItem`";
	private static final String SELECT_SPECIFIC_ORDERITEM="select * from `OrderItem` where `orderItemId`=?";
	private static final String GET_ORDERITEMS_BY_ORDERID="select * from `OrderItem` where `orderId`=?";
	int status=0;
	ArrayList<OrderItem> orderList=new ArrayList<OrderItem>();
	OrderItem orderItem;
	
	public OrderItemDAOImpl()
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
	public int addOrderItem(OrderItem oi) {
		try
		{
			pstmt=con.prepareStatement(ADD_ORDER_ITEM);
			pstmt.setInt(1, oi.getOrderId());
			pstmt.setInt(2, oi.getMenuId());
			pstmt.setInt(3, oi.getQuantity());
			pstmt.setFloat(4, oi.getSubTotal());
			status=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<OrderItem> getAllOrderItems() {
		try
		{
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(SELECT_ALL_ORDERITEMS);
			orderList=extractOrderItemFromResultSet(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public OrderItem getSpecificOrderItem(int orderItemId) {
		try
		{
			pstmt=con.prepareStatement(SELECT_SPECIFIC_ORDERITEM);
			resultSet=pstmt.executeQuery();
			orderList=extractOrderItemFromResultSet(resultSet);
			orderItem=orderList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderItem;
	}

	@Override
	public ArrayList<OrderItem> getOrderItemByOrderItemId(int orderId) {
		try
		{
			pstmt=con.prepareStatement(GET_ORDERITEMS_BY_ORDERID);
			pstmt.setInt(1, orderId);
			resultSet = pstmt.executeQuery();
			orderList=extractOrderItemFromResultSet(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderList;
	}
	
	public ArrayList<OrderItem> extractOrderItemFromResultSet(ResultSet resultSet)
	{
		try
		{
			while(resultSet.next())
			{
				orderList.add(new OrderItem(resultSet.getInt("orderItemId"),
				resultSet.getInt("orderId"),
				resultSet.getInt("menuId"),
				resultSet.getInt("quantity"),
				resultSet.getFloat("subTotal")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderList;
	}

}
