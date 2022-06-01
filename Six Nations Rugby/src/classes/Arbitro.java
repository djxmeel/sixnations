package classes;

import java.util.HashMap;

public class Arbitro extends Persona {
	
	private int id;
	private int precision;
	private HashMap<Game, String> games;

	public Arbitro(String fullname, float peso, int precision) {
		super(fullname, peso);
		this.precision = precision;
		this.games = new HashMap<>();
		this.id = 0;
	}
	
	public void acta(Game game) {
		String acta = "It's a draw!";
		
		SqlManager.insertActa(game.getId(), this.id, acta);
		games.put(game, acta);
		
	}
	
	public void acta(Game game, String winner) {
		String acta = "The winner is "+ winner;
		
		SqlManager.insertActa(game.getId(), this.id, acta);
		games.put(game, acta);
	}
	
	@Override
	public String toString() {
		return this.getFullname();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}
	
	public HashMap<Game, String> getGames() {
		return games;
	}
	
	public void addGame(Game game, String acta) {
		games.put(game, acta);
	}
	
}
