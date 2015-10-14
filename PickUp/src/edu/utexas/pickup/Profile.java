package edu.utexas.pickup;

import java.util.ArrayList;

public class Profile {
		
	private String title;
	private String info;

		
		
		public Profile(String title, String info){
			this.title = title;
			this.info = info;
		}
		
		public String getTitle() {
			return title;
		}

		public String getInfo() {
			return info;
		}




		public static ArrayList<Profile> getProfile(){
			ArrayList<Profile> profile = new ArrayList<Profile>();
			profile.add(new Profile("Player Bio", "Im new to this city and Im " +
					"looking to make new friends through playing sports. I love playing " +
					"basketball and staying active."));
			
			profile.add(new Profile("Joined Games", "\t\t\t\tTouchdown\t\t(Football)\t\tDate: Dec 10th    Time: 6:00pm   " + 
					                              "\n\n\t\t\t\tKick It\t\t\t\t\t(Soccer)\t\t\tDate: Dec 13th    Time: 2:00pm "));
			return profile;
			}
}

