package com.amazon.pushNotification;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.amazon.pushNotification.ResourceManager;

public class SendNotification {
	
	public void getNotificationData(Long stampID)
	{
		try {
			Connection connection=ResourceManager.obtainDbConnection();
	        PreparedStatement preparedStatement=connection.prepareStatement("select distinct(i.item_id) item_id,i.attr_name attr_name,s.subs_op subs_op, n.subs_val subs_val, i.attr_val attr_val, n.cust_id cust_id, c.name name from item i, subscription s , cust_subcr n , customer c" +
	        		" where i.item_timestamp=(select max(t.item_timestamp) from item t)" +
	        		" and s.subs_attr=i.attr_name" +
	        		" and n.subs_code=s.subs_id" +
	        		" and c.cust_id = n.cust_id");
	        java.sql.ResultSet rs=preparedStatement.executeQuery();
	        System.out.println("As of now we have written logic only for Equal check as per subscription change check alone");
	        StringBuilder sb = new StringBuilder("");
	        while(rs.next()){
	             //Retrieve by column name
	        	 String id  = rs.getString("item_id");
	             String attr = rs.getString("attr_name");
	             String val = rs.getString("attr_val");
	             String name = rs.getString("name");
	             String subs_val = rs.getString("subs_val");
	             String condiu8 = rs.getString("subs_op");
	             if(condiu8.equals("eq"))
	             {
	            	 if(subs_val.equals(val))
	            		 sendNotification(name,id,attr+"-"+val);
	             }
	             
	         }
	         ResourceManager.close(preparedStatement);
	         ResourceManager.close(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendNotification(String name,String Item,String Value)
	{
		System.out.println("Hi "+name+" your subcribed item got changed in value (item code="+Item+")"+ " as "+Value);
	}
	
}
