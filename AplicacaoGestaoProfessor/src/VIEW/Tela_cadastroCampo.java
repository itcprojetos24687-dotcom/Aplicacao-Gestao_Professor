package VIEW;
import java.awt.EventQueue;
import controller.CampoController;
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
import model.Campo;
import dao.ExceptionDao;

public class Tela_cadastroCampo extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeCampo;

	public static void main(String[] args) {
		try { 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		} catch (Exception e) {}
		
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

	public Tela_cadastroCampo() {
		setTitle("Cadastro de Campo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(488, 323);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(15, 38, 70));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarCampo = new JLabel("Cadastrar Campo");
		lblCadastrarCampo.setForeground(Color.WHITE);
		lblCadastrarCampo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblCadastrarCampo.setBounds(38, 20, 250, 34);
		contentPane.add(lblCadastrarCampo);
		
		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setBounds(38, 65, 400, 185);
		contentPane.add(card);
		card.setLayout(null);
		
		JLabel lblNomeCampo = new JLabel("Nome do Campo");
		lblNomeCampo.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblNomeCampo.setForeground(new Color(30, 41, 59));
		lblNomeCampo.setBounds(40, 30, 200, 20);
		card.add(lblNomeCampo);
		
		txtNomeCampo = new JTextField();
		txtNomeCampo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtNomeCampo.setBounds(40, 55, 320, 35);
		card.add(txtNomeCampo);
		txtNomeCampo.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(114, 28, 36));
		btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(248, 215, 218));
		btnCancelar.setBorder(new LineBorder(new Color(245, 198, 203)));
		btnCancelar.setBounds(40, 120, 150, 35);
		btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancelar.setFocusPainted(false);
		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();
			}
		});
		card.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnGuardar.setBackground(new Color(13, 110, 253));
		btnGuardar.setBorder(null);
		btnGuardar.setBounds(210, 120, 150, 35);
		btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGuardar.setFocusPainted(false);
		
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
		        String nome = txtNomeCampo.getText().trim();
		        if (nome.isEmpty()) {
		            JOptionPane.showMessageDialog(Tela_cadastroCampo.this, "Por favor, insira o nome do campo!", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        try {
		            CampoController cc = new CampoController();
		            boolean sucesso = cc.cadastrarCampo(nome);
		            if(sucesso) {
		                JOptionPane.showMessageDialog(Tela_cadastroCampo.this, "Campo guardado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		                dispose();
		            } else {
		                JOptionPane.showMessageDialog(Tela_cadastroCampo.this, "Dados inválidos.", "Aviso", JOptionPane.WARNING_MESSAGE);
		            }
		        } catch (ExceptionDao ex) {
		            JOptionPane.showMessageDialog(Tela_cadastroCampo.this, "Erro ao guardar campo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		card.add(btnGuardar);
	}
}