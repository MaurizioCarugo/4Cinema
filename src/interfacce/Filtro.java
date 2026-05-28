package interfacce;

public interface Filtro <T> {
	// servirà per film e proiezioni (anche in questo caso credo che quella astratta vada bene)
	boolean accetta(T elemento);
}
