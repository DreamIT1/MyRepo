package com.bl.pizzaorder.dao;

import java.sql.SQLException;

import com.bl.pizzaorder.model.Order;

public interface PizzaOrderDAOIF {
	
	/**
	 * For a give orderId, this gets all the order details.
	 * @param orderId
	 * @return Order object will be populated with query data and returned.
	 */
	public Order executeQueryGetOrder(int orderId);
	
	/**
	 * This is used to create an order in the db
	 * @param order
	 * @return created Order (Order object)
	 */
	public Order executeQueryCreateOrder(Order order);

}
