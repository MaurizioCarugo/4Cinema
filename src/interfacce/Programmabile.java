package interfacce;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface Programmabile {
	// da implementare in Proiezione(quella astratta)
	LocalDate getData();
	LocalTime getOraInizio();
	default LocalDateTime getDataOraInizio() {
		if (getData() != null && getOraInizio() != null) {
			return LocalDateTime.of(getData(), getOraInizio());
		}
		return null;
	}
}
