package picerija;

import java.io.Serializable;

public abstract class DatuVieniba implements Serializable {
	private static final long serialVersionUID = 1L;
	public abstract String getNosaukums();
	public abstract String getInfo();
}
