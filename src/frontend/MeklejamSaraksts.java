package frontend;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class MeklejamSaraksts extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/* elementi */
	private JTextField mekletajs;
	private JList saraksts;

	public void setEnabled(boolean sledze) {
		this.mekletajs.setEnabled(sledze);
		this.saraksts.setEnabled(sledze);
	}
	
	/**
	 * Create the panel.
	 */
	public MeklejamSaraksts() {
		setLayout(new BorderLayout(0, 0));
		
		this.mekletajs = new JTextField();
		add(this.mekletajs, BorderLayout.NORTH);
		this.mekletajs.setColumns(10);
		
		this.saraksts = new JList();
		this.saraksts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(this.saraksts, BorderLayout.CENTER);

	}

}
