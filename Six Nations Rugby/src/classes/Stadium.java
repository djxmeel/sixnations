package classes;

import java.util.HashSet;

public class Stadium {
	
	private int id;
	private int capacity;
	private String country;
	HashSet<Game> gamesHosted;
	
	public Stadium() {
		this.capacity = 3000 + (int)(Math.random()*3000);
		this.country = "";
		this.gamesHosted = new HashSet<>();
		this.id = 0;
	}
	
	public Stadium(String country) {
		this.capacity = 3000 + (int)(Math.random()*3000);
		this.country = country;
		this.gamesHosted = new HashSet<>();
		this.id = 0;
	}
	
	public Stadium(String country, int capacity) {
		this.capacity = capacity;
		this.country = country;
		this.gamesHosted = new HashSet<>();
		this.id = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String getCountry() {
		return country;
	}

	public HashSet<Game> getGames() {
		return gamesHosted;
	}

	public boolean addGame(Game game) {
		return this.gamesHosted.add(game);
	}
	
	
}
