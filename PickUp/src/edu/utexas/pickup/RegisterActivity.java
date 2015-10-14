package edu.utexas.pickup;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends Activity {
	
	private static final String TAG = "registeractivity";
	
	private int startLoginActivity()
	{
		Log.v(TAG, "User pressed the register button");
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		return 8;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
        final Button Reg_button = (Button) findViewById(R.id.register_button);
        View.OnClickListener register = new View.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		startLoginActivity();        		
        	}
        };
        Reg_button.setOnClickListener(register); 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
        ActionBar bar = getActionBar();
        // blue
        // bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2694ed")));
        // gray
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#052937")));
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
}
