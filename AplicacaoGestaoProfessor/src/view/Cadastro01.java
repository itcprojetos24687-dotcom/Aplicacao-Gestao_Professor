package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class Cadastro01 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_5;
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
					Cadastro01 frame = new Cadastro01();
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
	public Cadastro01() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1281, 562);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastrar Professor");
		lblNewLabel.setBounds(134, 41, 309, 34);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 24));
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(144, 86, 415, 398);
		panel.setToolTipText("Maculino\r\nFeminino");
		panel.setBackground(Color.BLUE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\r\n");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\scientist-icon (2).png"));
		lblNewLabel_1.setBounds(74, 23, 260, 295);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(" Adicione um novo Professor\r\n");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(29, 329, 386, 36);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(639, 86, 523, 398);
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 212, 35);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 130, 212, 35);
		panel_1.add(textField_1);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(10, 215, 212, 35);
		panel_1.add(textField_5);
		
		JLabel lblNewLabel_3 = new JLabel("Nome");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 11, 94, 21);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Apelido");
		lblNewLabel_3_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(10, 98, 94, 21);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Genero");
		lblNewLabel_3_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_2.setBounds(281, 304, 94, 21);
		panel_1.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Horas de Trabalho");
		lblNewLabel_3_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_3.setBounds(10, 272, 140, 21);
		panel_1.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_4 = new JLabel("Salário Hora");
		lblNewLabel_3_4.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_4.setBounds(10, 304, 109, 21);
		panel_1.add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_3_5 = new JLabel("Área de actuação");
		lblNewLabel_3_5.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_5.setBounds(10, 191, 186, 21);
		panel_1.add(lblNewLabel_3_5);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		spinner.setBounds(160, 304, 67, 20);
		panel_1.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		spinner_1.setBounds(160, 272, 67, 20);
		panel_1.add(spinner_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(392, 303, 106, 22);
		panel_1.add(comboBox);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(409, 581, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.BLUE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(281, 581, 101, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_3_6 = new JLabel("Contacto ");
		lblNewLabel_3_6.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_6.setBounds(288, 11, 94, 21);
		panel_1.add(lblNewLabel_3_6);
		
		JLabel lblNewLabel_3_7 = new JLabel("Curso");
		lblNewLabel_3_7.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_7.setBounds(281, 98, 94, 21);
		panel_1.add(lblNewLabel_3_7);
		
		JLabel lblNewLabel_3_8 = new JLabel("Turma");
		lblNewLabel_3_8.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_8.setBounds(281, 191, 94, 21);
		panel_1.add(lblNewLabel_3_8);
		
		JLabel lblNewLabel_3_9 = new JLabel("EstadoCivil");
		lblNewLabel_3_9.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_9.setBounds(281, 272, 94, 21);
		panel_1.add(lblNewLabel_3_9);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(286, 36, 212, 35);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(286, 130, 212, 35);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(286, 215, 212, 35);
		panel_1.add(textField_4);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Casado/a", "Solteiro/a", "Viúvo/a"}));
		comboBox_1.setBounds(392, 271, 106, 22);
		panel_1.add(comboBox_1);

	}
}
