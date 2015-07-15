package com.ihealth.plugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.util.Log;

public class Bg5Manager {


	private final static String TAG = "BpManager";
	private final static String BT_BP5_NAME = "BP5";
	private final static String BT_BP7_NAME = "BP7";
	private final static String BT_BG5_NAME = "BG5";
	private static final UUID UUID_SPP = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //SPP
	public static final String MSG_BT_CONNECTED = "com.ihealth.msg.bt.connected";
	public static final String MSG_BT_DISCONNECT = "com.ihealth.msg.bt.disconnect";
	public static final String MSG_MAC = "com.ihealth.msg.mac";
	public static final String MSG_TYPE = "com.ihealth.msg.type";
	public static final String MSG_NAME = "com.ihealth.msg.name";
	
	public static final String MSG_BG5_IDPS = "com.ihealth.msg.idps";
	public static final String MSG_BG5_IDPS_PROTOCOL = "com.ihealth.msg.bg5.idps.protocol";
	public static final String MSG_BG5_IDPS_ACCESSORYNAME = "com.ihealth.msg.bg5.idps.accessoryname";
	public static final String MSG_BG5_IDPS_FIRMWARE = "com.ihealth.msg.bg5.idps.firmware";
	public static final String MSG_BG5_IDPS_HARDWARE = "com.ihealth.msg.bg5.idps.hardware";
	public static final String MSG_BG5_IDPS_MODENUMBER = "com.ihealth.msg.bg5.idps.modenumber";
	public static final String MSG_BG5_IDPS_MANUFACTURE = "com.ihealth.msg.bg5.idps.manufacture";
	public static final String MSG_BG5_IDPS_SERIALNUMBER = "com.ihealth.msg.bg5.idps.serialnumber";
	
	public static final String MSG_DISCOVER_DEVICE = "com.ihealth.msg.discover.device";
	public static final String MSG_CONNECTING_DEVICE = "com.ihealth.msg.connecting.device";
	public static final String MSG_CREATE_BLUETOOTHSOCKET_SUCCESS = "com.ihealth.msg.bluetoothsocket.success";
	public static final String MSG_CREATE_BLUETOOTHSOCKET_FAIL = "com.ihealth.msg.bluetoothsocket.fail";
	public static final String MSG_CREATE_IOSTREAM_SUCCESS = "com.ihealth.msg.create.iostream.success";
	public static final String MSG_CREATE_IOSTREAM_FAIL = "com.ihealth.msg.iostream.fail";
	public static final String MSG_CONNECTED_DEVICE = "com.ihealth.msg.connected.device";
	
	public static final String MSG_DISCOVERING_DEVICE = "com.ihealth.msg.discovering";
    public static final String MSG_DISCOVER_FINISHED = "com.ihealth.msg.discovery.finished";
	
    private static class SingletonHolder {
		static final Bg5Manager INSTANCE = new Bg5Manager();
	}

	public static Bg5Manager getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private Bg5Manager() {

	}

	private Context mContext;
	private BluetoothAdapter mBtAdapter;
	public void init(Context context){
		this.mContext = context;
		mBtAdapter = BluetoothAdapter.getDefaultAdapter();
		initReceiver();
		mapDevices = new HashMap<String, BluetoothDevice>();
	}

	public void destory(){
		unReceiver();
	}
	
