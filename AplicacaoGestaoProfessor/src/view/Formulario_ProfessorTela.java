package view;
import controller.FormadorController;
import dao.ExceptionDao;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Formulario_ProfessorTela extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtCadatroProfessores;
    private JTextField textNome;
    private JTextField textApelido;
    private JTextField textContacto;
    private JTextField textEmail;
    private JTextField textValor_horas;
    JSpinner spinHoras;
    JComboBox<String> comboGenero;
    JComboBox<String> comboCivil;

    /**
     * Create the frame.
     */
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulario_ProfessorTela frame = new Formulario_ProfessorTela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    
    public Formulario_ProfessorTela() {

        setTitle("Cadastro de Professores");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("Guardar");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		boolean sucesso;
        		try {
        			String nome = textNome.getText();
        			String apelido =textApelido.getText();
        			int contacto = Integer.parseInt(textContacto.getText());
        			String email = textEmail.getText();
        			String genero = comboGenero.getSelectedItem().toString();
        			String estadoCivil = comboCivil.getSelectedItem().toString();
        			int horas = spinHoras.getComponentCount();
        			int valor = Integer.parseInt(textValor_horas.getText());
        			double salario = valor * horas;
        			
        			// instancia o controller
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
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnNewButton.setBounds(573, 463, 89, 23);
        getContentPane().add(btnNewButton);

        txtCadatroProfessores = new JTextField();
        txtCadatroProfessores.setFont(new Font("Times New Roman", Font.BOLD, 20));
        txtCadatroProfessores.setText("Cadastro Professores");
        txtCadatroProfessores.setBounds(224, 28, 184, 30);
        getContentPane().add(txtCadatroProfessores);
        txtCadatroProfessores.setColumns(10);

        JLabel lblNewLabel = new JLabel("Nome");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel.setBounds(32, 95, 49, 14);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Apelido");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_1.setBounds(32, 127, 49, 14);
        getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Contacto");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_2.setBounds(31, 163, 61, 14);
        getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("email");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_3.setBounds(32, 201, 46, 14);
        getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_5 = new JLabel("Genero");
        lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_5.setBounds(32, 271, 49, 14);
        getContentPane().add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Estado Civil");
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_6.setBounds(35, 309, 78, 14);
        getContentPane().add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Horas Trabalho");
        lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_7.setBounds(32, 344, 101, 14);
        getContentPane().add(lblNewLabel_7);

        textNome = new JTextField();
        textNome.setBounds(91, 93, 134, 20);
        getContentPane().add(textNome);
        textNome.setColumns(10);

        textApelido = new JTextField();
        textApelido.setBounds(91, 125, 134, 20);
        getContentPane().add(textApelido);
        textApelido.setColumns(10);

        textContacto = new JTextField();
        textContacto.setBounds(91, 161, 134, 20);
        getContentPane().add(textContacto);
        textContacto.setColumns(10);

        textEmail = new JTextField();
        textEmail.setBounds(91, 199, 134, 20);
        getContentPane().add(textEmail);
        textEmail.setColumns(10);

        spinHoras = new JSpinner();
        spinHoras.setBounds(195, 342, 60, 20);
        getContentPane().add(spinHoras);

        comboGenero = new JComboBox<String>();
        comboGenero.setModel(new DefaultComboBoxModel<String>(
                new String[] {"Masculino", "Feminino"}));
        comboGenero.setBounds(91, 267, 134, 22);
        getContentPane().add(comboGenero);

        comboCivil = new JComboBox<String>();
        comboCivil.setModel(new DefaultComboBoxModel<String>(
                new String[] {"Casado", "Solteiro"}));
        comboCivil.setBounds(123, 305, 102, 22);
        getContentPane().add(comboCivil);

        JLabel lblNewLabel_8 = new JLabel("Valor Horas");
        lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_8.setBounds(35, 384, 78, 14);
        getContentPane().add(lblNewLabel_8);

        textValor_horas = new JTextField();
        textValor_horas.setBounds(123, 382, 102, 20);
        getContentPane().add(textValor_horas);
        textValor_horas.setColumns(10);

        setVisible(true);
    }
}