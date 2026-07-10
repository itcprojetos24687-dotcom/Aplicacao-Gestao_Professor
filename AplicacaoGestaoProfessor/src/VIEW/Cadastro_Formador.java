package VIEW;
import model.*;
import controller.*;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Cadastro_Formador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textApelido;
	private JTextField textContacto;
	private JTextField textEmail;
	private JTextField textValor_Hora;
	private JTextField textHoras_Mes;
	private JComboBox comboGenero;
	private JComboBox comboEstadoCivil;
	private int idUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_Formador frame = new Cadastro_Formador();
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
	public Cadastro_Formador() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(640,440));
		contentPane.setLayout(null);
		
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(12, 12, 619, 367);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel lbNome = new JLabel("Nome");
		lbNome.setFont(new Font("Dialog", Font.BOLD, 16));
		lbNome.setBounds(37, 46, 84, 30);
		panelPrincipal.add(lbNome);
		
		textNome = new JTextField();
		textNome.setFont(new Font("Dialog", Font.PLAIN, 17));
		textNome.setBounds(37, 75, 193, 38);
		panelPrincipal.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblCadastroFormador = new JLabel("Cadastro de Formador");
		lblCadastroFormador.setFont(new Font("Montserrat", Font.BOLD, 20));
		lblCadastroFormador.setBounds(220, 0, 253, 38);
		panelPrincipal.add(lblCadastroFormador);
		
		JLabel lblApelido = new JLabel("Apelido");
		lblApelido.setFont(new Font("Dialog", Font.BOLD, 16));
		lblApelido.setBounds(37, 125, 97, 17);
		panelPrincipal.add(lblApelido);
		
		textApelido = new JTextField();
		textApelido.setFont(new Font("Dialog", Font.PLAIN, 17));
		textApelido.setBounds(37, 154, 193, 38);
		panelPrincipal.add(textApelido);
		textApelido.setColumns(10);
		
		JLabel lbContacto = new JLabel("Contacto\n");
		lbContacto.setFont(new Font("Dialog", Font.BOLD, 17));
		lbContacto.setBounds(37, 204, 111, 25);
		panelPrincipal.add(lbContacto);
		
		textContacto = new JTextField();
		textContacto.setFont(new Font("Dialog", Font.PLAIN, 17));
		textContacto.setBounds(37, 230, 193, 38);
		panelPrincipal.add(textContacto);
		textContacto.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 17));
		lblEmail.setBounds(317, 53, 65, 17);
		panelPrincipal.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Dialog", Font.PLAIN, 17));
		textEmail.setColumns(10);
		textEmail.setBounds(317, 75, 220, 38);
		panelPrincipal.add(textEmail);
		
		JLabel lblValorPorHora = new JLabel("Valor por Hora");
		lblValorPorHora.setFont(new Font("Dialog", Font.BOLD, 17));
		lblValorPorHora.setBounds(317, 144, 142, 30);
		panelPrincipal.add(lblValorPorHora);
		
		textValor_Hora = new JTextField();
		textValor_Hora.setFont(new Font("Dialog", Font.PLAIN, 16));
		textValor_Hora.setBounds(460, 144, 70, 32);
		panelPrincipal.add(textValor_Hora);
		textValor_Hora.setColumns(10);
		
		JLabel lbHoras_Mes = new JLabel("Horas por mes");
		lbHoras_Mes.setFont(new Font("Dialog", Font.BOLD, 17));
		lbHoras_Mes.setBounds(317, 204, 133, 25);
		panelPrincipal.add(lbHoras_Mes);
		
		textHoras_Mes = new JTextField();
		textHoras_Mes.setFont(new Font("Dialog", Font.PLAIN, 16));
		textHoras_Mes.setBounds(468, 202, 70, 30);
		panelPrincipal.add(textHoras_Mes);
		textHoras_Mes.setColumns(10);
		
		JCheckBox chckbxCoordenador = new JCheckBox("Coordenador");
		chckbxCoordenador.setFont(new Font("Dialog", Font.BOLD, 15));
		chckbxCoordenador.setBounds(284, 267, 142, 25);
		panelPrincipal.add(chckbxCoordenador);
		
		JCheckBox chckbxDiretor = new JCheckBox("Diretor de Turma");
		chckbxDiretor.setFont(new Font("Dialog", Font.BOLD, 15));
		chckbxDiretor.setBounds(438, 267, 173, 25);
		panelPrincipal.add(chckbxDiretor);
		
		JLabel lblGenero = new JLabel("Genero\n");
		lblGenero.setFont(new Font("Dialog", Font.BOLD, 17));
		lblGenero.setBounds(37, 280, 111, 25);
		panelPrincipal.add(lblGenero);
		
		JLabel lblEstadoCivil = new JLabel("Estado Civil");
		lblEstadoCivil.setFont(new Font("Dialog", Font.BOLD, 17));
		lblEstadoCivil.setBounds(299, 314, 111, 30);
		panelPrincipal.add(lblEstadoCivil);
		
		comboEstadoCivil = new JComboBox();
		comboEstadoCivil.setFont(new Font("Dialog", Font.BOLD, 15));
		comboEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"Solteiro", "Casado"}));
		comboEstadoCivil.setBounds(414, 317, 152, 26);
		panelPrincipal.add(comboEstadoCivil);
		
		comboGenero = new JComboBox();
		comboGenero.setFont(new Font("Dialog", Font.BOLD, 15));
		comboGenero.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
		comboGenero.setBounds(37, 317, 142, 26);
		panelPrincipal.add(comboGenero);
		
		JPanel panelFoater = new JPanel();
		panelFoater.setBounds(12, 391, 619, 38);
		contentPane.add(panelFoater);
		panelFoater.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setPreferredSize(new Dimension(100,30));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		panelFoater.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setPreferredSize(new Dimension(100,30));
		panelFoater.add(btnCancelar);
		
		JButton btnSalvar = new JButton("Guardar");
		btnSalvar.setPreferredSize(new Dimension(100,30));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textNome.getText();
				String apelido = textApelido.getText();
				int contacto = Integer.parseInt(textContacto.getText());
				String email = textEmail.getText();
				String genero = comboGenero.getSelectedItem().toString();
				String estadoCivil = comboEstadoCivil.getSelectedItem().toString();
				int valor_hora = Integer.parseInt(textValor_Hora.getText());
				int horas_mes = Integer.parseInt(textHoras_Mes.getText());
				boolean isDiretor = chckbxDiretor.isSelected();
				boolean isCoordenador = chckbxCoordenador.isSelected();
				int salario = valor_hora * horas_mes;
				boolean sucesso;
				try {
					FormadorController fc = new FormadorController();
					if(idUser == 0) {
						sucesso = fc.cadastrarFormador(nome, apelido, email, genero, estadoCivil, contacto, salario, isDiretor,isCoordenador);
						new Tela_Principal(Seccao.obterUtilizador()).listar();
						if(sucesso) {
							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
						}
						else {
							JOptionPane.showMessageDialog(null,"Falha ao cadastrar");
						}
					}
					else {
						sucesso = fc.atualizarFormador(idUser, nome, apelido, email, genero, estadoCivil, contacto, salario);
						new Tela_Principal(Seccao.obterUtilizador()).listar();
						if (sucesso){
							idUser = 0;
							JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
						}
						else {
							JOptionPane.showMessageDialog(null, "Falha ao atualizar");
						}
					}
				}catch(Exception s) {
					s.printStackTrace();
				}
			}
		});
		panelFoater.add(btnSalvar);

	}
	public void buscarFormador(int codigo, String nome, String apelido, String email,String genero,String estadoCivil, int contacto, int salario) {
		idUser = codigo;
		textNome.setText(nome);
		textApelido.setText(apelido);
		textContacto.setText(String.valueOf(contacto));
		textEmail.setText(email);
		for(int i = 0; i <comboGenero.getItemCount(); i++) {
			if(comboGenero.getItemAt(i).equals(genero)) {
				comboGenero.setSelectedIndex(i);
			}
		}
		for(int i = 0; i <comboEstadoCivil.getItemCount(); i++) {
			if(comboEstadoCivil.getItemAt(i).equals(estadoCivil)) {
				comboEstadoCivil.setSelectedIndex(i);
			}
		}
		
	}
	private void limpar() {
		textNome.setText("");
		textApelido.setText("");
		textContacto.setText("");
		textEmail.setText("");
		textValor_Hora.setText("");
		textHoras_Mes.setText("");
	}
	
}
