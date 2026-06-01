package classiModello;

import java.util.List;

import interfacce.Identificabile;

public class Sala implements Identificabile{
	
	private int identificativoNumericoUnivoco; 
	private String nomeDellaSala; 
	private int numeroMassimoDiPosti; 
	private boolean has3D;
	private List<CaratteristicheSala> caratteristiche;
	
	public Sala(int identificativoNumericoUnivoco, String nomeDellaSala, int numeroMassimoDiPosti, boolean has3d,
			List<CaratteristicheSala> caratteristiche) {
		super();
		this.identificativoNumericoUnivoco = identificativoNumericoUnivoco;
		this.nomeDellaSala = nomeDellaSala;
		this.numeroMassimoDiPosti = numeroMassimoDiPosti;
		has3D = has3d;
		this.caratteristiche = caratteristiche;
	}

	public int getIdentificativoNumericoUnivoco() {
		return identificativoNumericoUnivoco;
	}

	public void setIdentificativoNumericoUnivoco(int identificativoNumericoUnivoco) {
		this.identificativoNumericoUnivoco = identificativoNumericoUnivoco;
	}

	public String getNomeDellaSala() {
		return nomeDellaSala;
	}

	public void setNomeDellaSala(String nomeDellaSala) {
		this.nomeDellaSala = nomeDellaSala;
	}

	public int getNumeroMassimoDiPosti() {
		return numeroMassimoDiPosti;
	}

	public void setNumeroMassimoDiPosti(int numeroMassimoDiPosti) {
		this.numeroMassimoDiPosti = numeroMassimoDiPosti;
	}

	public boolean isHas3D() {
		return has3D;
	}

	public void setHas3D(boolean has3d) {
		has3D = has3d;
	}

	public List<CaratteristicheSala> getCaratteristiche() {
		return caratteristiche;
	}

	public void setCaratteristiche(List<CaratteristicheSala> caratteristiche) {
		this.caratteristiche = caratteristiche;
	}

	@Override
	public String toString() {
		return "Sala [identificativoNumericoUnivoco=" + identificativoNumericoUnivoco + ", nomeDellaSala="
				+ nomeDellaSala + ", numeroMassimoDiPosti=" + numeroMassimoDiPosti + ", has3D=" + has3D
				+ ", caratteristiche=" + caratteristiche + "]";
	}

	@Override
	public int getID() {
		return this.identificativoNumericoUnivoco;
	}
	
	
	
	
	
	
	
}
