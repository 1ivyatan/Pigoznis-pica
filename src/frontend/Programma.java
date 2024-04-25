package frontend;

import java.io.File;
import java.util.ArrayList;

import picerija.DatiemSaraksts;
import picerija.Datubaze;
import picerija.Kontakts;

public class Programma {
	/* datubāze */
	private static Datubaze db = null;
	private static boolean izmaina = false;
	
	/* s/geteri */
	public static boolean getIzmaina() {
		return izmaina;
	}
	
	public static Datubaze getDb() {
		return db;
	}
		
	/* metodes */
	public static void aizvertDb() {
		izmaina = false;
		db.aizvert();
		db = null;
	}
	
	public static void tuksotDb() {
		db = new Datubaze();
	}
	
	public static void atvertDb(File fails) throws Exception {
		try {
			db = new Datubaze(fails);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void saglDb(File fails) throws Exception {
		try {
			if (fails != null) {
				db.setFails(fails);
				db.saglabat();
				izmaina = false;
			} else if (fails == null && db.getFails() == null) {
				throw new Exception("Jāievada fails pirms sagalbāšanas");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void saglDb(String vieta) throws Exception {
		try {
			if (vieta != null) {
				db.setFails(new File(vieta));
				db.saglabat();
				izmaina = false;
			} else if (vieta == null && db.getFails() == null) {
				throw new Exception("Jāievada fails pirms sagalbāšanas");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void saglDb() throws Exception {
		try {
			db.saglabat();
			izmaina = false;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/* DB METODES */
	public static String dbGetValutasSim() {
		return db.getDati().getValutasSim();
	}
	
	public static String[] dbGetKontaktiStr() {
		return db.getDati().getKontaktiStr();
	}
	
	public static ArrayList<Object> dbGetKontaktiObj() {
		ArrayList<Object> ret = new ArrayList<Object>();
		
		for (int i = 0; i < db.getDati().getKontakti().size(); i++) {
			ret.add(db.getDati().getKontakti().get(i));
		}
		
		return ret;
	}
	
	public static void dbAddKontakts(DatiemSaraksts nk) {
		db.getDati().addToKontakts((Kontakts)nk);
		izmaina = true;
	}
	
	public static void dbSetValutasSim(String valuta) {
		db.getDati().setValutasSim(valuta);
		izmaina = true;
	}
}
