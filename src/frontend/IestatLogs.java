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
	
	public IestatLogs() {
		setLocationRelativeTo(null);
		
		setTitle(
			(Programma.getDb() != null && Programma.getDb().getFails() != null) ? Programma.getDb().getFails().getName() : (Resursi.defDbNos)
			+ " iestatījumi"
		);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(340, 180));
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
		
		valutaIest.add(new JLabel("Valūta"));
		
		JTextField valutaVert = new JTextField();
		valutaVert.setColumns(10);
		valutaVert.setText(Programma.getDb().getDati().getValutasSim());
		valutaIest.add(valutaVert);
		
		JPanel mervIest = new JPanel();
		mervIest.add(new JLabel("Mērvienība"));
		
		JTextField mervVert = new JTextField();
		mervVert.setColumns(15);
		mervVert.setText(Programma.getDb().getDati().getMervienibasSim());
		mervIest.add(mervVert);
		
		iestPanel.add(mervIest);
		
		JPanel piegIest = new JPanel();
		piegIest.add(new JLabel("Piegādes cena"));
		
		JTextField piegVert = new JTextField();
		piegVert.setColumns(10);
		piegVert.setText(Programma.getDb().getDati().getPiegadesCena() + "");
		piegIest.add(piegVert);
		
		iestPanel.add(piegIest);
		
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
		
		mervVert.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				atjaunotMervienubu();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				atjaunotMervienubu();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				atjaunotMervienubu();
			}
			
			private void atjaunotMervienubu() {
				String in = mervVert.getText();
				
				if (in.length() > 10) {
					statusTeksts.setText("Mērvienība: rakstzīmju skaitam jābut ne vairāk kā 20");
				} else if (in.length() < 1 || ( in.isBlank() )) {
					statusTeksts.setText("Mērvienība: jābūt vērtībai");
				} else {
					Programma.getDb().getDati().setMervienibasSim(in);;
					statusTeksts.setText(" ");
				}
			}
		});
		
		piegVert.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				atjaunotPiegCenu();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				atjaunotPiegCenu();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				atjaunotPiegCenu();
			}
			
			private void atjaunotPiegCenu() {
				String in = piegVert.getText();
				
				if (in.length() < 1 || in.isBlank() || !in.matches("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$")) {
					statusTeksts.setText("Piegādes cena: tā nav cena");
				} else {
					try {
						double piegncena = Double.parseDouble(in);
						
						if (Double.isNaN(piegncena)) {
							throw new Exception();
						} else {
							Programma.getDb().getDati().setPiegadesCena((double) Math.round(piegncena * 100) / 100);
							statusTeksts.setText(" ");
						}
					} catch (Exception e) {
						statusTeksts.setText("Piegādes cena: tā nav cena");
					}
				}
			}
		});
	}

}
