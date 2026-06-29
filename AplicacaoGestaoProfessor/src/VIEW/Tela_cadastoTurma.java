package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TurmaController;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_cadastoTurma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTurma;
	private JTextField textano_ingresso;
	JComboBox comboDiretor_turma;
	JComboBox comboTurno;
	JComboBox comboQualificacao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastoTurma frame = new Tela_cadastoTurma();
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
	public Tela_cadastoTurma() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1125, 614);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(576, 48, 523, 504);
		contentPane.add(panel_1);
		
		textTurma = new JTextField();
		textTurma.setColumns(10);
		textTurma.setBounds(12, 69, 212, 28);
		panel_1.add(textTurma);
		
		JLabel lblNewLabel_3 = new JLabel("Nome da Turma");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3.setBounds(48, 36, 143, 21);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_5 = new JLabel("Ano de Ingresso");
		lblNewLabel_3_5.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_5.setBounds(327, 190, 186, 21);
		panel_1.add(lblNewLabel_3_5);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean sucesso;
				try {
					String nome = textTurma.getText();
					JOptionPane.showMessageDialog(null, nome);
					int ano_ingresso = Integer.parseInt(textano_ingresso.getText());
					String turno = comboTurno.getSelectedItem().toString();
					TurmaController tc = new TurmaController();
					sucesso = tc.cadastrarTurma(nome,ano_ingresso,turno);
					if(sucesso) {
						JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
					}else {
						JOptionPane.showMessageDialog(null, "Dados invalidos");
					}
				}catch(Exception sq) {
					sq.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ocorreu um erro");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(409, 454, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBackground(Color.BLUE);
		btnNewButton_1.setBounds(288, 454, 101, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_3_6 = new JLabel("Qualificação Associada");
		lblNewLabel_3_6.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_6.setBounds(311, 36, 165, 21);
		panel_1.add(lblNewLabel_3_6);
		
		JLabel lblNewLabel_3_7 = new JLabel("Director de Turma\r\n");
		lblNewLabel_3_7.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_7.setBounds(206, 328, 165, 21);
		panel_1.add(lblNewLabel_3_7);
		
		JLabel lblNewLabel_3_8 = new JLabel("Turmo");
		lblNewLabel_3_8.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_8.setBounds(79, 190, 62, 21);
		panel_1.add(lblNewLabel_3_8);
		
		textano_ingresso = new JTextField();
		textano_ingresso.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textano_ingresso.setColumns(10);
		textano_ingresso.setBounds(311, 223, 149, 28);
		panel_1.add(textano_ingresso);
		
		 comboTurno = new JComboBox();
		comboTurno.setBackground(new Color(255, 255, 255));
		comboTurno.setModel(new DefaultComboBoxModel(new String[] {"Diurno", "Noturno"}));
		comboTurno.setBounds(36, 222, 111, 26);
		panel_1.add(comboTurno);
		
		comboQualificacao = new JComboBox();
		comboQualificacao.setBackground(new Color(255, 255, 255));
		comboQualificacao.setBounds(288, 69, 169, 26);
		panel_1.add(comboQualificacao);
		
		comboDiretor_turma = new JComboBox();
		comboDiretor_turma.setBackground(new Color(255, 255, 255));
		comboDiretor_turma.setBounds(187, 376, 156, 26);
		panel_1.add(comboDiretor_turma);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setToolTipText("Masculino\r\nFeminino");
		panel.setBackground(Color.BLUE);
		panel.setBounds(194, 48, 372, 504);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("\r\n");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\Screenshot 2026-06-28 163103.png"));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(91, 42, 211, 254);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(" Gestão de Turmas\r\n\r\n");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(74, 360, 228, 36);
		panel.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.BLUE);
		panel_3.setBounds(37, 48, 133, 504);
		contentPane.add(panel_3);
		
		JButton btnNewButton_2 = new JButton("Cadastro");
		btnNewButton_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(10, 32, 114, 30);
		panel_3.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Consulta");
		btnNewButton_2_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_2_1.setBounds(10, 155, 114, 30);
		panel_3.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Actualizar");
		btnNewButton_2_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_2_2.setBounds(10, 290, 114, 30);
		panel_3.add(btnNewButton_2_2);
		
		JButton btnNewButton_2_3 = new JButton("Remover");
		btnNewButton_2_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_2_3.setBounds(10, 431, 114, 30);
		panel_3.add(btnNewButton_2_3);
		
		JLabel lblCadastrarTurma = new JLabel("Cadastrar Turma");
		lblCadastrarTurma.setForeground(Color.WHITE);
		lblCadastrarTurma.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblCadastrarTurma.setBounds(34, 3, 309, 34);
		contentPane.add(lblCadastrarTurma);

	}

}
