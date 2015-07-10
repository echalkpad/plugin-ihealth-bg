package com.ihealth.plugin;


import java.util.LinkedList;
import java.util.Queue;
import android.util.Log;

public class BtUnpackageData {

	private static final String TAG = "BtUnpackageData::";
	private byte[] readBuffer;
	private Queue<Byte> readDataQueue = new LinkedList<Byte>();
	private int quencesequenceID;
	private boolean isHead = false;
	private BaseCommProtocol mBtCommProtocol;
	public void addBtCommProtocol(BaseCommProtocol btCommCallback){
		this.mBtCommProtocol = btCommCallback;
	}
	
	public void addReadUsbData(byte[] data, int count) {
		for (int i = 0; i < count; i++) {
			readDataQueue.offer(data[i]);
		}
		isFullCommand();
		isFullCommand();
	}
	
	private void isFullCommand() {
		int temp;
		byte[] datas = null;
		if (readDataQueue.size()<6) {
//			Log.i(TAG, "没有收全指令");
			return;
		}
		int length = readDataQueue.peek() & 0xff;
		if (160 == length) {
			isHead = true;
			readDataQueue.poll();
		}
		if(!isHead){
			readDataQueue.poll();
			return;
		}
		temp = readDataQueue.peek() & 0xff;
		int len = temp + 3;
		if (readDataQueue.size() >= temp + 2) {
			datas = new byte[len];
			datas[0] = (byte) 0xA0;
			for (int i = 1; i < len; i++) {
				Byte b = readDataQueue.poll().byteValue();
				if (null != b) {
					datas[i] = b;
				}
			}

		} else {
//			Log.i(TAG, "这条指令没有收全");
			return;
		}
		if(datas.length <= 3){
			return;
		}
		temp = (datas[3] & 0xff);
		if (quencesequenceID != temp) {
			quencesequenceID = temp;
		} else {
//			Log.i(TAG, "这条指令已经收到了");
//			return;
		}
		readBuffer = new byte[datas.length];
		for (int i = 0; i < datas.length; i++) {
			readBuffer[i] = datas[i];
		}
		Log.i(TAG, "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Log.i(TAG, "收到了数据:" + ByteBufferUtil.Bytes2HexString(readBuffer, readBuffer.length));
		Log.i(TAG, "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		isHead = false;
		mBtCommProtocol.unPackageData(readBuffer);
	}
}
