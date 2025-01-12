package com.tapfoods.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	//The cart items stored as a map of item IDs to CartItem objects
	private Map<Integer, CartItem> items;
	
	
	public Cart() {
		this.items=new HashMap<>();
	}
	public void addItem(CartItem item)
	{
		int itemId=item.getItemId();
		if(items.containsKey(itemId))
		{
			CartItem existingItem=items.get(itemId);
			existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());	
		}
		else
		{
			items.put(itemId, item);
		}
	}
	
	public void updateItem(int itemId, int quantity)
	{
		if(items.containsKey(itemId))
		{
			if(quantity<=0)
			{
				items.remove(itemId);
			}
			else
			{
				CartItem cartItem=items.get(itemId);
				cartItem.setQuantity(quantity);
			}
		}
	}
	
	public void removeItem(int itemId)
	{
		items.remove(itemId);
	}

	public Map<Integer, CartItem> getItems()
	{
		return items;
	}
	
	public void clear()
	{
		items.clear();
	}	
}
