package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Tela_cadastroModulo extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtNomeModulo;
    private JTextField txtCargaHoraria;
    private JComboBox<String> comboSemestres;
    private JComboBox<String> comboQualificacao;
    private JComboBox<String> comboNivelQualificacao;
    private JButton btnSalvar, btnLimpar;

    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

    public Tela_cadastroModulo() {
        setTitle("Cadastro de Módulo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(850, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 15));
        painelPrincipal.setBackground(BRANCO);
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Cabeçalho
        JPanel panelAcoes = new JPanel(new BorderLayout());
        panelAcoes.setBackground(BRANCO);

        JLabel lblTitulo = new JLabel("Cadastro de Módulo");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(AZUL_ESCURO_NAV);
        panelAcoes.add(lblTitulo, BorderLayout.NORTH);

        JLabel lblInfo = new JLabel("Preencha os dados abaixo para registar um novo módulo no sistema.");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(TEXTO_MUTED);
        panelAcoes.add(lblInfo, BorderLayout.SOUTH);

        painelPrincipal.add(panelAcoes, BorderLayout.NORTH);

        // Campos do formulário
        JPanel panelFormContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelFormContainer.setBackground(BRANCO);

        JPanel panelGridCampos = new JPanel(new GridLayout(5, 2, 20, 20));
        panelGridCampos.setBackground(BRANCO);
        panelGridCampos.setPreferredSize(new Dimension(780, 290));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText  = new Font("Segoe UI", Font.PLAIN, 14);

        // Nome do Módulo
        panelGridCampos.add(new JLabel("Nome do Módulo *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtNomeModulo = new JTextField();
        txtNomeModulo.setFont(fontText);
        panelGridCampos.add(txtNomeModulo);

        // Carga Horária
        panelGridCampos.add(new JLabel("Carga Horária *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtCargaHoraria = new JTextField();
        txtCargaHoraria.setFont(fontText);
        panelGridCampos.add(txtCargaHoraria);

        // Semestres
        panelGridCampos.add(new JLabel("Semestres *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        comboSemestres = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "1º Semestre", "2º Semestre"
        }));
        comboSemestres.setFont(fontText);
        panelGridCampos.add(comboSemestres);

        // Qualificação
        panelGridCampos.add(new JLabel("Qualificação *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        comboQualificacao = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "Suporte Informatico", "Programacao Web", "Administração de redes"
        }));
        comboQualificacao.setFont(fontText);
        panelGridCampos.add(comboQualificacao);

        // Nível da Qualificação
        panelGridCampos.add(new JLabel("Nível da Qualificação *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        comboNivelQualificacao = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "CV 3", "CV 4", "CV 5"
        }));
        comboNivelQualificacao.setFont(fontText);
        panelGridCampos.add(comboNivelQualificacao);

        panelFormContainer.add(panelGridCampos);
        painelPrincipal.add(panelFormContainer, BorderLayout.CENTER);

        // Botões
        JPanel panelBotoesRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        panelBotoesRodape.setBackground(BRANCO);
        panelBotoesRodape.setBorder(new EmptyBorder(10, 0, 0, 0));

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(BRANCO);
        btnLimpar.setFont(fontLabel);
        btnLimpar.setPreferredSize(new Dimension(150, 40));
        btnLimpar.setBorder(new LineBorder(new Color(220, 224, 230)));
        btnLimpar.addActionListener(e -> limparCampos());

        btnSalvar = new JButton("Guardar Módulo");
        btnSalvar.setBackground(AZUL_DESTAQUE);
        btnSalvar.setForeground(BRANCO);
        btnSalvar.setFont(fontLabel);
        btnSalvar.setPreferredSize(new Dimension(170, 40));
        btnSalvar.setBorder(null);
        btnSalvar.addActionListener(e -> acaoSalvar());

        panelBotoesRodape.add(btnLimpar);
        panelBotoesRodape.add(btnSalvar);

        painelPrincipal.add(panelBotoesRodape, BorderLayout.SOUTH);

        setContentPane(painelPrincipal);
    }

    private void acaoSalvar() {
        String nome         = txtNomeModulo.getText().trim();
        String cargaHoraria = txtCargaHoraria.getText().trim();

        if (nome.isEmpty() || cargaHoraria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha os campos obrigatórios (*).", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Módulo guardado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        limparCampos();
    }

    private void limparCampos() {
        txtNomeModulo.setText("");
        txtCargaHoraria.setText("");
        comboSemestres.setSelectedIndex(0);
        comboQualificacao.setSelectedIndex(0);
        comboNivelQualificacao.setSelectedIndex(0);
    }
}