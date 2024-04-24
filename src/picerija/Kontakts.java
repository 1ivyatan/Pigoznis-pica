package picerija;

public class Kontakts {
	private String vards;
	
	/* s/geteri */
	public String getVards() {
		return this.vards;
	}
	
	public void setVards(String jv) {
		this.vards = jv;
	}
	
	/* kons */
	Kontakts(String vards) {
		this.vards = vards;
	}
}
