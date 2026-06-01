package classiModello;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import classiModello.Sala;
import classiModello.Film;


public class lettoreDatiCinema { // da confrontare con quello in merge-manuel
	
///FILM

	public List<Film> leggiFilm(String percorsoFile) {
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
			
			Film film = new Film (id,titolo,regista,durataMinuti,dataUscita);
			
			for (String g: generiString) {
				GenereFilm genereEnum = GenereFilm.valueOf(g.trim().toUpperCase());
				film.setGeneri(genereEnum);
			}
			
			gestore.aggiungiFilm(film);
			
			lettore.close();
		}
	}

///SALE

		public void caricaSale(String percorsoFile,GestoreCinema gestore) {
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
			
				Sala sala = new Sala(id,nome,numeroPosti,supporta3D); // manca la parte delle caratteristiche
			
				for(String c: caratteristicheArray) {
					sala.aggiungiCarrateristica(c.trim());
				}
				gestore.aggiungiSala(sala);
			}
			lettore.close();
		}
		// voto estetica: cuboso
}
