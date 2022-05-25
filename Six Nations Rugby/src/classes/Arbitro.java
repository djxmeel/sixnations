package classes;

import java.util.HashSet;

public class Arbitro extends Persona {

	private int precision;
	private HashSet<Game> games;

	public Arbitro(String fullname, float peso, int precision) {
		super(fullname, peso);
		this.precision = precision;
		this.games = new HashSet<>();
	}
	
	@Override
	public String toString() {
		return this.getFullname();
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}
}
