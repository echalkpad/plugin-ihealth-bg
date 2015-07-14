package com.ihealth.plugin;


import java.util.Random;

import android.util.Log;

public class IdentifyIns {

    private static final String TAG = "IdentifyIns";
    protected byte[] R1 = new byte[16];// 随机数R1——用于认证发给下位机
    protected byte[] R1_stroke = new byte[16];// R1'——用于保存下位机传上来的R1'
    protected byte[] R1_back = new byte[16];// R1——对下位机传上来的R1'进行解密后生成的R1，用于比较随机数R1

    protected byte[] R2_stroke = new byte[16];// R2'——用于保存下位机传上来的R2'
    protected byte[] R2 = new byte[16];// R2——对R2'解密后生成的R2
    protected byte[] deviceID = new byte[16];// 下位机传上来的产品ID
    protected byte[] K = new byte[16];
    private static byte[] KaA9 = {
            (byte) 0xB2, (byte) 0xDB, (byte) 0x8A, (byte) 0x30,
            (byte) 0x1E, (byte) 0x33, (byte) 0xE6, (byte) 0xBA,
            (byte) 0xC3, (byte) 0x0D, (byte) 0x42, (byte) 0xD9,
            (byte) 0x4B, (byte) 0x26, (byte) 0xE8, (byte) 0x53
    };
    // 血压计
    private static byte[] KaA1 = {
            (byte) 0x7E, (byte) 0xAE, (byte) 0x87, (byte) 0x38, // 正式认证过程
            (byte) 0x82, (byte) 0x62, (byte) 0x28, (byte) 0x23, // 正式认证过程
            (byte) 0x0C, (byte) 0xD4, (byte) 0xBA, (byte) 0xE0, // 正式认证过程
            (byte) 0x97, (byte) 0xB6, (byte) 0xC5, (byte) 0x03
            // 正式认证过程
    };

    private static byte[] KaA2 = { 
            (byte) 0xFF, (byte) 0xD5, (byte) 0x7A, (byte) 0x65, 
            (byte) 0x75, (byte) 0x48, (byte) 0x7E, (byte) 0x71, 
            (byte) 0x63, (byte) 0xCD, (byte) 0xED, (byte) 0x4B,
            (byte) 0x5D, (byte) 0x93, (byte) 0x11, (byte) 0x9C 
    };

    private static byte[] KaWeixinA1 = {
            (byte) 0x92, (byte) 0xE0, (byte) 0x83, (byte) 0x5B,
            (byte) 0xF9, (byte) 0x7F, (byte) 0xC9, (byte) 0xA3,
            (byte) 0xAA, (byte) 0xBD, (byte) 0x46, (byte) 0x6A,
            (byte) 0x18, (byte) 0x98, (byte) 0xE9, (byte) 0x83
    };

    private static byte[] keyA6 = {
            (byte) 0x8A, (byte) 0xF0, (byte) 0xC3,
            (byte) 0x5D, (byte) 0x7E, (byte) 0xAB, (byte) 0xBD, (byte) 0x82,
            (byte) 0x84, (byte) 0xEB, (byte) 0xCF, (byte) 0x78, (byte) 0xA3,
            (byte) 0x2B, (byte) 0xD9, (byte) 0x8E
    };

    private static byte[] keyAm3 = {
            (byte) 0x46, (byte) 0x07, (byte) 0x73,
            (byte) 0xF1, (byte) 0x07, (byte) 0xD0, (byte) 0x06, (byte) 0x05,
            (byte) 0x9A, (byte) 0x50, (byte) 0xEA, (byte) 0x1A, (byte) 0x2C,
            (byte) 0x5D, (byte) 0xB5, (byte) 0xB0
    };

    private static byte[] keyAm3S = {
            (byte) 0x37, (byte) 0x6A, (byte) 0xD1,
            (byte) 0xA9, (byte) 0x22, (byte) 0x7C, (byte) 0xE3, (byte) 0x2C,
            (byte) 0xFF, (byte) 0x03, (byte) 0xEE, (byte) 0x31, (byte) 0xBD,
            (byte) 0xEC, (byte) 0x6F, (byte) 0x5E
    };

