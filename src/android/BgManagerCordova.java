package com.ihealth.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;

public class BgManagerCordova extends CordovaPlugin {

    private Bg5Manager mBg5Manager;
    private Context mContext;
    private static final String MSG_NO_METHOD = "no such method";
    private CallbackContext mCallbackContext;
    private UnitTest mUnitTest;
    private static final String MSG_TEST_DATA = "com.msg.test.data";
    private static final String MSG_TEST_DATA_EXTRA = "com.msg.test.data.extra";

    @Override
    public void initialize(final CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        mContext = webView.getContext();
        mBg5Manager = Bg5Manager.getInstance();
        mBg5Manager.init(mContext);
        initReceiver();
        mUnitTest = new UnitTest();
        mapCallback = new HashMap<String, List<CallbackContext>>();
        mapdisconnectCallback = new HashMap<String, CallbackContext>();
    }

    @Override
    public void onDestroy() {
    	mBg5Manager.destory();
        unReceiver();
    }
    
    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Bg5Manager.MSG_BT_CONNECTED);
        intentFilter.addAction(Bg5Manager.MSG_BT_DISCONNECT);
        intentFilter.addAction(Bg5Manager.MSG_DISCOVER_DEVICE);
        intentFilter.addAction(Bg5Manager.MSG_DISCOVER_FINISHED);
        intentFilter.addAction(Bg5InsSet.MSG_GET_BOTTLEID);
        intentFilter.addAction(Bg5InsSet.MSG_GET_CODE);
        intentFilter.addAction(Bg5InsSet.MSG_GET_BATTERY);
        intentFilter.addAction(Bg5InsSet.MSG_ERROR);
        intentFilter.addAction(Bg5InsSet.MSG_STRIP_IN);
        intentFilter.addAction(Bg5InsSet.MSG_GET_BLOOD);
        intentFilter.addAction(Bg5InsSet.MSG_GET_VALUE);
        intentFilter.addAction(Bg5InsSet.MSG_STRIP_OUT);
        intentFilter.addAction(Bg5InsSet.MSG_GET_HISTORY);
        intentFilter.addAction(Bg5InsSet.MSG_SET_UNIT);
        intentFilter.addAction(MSG_TEST_DATA);
        mContext.registerReceiver(mReceiver, intentFilter);
    }
    
    private void unReceiver(){
        mContext.unregisterReceiver(mReceiver);
    }

    private Map<String, List<CallbackContext>> mapCallback;
    private Map<String, CallbackContext> mapdisconnectCallback;
    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        this.mCallbackContext = callbackContext;
        if (action.equals("startDiscovery")) {
        	if(!args.isNull(1) && args.getString(1).equals("debug")){
        		testlist = null;
        		testlist = mUnitTest.getTestconnect();
        		TestThread();
        	}else{
        		mBg5Manager.startDiscovery();
        	}
            return true;
            
        } else if(action.equals("stopDiscovery")){
        	mBg5Manager.stopDiscovery();
        	return true;
        	
        } else if(action.equals("connectDevice")){
        	String mac = args.getString(0);
        	mBg5Manager.connectDevice(mac);
        	return true;
        	
        } else if (action.equals("setUnit")) {
            String mac = args.getString(0);
            int unit = args.getInt(1);
            
            List<CallbackContext> list = mapCallback.get(mac);
            if(list != null){
            	list.add(callbackContext);
            }else{
            	List<CallbackContext> listtemp = new ArrayList<CallbackContext>();
            	listtemp.add(callbackContext);
            	mapCallback.put(mac, listtemp);
            }
            if(null != mBg5Manager.getBg5Control(mac)){
            	mBg5Manager.getBg5Control(mac).setUnit(unit);
            }
            return true;
            
        } else if(action.equals("getBattery")) {
            String mac = args.getString(0);
            List<CallbackContext> list = mapCallback.get(mac);
        	if(list != null){
        		list.add(callbackContext);
        	}else{
        		List<CallbackContext> listtemp = new ArrayList<CallbackContext>();
        		listtemp.add(callbackContext);
        		mapCallback.put(mac, listtemp);
        	}
        	if(null != mBg5Manager.getBg5Control(mac)){
        		mBg5Manager.getBg5Control(mac).getBattery();
        	}
            return true;
            
        } else if(action.equals("setBottleMessage")) {
            String mac = args.getString(0);
            String qr = args.getString(1);
            int leftNum = args.getInt(2);
            long timeTs = args.getLong(3);
             List<CallbackContext> list = mapCallback.get(mac);
            if(list != null){
                list.add(callbackContext);
            }else{
                List<CallbackContext> listtemp = new ArrayList<CallbackContext>();
                listtemp.add(callbackContext);
                mapCallback.put(mac, listtemp);
            }
            
            if(timeTs == 0){
                mBg5Manager.getBg5Control(mac).setBottleMessage(qr);
            }else{
                mBg5Manager.getBg5Control(mac).setBottleMessage(qr, leftNum, timeTs);
            }        	
            return true;
            
        } else if(action.equals("getBottleMessage")) {
            String mac = args.getString(0);
            List<CallbackContext> list = mapCallback.get(mac);
        	if(list != null){
        		list.add(callbackContext);
        	}else{
        		List<CallbackContext> listtemp = new ArrayList<CallbackContext>();
        		listtemp.add(callbackContext);
        		mapCallback.put(mac, listtemp);
        	}
        	mBg5Manager.getBg5Control(mac).getBottleMessage();
            return true;
            
		} else if (action.equals("startMeasure")) {
			String mac = args.getString(0);
			List<CallbackContext> list = mapCallback.get(mac);
			if (list != null) {
				list.add(callbackContext);
			} else {
				List<CallbackContext> listtemp = new ArrayList<CallbackContext>();
				listtemp.add(callbackContext);
				mapCallback.put(mac, listtemp);
			}
			mBg5Manager.getBg5Control(mac).startMeasure();
			return true;

		} else if(action.equals("getOfflineData")){
        	String mac = args.getString(0);
        	if(!args.isNull(1) && args.getString(1).equals("debug")){
        		String str = mUnitTest.getTestHistroyData();
        		testlist = null;
        		testlist = new ArrayList<String>();
        		testlist.add(str);
        		TestThread();
        	}else{
        		List<CallbackContext> list = mapCallback.get(mac);
            	if(list != null){
            		list.add(callbackContext);
            	}else{
            		List<CallbackContext> listtemp = new ArrayList<CallbackContext>();
            		listtemp.add(callbackContext);
            		mapCallback.put(mac, listtemp);
            	}
            	mBg5Manager.getBg5Control(mac).getOfflineData();
        	}
            return true;
            
        } else if(action.equals("disConnectDevice")){
        	String mac = args.getString(0);
        	mBg5Manager.getBg5Control(mac).disconnect();
        	return true;
        	
        } else if(action.equals("setDisconnectCallback")){
        	String mac = args.getString(0);
        	mapdisconnectCallback.put(mac, callbackContext);
        	return true;
        	
        } else if(action.equals("setBottleId")){
            String mac = args.getString(0);
            int bottleid = args.getInt(1);
            mBg5Manager.getBg5Control(mac).setBottleId();
            return true;

        } else if(action.equals("getBottleId")){
            String mac = args.getString(0);
            mBg5Manager.getBg5Control(mac).getBottleId();
            return true;

        } else {
            mCallbackContext.error(MSG_NO_METHOD);
            return false;
        }
    }

    private void keepCallback(final CallbackContext callbackContext, String message) {
        PluginResult r = new PluginResult(PluginResult.Status.OK, message);
        r.setKeepCallback(true);
        callbackContext.sendPluginResult(r);
    }
    
    private int[] mBPmeasureResult;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(Bg5InsSet.MSG_GET_BATTERY.equals(action)) {
            	String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
            	int battery = intent.getIntExtra(Bg5InsSet.MSG_GET_BATTERY_EXTRA, 0);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "battery");
                    o.put("address", mac);
                    o.put("battery", battery + "");
                } catch (Exception e) {                   
                    e.printStackTrace();
                }
                keepCallback(mCallbackContext, o.toString());
            } else if(Bg5InsSet.MSG_GET_BOTTLEID.equals(action)) {
            	String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
            	int bottleid = intent.getIntExtra(Bg5InsSet.MSG_GET_BOTTLEID_EXTRA, 0);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "bottleid");
                    o.put("mac", mac);
                    o.put("bottleid", bottleid + "");
                } catch (Exception e) {                   
                    e.printStackTrace();
                }
                keepCallback(mCallbackContext, o.toString());
            } else if(Bg5InsSet.MSG_GET_CODE.equals(action)) {
                String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
                String code = intent.getStringExtra(Bg5InsSet.MSG_GET_CODE_EXTRA);
                int leftnum = intent.getIntExtra(Bg5InsSet.MSG_GET_LEFTNUM, 0);
                String expiretime = intent.getStringExtra(Bg5InsSet.MSG_GET_EXPIRECTIME);
                JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "code");
                    o.put("address", mac);
                    o.put("code", code);
                    o.put("leftnum", leftnum + "");
                    o.put("expiretime", expiretime);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }
                keepCallback(mCallbackContext, o.toString());
            } else if(Bg5Manager.MSG_DISCOVER_DEVICE.equals(action)){
            	String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
            	String name = intent.getStringExtra(Bg5Manager.MSG_NAME);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "discovery doing");
                    o.put("address", mac);
                    o.put("name", name);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } else if(Bg5Manager.MSG_CONNECTING_DEVICE.equals(action)){
            	String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
            	String name = intent.getStringExtra(Bg5Manager.MSG_NAME);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "connecting");
                    o.put("address", mac);
                    o.put("name", name);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } else if(Bg5Manager.MSG_CREATE_BLUETOOTHSOCKET_SUCCESS.equals(action)){
            	String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "create bluetoothsocket success");
                    o.put("address", mac);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } else if(Bg5Manager.MSG_CREATE_BLUETOOTHSOCKET_FAIL.equals(action)){
            	String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "create bluetoothsocket fail");
                    o.put("address", mac);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }  
                mCallbackContext.error(o.toString());
            } else if(Bg5Manager.MSG_CREATE_IOSTREAM_SUCCESS.equals(action)){
            	String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "create iostream success");
                    o.put("address", mac);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } else if(Bg5Manager.MSG_CREATE_IOSTREAM_FAIL.equals(action)){
            	String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
            	JSONObject o = null;
                try {
                   o = new JSONObject();
                   o.put("msg", "create iostream fail");
                   o.put("address", mac);
                } catch (Exception e) {                   
                   e.printStackTrace();
                } 
                mCallbackContext.error(o.toString());
            } else if(Bg5Manager.MSG_BT_CONNECTED.equals(action)){
            	String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
            	List<CallbackContext> list = mapCallback.get(mac);
            	if(list != null){
            		list.add(mCallbackContext);
            	}else{
            		List<CallbackContext> listtemp = new ArrayList<CallbackContext>();
            		listtemp.add(mCallbackContext);
            		mapCallback.put(mac, listtemp);
            	}
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "connected");
                    o.put("address", mac);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } 
