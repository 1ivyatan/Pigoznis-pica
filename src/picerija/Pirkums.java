package picerija;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pirkums extends DatuVieniba implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/* dati */
	private ArrayList<DatuVieniba> preces = null;
	private DatuVieniba sanemejs = null;
	private String nosaukums;
	private double cena, piegCena;
	private boolean gatavs, piegadeUzAdresi;
	
	/* get */
	public ArrayList<DatuVieniba> getPreces() {
		return this.preces;
	}
	
	public DatuVieniba getSanemejs() {
		return this.sanemejs;
	}
	
	public double getCena() {
		return this.cena;
	}
	
	public double getPiegCena() {
		return this.piegCena;
	}
	
	public boolean getGatavs() {
		return this.gatavs;
	}
	
	public boolean getPiegadeUzAdresi() {
		return this.piegadeUzAdresi;
	}
	
	public String getNosaukums() {
		return this.nosaukums;
	}
	
	/* set */
	public void setPreces(ArrayList<DatuVieniba> jp) {
		if (this.gatavs) return;
		this.preces = jp;
		setCena();
	}
	
	public void setSanemejs(DatuVieniba kontakts) {
		if (this.gatavs) return;
		this.sanemejs = kontakts;
	}
	
	public void setPiegCena(double cena) {
		if (this.gatavs) return;
		this.piegCena = cena;
		setCena();
	}
	
	public void setCena() {
		if (this.gatavs) return;
		if (this.piegadeUzAdresi) this.cena = this.piegCena;
		else this.cena = 0;
		
		for (DatuVieniba prece : this.preces) {
			this.cena += ((Pica) prece).getCena();
		}
	}
	
	public void setGatavs(boolean gatavs) {
		if (this.gatavs) return;
		this.gatavs = gatavs;
	}
	
	public void setPiegadeUzAdresi(boolean piegade) {
		if (this.gatavs) return;
		this.piegadeUzAdresi = piegade;
	}
	
	/* kons */
	public Pirkums(DatuVieniba sanemejaKontakts, ArrayList<DatuVieniba> preces, boolean gatavs, boolean piegade, int no) {
		this.gatavs = gatavs;
		this.piegadeUzAdresi = piegade;
		
		this.sanemejs = sanemejaKontakts;
		this.preces = preces;
		
		LocalDateTime datums = LocalDateTime.now();
		this.nosaukums = "#" + no + " - " + datums;
		
		this.setCena();
	}
	
	public Pirkums(Pirkums pirkums) {
		this.cena = pirkums.cena;
		
		this.gatavs = pirkums.gatavs;
		this.piegadeUzAdresi = pirkums.piegadeUzAdresi;
		
		this.sanemejs = pirkums.sanemejs;
		
		this.nosaukums = pirkums.nosaukums;
	}
	
	public DatuVieniba copy() {
		return new Pirkums(this);
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}
