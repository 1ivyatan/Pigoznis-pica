package picerija;

public class PicasSastavdala extends DatuVieniba implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private double cena;
	private String vards, piezime;
	
	public double getCena() {
		return this.cena;
	}
	
	public void setCena(float cena) {
		this.cena = cena;
	}

	public void setVards(String v) {
		this.vards = v;
	}
	
	public void setPiezime(String v) {
		this.piezime = v;
	}
	
	public String getVards() {
		return this.vards;
	}
	
	public String getPiezime() {
		return this.piezime;
	}
	
	@Override
	public String getNosaukums() {
		return this.vards;
	}

	public String getInfo() {
		return this.vards + "\n" + "Cena: " + this.cena + "\n\n" + this.piezime;
	}

	public PicasSastavdala(String v, String p, double c) {
		this.vards = v;
		this.piezime = p;
		this.cena = c;
	}
	
	public DatuVieniba copy() {
		return new PicasSastavdala(this.vards, this.piezime, this.cena);
	}
}
