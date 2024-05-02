package picerija;

public class Aprekini {
	public static boolean pareizsFloat(String src) {						/*  tas nav float*/
		if (src == null || src.isBlank() || src.isEmpty()) return false;	/* vai ir tāda virkne */
		
		Double num;															/* vai var dabūt skaitli*/
		try {
			num = Double.parseDouble(src);
		} catch (Exception e) {
			return false;
		}
		
		if (Double.isNaN(num) || num <= 0) return false;					/* neg. float? */
		return true;
	}
	
	public static double uzFloat(String src) {
		if (pareizsFloat(src)) return Double.parseDouble(src);
		else return -1;
	}
	
	public static double apalotFloatUz2(String src) {
		Double fl = uzFloat(src);
		if (fl == -1) return (double) Math.round(fl * 100) / 100;
		else return -1;
	}
}
