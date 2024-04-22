package frontend;

import java.io.File;

import picerija.Datubaze;

public class Programma {
	/* datubāze */
	private static Datubaze db = null;
	private static boolean izmaina = false;
	private static boolean atverts = true;
	
	/* s/geteri */
	public static boolean getIzmaina() {
		return izmaina;
	}
	
	public static boolean getAtverts() {
		return atverts;
	}
	
	public static Datubaze getDb() {
		return db;
	}
		
	/* metodes */
	public static void aizvertDb() {
		atverts = false;
		db.aizvert();
		db = null;
	}
	
	public static void tuksotDb() {
		db = new Datubaze();
		atverts = true;
	}
	
	public static void atvertDb(File fails) throws Exception {
		try {
			db = new Datubaze(fails);
			atverts = true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void saglDb(File fails) throws Exception {
		try {
			if (fails != null) {
				db.setFails(fails);
				db.saglabat();
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
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
