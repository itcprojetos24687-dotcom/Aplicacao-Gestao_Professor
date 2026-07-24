package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.QualificacaoController;
import model.*;
import controller.*;

public class Tela_cadastroModulo extends JFrame implements OnDadosAlteradosListener {

    private static final long serialVersionUID = 1L;

    private JTextField txtNomeModulo;
    private JTextField txtCargaHoraria;
    private JComboBox<String> comboSemestres;
    private JComboBox<Qualificacao> comboQualificacao;
    private JComboBox<Nivel> comboNivelQualificacao;
    private ArrayList<Qualificacao> qualificacoes;
    private ArrayList<Nivel> niveis;
    private JButton btnSalvar, btnLimpar;
    private int idUser = 0;

    // Paleta de Cores Moderna Clean e Minimalista (Fundo Branco)
    private final Color COLOR_PANEL        = new Color(255, 255, 255); 
    private final Color COLOR_TEXT         = new Color(29, 29, 31); 
    private final Color COLOR_TEXT_MUTED   = new Color(134, 134, 139); 
    private final Color COLOR_FIELD_BG     = new Color(255, 255, 255); 
    private final Color COLOR_FIELD_BORDER = new Color(210, 210, 215); 
    private final Color COLOR_ACCENT       = new Color(0, 122, 255); 
    private final Color COLOR_BUTTON_BG    = new Color(232, 232, 237); 

    public Tela_cadastroModulo() {
        // Uniformização global dos componentes JComboBox no Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.put("ComboBox.background", COLOR_FIELD_BG);
            UIManager.put("ComboBox.foreground", COLOR_TEXT);
            UIManager.put("ComboBox.selectionBackground", COLOR_ACCENT);
            UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        } catch (Exception e) {
            // Fallback silencioso
        }

