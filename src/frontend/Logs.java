package frontend;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class Logs extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel logaPanelis;
	
	/* izvēle */
	private static JFileChooser izv;
	private static FileNameExtensionFilter extFiltrs;
	
	/* statuss */
	private static JLabel statusaTeksts;
	
	/* atveršana */
	private static void atvertUi() {
		int rez = izv.showOpenDialog(null);
		
		if (rez == JFileChooser.APPROVE_OPTION) {
			try {
				Programma.atvertDb(izv.getSelectedFile());
				statusaTeksts.setText("Atvēra datubāzi " + izv.getSelectedFile().getAbsolutePath());
			} catch (Exception e) {
				statusaTeksts.setText("Nevarēja atvērt datubāzi " + izv.getSelectedFile().getAbsolutePath());
				JOptionPane.showMessageDialog(logaPanelis, e.getMessage(), "Nevarēja atvērt datubāzi", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logs frame = new Logs();
					frame.setVisible(true);
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
		Programma.tuksotDb();
		
		extFiltrs = new FileNameExtensionFilter("Picērijas datubāze (." + Resursi.failaExt + ")", Resursi.failaExt);

		izv = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		izv.setFileSelectionMode(JFileChooser.FILES_ONLY);
		izv.addChoosableFileFilter(extFiltrs);
		izv.setFileFilter(extFiltrs);
		
		/* logs */
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
		
		JSeparator separator = new JSeparator();
		topNavDatne.add(separator);
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
							Programma.tuksotDb();
							statusaTeksts.setText("Izveidota datubāze");
							break;
					}
				} else {
					Programma.tuksotDb();
					statusaTeksts.setText("Izveidota datubāze");
				}
				
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
					}
				} else {
					atvertUi();
				}
			}
		});
	}
}
