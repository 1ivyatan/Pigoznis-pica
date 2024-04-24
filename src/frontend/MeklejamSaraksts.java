package frontend;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import picerija.Kontakts;

public class MeklejamSaraksts extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/* elementi */
	private JTextField mekletajs;
	private JList saraksts;
	
	/* saraksti */
	private static ArrayList<Object> returnables = null;
	private static DefaultListModel lietuNos = null;

	public void setEnabled(boolean sledze) {
		this.mekletajs.setEnabled(sledze);
		this.saraksts.setEnabled(sledze);
	}
	
	public void setElementi(ArrayList<Object> lietas, String[] lietuNosaukumi) {
		//if (lietas.isEmpty() || lietuNosaukumi.length < 1) {
		//}
		this.returnables = lietas;
		
		this.lietuNos = new DefaultListModel();
		for (String i : lietuNosaukumi) {
			this.lietuNos.addElement(i);
		}
		
		/* ui */
		this.saraksts.setModel(this.lietuNos);
		this.saraksts.setSelectedIndex(0);
	}
	
	public void setElementi() {
		this.saraksts.setModel(new DefaultListModel());
		
		this.returnables = null;
		this.lietuNos = null;
	}
	
	public MeklejamSaraksts() {
		setLayout(new BorderLayout(0, 0));
		
		this.mekletajs = new JTextField();
		add(this.mekletajs, BorderLayout.NORTH);
		this.mekletajs.setColumns(10);
		
		this.saraksts = new JList();
		this.saraksts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(this.saraksts, BorderLayout.CENTER);

		/* notikumi */
		this.saraksts.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println("yo!!!!!!!!!");
				//System.out.println("y);

			}
			
		});
	}

}
