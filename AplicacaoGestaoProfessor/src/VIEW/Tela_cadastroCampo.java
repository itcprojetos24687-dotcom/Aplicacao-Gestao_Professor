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

public class Tela_cadastroCampo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastroCampo frame = new Tela_cadastroCampo();
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
	public Tela_cadastroCampo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 572);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarCampo = new JLabel("Cadastrar Campo\r\n");
		lblCadastrarCampo.setForeground(Color.WHITE);
		lblCadastrarCampo.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblCadastrarCampo.setBounds(276, 59, 259, 34);
		contentPane.add(lblCadastrarCampo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(134, 105, 523, 390);
		contentPane.add(panel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 93, 212, 21);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(111, 217, 306, 78);
		panel_1.add(textField_1);
		
		JLabel lblNewLabel_3 = new JLabel("Nome do Campo");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3.setBounds(44, 47, 140, 21);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Descrição do Campo\r\n");
		lblNewLabel_3_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(196, 185, 140, 21);
		panel_1.add(lblNewLabel_3_1);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(392, 337, 101, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBackground(Color.BLUE);
		btnNewButton_1.setBounds(281, 337, 101, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_3_6 = new JLabel("Área do Campo");
		lblNewLabel_3_6.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_6.setBounds(323, 47, 120, 21);
		panel_1.add(lblNewLabel_3_6);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(281, 93, 212, 21);
		panel_1.add(textField_2);

	}

}
