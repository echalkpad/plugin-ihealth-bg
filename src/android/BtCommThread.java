package com.ihealth.plugin;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BtCommThread extends Thread implements BaseComm{

	private static final String TAG = "BtCommThread";
	private BluetoothDevice mDevice = null;
	private BluetoothSocket mSocket = null;
	private InputStream mInputStream = null;
	private OutputStream mOutputStream = null;
	private int mReadBufferLen;
	private byte[] mReadBuffer;
	private Context mContext;
	private BtUnpackageData mBtUnpackageData;
	
	public BtCommThread(Context context, BluetoothDevice device, BluetoothSocket socket) throws IOException{
		this.mContext = context;
		this.mDevice = device;
		this.mSocket = socket;
		this.mInputStream = socket.getInputStream();
		this.mOutputStream = socket.getOutputStream();
		mReadBuffer = new byte[256];
		mBtUnpackageData = new BtUnpackageData();
	}
	
	private Intent mIntent;
	@Override
	public void run() {
		while (true) {
			try {
				mReadBufferLen = mInputStream.read(mReadBuffer);
				if(mReadBufferLen > 0){
					Log.i(TAG, "读取:" + ByteBufferUtil.Bytes2HexString(mReadBuffer, mReadBufferLen));
					mBtUnpackageData.addReadUsbData(mReadBuffer, mReadBufferLen);
				}
			} catch (IOException e) {
				mIntent = new Intent(Bg5Manager.MSG_BT_DISCONNECT);
				mIntent.putExtra(Bg5Manager.MSG_MAC, mDevice.getAddress().replace(":", ""));
				mIntent.putExtra(Bg5Manager.MSG_NAME, mDevice.getName());
				mContext.sendBroadcast(mIntent);
				return;
			}
		}
	}

	private void close(){
		try {
			this.mInputStream.close();
			this.mOutputStream.close();
			this.mSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendData(String mac, byte[] data) {
		try {
			Log.i(TAG, "写入:" + ByteBufferUtil.Bytes2HexString(data, data.length));
			this.mOutputStream.write(data);
			this.mOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void disconnect() {
		close();
	}
	
//	@Override
//	public void addCommNotify(NewDataCallback dataCallBack) {
//		btNotify.attach(dataCallBack);
//	}

//	@Override
//	public void addCommNotify(String mac, BleCommProtocol dataCallBack) {
//		// TODO Auto-generated method stub
//		
//	}
//
	@Override
	public void addCommNotify(BaseCommProtocol dataCallBack) {
		mBtUnpackageData.addBtCommProtocol(dataCallBack);
	}
    
}
