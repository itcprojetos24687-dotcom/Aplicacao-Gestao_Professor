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
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Tela_cadastoTurma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastoTurma frame = new Tela_cadastoTurma();
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
	public Tela_cadastoTurma() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1125, 614);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(576, 48, 523, 504);
		contentPane.add(panel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 60, 212, 28);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(159, 360, 212, 28);
		panel_1.add(textField_1);
		
		JLabel lblNewLabel_3 = new JLabel("Nome da Turma");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3.setBounds(48, 36, 109, 21);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_5 = new JLabel("Ano de Ingresso");
		lblNewLabel_3_5.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_5.setBounds(327, 190, 186, 21);
		panel_1.add(lblNewLabel_3_5);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(409, 454, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBackground(Color.BLUE);
		btnNewButton_1.setBounds(288, 454, 101, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_3_6 = new JLabel("Qualificação Associada");
		lblNewLabel_3_6.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_6.setBounds(311, 36, 165, 21);
		panel_1.add(lblNewLabel_3_6);
		
		JLabel lblNewLabel_3_7 = new JLabel("Director de Turma\r\n");
		lblNewLabel_3_7.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_7.setBounds(206, 328, 165, 21);
		panel_1.add(lblNewLabel_3_7);
		
		JLabel lblNewLabel_3_8 = new JLabel("Turmo");
		lblNewLabel_3_8.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_8.setBounds(79, 190, 62, 21);
		panel_1.add(lblNewLabel_3_8);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(286, 60, 212, 28);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(286, 223, 212, 28);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 222, 212, 28);
		panel_1.add(textField_4);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setToolTipText("Masculino\r\nFeminino");
		panel.setBackground(Color.BLUE);
		panel.setBounds(194, 48, 372, 504);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("\r\n");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\Screenshot 2026-06-28 163103.png"));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(91, 42, 211, 254);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(" Gestão de Turmas\r\n\r\n");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(74, 360, 228, 36);
		panel.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.BLUE);
		panel_3.setBounds(37, 48, 133, 504);
		contentPane.add(panel_3);
		
		JButton btnNewButton_2 = new JButton("Cadastro");
		btnNewButton_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(10, 32, 114, 30);
		panel_3.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Consulta");
		btnNewButton_2_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_2_1.setBounds(10, 155, 114, 30);
		panel_3.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Actualizar");
		btnNewButton_2_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_2_2.setBounds(10, 290, 114, 30);
		panel_3.add(btnNewButton_2_2);
		
		JButton btnNewButton_2_3 = new JButton("Remover");
		btnNewButton_2_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_2_3.setBounds(10, 431, 114, 30);
		panel_3.add(btnNewButton_2_3);
		
		JLabel lblCadastrarTurma = new JLabel("Cadastrar Turma");
		lblCadastrarTurma.setForeground(Color.WHITE);
		lblCadastrarTurma.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblCadastrarTurma.setBounds(34, 3, 309, 34);
		contentPane.add(lblCadastrarTurma);

	}

}
