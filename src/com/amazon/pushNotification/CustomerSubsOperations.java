package com.amazon.pushNotification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.amazon.dataObjects.ItemDetails;

public class CustomerSubsOperations {

	ArrayList<ItemDetails> getItemDetail(int itemId) throws SQLException{

		Connection connection=ResourceManager.obtainDbConnection();

		PreparedStatement preparedStatement=connection.prepareStatement("select * from cu where item_id="+itemId);
		java.sql.ResultSet rs=preparedStatement.executeQuery();
		ArrayList<ItemDetails> itemList = new ArrayList<ItemDetails>();
		while(rs.next()){
	         //Retrieve by column name
			ItemDetails item = new ItemDetails();
	    	 int id  = rs.getInt("item_id");
	         String attr = rs.getString("attr_name");
	         String val = rs.getString("attr_val");

	         item.setItemID(id+"");
	         item.setAttribute(attr);
	         item.setValue(val);
	         //Display values
	         /*System.out.print("ID: " + id);
	         System.out.print(", Attr: " + attr);
	         System.out.print(", Val: " + val);
	         System.out.println();*/
	         itemList.add(item);
	     }
		 ResourceManager.close(preparedStatement);
		 ResourceManager.close(connection);
		 return itemList;
	}

	public int insertCustomerSubscr() throws Exception{

		int totalInserted = 0;
		Connection connection=ResourceManager.obtainDbConnection();

		String command="insert into item values (?,?,?,?)";
		command = command + "ON DUPLICATE KEY UPDATE attr_val = IF(? < item_timestamp, attr_val, ?),";
		command = command + "item_timestamp = IF(? < item_timestamp, ";
		command = command + "item_timestamp, ?)";

		PreparedStatement preparedStatement=connection.prepareStatement(command);

		for(ItemDetails item : itemDetails){

			Long itemId= (long) Integer.parseInt(item.getItemID());
			String attr=item.getAttribute();
			String val=item.getValue();
			Timestamp time = new java.sql.Timestamp(timestamp);

			preparedStatement.setLong(1, itemId);
			preparedStatement.setString(2, attr);
			preparedStatement.setString(3, val);
			preparedStatement.setTimestamp(4, time);
			preparedStatement.setTimestamp(5, time);
			preparedStatement.setString(6, val);
			preparedStatement.setTimestamp(7, time);
			preparedStatement.setTimestamp(8, time);
			int rowCount=preparedStatement.executeUpdate();
			if(rowCount==0)
			{
				throw new Exception("Ooops..! Record could not be inserted!!!");
			}

			preparedStatement.addBatch();
			totalInserted++;
		}
		preparedStatement.executeBatch();
		ResourceManager.close(preparedStatement);
		ResourceManager.close(connection);
		return totalInserted;
	}

}
