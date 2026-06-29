package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.FormadorController;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class Tela_cadastroProfessor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textApelido;
	private JTextField textEmail;
	private JTextField textContacto;
	private JComboBox comboGenero;
	private JComboBox comboEstadoCivil;
	private JSpinner spinnerSalariohora; 
	private JSpinner spinnerHoras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastroProfessor frame = new Tela_cadastroProfessor();
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
	public Tela_cadastroProfessor() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 613);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(437, 40, 523, 504);
		contentPane.add(panel_1);
		
		textNome = new JTextField();
		textNome.setColumns(10);
		textNome.setBounds(10, 36, 212, 35);
		panel_1.add(textNome);
		
		textApelido = new JTextField();
		textApelido.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textApelido.setColumns(10);
		textApelido.setBounds(10, 130, 212, 35);
		panel_1.add(textApelido);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textEmail.setColumns(10);
		textEmail.setBounds(10, 215, 212, 35);
		panel_1.add(textEmail);
		
		JLabel lblNewLabel_3 = new JLabel("Nome");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 11, 94, 21);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Apelido");
		lblNewLabel_3_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(10, 98, 94, 21);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_5 = new JLabel("E-mail");
		lblNewLabel_3_5.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_5.setBounds(10, 191, 186, 21);
		panel_1.add(lblNewLabel_3_5);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textNome.getText();
				String apelido = textApelido.getText();
				String email = textEmail.getText();
				int contacto = Integer.parseInt(textContacto.getText());
				String genero = comboGenero.getSelectedItem().toString();
				String estadoCivil = comboEstadoCivil.getSelectedItem().toString();
				int valor_hora = spinnerHoras.getComponentCount();
				int hora = spinnerSalariohora.getComponentCount();
				int salario = valor_hora * hora;
				boolean sucesso;
				try {
					FormadorController formadorController = new FormadorController();
        			
        			//Chamada do metodo cadastrar para validar todos os dados
        			sucesso = formadorController.cadastrarFormador(nome, apelido, email, genero, estadoCivil,contacto, salario); 
        			if (sucesso) {
        				JOptionPane.showMessageDialog(null, "Dados guardados com sucesso");
        			}
        			else {
        				JOptionPane.showMessageDialog(null, "Introducao invalida, Tente Novamente");
        			}
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Introduca todos os dados corretamente");
        			ex.printStackTrace();
				}catch(Exception el) {
        			JOptionPane.showMessageDialog(null, "Introduca todo os dados");
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
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();
		    }
		});
		
		JLabel lblNewLabel_3_6 = new JLabel("Genero");
		lblNewLabel_3_6.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_6.setBounds(10, 410, 76, 21);
		panel_1.add(lblNewLabel_3_6);
		
		JLabel lblNewLabel_3_8 = new JLabel("Turma");
		lblNewLabel_3_8.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_8.setBounds(286, 98, 94, 21);
		panel_1.add(lblNewLabel_3_8);
		
		textContacto = new JTextField();
		textContacto.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textContacto.setColumns(10);
		textContacto.setBounds(10, 303, 212, 35);
		panel_1.add(textContacto);
		
		JLabel lblNewLabel_3_6_1 = new JLabel("Contacto ");
		lblNewLabel_3_6_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_6_1.setBounds(10, 274, 94, 21);
		panel_1.add(lblNewLabel_3_6_1);
		
		JLabel lblNewLabel_3_7 = new JLabel("Função Desempenhada");
		lblNewLabel_3_7.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_7.setBounds(288, 193, 165, 21);
		panel_1.add(lblNewLabel_3_7);
		
		JLabel lblNewLabel_3_3 = new JLabel("Horas de Trabalho");
		lblNewLabel_3_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_3.setBounds(288, 276, 140, 21);
		panel_1.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_4 = new JLabel("Salário Hora");
		lblNewLabel_3_4.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_4.setBounds(288, 313, 109, 21);
		panel_1.add(lblNewLabel_3_4);
		
		 spinnerHoras = new JSpinner();
		spinnerHoras.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		spinnerHoras.setBounds(431, 273, 67, 20);
		panel_1.add(spinnerHoras);
		
		spinnerSalariohora = new JSpinner();
		spinnerSalariohora.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		spinnerSalariohora.setBounds(431, 318, 67, 20);
		panel_1.add(spinnerSalariohora);
		
		JLabel lblNewLabel_3_9 = new JLabel("EstadoCivil");
		lblNewLabel_3_9.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_9.setBounds(10, 367, 82, 21);
		panel_1.add(lblNewLabel_3_9);
		
		JLabel lblNewLabel_3_6_2 = new JLabel("Qualificação");
		lblNewLabel_3_6_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_3_6_2.setBounds(286, 11, 94, 21);
		panel_1.add(lblNewLabel_3_6_2);
		
		comboEstadoCivil = new JComboBox();
		comboEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"solteiro", "casado"}));
		comboEstadoCivil.setBounds(110, 364, 107, 26);
		panel_1.add(comboEstadoCivil);
		
		comboGenero = new JComboBox();
		comboGenero.setModel(new DefaultComboBoxModel(new String[] {"masculino", "feminino"}));
		comboGenero.setBounds(104, 407, 92, 26);
		panel_1.add(comboGenero);
		
		JComboBox comboTurma = new JComboBox();
		comboTurma.setBounds(296, 133, 157, 26);
		panel_1.add(comboTurma);
		
		JComboBox comboFuncao = new JComboBox();
		comboFuncao.setBounds(298, 224, 165, 26);
		panel_1.add(comboFuncao);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(288, 44, 165, 26);
		panel_1.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Cadastrar Professor");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 0, 309, 34);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setToolTipText("Maculino\r\nFeminino");
		panel.setBackground(Color.BLUE);
		panel.setBounds(134, 40, 304, 504);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("\r\n");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\scientist-icon (2).png"));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(21, 23, 260, 295);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(" Adicione um novo Professor\r\n");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(0, 329, 299, 36);
		panel.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.BLUE);
		panel_3.setBounds(10, 40, 119, 504);
		contentPane.add(panel_3);
		
		JButton btnNewButton_2 = new JButton("Cadastro");
		btnNewButton_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(10, 32, 104, 30);
		panel_3.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Consulta");
		btnNewButton_2_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_2_1.setBounds(10, 156, 104, 30);
		panel_3.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Actualizar");
		btnNewButton_2_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_2_2.setBounds(10, 290, 104, 30);
		panel_3.add(btnNewButton_2_2);
		
		JButton btnNewButton_2_3 = new JButton("Remover");
		btnNewButton_2_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_2_3.setBounds(10, 431, 104, 30);
		panel_3.add(btnNewButton_2_3);

	}
}
