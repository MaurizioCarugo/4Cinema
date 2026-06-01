package classiModello;

import java.time.LocalDate;
import java.time.LocalTime;

public class Proiezione3d extends Proiezione {

	private double supplemento3D;
	private boolean occhialiInclusi;
	
	public Proiezione3d(Film film, Sala sala, LocalDate data, LocalTime oraProiezione, double prezzoBase, String[] tags,
			double supplemento3d, boolean occhialiInclusi) {
		
		super(film, sala, data, oraProiezione, prezzoBase, tags);
		
		if (!sala.isHas3D()) {
			System.out.println("ERRORE: La sala '" + sala.getNomeDellaSala() + "' non supporta proiezioni in 3D!");
		}
		
		supplemento3D = supplemento3d;
		this.occhialiInclusi = occhialiInclusi;
		
	}

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public double calcolaPrezzoFinale() {
     double prezzoFinale= getPrezzoBase();
     prezzoFinale += this.supplemento3D;
    	
		if(IsInWeekend()) {
			prezzoFinale +=2.00;
		}
		
		if (!this.occhialiInclusi) {
			prezzoFinale += 1.00;
		}
		
		return prezzoFinale;
	}

	public double getSupplemento3D() {
		return supplemento3D;
	}

	public void setSupplemento3D(double supplemento3d) {
		supplemento3D = supplemento3d;
	}

	public boolean isOcchialiInclusi() {
		return occhialiInclusi;
	}

	public void setOcchialiInclusi(boolean occhialiInclusi) {
		this.occhialiInclusi = occhialiInclusi;
	}
	
	

	
}
	
	
	
	

