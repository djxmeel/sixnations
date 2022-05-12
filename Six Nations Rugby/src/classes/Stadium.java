package classes;

import java.util.ArrayList;

public class Stadium {

	private int capacity;
	private Naciones country;
	ArrayList<Game> games;
	
	public Stadium(int capacity, Naciones country) {
		this.capacity = capacity;
		this.country = country;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public Naciones getCountry() {
		return country;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void addGame(Game game) {
		this.games.add(game);
	}
	
	
}