	private void initReceiver() {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
		intentFilter.addAction(MSG_BT_CONNECTED);
		intentFilter.addAction(android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		mContext.registerReceiver(mReceiver, intentFilter);
	}
	
	private void unReceiver(){
		mContext.unregisterReceiver(mReceiver);
	}

	private Bg5Control mBg5Control;
	private Map<String, Bg5Control> mapBg5Control = new ConcurrentHashMap<String, Bg5Control>();
	public Bg5Control getBg5Control(String mac){
		return mapBg5Control.get(mac);
	}
	
	private Map<String, BluetoothDevice> mapDevices;
	private Intent mIntent;
	private BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.i(TAG, "发现蓝牙设备 - 名称:" + device.getName() + " --- " + "地址: " + device.getAddress());
                if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
					if (device.getName().contains(BT_BG5_NAME)) {
						Log.d(TAG, "此设备已经绑定了");
						mapDevices.put(device.getAddress().replace(":", ""), device);
						mIntent = new Intent(MSG_DISCOVER_DEVICE);
						mIntent.putExtra(Bg5Manager.MSG_MAC, device.getAddress().replace(":", ""));
						mIntent.putExtra(Bg5Manager.MSG_NAME, device.getName());
						mContext.sendBroadcast(mIntent);
					}
                }
            } else if(MSG_BT_CONNECTED.equals(action)) {
            	String mac = intent.getStringExtra(MSG_MAC);
            	mapBg5Control.put(mac, mBg5Control);
            } else if(android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            	mContext.sendBroadcast(new Intent(MSG_DISCOVER_FINISHED));
            }
		}
		
	};

	public class BtConnectThread extends Thread{

	    	private BluetoothDevice mDevice;
	    	private BluetoothSocket mSocket;
	    	private String mac;
	    	public BtConnectThread(BluetoothDevice device){
	    		this.mDevice = device;
	    		mac = mDevice.getAddress().replace(":","");
	    		try {
					mSocket = mDevice.createRfcommSocketToServiceRecord(UUID_SPP);
	    		} catch (IOException e) {
                	Log.e(TAG, "创建IO流的时候，IOException");
            	}
	    		
	    	}
	    	
	    	@Override
	    	public void run() {
			try {
				mSocket.connect();
				SystemClock.sleep(500);
				Log.i(TAG, "创建socket --- 成功");
				mContext.sendBroadcast(new Intent(MSG_CREATE_BLUETOOTHSOCKET_SUCCESS).putExtra(MSG_MAC,mac));
				createIoStream(mDevice, mSocket);
			} catch (Exception e) {
					Log.e(TAG, "创建socket出现问题异常");
					mContext.sendBroadcast(new Intent(MSG_CREATE_BLUETOOTHSOCKET_FAIL).putExtra(MSG_MAC,mac));
					e.printStackTrace();
				}
	    	}
	    	
	    	private void createIoStream(BluetoothDevice device, BluetoothSocket socket){
	        	BtCommThread btCommThread = null;
				try {
					btCommThread = new BtCommThread(mContext, device, socket);
					btCommThread.start();
					SystemClock.sleep(500);
					mContext.sendBroadcast(new Intent(MSG_CREATE_IOSTREAM_SUCCESS).putExtra(MSG_MAC,mac));
					createControl(btCommThread,device);
				} catch (IOException e) {
					Log.e(TAG, "获得IO流出现异常");
					mContext.sendBroadcast(new Intent(MSG_CREATE_IOSTREAM_FAIL).putExtra(MSG_MAC,mac));
					e.printStackTrace();
				}
	        }
	    	
	    	private void createControl(BtCommThread btCommThread, BluetoothDevice device){
	    		String mac = device.getAddress().replace(":", "");
	    		String name = device.getName();
	    		Log.i(TAG, "createControl mac:" + mac +" - name:" + name);
	    		if(name.contains(BT_BG5_NAME)){
	    			mBg5Control = new Bg5Control(btCommThread, mContext, mac, "BG5"); 
	    			mBg5Control.init();
	    		}
	    		
//	    		if(name.contains(BT_BP5_NAME)){
//	    			mBpControl = new Bg5Control(btCommThread, mContext, mac, "BP5");
//	    			mBpControl.init();
//	    		}else if(name.contains(BT_BP7_NAME)){
//	    			mBpControl = new Bg5Control(btCommThread, mContext, mac, "BP7");
//	    			mBpControl.init();
//	    		}else{
//	    			Log.e(TAG, "没有此设备");
//	    		}
	    	}
	    }
	
	
	public void startDiscovery(){
		if(!mBtAdapter.isDiscovering()){
			mBtAdapter.startDiscovery();
		}
	}

	public void stopDiscovery(){
		if(mBtAdapter.isDiscovering()){
			mBtAdapter.cancelDiscovery();
		}
	}

	public boolean connectDevice(String mac) {
		BluetoothDevice device = mapDevices.get(mac);
		if(device == null){
			return false;
		}
		mIntent = new Intent(MSG_CONNECTING_DEVICE);
        mIntent.putExtra(Bg5Manager.MSG_MAC, device.getAddress().replace(":", ""));
        mIntent.putExtra(Bg5Manager.MSG_NAME, device.getName());
        mContext.sendBroadcast(mIntent);
		if (mBtAdapter.isDiscovering()) {
			mBtAdapter.cancelDiscovery();
		}
		BtConnectThread BtConnectThread = new BtConnectThread(device);
		BtConnectThread.start();
		return true;
	}
	
	public void startMeasure(String mac){
		Bg5Control bg5Control = mapBg5Control.get(mac);
		if(null != bg5Control){
			bg5Control.startMeasure();
		}
	}

	public void setUnit(String mac, int type){
		Bg5Control bg5Control = mapBg5Control.get(mac);
		if(null != bg5Control){
			bg5Control.setUnit(type);
		}
	}

	public void getBattery(String mac){
		Bg5Control bg5Control = mapBg5Control.get(mac);
		if(null != bg5Control){
			bg5Control.getBattery();
		}
	}

	public void setBottleId(String mac, int bottleId){
		Bg5Control bg5Control = mapBg5Control.get(mac);
		if(null != bg5Control){
			bg5Control.setBottleId(bottleId);
		}
	}

	public void getBottleId(String mac){
		Bg5Control bg5Control = mapBg5Control.get(mac);
		if(null != bg5Control){
			bg5Control.getBottleId();
		}
	}
	
	public void setBottleMessage(String mac, String qr, int mun, long data){
		Bg5Control bg5Control = mapBg5Control.get(mac);
		if(null != bg5Control){
			bg5Control.setBottleMessage(qr, mun, data);
		}
	}
	
	public void getBottleMessage(String mac){
		Bg5Control bg5Control = mapBg5Control.get(mac);
		if(null != bg5Control){
			bg5Control.getBottleMessage();
		}
	}
	
	public  void getOfflineData(String mac){
		Bg5Control bg5Control = mapBg5Control.get(mac);
		if(null != bg5Control){
			bg5Control.getOfflineData();
		}
	}
	
	public void deleteOfflineData(String mac){
		Bg5Control bg5Control = mapBg5Control.get(mac);
		if(null != bg5Control){
			bg5Control.deleteOfflineData();
		}
	}
	
	public void disConnectDevice(String mac){
		Bg5Control bg5Control = mapBg5Control.get(mac);
		if(null != bg5Control){
			bg5Control.deleteOfflineData();
		}
	}
	
	public void setDisconnectCallback(){
		
	}
	
	public void holdLink(String mac){
		
	}

}
