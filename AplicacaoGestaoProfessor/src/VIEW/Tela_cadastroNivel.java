package VIEW;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;

public class Tela_cadastroNivel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeNivel;

	public static void main(String[] args) {
		try { 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		} catch (Exception e) {}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastroNivel frame = new Tela_cadastroNivel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela_cadastroNivel() {
		setTitle("Cadastro de Nível");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(488, 323);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(15, 38, 70));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarNvel = new JLabel("Cadastrar Nível");
		lblCadastrarNvel.setForeground(Color.WHITE);
		lblCadastrarNvel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblCadastrarNvel.setBounds(38, 20, 250, 34);
		contentPane.add(lblCadastrarNvel);
		
		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setBounds(38, 65, 400, 185);
		contentPane.add(card);
		card.setLayout(null);
		
		JLabel lblNomeNivel = new JLabel("Nome do Nível de Acesso");
		lblNomeNivel.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblNomeNivel.setForeground(new Color(30, 41, 59));
		lblNomeNivel.setBounds(40, 30, 200, 20);
		card.add(lblNomeNivel);
		
		txtNomeNivel = new JTextField();
		txtNomeNivel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtNomeNivel.setBounds(40, 55, 320, 35);
		card.add(txtNomeNivel);
		txtNomeNivel.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(114, 28, 36));
		btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(248, 215, 218));
		btnCancelar.setBorder(new LineBorder(new Color(245, 198, 203)));
		btnCancelar.setBounds(40, 120, 150, 35);
		btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancelar.setFocusPainted(false);
		btnCancelar.addActionListener(e -> dispose());
		card.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnGuardar.setBackground(new Color(13, 110, 253));
		btnGuardar.setBorder(null);
		btnGuardar.setBounds(210, 120, 150, 35);
		btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGuardar.setFocusPainted(false);
		
		btnGuardar.addActionListener(e -> {
			if (txtNomeNivel.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Por favor, insira o nome do nível!", "Aviso", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Nível '" + txtNomeNivel.getText().trim() + "' guardado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		card.add(btnGuardar);
	}
}