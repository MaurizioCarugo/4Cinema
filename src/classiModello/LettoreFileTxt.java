package classiModello;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public class LettoreFileTxt {
	
	
	public void caricaFilm(String percorso, GestoreCinema gestore) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(percorso));
		
		String riga;
		String[] film;
		List<String[]> listaFilm = new ArrayList<>();
		while (scanner.hasNextLine()) {
			riga = scanner.nextLine();
			film = riga.split(";");
			listaFilm.add(film);
		}
		
		for (String[] filmSingolo : listaFilm) {
			List<GenereFilm> generi = new ArrayList<>();
			String[] generiStringa = filmSingolo[5].split(",");
			String errore = "";
			for (int i = 0; i < generiStringa.length; i++) {
				switch (generiStringa[i]) {
				
				case "AZIONE":
					generi.add(GenereFilm.AZIONE);
					break;
				case "FANTASCIENZA":
					generi.add(GenereFilm.FANTASCIENZA);
					break;
				case "AVVENTURA":
					generi.add(GenereFilm.AVVENTURA);
					break;
				case "THRILLER":
					generi.add(GenereFilm.THRILLER);
					break;
				case "GIALLO":
					generi.add(GenereFilm.GIALLO);
					break;
				case "DRAMMATICO":
					generi.add(GenereFilm.DRAMMATICO);
					break;
				case "ANIMAZIONE":
					generi.add(GenereFilm.ANIMAZIONE);
					break;
				case "COMMEDIA":
					generi.add(GenereFilm.COMMEDIA);
					break;
				case "FANTASIA":
					generi.add(GenereFilm.FANTASIA);
					break;
				case "MUSICALE":
					generi.add(GenereFilm.MUSICALE);
					break;
				case "ORRORE":
					generi.add(GenereFilm.ORRORE);
					break;
				case "STORICO":
					generi.add(GenereFilm.STORICO);
					break;
				default:
					errore = null;
					break;
				}
			}
			if (errore != null) {
				Film films = new Film(Integer.parseInt(filmSingolo[0]),
									filmSingolo[1],
									filmSingolo[2],
									Integer.parseInt(filmSingolo[3]),
									LocalDate.parse(filmSingolo[4]),
									generi);
				gestore.aggiungiFilm(films);
			} else {
				continue;
			}
		}
		// qui si aggiungerà la lista all'aggregatore in qualche modo
	}
}
