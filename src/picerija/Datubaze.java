package picerija;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

class Dati implements Serializable {
	private static final long serialVersionUID = 1L;
	int h = 10;
}

public class Datubaze {
	private Dati db = null;
	private File fails = null;
	
	/* s/getteri */
	public void setFails(File fails) {
		this.fails = fails;
	}
	
	public File getFails() {
		return this.fails;
	}
	
	/* metodes */
	public void saglabat() throws Exception {
		if (fails == null) {
			throw new Exception("Nav fails kur saglabāt");
		} else {
			try {
				FileOutputStream fos = new FileOutputStream(this.fails);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this.db);
				oos.close();
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
	}
	
	public void aizvert() {
		this.db = null;
		this.fails = null;
	}
	
	/* konstruktori */
	public Datubaze() {
		this.db = new Dati();
	}
	
	public Datubaze(File fails) throws Exception {
		try {
			FileInputStream fis = new FileInputStream(fails);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.fails = fails;
			
			if (fails.length() > 0) {
				this.db = (Dati)ois.readObject();
			} else {
				this.db = new Dati();
			}
			
			ois.close();
		} catch (EOFException e) {
			this.db = new Dati();
		} catch (StreamCorruptedException | ClassNotFoundException e) {
			throw new Exception("Nederīga datubāze: " + e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
