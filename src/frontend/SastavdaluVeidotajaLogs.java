package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import picerija.DatuVieniba;
import picerija.PicasSastavdala;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class SastavdaluVeidotajaLogs extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private static DatuVieniba sastavdala = null;
	
	public static DatuVieniba jaunaSastavdala(DatuVieniba preview) {
		SastavdaluVeidotajaLogs dialog = new SastavdaluVeidotajaLogs(preview);
		
		dialog.setVisible(true);
		return sastavdala;
	}
	
	public SastavdaluVeidotajaLogs(DatuVieniba preview) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel poguPanelis = new JPanel();
		FlowLayout flowLayout = (FlowLayout) poguPanelis.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(poguPanelis, BorderLayout.SOUTH);
		
		JButton saglPoga = new JButton("Saglabāt");
		poguPanelis.add(saglPoga);
		
		JButton atceltPoga = new JButton("Atcelt");
		poguPanelis.add(atceltPoga);
		
		JPanel infPanelis = new JPanel();
		getContentPane().add(infPanelis, BorderLayout.CENTER);
		infPanelis.setLayout(new BoxLayout(infPanelis, BoxLayout.Y_AXIS));
		
		if (preview != null) {
			setTitle("Rediģēt '" + preview.getNosaukums() + "'");
		} else {
			setTitle("Jauna sastāvdaļa");
		}
		
		/* notikumi */
		saglPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sastavdala = new PicasSastavdala("qqqqq", "qqqqq", 0.55);
				dispose();
			}
		});
		
		atceltPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
