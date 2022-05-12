package classes;

import java.util.ArrayList;
import java.util.Collections;

public class Equipo {
	
	private Naciones nation;
	private int played;
	private int victories;
	private int losses;
	private int draws;
	private ArrayList<Jugador> players;
	private ArrayList<Jugador> alineacion;
	private ArrayList<Entrenador> entrenadores;
	
	public Equipo(Naciones nation, ArrayList<Jugador> players, ArrayList<Entrenador> entrenadores) {
		this.nation = nation;
		this.players = players;
		this.entrenadores = entrenadores;
		this.alineacion = new ArrayList<>();
	}
	
	public Equipo(Naciones nation) {
		this.nation = nation;
		players = new ArrayList<>();
		entrenadores = new ArrayList<>();
		this.alineacion = new ArrayList<>();
	}
	
	
	
	public ArrayList<Jugador> getAlineacion() {
		return alineacion;
	}

	public void setAlineacion(ArrayList<Jugador> alineacion) {
		this.alineacion = alineacion;
	}

	public Naciones getCountry() {
		return nation;
	}

	public void setCountry(Naciones country) {
		this.nation = country;
	}

	public int getPlayed() {
		return played;
	}

	public int getVictory() {
		return victories;
	}
	
	public void addVictory() {
		victories++;
		played++;
	}

	public int getLosses() {
		return losses;
	}
	
	public void addLoss() {
		losses++;
		played++;
	}

	public int getDraws() {
		return draws;
	}
	
	public void addDraw() {
		draws++;
		played++;
	}

	public ArrayList<Jugador> getPlayers() {
		return players;
	}
	
	public void addPlayer(Jugador jugador) {
		players.add(jugador);
		jugador.setEquipo(this);
	}

	public ArrayList<Entrenador> getEntrenadores() {
		return entrenadores;
	}
	
	public void addEntrenador(Entrenador entrenador) {
		entrenadores.add(entrenador);
		entrenador.setEquipo(this);
	}
	
	public void sortEquipo() {
		Collections.sort(players);
	}

	public int calculatePoints() {
		//TODO calculate points
		return 0;
	}
}