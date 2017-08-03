package com.tawush.utils;

import org.json.JSONException;
import org.json.JSONObject;

import com.tawush.checkmark.MainActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class GoogleMapGeocoding {
	public static void addressToObject(Context context,String address,Handler handler) 
	{
		
		String url="https://maps.googleapis.com/maps/api/geocode/json?address="+address.replace(' ', '+')+"&key="+getGeoKey(context); 
		String _json=NetHelper.getHttp(url);
		if(_json!=null)
		{
			Log.e("geo", _json);
			try {
				JSONObject json=new JSONObject(_json);
				if(json!=null)
				{
					Message msg=handler.obtainMessage();
					msg.what=Consts.CMD_GEOCODING_OK;
					msg.obj=json;
					handler.sendMessage(msg);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static String getGeoKey(Context context)
	{
		try {
			ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
            PackageManager.GET_META_DATA);
			String val =appInfo.metaData.getString("geocodingKey");
			return val;
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
}
