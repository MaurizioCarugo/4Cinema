package classiModello;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestoreCinema {
	private Archivio<Film> archivioFilm = new Archivio<>();
	private Archivio<Sala> archivioSale = new Archivio<>();
	private Archivio<Proiezione> archivioProiezioni = new Archivio<>();
	private List<Proiezione> programmazione = new ArrayList<>();
	private Map<LocalDate, List<Proiezione>> proiezioniPerData = new HashMap<>();
	private Map<Integer, List<Proiezione>> proiezioniPerSala = new HashMap<>();
	private Map<String, List<Film>> filmPerGenere = new HashMap<>();
	
	// liste per genere
	private List<Film> azione = new ArrayList<>();
	private List<Film> fantascienza = new ArrayList<>();
	private List<Film> avventura = new ArrayList<>();
	private List<Film> thriller = new ArrayList<>();
	private List<Film> giallo = new ArrayList<>();
	private List<Film> drammatico = new ArrayList<>();
	private List<Film> animazione = new ArrayList<>();
	private List<Film> commedia = new ArrayList<>();
	private List<Film> fantasia = new ArrayList<>();
	private List<Film> musicale = new ArrayList<>();
	private List<Film> orrore = new ArrayList<>();
	private List<Film> storico = new ArrayList<>();
	
	
	// Singleton (per istanziare un solo cinema)
	private static GestoreCinema istanza;
	private GestoreCinema() {
	}
	public static GestoreCinema getIstanza() {
		if (istanza == null) {
			istanza = new GestoreCinema();
		}
		return istanza;
	}
	
	
	public void aggiungiFilm(Film film) {
		if (film != null) {
			archivioFilm.aggiungi(film);
			for (GenereFilm genere : film.getGeneri()) {
				switch (genere) {
					case AZIONE:
						azione.add(film);
						filmPerGenere.put("AZIONE", azione);
					break;
					case FANTASCIENZA:
						fantascienza.add(film);
						filmPerGenere.put("FANTASCIENZA", fantascienza);
					break;
					case AVVENTURA:
						avventura.add(film);
						filmPerGenere.put("AVVENTURA", avventura);
					break;
					case THRILLER:
						thriller.add(film);
						filmPerGenere.put("THRILLER", thriller);
					break;
					case GIALLO:
						giallo.add(film);
						filmPerGenere.put("GIALLO", giallo);
					break;
					case DRAMMATICO:
						drammatico.add(film);
						filmPerGenere.put("DRAMMATICO", drammatico);
					break;
					case ANIMAZIONE:
						animazione.add(film);
						filmPerGenere.put("ANIMAZIONE", animazione);
					break;
					case COMMEDIA:
						commedia.add(film);
						filmPerGenere.put("COMMEDIA", commedia);
					break;
					case FANTASIA:
						fantasia.add(film);
						filmPerGenere.put("FANTASIA", fantasia);
					break;
					case MUSICALE:
						musicale.add(film);
						filmPerGenere.put("MUSICALE", musicale);
					break;
					case ORRORE:
						orrore.add(film);
						filmPerGenere.put("ORRORE", orrore);
					break;
					case STORICO:
						storico.add(film);
						filmPerGenere.put("STORICO", storico);
					break;
					default:
						System.out.println("Non è stato possibile aggiungere il film: il genere "+genere+" non è valido");
					break;
				}
			}
		} else {
			System.out.println("Non è stato possibile aggiungere il film: film non è valido");
		}
	}
	
	void aggiungiSala(Sala sala) {
		if (sala != null) {
			archivioSale.aggiungi(sala);
		}
	}
	
	void aggiungiProiezione(Proiezione proiezione) {
		if ( proiezione != null) {
			archivioProiezioni.aggiungi(proiezione);
			// va aggiunto nelle due mappe ora
		}
	}
}
