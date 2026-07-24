package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.Diretor_TurmaController;
import controller.Quali_NivelController;
import controller.QualificacaoController;
import controller.TurmaController;
import model.Diretor_Turma;
import model.Nivel;
import model.Qualificacao;

public class Cadastro_Turma {

    public JFrame frame;
    private JTextField textNome;
    private JSpinner spinnerAnoLectivo;
    private JComboBox comboDiretor_Turma;
    private JComboBox comboQualificacaco;
    private JComboBox comboTurno;
    private JComboBox comboNivel;
    private ArrayList<Nivel> niveis;
    private ArrayList<Qualificacao> qualificacoes;
    private ArrayList<Diretor_Turma> diretor_turma;
    private int idUser = 0;

    private Tela_Principal tela_principal;
    private OnDadosAlteradosListener listener;

    // Definição da nova paleta de cores Clara (White Modern)
    private final Color COLOR_BG = new Color(245, 245, 247); // Cinza bem claro para o fundo externo
    private final Color COLOR_PANEL = new Color(255, 255, 255); // Fundo do formulário puramente branco
    private final Color COLOR_TEXT = new Color(29, 29, 31); // Texto principal escuro
    private final Color COLOR_TEXT_MUTED = new Color(134, 134, 139); // Labels secundárias em cinza neutro
    private final Color COLOR_FIELD_BG = new Color(255, 255, 255); // Fundo dos inputs branco
    private final Color COLOR_FIELD_BORDER = new Color(210, 210, 215); // Borda suave para os inputs
    private final Color COLOR_ACCENT = new Color(0, 122, 255); // Azul moderno para o botão principal
    private final Color COLOR_BUTTON_BG = new Color(232, 232, 237); // Botões secundários em cinza claro

