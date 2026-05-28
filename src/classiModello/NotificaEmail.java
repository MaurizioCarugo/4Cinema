package classiModello;

import interfacce.Notificatore;
import classiModello.Proiezione;

public class NotificaEmail implements Notificatore {

	@Override
	public String notifica(Proiezione proiezione) {
		return "Email: nuova proiezione programmata per "+proiezione.getFilm().getTitolo()+" il "+proiezione.getData()+" alle "+proiezione.getOraInizio();
	}

}
