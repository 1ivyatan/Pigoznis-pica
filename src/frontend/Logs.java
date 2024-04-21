package frontend;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;

public class Logs extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel logaPanelis;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar topNav = new JMenuBar();
		setJMenuBar(topNav);
		
		JMenu topNavDatne = new JMenu("Datne");
		topNav.add(topNavDatne);
		
		JMenuItem topNavDatneJauns = new JMenuItem("Jauns");
		topNavDatne.add(topNavDatneJauns);
		logaPanelis = new JPanel();
		logaPanelis.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(logaPanelis);
		logaPanelis.setLayout(new BorderLayout(0, 0));
		
		JPanel statusaPanelis = new JPanel();
		statusaPanelis.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) statusaPanelis.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		logaPanelis.add(statusaPanelis, BorderLayout.SOUTH);
		
		JLabel statusaTeksts = new JLabel("Sviecināti");
		statusaPanelis.add(statusaTeksts);
		
		JPanel izvPanelis = new JPanel();
		izvPanelis.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		logaPanelis.add(izvPanelis, BorderLayout.WEST);
		
		JPanel prevPanelis = new JPanel();
		prevPanelis.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		logaPanelis.add(prevPanelis, BorderLayout.CENTER);
		
		/* ------   Notikumi   ----------- */
		topNavDatneJauns.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		/* ------   Db sagatavošana   ----------- */
		Programma.tuksotDb();
	}

	
}