//            else if(Bg5Manager.MSG_BP_OFFLINE_DATA.equals(action)){
//            	String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
//            	String msg = intent.getStringExtra(BpInsSet.MSG_BP_OFFLINE_DATA_EXTRA);
//            	JSONObject o = null;
//                try {
//                    o = new JSONObject();
//                    o.put("msg", "offlineData");
//                    o.put("address", mac);
//                    o.put("value", msg);
//                } catch (Exception e) {                   
//                    e.printStackTrace();
//                }         
//            	keepCallback(mCallbackContext, o.toString());
//            	
//            } 
            else if(MSG_TEST_DATA.equals(action)){
            	String str = intent.getStringExtra(MSG_TEST_DATA_EXTRA);
            	keepCallback(mCallbackContext, str);
            } else if(Bg5Manager.MSG_DISCOVER_FINISHED.equals(action)){
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "discovery done");
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } else if(Bg5Manager.MSG_BG5_IDPS.equals(action)){
            	String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
            	String str1 = intent.getStringExtra(Bg5Manager.MSG_BG5_IDPS_PROTOCOL);
            	String str2 = intent.getStringExtra(Bg5Manager.MSG_BG5_IDPS_ACCESSORYNAME);
            	String str3 = intent.getStringExtra(Bg5Manager.MSG_BG5_IDPS_FIRMWARE);
            	String str4 = intent.getStringExtra(Bg5Manager.MSG_BG5_IDPS_HARDWARE);
            	String str5 = intent.getStringExtra(Bg5Manager.MSG_BG5_IDPS_MODENUMBER);
            	String str6 = intent.getStringExtra(Bg5Manager.MSG_BG5_IDPS_MANUFACTURE);
            	String str7 = intent.getStringExtra(Bg5Manager.MSG_BG5_IDPS_SERIALNUMBER);
            	JSONObject o = null;
            	try {
                    o = new JSONObject();
                    o.put("msg", "idps");
                    o.put("address", mac);
                    o.put("potocol", str1);
                    o.put("accessoryname", str2);
                    o.put("firmware", str3);
                    o.put("hardware", str4);
                    o.put("modenumber", str5);
                    o.put("manufacture", str6);
                    o.put("serialnumber", str7);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } else if(Bg5Manager.MSG_BT_DISCONNECT.equals(action)){
            	String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
            	String type = intent.getStringExtra(Bg5Manager.MSG_TYPE);
            	JSONObject o = null;
            	try {
                    o = new JSONObject();
                    o.put("msg", "disconnect");
                    o.put("address", mac);
                    o.put("type", type);
                } catch (Exception e) {                   
                    e.printStackTrace();
                } 
            	CallbackContext callbackContext = mapdisconnectCallback.get(mac);
            	if(callbackContext != null){
            		keepCallback(callbackContext, o.toString());
            		mapdisconnectCallback.remove(mac);
            	}
            	for(CallbackContext callback : mapCallback.get(mac)){
            		if(callback != null){
                		keepCallback(callback, o.toString());
                	}
            	}
            	mapCallback.remove(mac);
            } else if(Bg5InsSet.MSG_SET_UNIT.equals(action)){
                String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
                JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "unit");
                    o.put("address", mac);
                } catch (Exception e) {                   
                    e.printStackTrace();
                } 
                keepCallback(mCallbackContext, o.toString());

            } else if(Bg5InsSet.MSG_STRIP_IN.equals(action)){
                String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
                JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "strip in");
                    o.put("address", mac);
                } catch (Exception e) {                   
                    e.printStackTrace();
                } 
                keepCallback(mCallbackContext, o.toString());

            } else if(Bg5InsSet.MSG_GET_BLOOD.equals(action)){
                String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
                JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "get blood");
                    o.put("address", mac);
                } catch (Exception e) {                   
                    e.printStackTrace();
                } 
                keepCallback(mCallbackContext, o.toString());    

            } else if(Bg5InsSet.MSG_STRIP_OUT.equals(action)){
                String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
                JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "strip out");
                    o.put("address", mac);
                } catch (Exception e) {                   
                    e.printStackTrace();
                } 
                keepCallback(mCallbackContext, o.toString());

            } else if(Bg5InsSet.MSG_GET_VALUE.equals(action)){
                String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
                int value = intent.getIntExtra(Bg5InsSet.MSG_GET_VALUE_EXTRA, 0);
                JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "value");
                    o.put("address", mac);
                    o.put("value", value);
                } catch (Exception e) {                   
                    e.printStackTrace();
                } 
                keepCallback(mCallbackContext, o.toString());
            } else if(Bg5InsSet.MSG_GET_HISTORY.equals(action)){
                String mac = intent.getStringExtra(Bg5Manager.MSG_MAC);
                String history = intent.getStringExtra(Bg5InsSet.MSG_GET_HISTORY_EXTRA);
                JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "value");
                    o.put("address", mac);
                    o.put("history", history);
                } catch (Exception e) {                   
                    e.printStackTrace();
                } 
                keepCallback(mCallbackContext, o.toString());
            }
        }
    };
    
    private Timer timer;
	private TimerTask btTask;
	private ArrayList<String> testlist;
	private int index = 0;
    private void TestThread() {
		
		timer = new Timer();
		btTask = new TimerTask() {
			@Override
			public void run() {
				if(index < testlist.size()){
					Intent mIntenttest = new Intent(MSG_TEST_DATA);
	    			mIntenttest.putExtra(MSG_TEST_DATA_EXTRA, testlist.get(index));
					mContext.sendBroadcast(mIntenttest);
					index += 1;
				}else{
					index = 0;
					cancels();
				}
			}
		};
		timer.schedule(btTask, 200, 200);
	}
    
    private void cancels(){
    	if(timer != null){
    		timer.cancel();
    		timer = null;
    	}
    	if(btTask != null){
    		btTask.cancel();
    		btTask = null;
    	}
    }
}