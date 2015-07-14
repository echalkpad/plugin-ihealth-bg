package com.ihealth.plugin;

import android.content.Context;

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
	
	public void enableMeasure(){
		
	}
	
	public void disenableMeasure(){
		
	}
	
	public void startMeasure(){
		
	}
	
	public void getHistoryData(int index){
		
	}
	
	public void getHistoryNum(){
		
	}
	
	public void deleteHistoryData(){
		
	}
	
	public void holdLink(){
		
	}
	
	public void getCode(){
		
	}

	public void setBottleMessage(String qr, int mun, long timeTs) {
		
	}

	public void getBottleMessage() {
		
	}

	public void getOfflineData() {
		
	}

	public void deleteOfflineData() {
		// TODO Auto-generated method stub
		
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
