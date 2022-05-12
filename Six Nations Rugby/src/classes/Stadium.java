package classes;

import java.util.ArrayList;

public class Stadium {

	private int capacity;
	private Naciones country;
	ArrayList<Game> games;
	
	public Stadium(Naciones country) {
		this.capacity = 3000 + (int)(Math.random()*3000);
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
