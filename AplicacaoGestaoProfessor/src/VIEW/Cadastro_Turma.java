package VIEW;

import java.awt.Dimension;
import model.*;
import controller.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
public class Cadastro_Turma {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textTurno;
	private JTextField textAno_Lectivo;
	private JComboBox comboDiretor_Turma;
	private JComboBox comboQualificacaco ;
	private ArrayList<Qualificacao> qualificacoes;
	private ArrayList<Diretor_Turma> diretor_turma;
	private int idUser = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_Turma window = new Cadastro_Turma();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cadastro_Turma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 590, 460);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(new Dimension(640,440));
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 25, 616, 367);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCadastroDeTurma = new JLabel("Cadastro de Turma");
		lblCadastroDeTurma.setFont(new Font("Dialog", Font.BOLD, 17));
		lblCadastroDeTurma.setBounds(224, 0, 187, 24);
		panel.add(lblCadastroDeTurma);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNome.setBounds(71, 84, 59, 17);
		panel.add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(79, 113, 152, 29);
		panel.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTurno.setBounds(71, 154, 59, 17);
		panel.add(lblTurno);
		
		textTurno = new JTextField();
		textTurno.setColumns(10);
		textTurno.setBounds(79, 183, 152, 29);
		panel.add(textTurno);
		
		JLabel lblAnoLectivo = new JLabel("Ano Lectivo\n\n");
		lblAnoLectivo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAnoLectivo.setBounds(71, 224, 131, 24);
		panel.add(lblAnoLectivo);
		
		textAno_Lectivo = new JTextField();
		textAno_Lectivo.setColumns(10);
		textAno_Lectivo.setBounds(79, 260, 152, 29);
		panel.add(textAno_Lectivo);
		
		comboDiretor_Turma = new JComboBox();
		try {
			Diretor_TurmaController dc = new Diretor_TurmaController();
			diretor_turma = dc.comboDiretor_Turma();
			for(Diretor_Turma dt: diretor_turma) {
				comboDiretor_Turma.addItem(dt);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		comboDiretor_Turma.setBounds(356, 113, 158, 28);
		panel.add(comboDiretor_Turma);
		
		JLabel lblNewLabel = new JLabel("Diretor de Turma\n");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(356, 84, 158, 17);
		panel.add(lblNewLabel);
		
		JLabel lblQualificacao = new JLabel("Qualificacao\n");
		lblQualificacao.setFont(new Font("Dialog", Font.BOLD, 15));
		lblQualificacao.setBounds(356, 154, 158, 17);
		panel.add(lblQualificacao);
		
		comboQualificacaco = new JComboBox();
		try {
			QualificacaoController qc = new QualificacaoController();
			qualificacoes = qc.comboQualificacao();
			for(Qualificacao q : qualificacoes) {
				comboQualificacaco.addItem(q);
			}
		}catch(Exception s) {
			s.printStackTrace();
		}
		comboQualificacaco.setBounds(356, 184, 158, 28);
		panel.add(comboQualificacaco);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 405, 616, 45);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnLimpar = new JButton("Limpar");
		panel_1.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar\n");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel_1.add(btnCancelar);
		
		JButton btnNewButton = new JButton("Guardar\n\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textNome.getText();
				String turno = textTurno.getText();
				int ano_lectivo = Integer.parseInt(textAno_Lectivo.getText());
				Diretor_Turma dt = (Diretor_Turma)comboDiretor_Turma.getSelectedItem();
				Qualificacao q = (Qualificacao) comboQualificacaco.getSelectedItem();
				boolean sucesso;
				try {
					TurmaController tc = new TurmaController();
					if(idUser == 0) {
						sucesso = tc.cadastrarTurma(nome, ano_lectivo, turno, dt, q);
						if (sucesso) {
							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
						}
						else {
							JOptionPane.showMessageDialog(null, "Falha ao cadastrar");
						}
					}else {
						sucesso = tc.atualizarTurma(idUser, nome, ano_lectivo, turno, dt, q);
						if(sucesso) {
							idUser = 0;
							JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
						}else {
							JOptionPane.showMessageDialog(null,"Falha ao atualizar");
						}
					}
				}catch(Exception s) {
					s.printStackTrace();
				}
			}
		});
		panel_1.add(btnNewButton);
	}
	public void buscarTurma(int codigo, String nome, int ano_lectivo, String turno, String diretor, String Qualificacao) {
		idUser = codigo;
		textNome.setText(nome);
		textAno_Lectivo.setText(String.valueOf(ano_lectivo));
		textTurno.setText(turno);
		for(int i = 0; i< comboDiretor_Turma.getItemCount();i++) {
			if(comboDiretor_Turma.getItemAt(i).equals(Qualificacao)) {
				comboDiretor_Turma.setSelectedIndex(i);
			}
		}
		for(int i = 0; i< comboQualificacaco.getItemCount();i++) {
			if(comboQualificacaco.getItemAt(i).equals(Qualificacao)) {
				comboQualificacaco.setSelectedIndex(i);
			}
		}
	}

}
