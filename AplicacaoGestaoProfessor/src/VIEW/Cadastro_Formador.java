package VIEW;

import model.*;
import controller.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

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
    private JCheckBox chckbxDiretor;
    private JCheckBox chckbxCoordenador;
    private int idUser;

    private Tela_Principal tela_principal;
    private OnDadosAlteradosListener listener;

    // Paleta de Cores Moderna Clean (Fundo Branco)
    private final Color COLOR_BG = new Color(245, 245, 247); 
    private final Color COLOR_PANEL = new Color(255, 255, 255); 
    private final Color COLOR_TEXT = new Color(29, 29, 31); 
    private final Color COLOR_TEXT_MUTED = new Color(134, 134, 139); 
    private final Color COLOR_FIELD_BG = new Color(255, 255, 255); 
    private final Color COLOR_FIELD_BORDER = new Color(210, 210, 215); 
    private final Color COLOR_ACCENT = new Color(0, 122, 255); 
    private final Color COLOR_BUTTON_BG = new Color(232, 232, 237); 

    /**
     * Create the frame.
     */
    public Cadastro_Formador() {
        // Uniformização visual dos componentes Swing nativos
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.put("ComboBox.background", COLOR_FIELD_BG);
            UIManager.put("ComboBox.foreground", COLOR_TEXT);
            UIManager.put("ComboBox.selectionBackground", COLOR_ACCENT);
            UIManager.put("ComboBox.selectionForeground", Color.WHITE);
            UIManager.put("CheckBox.background", COLOR_PANEL);
            UIManager.put("CheckBox.foreground", COLOR_TEXT);
        } catch (Exception e) {
            // Fallback silencioso
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 680, 520);
        setMinimumSize(new Dimension(680, 520));
        setLocationRelativeTo(null);
        
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(COLOR_BG);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        
        // --- TOPO: Título ---
        JLabel lblCadastroFormador = new JLabel("Cadastro de Formador");
        lblCadastroFormador.setHorizontalAlignment(SwingConstants.CENTER);
        lblCadastroFormador.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblCadastroFormador.setForeground(COLOR_TEXT);
        lblCadastroFormador.setBorder(new EmptyBorder(0, 0, 15, 0));
        contentPane.add(lblCadastroFormador, BorderLayout.NORTH);
        
        // --- CORPO: Painel de Formulário GridBag ---
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(COLOR_PANEL);
        panelPrincipal.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(225, 225, 230), 1),
            new EmptyBorder(15, 20, 15, 20)
        ));
        contentPane.add(panelPrincipal, BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.weightx = 0.5;

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontField = new Font("Segoe UI", Font.PLAIN, 14);
        
        // Linha 0: Nome vs Email
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lbNome = new JLabel("Nome");
        lbNome.setFont(fontLabel);
        lbNome.setForeground(COLOR_TEXT_MUTED);
        panelPrincipal.add(lbNome, gbc);
        
        gbc.gridx = 1;
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(fontLabel);
        lblEmail.setForeground(COLOR_TEXT_MUTED);
        panelPrincipal.add(lblEmail, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        textNome = new JTextField();
        styleTextField(textNome, fontField);
        panelPrincipal.add(textNome, gbc);
        
        gbc.gridx = 1;
        textEmail = new JTextField();
        styleTextField(textEmail, fontField);
        panelPrincipal.add(textEmail, gbc);
        
        // Linha 2: Apelido vs Valor por Hora
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblApelido = new JLabel("Apelido");
        lblApelido.setFont(fontLabel);
        lblApelido.setForeground(COLOR_TEXT_MUTED);
        panelPrincipal.add(lblApelido, gbc);
        
        gbc.gridx = 1;
        JLabel lblValorPorHora = new JLabel("Valor por Hora");
        lblValorPorHora.setFont(fontLabel);
        lblValorPorHora.setForeground(COLOR_TEXT_MUTED);
        panelPrincipal.add(lblValorPorHora, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        textApelido = new JTextField();
        styleTextField(textApelido, fontField);
        panelPrincipal.add(textApelido, gbc);
        
        gbc.gridx = 1;
        textValor_Hora = new JTextField();
        styleTextField(textValor_Hora, fontField);
        panelPrincipal.add(textValor_Hora, gbc);
        
        // Linha 4: Contacto vs Horas por Mês
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel lbContacto = new JLabel("Contacto");
        lbContacto.setFont(fontLabel);
        lbContacto.setForeground(COLOR_TEXT_MUTED);
        panelPrincipal.add(lbContacto, gbc);
        
        gbc.gridx = 1;
        JLabel lbHoras_Mes = new JLabel("Horas por Mês");
        lbHoras_Mes.setFont(fontLabel);
        lbHoras_Mes.setForeground(COLOR_TEXT_MUTED);
        panelPrincipal.add(lbHoras_Mes, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        textContacto = new JTextField();
        styleTextField(textContacto, fontField);
        panelPrincipal.add(textContacto, gbc);
        
        gbc.gridx = 1;
        textHoras_Mes = new JTextField();
        styleTextField(textHoras_Mes, fontField);
        panelPrincipal.add(textHoras_Mes, gbc);
        
        // Linha 6: Gênero vs Estado Civil
        gbc.gridx = 0; gbc.gridy = 6;
        JLabel lblGenero = new JLabel("Gênero");
        lblGenero.setFont(fontLabel);
        lblGenero.setForeground(COLOR_TEXT_MUTED);
        panelPrincipal.add(lblGenero, gbc);
        
        gbc.gridx = 1;
        JLabel lblEstadoCivil = new JLabel("Estado Civil");
        lblEstadoCivil.setFont(fontLabel);
        lblEstadoCivil.setForeground(COLOR_TEXT_MUTED);
        panelPrincipal.add(lblEstadoCivil, gbc);
        
        gbc.gridx = 0; gbc.gridy = 7;
        comboGenero = new JComboBox();
        comboGenero.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
        styleComboBox(comboGenero, fontField);
        panelPrincipal.add(comboGenero, gbc);
        
        gbc.gridx = 1;
        comboEstadoCivil = new JComboBox();
        comboEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"Solteiro", "Casado"}));
        styleComboBox(comboEstadoCivil, fontField);
        panelPrincipal.add(comboEstadoCivil, gbc);
        
        // Linha 8: Cargo / Checkboxes internos organizados em linha
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        gbc.insets = new Insets(12, 10, 4, 10);
        JPanel panelCheckboxes = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelCheckboxes.setBackground(COLOR_PANEL);
        
        chckbxCoordenador = new JCheckBox("Coordenador");
        chckbxCoordenador.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelCheckboxes.add(chckbxCoordenador);
        
        chckbxDiretor = new JCheckBox("Diretor de Turma");
        chckbxDiretor.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelCheckboxes.add(chckbxDiretor);
        
        panelPrincipal.add(panelCheckboxes, gbc);
        
        // --- RODAPÉ: Botões de Ação ---
        JPanel panelFoater = new JPanel();
        panelFoater.setBackground(COLOR_BG);
        panelFoater.setBorder(new EmptyBorder(15, 0, 0, 0));
        panelFoater.setLayout(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        contentPane.add(panelFoater, BorderLayout.SOUTH);
        
        JButton btnLimpar = new JButton("Limpar");
        styleButton(btnLimpar, COLOR_BUTTON_BG, COLOR_TEXT);
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });
        panelFoater.add(btnLimpar);
        
        JButton btnCancelar = new JButton("Cancelar");
        styleButton(btnCancelar, COLOR_BUTTON_BG, COLOR_TEXT);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelFoater.add(btnCancelar);
        
        JButton btnSalvar = new JButton("Guardar");
        styleButton(btnSalvar, COLOR_ACCENT, Color.WHITE);
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
                        sucesso = fc.cadastrarFormador(nome, apelido, email, genero, estadoCivil, contacto, valor_hora, horas_mes, salario, isDiretor, isCoordenador);
                        if(sucesso) {
                            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
                            tela_principal = Tela_Principal.getInstancia();
                            tela_principal.listarFormadores();
                            if (isCoordenador) {
                                EventoCadastro.notificar();
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Falha ao cadastrar");
                        }
                    }
                    else {
                        sucesso = fc.atualizarFormador(idUser, nome, apelido, email, genero, estadoCivil, contacto, valor_hora, horas_mes, salario, isDiretor, isCoordenador);
                        if (sucesso){
                            idUser = 0;
                            JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
                            tela_principal = Tela_Principal.getInstancia();
                            tela_principal.listarFormadores();
                            if (isCoordenador) {
                                EventoCadastro.notificar();
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Falha ao atualizar");
                        }
                    }
                } catch(Exception s) {
                    s.printStackTrace();
                }
            }
        });
        panelFoater.add(btnSalvar);
    }
    
    // --- MÉTODOS AUXILIARES DE ESTILIZAÇÃO (UI) ---
    private void styleTextField(JTextField field, Font font) {
        field.setFont(font);
        field.setBackground(COLOR_FIELD_BG);
        field.setForeground(COLOR_TEXT);
        field.setCaretColor(COLOR_TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_FIELD_BORDER, 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 36));
    }

    private void styleComboBox(JComboBox combo, Font font) {
        combo.setFont(font);
        combo.setBackground(COLOR_FIELD_BG);
        combo.setForeground(COLOR_TEXT);
        combo.setBorder(BorderFactory.createLineBorder(COLOR_FIELD_BORDER, 1));
        combo.setPreferredSize(new Dimension(combo.getPreferredSize().width, 36));
    }

    private void styleButton(JButton button, Color background, Color foreground) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 22, 8, 22));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    public void buscarFormador(int codigo, String nome, String apelido, String email, String genero, String estadoCivil, int contacto, int valor_horas, int horas_mes, double salario) {
        idUser = codigo;
        textNome.setText(nome);
        textApelido.setText(apelido);
        textContacto.setText(String.valueOf(contacto));
        textEmail.setText(email);
        
        for(int i = 0; i < comboGenero.getItemCount(); i++) {
            if(comboGenero.getItemAt(i).equals(genero)) {
                comboGenero.setSelectedIndex(i);
                break;
            }
        }
        
        for(int i = 0; i < comboEstadoCivil.getItemCount(); i++) {
            if(comboEstadoCivil.getItemAt(i).equals(estadoCivil)) {
                comboEstadoCivil.setSelectedIndex(i);
                break;
            }
        }

        try {
            FormadorController fc = new FormadorController();
            boolean [] status = fc.getStatusFormador(codigo);
            chckbxDiretor.setSelected(status[0]);
            chckbxCoordenador.setSelected(status[1]);
        } catch(Exception e) {
            e.printStackTrace();
            chckbxDiretor.setSelected(false);
            chckbxCoordenador.setSelected(false);
        }

        textValor_Hora.setText(String.valueOf(valor_horas));
        textHoras_Mes.setText(String.valueOf(horas_mes));
    }
    
    private void limpar() {
        textNome.setText("");
        textApelido.setText("");
        textContacto.setText("");
        textEmail.setText("");
        textValor_Hora.setText("");
        textHoras_Mes.setText("");
        comboGenero.setSelectedIndex(0);
        comboEstadoCivil.setSelectedIndex(0);
        chckbxDiretor.setSelected(false);
        chckbxCoordenador.setSelected(false);
    }
}