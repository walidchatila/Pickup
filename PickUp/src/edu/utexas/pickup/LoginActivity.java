package edu.utexas.pickup;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends Activity {
	
	private static final String TAG = "loginactivity";



	private int startRegisterActivity()
	{
		Log.v(TAG, "User pressed the register button");
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
		return 8;
	}
	
	private int startSportSelectionActivity()
	{
		Intent intent = new Intent(this, SportSelectionActivity.class);
		startActivity(intent);
		return 9;
	}
	
	private void signIn(){

		EditText usernameEdit = (EditText) findViewById(R.id.usernameText);
		EditText passwordEdit = (EditText) findViewById(R.id.passwordText);
	
		String username = usernameEdit.getText().toString();
		String password = passwordEdit.getText().toString();
		
		SharedPreferences sharedPref = getSharedPreferences("file", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();		
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        final Button Reg_button = (Button) findViewById(R.id.register_button);
        View.OnClickListener register = new View.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		startRegisterActivity();        		
        	}
        };
        Reg_button.setOnClickListener(register);    
    }
    
    @Override
    protected void onStart() {
    	Log.v(TAG, "started the login activity");
    	super.onStart();
    	 SharedPreferences sharedPref = getSharedPreferences("file", Context.MODE_PRIVATE);
		 SharedPreferences.Editor editor = sharedPref.edit();
		 String username = sharedPref.getString("username",null);
		 String password = sharedPref.getString("password",null);
		 Log.i(TAG, "username is " + username);
		if (username != null && password !=null )
		{	Log.i(TAG, "username is " + username);
			startSportSelectionActivity();
			finish();
		}
		else{
	    	final Button Sel_button = (Button) findViewById(R.id.login_button);
			View.OnClickListener select = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					signIn();
					startSportSelectionActivity();
					finish();
			}
			};
			Sel_button.setOnClickListener(select);
		}
    }
    
    @Override
    protected void onRestart() {
    	Log.i(TAG, "restarted the login activity");
    	super.onRestart();
    }
    
    @Override
    protected void onPause() {
    	Log.i(TAG, "pause the login activity");
    	super.onPause();

    }
    
    @Override
    protected void onStop() {
    	Log.i(TAG, "stopped the login activity");
    	super.onStop();
    }
    
    @Override
    protected void onDestroy() {
    	Log.i(TAG, "destroyed the login activity");
    	super.onDestroy();
    }
    
    
    
    
    // for action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
                
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
