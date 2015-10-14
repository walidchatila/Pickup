package edu.utexas.pickup;
import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayersListAdapter extends ArrayAdapter<Players>{
	 public PlayersListAdapter(Context context, ArrayList<Players> players) {
	        super(context, 0, players);
	     }
	 
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent){
		 Players players = getItem(position);
		 if (convertView == null){
			 convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_player, parent, false);
		 }
		 ImageView profile_pic = (ImageView) convertView.findViewById(R.id.profilepic);
		 TextView game_name = (TextView) convertView.findViewById(R.id.Name);

		 if (players.getProfilepic().equals("walid")){
				profile_pic.setImageResource(R.drawable.walid_pic);		}
		 
		 if (players.getProfilepic().equals("alyssa")){
		profile_pic.setImageResource(R.drawable.alyssa_pic);
		}
		 
		 if (players.getProfilepic().equals("megan")){
				profile_pic.setImageResource(R.drawable.megan_pic);
				}
		 if (players.getProfilepic().equals("kevin")){
				profile_pic.setImageResource(R.drawable.kevin_pic);
				}
		
		game_name.setText(players.getName());

		 
	    return convertView;
	 }
}