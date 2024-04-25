package picerija;

public class PicasMikla extends Sastavdala implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private float radiusCm;
	
	public float getRadius() {
		return this.radiusCm;
	}
	
	public void setRadius(float radiuscm) {
		this.radiusCm = radiuscm;
	}
	
	@Override
	public String kaVirkne() {
		return "eeeeee";
	}
	
	/* kons */
	public PicasMikla(String vards, float cena, float radiusCm) {
		this.nosaukums = vards;
		this.cena = cena;
		this.radiusCm = radiusCm;
	}
}
