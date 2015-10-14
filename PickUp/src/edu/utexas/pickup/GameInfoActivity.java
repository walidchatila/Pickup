package edu.utexas.pickup;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameInfoActivity extends Activity {
	
	private int startMapViewActivity(){
		Intent intent = new Intent(this, MapViewActivity.class);
		startActivity(intent);
		this.finish();
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
		//Log.i(TAG, "username is " + username);
		editor.clear();
		editor.commit();
		//Log.i(TAG, "username is " + username);
		startLoginActivity();
	}
	
	private int startPlayersList()
	{
		Intent intent = new Intent(this,PlayersList.class);
		startActivity(intent);
		return 9;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_info);
		
		
		
		// toast
        final Button toast = (Button) findViewById(R.id.join_game_button);
        View.OnClickListener viewtoast = new View.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Toast.makeText(getApplicationContext(), "Joined PickUp Game", Toast.LENGTH_LONG).show();        		
        	}
        };
        toast.setOnClickListener(viewtoast);
	
        final Button player_list = (Button) findViewById(R.id.players_list);
        View.OnClickListener players = new View.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		startPlayersList();
        	}
        };
        player_list.setOnClickListener(players);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_info, menu);
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
		if (id == R.id.sign_out){
			signout();
		}
		if (id == R.id.player_profile){
			startPlayerProfileActivity();
		}
		
		if (id == R.id.map_view){
			startMapViewActivity();
		}
		
		return super.onOptionsItemSelected(item);
	}
}