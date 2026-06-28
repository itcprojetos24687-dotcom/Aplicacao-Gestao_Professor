package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Qualificação extends JFrame {

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
					Qualificação frame = new Qualificação();
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
	public Qualificação() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 540);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(235, 76, 407, 380);
		contentPane.add(panel);
		
		JButton btnNewButton_1 = new JButton("Guardar");
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(230, 311, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setBounds(82, 311, 102, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Cod_qualificação");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(145, 61, 112, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Título_qualificação");
		lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(133, 173, 124, 22);
		panel.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(123, 106, 152, 26);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(123, 203, 152, 26);
		panel.add(textField_1);
		
		JLabel lblAdicionarQualificao = new JLabel("Adicione Qualificação");
		lblAdicionarQualificao.setForeground(Color.WHITE);
		lblAdicionarQualificao.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblAdicionarQualificao.setBounds(276, 43, 341, 30);
		contentPane.add(lblAdicionarQualificao);

	}

}
