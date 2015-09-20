package com.amazon.pushNotification;

import java.security.Timestamp;
import java.sql.Time;
import java.util.ArrayList;

import com.amazon.dataObjects.ItemDetails;

public class TestAllFunctions {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ItemOperations pn = new ItemOperations();
		ArrayList<ItemDetails> itemDetails = new ArrayList<ItemDetails>();
		ItemDetails id = new ItemDetails();
		id.setItemID("1");
		id.setAttribute("abc");
		id.setValue("vinoth111");
		long date = System.currentTimeMillis();
		java.sql.Timestamp timeStamp= new java.sql.Timestamp(date);

		id.setTimeStamp(timeStamp);
		itemDetails.add(id);
		System.out.println(pn.insertItemDetail(itemDetails,date));
	}

}
