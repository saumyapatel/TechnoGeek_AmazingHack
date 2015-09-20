package com.amazon.pooling;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProceedureCallThread extends Thread{

	public void run(){  
		try {
				System.out.println();
				while(true)
				{
					System.out.println("In progress "+PoolHandler.fileinProcessing.size());
					System.out.println("in Procecdurecolum "+PoolHandler.fileinProceedureCall.size());
					if((PoolHandler.fileinProcessing.size()>=0) && (PoolHandler.fileinProceedureCall.size()>0))
					{
						if(PoolHandler.fileinProcessing.size()==0)
						{
							System.out.println("Proceedure Complete for "+minValue(PoolHandler.fileinProceedureCall));
							markComplete(minValue(PoolHandler.fileinProceedureCall));
						}
						else if(minValue(PoolHandler.fileinProcessing)>minValue((PoolHandler.fileinProceedureCall)))
						{
							//Call Proceedure with Collections.min(PoolHandler.fileinProceedureCall)
							System.out.println("Calling Proceedure for "+minValue(PoolHandler.fileinProceedureCall));
							/*for(int i=0;i<10000;i++)
							{
								for(int j=0;j<10000;j++)
								{
									
								}
							}*/
							markComplete(minValue(PoolHandler.fileinProceedureCall));
							System.out.println("Proceedure Complete for "+minValue(PoolHandler.fileinProceedureCall));
						}
					}
					System.out.println(minValue(PoolHandler.fileinProceedureCall));
					TimeUnit.SECONDS.sleep(20);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void markComplete(Long fileID)
	{
		try {
			//Files.delete(new File(PoolHandler.inProgressPath+"/"+fileID+".csv").toPath());
			//PoolHandler.fileinProcessing.remove(fileID);
			//PoolHandler.fileinProceedureCall.remove(fileID);
			PoolHandler.AddRemoveDatafromNameList1(fileID, 2);
			PoolHandler.yetToProcess.remove(PoolHandler.yetToProcess2.get(fileID));
			PoolHandler.yetToProcess2.remove(fileID);
			PoolHandler.AddRemoveDatafromMilisList3(fileID, 2);
			processingThread.copyFileUsingJava7Files(new File(PoolHandler.inProgressPath+"/"+fileID+".csv"),new File(PoolHandler.endPath+"/"+fileID+".csv"));
			
			Collections.sort(PoolHandler.fileinProcessing);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Long minValue (List<Long> list) {
		if(list.size()>0)
		{
		  Collections.sort(list);
		  return list.get(0);
		}
		return 0L;
	}
}
