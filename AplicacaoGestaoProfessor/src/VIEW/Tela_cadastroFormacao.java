package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Tela_cadastroFormacao extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtCodigo;
    private JTextField txtNomeFormacao;
    private JTextField txtCargaHoraria;
    private JComboBox<String> comboTipoCurso;
    private JComboBox<String> comboEstado;
    private JButton btnSalvar, btnLimpar;

    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

    public Tela_cadastroFormacao() {
        setLayout(new BorderLayout(0, 15));
        setBackground(BRANCO);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel panelAcoes = new JPanel(new BorderLayout());
        panelAcoes.setBackground(BRANCO);

        JPanel panelTituloInterno = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelTituloInterno.setBackground(BRANCO);
        JLabel lblInfo = new JLabel("Preencha os dados abaixo para registar uma nova formação no sistema.");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(TEXTO_MUTED);
        panelTituloInterno.add(lblInfo);
        panelAcoes.add(panelTituloInterno, BorderLayout.WEST);

        add(panelAcoes, BorderLayout.NORTH);

        JPanel panelFormContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelFormContainer.setBackground(BRANCO);

        JPanel panelGridCampos = new JPanel(new GridLayout(5, 2, 20, 20));
        panelGridCampos.setBackground(BRANCO);
        panelGridCampos.setPreferredSize(new Dimension(750, 280));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);

        panelGridCampos.add(new JLabel("Código *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtCodigo = new JTextField();
        txtCodigo.setFont(fontText);
        panelGridCampos.add(txtCodigo);

        panelGridCampos.add(new JLabel("Nome da Formação *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtNomeFormacao = new JTextField();
        txtNomeFormacao.setFont(fontText);
        panelGridCampos.add(txtNomeFormacao);

        panelGridCampos.add(new JLabel("Carga Horária *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtCargaHoraria = new JTextField();
        txtCargaHoraria.setFont(fontText);
        panelGridCampos.add(txtCargaHoraria);

        panelGridCampos.add(new JLabel("Tipo de Curso") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        comboTipoCurso = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "Profissionalizante", "Técnico", "Livre", "Especialização"
        }));
        comboTipoCurso.setFont(fontText);
        panelGridCampos.add(comboTipoCurso);

        panelGridCampos.add(new JLabel("Estado") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        comboEstado = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "Ativo", "Inativo"
        }));
        comboEstado.setFont(fontText);
        panelGridCampos.add(comboEstado);

        panelFormContainer.add(panelGridCampos);
        add(panelFormContainer, BorderLayout.CENTER);

        JPanel panelBotoesRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        panelBotoesRodape.setBackground(BRANCO);
        panelBotoesRodape.setBorder(new EmptyBorder(10, 0, 0, 0));

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(BRANCO);
        btnLimpar.setFont(fontLabel);
        btnLimpar.setPreferredSize(new Dimension(150, 40));
        btnLimpar.setBorder(new LineBorder(new Color(220, 224, 230)));
        btnLimpar.addActionListener(e -> limparCampos());

        btnSalvar = new JButton("Guardar Formação");
        btnSalvar.setBackground(AZUL_DESTAQUE);
        btnSalvar.setForeground(BRANCO);
        btnSalvar.setFont(fontLabel);
        btnSalvar.setPreferredSize(new Dimension(170, 40));
        btnSalvar.setBorder(null);
        btnSalvar.addActionListener(e -> acaoSalvar());

        panelBotoesRodape.add(btnLimpar);
        panelBotoesRodape.add(btnSalvar);

        add(panelBotoesRodape, BorderLayout.SOUTH);
    }

    private void acaoSalvar() {
        String codigo = txtCodigo.getText().trim();
        String nome = txtNomeFormacao.getText().trim();
        String cargaHoraria = txtCargaHoraria.getText().trim();

        if (codigo.isEmpty() || nome.isEmpty() || cargaHoraria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha os campos obrigatórios (*).", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Formação guardada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        limparCampos();
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtNomeFormacao.setText("");
        txtCargaHoraria.setText("");
        comboTipoCurso.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
    }
}