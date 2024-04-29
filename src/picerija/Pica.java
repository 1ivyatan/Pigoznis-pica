package picerija;

import java.util.ArrayList;

public class Pica extends DatuVieniba implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String vards, piezime;
	private double cena, diametrsCm, baseCena;
	private ArrayList<DatuVieniba> sastavdalas;
	
	@Override
	public String getNosaukums() {
		return this.vards;
	}

	@Override
	public String getInfo() {
		String ret = 
			this.vards + "\n" +
			"Kopējā cena: " + this.cena + "\n" +
			"Cena: " + this.baseCena + "\n" +
			"Diametrs: " + this.diametrsCm + "\n\n ---- Sastāvdaļas ---\nNos.\t\t\tCena\n"
		;
		
		for (DatuVieniba i : sastavdalas) {
			ret += ((PicasSastavdala) i).getVards() + "\t\t" + ((PicasSastavdala) i).getCena() + "\n";
		}
		
		ret +=
			"\n ---- Piezīme ---\n" + this.piezime
		;
		
		return ret;
	}
	
	/* get */
	public String getVards() {
		return this.vards;
	}
	
	public String getPiezime() {
		return this.piezime;
	}
	
	public double getCena() {
		return this.cena;
	}
	
	public double getBaseCena() {
		return this.baseCena;
	}
	
	public double getDiametrsCm() {
		return this.diametrsCm;
	}
	
	public ArrayList<DatuVieniba> getSastavdalas() {
		return this.sastavdalas;
	}
	
	/* set */
	private void setCena() {
		this.cena = baseCena;
		
		if (this.sastavdalas != null && !this.sastavdalas.isEmpty()) {
			for (int i = 0; i < sastavdalas.size(); i++) {
				this.cena += ((PicasSastavdala) sastavdalas.get(i)).getCena();
			}
		}
	}
	
	public void setVards(String vards) {
		this.vards = vards;
	}
	
	public void setPiezime(String piezime) {
		this.piezime = piezime;
	}
	
	public void setBaseCena(double cena) {
		this.baseCena = cena;
		setCena();
	}
	
	public void setDiametrsCm(double diametrscm) {
		this.diametrsCm = diametrscm;
	}
	
	public void setSastavdalas(ArrayList<DatuVieniba> sastavdalas) {
		this.sastavdalas = sastavdalas;
		this.setCena();
	}
	
	/* kons */
	public Pica(String vards, String piezime, double baseCena, double diametrsCm, ArrayList<DatuVieniba> sastav) {
		this.vards = vards;
		this.piezime = piezime;
		this.baseCena = baseCena;
		this.diametrsCm = diametrsCm;
		this.sastavdalas = sastav;
		this.setCena();
	}
	
	public DatuVieniba copy() {
		return new Pica(this.vards, this.piezime, this.baseCena, this.diametrsCm, new ArrayList<DatuVieniba>(this.sastavdalas));
	}
}
