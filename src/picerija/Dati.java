package picerija;

import java.io.Serializable;
import java.util.ArrayList;

public class Dati implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/* saraksti */
	private ArrayList<DatuVieniba> kontakti;
	private ArrayList<DatuVieniba> sastavdalas;
	private ArrayList<DatuVieniba> picas;

	private ArrayList<DatuVieniba> pirkumi;
	private ArrayList<DatuVieniba> pirkumuVest;
	
	/* mainīgie */
	private String valutasSim = "€";
	private String mervienibasSim = "cm";
	private double piegadesCena = 4.0;
	
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
	
	/* picas */
	public void nonemtPicu(int idx) {
		if (picas.size() > 0)
			this.picas.remove(idx);
	}
	
	public void pievienotPicu(DatuVieniba jc) {
		if (jc != null)
			this.picas.add(jc);
	}
	
	public void nomainitPicu(int idx, DatuVieniba jc) {
		if (picas.size() > 0)
			this.picas.set(idx, jc);
	}
	
	public ArrayList<DatuVieniba> getPicas() {
		return this.picas;
	}
	
	/* pasūtījumi */
	public void nonemtPasut(int idx) {
		if (pirkumi.size() > 0)
			this.pirkumi.remove(idx);
	}
	
	public void pievienotPasut(DatuVieniba jp) {
		if (jp != null)
			this.pirkumi.add(jp);
	}
	
	public void nomainitPasut(int idx, DatuVieniba jp) {
		if (pirkumi.size() > 0)
			this.pirkumi.set(idx, jp);
	}
	
	public ArrayList<DatuVieniba> getPasut() {
		return this.pirkumi;
	}
	
	/* pasūtījumi */
	public void nonemtPasutVest(int idx) {
		if (pirkumuVest.size() > 0)
			this.pirkumuVest.remove(idx);
	}
	
	public void uzPasutVest(int idx) {
		if (-1 < idx && idx < this.pirkumi.size()) {
			DatuVieniba parv = this.pirkumi.get(idx);
			this.pirkumuVest.add(parv);
			this.pirkumi.remove(idx);
		}
	}
	
	public ArrayList<DatuVieniba> getPasutVest() {
		return this.pirkumuVest;
	}
	
	/* geteri */
	public double getPiegadesCena() {
		return this.piegadesCena;
	}
	
	public String getValutasSim() {
		return this.valutasSim;
	}
	
	public String getMervienibasSim() {
		return this.mervienibasSim;
	}
	
	/* seteri */
	public void setPiegadesCena(double cena) {
		this.piegadesCena = cena;
	}
	
	public void setValutasSim(String valuta) {
		this.valutasSim = valuta;
	}

	public void setMervienibasSim(String m) {
		this.mervienibasSim = m;
	}
	
	/* kons */
	public Dati() {
		this.kontakti = new ArrayList<DatuVieniba>();
		this.sastavdalas = new ArrayList<DatuVieniba>();
		this.picas = new ArrayList<DatuVieniba>();
		this.pirkumi = new ArrayList<DatuVieniba>();
		this.pirkumuVest = new ArrayList<DatuVieniba>();
		
		// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		this.kontakti.add(new Kontakts("Jaan", "dd", "dd", "dd"));
		this.kontakti.add(new Kontakts("paan", "dd", "dd", "dd"));
		this.kontakti.add(new Kontakts("Ajumu", "dd", "dd", "dd"));
		
		this.sastavdalas.add(new PicasSastavdala("Desas", "ggggggg", 1.00));
		this.sastavdalas.add(new PicasSastavdala("iiiiiiiiiesas", "ggggggg", 1.00));
		this.sastavdalas.add(new PicasSastavdala("Desas", "ggggggg", 1.00));
		
		this.picas.add(new Pica("pica", "asdasddas", 11.0, 25.0, new ArrayList<DatuVieniba>()));
		this.picas.add(new Pica("pica", "asdasddas", 11.0, 25.0, new ArrayList<DatuVieniba>()));
		this.picas.add(new Pica("pica", "asdasddas", 11.0, 25.0, new ArrayList<DatuVieniba>()));
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	}
}