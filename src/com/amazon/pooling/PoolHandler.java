package com.amazon.pooling;

import java.util.ArrayList;
import java.util.HashMap;

public class PoolHandler {

	/**
	 * @param args
	 */
	public static ArrayList<String> filenameYTI=new ArrayList<String>(); // Store file name of inserted (in yetToProcess)
	public static ArrayList<Long> fileMilisYTI=new ArrayList<Long>();// Store milllis of inserted (in yetToProcess)
	public static ArrayList<Long> fileinProcessing=new ArrayList<Long>();
	public static ArrayList<Long> fileinProceedureCall=new ArrayList<Long>();
	public static HashMap<String, Long> yetToProcess=new HashMap<String, Long>();// Insert file with millis
	public static HashMap<Long, String> yetToProcess2=new HashMap<Long, String>();// Insert file with millis
	//public static HashMap<String, Long> inProcessing=new HashMap<String, Long>();// in thread
	public static ArrayList<Long> fileorderProcess=new ArrayList<Long>(); //after thread inserted in temp table..
	//read from list and initiate proceedure.
	public static String srcPath=null;
	public static String inProgressPath=null;
	public static String endPath=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			 srcPath=PropsLoaderlabel.getCommonProperty("pollingLocation");
			 inProgressPath=PropsLoaderlabel.getCommonProperty("ProcessingLocation");
			 endPath=PropsLoaderlabel.getCommonProperty("endLocation");
			//System.out.println(s);
			PoolThread pl=new PoolThread();
			pl.start();
			processingThread processingThreadObj=new processingThread();
			processingThreadObj.start();
			ProceedureCallThread ProceedureCallThreadObj=new ProceedureCallThread();
			ProceedureCallThreadObj.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static synchronized void AddRemoveDatafromNameList1(Long longNumber,int mode)
	{
		if(mode==1)
			PoolHandler.filenameYTI.add(PoolHandler.yetToProcess2.get(longNumber));
		if(mode==2)
			PoolHandler.filenameYTI.remove(PoolHandler.yetToProcess2.get(longNumber));
	}
	
	public static synchronized void AddRemoveDatafromMilisList1(Long longNumber,int mode)
	{
		if(mode==1)
			PoolHandler.fileMilisYTI.add(longNumber);
		if(mode==2)
			PoolHandler.fileMilisYTI.remove(longNumber);
	}
	public static synchronized void AddRemoveDatafromMilisList2(Long longNumber,int mode)
	{
		if(mode==1)
			PoolHandler.fileinProcessing.add(longNumber);
		if(mode==2)
			PoolHandler.fileinProcessing.remove(longNumber);
	}
	
	public static synchronized void AddRemoveDatafromMilisList3(Long longNumber,int mode)
	{
		if(mode==1)
			PoolHandler.fileinProceedureCall.add(longNumber);
		if(mode==2)
			PoolHandler.fileinProceedureCall.remove(longNumber);
	}

}
                  