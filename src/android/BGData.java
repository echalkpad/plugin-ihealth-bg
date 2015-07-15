package com.ihealth.plugin;

public class BGData {
	
	String BGData_Date;
	int BGData_Value;
	
	public BGData() {
		super();
	}

	public BGData(String bGData_Date, int bGData_Value) {
		super();
		BGData_Date = bGData_Date;
		BGData_Value = bGData_Value;
	}

	public String getBGData_Date() {
		return BGData_Date;
	}

	public int getBGData_Value() {
		return BGData_Value;
	}

	public void setBGData_Date(String bGData_Date) {
		BGData_Date = bGData_Date;
	}

	public void setBGData_Value(int bGData_Value) {
		BGData_Value = bGData_Value;
	}
	
}
