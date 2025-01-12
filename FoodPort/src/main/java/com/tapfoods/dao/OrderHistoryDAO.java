package com.tapfoods.dao;

import java.util.ArrayList;
import com.tapfoods.model.OrderHistory;

public interface OrderHistoryDAO {
	int addOrderHistory(OrderHistory oh);
	ArrayList<OrderHistory> getAllOrderHistory();
	OrderHistory getSpecificOrderHistory(int orderHistoryId);
	ArrayList<OrderHistory> getOrderHistoryByUserId(int userId);
}
