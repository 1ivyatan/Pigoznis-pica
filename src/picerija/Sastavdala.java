package picerija;

public abstract class Sastavdala extends DatiemSaraksts implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	protected String nosaukums;
	protected float cena;
	
	/* sgeteri */
	public float getCena() {
		return this.cena;
	}
	
	public void setCena(float cena) {
		this.cena = cena;
	}
	
	public void setNosaukums(String nos) {
		this.nosaukums = nos;
	}
	
	public String nosaukums() {
		return nosaukums;
	}
}
