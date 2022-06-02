package main;

import java.util.ArrayList;
import java.util.Scanner;

import classes.SqlManager;
import classes.Arbitro;
import classes.Entrenador;
import classes.Equipo;
import classes.Jugador;
import classes.Naciones;
import classes.Stadium;
import classes.Torneo;
import classes.FileHandler;

public class Main {
	static private Scanner sc = new Scanner(System.in);
	static private int select = 0;
	static private ArrayList<Jugador> players = new ArrayList<>();
	static private ArrayList<Entrenador> trainers = new ArrayList<>();
	static private ArrayList<Arbitro> referees = new ArrayList<>();
	static private ArrayList<Equipo> teams = new ArrayList<>();
	static private ArrayList<Stadium> stadiums = new ArrayList<>();
	static private Torneo tournament;
	static private int teamConfig;
	static private int coachConfig;
	static private int playerConfig;
	static private int rosterConfig; 
	
	public static void main(String[] args) {
		
		System.out.println("* SIX NATIONS RUGBY TOURNAMENT *");
		
		int[] config = new int[4];
		boolean autoClear = FileHandler.readConfig(config);
		
		teamConfig = config[0];
		coachConfig = config[1];
		playerConfig = config[2];
		rosterConfig = config[3];
		
		SqlManager.sqlConnection();
		if(SqlManager.checkDatabase() && !autoClear) {
			System.out.println("Fetching data...");
			tournament = SqlManager.fetchData(players, trainers, referees, teams, stadiums, tournament);
			setRosters();
			
		} else {
			clearData();
			System.out.println("NO DATA");
		}
		SqlManager.closeConnection();
		
		menu();
	}
	
	public static void menu() {
		
		while(select >= 0) {
			System.out.println("\nPlayers ("+players.size()+"/180) Coachs ("+trainers.size()+"/18) Teams ("+teams.size()+"/6) Referees ("+referees.size()+") Stadiums ("+stadiums.size()+")");
			System.out.println("1. Generate data and set rosters");
			System.out.println("2. Show Players");
			System.out.println("3. Show Rosters");
			System.out.println("4. Clear data");
			System.out.println("5. Generate tournament");
			if(tournament != null) {
				System.out.println("6. Play tournament");
				System.out.println("7. Play day");
				if(tournament.getGamesPlayed() == 15) {
					System.out.println("8. Show results");
				}
			}
			
			select = sc.nextInt();
			
			switch (select) {
			case 1:
				System.out.println("Generating data...");
				clearData();
				createPlayers();
				createTrainers();
				createTeams();
				createReferees();
				createStadiums();
				assignTeams();
				setRosters();
				updateDatabase();
				break;
			case 2:
				showPlayers();				
				break;
			case 3:
				showRosters();
				break;
			case 4:
				clearData();
				break;
			case 5:
				System.out.println("Generating Tournament..");
				createTournament();
				break;
			case 6:
				if(tournament.getDays() == 0 || tournament.getDays() == 5)
					tournament.playTournament();
				else System.out.println("Tournament is already started");
				break;
			case 7:
				tournament.playDay();
				break;
			case 8:
				tournament.showResults();
				break;
			default:
				break;
			}
		}
		sc.close();
	}

	private static void updateDatabase() {
		SqlManager.sqlConnection();
		
		for(int i=0; i<teams.size() ;i++) {
			Equipo team = teams.get(i);
			team.setId(SqlManager.insertTeam(team.getCountry().toString()));
		}
		
		int teamAssign = 0;
		
		for(int i=0; i < players.size() ;i++) {
			Jugador player =  players.get(i);
			int teamId = teams.get(teamAssign).getId();
			
			player.setId(SqlManager.insertPlayer(teamId, player.getFullname(), player.getPeso(), player.getStrength(), player.getSpeed(), player.getResistence()));
			
			if((i+1) % 30 == 0) teamAssign++;
		}
		
		for(int i=0; i<stadiums.size() ;i++) {
			Stadium stadium = stadiums.get(i);
			stadium.setId(SqlManager.insertStadium(stadium.getCountry().toString(), stadium.getCapacity()));
		}
		
		for(int i=0; i<referees.size() ;i++) {
			Arbitro referee = referees.get(i);
			referee.setId(SqlManager.insertReferee(referee.getFullname(), referee.getPeso(), referee.getPrecision()));
		}
		
		teamAssign = 0;
		
		for(int i=0; i<trainers.size() ;i++) {
			Entrenador trainer = trainers.get(i);
			int teamId = teams.get(teamAssign).getId();
			trainer.setId(SqlManager.insertTrainer(teamId, trainer.getFullname(), trainer.getPeso(), trainer.getExperience()));
			if((i+1) % 3 == 0) teamAssign++;
		}
		
		SqlManager.closeConnection();
	}
	
