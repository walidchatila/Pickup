package edu.utexas.pickup;

import java.util.ArrayList;

public class Games {
	
	private String gameID;
	private String name;
	// added
	private String gameType;
	private String time;
	private String date;
	private String spotsAvailable;
	
	
	public Games(String gameID, String name, String gameType, String time, String date, String spotsAvailable){
		this.gameID = gameID;
		this.name = name;
		this.gameType = gameType;
		this.time = time;
		this.date = date;
		this.spotsAvailable = spotsAvailable;
	}
	public String getGameID() {
		return gameID;
	}
	/*public void setGameID(String gameID) {
		this.gameID = gameID;
	}*/
	public String getName() {
		return name;
	}
	/*public void setName(String name) {
		this.name = name;
	}*/
	public String getGameType() {
		return gameType;
	}
	/*public void setGameType(String gameType) {
		this.gameType = gameType;
	}*/
	public String getTime() {
		return time;
	}
	/*public void setTime(String time) {
		this.time = time;
	}*/
	public String getDate() {
		return date;
	}
	/*public void setDate(String date) {
		this.date = date;
	}*/
	public String getSpotsAvailable() {
		return spotsAvailable;
	}
	
	public static ArrayList<Games> getGames(){
		ArrayList<Games> games = new ArrayList<Games>();
		games.add(new Games("1", "Touchdown", "Football", "6:00pm", "Nov 19", "Available Spots: 5"));
		games.add(new Games("1", "Play Ball", "Football", "6:00pm", "Nov 19", "Available Spots: 3"));
		games.add(new Games("1", "Pig Skin", "Football", "6:00pm", "Nov 19", "Available Spots: 6"));
		return games;
		}
}