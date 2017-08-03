package com.tawush.checkmark;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tawush.utils.Consts;
import com.tawush.utils.GoogleMapGeocoding;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity implements OnMapReadyCallback  {

	private GoogleMap mMap;
	
	//DirectionServices s;
	private Handler geoCodingHandler=new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case Consts.CMD_GEOCODING_OK:
				onGeocoding((JSONObject)msg.obj);
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("salam", "hahaha");
		setContentView(R.layout.activity_main);
		
		MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        

        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override 
	public void onMapReady(GoogleMap arg0) {
		// TODO Auto-generated method stub
		this.mMap=arg0;
		new Thread()
        {
        		@Override
        		public void run()
        		{

        			GoogleMapGeocoding.addressToObject(MainActivity.this, "5581 Trent Ct apt111,Alexandria,VA,22311",geoCodingHandler);
        		}
        }.start();
	}
	
	public void onGeocoding(JSONObject obj)
	{
		double lat=0.0;
		double lng=0.0;
		try {
			JSONArray results=obj.getJSONArray("results");
			String status=obj.getString("status");
			if(status.equals("OK"))
			{
				JSONObject geometry=results.getJSONObject(0).getJSONObject("geometry");
				JSONObject location=geometry.getJSONObject("location");
				lat=location.getDouble("lat");
				lng=location.getDouble("lng");
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		LatLng sydney = new LatLng(lat, lng);

        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        MarkerOptions mo1=new MarkerOptions()
                //.title("Sydney")
                //.snippet("The most populous city in Australia.")
                .position(sydney);
        
        mMap.addMarker(mo1);
        
	}
	
	
}
