package com.ihealth.plugin;


public interface BaseCommProtocol {
	public void packageData(String mac, byte[] ins);
	public void unPackageData(byte[] data);
}
