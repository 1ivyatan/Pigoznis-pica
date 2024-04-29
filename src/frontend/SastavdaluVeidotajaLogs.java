package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import picerija.DatuVieniba;
import picerija.PicasSastavdala;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

public class SastavdaluVeidotajaLogs extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private static DatuVieniba sastavdala = null;
	private JTextField nosTeksts;
	private JTextField cenuTeksts;
	private JTextArea piezTeksts;
	
	public static DatuVieniba noDatubazes() {
		sastavdala = null;
		SastavdaluVeidotajaLogs dialog = new SastavdaluVeidotajaLogs();
		dialog.setVisible(true);
		return sastavdala;
	}
	
	public static DatuVieniba jaunaSastavdala(DatuVieniba preview) {
		sastavdala = null;
		SastavdaluVeidotajaLogs dialog = new SastavdaluVeidotajaLogs(preview);
		
		dialog.setVisible(true);
		return sastavdala;
	}
	
	public SastavdaluVeidotajaLogs() {
		setModal(true);
		setMinimumSize(new Dimension(400, 250));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		/* UI */
		setTitle("No datubāzes...");
		JTextArea priekshsakt = new JTextArea();
		getContentPane().add(new JScrollPane(priekshsakt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		
		MeklejamsSaraksts sar = new MeklejamsSaraksts(priekshsakt);
		JPanel izveletajs = new JPanel();
		izveletajs.setLayout(new BorderLayout(0, 0));
		izveletajs.add(sar);
		getContentPane().add(izveletajs, BorderLayout.WEST);
		
		JPanel pogupanelis = new JPanel();
		FlowLayout fl_pogupanelis = (FlowLayout) pogupanelis.getLayout();
		fl_pogupanelis.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(pogupanelis, BorderLayout.SOUTH);
		
		JButton saglPoga = new JButton("Pievienot");
		pogupanelis.add(saglPoga);
		
		JButton atceltPoga = new JButton("Atcelt");
		pogupanelis.add(atceltPoga);
		
		sar.setElementi(Programma.getDb().getDati().getSastavdalas());
		
		/* notikumi */
		saglPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatuVieniba sel = sar.getSelectedDV();
				
				if (sel != null) {
					sastavdala = sel;
					dispose();
				}
			}
		});
		
		atceltPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	public SastavdaluVeidotajaLogs(DatuVieniba preview) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setMinimumSize(new Dimension(250, 250));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel poguPanelis = new JPanel();
		FlowLayout flowLayout = (FlowLayout) poguPanelis.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(poguPanelis, BorderLayout.SOUTH);
		
		JButton saglPoga = new JButton("Saglabāt");
		poguPanelis.add(saglPoga);
		
		JButton atceltPoga = new JButton("Atcelt");
		poguPanelis.add(atceltPoga);
		
		JPanel infPanelis = new JPanel();
		getContentPane().add(infPanelis, BorderLayout.CENTER);
		infPanelis.setLayout(new BoxLayout(infPanelis, BoxLayout.Y_AXIS));
		
		JPanel nosPanelis = new JPanel();
		infPanelis.add(nosPanelis);
		nosPanelis.add(new JLabel("Nosaukums"));
		
		nosTeksts = new JTextField();
		nosPanelis.add(nosTeksts);
		nosTeksts.setColumns(10);
		
		JPanel cenuPanelis = new JPanel();
		infPanelis.add(cenuPanelis);
		cenuPanelis.add(new JLabel("Cena"));
		
		cenuTeksts = new JTextField();
		cenuPanelis.add(cenuTeksts);
		cenuTeksts.setColumns(10);
		
		cenuPanelis.add(new JLabel(Programma.getDb().getDati().getValutasSim()));
		
		JPanel piezPanelis = new JPanel();
		infPanelis.add(piezPanelis);
		piezPanelis.setLayout(new BorderLayout(0, 0));
		piezPanelis.add(new JLabel(" Piezīme    "), BorderLayout.WEST);
		
		piezTeksts = new JTextArea();
		piezPanelis.add(new JScrollPane(piezTeksts, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
		
		if (preview != null) {
			setTitle("Rediģēt '" + preview.getNosaukums() + "'");
			nosTeksts.setText( ((PicasSastavdala) preview).getVards() );
			cenuTeksts.setText( ((PicasSastavdala) preview).getCena() + "" );
			piezTeksts.setText( ((PicasSastavdala) preview).getPiezime() );
		} else {
			setTitle("Jauns sastavdala");
		}
		
		/* notikumi */
		saglPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nosTeksts.getText() == null || nosTeksts.getText().isEmpty() || nosTeksts.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(), "Jāievada vārds!", "!!!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (cenuTeksts.getText() == null || cenuTeksts.getText().isEmpty() || cenuTeksts.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(), "Jāievada cena!", "!!!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (piezTeksts.getText() == null) {
					JOptionPane.showMessageDialog(getContentPane(), "Piezīme pēkšņi nevar būt 'null'!", "!!!", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					/* pārbaudam cenu */
					try {
						Pattern numurs = Pattern.compile("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$", Pattern.CASE_INSENSITIVE);
						Matcher sakritiba = numurs.matcher(cenuTeksts.getText());
						
						if (!sakritiba.find()) {
							JOptionPane.showMessageDialog(getContentPane(), "Nederīga cena", "!!!", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						double nc = Double.parseDouble(cenuTeksts.getText());
						if (Double.isNaN(nc)) {
							JOptionPane.showMessageDialog(getContentPane(), "Nederīga cena", "!!!", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						sastavdala = new PicasSastavdala(nosTeksts.getText(), piezTeksts.getText(), (double) Math.round(nc * 100) / 100);
						dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Tā nav cena!", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		atceltPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
