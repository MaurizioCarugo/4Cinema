package classiModello;

import java.time.*;
import java.util.Arrays;

import interfacce.Filtro;
import interfacce.Identificabile;
import interfacce.Prezzabile;
import interfacce.Programmabile;

public abstract class Proiezione implements Identificabile, Programmabile, Prezzabile, Filtro<LocalDate> {

	private static final String[] TAG_CONSENTITI= {"SERALE", "WEEKEND", "FAMIGLIE", "ANTEPRIMA", "LINGUA_ORIGINALE", "EVENTO", "TRE_D"};
	
	private int id;
	private Film film;
	private Sala sala;
	private LocalDate data;
	private LocalTime oraInizio;
	private double prezzoBase;
	private String[] tags;
	
	private static int idIniziale = 1;
	
	public Proiezione(Film film, Sala sala, LocalDate data, LocalTime oraProiezione, double prezzoBase, String[] tags) {
		super();
		setId();
		this.film = film;
		this.sala = sala;
		this.data = data;
		this.oraInizio = oraProiezione;
		this.prezzoBase = prezzoBase;
		this.tags = tags;
	}
	
	public int getId() {
		return id;
	}

	public void setId() {
		this.id = idIniziale ++;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	
	public LocalTime getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(LocalTime oraProiezione) {
		this.oraInizio = oraProiezione;
	}

	public double getPrezzoBase() {
		return prezzoBase;
	}

	public void setPrezzoBase(double prezzoBase) {
		this.prezzoBase = prezzoBase;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		if (tags.equals(TAG_CONSENTITI)) {
			this.tags = tags;
		}
	}

//	public LocalDateTime getInizioProiezione() {		
//		return data.atTime(oraInizio);
//	}
	
	public LocalDateTime getDataOraFine(Duration durata) {
		return data.atTime(oraInizio.plus(durata));
	}
	
	public boolean isToday() {
		if (data.isEqual(LocalDate.now()))
			return true;
		else
			return false;
	}
	
	public boolean isInWeekend() {
		if (data.getDayOfWeek().getValue() == 6 || data.getDayOfWeek().getValue() == 7)
		return true;
			else
		return false;
		}
	
	public boolean isSerale() {
		if (oraInizio.isAfter(LocalTime.of(20, 0, 0)))
			return true;
				else
			return false;
	}
	
	public boolean isTerminata(Duration durata) {
		if (LocalDateTime.now().isAfter(getDataOraFine(durata)))
			return true;
		else
			return false;
	}
	
	public boolean isFutura() {
		if (LocalDateTime.now().isBefore(getDataOraInizio()))
			return true;
		else
			return false;
	}
	
	public String getDettagliBase() {
		return "Proiezione in Sala "+sala.getID()+" per il film "+film.getTitolo()+" alle ore "+getOraInizio()+" del giorno "+getData();
	}
	
	public abstract String getTipoProiezione();

	@Override
	public String toString() {
		return "Proiezione [data=" + data + ", oraProiezione=" + oraInizio + ", prezzoBase=" + prezzoBase
				+ ", tags=" + Arrays.toString(tags) + "]";
	}
}
