package picerija;

import java.io.Serializable;
import java.util.ArrayList;

class Kontakts {
	private String vards;
	
	/* s/geteri */
	private String getVards() {
		return this.vards;
	}
	
	private void setVards(String jv) {
		this.vards = jv;
	}
	
	/* kons */
	Kontakts(String vards) {
		this.vards = vards;
	}
}

public class Dati implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/* saraksti */
	private ArrayList<Kontakts> kontakti;
	
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