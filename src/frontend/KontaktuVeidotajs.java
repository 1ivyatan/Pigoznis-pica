package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import picerija.Kontakts;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class KontaktuVeidotajs extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private static Kontakts kontakts = null;
	
	public static Kontakts jaunsKontakts(Kontakts preview) {
		KontaktuVeidotajs dialog = new KontaktuVeidotajs(preview);
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		
		return kontakts;
	}

	public KontaktuVeidotajs(Kontakts prev) {
		JTextField vardsIevade,
		adreseIevade,
		talrunaIevade;
		JTextArea piezimeIevade;
		
		setTitle( (prev == null) ? "Jauns kontakts" : "Rediģēt kontaktu" );
		setMinimumSize(new Dimension(225, 250));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JPanel vardaPanelis = new JPanel();
		contentPanel.add(vardaPanelis);
		vardaPanelis.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		vardaPanelis.add(new JLabel("Vārds"));
		
		vardsIevade = new JTextField();
		vardaPanelis.add(vardsIevade);
		vardsIevade.setColumns(10);
		
		JPanel adresesPanelis = new JPanel();
		contentPanel.add(adresesPanelis);
		adresesPanelis.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		adresesPanelis.add(new JLabel("Adrese"));
		
		adreseIevade = new JTextField();
		adresesPanelis.add(adreseIevade);
		adreseIevade.setColumns(10);
		
		JPanel talrunaPanelis = new JPanel();
		contentPanel.add(talrunaPanelis);
		talrunaPanelis.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		talrunaPanelis.add(new JLabel("Tālruņa nr."));
		
		talrunaIevade = new JTextField();
		talrunaPanelis.add(talrunaIevade);
		talrunaIevade.setColumns(10);
		
		JPanel piezimjuPanelis = new JPanel();
		contentPanel.add(piezimjuPanelis);
		piezimjuPanelis.setLayout(new BorderLayout(0, 0));
		
		piezimjuPanelis.add(new JLabel("Piezīme  "), BorderLayout.WEST);
		
		piezimeIevade = new JTextArea();
		JScrollPane skirstams = new JScrollPane (piezimeIevade, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		piezimjuPanelis.add(skirstams, BorderLayout.CENTER);
		
		if (prev != null) {
			vardsIevade.setText(prev.getVards());
			adreseIevade.setText(prev.getAdrese());
			talrunaIevade.setText(prev.getTalrunis());
			piezimeIevade.setText(prev.getPiezime());
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton okButton = new JButton("Saglabāt");
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			
			JButton cancelButton = new JButton("Atcelt");
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			
			/* notikumi */
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/* Tukšums? */
					if (
						( vardsIevade.getText() == null || vardsIevade.getText().isEmpty() || vardsIevade.getText().isBlank())
					) {
						JOptionPane.showMessageDialog(contentPanel, "Jāievada vārds!", "!!!", JOptionPane.WARNING_MESSAGE);
						return;
					} else if ( adreseIevade.getText() == null || adreseIevade.getText().isEmpty() || adreseIevade.getText().isBlank()) {
						JOptionPane.showMessageDialog(contentPanel, "Jāievada adrese!", "!!!", JOptionPane.WARNING_MESSAGE);
						return;
					} else if ( talrunaIevade.getText() == null || talrunaIevade.getText().isEmpty() || talrunaIevade.getText().isBlank()) {
						JOptionPane.showMessageDialog(contentPanel, "Jāievada tālruņa numurs!", "!!!", JOptionPane.WARNING_MESSAGE);
						return;
					} else if ( piezimeIevade.getText() == null ) {
						JOptionPane.showMessageDialog(contentPanel, "Piezīme pēkšņi nevar būt 'null'!", "!!!", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					/* pārbaudīs tālruni ar regex!!!! */
					Pattern numurs = Pattern.compile("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", Pattern.CASE_INSENSITIVE);
					Matcher sakritiba = numurs.matcher(talrunaIevade.getText());
					
					if (!sakritiba.find()) {
						JOptionPane.showMessageDialog(contentPanel, "Nederīgs tālruņa numurs!", "!!!", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					kontakts = new Kontakts(vardsIevade.getText(), adreseIevade.getText(), talrunaIevade.getText(), piezimeIevade.getText());
					dispose();
				}
			});
			
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}
	}

}
