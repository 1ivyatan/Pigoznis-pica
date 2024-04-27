package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import picerija.DatuVieniba;
import picerija.Pica;
import picerija.PicasSastavdala;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PicuVeidotajaLogs extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private static DatuVieniba pica = null;
	private static ArrayList<DatuVieniba> sastavdalas = null;
	
	private static MeklejamsSaraksts picasSastavdalas = null;
	private JTextField nosTeksts;
	private JTextField diametrsTeksts;
	private JTextField cenuTeksts;
	private JTextArea piezTeksts;
	
	public static DatuVieniba jaunaPica(DatuVieniba preview) {
		pica = null;
		PicuVeidotajaLogs dialog = new PicuVeidotajaLogs(preview);
		
		dialog.setVisible(true);
		return pica;
	}
	
	public PicuVeidotajaLogs(DatuVieniba preview) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setMinimumSize(new Dimension(400, 450));
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
		
		JPanel nosPanelis = new JPanel();
		infPanelis.add(nosPanelis);
		nosPanelis.add(new JLabel("Nosaukums"));
		
		nosTeksts = new JTextField();
		nosPanelis.add(nosTeksts);
		nosTeksts.setColumns(10);
		
		JPanel diametrsPanelis = new JPanel();
		infPanelis.add(diametrsPanelis);
		diametrsPanelis.add(new JLabel("Diametrs"));
		
		diametrsTeksts = new JTextField();
		diametrsPanelis.add(diametrsTeksts);
		diametrsTeksts.setColumns(10);
		diametrsPanelis.add(new JLabel("cm"));
		
		JPanel cenuPanelis = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) cenuPanelis.getLayout();
		flowLayout_1.setVgap(10);
		infPanelis.add(cenuPanelis);
		cenuPanelis.add(new JLabel("Cena"));
		
		cenuTeksts = new JTextField();
		cenuPanelis.add(cenuTeksts);
		cenuTeksts.setColumns(10);
		cenuPanelis.add(new JLabel(Programma.getDb().getDati().getValutasSim()));
		
		JPanel sastavdaluPanelis = new JPanel();
		infPanelis.add(sastavdaluPanelis);
		sastavdaluPanelis.setLayout(new BorderLayout(0, 0));
		JLabel label = new JLabel(" Sastāvdaļas");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		sastavdaluPanelis.add(label, BorderLayout.NORTH);
		
		/* sastāvdaļas */
		sastavdalas = new ArrayList<DatuVieniba>();
		picasSastavdalas = new MeklejamsSaraksts(null);
		picasSastavdalas.setElementi(sastavdalas);
		sastavdaluPanelis.add(picasSastavdalas);
		
		JPanel sastavPogas = new JPanel();
		sastavdaluPanelis.add(sastavPogas, BorderLayout.SOUTH);
		
		JButton sastavJauns = new JButton("Jauns");
		sastavPogas.add(sastavJauns);
		
		JButton sastavRediget = new JButton("Rediģēt");
		sastavPogas.add(sastavRediget);
		
		JButton sastavDzest = new JButton("Dzēst");
		sastavPogas.add(sastavDzest);
		
		JButton sastavImp = new JButton("No datubāzes...");
		sastavPogas.add(sastavImp);
		/* ^^^^^^^^^^^^^^^ */
		
		JPanel piezPanelis = new JPanel();
		infPanelis.add(piezPanelis);
		piezPanelis.setLayout(new BorderLayout(0, 0));
		piezPanelis.add(new JLabel(" Piezīme    "), BorderLayout.WEST);
		
		piezTeksts = new JTextArea();
		piezPanelis.add(new JScrollPane(piezTeksts, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
		
		if (preview != null) {
			setTitle("Rediģēt '" + preview.getNosaukums() + "'");
			
		} else {
			setTitle("Jauna pica");
		}
		
		/* notikumi */
		/* sastāvdaļas */
		sastavJauns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatuVieniba sastavdala = SastavdaluVeidotajaLogs.jaunaSastavdala(null);
				
				if (sastavdala != null) {
					sastavdalas.add(sastavdala);
					picasSastavdalas.setElementi();
				}
			}
		});
		
		sastavRediget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = picasSastavdalas.getSelIdx();
				if (sel != -1) {
					DatuVieniba js = SastavdaluVeidotajaLogs.jaunaSastavdala( picasSastavdalas.getSelectedDV() );
					
					if (js != null) {
						sastavdalas.set(sel, js);
						picasSastavdalas.setElementi();
					}
				}
			}
		});
		
		sastavDzest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = picasSastavdalas.getSelIdx();
				if (sel != -1 && JOptionPane.showConfirmDialog(getContentPane(), "Tiešām dzēst šo sastāvdaļu '" + picasSastavdalas.getSelectedDV().getNosaukums() + "'?", "Jautājums", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					sastavdalas.remove(sel);
					picasSastavdalas.setElementi();
				}
			}
		});
		
		
		/* saglab. pogas */
		saglPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		atceltPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}

