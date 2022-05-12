package classes;

import java.util.ArrayList;
import java.util.Collections;

public class Torneo {
	private ArrayList<Game> games;
	private ArrayList<Equipo> teams;
	private ArrayList<Arbitro> referees;
	private ArrayList<Stadium> stadiums;
	private int days;
	private int gamesPlayed;
	
	public Torneo(ArrayList<Equipo> teams, ArrayList<Arbitro> referees, ArrayList<Stadium> stadiums) {
		games = new ArrayList<>();
		this.teams = teams;
		this.referees = referees;
		this.stadiums = stadiums;
		this.days = 0;
		this.gamesPlayed = 0;
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
		
		Collections.shuffle(games);
	}
	
	public void playTournament() {
		if(games.size() == 15) {
			if(days == 5 && gamesPlayed == 15) {
				System.out.println("Tournament is done.");
			} else {				
				for (Game game : games) {
					game.play();
					gamesPlayed++;
				}
				days=5;
			}
		}
	}
	
	public void playDay() {
		if(games.size() == 15) {
		
			if(days == 5 && gamesPlayed==15) {
				System.out.println("Tournament is done.");
			} else {
				System.out.println("\n* DAY "+(days+1)+" *\n");
				
				for (int i = 0; i < 3; i++) {
					games.get(gamesPlayed).play();
					System.out.println("Game " + (gamesPlayed+1) + ": " + games.get(gamesPlayed).getHost().getCountry() + " " + games.get(gamesPlayed).getHostScore() + " - " + games.get(gamesPlayed).getVisitorScore() + " " + games.get(gamesPlayed).getVisitor().getCountry());
					
					gamesPlayed++;					
				}
				days++;
			}
		}
	}
	
	public void showResults() {
		System.out.println("* SIX NATIONS TOURNAMENT RESULTS *");
		int n = 0;
		for (int i = 0; i < 5; i++) {
			System.out.println("\nDAY " + (i+1) + "\n");
			for (int j = 0; j < 3; j++) {
				System.out.println("Game " + (n+1) + ": " + games.get(n).getHost().getCountry() + " " + games.get(n).getHostScore() + " - " + games.get(n).getVisitorScore() + " " + games.get(n).getVisitor().getCountry());			
				n++;
			}
		}
		
		Collections.sort(teams);
		System.out.println("\nSCOREBOARD\n");
		System.out.println("COUNTRY | POINTS");
		for (Equipo team : teams) {
			System.out.print(team.getCountry() + " | " + team.getPoints() +"\n");
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

	public int getDays() {
		return days;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}
	
}
