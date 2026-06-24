package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Formulario_ProfessorTela extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtCadatroProfessores;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;

    /**
     * Create the frame.
     */
    public Formulario_ProfessorTela() {

        setTitle("Cadastro de Professores");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("Guardar");
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

        JLabel lblNewLabel_3 = new JLabel("Curso");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_3.setBounds(32, 201, 46, 14);
        getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Turma");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_4.setBounds(32, 235, 46, 14);
        getContentPane().add(lblNewLabel_4);

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

        textField = new JTextField();
        textField.setBounds(91, 93, 134, 20);
        getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(91, 125, 134, 20);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(91, 161, 134, 20);
        getContentPane().add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(91, 199, 134, 20);
        getContentPane().add(textField_3);
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setBounds(91, 233, 134, 20);
        getContentPane().add(textField_4);
        textField_4.setColumns(10);

        JSpinner spinner_1 = new JSpinner();
        spinner_1.setBounds(195, 342, 60, 20);
        getContentPane().add(spinner_1);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(
                new String[] {"Masculino", "Feminino"}));
        comboBox.setBounds(91, 267, 134, 22);
        getContentPane().add(comboBox);

        JComboBox<String> comboBox_1 = new JComboBox<String>();
        comboBox_1.setModel(new DefaultComboBoxModel<String>(
                new String[] {"Casado", "Solteiro"}));
        comboBox_1.setBounds(123, 305, 102, 22);
        getContentPane().add(comboBox_1);

        JLabel lblNewLabel_8 = new JLabel("Valor Horas");
        lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_8.setBounds(35, 384, 78, 14);
        getContentPane().add(lblNewLabel_8);

        textField_5 = new JTextField();
        textField_5.setBounds(123, 382, 102, 20);
        getContentPane().add(textField_5);
        textField_5.setColumns(10);

        setVisible(true);
    }
}