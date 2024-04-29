package frontend;

import java.io.File;

import picerija.Datubaze;

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
	
	/* SETERI */
	public static void setIzmaina(boolean sw) {
		izmaina = sw;
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
}
