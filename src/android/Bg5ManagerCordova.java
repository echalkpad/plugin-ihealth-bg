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

public class Bg5ManagerCordova extends CordovaPlugin {

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
        		mBg5Manager.scanDevice();
        	}
            return true;
            
        } else if(action.equals("stopDiscovery")){
        	mBg5Manager.cancelScan();
        	return true;
        	
        } else if(action.equals("connectDevice")){
        	String mac = args.getString(0);
        	mBg5Manager.connectDevice(mac);
        	return true;
        	
        } else if (action.equals("setUnit")) {
            String mac = args.getString(0);
            if(!args.isNull(1) && args.getString(1).equals("debug")){
            	testlist = null;
            	testlist = mUnitTest.getTestMeasure();
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
            	mBg5Manager.getBgControl(mac).startMeasure();
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
        	mBg5Manager.getBpControl(mac).interruptMeasure();
            return true;
            
        } else if(action.equals("setBottleMessage")) {
            String mac = args.getString(0);
            List<CallbackContext> list = mapCallback.get(mac);
        	if(list != null){
        		list.add(callbackContext);
        	}else{
        		List<CallbackContext> listtemp = new ArrayList<CallbackContext>();
        		listtemp.add(callbackContext);
        		mapCallback.put(mac, listtemp);
        	}
        	mBg5Manager.getBpControl(mac).enbleOffline();
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
        	mBg5Manager.getBpControl(mac).disenbleOffline();
            return true;
            
        } else if(action.equals("startMeasure")) {
            String mac = args.getString(0);
            if(!args.isNull(1) && args.getString(1).equals("debug")){
            	String str = mUnitTest.getTestHistoryNum();
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
            	mBg5Manager.getBpControl(mac).getOfflineNum();
            }
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
            	mBg5Manager.getBpControl(mac).getOfflineData();
        	}
            return true;
            
        } else if(action.equals("deleteOfflineData")){
        	String mac = args.getString(0);
            int battery = mBg5Manager.getBpControl(mac).getBattery();
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
            List<CallbackContext> list = mapCallback.get(mac);
        	if(list != null){
        		list.add(callbackContext);
        	}else{
        		List<CallbackContext> listtemp = new ArrayList<CallbackContext>();
        		listtemp.add(callbackContext);
        		mapCallback.put(mac, listtemp);
        	}
            return true;
            
        } else if(action.equals("holdLine")){
        	String mac = args.getString(0);
            boolean bool = mBg5Manager.getBpControl(mac).isEnableOffline();
            JSONObject o = null;
            try {
                o = new JSONObject();
                o.put("msg", "isEnableOffline");
                o.put("address", mac);
                o.put("isEnableOffline", bool + "");
            } catch (Exception e) {                   
                e.printStackTrace();
            }
            keepCallback(mCallbackContext, o.toString());
            List<CallbackContext> list = mapCallback.get(mac);
        	if(list != null){
        		list.add(callbackContext);
        	}else{
        		List<CallbackContext> listtemp = new ArrayList<CallbackContext>();
        		listtemp.add(callbackContext);
        		mapCallback.put(mac, listtemp);
        	}
            return true;
            
        } else if(action.equals("disConnectDevice")){
        	String mac = args.getString(0);
        	mBpManager.getBpControl(mac).disconnect();
        	return true;
        	
        } else if(action.equals("setDisconnectCallback")){
        	String mac = args.getString(0);
        	mapdisconnectCallback.put(mac, callbackContext);
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
            if(BpInsSet.MSG_BP_ZOREING.equals(action)) {
            	String message = "zero doing";
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", message);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }
                keepCallback(mCallbackContext, o.toString());
            } else if(BpInsSet.MSG_BP_ZOREOVER.equals(action)) {
                String message = "zero done";
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", message);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }
                keepCallback(mCallbackContext, o.toString());
            } else if(BpInsSet.MSG_BP_MEASURE.equals(action)) {
                String mac = intent.getStringExtra(BpManager.MSG_MAC);
                byte[] bs = intent.getByteArrayExtra(BpInsSet.MSG_BP_MEASURE_EXTRA);
                int pressure = (((bs[0] & 0xff) * 256 + (bs[1] & 0xff)) * 300 + 150) / 4096;               
                byte[] measureData = new byte[8];
                for (int i = 2; i < bs.length; i++) {
                    measureData[i-2] = bs[i];
                }
                String wave = ByteBufferUtil.Bytes2HexString(measureData);                              
                JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "measure doing");
                    o.put("address", mac);
                    o.put("pressure", pressure + "");
                    o.put("wave", wave);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }
                keepCallback(mCallbackContext, o.toString());
            } else if(BpInsSet.MSG_BP_PRESSURE.equals(action)) {
                String mac = intent.getStringExtra(BpManager.MSG_MAC);
                byte[] bs = intent.getByteArrayExtra(BpInsSet.MSG_BP_PRESSURE_EXTRA);
                int pressure = (((bs[0] & 0xff) * 256 + (bs[1] & 0xff)) * 300 + 150) / 4096;
                JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "measure doing");
                    o.put("address", mac);
                    o.put("pressure", pressure + "");
                } catch (Exception e) {                   
                    e.printStackTrace();
                }
                keepCallback(mCallbackContext, o.toString());
            } else if(BpInsSet.MSG_BP_RESULT.equals(action)){
                String mac = intent.getStringExtra(BpManager.MSG_MAC);
                byte[] bs = intent.getByteArrayExtra(BpInsSet.MSG_BP_RESULT_EXTRA);
                mBPmeasureResult = new int[4];
                mBPmeasureResult[0] = (bs[0] & 0xff);
                mBPmeasureResult[1] = (bs[1] & 0xff);
                mBPmeasureResult[2] = (bs[2] & 0xff);
                mBPmeasureResult[3] = (bs[3] & 0xff);
                float sys = mBPmeasureResult[1] + mBPmeasureResult[0];
                float dia = mBPmeasureResult[1];
                int heart = mBPmeasureResult[2];
                int isIHB = (mBPmeasureResult[3] == (byte)0x00?0:1);
                JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "measure done");
                    o.put("address", mac);
                    o.put("highpressure", sys + "");
                    o.put("lowpressure", dia + "");
                    o.put("heartrate", heart + "");
                    if(isIHB == 0){
                        o.put("arrhythmia", "false");
                    }else{
                        o.put("arrhythmia", "true");
                    } 
                } catch (Exception e) {                   
                    e.printStackTrace();
                }                      
                keepCallback(mCallbackContext, o.toString());
            } else if(BpManager.MSG_DISCOVER_DEVICE.equals(action)){
            	String mac = intent.getStringExtra(BpManager.MSG_MAC);
            	String name = intent.getStringExtra(BpManager.MSG_NAME);
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
            } else if(BpManager.MSG_CONNECTING_DEVICE.equals(action)){
            	String mac = intent.getStringExtra(BpManager.MSG_MAC);
            	String name = intent.getStringExtra(BpManager.MSG_NAME);
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
            } else if(BpManager.MSG_CREATE_BLUETOOTHSOCKET_SUCCESS.equals(action)){
            	String mac = intent.getStringExtra(BpManager.MSG_MAC);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "create bluetoothsocket success");
                    o.put("address", mac);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } else if(BpManager.MSG_CREATE_BLUETOOTHSOCKET_FAIL.equals(action)){
            	String mac = intent.getStringExtra(BpManager.MSG_MAC);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "create bluetoothsocket fail");
                    o.put("address", mac);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }  
                mCallbackContext.error(o.toString());
            } else if(BpManager.MSG_CREATE_IOSTREAM_SUCCESS.equals(action)){
            	String mac = intent.getStringExtra(BpManager.MSG_MAC);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "create iostream success");
                    o.put("address", mac);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } else if(BpManager.MSG_CREATE_IOSTREAM_FAIL.equals(action)){
            	String mac = intent.getStringExtra(BpManager.MSG_MAC);
            	JSONObject o = null;
                try {
                   o = new JSONObject();
                   o.put("msg", "create iostream fail");
                   o.put("address", mac);
                } catch (Exception e) {                   
                   e.printStackTrace();
                } 
                mCallbackContext.error(o.toString());
            } else if(BpManager.MSG_AUTHENTICATE.equals(action)){
            	String mac = intent.getStringExtra(BpManager.MSG_MAC);
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
                    o.put("msg", "authenticate device");
                    o.put("address", mac);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } else if(BpInsSet.MSG_BP_OFFLINE_NUM.equals(action)){
            	String mac = intent.getStringExtra(BpManager.MSG_MAC);
            	int num = intent.getIntExtra(BpInsSet.MSG_BP_OFFLINE_NUM_EXTRA, 0);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "offlineNum");
                    o.put("address", mac);
                    o.put("value", num + "");
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } else if(BpInsSet.MSG_BP_OFFLINE_DATA.equals(action)){
            	String mac = intent.getStringExtra(BpManager.MSG_MAC);
            	String msg = intent.getStringExtra(BpInsSet.MSG_BP_OFFLINE_DATA_EXTRA);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "offlineData");
                    o.put("address", mac);
                    o.put("value", msg);
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            	
            } else if(MSG_TEST_DATA.equals(action)){
            	String str = intent.getStringExtra(MSG_TEST_DATA_EXTRA);
            	keepCallback(mCallbackContext, str);
            } else if(BpInsSet.MSG_BP_ANGLE.equals(action)) {
            	String mac = intent.getStringExtra(BpManager.MSG_MAC);
            	int angle = intent.getIntExtra(BpInsSet.MSG_BP_ANGLE_EXTRA, 0);
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "angle");
                    o.put("address", mac);
                    o.put("value", angle + "");
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } else if(BpManager.MSG_DISCOVER_FINISHED.equals(action)){
            	JSONObject o = null;
                try {
                    o = new JSONObject();
                    o.put("msg", "discovery done");
                } catch (Exception e) {                   
                    e.printStackTrace();
                }         
            	keepCallback(mCallbackContext, o.toString());
            } else if(BpInsSet.MSG_BP_IDPS.equals(action)){
            	String mac = intent.getStringExtra(BpManager.MSG_MAC);
            	String str1 = intent.getStringExtra(BpInsSet.MSG_BP_IDPS_PROTOCOL);
            	String str2 = intent.getStringExtra(BpInsSet.MSG_BP_IDPS_ACCESSORYNAME);
            	String str3 = intent.getStringExtra(BpInsSet.MSG_BP_IDPS_FIRMWARE);
            	String str4 = intent.getStringExtra(BpInsSet.MSG_BP_IDPS_HARDWARE);
            	String str5 = intent.getStringExtra(BpInsSet.MSG_BP_IDPS_MODENUMBER);
            	String str6 = intent.getStringExtra(BpInsSet.MSG_BP_IDPS_MANUFACTURE);
            	String str7 = intent.getStringExtra(BpInsSet.MSG_BP_IDPS_SERIALNUMBER);
            	JSONObject o = null;
            	try {
                    o = new JSONObject();
                    o.put("msg", "idps");
                    o.put("mac", mac);
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
            } else if(BpManager.MSG_BT_DISCONNECT.equals(action)){
            	String mac = intent.getStringExtra(BpManager.MSG_MAC);
            	String type = intent.getStringExtra(BpManager.MSG_TYPE);
            	JSONObject o = null;
            	try {
                    o = new JSONObject();
                    o.put("msg", "disconnect");
                    o.put("mac", mac);
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