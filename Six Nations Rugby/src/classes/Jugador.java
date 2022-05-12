package classes;

import java.util.Date;

public class Jugador extends Persona implements Comparable<Jugador> {
	
	private int speed;
	private int strength;
	private int resistence;
	private int average;
	Equipo equipo;

	public Jugador(String fullname, Date birthdate, float peso, int speed, int strength, int resistence, Equipo equipo) {
		super(fullname, peso);
		this.speed = speed;
		this.strength = strength;
		this.resistence = resistence;
		this.equipo = equipo;
		this.average = (this.speed + this.strength + this.resistence) / 3;
	}
	
	public Jugador(String fullname, float peso, int speed, int strength, int resistence) {
		super(fullname, peso);
		this.speed = speed;
		this.strength = strength;
		this.resistence = resistence;
		this.average = (this.speed + this.strength + this.resistence) / 3;
	}
	
	@Override
	public int compareTo(Jugador o) {
		
		if(average > o.average)
			return -1;
		else if(average < o.average)
			return 1;
		return 0;
	}
	
	@Override
	public String toString() {
		int avg =(this.speed + this.strength + this.resistence) / 3;
		return "Jugador [Avg=" + avg + " speed=" + speed + ", strength=" + strength + ", resistence=" + resistence + ", equipo="
				+ equipo.getCountry() + "]";
	}
	
	public int getAverage() {
		return average;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getResistence() {
		return resistence;
	}

	public void setResistence(int resistence) {
		this.resistence = resistence;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public void unsetEquipo() {
		this.equipo = null;
	}
}
