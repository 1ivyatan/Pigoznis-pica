package frontend;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import picerija.Resursi;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import java.awt.Component;

public class Logs extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/* logs */
	private static JPanel logaPanelis;
	private static Logs ramis;
	
	/* izvēle */
	private static JFileChooser izv;
	private static FileNameExtensionFilter extFiltrs;
	
	/* statuss */
	private static JLabel statusaTeksts;
	
	/* ui slēdze */
	private static ArrayList<Object> atspejojamieUi;
	private static void setLogaNos() {
		String nos = (Programma.getDb() != null && Programma.getDb().getFails() != null) ? Programma.getDb().getFails().getName() + " - ": "";
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
		if (iznicinatFailu) {
			Programma.getDb().setFails(null);
		}
		
		if (Programma.getDb().getFails() == null) {
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
					ramis = new Logs();

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
	public Logs() {
		/* ------   Db sagatavošana   ----------- */
		extFiltrs = new FileNameExtensionFilter("Picērijas datubāze (." + Resursi.failaExt + ")", Resursi.failaExt);

		izv = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		izv.setFileSelectionMode(JFileChooser.FILES_ONLY);
		izv.addChoosableFileFilter(extFiltrs);
		izv.setFileFilter(extFiltrs);
		
		/* --------- logs -------------- */
		atspejojamieUi = new ArrayList<Object>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
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
		
		JPanel izvPanelis = new JPanel();
		izvPanelis.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		logaPanelis.add(izvPanelis, BorderLayout.WEST);
		
		JPanel prevPanelis = new JPanel();
		prevPanelis.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		logaPanelis.add(prevPanelis, BorderLayout.CENTER);
		
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
	}
}