    private static byte[] keyAc = {
            (byte) 0xFA, (byte) 0xA3, (byte) 0x53,
            (byte) 0x92, (byte) 0xCD, (byte) 0x6F, (byte) 0x29, (byte) 0x0F,
            (byte) 0x33, (byte) 0x59, (byte) 0x4B, (byte) 0xCC, (byte) 0x15,
            (byte) 0xBF, (byte) 0xC7, (byte) 0x25
    };

    private boolean isWeixin = false;

    protected byte[] identify(boolean b, byte deviceType) {
        isWeixin = b;
        byte[] r1 = setR1();
        int len = r1.length + 2;
        byte[] returnCommand = new byte[len];
        byte commandID = (byte) 0xfa;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        for (int i = 2; i < returnCommand.length; i++) {
            returnCommand[i] = r1[i - 2];
        }
        return returnCommand;
    }

    protected byte[] deciphering(byte[] returnData, String strType, byte bType) {
        Log.i(TAG, "returnData : " + ByteBufferUtil.Bytes2HexString(returnData));
        if (returnData.length == 48) {
            // 解析ID R1' 和 R2'
            for (int i = 0; i < 16; i++) {
                deviceID[i] = returnData[i];
                R1_stroke[i] = returnData[i + 16];
                R2_stroke[i] = returnData[i + 32];
            }
        } else {
            Log.e(TAG, "R1 + R2 + ID 的长度不是48");
        }
        K = XXTEA.encrypt(reverseByteArray(deviceID), getKa(strType));
        Log.i(TAG, "      k:" + ByteBufferUtil.Bytes2HexString(K));
        R1_back = XXTEA.encrypt(reverseByteArray(R1_stroke), K);
        Log.i(TAG, "R1_back:" + ByteBufferUtil.Bytes2HexString(R1_back));
        R2 = XXTEA.encrypt(reverseByteArray(R2_stroke), K);
        Log.i(TAG, "     R2:" + ByteBufferUtil.Bytes2HexString(R2));
        byte[] _R2 = reverseByteArray(R2);
        Log.i(TAG, "    _R2:" + ByteBufferUtil.Bytes2HexString(_R2));
        return decipheringMessage(_R2, bType);
    }

    private byte[] getKa(String type) {
        if (type.contains("BP")) {
            if (isWeixin) {
                return KaWeixinA1;
            } else {
                return KaA1;
            }
        } else if (type.contains("HS4")) {
            return keyA6;
        } else if (type.equals("AM3")) {
            return keyAm3;
        } else if (type.equals("AM3S")) {
            return keyAm3S;
        } else if (type.equals("PO3")) {
            return keyAc;
        } else if (type.equals("HS5")) {
            return KaA9;
        } else if (type.equals("BG5")) {
            return KaA2;
        }
        return K;
    }

    private byte[] decipheringMessage(byte[] r2, byte deviceType) {
        int len = r2.length + 2;
        byte[] returnCommand = new byte[len];
        byte commandID = (byte) 0xFC;
        returnCommand[0] = deviceType;
        returnCommand[1] = commandID;
        for (int i = 2; i < returnCommand.length; i++) {
            returnCommand[i] = r2[i - 2];
        }
        return returnCommand;

    }

    protected byte[] reverseByteArray(byte[] data) {
        byte[] result = new byte[16];
        for (int i = 0; i < 4; i++) {
            result[i] = data[3 - i];
            result[i + 4] = data[7 - i];
            result[i + 8] = data[11 - i];
            result[i + 12] = data[15 - i];
        }
        return result;
    }

    protected byte[] setR1() {
        new Random(System.currentTimeMillis()).nextBytes(R1);
        for (int i = 0; i < 16; i++) {
            if (R1[i] < 0)
                R1[i] = (byte) (0 - R1[i]);
        }
        Log.i(TAG, "R1: " + ByteBufferUtil.Bytes2HexString(R1));
        return reverseByteArray(R1);
    }

}
