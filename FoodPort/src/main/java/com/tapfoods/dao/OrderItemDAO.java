package com.tapfoods.dao;

import java.util.ArrayList;
import com.tapfoods.model.OrderItem;

public interface OrderItemDAO {
	
	int addOrderItem(OrderItem oi);
	ArrayList<OrderItem> getAllOrderItems();
	OrderItem getSpecificOrderItem(int orderItemId);
	ArrayList<OrderItem> getOrderItemByOrderItemId(int orderId);
}
