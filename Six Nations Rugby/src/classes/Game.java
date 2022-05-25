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
		int refereeSkill = 0;
		int hostSkill = host.getOverallSkill();
		int visitorSkill = visitor.getOverallSkill();
		
		if(!this.stadium.addGame(this)) {
			System.out.println("Game already played!");
			return;
		}
			
		for (Arbitro arbitro : referees) {
			refereeSkill += arbitro.getPrecision();
		}
		
		refereeSkill /= 3;
		
		hostScore = (int) (hostSkill * Math.random());
		visitorScore = (int) (visitorSkill * Math.random());
		
		if(hostScore > visitorScore)
			visitorScore += (refereeSkill * 5) / 100;
		else if(hostScore < visitorScore)
			hostScore += (refereeSkill * 5) / 100;
		else {
			visitorScore += (refereeSkill * 5) / 100;
			hostScore += (refereeSkill * 5) / 100;			
		}
		
		if(hostScore > visitorScore)
			host.won();
		else if(hostScore < visitorScore)
			visitor.won();
		else {
			host.draw();
			visitor.draw();
		}
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
