package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.FormadorController;
import controller.LicaoController;
import controller.ModuloController;
import controller.SalaController;
import controller.TurmaController;
import model.Formador;
import model.Modulo;
import model.Sala;
import model.Turma;

public class Tela_cadastroLicao extends JFrame {

    private static final long serialVersionUID = 1L;

    private JComboBox<Modulo> cbModulo;
    private JComboBox<Formador> cbFormador;
    private JComboBox<Sala> cbSala;
    private JComboBox<Turma> cbTurma;
    private JTextField txtData;
    private JTextField txtHoraInicio;
    private JTextField txtHoraFim;
    private JButton btnSalvar, btnLimpar;

    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

    public Tela_cadastroLicao() {
        setTitle("Cadastro de Lição");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(950, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BRANCO);
        setLayout(new BorderLayout(0, 15));
        getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel panelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelTopo.setBackground(BRANCO);
        JLabel lblInfo = new JLabel("Preencha os dados abaixo para registar uma nova lição no sistema.");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(TEXTO_MUTED);
        panelTopo.add(lblInfo);
        add(panelTopo, BorderLayout.NORTH);

        JPanel panelFormContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelFormContainer.setBackground(BRANCO);

        JPanel panelGridCampos = new JPanel(new GridLayout(7, 2, 20, 15));
        panelGridCampos.setBackground(BRANCO);
        panelGridCampos.setPreferredSize(new Dimension(800, 380));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);

