package view;

import java.awt.EventQueue;
import controller.UsuarioController;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsername;
	private JTextField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 788);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(126, 89, 534, 552);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SISTEMA\r\n\r\n");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblNewLabel.setBounds(169, 52, 130, 35);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("DE");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblNewLabel_1.setBounds(206, 98, 49, 26);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("GESTÃO\r\n");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblNewLabel_2.setBounds(169, 135, 130, 35);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("DE");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblNewLabel_1_1.setBounds(206, 181, 49, 26);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("PROFESSORES");
		lblNewLabel_2_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblNewLabel_2_1.setBounds(130, 218, 204, 35);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\scientist-icon (2).png"));
		lblNewLabel_4.setBounds(110, 264, 256, 264);
		panel.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(670, 95, 541, 546);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("LOGIN\r\n");
		lblNewLabel_2_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2_2.setForeground(Color.BLUE);
		lblNewLabel_2_2.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblNewLabel_2_2.setBounds(210, 55, 130, 35);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_3 = new JLabel("USUÁRIO:");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 18));
		lblNewLabel_3.setBounds(210, 152, 98, 26);
		panel_1.add(lblNewLabel_3);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textUsername.setBounds(146, 189, 225, 26);
		panel_1.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("SENHA:");
		lblNewLabel_3_1.setFont(new Font("Bahnschrift", Font.BOLD, 18));
		lblNewLabel_3_1.setBounds(210, 265, 73, 26);
		panel_1.add(lblNewLabel_3_1);
		
		textPassword = new JTextField();
		textPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textPassword.setColumns(10);
		textPassword.setBounds(146, 305, 225, 26);
		panel_1.add(textPassword);
		
		JButton btnNewButton = new JButton("SAIR\r\n");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		btnNewButton.setBounds(263, 488, 89, 25);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ENTRAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();
				String password = textPassword.getText();
				UsuarioController uc = null;
				boolean sucesso;
				try {
					uc = new UsuarioController();
					sucesso = uc.login(username,password);
					if(sucesso) {
						JOptionPane.showMessageDialog(null, "Login com sucesso, Bem-Vindo ");
					}
					else {
						JOptionPane.showMessageDialog(null, "Nome do usuario ou password invalido!");
					}
				}catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Por favor, Tente Novamente!");
				}
				
			}
		});
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		btnNewButton_1.setBounds(415, 487, 89, 26);
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLUE);
		panel_2.setBounds(93, 641, 1148, 21);
		contentPane.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLUE);
		panel_3.setBounds(93, 73, 39, 568);
		contentPane.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.BLUE);
		panel_4.setBounds(131, 73, 1110, 21);
		contentPane.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.BLUE);
		panel_5.setBounds(1211, 89, 30, 552);
		contentPane.add(panel_5);

	}
}
