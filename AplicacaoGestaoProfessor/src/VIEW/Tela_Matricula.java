package VIEW;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class Tela_Matricula extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private JTextField txtEstudanteId, txtTurmaId;
    private JButton btnConfirmar, btnCancelar;
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color FUNDO_CLARO      = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;
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
        JPanel panelCard = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCard.setBackground(BRANCO);
        panelCard.setBorder(new EmptyBorder(25, 25, 25, 25));
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);
        panelCard.add(new JLabel("Nome ou Código do Aluno:") {{ setFont(fontLabel); }});
        txtEstudanteId = new JTextField(); 
        txtEstudanteId.setFont(fontText); 
        panelCard.add(txtEstudanteId);
        panelCard.add(new JLabel("Código da Turma:") {{ setFont(fontLabel); }});
        txtTurmaId = new JTextField(); 
        txtTurmaId.setFont(fontText); 
        panelCard.add(txtTurmaId);
        // Barra de botões alinhada
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

    // Construtor sem listener: abre como janela independente, igual ao Cadastro_Formador
    public Tela_Matricula() {
        this(null);
        JFrame janela = new JFrame("Efetuar Matrícula");
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setSize(450, 280);
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
        String aluno = txtEstudanteId.getText().trim();
        String turma = txtTurmaId.getText().trim();
        if (aluno.isEmpty() || turma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos da matrícula.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Matrícula efetivada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        if (listener != null) {
            String dataCorrente = "07/07/2026";
            listener.onMatriculaCadastrada("MAT-TMP", aluno, turma, "Sala Definida", dataCorrente);
        }
        limparCampos();
    }
    private void limparCampos() {
        txtEstudanteId.setText("");
        txtTurmaId.setText("");
    }
}