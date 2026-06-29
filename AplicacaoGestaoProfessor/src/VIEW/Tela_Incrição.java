package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Tela_Incrição extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Incrição frame = new Tela_Incrição();
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
	public Tela_Incrição() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 538);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(147, 84, 523, 363);
		contentPane.add(panel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(21, 77, 212, 21);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 211, 212, 21);
		panel_1.add(textField_1);
		
		JLabel lblNewLabel_3 = new JLabel("Nome do Formando");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3.setBounds(45, 45, 140, 21);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Modulo a Inscrever");
		lblNewLabel_3_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(45, 169, 140, 21);
		panel_1.add(lblNewLabel_3_1);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(391, 320, 101, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBackground(Color.BLUE);
		btnNewButton_1.setBounds(280, 320, 101, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_3_6 = new JLabel("Data da Inscrição ");
		lblNewLabel_3_6.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_6.setBounds(318, 45, 120, 21);
		panel_1.add(lblNewLabel_3_6);
		
		JLabel lblNewLabel_3_7 = new JLabel("Semestre do Módulo");
		lblNewLabel_3_7.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_7.setBounds(312, 169, 165, 21);
		panel_1.add(lblNewLabel_3_7);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(280, 77, 212, 21);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(280, 211, 212, 21);
		panel_1.add(textField_3);
		
		JLabel lblInscrio = new JLabel("Inscrição");
		lblInscrio.setForeground(Color.WHITE);
		lblInscrio.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblInscrio.setBounds(319, 39, 157, 34);
		contentPane.add(lblInscrio);

	}

}
