package VIEW;

import model.*;
import controller.*;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Tela_Matricula extends JPanel {
    private static final long serialVersionUID = 1L;

    private JComboBox<Formando> comboFormando;
    private JComboBox<Qualificacao> comboQualificacao;
    private JComboBox<Nivel> comboNivel;
    private ArrayList<Formando> formandos;
    private ArrayList<Qualificacao> qualificacoes;
    private ArrayList<Nivel> niveis;
    private FormandoController fc = null;

    private JTextField txtDataMatricula;
    private JButton btnConfirmar, btnCancelar;

    private int idUser = 0;
    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE = new Color(13, 110, 253);
    private final Color FUNDO_CLARO = new Color(244, 246, 249);
    private final Color BRANCO = Color.WHITE;

    public interface OnMatriculaCadastradaListener {
        void onMatriculaCadastrada(String numMatricula, String aluno, String turma, String sala, String data);
        void onCancelar();
    }

    private OnMatriculaCadastradaListener listener;

    public Tela_Matricula(OnMatriculaCadastradaListener listener) {
        this.listener = listener;

        setLayout(new BorderLayout());
        setBackground(FUNDO_CLARO);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelCard = new JPanel(new GridLayout(4, 2, 10, 15));
        panelCard.setBackground(BRANCO);
        panelCard.setBorder(new EmptyBorder(25, 25, 25, 25));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);

        panelCard.add(new JLabel("Nome do Formando *") {{ setFont(fontLabel); }});
        comboFormando = new JComboBox<>();
        comboFormando.setFont(fontText);
        try {
            fc = new FormandoController();
            formandos = fc.comboFormando();
            for (Formando f : formandos) {
                comboFormando.addItem(f);
            }
        } catch (Exception s) {
            s.printStackTrace();
        }
        panelCard.add(comboFormando);

        panelCard.add(new JLabel("Qualificação *") {{ setFont(fontLabel); }});
        comboQualificacao = new JComboBox<>();
        comboQualificacao.setFont(fontText);
        panelCard.add(comboQualificacao);
        try {
            QualificacaoController qc = new QualificacaoController();
            qualificacoes = qc.comboQualificacao();
            for (Qualificacao q : qualificacoes) {
                comboQualificacao.addItem(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        panelCard.add(new JLabel("Nível *") {{ setFont(fontLabel); }});
        comboNivel = new JComboBox<>();
        comboNivel.setFont(fontText);
        panelCard.add(comboNivel);

        comboQualificacao.addActionListener(e -> preencherNiveis());

        // Data (com calendário)
        panelCard.add(new JLabel("Data da Matrícula *") {{ setFont(fontLabel); }});
        txtDataMatricula = new JTextField(new java.text.SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        txtDataMatricula.setFont(fontText);
        txtDataMatricula.setEditable(false);
        txtDataMatricula.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtDataMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                abrirCalendario(txtDataMatricula);
            }
        });
        panelCard.add(txtDataMatricula);

        JPanel barraBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        barraBotoes.setBackground(BRANCO);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(fontLabel);
        btnCancelar.setBackground(BRANCO);
        btnCancelar.addActionListener(e -> {
            limparCampos();
            setVisible(false);
        });

        btnConfirmar = new JButton("Efectuar Matrícula");
        btnConfirmar.setBackground(AZUL_DESTAQUE);
        btnConfirmar.setForeground(BRANCO);
        btnConfirmar.setFont(fontLabel);
        btnConfirmar.addActionListener(e -> acaoSalvar());

        barraBotoes.add(btnCancelar);
        barraBotoes.add(btnConfirmar);

        JPanel containerCentral = new JPanel(new BorderLayout(0, 20));
        containerCentral.setBackground(BRANCO);
        containerCentral.setBorder(new EmptyBorder(10, 10, 10, 10));
        containerCentral.add(panelCard, BorderLayout.CENTER);
        containerCentral.add(barraBotoes, BorderLayout.SOUTH);

        add(containerCentral, BorderLayout.CENTER);
    }

    public Tela_Matricula() {
        this(null);
        JFrame janela = new JFrame("Efetuar Matrícula");
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setSize(500, 350);
        janela.setLocationRelativeTo(null);
        janela.getContentPane().add(this);
        this.listener = new OnMatriculaCadastradaListener() {
            @Override
            public void onMatriculaCadastrada(String numMatricula, String aluno, String turma, String sala, String data) {
                janela.dispose();
            }

            @Override
            public void onCancelar() {
                janela.dispose();
            }
        };
        janela.setVisible(true);
    }

    // ---------- CALENDÁRIO (Data) ----------
    private void abrirCalendario(JTextField campoAlvo) {
        Calendar cal = Calendar.getInstance();
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            cal.setTime(sdf.parse(campoAlvo.getText()));
        } catch (Exception ignored) {}

        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout(5, 5));
        dialog.getContentPane().setBackground(BRANCO);

        int[] mesAno = { cal.get(Calendar.MONTH), cal.get(Calendar.YEAR) };

        JPanel panelCabecalho = new JPanel(new BorderLayout());
        panelCabecalho.setBackground(AZUL_ESCURO_NAV);
        JLabel lblMesAno = new JLabel("", javax.swing.SwingConstants.CENTER);
        lblMesAno.setForeground(BRANCO);
        lblMesAno.setFont(new Font("Segoe UI", Font.BOLD, 13));
        JButton btnAnterior = new JButton("<");
        JButton btnProximo = new JButton(">");
        panelCabecalho.add(btnAnterior, BorderLayout.WEST);
        panelCabecalho.add(lblMesAno, BorderLayout.CENTER);
        panelCabecalho.add(btnProximo, BorderLayout.EAST);

        JPanel panelDias = new JPanel(new GridLayout(0, 7, 2, 2));
        panelDias.setBackground(BRANCO);

        Runnable[] atualizar = new Runnable[1];
        atualizar[0] = () -> {
            panelDias.removeAll();
            Calendar temp = Calendar.getInstance();
            temp.set(mesAno[1], mesAno[0], 1);
            lblMesAno.setText(new java.text.SimpleDateFormat("MMMM yyyy").format(temp.getTime()));

            String[] diasSemana = {"D","S","T","Q","Q","S","S"};
            for (String d : diasSemana) {
                JLabel lbl = new JLabel(d, javax.swing.SwingConstants.CENTER);
                lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
                panelDias.add(lbl);
            }

            int diaSemanaInicio = temp.get(Calendar.DAY_OF_WEEK) - 1;
            int totalDias = temp.getActualMaximum(Calendar.DAY_OF_MONTH);

            for (int i = 0; i < diaSemanaInicio; i++) {
                panelDias.add(new JLabel(""));
            }
            for (int dia = 1; dia <= totalDias; dia++) {
                final int diaFinal = dia;
                JButton btnDia = new JButton(String.valueOf(dia));
                btnDia.setMargin(new java.awt.Insets(2, 2, 2, 2));
                btnDia.addActionListener(ev -> {
                    Calendar selecionado = Calendar.getInstance();
                    selecionado.set(mesAno[1], mesAno[0], diaFinal);
                    campoAlvo.setText(new java.text.SimpleDateFormat("dd/MM/yyyy").format(selecionado.getTime()));
                    dialog.dispose();
                });
                panelDias.add(btnDia);
            }
            panelDias.revalidate();
            panelDias.repaint();
        };

        btnAnterior.addActionListener(e -> {
            mesAno[0]--;
            if (mesAno[0] < 0) { mesAno[0] = 11; mesAno[1]--; }
            atualizar[0].run();
        });
        btnProximo.addActionListener(e -> {
            mesAno[0]++;
            if (mesAno[0] > 11) { mesAno[0] = 0; mesAno[1]++; }
            atualizar[0].run();
        });

        atualizar[0].run();

        dialog.add(panelCabecalho, BorderLayout.NORTH);
        dialog.add(panelDias, BorderLayout.CENTER);
        dialog.setSize(260, 220);
        dialog.setLocationRelativeTo(campoAlvo);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    private void preencherNiveis() {
        try {
            Qualificacao q = (Qualificacao) comboQualificacao.getSelectedItem();
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

    private void acaoSalvar() {
        try {
            Formando formando = (Formando) comboFormando.getSelectedItem();
            Qualificacao q = (Qualificacao) comboQualificacao.getSelectedItem();
            Nivel n = (Nivel) comboNivel.getSelectedItem();

            if (formando == null || q == null || n == null) {
                JOptionPane.showMessageDialog(this, "Selecione todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String data = txtDataMatricula.getText();

            MatriculaController mc = new MatriculaController();
            boolean sucesso;

            if (idUser == 0) {
                sucesso = mc.cadastrarMatricula(formando, q, n, data);
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Matricula efectuada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao efectuar matrícula");
                }
            } else {
                sucesso = mc.atualizarMatricula(idUser, formando, q, n, data);
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Matrícula atualizada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao atualizar matrícula");
                }
            }

            if (sucesso) {
                limparCampos();
                if (listener != null) {
                    listener.onMatriculaCadastrada("", "", "", "", data);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarMatricula(int codigo, Formando f, Qualificacao q, Nivel n, String data) {
        idUser = codigo;

        for (int i = 0; i < comboFormando.getItemCount(); i++) {
            if (comboFormando.getItemAt(i).equals(f)) {
                comboFormando.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < comboQualificacao.getItemCount(); i++) {
            if (comboQualificacao.getItemAt(i).equals(q)) {
                comboQualificacao.setSelectedIndex(i);
                break;
            }
        }

        preencherNiveis();

        for (int i = 0; i < comboNivel.getItemCount(); i++) {
            if (comboNivel.getItemAt(i).equals(n)) {
                comboNivel.setSelectedIndex(i);
                break;
            }
        }

        txtDataMatricula.setText(data);
    }

    private void limparCampos() {
        if (comboFormando.getItemCount() > 0) comboFormando.setSelectedIndex(0);
        if (comboQualificacao.getItemCount() > 0) comboQualificacao.setSelectedIndex(0);
        comboNivel.removeAllItems();
        txtDataMatricula.setText(new java.text.SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        idUser = 0;
    }
}