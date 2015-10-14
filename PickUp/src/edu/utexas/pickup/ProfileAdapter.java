package edu.utexas.pickup;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ProfileAdapter extends ArrayAdapter<Profile>{


	public ProfileAdapter(Context context, ArrayList<Profile> profile) {
		super(context, 0, profile);
	}
		 
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Profile profile = getItem(position);
		if (convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_profile, parent, false);
		}
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView info = (TextView) convertView.findViewById(R.id.info);

			 
		title.setText(profile.getTitle());
		info.setText(profile.getInfo());
 
		return convertView;
	}

}