        panelGridCampos.add(new JLabel("Módulo *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        cbModulo = new JComboBox<>();
        cbModulo.setFont(fontText);
        cbModulo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Modulo) {
                    setText(((Modulo) value).getNome());
                }
                return this;
            }
        });
        try {
            ModuloController mc = new ModuloController();
            ArrayList<Modulo> modulos = mc.listarModulo("");
            for (Modulo m : modulos) {
                cbModulo.addItem(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        panelGridCampos.add(cbModulo);

        panelGridCampos.add(new JLabel("Formador *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        cbFormador = new JComboBox<>();
        cbFormador.setFont(fontText);
        try {
            FormadorController fc = new FormadorController();
            ArrayList<Formador> formadores = fc.listarFormador("");
            for (Formador f : formadores) {
                cbFormador.addItem(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        panelGridCampos.add(cbFormador);

        panelGridCampos.add(new JLabel("Sala *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        cbSala = new JComboBox<>();
        cbSala.setFont(fontText);
        cbSala.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Sala) {
                    setText(((Sala) value).getDesignacao());
                }
                return this;
            }
        });
        try {
            SalaController sc = new SalaController();
            ArrayList<Sala> salas = sc.listarSala("");
            for (Sala s : salas) {
                cbSala.addItem(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        panelGridCampos.add(cbSala);

        panelGridCampos.add(new JLabel("Turma *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        cbTurma = new JComboBox<>();
        cbTurma.setFont(fontText);
        try {
            TurmaController tc = new TurmaController();
            ArrayList<Turma> turmas = tc.listarTurma("");
            for (Turma t : turmas) {
                cbTurma.addItem(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        panelGridCampos.add(cbTurma);

        // Data (com calendário)
        panelGridCampos.add(new JLabel("Data *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtData = new JTextField(new java.text.SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        txtData.setFont(fontText);
        txtData.setEditable(false);
        txtData.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtData.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                abrirCalendario(txtData);
            }
        });
        panelGridCampos.add(txtData);

        // Hora de Início (com relógio)
        panelGridCampos.add(new JLabel("Hora de Início *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtHoraInicio = new JTextField(new java.text.SimpleDateFormat("HH:mm").format(new Date()));
        txtHoraInicio.setFont(fontText);
        txtHoraInicio.setEditable(false);
        txtHoraInicio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtHoraInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                abrirRelogio(txtHoraInicio);
            }
        });
        panelGridCampos.add(txtHoraInicio);

        // Hora de Fim (com relógio)
        panelGridCampos.add(new JLabel("Hora de Fim *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtHoraFim = new JTextField(new java.text.SimpleDateFormat("HH:mm").format(new Date()));
        txtHoraFim.setFont(fontText);
        txtHoraFim.setEditable(false);
        txtHoraFim.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtHoraFim.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                abrirRelogio(txtHoraFim);
            }
        });
        panelGridCampos.add(txtHoraFim);

        panelFormContainer.add(panelGridCampos);
        add(panelFormContainer, BorderLayout.CENTER);

        JPanel panelBotoesRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        panelBotoesRodape.setBackground(BRANCO);
        panelBotoesRodape.setBorder(new EmptyBorder(10, 0, 0, 0));

        btnLimpar = new JButton("Limpar Campos");
        btnLimpar.setBackground(BRANCO);
        btnLimpar.setFont(fontLabel);
        btnLimpar.setPreferredSize(new Dimension(150, 40));
        btnLimpar.setBorder(new LineBorder(new Color(220, 224, 230)));
        btnLimpar.addActionListener(e -> limparCampos());

        btnSalvar = new JButton("Guardar Lição");
        btnSalvar.setBackground(AZUL_DESTAQUE);
        btnSalvar.setForeground(BRANCO);
        btnSalvar.setFont(fontLabel);
        btnSalvar.setPreferredSize(new Dimension(165, 40));
        btnSalvar.setBorder(null);
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(e -> acaoSalvar());

        panelBotoesRodape.add(btnLimpar);
        panelBotoesRodape.add(btnSalvar);
        add(panelBotoesRodape, BorderLayout.SOUTH);
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

    // ---------- RELÓGIO (Hora) ----------
    private void abrirRelogio(JTextField campoAlvo) {
        int horaAtual = 0, minutoAtual = 0;
        try {
            String[] partes = campoAlvo.getText().split(":");
            horaAtual = Integer.parseInt(partes[0]);
            minutoAtual = Integer.parseInt(partes[1]);
        } catch (Exception ignored) {}

        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout(5, 5));
        dialog.getContentPane().setBackground(BRANCO);

        int[] selecao = { horaAtual, minutoAtual };
        boolean[] escolhendoHora = { true };

        JLabel lblDisplay = new JLabel("", javax.swing.SwingConstants.CENTER);
        lblDisplay.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblDisplay.setForeground(BRANCO);
        lblDisplay.setOpaque(true);
        lblDisplay.setBackground(AZUL_ESCURO_NAV);
        lblDisplay.setPreferredSize(new Dimension(0, 60));

        JPanel painelRelogio = new JPanel(null);
        painelRelogio.setBackground(BRANCO);
        painelRelogio.setPreferredSize(new Dimension(260, 260));

        Runnable[] desenhar = new Runnable[1];
        desenhar[0] = () -> {
            painelRelogio.removeAll();
            lblDisplay.setText(String.format("%02d:%02d", selecao[0], selecao[1]));

            int centro = 130, raio = 95;
            if (escolhendoHora[0]) {
                for (int h = 0; h < 24; h++) {
                    int valorMostrado = (h == 0) ? 0 : h;
                    double angulo = Math.toRadians((h % 12) * 30 - 90);
                    int aneRaio = (h < 12) ? raio : raio - 35;
                    int x = (int) (centro + aneRaio * Math.cos(angulo)) - 16;
                    int y = (int) (centro + aneRaio * Math.sin(angulo)) - 16;
                    final int horaFinal = h;
                    JButton btn = new JButton(String.valueOf(valorMostrado));
                    btn.setMargin(new java.awt.Insets(0,0,0,0));
                    btn.setBounds(x, y, 32, 32);
                    if (h == selecao[0]) {
                        btn.setBackground(AZUL_DESTAQUE);
                        btn.setForeground(BRANCO);
                    }
                    btn.addActionListener(ev -> {
                        selecao[0] = horaFinal;
                        escolhendoHora[0] = false;
                        desenhar[0].run();
                    });
                    painelRelogio.add(btn);
                }
            } else {
                for (int m = 0; m < 60; m += 5) {
                    double angulo = Math.toRadians((m / 5) * 30 - 90);
                    int x = (int) (centro + raio * Math.cos(angulo)) - 16;
                    int y = (int) (centro + raio * Math.sin(angulo)) - 16;
                    final int minutoFinal = m;
                    JButton btn = new JButton(String.format("%02d", m));
                    btn.setMargin(new java.awt.Insets(0,0,0,0));
                    btn.setBounds(x, y, 32, 32);
                    if (m == selecao[1]) {
                        btn.setBackground(AZUL_DESTAQUE);
                        btn.setForeground(BRANCO);
                    }
                    btn.addActionListener(ev -> {
                        selecao[1] = minutoFinal;
                        campoAlvo.setText(String.format("%02d:%02d", selecao[0], selecao[1]));
                        dialog.dispose();
                    });
                    painelRelogio.add(btn);
                }
            }
            painelRelogio.revalidate();
            painelRelogio.repaint();
        };

        JPanel panelBotoesRelogio = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        panelBotoesRelogio.setBackground(BRANCO);
        JButton btnHoras = new JButton("Horas");
        JButton btnMinutos = new JButton("Minutos");
        btnHoras.addActionListener(e -> { escolhendoHora[0] = true; desenhar[0].run(); });
        btnMinutos.addActionListener(e -> { escolhendoHora[0] = false; desenhar[0].run(); });
        panelBotoesRelogio.add(btnHoras);
        panelBotoesRelogio.add(btnMinutos);

        desenhar[0].run();

        dialog.add(lblDisplay, BorderLayout.NORTH);
        dialog.add(painelRelogio, BorderLayout.CENTER);
        dialog.add(panelBotoesRelogio, BorderLayout.SOUTH);
        dialog.setSize(280, 380);
        dialog.setLocationRelativeTo(campoAlvo);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    private void acaoSalvar() {
        if (cbModulo.getSelectedItem() == null || cbFormador.getSelectedItem() == null ||
            cbSala.getSelectedItem() == null || cbTurma.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione todos os campos obrigatórios (*).", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Modulo modulo = (Modulo) cbModulo.getSelectedItem();
        Formador formador = (Formador) cbFormador.getSelectedItem();
        Sala sala = (Sala) cbSala.getSelectedItem();
        Turma turma = (Turma) cbTurma.getSelectedItem();

        String dataExibida = txtData.getText();
        String horaInicioStr = txtHoraInicio.getText();
        String horaFimStr = txtHoraFim.getText();

        try {
            java.text.SimpleDateFormat sdfExibicao = new java.text.SimpleDateFormat("dd/MM/yyyy");
            java.text.SimpleDateFormat sdfBanco = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String dataBanco = sdfBanco.format(sdfExibicao.parse(dataExibida));

            LicaoController lc = new LicaoController();
            boolean sucesso = lc.cadastrarLicao(modulo, formador, sala, turma, dataBanco, horaInicioStr, horaFimStr);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Lição guardada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                try {
                    Tela_Principal.getInstancia().listarLicoes();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                limparCampos();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao cadastrar a lição!", "Falhado", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao guardar lição: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        if (cbModulo.getItemCount() > 0) cbModulo.setSelectedIndex(0);
        if (cbFormador.getItemCount() > 0) cbFormador.setSelectedIndex(0);
        if (cbSala.getItemCount() > 0) cbSala.setSelectedIndex(0);
        if (cbTurma.getItemCount() > 0) cbTurma.setSelectedIndex(0);
        txtData.setText(new java.text.SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        txtHoraInicio.setText(new java.text.SimpleDateFormat("HH:mm").format(new Date()));
        txtHoraFim.setText(new java.text.SimpleDateFormat("HH:mm").format(new Date()));
    }
}