package frontend;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import picerija.DatuVieniba;
import picerija.Kontakts;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JCheckBox;

public class PirkumuVeidotajaLogs extends JDialog {
	private static final long serialVersionUID = 1L;
	
	/* mainīgie */
	private static DatuVieniba pirkums = null;
	private static ArrayList<DatuVieniba> preces = null;
	
	/* ievade */
	/* kontakta inf. */
	private JTextField konVardTxt;
	private JTextField konAdrTxt;
	private JTextField konTalrTxt;
	
	public static DatuVieniba jaunsPirkums(DatuVieniba preview) {
		pirkums = null;
		preces = null;
		PirkumuVeidotajaLogs dialog = new PirkumuVeidotajaLogs(preview);
		
		dialog.setVisible(true);
		return pirkums;
	}

	public PirkumuVeidotajaLogs(DatuVieniba preview) { 
		/* prep */
		preces = new ArrayList<DatuVieniba>();
		
		/* ui */
		getContentPane().setLayout(new BorderLayout(0, 0));
		setMinimumSize(new Dimension(580, 360));
		
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
		
		JCheckBox piegadeUzAdrChkbx = new JCheckBox("Piegāde uz adr.");
		piegadeUzAdrChkbx.setAlignmentX(Component.CENTER_ALIGNMENT);
		optTxti.add(piegadeUzAdrChkbx);
		
		JCheckBox gatavaChkbx = new JCheckBox("Pabeigt piegādi");
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
		
		JLabel pkcpCenuLbl = new JLabel(" ");
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
		
		JLabel kopaCenaLbl = new JLabel(" ");
		kopaCenaLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		kopaCenaPanelis.add(kopaCenaLbl);
		
		/* preces */
		MeklejamsSaraksts msPreces = new MeklejamsSaraksts(null);
		msPreces.setElementi(preces);
		picuPanN.add(msPreces, BorderLayout.CENTER);
		
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
		
	}

}
