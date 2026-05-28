package classiModello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import interfacce.Identificabile;

public class Archivio <T extends Identificabile> {
	private Map<Integer, T> elementi;
	
	public void aggiungi(T elemento) {
		if (elemento != null) {
			elementi.put(elemento.getID(), elemento);
		} else {
			System.out.println("Elemento non riconosciuto");
		}
	}
	
	public T cercaPerID(int id) {
		return elementi.get(id);
	}
	
	public boolean rimuoviPerId(int id) {
		elementi.remove(id);
		return elementi.remove(id) == null;
	}
	
	// da guardare perchè non l'ho capito
	public List<T> trovaTutti() {
		List<T> tutti = new ArrayList<>();
		for (T elemento : elementi.values()) {
			if (elemento instanceof T) {
				tutti.add(elemento);
			}
		}
		return tutti;
	}
	
	public int contaElementi() {
		return elementi.size();
	}

}
