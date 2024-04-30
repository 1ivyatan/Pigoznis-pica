package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import picerija.DatuVieniba;
import picerija.Kontakts;
import picerija.Pica;
import picerija.Pirkums;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JCheckBox;

public class PirkumuVeidotajaLogs extends JDialog {
	private static final long serialVersionUID = 1L;
	
	/* mainīgie */
	private static DatuVieniba pirkums = null;
	private static ArrayList<DatuVieniba> preces = null;
	private double totCena = 0.0, precuCena = 0.0;
	
	/* ievade */
	/* kontakta inf. */
	private JTextField konVardTxt;
	private JTextField konAdrTxt;
	private JTextField konTalrTxt;
	
	/* ui */
	private JCheckBox piegadeUzAdrChkbx;
	private JLabel pkcpCenuLbl;
	private JLabel kopaCenaLbl;
	
	private void setCenasUi() {
		prepPrecuCena();
		prepTotCena();
		
		pkcpCenuLbl.setText(precuCena + " " + Programma.getDb().getDati().getValutasSim());
		kopaCenaLbl.setText(totCena + " " + Programma.getDb().getDati().getValutasSim());
	}
	
	/* vars */
	private void prepPrecuCena() {
		precuCena = 0;
		
		if (preces != null) {
			for (DatuVieniba i : preces) {
				precuCena += ((Pica) i).getCena();
			}
		}
		
		precuCena = (double) Math.round(precuCena * 100) / 100;
	}
	
	private void prepTotCena() {
		totCena =  ((piegadeUzAdrChkbx.isSelected()) ? Programma.getDb().getDati().getPiegadesCena() : 0);
		
		if (preces != null) {
			for (DatuVieniba i : preces) {
				totCena += ((Pica) i).getCena();
			}
		}
		
		precuCena = (double) Math.round(totCena * 100) / 100;
	}
	
	public static DatuVieniba jaunsPirkums(DatuVieniba preview) {
		pirkums = null;
		preces = null;
		
		PirkumuVeidotajaLogs dialog = new PirkumuVeidotajaLogs(preview);
		
		dialog.setVisible(true);
		return pirkums;
	}

	public PirkumuVeidotajaLogs(DatuVieniba preview) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
		/* prep */
		preces = new ArrayList<DatuVieniba>();
		
		/* ui */
		getContentPane().setLayout(new BorderLayout(0, 0));
		setMinimumSize(new Dimension(580, 360));
		setModal(true);
		
		JPanel poguPanelis = new JPanel();
		FlowLayout flowLayout = (FlowLayout) poguPanelis.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(poguPanelis, BorderLayout.SOUTH);
		
		JButton saglPoga = new JButton("Saglabāt");
		poguPanelis.add(saglPoga);
		
		JButton atceltPoga = new JButton("Atcelt");
		poguPanelis.add(atceltPoga);
		
		JPanel cekuPanelis = new JPanel();
		getContentPane().add(cekuPanelis, BorderLayout.CENTER);
		cekuPanelis.setLayout(new BorderLayout(0, 0));
		
		JPanel kontaktuPanelis = new JPanel();
		cekuPanelis.add(kontaktuPanelis, BorderLayout.WEST);
		kontaktuPanelis.setLayout(new BorderLayout(0, 0));
		
		Box konTxti = Box.createVerticalBox();
		kontaktuPanelis.add(konTxti, BorderLayout.NORTH);
		
		JPanel konTitle = new JPanel();
		konTxti.add(konTitle);
		
		JLabel konTitleLbl = new JLabel("Saņēmējs");
		konTitleLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		konTitle.add(konTitleLbl);
		
		JPanel konVardaPanelis = new JPanel();
		konTxti.add(konVardaPanelis);
		konVardaPanelis.add(new JLabel("Vārds"));
		
		konVardTxt = new JTextField();
		konVardaPanelis.add(konVardTxt);
		konVardTxt.setColumns(10);
		
		JPanel konAdrPanelis = new JPanel();
		konTxti.add(konAdrPanelis);
		konAdrPanelis.add(new JLabel("Adrese"));
		
		konAdrTxt = new JTextField();
		konAdrPanelis.add(konAdrTxt);
		konAdrTxt.setColumns(10);
		
		JPanel konTalPanelis = new JPanel();
		konTxti.add(konTalPanelis);
		konTalPanelis.add(new JLabel("Tālruņa nr."));
		
		konTalrTxt = new JTextField();
		konTalPanelis.add(konTalrTxt);
		konTalrTxt.setColumns(10);
		
