package picerija;

import java.util.ArrayList;

public class Pica extends DatuVieniba implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String vards, piezime;
	private double cena, diametrsCm, totalCena;
	private ArrayList<DatuVieniba> sastavdalas;
	
	@Override
	public String getNosaukums() {
		return this.vards;
	}

	@Override
	public String getInfo() {
		String ret = 
			this.vards + "\n" +
			"Kopējā cena: " + this.totalCena + "\n" +
			"Cena: " + this.cena + "\n" +
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
	
	public double getTotalCena() {
		return totalCena;
	}
	
	public double getDiametrsCm() {
		return this.diametrsCm;
	}
	
	public ArrayList<DatuVieniba> getSastavdalas() {
		return this.sastavdalas;
	}
	
	/* set */
	private void setTotalCena() {
		this.totalCena = cena;
		
		if (this.sastavdalas != null && !this.sastavdalas.isEmpty()) {
			for (int i = 0; i < sastavdalas.size(); i++) {
				this.totalCena += ((PicasSastavdala) sastavdalas.get(i)).getCena();
			}
		}
	}
	
	public void setVards(String vards) {
		this.vards = vards;
	}
	
	public void setPiezime(String piezime) {
		this.piezime = piezime;
	}
	
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public void setDiametrsCm(double diametrscm) {
		this.diametrsCm = diametrscm;
	}
	
	public void setSastavdalas(ArrayList<DatuVieniba> sastavdalas) {
		this.sastavdalas = sastavdalas;
		this.setTotalCena();
	}
	
	/* kons */
	public Pica(String vards, String piezime, double cena, double diametrsCm, ArrayList<DatuVieniba> sastav) {
		this.vards = vards;
		this.piezime = piezime;
		this.cena = cena;
		this.diametrsCm = diametrsCm;
		this.sastavdalas = sastav;
		this.setTotalCena();
	}
	
	public DatuVieniba copy() {
		return new Pica(this.vards, this.piezime, this.cena, this.diametrsCm, new ArrayList<DatuVieniba>(this.sastavdalas));
	}
}
