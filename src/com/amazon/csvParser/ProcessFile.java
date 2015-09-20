package com.amazon.csvParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazon.dataObjects.ItemDetails;
import com.amazon.pushNotification.ItemOperations;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class ProcessFile {

	public static void processFile(String path,long timestamp) throws IOException {
		File file = new File(path);
		if(file.getName().endsWith("csv")) {
			try {
				parseCSVToBeanList(file,timestamp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void parseCSVToBeanList(File csvFile,long timestamp) throws IOException {
        try {


       ColumnPositionMappingStrategy<ItemDetails> beanStrategy = new ColumnPositionMappingStrategy<ItemDetails>();
       beanStrategy.setType(ItemDetails.class);

       Map<String, String> columnMapping = new HashMap<String, String>();
       String columns[] = {"itemID","attribute","value"};
      /* columnMapping.put(, "itemID");
       columnMapping.put("Attribute Name", "attribute");
       columnMapping.put("Attribute Value", "value");*/
       //columnMapping.put("Salary", "salary");

       beanStrategy.setColumnMapping(columns);

       CsvToBean<ItemDetails> csvToBean = new CsvToBean<ItemDetails>();
       CSVReader reader = new CSVReader(new FileReader(csvFile));
       List<ItemDetails> items = csvToBean.parse(beanStrategy, reader);
       insertData(items,timestamp);
       if(reader!=null)
       reader.close();
        } catch (Exception e) {
				e.printStackTrace();
		}
   }

	private static void insertData(List<ItemDetails> items,long timestamp) {
		try {
			int counter = 0;
			ItemOperations io = new ItemOperations();
			ArrayList<ItemDetails> insertionRecs = new ArrayList<ItemDetails>();
			for(int index=0;index<items.size();index++) {
				counter++;
				if(counter < 2000) {
					insertionRecs.add(items.get(index));
					System.out.print("ItemID:"+items.get(index).getItemID());


				} else if(counter >= 2000) {
					counter=0;
					io.insertItemDetail(insertionRecs, timestamp);
					insertionRecs = new ArrayList<ItemDetails>();
				}
				if(index == items.size() - 1){
					io.insertItemDetail(insertionRecs, timestamp);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
