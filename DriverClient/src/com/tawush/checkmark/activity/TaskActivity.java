package com.tawush.checkmark.activity;

import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.zxing.client.android.CaptureActivity;
import com.tawush.checkmark.R;
import com.tawush.checkmark.R.id;
import com.tawush.checkmark.R.layout;
import com.tawush.checkmark.R.menu;
import com.tawush.utils.UMarkNumPanel;

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
import android.widget.EditText;
import android.widget.Toast;

public class TaskActivity extends ActionBarActivity implements OnMapReadyCallback{

	private GoogleMap gMap;
	UMarkNumPanel numPanel;
	
	LocationManager locManager;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);
		
		MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        
        numPanel=new UMarkNumPanel();
        //this.locManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	}

	
	@Override
	public void onResume()
	{
		super.onResume();
	}
	@Override
	public void onPause()
	{
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
		this.gMap=arg0;
		this.gMap.setMyLocationEnabled(true);
		
		addMapPoint(38.812503,-77.1169519);
		addMapPoint(38.8123585,-77.1174088);
		addMapPoint(38.8120453,-77.117747);
		
		
		this.gMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker arg0) {
				// TODO Auto-generated method stub
				numPanel.calcMark(arg0);
				return false;
			}
		});
	}
	
	public void onScan(View view)
	{
		//Intent intent=new Intent(this,CaptureActivity.class);
		//startActivity(intent);
		String val=this.gMap.getMyLocation().getLatitude()+","+this.gMap.getMyLocation().getLongitude();
		EditText ed=(EditText) this.findViewById(R.id.editText1);
		ed.append(val+"\n");
		
	} 
	
	public void addMapPoint(double lt,double lng)
	{
		LatLng sydney = new LatLng(lt, lng);
		MarkerOptions mo=new MarkerOptions().position(sydney);
        gMap.setMyLocationEnabled(true);
        gMap.addMarker(mo);
        
        
        
	}

	
	
}
