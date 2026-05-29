package classiModello;

import java.time.LocalDate;
import java.time.LocalTime;

public class ProiezioneStandard extends Proiezione {

	public ProiezioneStandard(Film film, Sala sala, LocalDate data, LocalTime oraProiezione, double prezzoBase, String[] tags) {
		super(film, sala, data, oraProiezione, prezzoBase, tags);
	}

	@Override
	public int getID() {
		return super.getId();
	}

	@Override
	public double calcolaPrezzoFinale() {
		double prezzoFinale = super.getPrezzoBase();
		if (super.isInWeekend()) {
			prezzoFinale += 2.00;
		}
		if (super.isSerale()) {
			prezzoFinale += 1.50;
		}
		return prezzoFinale;
	}

	@Override
	public String getTipoProiezione() {
		return "Standard";
	}

	@Override
	public String toString() {
		return "ProiezioneStandard "+super.toString()+calcolaPrezzoFinale();
	}

	@Override
	public boolean accetta(LocalDate elemento) {
		// TODO Auto-generated method stub
		return false;
	}
}
