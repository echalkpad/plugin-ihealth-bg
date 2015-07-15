package com.ihealth.plugin;

import android.content.Context;
import java.util.Calendar;
import java.util.Date;

public class Bg5Control implements DeviceControl{
	
	private Bg5InsSet mBg5InsSet;
	private BaseComm mComm;
	
	public Bg5Control(BaseComm com, Context context, String mac, String type){
		this.mComm = com;
		mBg5InsSet = new Bg5InsSet(com, context, mac, "BG5");
	}

	private void setTime(){
		mBg5InsSet.setTime();
	}
	
	public boolean setUnit(int type){
		if((type > 0) && (type < 4)){
			mBg5InsSet.setUnit(type);
			return true;
		}else
			return false;
	}

	public void getBattery(){
		
	}
	
	
	public void startMeasure(){
		mBg5InsSet.startMeasure(1);
	}	

	public void setBottleMessage(String qr) {
		mBg5InsSet.setBottleMessage(qr);
	}

	public void setBottleMessage(String qr, int num, long timeTs) {	
		Calendar time = Calendar.getInstance();
		time.setTimeInMillis(timeTs);	 
		byte year = (byte)(time.get(Calendar.YEAR));
		byte month = (byte)(time.get(Calendar.MONTH) + 1); 
		byte day = (byte)(time.get(Calendar.DATE));	
		mBg5InsSet.setBottleMessage(qr, (byte)num, year, month, day);
	}

	public void getBottleMessage() {
		mBg5InsSet.getCode();
	}

	public void getOfflineData() {
		mBg5InsSet.readEENum();
	}

	public void deleteOfflineData() {
		mBg5InsSet.deleteEE();
	}

	public void holdLink(){
		
	}

	public void setBottleId(int bottleId){
		mBg5InsSet.setBottleId(bottleId);
	}

	public void getBottleId(){
		mBg5InsSet.getBottleId();
	}

	@Override
	public void init() {
		mBg5InsSet.identify(false);
	}
	
	@Override
	public void disconnect(){
		mComm.disconnect();
	}
	
	
}
