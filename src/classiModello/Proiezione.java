package classiModello;

import java.time.*;

public abstract class Proiezione {

	private static final String[] TAG_CONSENTITI= {"SERALE", "WEEKEND", "FAMIGLIE", "ANTEPRIMA", "LINGUA_ORIGINALE", "EVENTO", "TRE_D"};
	
	private static int identificativo = 0;
	private int codiceFilm;
	private int codiceSala;
	private LocalDate dataProiezione;
	private LocalTime oraProiezione;
	private double prezzoBase;
	private String tags;
	
	public abstract 
	
	public abstract double calcoloPrezzoFinale();

	public LocalDateTime getInizioProiezione() {		
		return dataProiezione.atTime(oraProiezione);
	}
	
	public LocalDateTime getFineProiezione(Duration durata) {
		return dataProiezione.atTime(oraProiezione.plus(durata));
	}
	
	public boolean IsToday() {
		if (dataProiezione.isEqual(LocalDate.now()))
			return true;
		else
			return false;
	}
	
	public boolean IsInWeekend() {
		if (dataProiezione.getDayOfWeek().getValue() == 6 || dataProiezione.getDayOfWeek().getValue() == 7)
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
	
}
