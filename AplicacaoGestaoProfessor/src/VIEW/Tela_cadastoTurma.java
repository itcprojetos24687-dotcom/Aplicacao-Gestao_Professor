package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;

public class Tela_cadastoTurma extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtNomeTurma;
    private JComboBox<String> comboTurno, comboQualificacao, comboDiretor;
    private JSpinner spinnerAnoIngresso;
    private JButton btnSalvar, btnCancelar;

    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color FUNDO_CLARO      = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;

    // Interface para enviar a nova turma de volta à Tela Principal
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
        btnCancelar.addActionListener(e -> {
            limparCampos();
            if(listener != null) listener.onCancelar();
        });

        btnSalvar = new JButton("Gravar Turma");
        btnSalvar.setBackground(AZUL_DESTAQUE);
        btnSalvar.setForeground(BRANCO);
        btnSalvar.setFont(fontLabel);
        btnSalvar.addActionListener(e -> acaoSalvar());

        panelCard.add(btnCancelar);
        panelCard.add(btnSalvar);

        add(panelCard, BorderLayout.CENTER);
    }

    // Construtor sem listener: abre como janela independente, igual ao Cadastro_Formador
    public Tela_cadastoTurma() {
        this(null);
        JFrame janela = new JFrame("Cadastro de Turma");
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setSize(650, 480);
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

    private void acaoSalvar() {
        String nomeTurma = txtNomeTurma.getText().trim();
        
        if (nomeTurma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduza o nome da turma.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String turno = comboTurno.getSelectedItem().toString();
        String qualificacao = comboQualificacao.getSelectedItem().toString();
        String diretor = comboDiretor.getSelectedItem().toString();

        JOptionPane.showMessageDialog(this, "Turma guardada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
        // Notifica a Tela_Principal passando os dados estruturados para a tabela
        if (listener != null) {
            listener.onTurmaCadastrada(nomeTurma, qualificacao, turno, diretor);
        }
        
        limparCampos();
    }

    private void limparCampos() {
        txtNomeTurma.setText("");
        comboTurno.setSelectedIndex(0);
        spinnerAnoIngresso.setValue(2026);
        comboQualificacao.setSelectedIndex(0);
        comboDiretor.setSelectedIndex(0);
    }
}