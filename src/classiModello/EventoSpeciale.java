package classiModello;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventoSpeciale extends Proiezione {
	private String nomeEvento; 
	private String ospite;
	private boolean postiLimitati;
	
	public EventoSpeciale(Film film, Sala sala, LocalDate data, LocalTime oraProiezione, double prezzoBase, String[] tags, String nomeEvento, String ospite, boolean postiLimitati) {
		super(film, sala, data, oraProiezione, prezzoBase, tags);
		this.nomeEvento = nomeEvento;
		this.ospite = ospite;
		this.postiLimitati = postiLimitati;
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

	@Override
	public int getID() {
		return super.getId();
	}

	@Override
	public double calcolaPrezzoFinale() {
		double prezzoFinale = super.getPrezzoBase() + 5.00;
		if (super.isSerale()) {
			prezzoFinale += 1.50;
		}
		if (!postiLimitati) {
			prezzoFinale += 3.00;
		}
		return prezzoFinale;
	}

	@Override
	public String getTipoProiezione() {
		return "Evento Speciale";
	}

	@Override
	public String toString() {
		return "EventoSpeciale [nomeEvento=" + nomeEvento + ", ospite=" + ospite + ", postiLimitati=" + postiLimitati + super.toString() + calcolaPrezzoFinale() + "]";
	}

	@Override
	public boolean accetta(LocalDate elemento) {
		// TODO Auto-generated method stub
		return false;
	} 
	
	
	
	
	
	
}
