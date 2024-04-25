package picerija;

public class Kontakts extends DatiemSaraksts implements java.io.Serializable {
	private String vards, adrese, talrunis, piezime;
	
	/* s/geteri */
	public String getVards() { return this.vards; }
	public String getAdrese() { return this.adrese; }
	public String getTalrunis() { return this.talrunis; }
	public String getPiezime() { return this.piezime; }
	
	public void setVards(String jv) { this.vards = jv; }
	public void setAdrese(String ja) { this.adrese = ja; }
	public void setTalrunis(String jt) { this.talrunis = jt; }
	public void setPiezime(String jp) { this.piezime = jp; }
	
	/* metodes */
	public String kaVirkne() {
		return (
			  this.vards + "\n"
			+ "Adrese: " + this.adrese + "\n"
			+ "Tālrunis: " + this.talrunis + "\n\n"
			+ this.piezime
		);
	}
	
	public String nosaukums() {
		return this.getVards();
	}
	
	/* kons */
	public Kontakts(String vards, String adrese, String talrunis, String piezime) {
		this.vards = vards;
		this.adrese = adrese;
		this.talrunis = talrunis;
		this.piezime = piezime;
	}
}
