package classes;

import java.util.ArrayList;
import java.util.Collections;

public class Equipo implements Comparable<Equipo>{
	
	private int id;
	private String nation;
	private int played;
	private int victories;
	private int losses;
	private int draws;
	private int points;
	private ArrayList<Jugador> players;
	private ArrayList<Jugador> alineacion;
	private ArrayList<Entrenador> entrenadores;
	
	public Equipo(String nation, ArrayList<Jugador> players, ArrayList<Entrenador> entrenadores) {
		this.nation = nation;
		this.players = players;
		this.entrenadores = entrenadores;
		this.alineacion = new ArrayList<>();
		this.points = 0;
		this.played = 0;
		this.victories = 0;
		this.losses = 0;
		this.draws = 0;
		this.id = 0;
	}
	
	public Equipo() {
		this.nation = "";
		players = new ArrayList<>();
		entrenadores = new ArrayList<>();
		this.alineacion = new ArrayList<>();
		this.points = 0;
		this.played = 0;
		this.victories = 0;
		this.losses = 0;
		this.draws = 0;
		this.id = 0;
	}
	
	public Equipo(String nation) {
		this.nation = nation;
		players = new ArrayList<>();
		entrenadores = new ArrayList<>();
		this.alineacion = new ArrayList<>();
		this.points = 0;
		this.played = 0;
		this.victories = 0;
		this.losses = 0;
		this.draws = 0;
		this.id = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPoints() {
		return points;
	}

	public void won() {
		this.points += 3;
		this.played++;
		this.victories++;
	}
	
	public void draw() {
		this.points += 1;
		this.played++;
		this.draws++;
	}
	
	public void lost() {
		this.played++;
		this.losses++;
	}

	public ArrayList<Jugador> getAlineacion() {
		return alineacion;
	}

	public void setAlineacion(ArrayList<Jugador> alineacion) {
		this.alineacion = alineacion;
	}

	public String getCountry() {
		return nation;
	}

	public void setCountry(String country) {
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
	

	public void setPlayed(int played) {
		this.played = played;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public void setPoints(int points) {
		this.points = points;
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
	
	public int getOverallSkill() {
		int coachesSkill = 0;
		int playersSkill = 0;
		int skill = 0;
		
		for (Jugador jugador : alineacion) {
			playersSkill += jugador.getAverage();
		}
		
		for (Entrenador entrenador : entrenadores) {
			coachesSkill += entrenador.getExperience();
		}
		
		playersSkill /= 15;
		coachesSkill /= 3;
		
		skill = (playersSkill + coachesSkill) / 2;
		
		return skill;
	}

	@Override
	public int compareTo(Equipo o) {
		if(this.points < o.points)
			return 1;
		else if(this.points > o.points)
			return -1;
		return 0;
	}
}