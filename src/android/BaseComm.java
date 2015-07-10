package com.ihealth.plugin;

interface BaseComm {
	void sendData(String mac, byte[] data);
	void disconnect();
	void addCommNotify(BaseCommProtocol dataCallBack);
}
