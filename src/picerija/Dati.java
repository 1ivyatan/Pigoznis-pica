package picerija;

import java.io.Serializable;
import java.util.ArrayList;

public class Dati implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/* saraksti */
	private ArrayList<DatiemSaraksts> kontakti;
	private ArrayList<DatiemSaraksts> sastavdalas;
	
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
	public ArrayList<DatiemSaraksts> getKontakti() {
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
	
	/* sastāvdaļas */
	public ArrayList<DatiemSaraksts> getSastavdalas() {
		return this.sastavdalas;
	}
	
	public void addToSastavdalas(Sastavdala nk) {
		this.sastavdalas.add(nk);
	}
	
	public void editSastavdalaIdx(int idx, Sastavdala nk) {
		this.sastavdalas.set(idx, nk);
	}
	
	public void removeSastavdalaIdx(int idx) {
		this.sastavdalas.remove(idx);
	}
	
	/* kons */
	public Dati() {
		this.kontakti = new ArrayList<DatiemSaraksts>();
		this.sastavdalas = new ArrayList<DatiemSaraksts>();
		
		this.sastavdalas.add(new PicasMikla("ssss", 12, 30));
	}
}