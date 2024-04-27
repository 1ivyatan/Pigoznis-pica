package picerija;

import java.io.Serializable;
import java.util.ArrayList;

public class Dati implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/* saraksti */
	private ArrayList<DatuVieniba> kontakti;
	private ArrayList<DatuVieniba> sastavdalas;
	
	/* mainīgie */
	private String valutasSim = "EUR";
	
	/* kontakti */
	public void nonemtKontaktu(int idx) {
		if (kontakti.size() > 0)
			this.kontakti.remove(idx);
	}
	
	public void pievienotKontaktu(DatuVieniba jk) {
		if (jk != null)
			this.kontakti.add(jk);
	}
	
	public void nomainitKontaktu(int idx, DatuVieniba jk) {
		if (kontakti.size() > 0)
			this.kontakti.set(idx, jk);
	}

	public ArrayList<DatuVieniba> getKontakti() {
		return this.kontakti;
	}
	
	/* sastāvdaļas */
	public void nonemtSastavdalu(int idx) {
		if (sastavdalas.size() > 0)
			this.sastavdalas.remove(idx);
	}
	
	public void pievienotSastavdalu(DatuVieniba jc) {
		if (jc != null)
			this.sastavdalas.add(jc);
	}
	
	public void nomainitSastavdalu(int idx, DatuVieniba jc) {
		if (sastavdalas.size() > 0)
			this.sastavdalas.set(idx, jc);
	}
	
	public ArrayList<DatuVieniba> getSastavdalas() {
		return this.sastavdalas;
	}
	
	/* geteri */
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
		this.sastavdalas = new ArrayList<DatuVieniba>();
		
		// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		this.kontakti.add(new Kontakts("Jaan", "dd", "dd", "dd"));
		this.kontakti.add(new Kontakts("paan", "dd", "dd", "dd"));
		this.kontakti.add(new Kontakts("Ajumu", "dd", "dd", "dd"));
		
		this.sastavdalas.add(new PicasSastavdala("Desas", "ggggggg", 1.00));
		this.sastavdalas.add(new PicasSastavdala("iiiiiiiiiesas", "ggggggg", 1.00));
		this.sastavdalas.add(new PicasSastavdala("Desas", "ggggggg", 1.00));
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	}
}