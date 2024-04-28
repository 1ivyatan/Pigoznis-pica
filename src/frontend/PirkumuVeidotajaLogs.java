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
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JCheckBox;

public class PirkumuVeidotajaLogs extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private static DatuVieniba pirkums = null;
	private JTextField konVardTxt;
	private JTextField konAdrTxt;
	private JTextField konTalrTxt;
	
	public static DatuVieniba jaunsPirkums(DatuVieniba preview) {
		pirkums = null;
		PirkumuVeidotajaLogs dialog = new PirkumuVeidotajaLogs(preview);
		
		dialog.setVisible(true);
		return pirkums;
	}

	public PirkumuVeidotajaLogs(DatuVieniba preview) { 
		getContentPane().setLayout(new BorderLayout(0, 0));
		setMinimumSize(new Dimension(480, 360));
		
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
		
		JPanel kontaktaLogs = new JPanel();
		cekuPanelis.add(kontaktaLogs, BorderLayout.WEST);
		kontaktaLogs.setLayout(new BorderLayout(0, 0));
		
		Box konTxti = Box.createVerticalBox();
		kontaktaLogs.add(konTxti, BorderLayout.NORTH);
		
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
		kontaktaLogs.add(optTxti, BorderLayout.SOUTH);
		
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
		
	}

}
