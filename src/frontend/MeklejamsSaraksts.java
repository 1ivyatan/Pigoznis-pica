package frontend;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import picerija.DatuVieniba;

public class MeklejamsSaraksts extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<DatuVieniba> returnables = null;
	private ArrayList<Integer> returnableIdxi = null;
	
	/* UI */
	private JTextArea izvade = null;
	private JList<String> saraksts = null;
	private JTextField mekletajs = null;
	
	/* metodes */
	public void filtretElementus(String filtrs) {
		DefaultListModel<String> jaunsMod = new DefaultListModel<String>();
		this.saraksts.setSelectedIndex(-1);
		int c = 0;
		
		for (int i = 0; i < this.returnables.size(); i++) {
			if (this.returnables.get(i).getNosaukums().toLowerCase().contains(filtrs)) {
				jaunsMod.addElement(this.returnables.get(i).getNosaukums());
				this.returnableIdxi.set(c, i);
				c++; // XDDDDDDDDDDDDDDDDDDDd
			}
		}
		this.saraksts.setModel(jaunsMod);
	}
	
	/* set */
	public void setElementi(ArrayList<DatuVieniba> lietas) {
		this.mekletajs.setText(null);
		this.saraksts.setSelectedIndex(-1);

		if (lietas != null) {
			DefaultListModel<String> lietuNos = new DefaultListModel<String>();
			this.returnables = lietas;
			this.returnableIdxi = new ArrayList<Integer>();
			
			for (int i = 0; i < lietas.size(); i++) {
				lietuNos.addElement(lietas.get(i).getNosaukums());
				this.returnableIdxi.add(i);
			}
			
			this.saraksts.setModel(lietuNos);
		} else {
			returnables = null;
			returnableIdxi = null;
			this.saraksts.setModel(new DefaultListModel<String>());
		}
	}
	
	public void setElementi() {
		izvade.setText(null);
		this.saraksts.setSelectedIndex(-1);
		
		if (this.returnables != null) {
			DefaultListModel<String> lietuNos = new DefaultListModel<String>();
			this.returnableIdxi = new ArrayList<Integer>();
			
			int c = 0;
			for (DatuVieniba i : this.returnables) {
				if (i != null) {
					lietuNos.addElement(this.returnables.get(c).getNosaukums());
					this.returnableIdxi.add(c);		
					c++;
				}
			}
			
			this.saraksts.setModel(lietuNos);
			
			if (!mekletajs.getText().isBlank()) {
				 filtretElementus(mekletajs.getText().toLowerCase());
			}
		} else {
			returnables = null;
			returnableIdxi = null;
			this.saraksts.setModel(new DefaultListModel<String>());
		}
	}
	
	public void setEnabled(boolean enabled) {
		this.saraksts.setEnabled(enabled);
		this.mekletajs.setEnabled(enabled);
	}
	
	/* get */
	public int getSelIdx() {
		if (saraksts != null)
			return this.returnableIdxi.get(this.getRealSelIdx());
		else return -1;
	}
	
	public int getRealSelIdx() {
		if (saraksts != null)
			return this.saraksts.getSelectedIndex();
		else return -1;
	}
	
	public DatuVieniba getSelectedDV() {
		return this.returnables.get(returnableIdxi.get(this.getRealSelIdx()));
	}
	
	/* kons */
	public MeklejamsSaraksts(JTextArea izvade) {
		setLayout(new BorderLayout(0, 0));
		
		this.saraksts = new JList<String>();
		this.mekletajs = new JTextField();
		this.izvade = izvade;
		
		add(this.saraksts, BorderLayout.CENTER);
		add(this.mekletajs, BorderLayout.NORTH);
		
		/* notikumi */
		this.mekletajs.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {mekletSaraksta(); }

			@Override
			public void removeUpdate(DocumentEvent e) { mekletSaraksta(); }

			@Override
			public void changedUpdate(DocumentEvent e) { mekletSaraksta(); }
			
			private void mekletSaraksta() {
				String ievade = mekletajs.getText().toLowerCase();

				if (returnables != null || !returnables.isEmpty()) filtretElementus(ievade);
			}
		});
		
		this.saraksts.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && saraksts.getSelectedValue() != null) {
					int idx = saraksts.getSelectedIndex();
					izvade.setText(returnables.get(returnableIdxi.get(idx)).getInfo());
				}
		}});
	}

}
