package edu.utexas.pickup;

import java.util.ArrayList;

import edu.utexas.pickup.GameAdapter;
import edu.utexas.pickup.Games;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class GameLobbyActivity extends Activity {
	
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
	
	private int startCreateGameActivity()
	{
		Intent intent = new Intent(this, CreateGameActivity.class);
		startActivity(intent);
		return 9;
	}
	
	private int startGameInfoActivity()
	{
		Intent intent = new Intent(this, GameInfoActivity.class);
		startActivity(intent);
		return 9;
	}
	
	//for list view
	private void populateGameList(){
		ArrayList<Games> arrayOfGames = Games.getGames();
		GameAdapter adapter = new GameAdapter(this, arrayOfGames);
		ListView listview = (ListView) findViewById(R.id.games_list);
		listview.setAdapter(adapter);
	} // need to add list to xml
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_lobby);
		
		populateGameList();
		
		// to click list item
		ListView gamelistview = (ListView) findViewById(R.id.games_list);
		gamelistview.setOnItemClickListener(new OnItemClickListener(){
		      @Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id){
		        startGameInfoActivity();
		      }
		     });
		
		
        final Button create_button = (Button) findViewById(R.id.create_a_game_button);
        View.OnClickListener create = new View.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		startCreateGameActivity();        		
        	}
        };
        create_button.setOnClickListener(create); 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_lobby, menu);
		
        ActionBar bar = getActionBar();
        // blue
        //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2694ed")));
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
