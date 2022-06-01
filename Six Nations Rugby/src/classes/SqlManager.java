package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlManager {
	
	static private Connection con;
	
	public static void sqlConnection() {
		
		try {
			
            String basedatos = "rugby";
            String host = "localhost";
            String port = "3306";
            String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
            String user = "root";
            String pwd = "";

            //Class.forName("com.mysql.jdbc.Driver");    // No necesario desde SE 6.0
            //Class.forName("com.mysql.cj.jdbc.Driver"); // para MySQL 8.0, no necesario
            con = DriverManager.getConnection(urlConnection, user, pwd);

            // Hace commit automaticamente
            con.setAutoCommit(true);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public static boolean checkDatabase() {
		try {			
			String query = "SELECT * from player;";
			Statement stmt = con.createStatement();
			
			ResultSet st = stmt.executeQuery(query);
			
			if(st.next())
				return true;
				
		} catch (SQLException ex ) {
			System.out.println(ex.getMessage());
		}
		
		return false;
	}
	
	public static Torneo fetchData(ArrayList<Jugador> players, ArrayList<Entrenador> trainers, ArrayList<Arbitro> referees, ArrayList<Equipo> teams, ArrayList<Stadium> stadiums, Torneo tournament) {
		try {			
			String query = "SELECT * from team;";
			Statement stmt = con.createStatement();
			
			ResultSet st = stmt.executeQuery(query);
			
			while(st.next()) {
				Equipo team = new Equipo(st.getString("nation"));
				team.setId(st.getInt("id"));
				team.setPlayed(st.getInt("played"));
				team.setLosses(st.getInt("losses"));
				team.setVictories(st.getInt("victories"));
				team.setDraws(st.getInt("draws"));
				team.setPoints(st.getInt("points"));
				
				teams.add(team);
			}
			st.close();
			stmt.close();
			
			query = "SELECT * from player;";
			stmt = con.createStatement();
			st = stmt.executeQuery(query);
			
			while(st.next()) {
				Jugador player = new Jugador(st.getString("fullname"), st.getFloat("weight"), st.getInt("speed"), st.getInt("strength"), st.getInt("resistence"));
				player.setId(st.getInt("id"));
				
				for (Equipo team : teams) {
					if(team.getId() == st.getInt("id_team")) {
						team.addPlayer(player);
						break;
					}
				}
				players.add(player);
			}
			
			st.close();
			stmt.close();
			
			query = "SELECT * from stadium;";
			stmt = con.createStatement();
			st = stmt.executeQuery(query);
			
			while(st.next()) {
				Stadium stadium = new Stadium(st.getString("country"), st.getInt("capacity"));
				stadium.setId(st.getInt("id"));
				stadiums.add(stadium);
			}
			
			st.close();
			stmt.close();
			
			query = "SELECT * from coach;";
			stmt = con.createStatement();
			st = stmt.executeQuery(query);
			
			while(st.next()) {
				Entrenador coach = new Entrenador(st.getString("fullname"), st.getFloat("weight"), st.getInt("experience"));
				coach.setId(st.getInt("id"));
				
				for (Equipo team : teams) {
					if(team.getId() == st.getInt("id_team")) {
						team.addEntrenador(coach);
						break;
					}
				}
				trainers.add(coach);
			}
			
			st.close();
			stmt.close();
			
			query = "SELECT * from referee;";
			stmt = con.createStatement();
			st = stmt.executeQuery(query);
			
			while(st.next()) {
				Arbitro referee = new Arbitro(st.getString("fullname"), st.getFloat("weight"), st.getInt("accuracy"));
				referee.setId(st.getInt("id"));
				referees.add(referee);
			}
			
			st.close();
			stmt.close();
			
			tournament = new Torneo(teams, referees, stadiums);
			
			query = "SELECT * from game;";
			stmt = con.createStatement();
			st = stmt.executeQuery(query);
			int gameCounter = 0;
			
			while(st.next()) {
				int id = st.getInt("id");
				int idStadium = st.getInt("id_stadium");
				int hostScore = st.getInt("host_score");
				int visitorScore = st.getInt("visitor_score");
				Equipo host = new Equipo();
				Equipo visitor = new Equipo();
				Stadium stadium = new Stadium();
				ArrayList<Arbitro> gameRefs = new ArrayList<>();
				
				if(hostScore > 0 && visitorScore > 0)
				gameCounter++;
				
				String query2 = "SELECT * from participate;";
				Statement stmt2 = con.createStatement();
				ResultSet st2 = stmt2.executeQuery(query2);
				
				while(st2.next()) {
					if(st2.getInt("id_game")==id) {						
						for (Equipo team : teams) {
							if(st2.getInt("id_team") == team.getId()) {
								if(st2.getInt("is_host") == 1) {
									host = team;
								} else {
									visitor = team;
								}
							}
						}
					}
				}
				st2.close();
				stmt2.close();
				
				query2 = "SELECT * FROM enforce";
				stmt2 = con.createStatement();
				st2 = stmt2.executeQuery(query2);
				
				while(st2.next()) {
					if(st2.getInt("id_game")==id) {
						for (Arbitro referee : referees) {
							if(st2.getInt("id_referee") == referee.getId()) {
								gameRefs.add(referee);
							}
						}
					}
				}
				st2.close();
				stmt2.close();
				
				for (Stadium std : stadiums) {
					if(std.getId()==idStadium) {
						stadium = std;
						break;
					}
				}
				
				Game fetchedGame = new Game(host, visitor, gameRefs, stadium);
				
				fetchedGame.setHostScore(hostScore);
				fetchedGame.setVisitorScore(visitorScore);				
				
				tournament.getGames().add(fetchedGame);
			}
			
			st.close();
			stmt.close();
			
			tournament.setPlayed(gameCounter);
			tournament.setDays((int) gameCounter /3);
				
		} catch (SQLException ex ) {
			System.out.println(ex.getMessage());
		}
		
		return tournament;
	};
	
	public static void truncateTables() {
		try {
			
			String query = "DELETE FROM coach;";
			
			Statement statement = con.createStatement();
			statement.executeUpdate(query); 
			statement.close();
			
			query = "DELETE FROM player;";
			
			statement = con.createStatement();
			statement.executeUpdate(query); 
			statement.close();
			
			query = "DELETE FROM participate;";
			
			statement = con.createStatement();
			statement.executeUpdate(query); 
			statement.close();
			
			query = "DELETE FROM team;";
			
			statement = con.createStatement();
			
			statement.executeUpdate(query);
			statement.close();
			
			query = "DELETE FROM enforce;";
			
			statement = con.createStatement();
			statement.executeUpdate(query); 
			statement.close();
			
			query = "DELETE FROM referee;";
			
			statement = con.createStatement();
			statement.executeUpdate(query); 
			statement.close();
			
			query = "DELETE FROM game;";
			
			statement = con.createStatement();
			statement.executeUpdate(query); 
			statement.close();
			
			query = "DELETE FROM stadium;";
			
			statement = con.createStatement();
			statement.executeUpdate(query); 
			statement.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static int insertTeam(String nation) {
		int id = 0;
		try {
			
			String query = "INSERT INTO team VALUES(NULL,?,0,0,0,0,0);";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setString(1 ,nation);
		
			statement.executeUpdate();
			statement.close();
			
			query = "SELECT last_insert_id() as last_id";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			id = rs.getInt("last_id");
			rs.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return id;
	}
	
	public static int insertPlayer(int teamId,String name, float weight, int strength, int speed, int resistence) {
		int id=0;
		
		try {	
			
			String query = "INSERT INTO player VALUES(NULL,?,?,?,?,?,?);";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1, teamId);
			statement.setString(2, name);
			statement.setFloat(3, weight);
			statement.setInt(4, strength);
			statement.setInt(5, speed);
			statement.setInt(6, resistence);
			
			statement.executeUpdate();
			statement.close();
			
			query = "SELECT last_insert_id() as last_id";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			id = rs.getInt("last_id");
			rs.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return id;
	}
	
	public static int insertStadium(String nation ,int capacity) {
		int id = 0;
		
		try {
			
			String query = "INSERT INTO stadium VALUES(NULL,?,?);";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			
			statement.setString(1 ,nation);
			statement.setInt(2, capacity);
		
			statement.executeUpdate();
			statement.close();
			
			query = "SELECT last_insert_id() as last_id";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			id = rs.getInt("last_id");
			rs.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return id;
	}
	
	public static int insertReferee(String name, float weight,int precision) {
		int id = 0;
		
		try {
			String query = "INSERT INTO referee VALUES(NULL,?,?,?);";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setString(1, name);
			statement.setFloat(2 , weight);
			statement.setInt(3 , precision);
		
			statement.executeUpdate();
			statement.close();
			
			query = "SELECT last_insert_id() as last_id";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			id = rs.getInt("last_id");
			rs.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return id;
	}
	
	public static int insertTrainer(int teamId,String name, float weight,int experience) {
		int id = 0;
		
		try {
			String query = "INSERT INTO coach VALUES(NULL,?,?,?,?);";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1 , teamId);
			statement.setString(2, name);
			statement.setFloat(3 , weight);
			statement.setInt(4 , experience);
		
			statement.executeUpdate();
			statement.close();
			
			query = "SELECT last_insert_id() as last_id";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			id = rs.getInt("last_id");
			rs.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return id;
	}
	
	public static int insertGame(int hostId, int visitorId, int stadiumId, ArrayList<Arbitro> referees) {
		int id = 0;
		
		try {
			String query = "INSERT INTO game VALUES(NULL,?,0,0);";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1 , stadiumId);
		
			statement.executeUpdate();
			statement.close();
			
			query = "SELECT last_insert_id() as last_id";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			id = rs.getInt("last_id");
			rs.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
	insertParticipants(hostId, visitorId, id);
	insertEnforcers(id, referees);
		
		return id;
	}

	private static void insertParticipants(int hostId, int visitorId, int gameId) {
		
		try {
			String query = "INSERT INTO participate VALUES(?,?,1);";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1 , gameId);
			statement.setInt(2 , hostId);
		
			statement.executeUpdate();
			statement.close();
			
			query = "INSERT INTO participate VALUES(?,?,0);";
			
			statement = con.prepareStatement(query);
			
			statement.setInt(1 , gameId);
			statement.setInt(2 , visitorId);
		
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private static void insertEnforcers(int gameId, ArrayList<Arbitro> referees) {
		try {
			
			for (int i = 0; i < referees.size(); i++) {				
				String query = "INSERT INTO enforce VALUES(?,?,NULL);";
				
				PreparedStatement statement = con.prepareStatement(query);
				
				statement.setInt(1 , gameId);
				statement.setInt(2 , referees.get(i).getId());
				
				statement.executeUpdate();
				statement.close();
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void insertScores(int hostScore, int visitorScore, int gameId) {
		try {
			String query = "UPDATE game SET host_score=? , visitor_score=? WHERE id=?";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1 , hostScore);
			statement.setInt(2 , visitorScore);
			statement.setInt(3 , gameId);
		
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void updateTeamStats(int teamId, int played, int victories, int losses, int draws, int points) {
		try {
			String query = "UPDATE team SET played=? , victories=? , losses=?, draws=?, points=? WHERE id=?";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1 , played);
			statement.setInt(2 , victories);
			statement.setInt(3 , losses);
			statement.setInt(4 , draws);
			statement.setInt(5 , points);			
			statement.setInt(6 , teamId);
		
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void insertActa(int gameId, int refereeId, String acta) {
		try {
			String query = "UPDATE enforce SET acta=? WHERE id_game=? AND id_referee=?";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setString(1 , acta);
			statement.setInt(2 , gameId);
			statement.setInt(3 , refereeId);
		
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage()+ "egreg");
		}
	}

	public static void closeConnection() {
		try {			
			con.close();
		} catch (SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
	
}