	private static void createPlayers() {
		String fullname;
		float peso;
		int speed;
		int strength;
		int resistence;
		
		players.clear();
		
		for (int i = 0; i < 180; i++) {
			fullname = "Player " + (i+1);
			peso = (float)(60 + Math.random() * 60);
			peso = (float) Math.round(peso * 100) / 100; // round to 2 decimal
			speed = (int) (Math.random() * 100);
			strength = (int) (Math.random() *100);
			resistence = (int) (Math.random() *100);
			
			players.add(new Jugador(fullname, peso, speed, strength, resistence));
		}
		
	}
	
	private static void clearData() {
		SqlManager.sqlConnection();
		SqlManager.truncateTables();
		SqlManager.closeConnection();
		teams.clear();
		players.clear();
		stadiums.clear();
		referees.clear();
		trainers.clear();
		tournament = null;
		FileHandler.resetFile();
	}
	
	private static void showPlayers() {
		for (Jugador jugador : players) {
			String team;
			team =  jugador.getEquipo().getCountry();
			
			System.out.println(jugador.getFullname() + " Team: "+ team + " Weight: " + jugador.getPeso() + " Avg: "+ jugador.getAverage() +" Str: "+ jugador.getStrength() +" Spd: "+ jugador.getSpeed()+ " Res: "+ jugador.getResistence());
		}
	}
	
	private static void showRosters() {
		for (Equipo equipo : teams) {
			String team;
			team =  equipo.getCountry();
			int c = 1;
			for(Jugador jugador : equipo.getAlineacion()) {
				System.out.println(c++ + "  " +jugador.getFullname() + " Team: "+ team + " Weight: " + jugador.getPeso() + " Avg: "+ jugador.getAverage() +" Str: "+ jugador.getStrength() +" Spd: "+ jugador.getSpeed()+ " Res: "+ jugador.getResistence());
			}
		}
	}
	
	private static void createTrainers() {
		String fullname;
		int experience;
		float peso;
		
		trainers.clear();
		
		for (int i = 0; i < 18; i++) {
			fullname = "Trainer " + (i+1);
			experience = (int) (Math.random() *100);
			peso = (float)(60 + Math.random() * 60);
			peso = (float) Math.round(peso * 100) / 100; // round to 2 decimal
			
			trainers.add(new Entrenador(fullname, peso, experience));
		}
	}
	
	private static void createTeams() {		
		teams.clear();
		Naciones nations[] = Naciones.values();
			
		for (int i = 0; i < teamConfig; i++) {
			teams.add(new Equipo(nations[i].toString()));
			
		}
	}
	
	private static void assignTeams() {
		if(players.size() > 0 && trainers.size() > 0) {			
			int playerCounter = 0;
			int trainerCounter = 0;
			
			for (int i = 0; i < 6; i++) {
				
				for (int j = 0; j < playerConfig; j++) {
					teams.get(i).addPlayer(players.get(playerCounter));
					playerCounter++;
				}
				
				for (int j = 0; j < coachConfig; j++) {
					teams.get(i).addEntrenador(trainers.get(trainerCounter));
					trainerCounter++;
				}
			}
		}
	}

	private static void createReferees() {
		String fullname;
		int precision;
		float peso;
		
		referees.clear();
		
		for (int i = 0; i < 6; i++) {
			fullname = "Referee " + (i+1);
			precision = (int) (Math.random() *100);
			peso = (float)(60 + Math.random() * 60);
			peso = (float) Math.round(peso * 100) / 100; // round to 2 decimal
			
			referees.add(new Arbitro(fullname, peso, precision));
		}
		
	}
	
	private static void createStadiums() {		
		stadiums.clear();
		Naciones nations[] = Naciones.values();
		
		for (int i = 0; i < 6; i++) {
			stadiums.add(new Stadium(nations[i].toString()));
		}
	}
	
	private static void setRosters() {
		for (int i = 0; i < 6; i++) {
			teams.get(i).getEntrenadores().get(0).createRoster(rosterConfig);
		}
	}
	
	private static void createTournament() {
		if(teams.size() > 0 && referees.size() > 0 && stadiums.size() > 0) {
			tournament = new Torneo(teams, referees, stadiums);
			tournament.generateGames();			
		} else 
			System.out.println("No data found!");
	}
}
