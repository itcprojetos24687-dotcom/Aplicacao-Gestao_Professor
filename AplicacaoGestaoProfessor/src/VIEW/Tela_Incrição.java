package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

// Alterado de JFrame para JPanel para encaixar dinamicamente no painel central da Tela Principal
public class Tela_Incrição extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtNomeFormando, txtDataInscricao;
    private JComboBox<String> comboModulo, comboSemestre;
    private JButton btnGravar, btnLimpar;
    private JTable tabelaHistorico;
    private DefaultTableModel modeloTabela;

    // Paleta de Cores Alinhada com a Tela_Principal
    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color FUNDO_CLARO     = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

    public Tela_Incrição() {
        // Define o layout e cor de fundo do container principal
        setLayout(new BorderLayout(0, 15));
        setBackground(BRANCO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 13);

        // --- PAINEL DO FORMULÁRIO (ESTILO CARD) ---
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 15, 12));
        panelForm.setBackground(BRANCO);
        
        panelForm.add(new JLabel("Nome do Formando:") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtNomeFormando = new JTextField(); 
        txtNomeFormando.setFont(fontText);
        panelForm.add(txtNomeFormando);

        panelForm.add(new JLabel("Módulo:") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        comboModulo = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "Programação Orientada a Objetos", "Bases de Dados", "Desenvolvimento Web", "Redes de Computadores"
        }));
        comboModulo.setBackground(BRANCO);
        comboModulo.setFont(fontText);
        panelForm.add(comboModulo);

        panelForm.add(new JLabel("Data de Inscrição:") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtDataInscricao = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        txtDataInscricao.setFont(fontText);
        panelForm.add(txtDataInscricao);

        panelForm.add(new JLabel("Semestre:") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        comboSemestre = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "Primeiro semestre", "Segundo semestre"
        }));
        comboSemestre.setBackground(BRANCO);
        comboSemestre.setFont(fontText);
        panelForm.add(comboSemestre);

        // --- BOTÕES DE AÇÃO ---
        btnLimpar = new JButton("Limpar Campos");
        btnLimpar.setBackground(BRANCO);
        btnLimpar.setFont(fontLabel);
        btnLimpar.setPreferredSize(new Dimension(120, 35));
        btnLimpar.addActionListener(e -> limparCampos());
        panelForm.add(btnLimpar);

        btnGravar = new JButton("Gravar Inscrição");
        btnGravar.setBackground(AZUL_DESTAQUE);
        btnGravar.setForeground(BRANCO);
        btnGravar.setFont(fontLabel);
        btnGravar.setPreferredSize(new Dimension(140, 35));
        btnGravar.addActionListener(e -> acaoSalvar());
        panelForm.add(btnGravar);

        add(panelForm, BorderLayout.NORTH);

        // --- SEÇÃO DO HISTÓRICO / TABELA ---
        JPanel panelHistorico = new JPanel(new BorderLayout(0, 8));
        panelHistorico.setBackground(BRANCO);
        panelHistorico.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        JLabel lblHistorico = new JLabel("Histórico de Inscrições Recentes");
        lblHistorico.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblHistorico.setForeground(AZUL_ESCURO_NAV);
        panelHistorico.add(lblHistorico, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(
            new Object[][] {
                {"Lucas Silva", "Bases de Dados", "02/07/2026", "Primeiro semestre"},
                {"Maria Santos", "Desenvolvimento Web", "01/07/2026", "Segundo semestre"}
            },
            new String[] {"Formando", "Módulo", "Data", "Semestre"}
        );

        tabelaHistorico = new JTable(modeloTabela);
        tabelaHistorico.setRowHeight(35); // Mantendo o padrão alto e elegante da tabela de formadores
        tabelaHistorico.setFont(fontText);
        tabelaHistorico.getTableHeader().setFont(fontLabel);
        tabelaHistorico.getTableHeader().setBackground(new Color(248, 249, 250));
        
        JScrollPane scroll = new JScrollPane(tabelaHistorico);
        panelHistorico.add(scroll, BorderLayout.CENTER);

        add(panelHistorico, BorderLayout.CENTER);
    }

    private void acaoSalvar() {
        String nome = txtNomeFormando.getText().trim();
        String modulo = comboModulo.getSelectedItem().toString();
        String data = txtDataInscricao.getText().trim();
        String semestre = comboSemestre.getSelectedItem().toString();

        if (nome.isEmpty() || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Insere no topo da tabela
        modeloTabela.insertRow(0, new Object[]{nome, modulo, data, semestre});
        
        JOptionPane.showMessageDialog(this, "Inscrição efetuada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        limparCampos();
    }

    private void limparCampos() {
        txtNomeFormando.setText("");
        comboModulo.setSelectedIndex(0);
        comboSemestre.setSelectedIndex(0);
        txtDataInscricao.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    }
}