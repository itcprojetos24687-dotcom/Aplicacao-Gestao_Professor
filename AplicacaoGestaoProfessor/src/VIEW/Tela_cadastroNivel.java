package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Tela_cadastroNivel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastroNivel frame = new Tela_cadastroNivel();
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
	public Tela_cadastroNivel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 323);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarNvel = new JLabel("Cadastrar Nível");
		lblCadastrarNvel.setForeground(Color.WHITE);
		lblCadastrarNvel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblCadastrarNvel.setBounds(139, 22, 189, 34);
		contentPane.add(lblCadastrarNvel);
		
		JPanel panel = new JPanel();
		panel.setBounds(97, 67, 293, 185);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Nome do Nível");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 11, 152, 21);
		panel.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 43, 212, 28);
		panel.add(textField);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBackground(Color.BLUE);
		btnNewButton_1.setBounds(61, 151, 101, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(182, 151, 101, 23);
		panel.add(btnNewButton);

	}

}