		JPanel konPogas = new JPanel();
		konTxti.add(konPogas);
		
		JButton konPogaNoDb = new JButton("No datubāzes");
		konPogas.add(konPogaNoDb);
		
		Box optTxti = Box.createVerticalBox();
		kontaktuPanelis.add(optTxti, BorderLayout.SOUTH);
		
		JPanel optTxtPanelis = new JPanel();
		optTxti.add(optTxtPanelis);
		
		JLabel optTxtLbl = new JLabel("Papildiespējas");
		optTxtLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		optTxtPanelis.add(optTxtLbl);
		
		piegadeUzAdrChkbx = new JCheckBox("Piegāde uz adr.", true);
		piegadeUzAdrChkbx.setAlignmentX(Component.CENTER_ALIGNMENT);
		optTxti.add(piegadeUzAdrChkbx);
		
		JCheckBox gatavaChkbx = new JCheckBox("Pabeigt piegādi", false);
		gatavaChkbx.setFont(new Font("Tahoma", Font.BOLD, 11));
		gatavaChkbx.setAlignmentX(Component.CENTER_ALIGNMENT);
		optTxti.add(gatavaChkbx);
		
		JPanel picuPanelis = new JPanel();
		cekuPanelis.add(picuPanelis, BorderLayout.CENTER);
		picuPanelis.setLayout(new BorderLayout(0, 0));
		
		JPanel picuPanN = new JPanel();
		picuPanelis.add(picuPanN, BorderLayout.CENTER);
		picuPanN.setLayout(new BorderLayout(0, 0));
		
		JLabel picuPanTxt = new JLabel("Preces");
		picuPanTxt.setFont(new Font("Tahoma", Font.BOLD, 11));
		picuPanTxt.setHorizontalAlignment(SwingConstants.CENTER);
		picuPanN.add(picuPanTxt, BorderLayout.NORTH);
		
		JPanel picuPanPogas = new JPanel();
		picuPanN.add(picuPanPogas, BorderLayout.SOUTH);
		
		JButton ppJaunsPoga = new JButton("Izveidot");
		picuPanPogas.add(ppJaunsPoga);
		
		JButton ppRedPoga = new JButton("Rediģēt");
		picuPanPogas.add(ppRedPoga);
		
		JButton ppDzestPoga = new JButton("Dzēst");
		picuPanPogas.add(ppDzestPoga);
		
		JButton ppDbPoga = new JButton("No datubāzes");
		picuPanPogas.add(ppDbPoga);
		
		JPanel picuPanCena = new JPanel();
		picuPanelis.add(picuPanCena, BorderLayout.SOUTH);
		
		JLabel cenaLbl = new JLabel("Kopējā cena:");
		cenaLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		picuPanCena.add(cenaLbl);
		
		JPanel panel = new JPanel();
		picuPanCena.add(panel);
		
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		JPanel precuKopCenPanelis = new JPanel();
		verticalBox.add(precuKopCenPanelis);
		
		JLabel pkcpNosLbl = new JLabel("Preču kop. cena");
		pkcpNosLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		precuKopCenPanelis.add(pkcpNosLbl);
		
		pkcpCenuLbl = new JLabel(" ");
		precuKopCenPanelis.add(pkcpCenuLbl);
		
		JPanel piegCenaPanelis = new JPanel();
		verticalBox.add(piegCenaPanelis);
		
		JLabel pcpNosLbl = new JLabel("Piegādes cena");
		pcpNosLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		piegCenaPanelis.add(pcpNosLbl);
		
		JLabel pcpCenuLbl = new JLabel(Programma.getDb().getDati().getPiegadesCena() + " " + Programma.getDb().getDati().getValutasSim());
		piegCenaPanelis.add(pcpCenuLbl);
		
		JPanel kopaCenaPanelis = new JPanel();
		kopaCenaPanelis.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.add(kopaCenaPanelis);
		
		kopaCenaLbl = new JLabel(" ");
		kopaCenaLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		kopaCenaPanelis.add(kopaCenaLbl);
		
		/* preces */
		MeklejamsSaraksts msPreces = new MeklejamsSaraksts(null);
		picuPanN.add(msPreces, BorderLayout.CENTER);
		
