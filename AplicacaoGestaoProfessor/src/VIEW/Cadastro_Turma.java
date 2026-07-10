package VIEW;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cadastro_Turma {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textTurno;
	private JTextField textAno_Lectivo;
	private JComboBox comboDiretor_Turma;
	private JComboBox comboQualificacaco ;

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
				
			}
		});
		panel_1.add(btnNewButton);
	}

}
