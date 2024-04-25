package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import picerija.Kontakts;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class KontaktuVeidotajs extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private static Kontakts kontakts = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public static Kontakts jaunsKontakts() {
		KontaktuVeidotajs dialog = new KontaktuVeidotajs();
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		
		return kontakts;
	}

	public KontaktuVeidotajs() {
		setTitle("Jauns kontakts");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JPanel vardaPanelis = new JPanel();
		contentPanel.add(vardaPanelis);
		vardaPanelis.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		vardaPanelis.add(lblNewLabel_1);
		
		textField = new JTextField();
		vardaPanelis.add(textField);
		textField.setColumns(10);
		
		JPanel adresesPanelis = new JPanel();
		contentPanel.add(adresesPanelis);
		adresesPanelis.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		adresesPanelis.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		adresesPanelis.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel talrunaPanelis = new JPanel();
		contentPanel.add(talrunaPanelis);
		talrunaPanelis.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("New label");
		talrunaPanelis.add(lblNewLabel);
		
		textField_2 = new JTextField();
		talrunaPanelis.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel piezimjuPanelis = new JPanel();
		contentPanel.add(piezimjuPanelis);
		piezimjuPanelis.setLayout(new BorderLayout(0, 0));
		
		JLabel piezimjuTeksts = new JLabel("Piezīme  ");
		piezimjuPanelis.add(piezimjuTeksts, BorderLayout.WEST);
		
		JTextArea textArea = new JTextArea();
		piezimjuPanelis.add(textArea, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton okButton = new JButton("Saglabāt");
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			
			JButton cancelButton = new JButton("Atcelt");
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			
			/* notikumi */
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					kontakts = new Kontakts("pārbaude", "x", "s", "a");
					dispose();
				}
			});
			
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}
	}

}
