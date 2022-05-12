package classes;

import java.util.ArrayList;// AIDAR

public class Torneo {
	ArrayList<Game> games;
	ArrayList<Equipo> teams;
	ArrayList<Arbitro> referees;
	ArrayList<Stadium> stadiums;
	
	public Torneo(ArrayList<Equipo> teams, ArrayList<Arbitro> referees, ArrayList<Stadium> stadiums) {
		games = new ArrayList<>();
		this.teams = teams;
		this.referees = referees;
		this.stadiums = stadiums;
	}
	
	public void generateGames() {
		int counter=0;
		boolean swap = true;
		ArrayList<Arbitro> gameReferees1 = new ArrayList<>();
		ArrayList<Arbitro> gameReferees2 = new ArrayList<>();
		
		for (int i = 0; i < 3; i++) {
			gameReferees1.add(referees.get(i));
			gameReferees2.add(referees.get(i+3));
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = i+1; j <= 5; j++) {
				if(counter>5) counter=0;
				
				if(swap) {
					games.add(new Game(teams.get(i), teams.get(j), gameReferees1, stadiums.get(counter)));
					swap = false;
				}else {
					games.add(new Game(teams.get(i), teams.get(j), gameReferees2, stadiums.get(counter)));
					swap = true;
				}
				counter++;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Torneo [games=" + games + "]";
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public ArrayList<Equipo> getTeams() {
		return teams;
	}

	public ArrayList<Arbitro> getReferees() {
		return referees;
	}

	public ArrayList<Stadium> getStadiums() {
		return stadiums;
	}
	
	
}
