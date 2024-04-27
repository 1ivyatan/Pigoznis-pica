package picerija;

public class Kontakts extends DatuVieniba implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/* kontakta informacija */
	private String vards, adrese, numurs, piezime;
	
	/* get */
	public String getNosaukums() {
		return this.vards;
	}
	
	public String getVards() {
		return this.vards;
	}

	public String getAdrese() {
		return this.adrese;
	}
	
	public String getNumurs() {
		return this.numurs;
	}
	
	public String getPiezime() {
		return this.piezime;
	}

	public String getInfo() {
		return this.vards + "\n"
				+ "Adrese: " + this.adrese + "\n" +
				  "Numurs: " + numurs + "\n\n" + piezime;
	}
	
	/* kons */
	public Kontakts(String vards, String adrese, String numurs, String piezime) {
		this.vards = vards;
		this.adrese = adrese; 
		this.numurs = numurs; 
		this.piezime = piezime;
	}
}
