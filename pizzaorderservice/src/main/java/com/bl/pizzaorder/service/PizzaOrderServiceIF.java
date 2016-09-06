package com.bl.pizzaorder.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.bl.pizzaorder.model.Order;

@Service("pizzaOrderServiceImpl")
public interface PizzaOrderServiceIF {
	/**
	 * Gets the Order for an orderId
	 * @param orderId
	 * @return
	 */
	public Order getOrder(int orderId);
	
	/**
	 * Creates an order
	 * @param order
	 * @return
	 */
	public Order saveOrder(Order order);

}
