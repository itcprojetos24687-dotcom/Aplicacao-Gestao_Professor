package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Turma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Turma frame = new Turma();
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
	public Turma() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 540);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastrar Turma");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblNewLabel.setBounds(278, 41, 254, 30);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(207, 82, 407, 380);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Guardar");
		btnNewButton_1.setBounds(230, 311, 89, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnNewButton_1.setBackground(Color.WHITE);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2020", "2021", "2022", "2023", "2024", "2025", "2026"}));
		comboBox.setBounds(22, 144, 149, 22);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(82, 311, 89, 23);
		panel.add(btnNewButton);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnNewButton.setBackground(Color.BLUE);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo");
		lblNewLabel_1.setBounds(27, 26, 87, 22);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_1 = new JLabel("Ano_Ingresso");
		lblNewLabel_1_1.setBounds(25, 111, 104, 22);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		
		textField_4 = new JTextField();
		textField_4.setBounds(20, 59, 152, 26);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(215, 59, 152, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(215, 142, 152, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Turno");
		lblNewLabel_2_1.setBounds(215, 113, 64, 14);
		panel.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setBounds(215, 30, 64, 14);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));

	}

}
