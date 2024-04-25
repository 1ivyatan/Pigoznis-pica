package frontend;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import picerija.DatiemSaraksts;

public class MeklejamSaraksts extends JPanel {
	private static final long serialVersionUID = 1L;
	
	/* elementi */
	private JTextField mekletajs;
	private JList<String> saraksts;
	
	private JTextArea uiIzvade = null;
	
	/* saraksti */
	private ArrayList<DatiemSaraksts> returnables = null;
	private DefaultListModel<String> lietuNos = null;
	private ArrayList<Integer> filtretieIdxi = null;
	
	private void resetFiltretieIdx() {
		for (int i = 0; i < filtretieIdxi.size(); i++) filtretieIdxi.set(i, i);
	}
	
	private void resetFiltretieIdx(int n) {
		for (int i = 0; i < n; i++) filtretieIdxi.add(i, i);
	}
	
	/* metodes */
	public void setEnabled(boolean sledze) {
		this.mekletajs.setEnabled(sledze);
		this.saraksts.setEnabled(sledze);
	}
	
	public void setElementi(ArrayList<DatiemSaraksts> lietas) {
		this.returnables = lietas;

		this.lietuNos = new DefaultListModel<String>();
		this.filtretieIdxi = new ArrayList<Integer>();
		resetFiltretieIdx(lietas.size());
		
		for (DatiemSaraksts i : lietas) {
			this.lietuNos.addElement(i.nosaukums());
		}
		
		/* ui */
		this.saraksts.setModel(this.lietuNos);
	}
	
	public void setElementi() {
		this.saraksts.setModel(new DefaultListModel<String>());
		
		this.returnables = null;
		this.lietuNos = null;
	}
	
	public void atjaunotElementu(int idx, DatiemSaraksts jaunsElements) {
		this.returnables.set(idx, jaunsElements);
		this.lietuNos.set(idx, jaunsElements.nosaukums());
		this.atjaunotOut();
	}
	
	public void pievienotSaraksta(DatiemSaraksts lieta) {
		this.returnables.add(lieta);
		this.lietuNos.addElement(lieta.nosaukums());
		
		this.filtretieIdxi.add(this.filtretieIdxi.size());
	}
	
	public void nonemElementu(int idx) {
		this.returnables.remove(idx);
		this.lietuNos.remove(idx);
		
		for (int i = idx; i < filtretieIdxi.size(); i++) {
			filtretieIdxi.set(i, filtretieIdxi.get(i) - 1);
		}
		this.filtretieIdxi.remove(idx);
		
		this.atjaunotOut();
	}
	
	public int izmers() {
		return returnables.size();
	}
	
	public Object getSelObj() {
		if (returnables.size() < 1) return null;
		
		int idx = saraksts.getSelectedIndex();
		if (idx == -1) return null;
		
		return ( returnables.get(filtretieIdxi.get(idx)));
	}
	
	public int getSelIdx() {
		if (returnables.size() < 1) return -1;
		
		int idx = saraksts.getSelectedIndex();
		return filtretieIdxi.get(idx);
	}
	
	public MeklejamSaraksts(JTextArea izvade) {
		setLayout(new BorderLayout(0, 0));
		
		this.uiIzvade = izvade;
		
		this.mekletajs = new JTextField();
		add(this.mekletajs, BorderLayout.NORTH);
		this.mekletajs.setColumns(10);
		
		this.saraksts = new JList<String>();
		this.saraksts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(this.saraksts, BorderLayout.CENTER);
		
		/* notikumi */
		this.mekletajs.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				mekletSaraksta();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				mekletSaraksta();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				mekletSaraksta();
			}
			
			private void mekletSaraksta() {
				String ievade = mekletajs.getText().toLowerCase();
				int filtraKursors = 0;
				
				if (ievade.isEmpty()) {
					resetFiltretieIdx();
					saraksts.setModel(lietuNos);
				} else {
					DefaultListModel<String> rezultati = new DefaultListModel<String>();
					
					for (int i = 0; i < lietuNos.size(); i++) {
						if (lietuNos.get(i).toLowerCase().contains(ievade)) {
							rezultati.addElement(lietuNos.get(i));
							filtretieIdxi.set(filtraKursors, i);
							filtraKursors++;
						}
					}

					saraksts.setModel(rezultati);
				}
			}
		});
		
		this.saraksts.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && saraksts.getSelectedValue() != null) {
					int idx = saraksts.getSelectedIndex();
					uiIzvade.setText(( returnables.get(filtretieIdxi.get(idx))).kaVirkne());
				}
			}
		});
	}
	
	public void atjaunotOut() {
		if (this.saraksts.getSelectedValue() != null) {
			int idx = this.saraksts.getSelectedIndex();
			this.uiIzvade.setText(( returnables.get(filtretieIdxi.get(idx))).kaVirkne());
		} else {
			this.uiIzvade.setText(null);
		}
	}

}
