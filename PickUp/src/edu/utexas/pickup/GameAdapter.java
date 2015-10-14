package edu.utexas.pickup;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GameAdapter extends ArrayAdapter<Games>{


	public GameAdapter(Context context, ArrayList<Games> games) {
		super(context, 0, games);
		}
		 
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Games games = getItem(position);
		if (convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_game, parent, false);
		}
		/*TextView game_id = (TextView) convertView.findViewById(R.id.game_id);*/
		TextView game_name = (TextView) convertView.findViewById(R.id.game_name);
		TextView game_type = (TextView) convertView.findViewById(R.id.game_type);
		TextView game_time = (TextView) convertView.findViewById(R.id.game_time);
		TextView game_date = (TextView) convertView.findViewById(R.id.game_date);
		TextView spots_available = (TextView) convertView.findViewById(R.id.spots_available);
			 
		/*game_id.setText(games.getGameID());*/
		game_name.setText(games.getName());
		game_type.setText(games.getGameType());
		game_time.setText(games.getTime());
		game_date.setText(games.getDate());
		spots_available.setText(games.getSpotsAvailable());
			 
		return convertView;
	}
}



