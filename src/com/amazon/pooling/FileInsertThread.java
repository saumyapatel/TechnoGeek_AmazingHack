package com.amazon.pooling;

import java.io.File;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import com.amazon.csvParser.*;
import com.amazon.pushNotification.SendNotification;

public class FileInsertThread extends Thread {
	private  String filename=null;
	private  Long fileID=null;
	public FileInsertThread(String name,Long longID)
	{
		filename=name;
		fileID=longID;
	}
	public void run(){
		try {
			System.out.println("Inserting file.. "+filename);
			ProcessFile.processFile(PoolHandler.inProgressPath+"/"+filename+".csv", fileID);
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Insert Complete file.. "+filename);

				if(!PoolHandler.fileinProceedureCall.contains(fileID))
				{
					//PoolHandler.fileinProcessing.remove(fileID);
					//PoolHandler.fileinProceedureCall.add(fileID);
					PoolHandler.AddRemoveDatafromMilisList2(fileID, 2);
					PoolHandler.AddRemoveDatafromMilisList3(fileID, 1);
					Collections.sort(PoolHandler.fileinProcessing);
					Collections.sort(PoolHandler.fileinProceedureCall);
					SendNotification notify = new SendNotification();
					notify.getNotificationData(fileID);
				}

		} catch (Exception e) {
			System.out.println("in FileInsertThread ");
			e.printStackTrace();
		}

	}
}
