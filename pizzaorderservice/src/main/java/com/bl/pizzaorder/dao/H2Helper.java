package com.bl.pizzaorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.bl.pizzaorder.model.*;

/**
 * 
 * Helper class for accessing the H2 db and to perform operations in the same.
 *
 */
public class H2Helper {

	public static H2Helper h2Helper = null;
	private Connection conn = null;
	DataSource ds = null;
	InitialContext initialContext = null;
	public static final String DBURL = "jdbc:h2:tcp://localhost/server~/test";// jdbc:h2:~/test
	public static final String DRIVER_NAME = "org.h2.Driver";
	
	private H2Helper(){
		
	}

	/**
	 * Creates the connection for the H2 db
	 * 
	 * @return Connection object
	 */
	public Connection getConnection()  {
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DBURL, "sa", "");
		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException(se);
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			throw new RuntimeException(ce);
		}

		return conn;
	}

	/**
	 * Utility for closing the connection. 
	 */
	public void closeConnection() throws SQLException {
		try {
			if (isConnectionOpen()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException(se);
		}
	}

	public boolean isConnectionOpen() {
		return (conn != null);
	}

	/**
	 * To make this class singleton Singleton makes sure that there is only one
	 * instance being used/created at any point of time.
	 * 
	 * @return H2Helper instance
	 */
	public static H2Helper getInstance() {
		if (h2Helper == null) {
			h2Helper = new H2Helper();
		}
		return h2Helper;
	}

}
