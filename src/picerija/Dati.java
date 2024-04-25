package picerija;

import java.io.Serializable;
import java.util.ArrayList;

public class Dati implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/* saraksti */
	private ArrayList<Kontakts> kontakti;
	
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
	/* valūta */
	public String getValutasSim() {
		return this.valutasSim;
	}
	
	public void setValutasSim(String valuta) {
		this.valutasSim = valuta;
	}
	
	/* kontakts */
	public ArrayList<Kontakts> getKontakti() {
		return this.kontakti;
	}
	
	public void addToKontakts(Kontakts nk) {
		this.kontakti.add(nk);
	}
	
	public void editKontaktsIdx(int idx, Kontakts nk) {
		this.kontakti.set(idx, nk);
	}
	
	public void removeKontaktsIdx(int idx) {
		this.kontakti.remove(idx);
	}
	
	/* kons */
	public Dati() {
		this.kontakti = new ArrayList<Kontakts>();
	}
}