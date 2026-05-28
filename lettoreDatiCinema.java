package modellicinema;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class lettoreDatiCinema {
	
///FILM

	public List<Film> leggiFilm(String percorsoFile){
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
				film.aggiungiGenere(genereEnum);
			}
			
			gestore.aggiungiFilm(film);
			
			lettore.close();
			
		}

///SALE

		public void caricaSale(String percorsoFile,GestoreCinema gestore)  {
			
	
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
			
			Sala sala = new Sala(id,nome,numeroPosti,supporta3D);
			
			for(String c: caratteristicheArray) {
				sala.aggiungiCarrateristica(c.trim());
			}
			
			gestore.aggiungiSala(sala);
		}	
		
	
		
		lettore.close();
		
	
}

	
	
	public void caricaProiezioni(String percorsoFile, GestoreCinema gestore)  {
        File f = new File(percorsoFile);
        Scanner lettore = new Scanner(f);

        while (lettore.hasNextLine()) {
            String riga = lettore.nextLine();

            if (riga.trim().isEmpty()) {
                continue;
            }

            String[] campi = riga.split(";");

   
            if (campi.length != 11) {
                System.out.println("Riga non valida nel file " + percorsoFile + ": numero campi errato");
                continue;
            }

            int id = Integer.parseInt(campi[0]);
            String tipo = campi[1].toUpperCase();
            

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

          
            String extra1 = campi[7];
            String extra2 = campi[8];
            String extra3 = campi[9];
            
        
            String[] pezziTag = campi[10].split(",");

          
            Film filmCercato = gestore.getArchivioFilm().cercaPerId(filmId);
            if (filmCercato == null) {
                System.out.println("Riga non valida nel file " + percorsoFile + ": film");
                continue; 
            }


            Sala salaCercata = gestore.getArchivioSale().cercaPerId(salaId);
            if (salaCercata == null) {
                System.out.println("Riga non valida nel file " + percorsoFile + ": sala");
                continue; 
            }


            Proiezione nuovaProiezione = null;

            if (tipo.equals("STANDARD")) {
                nuovaProiezione = new ProiezioneStandard(id, filmCercato, salaCercata, data, oraInizio, prezzoBase);
            } 
            else if (tipo.equals("TRE_D")) {
                double supplemento3D = Double.parseDouble(extra1);
                boolean occhialiInclusi = Boolean.parseBoolean(extra2);
                nuovaProiezione = new Proiezione3D(id, filmCercato, salaCercata, data, oraInizio, prezzoBase, supplemento3D, occhialiInclusi);
            } 
            else if (tipo.equals("EVENTO")) {
                String nomeEvento = extra1;
                String ospite = extra2;
                boolean postiLimitati = Boolean.parseBoolean(extra3);
                nuovaProiezione = new EventoSpeciale(id, filmCercato, salaCercata, data, oraInizio, prezzoBase, nomeEvento, ospite, postiLimitati);
            }

            if (nuovaProiezione != null) {
                for (String t : pezziTag) {
                    nuovaProiezione.aggiungiTag(t.trim().toLowerCase());
                }
                gestore.aggiungiProiezione(nuovaProiezione);
            }
        }
        
        lettore.close();
    }
}