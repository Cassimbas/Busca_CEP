package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.URI;
import java.awt.Desktop;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setResizable(false);
		setTitle("Sobre...");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/Casa.png")));
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Buscar CEP Vers\u00E3o 1.0");
		lblNewLabel.setBounds(10, 37, 132, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Autor: C\u00E1ssio Rodrigues Braga");
		lblNewLabel_1.setBounds(10, 80, 223, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Web Server");
		lblNewLabel_2.setBounds(10, 121, 70, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblWebService = new JLabel("republicavirtual.com.br/");
		lblWebService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://www.republicavirtual.com.br/");
			}
		});
		lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblWebService.setForeground(SystemColor.textHighlight);
		lblWebService.setBounds(115, 121, 154, 14);
		getContentPane().add(lblWebService);

		JButton btnYouTube = new JButton("");
		btnYouTube.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://www.youtube.com/playlist?list=PLbEOwbQR9lqxVuDWNIrG57_JGcbIL3FWP");
			}
		});
		btnYouTube.setToolTipText("YouTube");
		btnYouTube.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnYouTube.setBorder(null);
		btnYouTube.setIcon(new ImageIcon(Sobre.class.getResource("/img/YouTube.png")));
		btnYouTube.setBounds(10, 167, 51, 51);
		getContentPane().add(btnYouTube);

		JButton btnGithub = new JButton("");
		btnGithub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://github.com/Cassimbas?tab=repositories");
			}
		});
		btnGithub.setToolTipText("Projeto");
		btnGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGithub.setBorder(null);
		btnGithub.setIcon(new ImageIcon(Sobre.class.getResource("/img/Github.png")));
		btnGithub.setBounds(115, 167, 51, 51);
		getContentPane().add(btnGithub);

	}// Fim do Construtor

	private void link(String site) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI(site);
			desktop.browse(uri);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
