package com.tawush.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetHelper {

	public static byte[] read(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len = inStream.read(buffer)) != -1){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    }  
	public static String getHttp(String _url)
	{
		
		try {
			URL url = new URL(_url);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
	        conn.setConnectTimeout(5000);  
	        conn.setRequestMethod("GET");  
	        if(conn.getResponseCode() == 200){  
	            InputStream inStream = conn.getInputStream();  
	            byte[] data = read(inStream);  
	            String html = new String(data, "UTF-8");  
	            return html;  
	        } 
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
}
