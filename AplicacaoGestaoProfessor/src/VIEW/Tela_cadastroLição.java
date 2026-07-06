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

public class Tela_cadastroLição extends JPanel {

    private static final long serialVersionUID = 1L;
    
    // Componentes de Entrada de Dados com nomes limpos e intuitivos
    private JTextField txtModuloAula;
    private JTextField txtTurmaParticipante;
    private JTextField txtModulosAssociados;
    private JTextField txtDataAula;
    private JTextField txtHoraAula;
    private JButton btnSalvar, btnLimpar;

    // Paleta de Cores unificada AcademiaPro
    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

    public Tela_cadastroLição() {
        // Alinhado ao plano de design do painel central responsivo
        setLayout(new BorderLayout(0, 15));
        setBackground(BRANCO);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // --- Barra de Ações Superior (Idêntico ao padrão do sistema) ---
        JPanel panelAcoes = new JPanel(new BorderLayout());
        panelAcoes.setBackground(BRANCO);

        JPanel panelTituloInterno = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelTituloInterno.setBackground(BRANCO);
        JLabel lblInfo = new JLabel("Preencha os dados abaixo para registar um novo sumário/lição no sistema.");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(TEXTO_MUTED);
        panelTituloInterno.add(lblInfo);
        panelAcoes.add(panelTituloInterno, BorderLayout.WEST);
        
        add(panelAcoes, BorderLayout.NORTH);

        // --- Área do Formulário Customizado ---
        JPanel panelFormContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelFormContainer.setBackground(BRANCO);

        // Grid Layout estruturado (5 linhas, 2 colunas)
        JPanel panelGridCampos = new JPanel(new GridLayout(5, 2, 20, 20));
        panelGridCampos.setBackground(BRANCO);
        panelGridCampos.setPreferredSize(new Dimension(750, 280));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);

        // 1. Módulo da Aula
        panelGridCampos.add(new JLabel("Módulo da Aula *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtModuloAula = new JTextField();
        txtModuloAula.setFont(fontText);
        panelGridCampos.add(txtModuloAula);

        // 2. Data da Aula
        panelGridCampos.add(new JLabel("Data da Aula *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtDataAula = new JTextField();
        txtDataAula.setFont(fontText);
        panelGridCampos.add(txtDataAula);

        // 3. Turma Participante
        panelGridCampos.add(new JLabel("Turma Participante *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtTurmaParticipante = new JTextField();
        txtTurmaParticipante.setFont(fontText);
        panelGridCampos.add(txtTurmaParticipante);

        // 4. Hora da Aula
        panelGridCampos.add(new JLabel("Hora da Aula *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtHoraAula = new JTextField();
        txtHoraAula.setFont(fontText);
        panelGridCampos.add(txtHoraAula);

        // 5. Módulos Associados
        panelGridCampos.add(new JLabel("Módulos Associados") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtModulosAssociados = new JTextField();
        txtModulosAssociados.setFont(fontText);
        panelGridCampos.add(txtModulosAssociados);

        panelFormContainer.add(panelGridCampos);
        add(panelFormContainer, BorderLayout.CENTER);

        // --- Barra de Comando Inferior (Botões) ---
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
     * Validação básica e salvamento simulado
     */
    private void acaoSalvar() {
        String modulo = txtModuloAula.getText().trim();
        String data = txtDataAula.getText().trim();
        String turma = txtTurmaParticipante.getText().trim();
        String hora = txtHoraAula.getText().trim();
        txtModulosAssociados.getText().trim();

        if (modulo.isEmpty() || data.isEmpty() || turma.isEmpty() || hora.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha os campos obrigatórios (*).", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String resumo = "Módulo: " + modulo + "\nTurma: " + turma + "\nData/Hora: " + data + " às " + hora;
        JOptionPane.showMessageDialog(this, "Lição guardada com sucesso!\n\n" + resumo, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
        limparCampos();
    }

    private void limparCampos() {
        txtModuloAula.setText("");
        txtDataAula.setText("");
        txtTurmaParticipante.setText("");
        txtHoraAula.setText("");
        txtModulosAssociados.setText("");
    }
}