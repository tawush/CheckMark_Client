package com.tawush.checkmark.activity;

import android.support.v7.app.ActionBarActivity;

import com.tawush.checkmark.R;
import com.tawush.checkmark.R.id;
import com.tawush.checkmark.R.layout;
import com.tawush.checkmark.R.menu;
import com.tawush.utils.Consts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	private ProgressDialog pd;
	Handler loginHandler=new Handler() {
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case Consts.CMD_ERR:
				pd.hide();
				Toast.makeText(getApplicationContext(), "Network error ...", Toast.LENGTH_SHORT).show();
				break;
			case Consts.CMD_SUC:
				pd.hide();
				Intent intent=new Intent(LoginActivity.this,TaskActivity.class);
				startActivity(intent);
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		this.pd=new ProgressDialog(this);
		pd.setMessage("Logining ...");
		pd.setCancelable(false);
		pd.setCanceledOnTouchOutside(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void btnLoginPress(View view)
	{
		String email=((EditText)this.findViewById(R.id.editEmail)).getText().toString().trim();
		String password=((EditText)this.findViewById(R.id.editPassword)).getText().toString().trim();
		if(email.equals(""))
		{
			Toast.makeText(this, "email cant be empty!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(password.equals(""))
		{
			Toast.makeText(this, "password cant be empty!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		pd.show();
		new Thread()
		{
			@Override
			public void run()
			{
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				loginHandler.sendEmptyMessage(Consts.CMD_SUC);
			}
		}.start();
		
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
}
