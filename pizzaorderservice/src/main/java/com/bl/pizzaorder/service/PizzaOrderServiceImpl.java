package com.bl.pizzaorder.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bl.pizzaorder.dao.H2Helper;
import com.bl.pizzaorder.dao.PizzaOrderDAOIF;
import com.bl.pizzaorder.dao.PizzaOrderDAOImpl;
import com.bl.pizzaorder.model.Order;

@Service("pizzaOrderServiceImpl")
public class PizzaOrderServiceImpl implements PizzaOrderServiceIF{

	@Autowired
	PizzaOrderDAOIF pizzaOrderDAOIF;
	
	public Order getOrder(int orderId) {		
		return pizzaOrderDAOIF.executeQueryGetOrder(orderId);
		
	}

	public Order saveOrder(Order order){
		return pizzaOrderDAOIF.executeQueryCreateOrder(order);
	}

	
	
}
