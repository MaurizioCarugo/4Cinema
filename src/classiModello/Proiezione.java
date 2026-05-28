package classiModello;

import java.time.*;
import java.util.Arrays;

public abstract class Proiezione {

	private static final String[] TAG_CONSENTITI= {"SERALE", "WEEKEND", "FAMIGLIE", "ANTEPRIMA", "LINGUA_ORIGINALE", "EVENTO", "TRE_D"};
	
	private static int identificativo = 0;
	private Film film;
	private Sala sala;
	private LocalDate data;
	private LocalTime oraProiezione;
	private double prezzoBase;
	private String[] tags;
	
	
	public Proiezione(Film film, Sala sala, LocalDate data, LocalTime oraProiezione, double prezzoBase, String[] tags) {
		super();
		this.film = film;
		this.sala = sala;
		this.data = data;
		this.oraProiezione = oraProiezione;
		this.prezzoBase = prezzoBase;
		this.tags = tags;
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

	public LocalTime getOraProiezione() {
		return oraProiezione;
	}

	public void setOraProiezione(LocalTime oraProiezione) {
		this.oraProiezione = oraProiezione;
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

	public abstract double calcoloPrezzoFinale();

	public LocalDateTime getInizioProiezione() {		
		return data.atTime(oraProiezione);
	}
	
	public LocalDateTime getFineProiezione(Duration durata) {
		return data.atTime(oraProiezione.plus(durata));
	}
	
	public boolean IsToday() {
		if (data.isEqual(LocalDate.now()))
			return true;
		else
			return false;
	}
	
	public boolean IsInWeekend() {
		if (data.getDayOfWeek().getValue() == 6 || data.getDayOfWeek().getValue() == 7)
		return true;
			else
		return false;
		}
	
	public boolean IsSerale() {
		if (oraProiezione.isAfter(LocalTime.of(20, 0, 0)))
			return true;
				else
			return false;
	}
	
	public boolean IsTerminata(Duration durata) {
		if (LocalDateTime.now().isAfter(getFineProiezione(durata)))
			return true;
		else
			return false;
	}
	
	public boolean IsFutura() {
		if (LocalDateTime.now().isBefore(getInizioProiezione()))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Proiezione [data=" + data + ", oraProiezione=" + oraProiezione + ", prezzoBase=" + prezzoBase
				+ ", tags=" + Arrays.toString(tags) + "]";
	}
}
