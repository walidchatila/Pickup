-- schema for PickUP


CREATE EXTENSION cube;
CREATE EXTENSION earthdistance;

DROP TABLE IF EXISTS appuser cascade;
CREATE TABLE appuser (
	user_id 	serial primary key,
	firstname	varchar(80) NOT NULL,
	lastname	varchar(80) NOT NULL,
	username	varchar(80) UNIQUE NOT NULL,
	password	varchar(128) NOT NULL,
	location 	varchar(128),
	lat		FLOAT,
    	lng		FLOAT,
    	current_ranking integer,
	current_player  integer
);

DROP TABLE IF EXISTS game cascade;
CREATE TABLE game (
	game_id 	serial primary key,
	admin_id 	int NOT NULL REFERENCES appuser,
	name		varchar(80) NOT NULL,
	sport_type 	varchar(80) NOT NULL,
	max_players	int NOT NULl,
	accessibility int DEFAULT 0, 
	rating 	int DEFAULT NULL,
	lat 	FLOAT NOT NULL,
	lng 	FLOAT NOT NULL,
	location varchar(128) NOT NULL, 
	loacation_specifics varchar(128),
	gender varchar(80), 
	age 	int DEFAULT 0, 
	date	int, 
	time	int
);

DROP TABLE IF EXISTS player cascade;
CREATE TABLE player (
   	player_id	serial primary key,
	game_id		INTEGER REFERENCES game
); 


DROP TABLE IF EXISTS rank cascade; 
CREATE TABLE rank( 
	rank_id 	serial primary key,
	user_id 	int NOT NULL REFERENCES appuser,
	skill 		int DEFAULT NULL, 
	sportsmanship int DEFAULT NULL
);