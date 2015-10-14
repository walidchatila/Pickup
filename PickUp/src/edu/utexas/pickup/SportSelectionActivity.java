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
import android.widget.ImageView;

public class SportSelectionActivity extends Activity {

	private static final String TAG = "SportSelectionActivity";
	
	// used for testing. Will be applied to gamelobby buttons
	private int startGameLobbyActivity()
	{
		Intent intent = new Intent(this, GameLobbyActivity.class);
		startActivity(intent);
		return 9;
	}
	
	// used for testing. Will be applied to mis.lobby buttons
	private int startMiscellaneousLobbyActivity()
	{
		Intent intent = new Intent(this, MiscellaneousLobbyActivity.class);
		startActivity(intent);
		return 9;
	}
	
	// for action bar
	private int startPlayerProfileActivity(){
		Intent intent = new Intent(this, PlayerProfileActivity.class);
		startActivity(intent);
		this.finish();
		return 9;
	}
	private int startLoginActivity(){
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		this.finish();
		return 9;
	}
	public void signout(){
		SharedPreferences sharedPref = getSharedPreferences("file", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		String username = sharedPref.getString("username", "");
		Log.i(TAG, "username is " + username);
		editor.clear();
		editor.commit();
		Log.i(TAG, "username is " + username);
		startLoginActivity();
	}
	
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sport_selection);
		
		// used for testing. Will be applied to gamelobby buttons
        final ImageView football_button = (ImageView) findViewById(R.id.footbal_button);
        View.OnClickListener gamelobby = new View.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		startGameLobbyActivity();        		
        	}
        };
         
        football_button.setOnClickListener(gamelobby);
       
		
		
	}

		
		
		
		
		
		
	// for action bar	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sport_selection, menu);
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
		
		if (id == R.id.player_profile){
			startPlayerProfileActivity();
		}
		if (id == R.id.sign_out){
			signout();
		}
		return super.onOptionsItemSelected(item);
	}
}
