package classes;

import java.util.HashSet;

public class Stadium {

	private int capacity;
	private Naciones country;
	HashSet<Game> gamesHosted;
	
	public Stadium(Naciones country) {
		this.capacity = 3000 + (int)(Math.random()*3000);
		this.country = country;
		this.gamesHosted = new HashSet<>();
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

	public HashSet<Game> getGames() {
		return gamesHosted;
	}

	public boolean addGame(Game game) {
			return this.gamesHosted.add(game);
	}
	
	
}
