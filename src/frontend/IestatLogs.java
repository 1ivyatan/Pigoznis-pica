package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import picerija.Resursi;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class IestatLogs extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public static void atvertIest() {
		IestatLogs dialog = new IestatLogs();
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	
	public IestatLogs() {
		setLocationRelativeTo(null);
		
		setTitle(
			(Programma.getDb() != null && Programma.getDb().getFails() != null) ? Programma.getDb().getFails().getName() : (Resursi.defDbNos)
			+ " iestatījumi"
		);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(320, 180));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		/* UI */
		contentPanel.setLayout(new BorderLayout(0, 0));
		JLabel statusTeksts = new JLabel(" ");
		
		JScrollPane scrollIest = new JScrollPane();
		contentPanel.add(scrollIest, BorderLayout.CENTER);
		
		JPanel iestPanel = new JPanel();
		scrollIest.setViewportView(iestPanel);
		iestPanel.setLayout(new BoxLayout(iestPanel, BoxLayout.Y_AXIS));
		
		JPanel valutaIest = new JPanel();
		iestPanel.add(valutaIest);
		
		JLabel valutaNos = new JLabel("Valūta");
		valutaIest.add(valutaNos);
		
		JTextField valutaVert = new JTextField();
		valutaVert.setColumns(10);
		valutaVert.setText(Programma.getDb().getDati().getValutasSim());
		valutaIest.add(valutaVert);
		
		JPanel status = new JPanel();
		contentPanel.add(status, BorderLayout.SOUTH);
		
		status.add(statusTeksts);
		
		/* notikumi */
		valutaVert.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				atjaunotValutu();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				atjaunotValutu();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				atjaunotValutu();
			}
			
			private void atjaunotValutu() {
				String in = valutaVert.getText();
				
				if (in.length() > 10) {
					statusTeksts.setText("Valūta: rakstzīmju skaitam jābut ne vairāk kā 10");
				} else if (in.length() < 1 || ( in.isBlank() )) {
					statusTeksts.setText("Valūta: jābūt vērtībai");
				} else {
					Programma.getDb().getDati().setValutasSim(in);;
					statusTeksts.setText(" ");
				}
			}
		});
	}

}
