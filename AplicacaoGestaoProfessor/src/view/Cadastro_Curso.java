package view;
import controller.CursoController;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cadastro_Curso {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	private void limpar() {
		textField.setText(null);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_Curso window = new Cadastro_Curso();
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
	public Cadastro_Curso() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 733, 304);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCadastroDeCurso = new JLabel("   Cadastro de Curso");
		lblCadastroDeCurso.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblCadastroDeCurso.setIcon(new ImageIcon("/home/malik-dev/git/Aplicacao-Gestao_Professor/AplicacaoGestaoProfessor/src/img/study-icon(1).png"));
		lblCadastroDeCurso.setBounds(252, 12, 201, 38);
		frame.getContentPane().add(lblCadastroDeCurso);
		
		JLabel lblNomeDoCurso = new JLabel("Nome do Curso");
		lblNomeDoCurso.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNomeDoCurso.setBounds(83, 95, 120, 38);
		frame.getContentPane().add(lblNomeDoCurso);
		
		textField = new JTextField();
		textField.setBounds(221, 104, 354, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblDuracao = new JLabel("Duracao ");
		lblDuracao.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracao.setBounds(83, 145, 84, 17);
		frame.getContentPane().add(lblDuracao);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1 ano", "2 anos", "3 anos"}));
		comboBox.setBounds(221, 140, 84, 26);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean sucesso;
				try {
					CursoController cursocontroller = new CursoController();
					sucesso = cursocontroller.CadastrarCurso(textField.getText().toString(), comboBox.getSelectedItem().toString());
					
					if(sucesso == true) {
						JOptionPane.showMessageDialog(null,"Introducao de dados com sucesso");
						limpar();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Introducao dos dados invalida");
					}
					
				}catch(Exception p) {
					JOptionPane.showMessageDialog(null,p);
					
				}
			}
		});
		btnNewButton.setBounds(83, 202, 103, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnNewButton_1.setBounds(222, 202, 103, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setBackground(new Color(224, 255, 255));
		btnCancelar.setBounds(371, 202, 103, 27);
		frame.getContentPane().add(btnCancelar);
	}
}
