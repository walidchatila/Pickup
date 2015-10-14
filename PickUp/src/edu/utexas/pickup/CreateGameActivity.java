package edu.utexas.pickup;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateGameActivity extends Activity {
	
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
	
	private int startGameInfoActivity()
	{
		Intent intent = new Intent(this, GameInfoActivity.class);
		startActivity(intent);
		return 9;
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_game);
		
		
		Spinner dropdown = (Spinner)findViewById(R.id.spinner);
		String[] items = new String[]{"Bowling", "Football", "Tennis", "Golf", "Hockey", "Baseball", "Soccer", "Volleyball", "Miscellaneous"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
		dropdown.setAdapter(adapter);
	
		
        final Button gi_button = (Button) findViewById(R.id.create_game_button);
        View.OnClickListener gameinfo = new View.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		startGameInfoActivity();        		
        	}
        };
        gi_button.setOnClickListener(gameinfo); 
		
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_game, menu);
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
		return super.onOptionsItemSelected(item);
	}
}
