package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            System.out.println(ex.getMessage() + "vvvvv");
        }
	}
	
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
			
			query = "DELETE FROM team;";
			
			statement = con.createStatement();
			
			statement.executeUpdate(query);
			statement.close();
			
			query = "DELETE FROM referee;";
			
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
	
	public static void insertTeam(int id, String nation) {
		try {
			
			String query = "INSERT INTO team VALUES(?,?,0,0,0,0);";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1, id);
			statement.setString(2 ,nation);
		
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void insertPlayer(int teamId,String name, float weight, int strength, int speed, int resistence) {
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
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void insertStadium(int id, String nation ,int capacity) {
		try {
			
			String query = "INSERT INTO stadium VALUES(?,?,?);";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			
			statement.setInt(1, id);
			statement.setString(2 ,nation);
			statement.setInt(3, capacity);
		
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void insertReferee(int id, String name, float weight,int precision) {
		try {
			String query = "INSERT INTO referee VALUES(?,?,?,?);";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1 ,id);
			statement.setString(2, name);
			statement.setFloat(3 , weight);
			statement.setInt(4 , precision);
		
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void insertTrainer(String name, float weight,int experience) {
		try {
			String query = "INSERT INTO coach VALUES(NULL, NULL,?,?,?);";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setString(1, name);
			statement.setFloat(2 , weight);
			statement.setInt(3 , experience);
		
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
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
