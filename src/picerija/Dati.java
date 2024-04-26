package picerija;

import java.io.Serializable;
import java.util.ArrayList;

public class Dati implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/* saraksti */
	private ArrayList<DatuVieniba> kontakti;
	
	/* mainīgie */
	private String valutasSim = "EUR";
	
	/* kontakti */
	public void nonemtKontaktu(int idx) {
		if (kontakti.size() > 0)
			this.kontakti.remove(idx);
	}
	
	/* geteri */
	public ArrayList<DatuVieniba> getKontakti() {
		return this.kontakti;
	}
	
	public String getValutasSim() {
		return this.valutasSim;
	}
	
	/* seteri */
	public void setValutasSim(String valuta) {
		this.valutasSim = valuta;
	}
	
	/* kons */
	public Dati() {
		this.kontakti = new ArrayList<DatuVieniba>();
		
		// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		this.kontakti.add(new Kontakts("Jaan", "dd", "dd", "dd"));
		this.kontakti.add(new Kontakts("paan", "dd", "dd", "dd"));
		this.kontakti.add(new Kontakts("Ajumu", "dd", "dd", "dd"));
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	}
}