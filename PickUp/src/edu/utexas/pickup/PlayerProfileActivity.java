package edu.utexas.pickup;

import java.util.ArrayList;

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
import android.widget.ListView;

public class PlayerProfileActivity extends Activity {
	
	// for action bar
	private int startCreteGameActivity(){
		Intent intent = new Intent(this, CreateGameActivity.class);
		startActivity(intent);
		this.finish();
		return 9;
	}
	private int startSportSelectionActivity(){
		Intent intent = new Intent(this, SportSelectionActivity.class);
		startActivity(intent);
		this.finish();
		return 9;
	}
	private int startFriendsListActivity(){
		Intent intent = new Intent(this, FriendsListActivity.class);
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

	//for list view
	private void populateProfileList(){
		ArrayList<Profile> arrayOfProfile = Profile.getProfile();
		ProfileAdapter adapter = new ProfileAdapter(this, arrayOfProfile);
		ListView listview = (ListView) findViewById(R.id.profile_list);
		listview.setAdapter(adapter);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_profile);
		populateProfileList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player_profile, menu);
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
			startFriendsListActivity();
		}
		if (id == R.id.create_game){
			startCreteGameActivity();
		}
		if (id == R.id.join_game){
			startSportSelectionActivity();
		}
		if (id == R.id.friends_list){
			startFriendsListActivity();
		}
		return super.onOptionsItemSelected(item);
	}
}
