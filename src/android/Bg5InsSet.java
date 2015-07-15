package com.ihealth.plugin;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Bg5InsSet extends IdentifyIns implements NewDataCallback{

	
	private static final String TAG = "Bg5InsSet";
	
	public static final String MSG_GET_BOTTLEID = "com.ihealth.bt.bg5.getbottleid";
	public static final String MSG_GET_BOTTLEID_EXTRA = "com.ihealth.bt.bg5.getbottleid.extra";
	public static final String MSG_GET_CODE = "com.ihealth.bt.bg5.code";
	public static final String MSG_GET_CODE_EXTRA = "com.ihealth.bt.bg5.code.extra";
	public static final String MSG_GET_LEFTNUM = "com.ihealth.bt.bg5.leftnum";
	public static final String MSG_GET_EXPIRECTIME = "com.ihealth.bt.bg5.ecpirectime";
	public static final String MSG_GET_BATTERY = "com.ihealth.bt.bg5.getbattery";
	public static final String MSG_GET_BATTERY_EXTRA = "com.ihealth.bt.bg5.getbattery.extra";
	public static final String MSG_ERROR = "com.ihealth.bt.bg5.error";
	public static final String MSG_ERROR_EXTRA = "com.ihealth.bt.bg5.error.extra";
	public static final String MSG_STRIP_IN = "com.ihealth.bt.bg5.strip.in";
	public static final String MSG_GET_BLOOD = "com.ihealth.bt.bg5.getblood";
	public static final String MSG_GET_VALUE = "com.ihealth.bt.bg5.getvalue";
	public static final String MSG_GET_VALUE_EXTRA = "com.ihealth.bt.bg5.value.extra";
	public static final String MSG_STRIP_OUT = "com.ihealth.bt.bg5.strip.out";
	public static final String MSG_GET_HISTORY = "com.ihealth.bt.bg5.get.history";
	public static final String MSG_GET_HISTORY_EXTRA = "com.ihealth.bg.bg5.get.history.extra";
	public static final String MSG_SET_UNIT = "com.ihealth.bg.bg5.set.unit";
	
	private static final byte deviceType = (byte) 0xa2;
	private Context mContext;
	private BtCommProtocol btcm;
	private String mAddress;
	private String mType;

	private String[] bloodStr = new String[]{
			"01",
			"030168003C003C01F4025814015E3200A0",
			"03D100320046005A006E" + "0082009600AA00B400E6" + "0104011801400168017C" + "0190019A04C604A8048B"
					+ "04700456043E0428041D" + "03E803E803E803B303A3"
					+ "039D039903981027383D" + "4E6F", "03", "0001"	
	};
	
	
	
	public Bg5InsSet(BaseComm com, Context context, String mac, String type){
		this.btcm = new BtCommProtocol(com, this);
		this.mContext = context;
		this.mAddress = mac;
		this.mType = type;
	}

	public void getIdps() {
		byte[] returnCommand = new byte[2];
		byte commandID = (byte) 0xf1;
		returnCommand[0] = deviceType;
		returnCommand[1] = commandID;
		btcm.packageData(null, returnCommand);
	}

	public void identify(boolean b) {
		btcm.packageData(null, identify(b, deviceType));
	}
	
	private void ask() {
		byte[] returnCommand = new byte[1];
		returnCommand[0] = deviceType;
		btcm.packageDataAsk(returnCommand);
	}
	
	public void setTime(){
		byte[] returnCommand = new byte[8];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getDefault());
		String date = sdf.format(new Date(System.currentTimeMillis()));

		Integer year = Integer.parseInt(date.split(" ")[0].split("-")[0]) - 2000;
		Integer month = Integer.parseInt(date.split(" ")[0].split("-")[1]);
		Integer day = Integer.parseInt(date.split(" ")[0].split("-")[2]);

		Integer hour = Integer.parseInt(date.split(" ")[1].split(":")[0]);
		Integer min = Integer.parseInt(date.split(" ")[1].split(":")[1]);
		Integer sec = Integer.parseInt(date.split(" ")[1].split(":")[2]);
		byte commandID = (byte) 0x21;
		returnCommand[0] = deviceType;
		returnCommand[1] = commandID;
		returnCommand[2] = year.byteValue();
		returnCommand[3] = month.byteValue();
		returnCommand[4] = day.byteValue();
		returnCommand[5] = hour.byteValue();
		returnCommand[6] = min.byteValue();
		returnCommand[7] = sec.byteValue();
		btcm.packageData(null, returnCommand);
	}
	
	public void sendCode(String qr){
		byte[] allCodeBuf = complieQR(qr);
		byte[] returnCommand = new byte[128];
		byte commandID = (byte) 0x20;
		returnCommand[0] = deviceType;
		returnCommand[1] = commandID;
		for (int i = 0; i < returnCommand.length; i++) {
			returnCommand[2 + i] = allCodeBuf[i];
		}
		btcm.packageData(null, returnCommand);
	}
	
	public void setUnit(int type){
		byte[] returnCommand = new byte[5];
		byte commandID = (byte) 0x23;
		returnCommand[0] = deviceType;
		returnCommand[1] = commandID;
		returnCommand[2] = (byte) type;
		returnCommand[3] = 0;
		returnCommand[4] = 0;
		btcm.packageData(null, returnCommand);
	}

	public void setBottleId(int userId){
		byte[] userIds = ByteBufferUtil.intTo4Byte(userId);
		byte[] returnCommand = new byte[6];
		byte commandID = (byte) 0x2d;
		returnCommand[0] = deviceType;
		returnCommand[1] = commandID;
		returnCommand[2] = userIds[0];
		returnCommand[3] = userIds[1];
		returnCommand[4] = userIds[2];
		returnCommand[5] = userIds[3];
		btcm.packageData(null, returnCommand);
	}
	
	public void getBottleId(){
		byte[] returnCommand = new byte[6];
		byte commandID = (byte) 0x2e;
		returnCommand[0] = deviceType;
		returnCommand[1] = commandID;
		returnCommand[2] = (byte)0;
		returnCommand[3] = (byte)0;;
		returnCommand[4] = (byte)0;;
		returnCommand[5] = (byte)0;;
		btcm.packageData(null, returnCommand);
	}
	
	/**
	 * 开始测量 命令ID：0x31 1代表血液测量方式, 2代表质控液测量方式。
	 */
	public void startMeasure(int state) {

		byte[] returnCommand = new byte[5];
		byte commandID = (byte) 0x31;
		returnCommand[0] = deviceType;
		returnCommand[1] = commandID;
		returnCommand[2] = (byte) 0x00;
		returnCommand[3] = (byte) 0x00;
		if(state == 1){
			returnCommand[4] = (byte) 0x01;
		}else if(state == 2){
			returnCommand[4] = (byte) 0x02;
		}
		btcm.packageData(null, returnCommand);
		
	}
	
	private byte[] complieQR(String qr) {
		byte[] allCodeBuf;
		allCodeBuf = new byte[126];
		byte[] someCodeBuf = ByteBufferUtil.hexStringToByte(qr);
		byte[] temp = null;
		for (int i = 0; i < 6; i++) {
			allCodeBuf[i] = someCodeBuf[i];
		}
		allCodeBuf[6] = 0x01;
		int sampleTime = (int) ((someCodeBuf[6] & 0xFF) * 0.1 * 1000 / 20);
		allCodeBuf[7] = (byte) ((sampleTime & 0xFF00) >> 8);
		allCodeBuf[8] = (byte) (sampleTime & 0x00FF);

		sampleTime = (int) ((someCodeBuf[7] & 0xFF) * 0.1 * 1000 / 20);
		allCodeBuf[9] = (byte) ((sampleTime & 0xFF00) >> 8);
		allCodeBuf[10] = (byte) (sampleTime & 0x00FF);
		sampleTime = (int) ((someCodeBuf[8] & 0xFF) * 0.1 * 1000 / 20);
		allCodeBuf[11] = (byte) ((sampleTime & 0xFF00) >> 8);
		allCodeBuf[12] = (byte) (sampleTime & 0x00FF);

		// 13-29
		temp = ByteBufferUtil.hexStringToByte(bloodStr[1]);
		for (int i = 13; i < 30; i++) {
			allCodeBuf[i] = temp[i - 13];
		}
		sampleTime = (int) ((someCodeBuf[9] & 0xFF) * 0.1 * 1000 / 20);
		allCodeBuf[30] = (byte) ((sampleTime & 0xFF00) >> 8);
		allCodeBuf[31] = (byte) (sampleTime & 0x00FF);
		allCodeBuf[32] = someCodeBuf[10];
		allCodeBuf[33] = someCodeBuf[11];
		temp = ByteBufferUtil.hexStringToByte(bloodStr[2]);
		for (int i = 34; i < 106; i++) {
			allCodeBuf[i] = temp[i - 34];
		}
		allCodeBuf[106] = someCodeBuf[12];
		allCodeBuf[107] = someCodeBuf[13];
		allCodeBuf[108] = someCodeBuf[14];
		allCodeBuf[109] = someCodeBuf[15];
		allCodeBuf[110] = someCodeBuf[16];
		allCodeBuf[111] = someCodeBuf[17];
		allCodeBuf[112] = someCodeBuf[18];
		allCodeBuf[113] = someCodeBuf[19];
		allCodeBuf[114] = someCodeBuf[20];
		allCodeBuf[115] = someCodeBuf[21];
		allCodeBuf[116] = ByteBufferUtil.hexStringToByte(bloodStr[3])[0];
		allCodeBuf[117] = someCodeBuf[22];
		// 试纸条数
		allCodeBuf[118] = someCodeBuf[23];
		Integer year = (someCodeBuf[24] & 0xFE) >> 1;
		Integer month = (someCodeBuf[24] & 0x01) * 8 + ((someCodeBuf[25] & 0xE0) >> 5);
		Integer day = (int) (someCodeBuf[25] & 0x1F);
		allCodeBuf[119] = year.byteValue();
		allCodeBuf[120] = month.byteValue();
		allCodeBuf[121] = day.byteValue();
		CrcCheck cc = new CrcCheck(ByteBufferUtil.hexByteToInt(allCodeBuf, 122));
		int chechsum = cc.getCRCValue();

		allCodeBuf[122] = (byte) ((chechsum & 0Xff00) >> 8);
		allCodeBuf[123] = (byte) (chechsum & 0X00ff);

		allCodeBuf[124] = (byte) 0x00;
		allCodeBuf[125] = (byte) 0x01;
		return allCodeBuf;
	}
	
	
	@Override
	public void haveNewData(int what, int stateId, byte[] returnData) {
		Log.i(TAG, "what:" + what);
		// if (0 == stateId) {
		// 	ask();
		// }
		switch (what) {
		case 0xfb:
			byte[] req = deciphering(returnData, mType, deviceType);
			btcm.packageData(null, req);
			break;
		case 0xfd:
			Log.i(TAG, "认证成功");
			Intent mIntentfd = new Intent(Bg5Manager.MSG_BT_CONNECTED);
			mIntentfd.putExtra(Bg5Manager.MSG_MAC, mAddress);
			mContext.sendBroadcast(mIntentfd);	
			break;
		case 0xfe:
			break;
		
		case 0x23:
			Intent intent23 = new Intent(MSG_SET_UNIT);
			intent23.putExtra(Bg5Manager.MSG_MAC, mAddress);
			mContext.sendBroadcast(intent23);
			break;
		
		case 0x2e:
			Intent intent2e = new Intent(MSG_GET_BOTTLEID);
			intent2e.putExtra(Bg5Manager.MSG_MAC, mAddress);
			int value = (res[3] & 0xff) | ((res[2] << 8) & 0xff00) // | 表示安位或
					| ((res[1] << 24) >>> 8) | (res[0] << 24);
			intent2e.putExtra(MSG_GET_BOTTLEID_EXTRA, value);
			mContext.sendBroadcast(intent2e);
			break;

		case 0xf0:
			String str = "";
			// com.jiuan.BPV20
			byte[] protocolString = new byte[15];
			// BP Monitor
			byte[] accessoryname = new byte[10];
			// 1.0.2
			byte[] fwVer = new byte[3];
			// 1.0.1
			byte[] hwVer = new byte[3];
			// iHealth
			byte[] manufacture = new byte[7];
			// BP5 110700
			byte[] modeNumber = new byte[9];
			try {
				for (int i = 0; i < protocolString.length; i++) {
					protocolString[i] = returnData[i + 0];
				}
				for (int i = 0; i < accessoryname.length; i++) {
					accessoryname[i] = returnData[i + 16];
				}
				for (int i = 0; i < fwVer.length; i++) {
					fwVer[i] = returnData[i + 32];
				}
				for (int i = 0; i < hwVer.length; i++) {
					hwVer[i] = returnData[i + 35];
				}
				for (int i = 0; i < manufacture.length; i++) {
					manufacture[i] = returnData[i + 38];
				}
				for (int i = 0; i < modeNumber.length; i++) {
					modeNumber[i] = returnData[i + 54];
				}
				String str1 = new String(protocolString, "UTF-8");
				str += "协议版本：" + str1 + "\n";
				String str2 = new String(accessoryname, "UTF-8");
				str += "附件署名：" + str2 + "\n";
				String str3 = ByteBufferUtil.Bytes2HexString(fwVer);
				str += "固件版本：" + str3 + "\n";
				String str4 = ByteBufferUtil.Bytes2HexString(hwVer);
				str += "硬件版本：" + str4 + "\n";
				String str5 = new String(modeNumber, "UTF-8");
				str += "附件型号：" + str5 + "\n";
				String str6 = new String(manufacture, "UTF-8");
				str += "生产商：" + str6 + "\n";
				Log.i(TAG, "IDPS:" + str);
				if(str.contains("com.jiuan.BPV23")){
					identify(true);
				}else{
					identify(false);
				}
				Intent mIntentf0 = new Intent(Bg5Manager.MSG_BG5_IDPS);
				mIntentf0.putExtra(Bg5Manager.MSG_MAC, mAddress);
				mIntentf0.putExtra(Bg5Manager.MSG_TYPE, mType);
				mIntentf0.putExtra(Bg5Manager.MSG_BG5_IDPS_PROTOCOL, str1);
				mIntentf0.putExtra(Bg5Manager.MSG_BG5_IDPS_ACCESSORYNAME, str2);
				mIntentf0.putExtra(Bg5Manager.MSG_BG5_IDPS_FIRMWARE, str3);
				mIntentf0.putExtra(Bg5Manager.MSG_BG5_IDPS_HARDWARE, str4);
				mIntentf0.putExtra(Bg5Manager.MSG_BG5_IDPS_MODENUMBER, str5);
				mIntentf0.putExtra(Bg5Manager.MSG_BG5_IDPS_MANUFACTURE, str6);
				mIntentf0.putExtra(Bg5Manager.MSG_BG5_IDPS_SERIALNUMBER, mAddress);
				mContext.sendBroadcast(mIntentf0);
								
			} catch (UnsupportedEncodingException e) {
				Log.e("control", "IDPS 信息转换失败");
				e.printStackTrace();
			} catch (Exception e1){
				e1.printStackTrace();
			}
			break;
		case 0xff:
			identify(false);
			break;
		default:
			Log.i(TAG, "没有这条指令");
			break;
		}
	}
	
	private String convertOffline(byte[] datas){
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();	
		int num = (datas.length - 2)/8;
		int j = 0;
		try {
		for(int i = 0; i < num; i++){
			String year = String.valueOf(2000 + (int)(datas[j + 2]&0x7f));
			String month = String.valueOf((datas[j + 3]&0x0f));
			String day = String.valueOf((datas[j + 4]&0x1f));
			String hour = String.valueOf((datas[j + 5]&0x3f));
			String min = String.valueOf((datas[j + 6]&0x3f));
			int temp = (int)(datas[j + 7]&0xff);
			int dia = (int)(datas[j + 8]&0xff);
			int sys = temp + dia;
			int pulse = (int)(datas[j + 9]&0xff);
			int ahr = (datas[j + 2]&0xff) >> 7;
			int hsd = (datas[j + 3]&0xff) >> 7;
			String dateStr = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + "00";
			JSONObject stoneObject = new JSONObject();
			stoneObject.put("date", dateStr);
			stoneObject.put("sys", String.valueOf(sys));
			stoneObject.put("dia", String.valueOf(dia));
			stoneObject.put("pulse", String.valueOf(pulse));
			stoneObject.put("ahr", String.valueOf(ahr));
			stoneObject.put("hsd", String.valueOf(hsd));
			array.put(stoneObject);
			j += 8;
		}
		object.putOpt("offlineData", array);
		} catch (JSONException e){
			e.printStackTrace();
		}
		return object.toString();
	}


}
