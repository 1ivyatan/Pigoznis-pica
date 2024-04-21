package picerija;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

class Dati implements Serializable {
	int h = 10;
}

public class Datubaze {
	private Dati db;
	
	
	/* konstruktori */
	public Datubaze() {
		this.db = new Dati();
	}
	
	public Datubaze(File fails) throws Exception {
		try {
			FileInputStream fis = new FileInputStream(fails);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			if (fails.length() > 0) {
				this.db = (Dati)ois.readObject();
			} else {
				this.db = new Dati();
			}
			
			ois.close();
		} catch (EOFException e) {
			this.db = new Dati();
		} catch (StreamCorruptedException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Nederīga datubāze: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
}
