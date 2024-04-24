package picerija;

import java.io.Serializable;
import java.util.ArrayList;

public class Dati implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/* saraksti */
	private ArrayList<Object> kontakti;
	
	public String[] getKontaktiStr() {
		String[] ret = new String[this.kontakti.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = ((Kontakts) this.kontakti.get(i)).getVards();
		}
		return ret;
	}
	
	/* mainīgie */
	private String valutasSim = "EUR";
	
	/* s/geteri */
	public ArrayList<Object> getKontakti() {
		return this.kontakti;
	}
	
	public String getValutasSim() {
		return this.valutasSim;
	}
	
	public void setValutasSim(String valuta) {
		this.valutasSim = valuta;
	}
	
	/* kons */
	public Dati() {
		this.kontakti = new ArrayList<Object>();
		
		// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		this.kontakti.add(new Kontakts("Jaan"));
		this.kontakti.add(new Kontakts("Pols"));
		this.kontakti.add(new Kontakts("ttttttttt"));
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	}
}