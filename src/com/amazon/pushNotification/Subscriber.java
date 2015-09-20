package com.amazon.pushNotification;

import java.io.File;

import com.amazon.dataObjects.Subscription;

public class Subscriber {

	public void subscribe(String subscriptionFile) //change to file
	{
		String subscr = "c002,deact,publisher,eq,William Morrow";
		String subArray[] = subscr.split(",");
		if(subArray.length == 5){
			Boolean act = false;
			if(subArray[1].equals("act"))
				act = true;
			updateSubscription(subArray[0], act, subArray[2], subArray[3], subArray[4]);
		}
	}

	public void updateSubscription(String subscriberId, Boolean act, String attr, String operator, String value)
	{
		if(act == true){
			Subscription subcr = new Subscription();
			subcr.setSubs_attr(attr);
			subcr.setSubs_op(operator);
			subcr.setSubs_val(value);
			SubscriptionOperations so = new SubscriptionOperations();
			int subcriptionId = so.searchSubscription(subcr);
			if(subcriptionId > -1){
				registerSubscription(subscriberId,subcriptionId);
			}
			else{
				insertSubscription(subcr);
				subcriptionId = so.searchSubscription(subcr);
				registerSubscription(subscriberId,subcriptionId);
			}
		}
		else{
			unregisterSubscription();
		}
	}
}
