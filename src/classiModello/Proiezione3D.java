package classiModello;

import java.time.LocalDate;
import java.time.LocalTime;

public class Proiezione3D extends Proiezione {
	private double supplemento3D;
	private boolean occhialiniInclusi;
	
	public Proiezione3D(Film film, Sala sala, LocalDate data, LocalTime oraProiezione, double prezzoBase, String[] tags, double supplemento3d, boolean occhialiniInclusi) {
		super(film, sala, data, oraProiezione, prezzoBase, tags);
		supplemento3D = supplemento3d;
		this.occhialiniInclusi = occhialiniInclusi;
	}
	
	public double getSupplemento3D() {
		return supplemento3D;
	}

	public void setSupplemento3D(double supplemento3d) {
		supplemento3D = supplemento3d;
	}

	public boolean isOcchialiniInclusi() {
		return occhialiniInclusi;
	}

	public void setOcchialiniInclusi(boolean occhialiniInclusi) {
		this.occhialiniInclusi = occhialiniInclusi;
	}

	@Override
	public int getID() {
		return super.getId();
	}

	@Override
	public double calcolaPrezzoFinale() {
		double prezzoFinale = super.getPrezzoBase() + supplemento3D;
		if (super.isInWeekend()) {
			prezzoFinale += 2.00;
		}
		if (!occhialiniInclusi) {
			prezzoFinale += 1.00;
		}
		return prezzoFinale;
	}

	@Override
	public String getTipoProiezione() {
		return "Proiezione 3D";
	}

	@Override
	public String toString() {
		return "Proiezione3D [supplemento3D=" + supplemento3D + ", occhialiniInclusi=" + occhialiniInclusi + super.toString() + calcolaPrezzoFinale() + "]";
	}

	@Override
	public boolean accetta(LocalDate elemento) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
