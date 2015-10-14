package edu.utexas.pickup;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListView;

public class PlayersList extends Activity {
	
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_players_list);
		
		
		ArrayList<Players> arrayOfPlayers = new ArrayList<Players>();
		PlayersListAdapter adapter = new PlayersListAdapter(this, arrayOfPlayers);
		arrayOfPlayers.add(new Players("Walid", "walid"));
		arrayOfPlayers.add(new Players("Alyssa", "alyssa"));
		arrayOfPlayers.add(new Players("Megan", "megan"));
		arrayOfPlayers.add(new Players("Kevin", "kevin"));
		

		

		ListView LobbyListView = (ListView) findViewById(R.id.player_listview);
		LobbyListView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.players_list, menu);
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
		return super.onOptionsItemSelected(item);
	}
}
