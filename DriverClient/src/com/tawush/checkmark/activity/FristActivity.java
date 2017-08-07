package com.tawush.checkmark.activity;

import com.tawush.checkmark.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class FristActivity extends ActionBarActivity{

	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		this.setContentView(R.layout.activity_frist);
	}
	
	public void btnLoginPress(View view)
	{
		Intent intent=new Intent(this,LoginActivity.class);
		this.startActivity(intent);
	}
}
