package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

public class Cep extends JFrame {

	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox cboUF;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
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
	public Cep() {
		setTitle("Buscar Cep");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/Casa.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCEP = new JLabel("CEP");
		lblCEP.setBounds(10, 40, 46, 14);
		contentPane.add(lblCEP);

		txtCep = new JTextField();
		txtCep.setBounds(97, 37, 110, 20);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		JLabel lblEND = new JLabel("ENDERE\u00C7O");
		lblEND.setBounds(10, 78, 67, 14);
		contentPane.add(lblEND);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(97, 75, 265, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblBAIRRO = new JLabel("BAIRRO");
		lblBAIRRO.setBounds(10, 128, 46, 14);
		contentPane.add(lblBAIRRO);

		txtBairro = new JTextField();
		txtBairro.setBounds(97, 125, 265, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel = new JLabel("CIDADE");
		lblNewLabel.setBounds(10, 177, 46, 14);
		contentPane.add(lblNewLabel);

		txtCidade = new JTextField();
		txtCidade.setBounds(97, 174, 155, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("UF");
		lblNewLabel_1.setBounds(282, 177, 30, 14);
		contentPane.add(lblNewLabel_1);

		cboUF = new JComboBox();
		cboUF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUF.setBounds(308, 173, 54, 22);
		contentPane.add(cboUF);

		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(10, 227, 89, 23);
		contentPane.add(btnLimpar);

		JButton btnCep = new JButton("BUSCAR");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Cep");
					txtCep.requestFocus();
				} else {
					buscarCep();
				}
			}
		});
		btnCep.setBounds(273, 36, 89, 23);
		contentPane.add(btnCep);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setToolTipText("Sobre");
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/img/Sobre.png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBorder(null);
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setBounds(376, 11, 48, 48);
		contentPane.add(btnSobre);

		/* Uso da Biblioteca Atxy2k para validação do campo txtCep */
		RestrictedTextField validar = new RestrictedTextField(txtCep);

		lblStatus = new JLabel("");
		lblStatus.setBounds(217, 40, 20, 20);
		contentPane.add(lblStatus);
		validar.setOnlyNums(true);
		validar.setLimit(8);
	} // Fim do construtor

	private void buscarCep() {
		String logradouro = "";
		String tipologradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUF.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipologradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Validado.png")));
					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrato");
					}
				}
			}
			// setar o campo endereço
			txtEndereco.setText(tipologradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void limpar() {
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUF.setSelectedItem(null);
		txtCep.requestFocus();
		lblStatus.setIcon(null);
	}

}
