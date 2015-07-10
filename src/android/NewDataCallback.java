package com.ihealth.plugin;


public interface NewDataCallback {
	void haveNewData(int what, int stateId, byte[] command);
}
