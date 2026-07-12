package VIEW;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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

public class Tela_Matricula extends JPanel {
    private static final long serialVersionUID = 1L;

    private JComboBox<String> comboFormando;
    private JComboBox<String> comboQualificacao;
    private JComboBox<String> comboNivel;
    private JTextField txtDataMatricula;
    private JButton btnConfirmar, btnCancelar;

    private final Color AZUL_DESTAQUE = new Color(13, 110, 253);
    private final Color FUNDO_CLARO   = new Color(244, 246, 249);
    private final Color BRANCO        = Color.WHITE;

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
        Font fontText  = new Font("Segoe UI", Font.PLAIN, 14);

        // Nome do Formando
        panelCard.add(new JLabel("Nome do Formando *") {{ setFont(fontLabel); }});
        comboFormando = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "Edmundo Mapotere", "Malik Mangue", "Keany pessula"   }));
        comboFormando.setFont(fontText);
        panelCard.add(comboFormando);

        // Qualificação
        panelCard.add(new JLabel("Qualificação *") {{ setFont(fontLabel); }});
        comboQualificacao = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "Suporte Informatico", "Programção Web", "Administração de Redes"
        }));
        comboQualificacao.setFont(fontText);
        panelCard.add(comboQualificacao);

        // Nível
        panelCard.add(new JLabel("Nível *") {{ setFont(fontLabel); }});
        comboNivel = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
             "CV 3", "CV 4", "CV 5"
        }));
        comboNivel.setFont(fontText);
        panelCard.add(comboNivel);

        // Data da Matrícula
        panelCard.add(new JLabel("Data da Matrícula *") {{ setFont(fontLabel); }});
        txtDataMatricula = new JTextField();
        txtDataMatricula.setFont(fontText);
        txtDataMatricula.setToolTipText("dd/mm/aaaa");
        panelCard.add(txtDataMatricula);

        // Barra de botões
        JPanel barraBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        barraBotoes.setBackground(BRANCO);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(fontLabel);
        btnCancelar.setBackground(BRANCO);
        btnCancelar.addActionListener(e -> {
            limparCampos();
            if (listener != null) listener.onCancelar();
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

    private void acaoSalvar() {
        String formando = (String) comboFormando.getSelectedItem();
        String data     = txtDataMatricula.getText().trim();

        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha a data da matrícula.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Matrícula efetivada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        if (listener != null) {
            listener.onMatriculaCadastrada("MAT-TMP", formando,
                (String) comboQualificacao.getSelectedItem(),
                (String) comboNivel.getSelectedItem(),
                data);
        }
        limparCampos();
    }

    private void limparCampos() {
        comboFormando.setSelectedIndex(0);
        comboQualificacao.setSelectedIndex(0);
        comboNivel.setSelectedIndex(0);
        txtDataMatricula.setText("");
    }
}