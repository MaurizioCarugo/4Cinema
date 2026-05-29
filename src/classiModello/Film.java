package classiModello;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import interfacce.Filtro;
import interfacce.Identificabile;

public class Film implements Identificabile, Filtro<Film>{
	
	private int identificativoNumericoUnivoco; 
	private String titolo; 
	private String regista; 
	private int durataInMinuti; 
	private LocalDate dataDiUscita; 
	private List<GenereFilm> generi = new ArrayList<>();
	
	private static int idIniziale = 1;
	

	public Film(String titolo, String regista, int durataInMinuti, LocalDate dataDiUscita, List<GenereFilm> generi) {
		super();
		setIdentificativoNumericoUnivoco();
		this.titolo = titolo;
		this.regista = regista;
		this.durataInMinuti = durataInMinuti;
		this.dataDiUscita = dataDiUscita;
		this.generi = generi;
	}

	public int getIdentificativoNumericoUnivoco() {
		return identificativoNumericoUnivoco;
	}

	public void setIdentificativoNumericoUnivoco() {
		this.identificativoNumericoUnivoco = idIniziale ++;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getRegista() {
		return regista;
	}

	public void setRegista(String regista) {
		this.regista = regista;
	}

	public int getDurataInMinuti() {
		return durataInMinuti;
	}

	public void setDurataInMinuti(int durataInMinuti) {
		this.durataInMinuti = durataInMinuti;
	}

	public LocalDate getDataDiUscita() {
		return dataDiUscita;
	}

	public void setDataDiUscita(LocalDate dataDiUscita) {
		this.dataDiUscita = dataDiUscita;
	}

	public List<GenereFilm> getGeneri() {
		return generi;
	}

	public void setGeneri(GenereFilm generi) {
		if(this.getGeneri().contains(generi)) {
			System.out.println("Il genere è gia presente");
			return;
		}
		this.generi.add(generi);
		
	}

	
	@Override
	public String toString() {
		return "Titolo: " + titolo + "\nNumero Univoco: " + identificativoNumericoUnivoco + 
				"\nRegista: " + regista + "\nDurata: " + durataInMinuti + 
				" Minuti\nData Di Uscita:" + dataDiUscita + ", generi: " + generi;
	}

	
	public boolean thisFilmIsA (GenereFilm generi) {
		return this.getGeneri().contains(generi);
	}

	public void anniTrascorsiDallUscita() {
		Period periodo = Period.between(dataDiUscita, LocalDate.now());
		if(periodo.isNegative()) {
			System.out.println("Il film non è ancora uscito");
			return;
		}
		int anni = periodo.getYears();
		int mesi = periodo.getMonths();
		int giorni = periodo.getDays();
		System.out.println("Il film è uscito da: " + anni + " anni, " + mesi + " mesi, " + giorni + " giorni.");
		return;
	}
	
	public boolean isRecente() {
		Period periodo = Period.between(this.getDataDiUscita(), LocalDate.now());
	    return !periodo.isNegative() && periodo.getYears() < 2;
	}

	@Override
	public boolean accetta(Film elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
