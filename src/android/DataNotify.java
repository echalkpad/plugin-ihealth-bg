package com.ihealth.plugin;


interface DataNotify {
	void attach(NewDataCallback dataCallBack);
	void detach(NewDataCallback dataCallBack);
	void haveNewData(int what, int stateId, byte[] command);
}
