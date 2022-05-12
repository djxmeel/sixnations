package classes;

public abstract class Persona {
	private String fullname;
	private float peso;
	
	Persona(String fullname, float peso) {
		this.fullname = fullname;
		this.peso = peso;
	}

	public String getFullname() {
		return fullname;
	}

	void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public float getPeso() {
		return peso;
	}

	void setPeso(float peso) {
		this.peso = peso;
	}
}
