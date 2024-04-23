package picerija;

import java.io.Serializable;

public class Dati implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/* mainīgie */
	private String valutasSim = "EUR";
	
	/* s/geteri */
	public String getValutasSim() {
		return this.valutasSim;
	}
	
	public void setValutasSim(String valuta) {
		this.valutasSim = valuta;
	}
	
}