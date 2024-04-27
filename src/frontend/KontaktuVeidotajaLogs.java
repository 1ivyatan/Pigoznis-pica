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
import picerija.Kontakts;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class KontaktuVeidotajaLogs extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private static DatuVieniba kontakts = null;
	private JTextField nosTeksts;
	private JTextField adrTeksts;
	private JTextField numTeksts;
	private JTextArea piezTeksts;
	
	public static DatuVieniba jaunsKontakts(DatuVieniba preview) {
		kontakts = null;
		KontaktuVeidotajaLogs dialog = new KontaktuVeidotajaLogs(preview);
		
		dialog.setVisible(true);
		return kontakts;
	}
	
	public KontaktuVeidotajaLogs(DatuVieniba preview) {
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
		
		JPanel adrPanelis = new JPanel();
		infPanelis.add(adrPanelis);
		adrPanelis.add(new JLabel("Adrese"));
		
		adrTeksts = new JTextField();
		adrPanelis.add(adrTeksts);
		adrTeksts.setColumns(10);
		
		JPanel numPanelis = new JPanel();
		infPanelis.add(numPanelis);
		numPanelis.add(new JLabel("Tālruņa numurs"));
		
		numTeksts = new JTextField();
		numPanelis.add(numTeksts);
		numTeksts.setColumns(10);
		
		JPanel piezPanelis = new JPanel();
		infPanelis.add(piezPanelis);
		piezPanelis.setLayout(new BorderLayout(0, 0));
		piezPanelis.add(new JLabel(" Piezīme    "), BorderLayout.WEST);
		
		piezTeksts = new JTextArea();
		piezPanelis.add(new JScrollPane(piezTeksts, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
		
		if (preview != null) {
			setTitle("Rediģēt '" + preview.getNosaukums() + "'");
			nosTeksts.setText( ((Kontakts) preview).getVards() );
			adrTeksts.setText( ((Kontakts) preview).getAdrese() );
			numTeksts.setText( ((Kontakts) preview).getNumurs() );
			piezTeksts.setText( ((Kontakts) preview).getPiezime() );
		} else {
			setTitle("Jauns kontakts");
		}
		
		/* notikumi */
		saglPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nosTeksts.getText() == null || nosTeksts.getText().isEmpty() || nosTeksts.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(), "Jāievada vārds!", "!!!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (adrTeksts.getText() == null || adrTeksts.getText().isEmpty() || adrTeksts.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(), "Jāievada adrese!", "!!!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (numTeksts.getText() == null || numTeksts.getText().isEmpty() || numTeksts.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(), "Jāievada tālruņa numurs!", "!!!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (piezTeksts.getText() == null) {
					JOptionPane.showMessageDialog(getContentPane(), "Piezīme pēkšņi nevar būt 'null'!", "!!!", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					/* pārbaudīs tālruni ar regex!!!! */
					Pattern numurs = Pattern.compile("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", Pattern.CASE_INSENSITIVE);
					Matcher sakritiba = numurs.matcher(numTeksts.getText());
					
					if (!sakritiba.find()) {
						JOptionPane.showMessageDialog(getContentPane(), "Nederīgs tālruņa numurs!", "!!!", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					kontakts = new Kontakts(nosTeksts.getText(), adrTeksts.getText(), numTeksts.getText(), piezTeksts.getText());	
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
}
