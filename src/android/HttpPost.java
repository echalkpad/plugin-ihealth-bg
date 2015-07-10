package com.ihealth.plugin;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;

public class HttpPost {
	
	private HttpURLConnection conn = null;
	
	public String requireClass(String path, Map<String, String>params, String encoding) throws ConnectTimeoutException, SocketTimeoutException, Exception{
		String bakn = "", RetBak = "";
		InputStream inStream = null;
		OutputStream outStream = null;
		
		StringBuffer buffer = new StringBuffer();
		if(params != null && !params.isEmpty()){
			for(Map.Entry<String, String> entry : params.entrySet()){
				buffer.append(entry.getKey()).append('=');
				buffer.append(URLEncoder.encode(entry.getValue(), encoding));
				buffer.append('&');
			}
			buffer.deleteCharAt(buffer.length() - 1);
		}
		
		URL url = new URL(path);
		conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(60000);
		conn.setReadTimeout(60000);
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		String bSt = buffer.toString();
		byte[] entity = bSt.getBytes();
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		conn.connect();
		
		outStream = conn.getOutputStream();
		outStream.write(entity);
		outStream.flush();
		
		if(conn.getResponseCode() != HttpURLConnection.HTTP_OK){
			return "";
		}
		
		inStream = conn.getInputStream();
		int num = 0;
		byte[] result = new byte[1024];
		StringBuffer sb = new StringBuffer();
		while((num = inStream.read(result)) != -1){
			bakn = new String(result, 0, num);
			sb.append(URLDecoder.decode(bakn, encoding));
		}
		RetBak = sb.toString();
		
		if(outStream != null)
			outStream.close();
		if(inStream != null)
			inStream.close();
		if(null != null)
			conn.disconnect();
		return RetBak;
	}
	
}
