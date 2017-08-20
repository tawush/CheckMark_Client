package com.tawush.checkmark.activity;

import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.zxing.client.android.CaptureActivity;
import com.tawush.checkmark.R;
import com.tawush.checkmark.R.id;
import com.tawush.checkmark.R.layout;
import com.tawush.checkmark.R.menu;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class TaskActivity extends ActionBarActivity implements OnMapReadyCallback,LocationListener{

	private GoogleMap mMap;
	LocationManager locManager;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);
		
		MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        
        this.locManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	}

	
	@Override
	public void onResume()
	{
		locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 200, 10, this);
		super.onResume();
	}
	@Override
	public void onPause()
	{
		locManager.removeUpdates(this);
		super.onPause();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task, menu);
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
	}
	
	public void onScan(View view)
	{
		Intent intent=new Intent(this,CaptureActivity.class);
		startActivity(intent);
	}

	
	/////////Location Manager ////////////
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if(location==null)
		{
			Toast.makeText(this, "location is null", Toast.LENGTH_SHORT).show();
		}else {
			if(mMap!=null)
			{
				LatLng latlng=new LatLng(location.getLatitude(),location.getLongitude());
				mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
				mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
			}
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	////////////Location Manager end ////////////////
}
