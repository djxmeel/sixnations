package classes;

import java.util.ArrayList;

public class Entrenador extends Persona {
	
	private int experience;
	private Equipo equipo;

	public Entrenador(String fullname, float peso, int experience, Equipo equipo) {
		super(fullname, peso);
		this.experience = experience;
		this.equipo = equipo;		
	}

	public Entrenador(String fullname, float peso, int experience) {
		super(fullname, peso);
		this.experience = experience;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public void unsetEquipo() {
		this.equipo = null;
	}
	
	public void createRoster() {
		equipo.sortEquipo();
		ArrayList<Jugador> alineacion = new ArrayList<>();
		
		for (int i = 0; i < 15; i++) {
			alineacion.add(equipo.getPlayers().get(i));
		}
		
		this.equipo.setAlineacion(alineacion);
	}
}
