package classes;

import java.util.ArrayList;

public class Game {
	
	private int id;
	private Equipo host;
	private Equipo visitor;
	private ArrayList<Arbitro> referees;
	private Stadium stadium;
	private int hostScore;
	private int visitorScore;
	
	public Game(int id, Equipo host, Equipo visitor, ArrayList<Arbitro> referees, Stadium stadium) {
		this.host = host;
		this.visitor = visitor;
		this.referees = referees;
		for (Arbitro arbitro : referees) {
			arbitro.addGame(this, null);
		}
		this.stadium = stadium;
		this.id = id;
	}
	
	public Game(int id ,Equipo host, Equipo visitor, ArrayList<Arbitro> referees) {
		this.host = host;
		this.visitor = visitor;
		this.referees = referees;
		for (Arbitro arbitro : referees) {
			arbitro.addGame(this, null);
		}
		this.id = id;
	}
	
	public void play() {
		int refereeSkill = 0;
		int hostSkill = host.getOverallSkill();
		int visitorSkill = visitor.getOverallSkill();
		String acta;
		
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
		
		SqlManager.insertScores(hostScore, visitorScore, this.id);
		
		if(hostScore > visitorScore) {
			host.won();
			visitor.lost();
			referees.get(0).acta(this, host.getCountry().toString());
			referees.get(0).insertActa(this);
		}
		else if(hostScore < visitorScore) {
			visitor.won();
			host.lost();
			referees.get(0).acta(this, visitor.getCountry().toString());	
			referees.get(0).insertActa(this);
		}
		else {
			host.draw();
			visitor.draw();
			referees.get(0).acta(this);
			referees.get(0).insertActa(this);
		}
		
		SqlManager.updateTeamStats(host.getId(), host.getPlayed(), host.getVictory(), host.getLosses(), host.getDraws(), host.getPoints());
		SqlManager.updateTeamStats(visitor.getId(), visitor.getPlayed(), visitor.getVictory(), visitor.getLosses(), visitor.getDraws(), visitor.getPoints());
	}
	
	@Override
	public String toString() {
		return "\nGame [host=" + host.getCountry() + ", visitor=" + visitor.getCountry() + ", referees=" + this.referees + ", stadium=" + stadium.getCountry()
				+ ", hostScore=" + hostScore + ", visitorScore=" + visitorScore + "]";
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
		stadium.addGame(this);
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
