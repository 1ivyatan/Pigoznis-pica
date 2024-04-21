package frontend;

import java.io.File;

import picerija.Datubaze;

public class Programma {
	/* datubāze */
	private static Datubaze db;
	private static boolean izmaina = false;
	
	/* s/geteri */
	public static boolean getIzmaina() {
		return izmaina;
	}
	
	/* metodes */
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
}
