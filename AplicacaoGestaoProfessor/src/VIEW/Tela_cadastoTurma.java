package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.QualificacaoController;
import controller.NivelController;
import controller.Diretor_TurmaController;
import controller.TurmaController;
import controller.Quali_NivelController;
import model.Qualificacao;
import model.Nivel;
import model.Diretor_Turma;

public class Tela_cadastoTurma extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtNomeTurma;
    private JComboBox<String> comboTurno;
    private JComboBox<Qualificacao> comboQualificacao;
    private JComboBox<Nivel> comboNivel;
    private JComboBox<Diretor_Turma> comboDiretor;
    private JComboBox<Integer> comboAnoIngresso;
    private JButton btnSalvar, btnCancelar;

    private final Color AZUL_DESTAQUE = new Color(13, 110, 253);
    private final Color FUNDO_CLARO = new Color(244, 246, 249);
    private final Color BRANCO = Color.WHITE;

    public interface OnTurmaCadastradaListener {
        void onTurmaCadastrada(String codigo, String curso, String regime, String formador);
        void onCancelar();
    }

    private OnTurmaCadastradaListener listener;

    public Tela_cadastoTurma(OnTurmaCadastradaListener listener) {
        this.listener = listener;

        setLayout(new BorderLayout());
        setBackground(FUNDO_CLARO);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelCard = new JPanel(new GridLayout(7, 2, 15, 20));
        panelCard.setBackground(BRANCO);
        panelCard.setBorder(new EmptyBorder(25, 25, 25, 25));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel lblNomeTurma = new JLabel("Nome da Turma:");
        lblNomeTurma.setFont(fontLabel);
        panelCard.add(lblNomeTurma);

        txtNomeTurma = new JTextField();
        txtNomeTurma.setFont(fontText);
        panelCard.add(txtNomeTurma);

        JLabel lblTurno = new JLabel("Turno:");
        lblTurno.setFont(fontLabel);
        panelCard.add(lblTurno);

        comboTurno = new JComboBox<>();
        comboTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Manhã", "Tarde", "Noite"}));
        comboTurno.setFont(fontText);
        panelCard.add(comboTurno);

        JLabel lblAno = new JLabel("Ano de Ingresso:");
        lblAno.setFont(fontLabel);
        panelCard.add(lblAno);

        comboAnoIngresso = new JComboBox<>();
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        for (int ano = anoAtual - 2; ano <= anoAtual + 5; ano++) {
            comboAnoIngresso.addItem(ano);
        }
        comboAnoIngresso.setSelectedItem(anoAtual);
        comboAnoIngresso.setFont(fontText);
        panelCard.add(comboAnoIngresso);

        JLabel lblQualificacao = new JLabel("Qualificação:");
        lblQualificacao.setFont(fontLabel);
        panelCard.add(lblQualificacao);

        comboQualificacao = new JComboBox<>();
        comboQualificacao.setFont(fontText);
        panelCard.add(comboQualificacao);

        JLabel lblNivel = new JLabel("Nível:");
        lblNivel.setFont(fontLabel);
        panelCard.add(lblNivel);

        comboNivel = new JComboBox<>();
        comboNivel.setFont(fontText);
        panelCard.add(comboNivel);

        JLabel lblDiretor = new JLabel("Diretor de Turma:");
        lblDiretor.setFont(fontLabel);
        panelCard.add(lblDiretor);

        comboDiretor = new JComboBox<>();
        comboDiretor.setFont(fontText);
        panelCard.add(comboDiretor);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(BRANCO);
        btnCancelar.setFont(fontLabel);
        btnCancelar.addActionListener(e -> {
            limparCampos();
            if (listener != null) {
                listener.onCancelar();
            }
        });

        btnSalvar = new JButton("Gravar Turma");
        btnSalvar.setBackground(AZUL_DESTAQUE);
        btnSalvar.setForeground(BRANCO);
        btnSalvar.setFont(fontLabel);
        btnSalvar.addActionListener(e -> acaoSalvar());

        panelCard.add(btnCancelar);
        panelCard.add(btnSalvar);

        add(panelCard, BorderLayout.CENTER);

        carregarDados();
        configurarEventoQualificacao();
    }

    public Tela_cadastoTurma() {
        this(null);
        JFrame janela = new JFrame("Cadastro de Turma");
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setSize(650, 520);
        janela.setLocationRelativeTo(null);
        janela.getContentPane().add(this);
        this.listener = new OnTurmaCadastradaListener() {
            @Override
            public void onTurmaCadastrada(String codigo, String curso, String regime, String formador) {
                janela.dispose();
            }

            @Override
            public void onCancelar() {
                janela.dispose();
            }
        };
        janela.setVisible(true);
    }

    private void carregarDados() {
        try {
            QualificacaoController qc = new QualificacaoController();
            ArrayList<Qualificacao> qualificacoes = qc.comboQualificacao();
            comboQualificacao.removeAllItems();
            for (Qualificacao q : qualificacoes) {
                comboQualificacao.addItem(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Diretor_TurmaController dc = new Diretor_TurmaController();
            ArrayList<Diretor_Turma> diretores = dc.comboDiretor_Turma();
            comboDiretor.removeAllItems();
            for (Diretor_Turma d : diretores) {
                comboDiretor.addItem(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        comboNivel.removeAllItems();
        if (comboQualificacao.getItemCount() > 0) {
            preencherNiveisDaQualificacao();
        }
    }

    private void configurarEventoQualificacao() {
        comboQualificacao.addActionListener(e -> preencherNiveisDaQualificacao());
    }

    private void preencherNiveisDaQualificacao() {
        try {
            comboNivel.removeAllItems();

            Qualificacao q = (Qualificacao) comboQualificacao.getSelectedItem();
            if (q != null) {
                Quali_NivelController qn = new Quali_NivelController();
                ArrayList<Nivel> niveis = qn.getQualificacao_Nivel(q);

                for (Nivel n : niveis) {
                    comboNivel.addItem(n);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void acaoSalvar() {
        String nomeTurma = txtNomeTurma.getText().trim();

        if (nomeTurma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduza o nome da turma.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String turno = (String) comboTurno.getSelectedItem();
        int anoIngresso = (Integer) comboAnoIngresso.getSelectedItem();
        Qualificacao qualificacaoSelecionada = (Qualificacao) comboQualificacao.getSelectedItem();
        Nivel nivelSelecionado = (Nivel) comboNivel.getSelectedItem();
        Diretor_Turma diretorSelecionado = (Diretor_Turma) comboDiretor.getSelectedItem();

        if (qualificacaoSelecionada == null || nivelSelecionado == null || diretorSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            TurmaController tc = new TurmaController();

            boolean sucesso = tc.cadastrarTurma(
                    nomeTurma,
                    anoIngresso,
                    turno,
                    diretorSelecionado,
                    qualificacaoSelecionada,
                    nivelSelecionado
            );

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Turma guardada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                if (listener != null) {
                    listener.onTurmaCadastrada(
                            nomeTurma,
                            qualificacaoSelecionada.getTitulo(),
                            turno,
                            diretorSelecionado.getFomador().getNome()
                    );
                }

                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao cadastrar a turma!", "Falhado", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao guardar turma: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        txtNomeTurma.setText("");
        comboTurno.setSelectedIndex(0);
        comboAnoIngresso.setSelectedItem(Calendar.getInstance().get(Calendar.YEAR));
        if (comboQualificacao.getItemCount() > 0) comboQualificacao.setSelectedIndex(0);
        comboNivel.removeAllItems();
        if (comboDiretor.getItemCount() > 0) comboDiretor.setSelectedIndex(0);
    }
}