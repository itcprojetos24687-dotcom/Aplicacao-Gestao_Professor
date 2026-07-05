package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Tela_Matricula extends JDialog {

    private JTextField txtEstudanteId, txtTurmaId;
    private JButton btnConfirmar;

    public Tela_Matricula(JFrame pai, boolean modal) {
        super(pai, modal);
        setTitle("Vincular Nova Matrícula");
        setSize(450, 300);
        setLocationRelativeTo(pai); // Centraliza sobre o painel principal
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(3, 1, 10, 15));
        painelCampos.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelCampos.setBackground(Color.WHITE);

        Font fLabel = new Font("Segoe UI", Font.BOLD, 13);

        painelCampos.add(new JLabel("ID / Código do Estudante:") {{ setFont(fLabel); }});
        txtEstudanteId = new JTextField(); painelCampos.add(txtEstudanteId);

        painelCampos.add(new JLabel("Código da Turma:") {{ setFont(fLabel); }});
        txtTurmaId = new JTextField(); painelCampos.add(txtTurmaId);

        getContentPane().add(painelCampos, BorderLayout.CENTER);

        // Barra inferior de confirmação
        JPanel barraBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        barraBotoes.setBackground(new Color(244, 246, 249));

        JButton btnFechar = new JButton("Cancelar");
        btnFechar.addActionListener(e -> dispose());
        
        btnConfirmar = new JButton("Efetivar Matrícula");
        btnConfirmar.setBackground(new Color(13, 110, 253));
        btnConfirmar.setForeground(Color.WHITE);

        barraBotoes.add(btnFechar);
        barraBotoes.add(btnConfirmar);
        getContentPane().add(barraBotoes, BorderLayout.SOUTH);
    }
}