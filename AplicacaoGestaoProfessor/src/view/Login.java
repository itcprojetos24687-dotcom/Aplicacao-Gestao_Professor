package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setFont(new Font("Times New Roman", Font.BOLD, 18));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 468);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(28, 113, 216));
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 27));
		lblNewLabel.setBounds(534, 30, 103, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblUsuario.setBounds(432, 92, 72, 24);
		contentPane.add(lblUsuario);
		
		textField = new JTextField();
		textField.setBounds(432, 121, 322, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPalavrapasse = new JLabel("Palavra-passe");
		lblPalavrapasse.setForeground(Color.WHITE);
		lblPalavrapasse.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPalavrapasse.setBounds(432, 192, 127, 33);
		contentPane.add(lblPalavrapasse);
		
		textField_1 = new JTextField();
		textField_1.setBounds(432, 226, 322, 33);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(new Color(28, 113, 216));
		btnEntrar.setFont(new Font("Cantarell Extra Bold", Font.BOLD, 18));
		btnEntrar.setBounds(534, 305, 103, 27);
		contentPane.add(btnEntrar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setForeground(new Color(28, 113, 216));
		btnSair.setFont(new Font("Cantarell Extra Bold", Font.BOLD, 18));
		btnSair.setBounds(534, 371, 103, 27);
		contentPane.add(btnSair);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/img/woman-user-color-icon.png")));
		lblNewLabel_1.setBounds(12, 23, 403, 373);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblSistemaDeGestao = new JLabel("Sistema de Gestao \nde Professores");
		lblSistemaDeGestao.setForeground(Color.WHITE);
		lblSistemaDeGestao.setBackground(Color.WHITE);
		lblSistemaDeGestao.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblSistemaDeGestao.setBounds(50, 402, 329, 41);
		contentPane.add(lblSistemaDeGestao);

	}
}
