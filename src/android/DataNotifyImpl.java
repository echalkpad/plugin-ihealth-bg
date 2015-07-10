package com.ihealth.plugin;


import java.util.Enumeration;
import java.util.Vector;

public class DataNotifyImpl implements DataNotify{


	private Vector<NewDataCallback> vector = new java.util.Vector<NewDataCallback>();
	
	public Enumeration<NewDataCallback> observers() {
		vector.clone();
		Enumeration<NewDataCallback> enu = vector.elements();
		return enu;
	}
	
	@Override
	public void attach(NewDataCallback usbDataCallBack) {
		vector.add(usbDataCallBack);
	}

	@Override
	public void detach(NewDataCallback usbDataCallBack) {
		vector.remove(usbDataCallBack);
	}

	@Override
	public void haveNewData(int what, int stateId, byte[] command) {
		Enumeration<NewDataCallback> enumeration = observers();
		while (enumeration.hasMoreElements()) {
			((NewDataCallback) enumeration.nextElement()).haveNewData(what, stateId, command);
		}
	}


}
