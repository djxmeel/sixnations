package classes;

import java.util.ArrayList;
import java.util.Date;

public class Game {
	
	private Equipo host;
	private Equipo visitor;
	private ArrayList<Arbitro> referees;
	private Stadium stadium;
	private int hostScore;
	private int visitorScore;
	
	public Game(Equipo host, Equipo visitor, ArrayList<Arbitro> referees, Stadium stadium) {
		this.host = host;
		this.visitor = visitor;
		this.referees = referees;
		this.stadium = stadium;
	}
	
	public Game(Equipo host, Equipo visitor, ArrayList<Arbitro> referees) {
		this.host = host;
		this.visitor = visitor;
		this.referees = referees;
	}
	
	public void play() {
		// TODO Play game
		
	}
	
	@Override
	public String toString() {
		return "\nGame [host=" + host.getCountry() + ", visitor=" + visitor.getCountry() + ", referees=" + this.referees + ", stadium=" + stadium.getCountry()
				+ ", hostScore=" + hostScore + ", visitorScore=" + visitorScore + "]";
	}

	public Equipo getHost() {
		return host;
	}

	public void setHost(Equipo host) {
		this.host = host;
	}

	public Equipo getVisitor() {
		return visitor;
	}

	public void setVisitor(Equipo visitor) {
		this.visitor = visitor;
	}

	public ArrayList<Arbitro> getReferees() {
		return referees;
	}

	public void addReferee(Arbitro referee) {
		referees.add(referee);
	}

	public Stadium getStadium() {
		return stadium;
	}

	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

	public int getHostScore() {
		return hostScore;
	}

	public void setHostScore(int hostScore) {
		this.hostScore = hostScore;
	}

	public int getVisitorScore() {
		return visitorScore;
	}

	public void setVisitorScore(int visitorScore) {
		this.visitorScore = visitorScore;
	}
	
	
}
