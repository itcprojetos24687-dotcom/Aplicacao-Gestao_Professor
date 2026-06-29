package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.QualificacaoController;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Tela_cadastroQualificação extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTitulo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastroQualificação frame = new Tela_cadastroQualificação();
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
	public Tela_cadastroQualificação() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 590);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(225, 103, 509, 455);
		contentPane.add(panel_1);
		
		textTitulo = new JTextField();
		textTitulo.setColumns(10);
		textTitulo.setBounds(37, 72, 212, 28);
		panel_1.add(textTitulo);
		
		JLabel lblNewLabel_3 = new JLabel("Nome da Qualificação");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3.setBounds(37, 36, 181, 21);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_5 = new JLabel("Nivel da Qualificação");
		lblNewLabel_3_5.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_5.setBounds(311, 168, 165, 21);
		panel_1.add(lblNewLabel_3_5);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = textTitulo.getText();
				boolean sucesso;
				try {
					QualificacaoController qc = new QualificacaoController();
					sucesso = qc.cadastrarQualificacao(titulo);
					if(sucesso) {
						JOptionPane.showMessageDialog(null, "Todos os dados introduzidos com sucesso");
					}
					else {
						JOptionPane.showMessageDialog(null,"Introduca Invalida dos daos");
					}
				}catch(NumberFormatException s) {
					JOptionPane.showMessageDialog(null,"Intoducao os dados corretamente");
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Intoducao todos os dados");
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(398, 402, 101, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBackground(Color.BLUE);
		btnNewButton_1.setBounds(286, 402, 101, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_3_6 = new JLabel("Módulos Associados");
		lblNewLabel_3_6.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_6.setBounds(311, 36, 165, 21);
		panel_1.add(lblNewLabel_3_6);
		
		JLabel lblNewLabel_3_7 = new JLabel("Campo Pertencente");
		lblNewLabel_3_7.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_7.setBounds(194, 277, 146, 21);
		panel_1.add(lblNewLabel_3_7);
		
		JLabel lblNewLabel_3_8 = new JLabel("Coordenador da Qualificação");
		lblNewLabel_3_8.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_8.setBounds(20, 168, 212, 21);
		panel_1.add(lblNewLabel_3_8);
		
		JComboBox comboCampo = new JComboBox();
		comboCampo.setBackground(new Color(255, 255, 255));
		comboCampo.setBounds(174, 317, 187, 26);
		panel_1.add(comboCampo);
		
		JComboBox comboNivel = new JComboBox();
		comboNivel.setBackground(new Color(255, 255, 255));
		comboNivel.setBounds(311, 202, 146, 26);
		panel_1.add(comboNivel);
		
		JComboBox comboModulos = new JComboBox();
		comboModulos.setBackground(new Color(255, 255, 255));
		comboModulos.setBounds(286, 72, 165, 26);
		panel_1.add(comboModulos);
		
		JComboBox comboCoordenador = new JComboBox();
		comboCoordenador.setBackground(new Color(255, 255, 255));
		comboCoordenador.setBounds(30, 201, 192, 26);
		panel_1.add(comboCoordenador);
		
		JLabel lblCadastrarQualificao = new JLabel("Cadastrar Qualificação");
		lblCadastrarQualificao.setForeground(Color.WHITE);
		lblCadastrarQualificao.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblCadastrarQualificao.setBounds(288, 36, 337, 34);
		contentPane.add(lblCadastrarQualificao);

	}

}
