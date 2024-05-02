package picerija;

public class Aprekini {
	public static boolean pareizsFloat(String src) {						/*  tas nav float*/
		if (src == null || src.isBlank() || src.isEmpty() || !src.matches("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$")) return false;	
																			/* vai ir tāda virkne */
		double num;															/* vai var dabūt skaitli*/
		try {
			num = Double.parseDouble(src);
		} catch (Exception e) {
			return false;
		}			
		
		if (Double.isNaN(num) || num < 0) return false;
		
		return true;
	}
	
	public static double uzFloat(String src) {
		if (pareizsFloat(src)) return Double.parseDouble(src);
		else return -1;
	}
	
	public static double apalotUzFloat2(String src) {
		Double fl = uzFloat(src);
		
		if (fl != -1) return (double) Math.round(fl * 100) / 100;
		else return -1;
	}
	
	public static double apalotUzFloat2(Double src) {
		return (double) Math.round(src * 100) / 100;
	}
}
