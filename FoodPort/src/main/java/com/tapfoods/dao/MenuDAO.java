package com.tapfoods.dao;

import java.util.ArrayList;
import com.tapfoods.model.Menu;

public interface MenuDAO {
	
	int addMenu(Menu m);
	ArrayList<Menu> getAllMenuItems();
	Menu getSpecificMenuItem(int menuId);
	int updateMenuItem(Menu m);
	int deleteMenuItem(int menuId);
	ArrayList<Menu> getMenuOnRestaurantId(int restaurantId);
}
