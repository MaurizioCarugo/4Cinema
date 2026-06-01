package classiModello;

import java.time.LocalDate;
import java.time.LocalTime;

public class ProiezioneStandard extends Proiezione {

	public ProiezioneStandard(Film film, Sala sala, LocalDate data, LocalTime oraProiezione, double prezzoBase,
			String[] tags) {
		super(film, sala, data, oraProiezione, prezzoBase, tags);
		
	}

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public double calcolaPrezzoFinale() {
		double prezzoFinale= prezzoBase;
		
		if(IsInWeekend()) {
			prezzoFinale +=2.00;
		}
		
		if(IsSerale()) {
			prezzoFinale +=1.50;
		}
		
		return prezzoFinale;
	}

	
}
