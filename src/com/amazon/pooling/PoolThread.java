package com.amazon.pooling;

import java.io.File;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class PoolThread extends Thread {
	
	public void run(){  
		System.out.println("thread is running..."); 
		try {
			while(true)
			{
				final File folder = new File(PoolHandler.srcPath);
				listFilesForFolder(folder);
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}  
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            //System.out.println(fileEntry.getName());
	            Long l= System.currentTimeMillis();
	            if(!PoolHandler.filenameYTI.contains(fileEntry.getName().trim()))
	            {
	            	if(PoolHandler.fileMilisYTI.contains(l))
	            	{
	            		l=l+100;
	            	}
	            	System.out.println("Adding new file name.. "+fileEntry.getName()+" TimeID = "+l);
	            	PoolHandler.yetToProcess.put(fileEntry.getName().trim(),l);
	            	PoolHandler.yetToProcess2.put( l,fileEntry.getName().trim());
	            	//PoolHandler.filenameYTI.add(fileEntry.getName().trim());
	            	//PoolHandler.fileMilisYTI.add(l);
	            	PoolHandler.AddRemoveDatafromNameList1(l, 1);
	            	PoolHandler.AddRemoveDatafromMilisList1(l, 1);
	            	Collections.sort(PoolHandler.fileMilisYTI);
	            	for(Long llong:PoolHandler.fileMilisYTI)
	            		System.out.println("Yet To Process "+llong);
	            }
	        }
	    }
	}
}
