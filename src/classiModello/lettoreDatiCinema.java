package classiModello;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import classiModello.Sala;
import classiModello.Film;


public class lettoreDatiCinema {
	
///FILM

	public List<Film> leggiFilm(String percorsoFile,GestoreCinema gestore)throws FileNotFoundException {
		List<Film>listaFilm = new ArrayList<>();
		
		File f = new File(percorsoFile);
		Scanner lettore = new Scanner(f);
		
		while (lettore.hasNextLine()) {
			String riga = lettore.nextLine();
			if(riga.trim().isEmpty()) {
				continue;
			}
			String [] campi = riga.split(";");
			
			if (campi.length!=20) {
				System.out.println("Riga non valida nel file "+ percorsoFile+ ":numero campi errato");
			continue;
			}
			
			int id = Integer.parseInt(campi [0]);
			String titolo = campi [1];
			String registra = campi [2];
			int durataMinuti =Integer.parseInt(campi [3]);
			
			if(durataMinuti <=0) {
				System.out.println("Riga non valida nel file "+percorsoFile+":durata");
				continue;
			}
			
			LocalDate dataUscita = LocalDate.parse(campi [4]);
			
			//gestione dei generi
			String [] generiString = campi[5].split(",");
			
			List<GenereFilm> listaGeneri = new ArrayList<>();
			
			for (String g: generiString) {
				GenereFilm genereEnum = GenereFilm.valueOf(g.trim().toUpperCase());
				listaGeneri.add(genereEnum);
			}
			
			Film film = new Film (id, titolo, registra, durataMinuti, dataUscita, listaGeneri);
			
			gestore.aggiungiFilm(film);
			
			
		}

		lettore.close();
		return listaFilm;
		
	}
		
///SALE

		public void caricaSale(String percorsoFile,GestoreCinema gestore)throws FileNotFoundException {
		File f = new File(percorsoFile);
		Scanner lettore = new Scanner(f);
		
		while (lettore.hasNextLine()) {
			String riga = lettore.nextLine();
			if(riga.trim().isEmpty()) {
				continue;
			}
			
			String [] campi = riga.split(";");
		

			if (campi.length!=12) {
				System.out.println("Riga non valida nel file "+ percorsoFile+ ":numero campi errato");
				continue;
			}
		
			int id = Integer.parseInt(campi [0]);
			String nome = campi [1];
			int numeroPosti =Integer.parseInt(campi [2]);
			boolean supporta3D = Boolean.parseBoolean(campi[3] );
			
			String [] caratteristicheArray = campi[4].split(",");
			List<CaratteristicheSala> listaCaratteristiche = new ArrayList<>();
			
			
			for(String c: caratteristicheArray) {
				CaratteristicheSala caratteristicaEnum = CaratteristicheSala.valueOf(c.trim().toUpperCase());
				listaCaratteristiche.add(caratteristicaEnum);
			}
			
			Sala sala = new Sala(id, nome, numeroPosti, supporta3D, listaCaratteristiche);
			
			gestore.aggiungiSala(sala);
			
		
	}
		
		lettore.close();
	
}
	}
	
	
	   public void caricaProiezioni(String percorsoFile, GestoreCinema gestore) throws FileNotFoundException {
	        File f = new File(percorsoFile);
	        Scanner lettore = new Scanner(f);

	        while (lettore.hasNextLine()) {
	            String riga = lettore.nextLine();

	            if (riga.trim().isEmpty()) {
	                continue;
	            }

	            String[] campi = riga.split(";");

	            // Controllo: la riga abbia il numero corretto di campi (sono 11 in totale)
	            if (campi.length != 11) {
	                System.out.println("Riga non valida nel file " + percorsoFile + ": numero campi errato");
	                continue;
	            }

	            int id = Integer.parseInt(campi[0]);
	            String tipo = campi[1].toUpperCase(); // Prende il tipo (STANDARD, TRE_D, EVENTO)

	            // Controllo: il tipo di proiezione sia valido
	            if (!tipo.equals("STANDARD") && !tipo.equals("TRE_D") && !tipo.equals("EVENTO")) {
	                System.out.println("Riga non valida nel file " + percorsoFile + ": tipo proiezione");
	                continue;
	            }

	            int filmId = Integer.parseInt(campi[2]);
	            int salaId = Integer.parseInt(campi[3]);

	     
	            LocalDate data = LocalDate.parse(campi[4]);
	            LocalTime oraInizio = LocalTime.parse(campi[5]);

	            double prezzoBase = Double.parseDouble(campi[6]);
	          
	            if (prezzoBase <= 0) {
	                System.out.println("Riga non valida nel file " + percorsoFile + ": prezzo base");
	                continue;
	            }

	            // Campi extra (cambiano significato a seconda del tipo di proiezione)
	            String extra1 = campi[7];
	            String extra2 = campi[8];
	            String extra3 = campi[9];
	            
	           
	            String[] pezziTag = campi[10].split(",");
	            for (int i = 0; i < pezziTag.length; i++) {
					pezziTag[i] = pezziTag[i].trim().toUpperCase();
				}

	           
	            Film filmCercato = null;
	            for (Film film : gestore.getListaFilm()) {
					if (film.getIdentificativoNumericoUnivoco() == filmId) {
						filmCercato = film;
						break;
					}
				}
				if (filmCercato == null) {
					System.out.println("Riga non valida nel file " + percorsoFile + ": film");
					continue; 
				}

	            
				Sala salaCercata = null;
				for (Sala sala : gestore.getListaSale()) {
					if (sala.getIdentificativoNumericoUnivoco() == salaId) {
						salaCercata = sala;
						break;
					}
				}
				if (salaCercata == null) {
					System.out.println("Riga non valida nel file " + percorsoFile + ": sala");
					continue; 
				}

	            Proiezione nuovaProiezione = null;

	            // In base al tipo, instanziamoo l'oggetto corretto (Polimorfismo)
	          
	            if (tipo.equals("STANDARD")) {
					nuovaProiezione = new ProiezioneStandard(filmCercato, salaCercata, data, oraInizio, prezzoBase, pezziTag);
				} 
				else if (tipo.equals("TRE_D")) {
					double supplemento3D = Double.parseDouble(extra1);
					boolean occhialiInclusi = Boolean.parseBoolean(extra2);
					nuovaProiezione = new Proiezione3d(filmCercato, salaCercata, data, oraInizio, prezzoBase, pezziTag, supplemento3D, occhialiInclusi);
				} 
				else if (tipo.equals("EVENTO")) {
					String nomeEvento = extra1;
					String ospite = extra2;
					boolean postiLimitati = Boolean.parseBoolean(extra3);
					nuovaProiezione = new EventoSpeciale(filmCercato, salaCercata, data, oraInizio, prezzoBase, pezziTag, nomeEvento, ospite, postiLimitati);
				}

	            if (nuovaProiezione != null) {
					gestore.aggiungiProiezione(nuovaProiezione);
				}
	        }
	        
	        lettore.close();
	    }
	}
