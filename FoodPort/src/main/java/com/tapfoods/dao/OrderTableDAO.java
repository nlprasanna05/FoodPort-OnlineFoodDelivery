package com.tapfoods.dao;

import java.util.ArrayList;
import com.tapfoods.model.OrderTable;

public interface OrderTableDAO {
	
	int addOrder(OrderTable o);
	ArrayList<OrderTable> getAllOrders();
	OrderTable getSpecificOrder(int orderId);
    int getMaxOrderId();
}
