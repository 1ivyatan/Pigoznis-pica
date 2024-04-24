package picerija;

public class Kontakts extends DatiemSaraksts implements java.io.Serializable {
	private String vards;
	
	/* s/geteri */
	public String getVards() {
		return this.vards;
	}
	
	public void setVards(String jv) {
		this.vards = jv;
	}
	
	/* metodes */
	public String kaVirkne() {
		return (
			this.vards
		);
	}
	
	/* kons */
	public Kontakts(String vards) {
		this.vards = vards;
	}
}
