package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Tela_cadastroQualificação extends JPanel {

    private static final long serialVersionUID = 1L;
    
    // Componentes de Entrada de Dados
    private JTextField txtNomeQualificacao;
    private JTextField txtModulosAssociados;
    private JTextField txtCoordenador;
    private JTextField txtNivelQualificacao;
    private JTextField txtCampoPertencente;
    private JButton btnSalvar, btnLimpar;

    // Paleta de Cores unificada AcademiaPro
    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color FUNDO_CLARO     = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

    public Tela_cadastroQualificação() {
        // Alinhado ao plano de design do painel central
        setLayout(new BorderLayout(0, 15));
        setBackground(BRANCO);
        setBorder(new EmptyBorder(20, 20, 20, 20));

 
        JPanel panelAcoes = new JPanel(new BorderLayout());
        panelAcoes.setBackground(BRANCO);

        JPanel panelTituloInterno = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelTituloInterno.setBackground(BRANCO);
        JLabel lblInfo = new JLabel("Preencha os dados abaixo para registar uma nova qualificação no sistema.");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(TEXTO_MUTED);
        panelTituloInterno.add(lblInfo);
        panelAcoes.add(panelTituloInterno, BorderLayout.WEST);
        
        add(panelAcoes, BorderLayout.NORTH);

        // --- Formulário Centralizado Customizado ---
        JPanel panelFormContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelFormContainer.setBackground(BRANCO);

        // Grid Layout para organizar os 5 campos (5 linhas, 2 colunas)
        JPanel panelGridCampos = new JPanel(new GridLayout(5, 2, 20, 20));
        panelGridCampos.setBackground(BRANCO);
        panelGridCampos.setPreferredSize(new Dimension(750, 280));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);

        // 1. Nome da Qualificação
        panelGridCampos.add(new JLabel("Nome da Qualificação *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtNomeQualificacao = new JTextField();
        txtNomeQualificacao.setFont(fontText);
        panelGridCampos.add(txtNomeQualificacao);

        // 2. Módulos Associados
        panelGridCampos.add(new JLabel("Módulos Associados") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtModulosAssociados = new JTextField();
        txtModulosAssociados.setFont(fontText);
        panelGridCampos.add(txtModulosAssociados);

        // 3. Coordenador da Qualificação
        panelGridCampos.add(new JLabel("Coordenador") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtCoordenador = new JTextField();
        txtCoordenador.setFont(fontText);
        panelGridCampos.add(txtCoordenador);

        // 4. Nível da Qualificação
        panelGridCampos.add(new JLabel("Nível da Qualificação *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtNivelQualificacao = new JTextField();
        txtNivelQualificacao.setFont(fontText);
        panelGridCampos.add(txtNivelQualificacao);

        // 5. Campo Pertencente
        panelGridCampos.add(new JLabel("Campo Pertencente") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtCampoPertencente = new JTextField();
        txtCampoPertencente.setFont(fontText);
        panelGridCampos.add(txtCampoPertencente);

        panelFormContainer.add(panelGridCampos);
        add(panelFormContainer, BorderLayout.CENTER);

        // --- Barra de Comando Inferior (Botões) ---
        JPanel panelBotoesRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        panelBotoesRodape.setBackground(BRANCO);
        panelBotoesRodape.setBorder(new EmptyBorder(10, 0, 0, 0));

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(BRANCO);
        btnLimpar.setFont(fontLabel);
        btnLimpar.setPreferredSize(new Dimension(150, 40));
        btnLimpar.setBorder(new LineBorder(new Color(220, 224, 230)));
        btnLimpar.addActionListener(e -> limparCampos());

        btnSalvar = new JButton("Guardar Qualificação");
        btnSalvar.setBackground(AZUL_DESTAQUE);
        btnSalvar.setForeground(BRANCO);
        btnSalvar.setFont(fontLabel);
        btnSalvar.setPreferredSize(new Dimension(185, 40));
        btnSalvar.setBorder(null);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acaoSalvar();
            }
        });

        panelBotoesRodape.add(btnLimpar);
        panelBotoesRodape.add(btnSalvar);
        
        add(panelBotoesRodape, BorderLayout.SOUTH);
    }

    /**
     * Validação básica e persistência fictícia
     */
    private void acaoSalvar() {
        String nome = txtNomeQualificacao.getText().trim();
        String modulos = txtModulosAssociados.getText().trim();
        String coordenador = txtCoordenador.getText().trim();
        String nivel = txtNivelQualificacao.getText().trim();
        String campo = txtCampoPertencente.getText().trim();

        if (nome.isEmpty() || nivel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha os campos obrigatórios (*).", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String resumo = "Qualificação: " + nome + "\nNível: " + nivel + "\nCoordenador: " + coordenador;
        JOptionPane.showMessageDialog(this, "Qualificação guardada com sucesso!\n\n" + resumo, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
        limparCampos();
    }

    private void limparCampos() {
        txtNomeQualificacao.setText("");
        txtModulosAssociados.setText("");
        txtCoordenador.setText("");
        txtNivelQualificacao.setText("");
        txtCampoPertencente.setText("");
    }
}