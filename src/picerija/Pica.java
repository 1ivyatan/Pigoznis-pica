package picerija;

import java.util.ArrayList;

public class Pica extends DatuVieniba implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String vards, piezime;
	private double cena, diametrsCm;
	private ArrayList<DatuVieniba> sastavdalas;
	
	@Override
	public String getNosaukums() {
		return this.vards;
	}

	@Override
	public String getInfo() {
		return "asd";
	}
	
	public Pica(String vards, String piezime, double cena, double diametrsCm, ArrayList<DatuVieniba> sastav) {
		this.vards = vards;
		this.piezime = piezime;
		this.cena = cena;
		this.diametrsCm = diametrsCm;
		this.sastavdalas = sastav;
	}

}
