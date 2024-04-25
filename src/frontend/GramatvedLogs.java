package frontend;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JFileChooser;

import picerija.Kontakts;
import picerija.Resursi;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class GramatvedLogs extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/* logs */
	private static JPanel logaPanelis;
	private static GramatvedLogs ramis;
	
	/* izvēle */
	private static JFileChooser izv;
	private static FileNameExtensionFilter extFiltrs;
	
	/* saraksti */
	
	/* statuss */
	private static JLabel statusaTeksts;
	
	/* ui slēdze */
	private static ArrayList<Object> atspejojamieUi;
	
	private static void setLogaNos() {
		String nos;

		if (Programma.getDb() != null && Programma.getDb().getFails() != null) {
			nos =  Programma.getDb().getFails().getName() + " - ";
		} else if (Programma.getDb() == null) {
			nos = "";
		} else {
			nos = Resursi.defDbNos + " - ";
		}

		ramis.setTitle(nos + "Picērija");
	}
	
	/* db fails */
	private static void jaunsUi(boolean init) {
		Programma.tuksotDb();
		sledzeUi(true);
		setLogaNos();
		
		if (init) statusaTeksts.setText("Sveicināti!");
		else statusaTeksts.setText("Izveidota datubāze");
	}
	
	private static void atvertUi() {
		int rez = izv.showOpenDialog(null);
		
		if (rez == JFileChooser.APPROVE_OPTION) {
			try {
				Programma.atvertDb(izv.getSelectedFile());
				statusaTeksts.setText("Atvēra datubāzi " + izv.getSelectedFile().getAbsolutePath());
				setLogaNos();
				sledzeUi(true);
			} catch (Exception e) {
				statusaTeksts.setText("Nevarēja atvērt datubāzi " + izv.getSelectedFile().getAbsolutePath());
				JOptionPane.showMessageDialog(logaPanelis, e.getMessage(), "Nevarēja atvērt datubāzi", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private static void sagalbatUi(boolean iznicinatFailu) {
		if (iznicinatFailu || Programma.getDb().getFails() == null) {
			int rez = izv.showSaveDialog(null);
			if (rez == JFileChooser.APPROVE_OPTION) {
				try {
					if (
						izv.getFileFilter().equals(extFiltrs) && !izv.getSelectedFile().getAbsolutePath().endsWith("." + Resursi.failaExt)
					) {
						String vieta = izv.getSelectedFile().getAbsolutePath() + "." + Resursi.failaExt;
						Programma.saglDb(vieta);
						statusaTeksts.setText("Saglabāja datubāzi " + vieta);
					} else {
						Programma.saglDb(izv.getSelectedFile());
						statusaTeksts.setText("Saglabāja datubāzi " + izv.getSelectedFile().getAbsolutePath());
					}
					setLogaNos();
				} catch (Exception e) {
					statusaTeksts.setText("Nevarēja saglabāt datubāzi " + izv.getSelectedFile().getAbsolutePath());
					JOptionPane.showMessageDialog(logaPanelis, e.getMessage(), "Nevarēja saglabāt datubāzi", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			try {
				Programma.saglDb();
				statusaTeksts.setText("Saglabāja datubāzi " + Programma.getDb().getFails().getAbsolutePath());
			} catch (Exception e) {
				statusaTeksts.setText("Nevarēja saglabāt datubāzi " + Programma.getDb().getFails().getAbsolutePath());
				JOptionPane.showMessageDialog(logaPanelis, e.getMessage(), "Nevarēja saglabāt datubāzi", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private static void aizvertUi() {
		statusaTeksts.setText("Aizvērta datubāze" + ( (Programma.getDb().getFails() != null) ? " " + Programma.getDb().getFails().getAbsolutePath() : ""));
		Programma.aizvertDb();
		sledzeUi(false);
		setLogaNos();
	}
	
	private static void izietUi() {
		if (Programma.getDb() != null) {
			sledzeUi(false);
			Programma.aizvertDb();
		}
		
		ramis.dispose();
		System.exit(0);
	}
	
	private static void sledzeUi(boolean sledze) {
		for (Object i : atspejojamieUi) {
			((Component)i).setEnabled(sledze);
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ramis = new GramatvedLogs();

					/* db */
					jaunsUi(true);
					
					ramis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GramatvedLogs() {
		/* ------   Db sagatavošana   ----------- */
		extFiltrs = new FileNameExtensionFilter("Picērijas datubāze (." + Resursi.failaExt + ")", Resursi.failaExt);

		izv = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		izv.setFileSelectionMode(JFileChooser.FILES_ONLY);
		izv.addChoosableFileFilter(extFiltrs);
		izv.setFileFilter(extFiltrs);
		
		/* --------- logs -------------- */
		atspejojamieUi = new ArrayList<Object>();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setMinimumSize(new Dimension(480, 360));
		setSize(new Dimension(630, 440));
		
		/* topnav */
		JMenuBar topNav = new JMenuBar();
		setJMenuBar(topNav);
		
		JMenu topNavDatne = new JMenu("Datne");
		topNav.add(topNavDatne);
		
		JMenuItem topNavDatneJauns = new JMenuItem("Jauns");
		topNavDatne.add(topNavDatneJauns);
		
		JMenuItem topNavDatneAtvert = new JMenuItem("Atvērt");
		topNavDatne.add(topNavDatneAtvert);
		
		JMenuItem topNavDatneSagl = new JMenuItem("Saglabāt");
		topNavDatne.add(topNavDatneSagl);
		atspejojamieUi.add(topNavDatneSagl);
		
		JMenuItem topNavDatneSaglKa = new JMenuItem("Saglabāt kā");
		topNavDatne.add(topNavDatneSaglKa);
		atspejojamieUi.add(topNavDatneSaglKa);
		
		JMenuItem topNavDatneAizv = new JMenuItem("Aizvērt");
		topNavDatne.add(topNavDatneAizv);
		atspejojamieUi.add(topNavDatneAizv);
		
		JSeparator separator = new JSeparator();
		topNavDatne.add(separator);
		
		JMenuItem topNavIziet = new JMenuItem("Iziet");
		topNavDatne.add(topNavIziet);
		
		JMenu topNavRediget = new JMenu("Rediģēt");
		topNav.add(topNavRediget);
		
		JMenuItem topNavRedigetIest = new JMenuItem("Datubāzes iestatījumi");
		topNavRediget.add(topNavRedigetIest);
		atspejojamieUi.add(topNavRedigetIest);
		
		JMenu topNavPal = new JMenu("Palīdzība");
		topNav.add(topNavPal);
		
		JMenuItem topNavPalPar = new JMenuItem("Par");
		topNavPal.add(topNavPalPar);
		
		/* panelis */
		logaPanelis = new JPanel();
		logaPanelis.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(logaPanelis);
		logaPanelis.setLayout(new BorderLayout(0, 0));
		
		JPanel statusaPanelis = new JPanel();
		statusaPanelis.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) statusaPanelis.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		logaPanelis.add(statusaPanelis, BorderLayout.SOUTH);
		
		statusaTeksts = new JLabel("Sviecināti");
		statusaPanelis.add(statusaTeksts);
		
		/* izvēlne */
		JPanel izvPanelis = new JPanel();
		izvPanelis.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		logaPanelis.add(izvPanelis, BorderLayout.WEST);
		izvPanelis.setLayout(new BorderLayout(0, 0));
		
		JPanel izvPogas = new JPanel();
		izvPanelis.add(izvPogas, BorderLayout.SOUTH);
		izvPogas.setLayout(new BoxLayout(izvPogas, BoxLayout.Y_AXIS));
		
		JPanel izvIzvRedPanelis = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) izvIzvRedPanelis.getLayout();
		flowLayout_1.setHgap(25);
		izvPogas.add(izvIzvRedPanelis);
		
		JButton izvIzvPoga = new JButton("Izveidot");
		izvIzvRedPanelis.add(izvIzvPoga);
		atspejojamieUi.add(izvIzvPoga);
		
		JButton izvRedigetPoga = new JButton("Rediģēt");
		izvIzvRedPanelis.add(izvRedigetPoga);
		atspejojamieUi.add(izvRedigetPoga);
		
		JPanel izvDzestInfoPanelis = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) izvDzestInfoPanelis.getLayout();
		flowLayout_2.setHgap(15);
		izvPogas.add(izvDzestInfoPanelis);
		
		JButton izvDzestPoga = new JButton("Dzēst");
		izvDzestInfoPanelis.add(izvDzestPoga);
		atspejojamieUi.add(izvDzestPoga);
		
		JButton izvInfoPoga = new JButton("Informācija");
		izvDzestInfoPanelis.add(izvInfoPoga);
		atspejojamieUi.add(izvInfoPoga);
		
		JPanel cilnIzvelne = new JPanel();
		izvPanelis.add(cilnIzvelne, BorderLayout.CENTER);
		cilnIzvelne.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane cilnes = new JTabbedPane(JTabbedPane.TOP);
		cilnes.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		cilnIzvelne.add(cilnes);
		atspejojamieUi.add(cilnes);
		
		JPanel pirkumuCilne = new JPanel();
		cilnes.addTab("Pasūtījumi", null, pirkumuCilne, null);
		pirkumuCilne.setLayout(new CardLayout(0, 0));
		
		JTabbedPane pirkumuCilnes = new JTabbedPane(JTabbedPane.BOTTOM);
		pirkumuCilnes.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		pirkumuCilne.add(pirkumuCilnes, "name_17432651049000");
		
		JPanel pasutCilne = new JPanel();
		pirkumuCilnes.addTab("Gaidošie", null, pasutCilne, null);
		pasutCilne.setLayout(new CardLayout(0, 0));
		
		JPanel pasutVestCilne = new JPanel();
		pirkumuCilnes.addTab("Vēsture", null, pasutVestCilne, null);
		pasutVestCilne.setLayout(new CardLayout(0, 0));
		
		JPanel kontaktuCilne = new JPanel();
		cilnes.addTab("Kontakti", null, kontaktuCilne, null);
		kontaktuCilne.setLayout(new CardLayout(0, 0));
		
		JPanel picuCilne = new JPanel();
		cilnes.addTab("Picas", null, picuCilne, null);
		picuCilne.setLayout(new CardLayout(0, 0));
		
		JTabbedPane picuCilnes = new JTabbedPane(JTabbedPane.BOTTOM);
		picuCilne.add(picuCilnes, "name_17765351404700");
		
		JPanel picuRecepCilne = new JPanel();
		picuCilnes.addTab("Receptes", null, picuRecepCilne, null);
		picuRecepCilne.setLayout(new CardLayout(0, 0));
		
		JPanel picuSastavCilne = new JPanel();
		picuCilnes.addTab("Sastāvdaļas", null, picuSastavCilne, null);
		picuSastavCilne.setLayout(new CardLayout(0, 0));
		
		/* priekšskatījums */
		JPanel prevPanelis = new JPanel();
		prevPanelis.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		logaPanelis.add(prevPanelis, BorderLayout.CENTER);
		prevPanelis.setLayout(new CardLayout(0, 0));
		
		JTextArea prevTeksts = new JTextArea();
		prevTeksts.setTabSize(4);
		prevTeksts.setEditable(false);
		prevPanelis.add(prevTeksts);
		atspejojamieUi.add(prevTeksts);
		
		/* ------   Notikumi   ----------- */
		/* Datne -> */
		topNavDatneJauns.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Programma.getIzmaina()) {
					switch(JOptionPane.showConfirmDialog(logaPanelis, "Saglabāt šo datubāzi?")) {
						case 1: //n
							jaunsUi(false);
							break;
						case 0: //y
							sagalbatUi(false);
							jaunsUi(false);
							break;
					}
				} else jaunsUi(false);
			}
		});
		
		topNavDatneAtvert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Programma.getIzmaina()) {
					switch(JOptionPane.showConfirmDialog(logaPanelis, "Saglabāt šo datubāzi?")) {
						case 1: //n
							atvertUi();
							break;
						case 0: //y
							sagalbatUi(false);
							atvertUi();
							break;
					}
				} else atvertUi();
			}
		});
		
		topNavDatneSagl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sagalbatUi(false);
			}
		});
		
		topNavDatneSaglKa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sagalbatUi(true);
			}
		});
		
		topNavDatneAizv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Programma.getIzmaina()) {
					switch(JOptionPane.showConfirmDialog(logaPanelis, "Saglabāt šo datubāzi?")) {
						case 1: //n
							aizvertUi();
							break;
						case 0: //y
							sagalbatUi(false);
							aizvertUi();
							break;
					}
				} else aizvertUi();
			}
		});
		
		topNavIziet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Programma.getIzmaina()) {
					switch(JOptionPane.showConfirmDialog(logaPanelis, "Saglabāt šo datubāzi?")) {
						case 1: //n
							izietUi();
							break;
						case 0: //y
							sagalbatUi(false);
							izietUi();
							break;
					}
				} else izietUi();
			}
		});
		
		/* rediģēt */
		topNavRedigetIest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				if (Programma.getDb() != null) {
					IestatLogs.atvertIest();
				}
			}
		});
		
		/* palīdzība */
		topNavPalPar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(ramis, "Picērija - piegāžu grāmatvedis\n2024-04", "Par", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		/* loga notikumi */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (Programma.getIzmaina()) {
					switch(JOptionPane.showConfirmDialog(logaPanelis, "Saglabāt šo datubāzi?")) {
						case 1: //n
							izietUi();
							break;
						case 0: //y
							sagalbatUi(false);
							izietUi();
							break;
					}
				} else izietUi();
			}
		});
	}
}
