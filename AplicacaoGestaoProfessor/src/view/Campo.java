package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Campo extends JFrame {

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
					Campo frame = new Campo();
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
	public Campo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 539);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(230, 78, 407, 380);
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
		btnNewButton.setBounds(87, 311, 97, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Cod_campo");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(145, 61, 112, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome_campo");
		lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(133, 173, 124, 22);
		panel.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(123, 94, 152, 26);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(123, 203, 152, 26);
		panel.add(textField_1);
		
		JLabel lblCampo = new JLabel("Campo");
		lblCampo.setForeground(Color.WHITE);
		lblCampo.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblCampo.setBounds(372, 37, 131, 30);
		contentPane.add(lblCampo);

	}

}
