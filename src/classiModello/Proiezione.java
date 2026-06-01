package classiModello;

import java.time.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import interfacce.Filtro;
import interfacce.Identificabile;
import interfacce.Prezzabile;
import interfacce.Programmabile;

public abstract class Proiezione implements Identificabile, Programmabile, Prezzabile, Filtro<String> {

	private static final String[] TAG_CONSENTITI= {"SERALE", "WEEKEND", "FAMIGLIE", "ANTEPRIMA", "LINGUA_ORIGINALE", "EVENTO", "TRE_D"};
	
	private int id;
	private Film film;
	private Sala sala;
	private LocalDate data;
	private LocalTime oraInizio;
	private double prezzoBase;
	private Set<String> tags = new HashSet<>();
	
	public Proiezione(int id, Film film, Sala sala, LocalDate data, LocalTime oraProiezione, double prezzoBase, String[] tags) {
		setId(id);
		setFilm(film);
		setSala(sala);
		this.data = data;
		this.oraInizio = oraProiezione;
		this.prezzoBase = prezzoBase;
		setTags(tags);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		if (film != null) {
			this.film = film;
		}
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		if (sala != null) {
			this.sala = sala;
		}
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

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		for (int i = 0; i < tags.length; i++) {
			for (String consentiti : TAG_CONSENTITI) {
				if (tags[i] == consentiti) {
					this.tags.add(tags[i]);
				} else {
					System.out.println(tags[i]+" tag non consentito");
				}
			}
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
				+ ", tags=" + tags + "]";
	}
	
	@Override
	public boolean accetta(String ricercaTag) {
		if (this.tags == null || ricercaTag == null) {
			return false;
		}
		for (String tag : this.tags) {
			if (tag.equalsIgnoreCase(ricercaTag)) {
				return true;
			}
		}
		return false;
	} 
}
