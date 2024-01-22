package com.example2.user;


import com.example2.devices.Projector;
import com.example2.devices.TV;
import com.example2.remotes.RemoteControl;

public class Person {
	
	public static void main (String args[]){
	
		//Devices in the home
		Projector niceProjector = new Projector();
		TV niceLargeScreenTV = new TV();
		
		// The user's control
		RemoteControl remoteControl = RemoteControl.getInstance();
		
		remoteControl.connectToDevice(niceProjector);
		remoteControl.clickOnButton();
		
		remoteControl.connectToDevice(niceLargeScreenTV);
		remoteControl.clickOnButton();

	}
	
}
