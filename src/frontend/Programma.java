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
		izmaina = false;
	}
	
	public static void atvertDb(File fails) throws Exception {
		try {
			db = new Datubaze(fails);
			izmaina = false;
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
	public static ArrayList<ArrayList<DatiemSaraksts>> dbSarUIam;
	
	/* valūta */
	public static String dbGetValutasSim() {
		return db.getDati().getValutasSim();
	}
	
	public static void dbSetValutasSim(String valuta) {
		db.getDati().setValutasSim(valuta);
		izmaina = true;
	}
	
	/* kontakti */
	public static void dbAddKontakts(DatiemSaraksts nk) {
		db.getDati().addToKontakts((Kontakts)nk);
		izmaina = true;
	}
	
	public static void dbEditKontakts(int idx, DatiemSaraksts nk) {
		db.getDati().editKontaktsIdx(idx, (Kontakts)nk);
		izmaina = true;
	}
	
	public static void dbRemoveKontakts(int idx) {
		db.getDati().removeKontaktsIdx(idx);
		izmaina = true;
	}
}
