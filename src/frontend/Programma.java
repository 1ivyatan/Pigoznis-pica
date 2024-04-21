package frontend;

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
}
