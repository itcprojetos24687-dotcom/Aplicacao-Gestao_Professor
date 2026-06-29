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
import controller.FormadorController;
public class Tela_Matricula extends JFrame {

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
					Tela_Matricula frame = new Tela_Matricula();
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
	public Tela_Matricula() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 495);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Matrícula");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblNewLabel.setBounds(364, 44, 132, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dados do Formando");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_1.setBounds(239, 99, 135, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Qualificação escolhida");
		lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(489, 99, 155, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nível da Qualificação ");
		lblNewLabel_1_1_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(230, 186, 144, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Data da Matrícula");
		lblNewLabel_1_1_1_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(489, 186, 155, 23);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		textField = new JTextField();
		textField.setBounds(211, 136, 163, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(481, 133, 163, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(211, 220, 163, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(481, 220, 163, 20);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		btnNewButton.setBounds(555, 334, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(0, 128, 255));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		btnCancelar.setBounds(441, 333, 89, 23);
		contentPane.add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setBounds(190, 78, 486, 298);
		contentPane.add(panel);

	}
}
