package com.bl.pizzaorder.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.bl.pizzaorder.model.Order;
import com.bl.pizzaorder.model.Pizza;
import com.bl.pizzaorder.model.Topping;
import com.bl.pizzaorder.rest.PizzaOrderRestService;
import com.bl.pizzaorder.dao.*;

@Repository
public class PizzaOrderDAOImpl implements PizzaOrderDAOIF{
	final static Logger logger = Logger.getLogger(PizzaOrderDAOImpl.class);


	public Order executeQueryGetOrder(int orderId) {
		String query = "select * from pizzaorder po,pizza p where po.orderid=p.orderid and po.orderid = "
				+ orderId;
		try {
			Connection con = H2Helper.getInstance().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			Order o = buildOrderObj(rs, con);		
			H2Helper.getInstance().closeConnection();
			return o;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				H2Helper.getInstance().closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
	}

	private Order buildOrderObj(ResultSet rs, Connection con) {
		Order o = new Order();
		try {
			while (rs.next()) {
				o.setOrderId(rs.getInt("orderid"));
				Pizza p = new Pizza();
				p.setPizzaType(rs.getString("TYPE"));
				p.setOrderId(rs.getInt("ORDERID"));
				p.setPizzaId(rs.getInt("PIZZAID"));
				p.setSize(rs.getString("SIZE"));

				Topping t = new Topping();
				String tQuery = "select * from TOPPING t where t.pizzaid = "
						+ p.getPizzaId();
				ResultSet toppingRs = con.createStatement().executeQuery(tQuery);
				while (toppingRs.next()) {
					t.setToppingId(toppingRs.getInt("TOPPINGID"));
					t.setName(toppingRs.getString("NAME"));
					t.setPizzaId(toppingRs.getInt("PizzaId"));
					p.getTopping().add(t);
				}
				o.getPizza().add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return o;
	}

	public Order executeQueryCreateOrder(Order order){
		String orderQuery = "insert into PIZZAORDER VALUES (" + order.getOrderId() + ")";
		Connection con = H2Helper.getInstance().getConnection();
		try {
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			stmt.execute(orderQuery);
			Iterator i = order.getPizza().iterator();
			while (i.hasNext()) {
				Pizza p = (Pizza) i.next();
				String pizzaQuery = "insert into pizza values("
						+ p.getPizzaId() + "," + order.getOrderId() + ",'"
						+ p.getPizzaType() + "','" + p.getSize() + "')";
				Iterator toppingsIter = p.getTopping().iterator();
				while (toppingsIter.hasNext()) {
					Topping t = (Topping) toppingsIter.next();
					String toppingQuery = "insert into topping values("
							+ t.getToppingId() + "," + p.getPizzaId() + ",'"
							+ t.getName() + "')";
					logger.info("topping query" + toppingQuery);
					stmt.execute(toppingQuery);
				}
				logger.info("pizza query" + pizzaQuery);
				stmt.execute(pizzaQuery);
			}

			con.commit();
			H2Helper.getInstance().closeConnection();
			return order;
		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
				H2Helper.getInstance().closeConnection();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}

