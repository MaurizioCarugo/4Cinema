package classiModello;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventoSpeciale extends Proiezione {

	private String nomeEvento;
	private String ospite;
	private boolean postiLimitati;
	public EventoSpeciale(Film film, Sala sala, LocalDate data, LocalTime oraProiezione, double prezzoBase,
			String[] tags, String nomeEvento, String ospite, boolean postiLimitati) {
		super(film, sala, data, oraProiezione, prezzoBase, tags);
		this.nomeEvento = nomeEvento;
		this.ospite = ospite;
		this.postiLimitati = postiLimitati;
	}
	
	
	
	@Override
	public int getID() {
		return 0;
	}
	@Override
	public double calcolaPrezzoFinale() {

		double prezzoFinale = getPrezzoBase();
		prezzoFinale += 5.00;
		
		if (this.postiLimitati) {
			prezzoFinale += 3.00;
		}
		
		if (IsSerale()) {
			prezzoFinale += 1.50;
		}
		
		return prezzoFinale;
	}



	public String getNomeEvento() {
		return nomeEvento;
	}



	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}



	public String getOspite() {
		return ospite;
	}



	public void setOspite(String ospite) {
		this.ospite = ospite;
	}



	public boolean isPostiLimitati() {
		return postiLimitati;
	}



	public void setPostiLimitati(boolean postiLimitati) {
		this.postiLimitati = postiLimitati;
	}
	
	
	
	
	
	
}
