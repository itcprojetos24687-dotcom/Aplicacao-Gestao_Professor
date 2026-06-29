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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_cadastroQualificação extends JFrame {

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
					Tela_cadastroQualificação frame = new Tela_cadastroQualificação();
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
	public Tela_cadastroQualificação() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 590);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(210, 80, 509, 455);
		contentPane.add(panel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 60, 212, 28);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(159, 309, 212, 28);
		panel_1.add(textField_1);
		
		JLabel lblNewLabel_3 = new JLabel("Nome da Qualificação");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3.setBounds(37, 36, 152, 21);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_5 = new JLabel("Nivel da Qualificação");
		lblNewLabel_3_5.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_5.setBounds(311, 168, 165, 21);
		panel_1.add(lblNewLabel_3_5);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(398, 402, 101, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBackground(Color.BLUE);
		btnNewButton_1.setBounds(286, 402, 101, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_3_6 = new JLabel("Módulos Associados");
		lblNewLabel_3_6.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_6.setBounds(311, 36, 165, 21);
		panel_1.add(lblNewLabel_3_6);
		
		JLabel lblNewLabel_3_7 = new JLabel("Campo Pertencente");
		lblNewLabel_3_7.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_7.setBounds(194, 277, 146, 21);
		panel_1.add(lblNewLabel_3_7);
		
		JLabel lblNewLabel_3_8 = new JLabel("Coordenador da Qualificação");
		lblNewLabel_3_8.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_8.setBounds(20, 168, 198, 21);
		panel_1.add(lblNewLabel_3_8);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(286, 60, 212, 28);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(286, 200, 212, 28);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 200, 212, 28);
		panel_1.add(textField_4);
		
		JLabel lblCadastrarQualificao = new JLabel("Cadastrar Qualificação");
		lblCadastrarQualificao.setForeground(Color.WHITE);
		lblCadastrarQualificao.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblCadastrarQualificao.setBounds(288, 36, 337, 34);
		contentPane.add(lblCadastrarQualificao);

	}

}
