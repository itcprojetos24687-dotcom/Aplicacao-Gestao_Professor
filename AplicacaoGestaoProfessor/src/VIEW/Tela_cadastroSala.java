package VIEW;
import model.*;
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
import javax.swing.JComboBox;
import model.Sala;
import dao.ExceptionDao;
import controller.*;
public class Tela_cadastroSala extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeSala;
	private JComboBox<String> cmbTipoSala;

//	public static void main(String[] args) {
//		try { 
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
//		} catch (Exception e) {}
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Tela_cadastroSala frame = new Tela_cadastroSala();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	// Em Tela_Principal.java, adiciona este caso em abrirJanelaLegada():
	//
	// private void abrirJanelaLegada(String nomeClasse) {
	//     try {
	//         if (nomeClasse.equals("Tela_cadastroQualificação")) new Tela_cadastroQualificação().setVisible(true);
	//         else if (nomeClasse.equals("Tela_cadastroNivel")) new Tela_cadastroNivel().setVisible(true);
	//         else if (nomeClasse.equals("Tela_cadastroSala")) new Tela_cadastroSala().setVisible(true);
	//     } catch (Exception e) {
	//         JOptionPane.showMessageDialog(frame, "Erro ao abrir a janela.", "Erro", JOptionPane.ERROR_MESSAGE);
	//     }
	// }

	public Tela_cadastroSala() {
		setTitle("Cadastro de Sala");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(488, 323);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(15, 38, 70));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarSala = new JLabel("Cadastrar Sala");
		lblCadastrarSala.setForeground(Color.WHITE);
		lblCadastrarSala.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblCadastrarSala.setBounds(38, 20, 250, 34);
		contentPane.add(lblCadastrarSala);
		
		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setBounds(38, 65, 400, 185);
		contentPane.add(card);
		card.setLayout(null);
		
		JLabel lblNomeSala = new JLabel("Designação da Sala");
		lblNomeSala.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblNomeSala.setForeground(new Color(30, 41, 59));
		lblNomeSala.setBounds(40, 10, 200, 20);
		card.add(lblNomeSala);
		
		txtNomeSala = new JTextField();
		txtNomeSala.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtNomeSala.setBounds(40, 32, 320, 30);
		card.add(txtNomeSala);
		txtNomeSala.setColumns(10);
		
		JLabel lblTipoSala = new JLabel("Tipo de Sala");
		lblTipoSala.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblTipoSala.setForeground(new Color(30, 41, 59));
		lblTipoSala.setBounds(40, 70, 200, 20);
		card.add(lblTipoSala);
		
		cmbTipoSala = new JComboBox<String>();
		cmbTipoSala.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] {"Teórica", "Prática", "Laboratório"}));
		cmbTipoSala.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbTipoSala.setBounds(40, 92, 320, 30);
		card.add(cmbTipoSala);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(114, 28, 36));
		btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(248, 215, 218));
		btnCancelar.setBorder(new LineBorder(new Color(245, 198, 203)));
		btnCancelar.setBounds(40, 135, 150, 35);
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
		btnGuardar.setBounds(210, 135, 150, 35);
		btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGuardar.setFocusPainted(false);
		
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String designacao = txtNomeSala.getText().trim();
				String tipo = (String) cmbTipoSala.getSelectedItem();
				boolean sucesso;
				if (designacao.isEmpty()) {
					JOptionPane.showMessageDialog(Tela_cadastroSala.this, "Por favor, insira a designação da sala!", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					SalaController sala = new SalaController();
					sucesso = sala.cadastrarSala(designacao,tipo);
					if(sucesso) {
						JOptionPane.showMessageDialog(Tela_cadastroSala.this, "Sala '" + designacao + "' guardada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						
					}else {
						JOptionPane.showMessageDialog(Tela_cadastroSala.this, "Falha ao cadastrar Sala", "Falhado", JOptionPane.INFORMATION_MESSAGE);

					}
				} catch (ExceptionDao ex) {
					JOptionPane.showMessageDialog(Tela_cadastroSala.this, "Erro ao guardar sala: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		card.add(btnGuardar);
	}
}