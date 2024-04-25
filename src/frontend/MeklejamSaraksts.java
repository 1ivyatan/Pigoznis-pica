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
	private ArrayList<Object> returnables = null;
	private DefaultListModel<String> lietuNos = null;
	private int filtretieIdxi[];
	
	private void resetFiltretieIdx() {
		for (int i = 0; i < filtretieIdxi.length; i++) filtretieIdxi[i] = i;
	}
	
	/* metodes */
	public void setEnabled(boolean sledze) {
		this.mekletajs.setEnabled(sledze);
		this.saraksts.setEnabled(sledze);
	}
	
	public void setElementi(ArrayList<Object> lietas) {
		this.returnables = lietas;

		this.lietuNos = new DefaultListModel<String>();
		this.filtretieIdxi = new int[lietas.size()];
		resetFiltretieIdx();
		
		for (Object i : lietas) {
			this.lietuNos.addElement(((DatiemSaraksts) i).nosaukums());
		}
		
		/* ui */
		this.saraksts.setModel(this.lietuNos);
	}
	
	public void setElementi() {
		this.saraksts.setModel(new DefaultListModel<String>());
		
		this.returnables = null;
		this.lietuNos = null;
	}
	
	public void atjaunotInc(DatiemSaraksts lieta) {
		System.out.print("jauns!!!");
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
							filtretieIdxi[filtraKursors] = i;
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
					uiIzvade.setText(((DatiemSaraksts) returnables.get(filtretieIdxi[idx])).kaVirkne());
				}
			}
		});
	}

}
