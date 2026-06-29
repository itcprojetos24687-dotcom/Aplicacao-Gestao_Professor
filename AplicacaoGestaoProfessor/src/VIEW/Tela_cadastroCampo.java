package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CampoController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_cadastroCampo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

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
		textField.setBounds(113, 117, 212, 21);
		panel_1.add(textField);
		
		JLabel lblNewLabel_3 = new JLabel("Nome do Campo");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3.setBounds(148, 76, 140, 21);
		panel_1.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean sucesso;
				try {
					String nome = textField.getText();
					CampoController cc = new CampoController();
					sucesso =cc.cadastrarCampo(nome);
					if(sucesso) {
						JOptionPane.showMessageDialog(null, "Dados guardados com sucesso");
					}
					else {
						JOptionPane.showMessageDialog(null, "Introducao invalida, Tente Novamente");
					}
					
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,"Introduca todos os dados corretamente"+ex);
				}catch(Exception s) {
					JOptionPane.showMessageDialog(null, "Introduca todo os dados" +s);
				}
				
				
			}
		});
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

	}

}
