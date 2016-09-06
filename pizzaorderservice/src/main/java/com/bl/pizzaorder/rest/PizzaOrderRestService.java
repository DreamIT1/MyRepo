package com.bl.pizzaorder.rest;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.bl.pizzaorder.model.Order;
import com.bl.pizzaorder.model.Pizza;
import com.bl.pizzaorder.service.PizzaOrderServiceIF;
import com.bl.pizzaorder.service.PizzaOrderServiceImpl;

@Path("/orders")
public class PizzaOrderRestService {
	final static Logger logger = Logger.getLogger(PizzaOrderRestService.class);

	/**
	 * To Get the Pizza Order for a specific orderID.
	 * @param orderId
	 * @return
	 * http://localhost:8080/pizzaorderservice/orders/{orderId}

	 */	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Order getOrder(@PathParam("param") int orderId){
	   logger.info("In get order rest"+ orderId);
       ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
       PizzaOrderServiceIF pizzaOrderServiceIF = (PizzaOrderServiceImpl)ctx.getBean("pizzaOrderServiceImpl");
       Order o = pizzaOrderServiceIF.getOrder(orderId);
	   return o;
	}

	/**
	 * To save the Pizza Order
	 * @param order
	 * @return
	 * http://localhost:8080/pizzaorderservice/orders/
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Order saveOrder(Order order){
		 logger.info("In save order service");
		 ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	     PizzaOrderServiceIF pizzaOrderServiceIF = (PizzaOrderServiceImpl)ctx.getBean("pizzaOrderServiceImpl");
	     Order o = pizzaOrderServiceIF.saveOrder(order);
		 return o;
	}
}
