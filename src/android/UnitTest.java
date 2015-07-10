package com.ihealth.plugin;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class UnitTest {
	private ArrayList<String> listconnect = new ArrayList<String>();
	JSONObject o = null;
	public ArrayList<String> getTestconnect(){
		listconnect.clear();
		o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "5CF938BED71E");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    listconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "F4F951C259FD");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    listconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "7FEE23DD7284");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    listconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("name", "BP5 143F1E");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    listconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("msg", "create bluetoothsocket success");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    listconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("msg", "create iostream success");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    listconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("msg", "authenticate device");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    listconnect.add(o.toString());
	    return listconnect;
	}
	
	private ArrayList<String> measureconnect = new ArrayList<String>();
	public ArrayList<String> getTestMeasure(){
		measureconnect.clear();
		measureconnect.add("zero doing!");
		measureconnect.add("zero doing!");
		measureconnect.add("zero doing!");
		measureconnect.add("zero doing!");
		measureconnect.add("zero doing!");
		measureconnect.add("zero doing!");
		measureconnect.add("zero doned!");
		
		o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "0");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "0");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "0");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "0");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "0");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "1");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "1");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "2");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "3");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "4");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "5");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "8");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "10");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "12");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "14");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "16");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "18");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "20");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "21");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "23");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "25");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "27");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "28");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "30");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "30");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "30");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "31");
	        o.put("wave", "0F0F111215171A1C");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "32");
	        o.put("wave", "1C1D1C1B19181614");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "32");
	        o.put("wave", "131110101010100F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "33");
	        o.put("wave", "10111215171A1C1C");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "34");
	        o.put("wave", "1D1C1B1918161413");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "35");
	        o.put("wave", "1110101010100F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "36");
	        o.put("wave", "1010111213141516");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "37");
	        o.put("wave", "1718181716141312");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "38");
	        o.put("wave", "11100F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "39");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "40");
	        o.put("wave", "0F0F0F0F0F0F1012");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "41");
	        o.put("wave", "1213131313131312");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "42");
	        o.put("wave", "1212121212111111");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "43");
	        o.put("wave", "1110100F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "44");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "45");
	        o.put("wave", "0F10121213131313");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "46");
	        o.put("wave", "1313121212121212");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "47");
	        o.put("wave", "1111111110100F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "48");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "49");
	        o.put("wave", "0F0F0F0F10100F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "50");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "51");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "52");
	        o.put("wave", "0F0F0F0F0F0F0F10");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "52");
	        o.put("wave", "1112131313131312");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "53");
	        o.put("wave", "1211111111101011");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "54");
	        o.put("wave", "1111111111101010");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "55");
	        o.put("wave", "10100F0F0F0F1112");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "57");
	        o.put("wave", "1415151516161615");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "58");
	        o.put("wave", "1515151516161717");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "60");
	        o.put("wave", "1716161514131211");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "60");
	        o.put("wave", "10100F0F10111212");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "61");
	        o.put("wave", "121210100F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "63");
	        o.put("wave", "0F11121415151516");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "64");
	        o.put("wave", "1616151515151516");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "65");
	        o.put("wave", "1617171716161514");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "65");
	        o.put("wave", "13121110100F0F10");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "67");
	        o.put("wave", "111212121210100F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "67");
	        o.put("wave", "0F0F0F1012151819");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "66");
	        o.put("wave", "1919191919181717");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "68");
	        o.put("wave", "1717171817171716");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "71");
	        o.put("wave", "1514131312121110");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "70");
	        o.put("wave", "100F0F1113171B1D");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "71");
	        o.put("wave", "1F20212223242425");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "71");
	        o.put("wave", "2628292B2C2D2E2E");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "74");
	        o.put("wave", "2F2F303031323233");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "75");
	        o.put("wave", "333435373B404549");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "76");
	        o.put("wave", "4C4D4E4E4E4D4C4A");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "80");
	        o.put("wave", "47433F3C3A393838");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "80");
	        o.put("wave", "372D231B161D2529");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "80");
	        o.put("wave", "2B2A28272523211F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "81");
	        o.put("wave", "372D231B161D2529");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "83");
	        o.put("wave", "1B1A191817151311");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "83");
	        o.put("wave", "1010121820272D30");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "82");
	        o.put("wave", "31302E2D2A282624");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "82");
	        o.put("wave", "2221212121201F1E");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "86");
	        o.put("wave", "1D1B191614121113");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "85");
	        o.put("wave", "1820293136383837");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "85");
	        o.put("wave", "35322D2925221F1D");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "88");
	        o.put("wave", "1B1A191716141210");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "90");
	        o.put("wave", "0F0F0F10131A222B");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "90");
	        o.put("wave", "3134353433312E2B");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	   
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "90");
	        o.put("wave", "2724211F1D1C1A18");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "94");
	        o.put("wave", "1715141211100F10");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "93");
	        o.put("wave", "1319222B32363837");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "93");
	        o.put("wave", "3633302D29262320");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "95");
	        o.put("wave", "1E1C1B1918161412");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "97");
	        o.put("wave", "100F0F101319222B");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "95");
	        o.put("wave", "3337393938363430");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "95");
	        o.put("wave", "2C282522201F1D1B");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "99");
	        o.put("wave", "1A18161412100F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "99");
	        o.put("wave", "1218212B33383B3B");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "98");
	        o.put("wave", "3B3936312C27221F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "99");
	        o.put("wave", "1C1A181716151414");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "103");
	        o.put("wave", "1312111010131921");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "102");
	        o.put("wave", "2B33393B3C3A3936");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "102");
	        o.put("wave", "322D2823201C1A18");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "103");
	        o.put("wave", "16141211100F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "107");
	        o.put("wave", "16141211100F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "107");
	        o.put("wave", "3137393A39383532");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "106");
	        o.put("wave", "2D28231F1B181614");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "106");
	        o.put("wave", "3137393A39383532");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "108");
	        o.put("wave", "2D28231F1B181614");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "110");
	        o.put("wave", "20293137393A3938");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "109");
	        o.put("wave", "35322D28231F1B18");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "110");
	        o.put("wave", "16141210100F1010");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "110");
	        o.put("wave", "0F0F0F0F0F0F1012");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "113");
	        o.put("wave", "171F2831373C3E3F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "113");
	        o.put("wave", "3F3D3A36302B2621");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "114");
	        o.put("wave", "1D1A181615141313");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "115");
	        o.put("wave", "1212121212121111");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "118");
	        o.put("wave", "10101114191E2224");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "118");
	        o.put("wave", "242321201D1A1815");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "120");
	        o.put("wave", "0F0F10100F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "122");
	        o.put("wave", "0F0F0F11151A1F22");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "122");
	        o.put("wave", "22221F1C1A171513");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "123");
	        o.put("wave", "1212131313131212");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "124");
	        o.put("wave", "1110101010100F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "126");
	        o.put("wave", "0F0F0F101114181B");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "126");
	        o.put("wave", "1D1D1C1917151413");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "127");
	        o.put("wave", "1313131414141413");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "128");
	        o.put("wave", "1212111110100F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "129");
	        o.put("wave", "0F0F0F0F11131719");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "130");
	        o.put("wave", "1A19171513121111");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "130");
	        o.put("wave", "1111111111111110");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "131");
	        o.put("wave", "101010100F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "132");
	        o.put("wave", "0F0F0F1114161818");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "133");
	        o.put("wave", "16141211100F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "134");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("pressure", "135");
	        o.put("wave", "0F0F0F0F0F0F0F0F");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    
	    o = null;
	    try {
	        o = new JSONObject();
	        o.put("address", "8CDE52143F1E");
	        o.put("highpressure", "120.0");
	        o.put("lowpressure", "77.0");
	        o.put("heartrate", "84");
	        o.put("arrhythmia", "true");
	    } catch (Exception e) {                   
	        e.printStackTrace();
	    }
	    measureconnect.add(o.toString());
	    return measureconnect;
	}
	
	public String getTestHistoryNum(){
		JSONObject o = null;
		    try {
		        o = new JSONObject();
		        o.put("address", "8CDE52143F1E");
		        o.put("num", "1");
		    } catch (Exception e) {                   
		        e.printStackTrace();
		    }
		    return o.toString();
	}
	
	public String getTestHistroyData(){
		JSONObject o = null;
        try {
            o = new JSONObject();
            o.put("address", "8CDE52143F1E");
            o.put("data", convert());
        } catch (Exception e) {                   
            e.printStackTrace();
        }
		return o.toString();
	}
	
	private String convert(){
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		String dateStr = "2015" + "-" + "5" + "-" + "4" + " " + "14" + ":" + "27" + ":" + "00";
		JSONObject stoneObject = new JSONObject();
		try {
			stoneObject.put("date", dateStr);
			stoneObject.put("sys", String.valueOf(111));
			stoneObject.put("dia", String.valueOf(78));
			stoneObject.put("pulse", String.valueOf(85));
			stoneObject.put("ahr", String.valueOf(0));
			stoneObject.put("hsd", String.valueOf(0));
			array.put(stoneObject);
			object.putOpt("offlineData", array);
        } catch (Exception e) {                   
            e.printStackTrace();
        }
		return object.toString();
	}
}
