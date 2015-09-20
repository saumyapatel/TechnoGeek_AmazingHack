package com.amazon.pushNotification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class ResourceManager.
 */
public class ResourceManager {

	/**
	 * Obtain db connection.
	 *
	 * @return the connection
	 *
	 * @throws SQLException the SQL exception
	 */
	public static Connection obtainDbConnection() throws SQLException
	{

		String URL="jdbc:mysql://192.168.6.18/item";
		String USER="root";
		String PASSWORD="shree";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connecting..");
		Connection connection=DriverManager.getConnection(URL,USER,PASSWORD);
		return connection;
	}

	/**
	 * Close.
	 *
	 * @param con the con
	 */
	public static void close(Connection con)
	{

			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	/**
	 * Close.
	 *
	 * @param preparedStatement the prepared statement
	 */
	public static void close(PreparedStatement preparedStatement)
	{
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Close.
	 *
	 * @param resultSet the result set
	 */
	public static void close(ResultSet resultSet)
	{
		try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
