package classes;

public class Arbitro extends Persona {

	private int precision;

	public Arbitro(String fullname, float peso, int precision) {
		super(fullname, peso);
		this.precision = precision;
	}
	
	public void startGame() {
		// TODO start game 
	}
	
	public void endGame() {
		// TODO end game
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
