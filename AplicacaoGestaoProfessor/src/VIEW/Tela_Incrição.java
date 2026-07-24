package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;
import controller.InscricaoController;
import controller.ModuloController;
import controller.FormandoController;
import dao.ExceptionDao;
import model.Modulo;
import model.Formando;

public class Tela_Incrição extends JPanel implements OnDadosAlteradosListener {

    private static final long serialVersionUID = 1L;
    
    private JComboBox<Formando> comboFormando;
    private JTextField txtDataInscricao;
    private JComboBox<Modulo> comboModulo;
    private JComboBox<String> comboSemestre;
    private JButton btnGravar, btnLimpar;
    
    private InscricaoController inscricaoController;

    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color BRANCO          = Color.WHITE;

    public Tela_Incrição() {
   
        setLayout(new BorderLayout(0, 15));
        setBackground(BRANCO);
        setBorder(new EmptyBorder(15, 15, 15, 15));
        
        inscricaoController = new InscricaoController();

        EventoCadastro.registrar(this);

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 13);

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));
        panelForm.setBackground(BRANCO);

        // Formando (ComboBox)
        comboFormando = new JComboBox<>();
        comboFormando.setBackground(BRANCO);
        comboFormando.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Formando) {
                    Formando f = (Formando) value;
                    setText(f.getNome() + " " + f.getApelido());
                }
                return this;
            }
        });
        carregarFormandos();
        panelForm.add(criarLinhaFormulario("Formando:", comboFormando, fontLabel, fontText));
        
        // Módulo (ComboBox)
        comboModulo = new JComboBox<>();
        comboModulo.setBackground(BRANCO);
        comboModulo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Modulo) {
                    Modulo m = (Modulo) value;
                    setText(m.getNome());
                }
                return this;
            }
        });
        carregarModulos();
        panelForm.add(criarLinhaFormulario("Módulo:", comboModulo, fontLabel, fontText));

        // Data de Inscrição (com calendário)
        txtDataInscricao = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        txtDataInscricao.setEditable(false);
        txtDataInscricao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtDataInscricao.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                abrirCalendario();
            }
        });
        panelForm.add(criarLinhaFormulario("Data de Inscrição:", txtDataInscricao, fontLabel, fontText));

        // Semestre (ComboBox fixa, igual ao Módulo)
        comboSemestre = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "1º Semestre", "2º Semestre"
        }));
        comboSemestre.setBackground(BRANCO);
        panelForm.add(criarLinhaFormulario("Semestre:", comboSemestre, fontLabel, fontText));

        JPanel panelBotoesContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panelBotoesContainer.setBackground(BRANCO);
        panelBotoesContainer.setBorder(new EmptyBorder(0, 0, 0, 0));

        btnLimpar = new JButton("Limpar Campos");
        btnLimpar.setBackground(BRANCO);
        btnLimpar.setForeground(AZUL_ESCURO_NAV);
        btnLimpar.setFont(fontLabel);
        btnLimpar.setPreferredSize(new Dimension(225, 38));
        btnLimpar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        btnLimpar.addActionListener(e -> limparCampos());

        btnGravar = new JButton("Gravar Inscrição");
        btnGravar.setBackground(AZUL_DESTAQUE);
        btnGravar.setForeground(BRANCO);
        btnGravar.setFont(fontLabel);
        btnGravar.setPreferredSize(new Dimension(225, 38));
        btnGravar.setBorder(null);
        btnGravar.addActionListener(e -> acaoSalvar());

        panelBotoesContainer.add(btnLimpar);
        panelBotoesContainer.add(btnGravar);
        
        panelForm.add(panelBotoesContainer);
        add(panelForm, BorderLayout.NORTH);
    }

    private void carregarFormandos() {
        try {
            FormandoController fc = new FormandoController();
            ArrayList<Formando> formandos = fc.listarFormando("");
            comboFormando.removeAllItems();
            for (Formando f : formandos) {
                comboFormando.addItem(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregarModulos() {
        try {
            ModuloController mc = new ModuloController();
            ArrayList<Modulo> modulos = mc.listarModulo("");
            comboModulo.removeAllItems();
            for (Modulo m : modulos) {
                comboModulo.addItem(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void abrirCalendario() {
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            cal.setTime(sdf.parse(txtDataInscricao.getText()));
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
            String[] nomesMeses = new SimpleDateFormat("MMMM yyyy").format(temp.getTime()).split(" ");
            lblMesAno.setText(new SimpleDateFormat("MMMM yyyy").format(temp.getTime()));

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
                    txtDataInscricao.setText(new SimpleDateFormat("dd/MM/yyyy").format(selecionado.getTime()));
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
        dialog.setLocationRelativeTo(txtDataInscricao);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    private JPanel criarLinhaFormulario(String textoLabel, javax.swing.JComponent componente, Font fontLabel, Font fontComponente) {
        JPanel linha = new JPanel(new BorderLayout(10, 0));
        linha.setBackground(BRANCO);
        linha.setBorder(new EmptyBorder(4, 0, 4, 0));

        JLabel label = new JLabel(textoLabel);
        label.setFont(fontLabel);
        label.setForeground(AZUL_ESCURO_NAV);
        label.setPreferredSize(new Dimension(150, 35));

        componente.setFont(fontComponente);
        componente.setPreferredSize(new Dimension(componente.getPreferredSize().width, 35));

        linha.add(label, BorderLayout.WEST);
        linha.add(componente, BorderLayout.CENTER);

        return linha;
    }

    private void acaoSalvar() {
        Formando formando = (Formando) comboFormando.getSelectedItem();
        Modulo modulo = (Modulo) comboModulo.getSelectedItem();
        String semestre = (String) comboSemestre.getSelectedItem();
        String dataExibida = txtDataInscricao.getText().trim();

        if (formando == null || modulo == null || dataExibida.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            SimpleDateFormat sdfExibicao = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfBanco = new SimpleDateFormat("yyyy-MM-dd");
            String dataBanco = sdfBanco.format(sdfExibicao.parse(dataExibida));

            boolean sucesso = inscricaoController.cadastrarInscricao(formando, modulo, semestre, dataBanco);
            
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Inscrição efetuada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                try {
                    Tela_Principal.getInstancia().listarInscricoes();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar a inscrição. Verifique os dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ExceptionDao e) {
            JOptionPane.showMessageDialog(this, "Erro na base de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao processar a data.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        if (comboFormando.getItemCount() > 0) comboFormando.setSelectedIndex(0);
        if (comboModulo.getItemCount() > 0) comboModulo.setSelectedIndex(0);
        comboSemestre.setSelectedIndex(0);
        txtDataInscricao.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    }

    @Override
    public void ListarDadosAoAlterar() {
        carregarFormandos();
        carregarModulos();
    }
}