    public Cadastro_Turma() {
        // Ajuste global para harmonizar componentes nativos do Swing com o tema claro
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.put("ComboBox.background", COLOR_FIELD_BG);
            UIManager.put("ComboBox.foreground", COLOR_TEXT);
            UIManager.put("ComboBox.selectionBackground", COLOR_ACCENT);
            UIManager.put("ComboBox.selectionForeground", Color.WHITE);
            UIManager.put("Spinner.background", COLOR_FIELD_BG);
            UIManager.put("Spinner.foreground", COLOR_TEXT);
        } catch (Exception e) {
            // Fallback silencioso
        }
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Gestão de Turmas");
        frame.setBounds(100, 100, 650, 480);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(650, 480));
        
        // Base da janela principal
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBackground(COLOR_BG);
        mainContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.setContentPane(mainContainer);

        // --- TOPO: Cabeçalho ---
        JLabel lblCadastroDeTurma = new JLabel("Cadastro de Turma");
        lblCadastroDeTurma.setHorizontalAlignment(SwingConstants.CENTER);
        lblCadastroDeTurma.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblCadastroDeTurma.setForeground(COLOR_TEXT);
        lblCadastroDeTurma.setBorder(new EmptyBorder(0, 0, 15, 0));
        mainContainer.add(lblCadastroDeTurma, BorderLayout.NORTH);

        // --- CORPO: Formulário Responsivo ---
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(COLOR_PANEL);
        panelForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(225, 225, 230), 1),
            new EmptyBorder(20, 25, 20, 25)
        ));
        mainContainer.add(panelForm, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.weightx = 0.5;

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontField = new Font("Segoe UI", Font.PLAIN, 14);

        // Coluna 1: Nome
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblNome = new JLabel("Nome da Turma");
        lblNome.setFont(fontLabel);
        lblNome.setForeground(COLOR_TEXT_MUTED);
        panelForm.add(lblNome, gbc);

        gbc.gridy = 1;
        textNome = new JTextField();
        styleTextField(textNome, fontField);
        panelForm.add(textNome, gbc);

        // Coluna 2: Diretor de Turma
        gbc.gridx = 1; gbc.gridy = 0;
        JLabel lblNewLabel = new JLabel("Diretor de Turma");
        lblNewLabel.setFont(fontLabel);
        lblNewLabel.setForeground(COLOR_TEXT_MUTED);
        panelForm.add(lblNewLabel, gbc);

        gbc.gridy = 1;
        comboDiretor_Turma = new JComboBox();
        styleComboBox(comboDiretor_Turma, fontField);
        panelForm.add(comboDiretor_Turma, gbc);

        // População Diretor
        try {
            Diretor_TurmaController dc = new Diretor_TurmaController();
            diretor_turma = dc.comboDiretor_Turma();
            for (Diretor_Turma dt : diretor_turma) {
                comboDiretor_Turma.addItem(dt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Coluna 1: Turno
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblTurno = new JLabel("Turno");
        lblTurno.setFont(fontLabel);
        lblTurno.setForeground(COLOR_TEXT_MUTED);
        panelForm.add(lblTurno, gbc);

        gbc.gridy = 3;
        comboTurno = new JComboBox();
        comboTurno.setModel(new DefaultComboBoxModel(new String[]{"Manha", "Tarde", "Noite"}));
        styleComboBox(comboTurno, fontField);
        panelForm.add(comboTurno, gbc);

        // Coluna 2: Qualificação
        gbc.gridx = 1; gbc.gridy = 2;
        JLabel lblQualificacao = new JLabel("Qualificação");
        lblQualificacao.setFont(fontLabel);
        lblQualificacao.setForeground(COLOR_TEXT_MUTED);
        panelForm.add(lblQualificacao, gbc);

        gbc.gridy = 3;
        comboQualificacaco = new JComboBox();
        styleComboBox(comboQualificacaco, fontField);
        panelForm.add(comboQualificacaco, gbc);

        // População Qualificação
        try {
            QualificacaoController qc = new QualificacaoController();
            qualificacoes = qc.comboQualificacao();
            for (Qualificacao q : qualificacoes) {
                comboQualificacaco.addItem(q);
            }
        } catch (Exception s) {
            s.printStackTrace();
        }

        // Coluna 1: Ano Lectivo
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel lblAnoLectivo = new JLabel("Ano Lectivo");
        lblAnoLectivo.setFont(fontLabel);
        lblAnoLectivo.setForeground(COLOR_TEXT_MUTED);
        panelForm.add(lblAnoLectivo, gbc);

        gbc.gridy = 5;
        Date hoje = new Date();
        SpinnerDateModel modelAno = new SpinnerDateModel(hoje, null, null, Calendar.YEAR);
        spinnerAnoLectivo = new JSpinner(modelAno);
        JSpinner.DateEditor editorAno = new JSpinner.DateEditor(spinnerAnoLectivo, "yyyy");
        spinnerAnoLectivo.setEditor(editorAno);
        spinnerAnoLectivo.setFont(fontField);
        spinnerAnoLectivo.setBorder(BorderFactory.createLineBorder(COLOR_FIELD_BORDER, 1));
        
        // Cores internas do Spinner
        editorAno.getTextField().setBackground(COLOR_FIELD_BG);
        editorAno.getTextField().setForeground(COLOR_TEXT);
        editorAno.getTextField().setCaretColor(COLOR_TEXT);
        panelForm.add(spinnerAnoLectivo, gbc);

        // Coluna 2: Nível
        gbc.gridx = 1; gbc.gridy = 4;
        JLabel lblNivel = new JLabel("Nível");
        lblNivel.setFont(fontLabel);
        lblNivel.setForeground(COLOR_TEXT_MUTED);
        panelForm.add(lblNivel, gbc);

        gbc.gridy = 5;
        comboNivel = new JComboBox();
        styleComboBox(comboNivel, fontField);
        panelForm.add(comboNivel, gbc);

        // Ação dependente da Qualificação
        comboQualificacaco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Qualificacao q = (Qualificacao) comboQualificacaco.getSelectedItem();
                    comboNivel.removeAllItems();
                    if (q != null) {
                        Quali_NivelController qn = new Quali_NivelController();
                        niveis = qn.getQualificacao_Nivel(q);
                        for (Nivel n : niveis) {
                            comboNivel.addItem(n);
                        }
                    }
                } catch (Exception s) {
                    s.printStackTrace();
                }
            }
        });

        // --- RODAPÉ: Botões de Ação ---
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        panelBotoes.setBackground(COLOR_BG);
        panelBotoes.setBorder(new EmptyBorder(20, 0, 0, 0));
        mainContainer.add(panelBotoes, BorderLayout.SOUTH);

        JButton btnLimpar = new JButton("Limpar");
        styleButton(btnLimpar, COLOR_BUTTON_BG, COLOR_TEXT);
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });
        panelBotoes.add(btnLimpar);

        JButton btnCancelar = new JButton("Cancelar");
        styleButton(btnCancelar, COLOR_BUTTON_BG, COLOR_TEXT);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panelBotoes.add(btnCancelar);

        JButton btnGuardar = new JButton("Guardar");
        styleButton(btnGuardar, COLOR_ACCENT, Color.WHITE);
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = textNome.getText().trim();
                    String turno = (String) comboTurno.getSelectedItem();
                    Diretor_Turma dt = (Diretor_Turma) comboDiretor_Turma.getSelectedItem();
                    Qualificacao q = (Qualificacao) comboQualificacaco.getSelectedItem();
                    Nivel n = (Nivel) comboNivel.getSelectedItem();

                    if (nome.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Informe o nome da turma");
                        return;
                    }

                    Date data = (Date) spinnerAnoLectivo.getValue();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(data);
                    int ano_lectivo = cal.get(Calendar.YEAR);

                    TurmaController tc = new TurmaController();
                    boolean sucesso;

                    if (idUser == 0) {
                        sucesso = tc.cadastrarTurma(nome, ano_lectivo, turno, dt, q, n);
                        if (sucesso) {
                            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
                            frame.dispose();
                            tela_principal = Tela_Principal.getInstancia();
                            tela_principal.listarTurma();
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao cadastrar");
                        }
                    } else {
                        sucesso = tc.atualizarTurma(idUser, nome, ano_lectivo, turno, dt, q, n);
                        if (sucesso) {
                            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
                            idUser = 0;
                            frame.dispose();
                            tela_principal = Tela_Principal.getInstancia();
                            tela_principal.listarTurma();
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao atualizar");
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });
        panelBotoes.add(btnGuardar);
    }

    // --- INTERFACE DE ESTILO ADAPTATIVA ---
    private void styleTextField(JTextField field, Font font) {
        field.setFont(font);
        field.setBackground(COLOR_FIELD_BG);
        field.setForeground(COLOR_TEXT);
        field.setCaretColor(COLOR_TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_FIELD_BORDER, 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 34));
    }

    private void styleComboBox(JComboBox combo, Font font) {
        combo.setFont(font);
        combo.setBackground(COLOR_FIELD_BG);
        combo.setForeground(COLOR_TEXT);
        combo.setBorder(BorderFactory.createLineBorder(COLOR_FIELD_BORDER, 1));
        combo.setPreferredSize(new Dimension(combo.getPreferredSize().width, 34));
    }

    private void styleButton(JButton button, Color background, Color foreground) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    public void buscarTurma(int codigo, String nome, int ano_lectivo, String turno, String diretor, String qualificacao, String nivel) {
        idUser = codigo;
        textNome.setText(nome);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, ano_lectivo);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        spinnerAnoLectivo.setValue(cal.getTime());

        for (int i = 0; i < comboTurno.getItemCount(); i++) {
            if (comboTurno.getItemAt(i).equals(turno)) {
                comboTurno.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < comboDiretor_Turma.getItemCount(); i++) {
            if (comboDiretor_Turma.getItemAt(i).toString().equals(diretor)) {
                comboDiretor_Turma.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < comboQualificacaco.getItemCount(); i++) {
            if (comboQualificacaco.getItemAt(i).toString().equals(qualificacao)) {
                comboQualificacaco.setSelectedIndex(i);
                break;
            }
        }
    }

    public void limpar() {
        textNome.setText("");
        spinnerAnoLectivo.setValue(new Date());
        comboTurno.setSelectedIndex(0);
        if (comboDiretor_Turma.getItemCount() > 0) comboDiretor_Turma.setSelectedIndex(0);
        if (comboQualificacaco.getItemCount() > 0) comboQualificacaco.setSelectedIndex(0);
        comboNivel.removeAllItems();
        idUser = 0;
    }
}