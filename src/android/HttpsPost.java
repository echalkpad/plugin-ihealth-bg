package com.ihealth.plugin;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

public class HttpsPost {
	
	private final AllowAllHostnameVerifier HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
	
	private X509TrustManager xtm = new X509TrustManager(){

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}
		
	};
	
	private X509TrustManager[] xtmArray = new X509TrustManager[] { xtm };;
	private HttpsURLConnection conn = null;
	
	public String requireClass(String path, Map<String, String> params, String encoding) throws ConnectTimeoutException, SocketTimeoutException ,Exception{
		
		String RetBak = "";
		InputStream inStream = null;
		OutputStream outStream = null;
		

		StringBuffer buffer = new StringBuffer();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				buffer.append(entry.getKey()).append('=');
				buffer.append(URLEncoder.encode(entry.getValue(), encoding));
				buffer.append('&');
			}
			buffer.deleteCharAt(buffer.length() - 1);
		}
		
		URL url = new URL(path);
		conn = (HttpsURLConnection) url.openConnection();
		if (conn instanceof HttpsURLConnection) {
			// Trust all certificates
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(new KeyManager[0], xtmArray, new SecureRandom());
			SSLSocketFactory socketFactory = context.getSocketFactory();
			conn.setSSLSocketFactory(socketFactory);
			conn.setHostnameVerifier(HOSTNAME_VERIFIER);
		}
		conn.setConnectTimeout(60000);
		conn.setReadTimeout(60000);
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		String bSt = buffer.toString();
		byte[] entity = bSt.getBytes();
		
		// 表示设置请求体的类型是文本类型
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// 获得上传信息的字节大小以及长度
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		// 获得输出流,向服务器输出数据
		outStream = conn.getOutputStream();
		outStream.write(entity);
		outStream.flush();

		inStream = conn.getInputStream();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		byte[] resu = new byte[1024];
		while ((len = inStream.read(resu)) != -1 ) {
			baos.write(resu, 0, len);
		}
		baos.close();//内存输入流不需要CLOSE
		byte[] result = baos.toByteArray();//转化为byte数组
		RetBak = URLDecoder.decode(new String(result), encoding);
		if(outStream != null)
			outStream.close();
		if(inStream != null)
			inStream.close();
		if(null != conn)
			conn.disconnect();
		return RetBak;
		
	}
}
