package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;

public class Tela_cadastoTurma extends JFrame {

    private static final long serialVersionUID = 1L;
    

    private JTextField txtNomeTurma;
    private JComboBox<String> comboTurno, comboQualificacao, comboDiretor;
    private JSpinner spinnerAnoIngresso;
    private JButton btnSalvar, btnCancelar;


    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE  = new Color(13, 110, 253);
    private final Color FUNDO_CLARO     = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;

  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Tela_cadastoTurma().setVisible(true);
        });
    }

    public Tela_cadastoTurma() {
        setTitle("AcademiaPro - Cadastrar Nova Turma");
        setSize(600, 550); 
        setMinimumSize(new Dimension(550, 500));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

     
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(AZUL_ESCURO_NAV);
        panelHeader.setBorder(new EmptyBorder(15, 25, 15, 25));
        
        JLabel lblTitulo = new JLabel("Cadastrar Turma");
        lblTitulo.setForeground(BRANCO);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelHeader.add(lblTitulo, BorderLayout.WEST);
        
        getContentPane().add(panelHeader, BorderLayout.NORTH);

   
        JPanel panelConteudo = new JPanel(new BorderLayout());
        panelConteudo.setBackground(FUNDO_CLARO);
        panelConteudo.setBorder(new EmptyBorder(25, 35, 25, 35));


        JPanel panelCard = new JPanel(new GridLayout(6, 2, 15, 20));
        panelCard.setBackground(BRANCO);
        panelCard.setBorder(new EmptyBorder(25, 25, 25, 25));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);


        panelCard.add(new JLabel("Nome da Turma:") {{ setFont(fontLabel); }});
        txtNomeTurma = new JTextField(); 
        txtNomeTurma.setFont(fontText); 
        panelCard.add(txtNomeTurma);


        panelCard.add(new JLabel("Turno:") {{ setFont(fontLabel); }});
        comboTurno = new JComboBox<>();
        comboTurno.setModel(new DefaultComboBoxModel<>(new String[] {"Manhã", "Tarde", "Noite"}));
        comboTurno.setFont(fontText);
        panelCard.add(comboTurno);


        panelCard.add(new JLabel("Ano de Ingresso:") {{ setFont(fontLabel); }});
        spinnerAnoIngresso = new JSpinner(new SpinnerNumberModel(2026, 2000, 2100, 1));
        spinnerAnoIngresso.setFont(fontText);
      
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinnerAnoIngresso, "#");
        spinnerAnoIngresso.setEditor(editor);
        panelCard.add(spinnerAnoIngresso);

   
        panelCard.add(new JLabel("Qualificação:") {{ setFont(fontLabel); }});
        comboQualificacao = new JComboBox<>();
        comboQualificacao.setModel(new DefaultComboBoxModel<>(new String[] {
            "Técnico de Programação Web", 
            "Suporte Informático", 
            "Administração de Redes", 
            "Gestão de Sistemas"
        }));
        comboQualificacao.setFont(fontText);
        panelCard.add(comboQualificacao);

   
        panelCard.add(new JLabel("Diretor de Turma:") {{ setFont(fontLabel); }});
        comboDiretor = new JComboBox<>();
        comboDiretor.setModel(new DefaultComboBoxModel<>(new String[] {
            "Malik Mangue", 
            "Keany Pessula", 
            "Edmundo Mapotere"
        }));
        comboDiretor.setFont(fontText);
        panelCard.add(comboDiretor);

  
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(BRANCO);
        btnCancelar.setFont(fontLabel);
        btnCancelar.addActionListener(e -> dispose());

        btnSalvar = new JButton("Gravar Turma");
        btnSalvar.setBackground(AZUL_DESTAQUE);
        btnSalvar.setForeground(BRANCO);
        btnSalvar.setFont(fontLabel);
        btnSalvar.addActionListener(e -> acaoSalvar());

        panelCard.add(btnCancelar);
        panelCard.add(btnSalvar);

        panelConteudo.add(panelCard, BorderLayout.CENTER);
        getContentPane().add(panelConteudo, BorderLayout.CENTER);
    }

    
    private void acaoSalvar() {
        String nomeTurma = txtNomeTurma.getText().trim();
        
        if (nomeTurma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduza o nome da turma.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String turno = comboTurno.getSelectedItem().toString();
        int ano = (int) spinnerAnoIngresso.getValue();
        String qualificacao = comboQualificacao.getSelectedItem().toString();
        String diretor = comboDiretor.getSelectedItem().toString();

      
        String resumo = "Turma: " + nomeTurma + "\nTurno: " + turno + "\nAno: " + ano + 
                        "\nQualificação: " + qualificacao + "\nDiretor: " + diretor;
                        
        JOptionPane.showMessageDialog(this, "Turma guardada com sucesso!\n\n" + resumo, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        limparCampos();
        dispose();
    }

    private void limparCampos() {
        txtNomeTurma.setText("");
        comboTurno.setSelectedIndex(0);
        spinnerAnoIngresso.setValue(2026);
        comboQualificacao.setSelectedIndex(0);
        comboDiretor.setSelectedIndex(0);
    }
}