        setTitle("Cadastro de Módulo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(850, 480);
        setLocationRelativeTo(null);
        setResizable(false);

        EventoCadastro.registrar(this);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                EventoCadastro.remover(Tela_cadastroModulo.this);
            }
        });

        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 15));
        painelPrincipal.setBackground(COLOR_PANEL);
        painelPrincipal.setBorder(new EmptyBorder(25, 25, 20, 25));

        // --- TOPO: Título e Subtítulo Informativo ---
        JPanel panelAcoes = new JPanel(new BorderLayout());
        panelAcoes.setBackground(COLOR_PANEL);

        JLabel lblTitulo = new JLabel("Cadastro de Módulo");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(COLOR_TEXT);
        panelAcoes.add(lblTitulo, BorderLayout.NORTH);

        JLabel lblInfo = new JLabel("Preencha os dados abaixo para registar um novo módulo no sistema.");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(COLOR_TEXT_MUTED);
        panelAcoes.add(lblInfo, BorderLayout.SOUTH);

        painelPrincipal.add(panelAcoes, BorderLayout.NORTH);

        // --- CORPO: Formulário Organizado com GridBagLayout ---
        JPanel panelFormContainer = new JPanel(new GridBagLayout());
        panelFormContainer.setBackground(COLOR_PANEL);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.weightx = 0.5;

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontText  = new Font("Segoe UI", Font.PLAIN, 14);

        // Linha 0: Nome do Módulo vs Carga Horária (Labels)
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblNomeTitle = new JLabel("Nome do Módulo *");
        lblNomeTitle.setFont(fontLabel);
        lblNomeTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblNomeTitle, gbc);

        gbc.gridx = 1;
        JLabel lblCargaTitle = new JLabel("Carga Horária *");
        lblCargaTitle.setFont(fontLabel);
        lblCargaTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblCargaTitle, gbc);

        // Linha 1: Nome do Módulo vs Carga Horária (Inputs)
        gbc.gridx = 0; gbc.gridy = 1;
        txtNomeModulo = new JTextField();
        styleTextField(txtNomeModulo, fontText);
        panelFormContainer.add(txtNomeModulo, gbc);

        gbc.gridx = 1;
        txtCargaHoraria = new JTextField();
        styleTextField(txtCargaHoraria, fontText);
        panelFormContainer.add(txtCargaHoraria, gbc);

        // Linha 2: Semestres vs Qualificação (Labels)
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblSemestreTitle = new JLabel("Semestres *");
        lblSemestreTitle.setFont(fontLabel);
        lblSemestreTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblSemestreTitle, gbc);

        gbc.gridx = 1;
        JLabel lblQualiTitle = new JLabel("Qualificação *");
        lblQualiTitle.setFont(fontLabel);
        lblQualiTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblQualiTitle, gbc);

        // Linha 3: Semestres vs Qualificação (Inputs)
        gbc.gridx = 0; gbc.gridy = 3;
        comboSemestres = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "1º Semestre", "2º Semestre"
        }));
        styleComboBox(comboSemestres, fontText);
        panelFormContainer.add(comboSemestres, gbc);

        gbc.gridx = 1;
        comboQualificacao = new JComboBox<>();
        try {
            QualificacaoController qc = new QualificacaoController();
            qualificacoes = qc.comboQualificacao();
            for(Qualificacao q: qualificacoes) {
                comboQualificacao.addItem(q);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        styleComboBox(comboQualificacao, fontText);
        panelFormContainer.add(comboQualificacao, gbc);

        // Linha 4: Nível da Qualificação (Label ocupando a coluna da esquerda)
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel lblNivelTitle = new JLabel("Nível da Qualificação *");
        lblNivelTitle.setFont(fontLabel);
        lblNivelTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblNivelTitle, gbc);

        // Linha 5: Nível da Qualificação (Input ocupando a coluna da esquerda)
        gbc.gridx = 0; gbc.gridy = 5;
        comboNivelQualificacao = new JComboBox<>();
        styleComboBox(comboNivelQualificacao, fontText);
        panelFormContainer.add(comboNivelQualificacao, gbc);

        // Lógica de atualização dinâmica do nível baseado na Qualificação selecionada
        comboQualificacao.addActionListener(e -> {
            try {
                Qualificacao q = (Qualificacao) comboQualificacao.getSelectedItem();
                comboNivelQualificacao.removeAllItems();
                if (q != null) {
                    Quali_Nivel qn = new Quali_Nivel();
                    niveis = qn.getQualificacao_Nivel(q);
                    for(Nivel n : niveis) {
                        comboNivelQualificacao.addItem(n);
                    }
                }
            } catch(Exception s) {
                s.printStackTrace();
            }
        });

        // Carga inicial do Nível da Qualificação baseado no item padrão selecionado
        if (comboQualificacao.getItemCount() > 0) {
            try {
                Qualificacao qInicial = (Qualificacao) comboQualificacao.getSelectedItem();
                Quali_Nivel qnInicial = new Quali_Nivel();
                niveis = qnInicial.getQualificacao_Nivel(qInicial);
                for(Nivel n : niveis) {
                    comboNivelQualificacao.addItem(n);
                }
            } catch(Exception s) {
                s.printStackTrace();
            }
        }

        painelPrincipal.add(panelFormContainer, BorderLayout.CENTER);

        // --- RODAPÉ: Botões de Ação ---
        JPanel panelBotoesRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        panelBotoesRodape.setBackground(COLOR_PANEL);
        panelBotoesRodape.setBorder(new EmptyBorder(10, 0, 0, 0));

        btnLimpar = new JButton("Limpar");
        styleButton(btnLimpar, COLOR_BUTTON_BG, COLOR_TEXT);
        btnLimpar.addActionListener(e -> limparCampos());

        btnSalvar = new JButton("Guardar Módulo");
        styleButton(btnSalvar, COLOR_ACCENT, Color.WHITE);
        btnSalvar.addActionListener(e -> acaoSalvar());

        panelBotoesRodape.add(btnLimpar);
        panelBotoesRodape.add(btnSalvar);

        painelPrincipal.add(panelBotoesRodape, BorderLayout.SOUTH);

        setContentPane(painelPrincipal);
    }

    // --- MÉTODOS AUXILIARES DE ESTILIZAÇÃO (UI) ---
    private void styleTextField(JTextField field, Font font) {
        field.setFont(font);
        field.setBackground(COLOR_FIELD_BG);
        field.setForeground(COLOR_TEXT);
        field.setCaretColor(COLOR_TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_FIELD_BORDER, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 38));
    }

    private void styleComboBox(JComboBox<?> combo, Font font) {
        combo.setFont(font);
        combo.setBackground(COLOR_FIELD_BG);
        combo.setForeground(COLOR_TEXT);
        combo.setBorder(BorderFactory.createLineBorder(COLOR_FIELD_BORDER, 1));
        combo.setPreferredSize(new Dimension(combo.getPreferredSize().width, 38));
    }

    private void styleButton(JButton button, Color background, Color foreground) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void acaoSalvar() {
        boolean sucesso;
        try {
            String nome      = txtNomeModulo.getText().trim();
            String cargaStr  = txtCargaHoraria.getText().trim();
            
            if (nome.isEmpty() || cargaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha os campos obrigatórios (*).", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int cargaHoraria = Integer.parseInt(cargaStr);
            if (cargaHoraria <= 0) {
                JOptionPane.showMessageDialog(this, "A carga horária deve ser maior que zero.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String semestre = (String) comboSemestres.getSelectedItem();
            Qualificacao q = (Qualificacao) comboQualificacao.getSelectedItem();
            Nivel n = (Nivel) comboNivelQualificacao.getSelectedItem();
            
            ModuloController mc = new ModuloController();
            
            if(idUser == 0) {
                sucesso = mc.cadastrarModulo(nome, cargaHoraria, semestre, q, n);
                if(sucesso) {
                    JOptionPane.showMessageDialog(this, "Módulo guardado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao cadastrar o módulo!", "Falhou", JOptionPane.ERROR_MESSAGE);
                    limparCampos();
                }
            } else {
                sucesso = mc.atualizarModulo(nome, cargaHoraria, idUser, q, n, semestre);
                if(sucesso) {
                    JOptionPane.showMessageDialog(this, "Módulo atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao atualizar o módulo!", "Falhou", JOptionPane.ERROR_MESSAGE);
                    limparCampos();
                }
            }
        } catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Insira um número válido para a Carga Horária.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        txtNomeModulo.setText("");
        txtCargaHoraria.setText("");
        if (comboSemestres.getItemCount() > 0) comboSemestres.setSelectedIndex(0);
        if (comboQualificacao.getItemCount() > 0) comboQualificacao.setSelectedIndex(0);
        if (comboNivelQualificacao.getItemCount() > 0) comboNivelQualificacao.setSelectedIndex(0);
    }

    public void buscarModulo(Integer codigo, String nome, Integer carga_horaria, String semestre, Qualificacao q, Nivel nivel) {
        idUser = codigo;
        txtNomeModulo.setText(nome);
        txtCargaHoraria.setText(String.valueOf(carga_horaria));
        
        for(int i = 0; i < comboSemestres.getItemCount(); i++) {
            if(comboSemestres.getItemAt(i).equals(semestre)) {
                comboSemestres.setSelectedIndex(i);
                break;
            }
        }
        for(int i = 0; i < comboQualificacao.getItemCount(); i++) {
            if(comboQualificacao.getItemAt(i).equals(q)) {
                comboQualificacao.setSelectedIndex(i);
                break;
            }
        }
        for(int i = 0; i < comboNivelQualificacao.getItemCount(); i++) {
            if(comboNivelQualificacao.getItemAt(i).equals(nivel)) {
                comboNivelQualificacao.setSelectedIndex(i);
                break;
            }
        }
    }

    @Override
    public void ListarDadosAoAlterar() {
        try {
            QualificacaoController qc = new QualificacaoController();
            qualificacoes = qc.comboQualificacao();
            comboQualificacao.removeAllItems();
            for(Qualificacao q: qualificacoes) {
                comboQualificacao.addItem(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}