		if (preview != null) {
			setTitle("Rediģēt '" + preview.getNosaukums() + "'");
			
			konVardTxt.setText( ((Kontakts) ((Pirkums)preview).getSanemejs()).getVards() );
			konAdrTxt.setText( ((Kontakts) ((Pirkums)preview).getSanemejs()).getAdrese() );
			konTalrTxt.setText( ((Kontakts) ((Pirkums)preview).getSanemejs()).getNumurs() );
			
			preces = ((Pirkums)preview).getPreces();
			
			piegadeUzAdrChkbx.setSelected( ((Pirkums)preview).getPiegadeUzAdresi() );
			gatavaChkbx.setSelected( ((Pirkums)preview).getGatavs() );
			
			piegCenaPanelis.setVisible(((Pirkums)preview).getPiegadeUzAdresi());
		} else {
			setTitle("Jauns pasūtījums");
		}

		msPreces.setElementi(preces);
		setCenasUi();
		
		/* notikumi */
		
		/* saņēmējs */
		konPogaNoDb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DatuVieniba sel = KontaktuVeidotajaLogs.noDatubazes();
				
				if (sel != null) {
					konVardTxt.setText(((Kontakts) sel).getVards());
					konAdrTxt.setText(((Kontakts) sel).getAdrese());
					konTalrTxt.setText(((Kontakts) sel).getNumurs());
				}
			}
		});
		
		/* preces */
		ppJaunsPoga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DatuVieniba pica = PicuVeidotajaLogs.jaunaPica(null);
				
				if (pica != null) {
					preces.add(pica);
					msPreces.setElementi();
					
					setCenasUi();
				}
			}
		});
		
		ppRedPoga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sel = msPreces.getSelIdx();
				if (sel != -1) {
					DatuVieniba js = PicuVeidotajaLogs.jaunaPica( msPreces.getSelectedDV().copy() );
					
					if (js != null) {
						preces.set(sel, js);
						msPreces.setElementi();
						
						setCenasUi();
					}
				}
			}
		});
		
		ppDzestPoga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sel = msPreces.getSelIdx();
				
				if (sel != -1 && JOptionPane.showConfirmDialog(getContentPane(), "Tiešām dzēst šo preci '" + msPreces.getSelectedDV().getNosaukums() + "'?", "Jautājums", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					preces.remove(sel);
					msPreces.setElementi();
					
					setCenasUi();
				}
			}
		});
		
		ppDbPoga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DatuVieniba sel = PicuVeidotajaLogs.noDatubazes();
				
				if (sel != null) {
					preces.add(sel);
					msPreces.setElementi();
					
					setCenasUi();
				}
			}
		});
		
		/* papildiest */
		piegadeUzAdrChkbx.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				piegCenaPanelis.setVisible(piegadeUzAdrChkbx.isSelected());
				setCenasUi();
			}
			
		});
		
		/* iziešana */
		atceltPoga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Tiešām aizvērsi?!", "Pagaidi!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Tiešām aizvērsi?!", "Pagaidi!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		
		saglPoga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* kontakts */
				if (konVardTxt.getText() == null || konVardTxt.getText().isEmpty() || konVardTxt.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(), "Saņēmējs: jāievada vārds!", "!!!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (konAdrTxt.getText() == null || konAdrTxt.getText().isEmpty() || konAdrTxt.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(), "Saņēmējs: jāievada adrese!", "!!!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (konTalrTxt.getText() == null || konTalrTxt.getText().isEmpty() || konTalrTxt.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(), "Saņēmējs: jāievada tālruņa numurs!", "!!!", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					Pattern numurs = Pattern.compile("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", Pattern.CASE_INSENSITIVE);
					Matcher sakritiba = numurs.matcher(konTalrTxt.getText());
					
					if (!sakritiba.find()) {
						JOptionPane.showMessageDialog(getContentPane(), "Saņēmējs: nederīgs tālruņa numurs!", "!!!", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					/* preces */
					if (preces != null && preces.isEmpty()) {
						JOptionPane.showMessageDialog(getContentPane(), "Preces: jābūt precēm!", "!!!", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if (preview != null) pirkums = new Pirkums(new Kontakts(konVardTxt.getText(), konAdrTxt.getText(), konTalrTxt.getText(), ""), preces, gatavaChkbx.isSelected(), piegadeUzAdrChkbx.isSelected(), ((Pirkums) preview).getNo(), Programma.getDb().getDati().getPiegadesCena());
					else pirkums = new Pirkums(new Kontakts(konVardTxt.getText(), konAdrTxt.getText(), konTalrTxt.getText(), ""), preces, gatavaChkbx.isSelected(), piegadeUzAdrChkbx.isSelected(), Programma.getDb().getDati().getPiegSk(), Programma.getDb().getDati().getPiegadesCena());
					dispose();					
				}
			}
		});
	}

}
