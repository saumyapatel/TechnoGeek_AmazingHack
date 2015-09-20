package com.amazon.pushNotification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.amazon.dataObjects.ItemDetails;
import com.amazon.dataObjects.Subscription;
import com.mysql.jdbc.ResultSet;

public class SubscriptionOperations {

	ArrayList<Subscription> getSubscriptionDetails(int subcr_Id) throws SQLException{

		Connection connection=ResourceManager.obtainDbConnection();

		String command = "select * from subscription where subs_code="+subcr_Id;
		PreparedStatement preparedStatement=connection.prepareStatement(command);
		java.sql.ResultSet rs=preparedStatement.executeQuery();
		ArrayList<Subscription> subscrList = new ArrayList<Subscription>();
		while(rs.next()){
			//Retrieve by column name
			Subscription subscr = new Subscription();
			String subs_id = rs.getString("subs_id");
			String subs_attr = rs.getString("subs_attr");
			String subs_op = rs.getString("subs_op");
			String subs_val = rs.getString("subs_val");

			subscr.setSubs_id(subs_id);
			subscr.setSubs_attr(subs_attr);
			subscr.setSubs_op(subs_op);
			subscr.setSubs_val(subs_val);

			subscrList.add(subscr);
		}
		ResourceManager.close(preparedStatement);
		ResourceManager.close(connection);
		return subscrList;
	}

	int insertSubscription(Subscription subscr) throws Exception{

		Connection connection=ResourceManager.obtainDbConnection();
		String subs_id = subscr.getSubs_id();
		String subs_attr = subscr.getSubs_attr();
		String subs_op = subscr.getSubs_op();
		String subs_val = subscr.getSubs_val();

		String command="insert into subscription values (?,?,?)";

		PreparedStatement preparedStatement=connection.prepareStatement(command);

		preparedStatement.setString(1, subs_attr);
		preparedStatement.setString(2, subs_op);
		preparedStatement.setString(3, subs_val);

		int rowCount=preparedStatement.executeUpdate();
		if(rowCount==0)
		{
			throw new Exception("Ooops..! Record could not be inserted!!!");
		}
		ResourceManager.close(preparedStatement);

		ResourceManager.close(connection);
		return rowCount;
	}
/*
	ArrayList<Subscription> searchSubscr(Subscription subscr)
	{
		Connection connection=ResourceManager.obtainDbConnection();

		String command = "select * from subscription where subs_code="+subcr_Id;
		PreparedStatement preparedStatement=connection.prepareStatement(command);
		java.sql.ResultSet rs=preparedStatement.executeQuery();
		ArrayList<Subscription> subscrList = new ArrayList<Subscription>();
		while(rs.next()){
			//Retrieve by column name
			Subscription subscr = new Subscription();
			String subs_id = rs.getString("subs_id");
			String subs_attr = rs.getString("subs_attr");
			String subs_op = rs.getString("subs_op");
			String subs_val = rs.getString("subs_val");

			subscr.setSubs_id(subs_id);
			subscr.setSubs_attr(subs_attr);
			subscr.setSubs_op(subs_op);
			subscr.setSubs_val(subs_val);

			subscrList.add(subscr);
		}
		ResourceManager.close(preparedStatement);
		ResourceManager.close(connection);
		return subscrList;
	}
*/
	int	searchSubscription(Subscription subscr) throws Exception{

		Connection connection=ResourceManager.obtainDbConnection();
		String subs_id = subscr.getSubs_id();
		String subs_attr = subscr.getSubs_attr();
		String subs_op = subscr.getSubs_op();
		String subs_val = subscr.getSubs_val();

		String command="select sub_id from subscription where sub_attr=? and sub_op=? and sub_val=?";

		PreparedStatement preparedStatement=connection.prepareStatement(command);

		preparedStatement.setString(1, subs_attr);
		preparedStatement.setString(2, subs_op);
		preparedStatement.setString(3, subs_val);

		ResultSet rs=(ResultSet) preparedStatement.executeQuery();

		while(rs.next()){
			//Retrieve by column name
			return Integer.parseInt(rs.getString("subs_id"));
		}

		ResourceManager.close(preparedStatement);
		ResourceManager.close(connection);
		return -1;
	}

	int registerSubscription(int subscriberId, int subscriptionId) {
		try{
			Connection connection=ResourceManager.obtainDbConnection();
			String command="insert into cust_subscr values (?,?)";

			PreparedStatement preparedStatement=connection.prepareStatement(command);

			preparedStatement.setString(1, subscriberId+"");
			preparedStatement.setString(2, subscriptionId+"");

			int rowCount=preparedStatement.executeUpdate();
			if(rowCount==0)
			{
				throw new Exception("Ooops..! Record could not be inserted!!!");
			}
			ResourceManager.close(preparedStatement);

			ResourceManager.close(connection);
			return rowCount;
		}catch(Exception e){
			//todo
		}
		return 0;
	}
}
