/*
 * Author : Vinoth balakrishnan
 */

package com.amazon.pooling;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class processingThread extends Thread {

	/*private String srcpath="";
	private String processingpath="";
	public processingThread(String filePath,String processing)
	{
		srcpath=filePath;
		processingpath=processing;
	}*/
	
	public void run(){  
		System.out.println("Processing thread is running..."); 
		try {
			while(true)
			{
				for(Long llong:PoolHandler.fileMilisYTI)
            		System.out.println("In Process "+llong);
				ArrayList<Long> listLong=(ArrayList<Long>)PoolHandler.fileMilisYTI.clone();
				for(Long nameLong:listLong)
				{
					
					if(!PoolHandler.fileinProcessing.contains(nameLong))
					{
						//PoolHandler.fileMilisYTI.remove(nameLong);
						//PoolHandler.fileinProcessing.add(nameLong);
						PoolHandler.AddRemoveDatafromMilisList1(nameLong, 2);
						PoolHandler.AddRemoveDatafromMilisList2(nameLong, 1);
						//System.out.println();
						Collections.sort(PoolHandler.fileMilisYTI);
						Collections.sort(PoolHandler.fileinProcessing);
						copyFileUsingJava7Files(new File(PoolHandler.srcPath+"/"+PoolHandler.yetToProcess2.get(nameLong)),new File(PoolHandler.inProgressPath+"/"+nameLong+".csv"));
						new FileInsertThread(nameLong+"",nameLong).start();
						
					}
				}
				TimeUnit.MILLISECONDS.sleep(50);
			}
		} catch (Exception e) {
			System.out.println("in processingThread ");
			e.printStackTrace();
		}
		
	}  
	public static void copyFileUsingJava7Files(File source, File dest) {
		try {
			System.out.println("Coping from "+source.toPath()+" Dest = "+dest.toPath());
			Files.copy(source.toPath(), dest.toPath());
			System.out.println("Going to delete from "+source.toPath());
			Files.delete(source.toPath());
			System.out.println("Deleted "+source.toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
