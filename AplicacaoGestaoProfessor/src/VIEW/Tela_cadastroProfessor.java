package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Tela_cadastroProfessor extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtNome, txtApelido, txtEmail, txtTelefone, txtArea;
    private JComboBox<String> cbSexo;
    private JButton btnConfirmar, btnCancelar;

    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color FUNDO_CLARO      = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;

 
    public interface OnProfessorCadastradoListener {
        void onProfessorCadastrado(String codigo, String nome, String apelido, String sexo, String email, String telefone, String area, String estado);
        void onCancelar();
    }

    private OnProfessorCadastradoListener listener;

    public Tela_cadastroProfessor() {
        this.listener = listener;

        setLayout(new BorderLayout());
        setBackground(FUNDO_CLARO);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel do formulário em malha (2 colunas para organizar melhor os campos)
        JPanel panelCard = new JPanel(new GridLayout(6, 2, 15, 15));
        panelCard.setBackground(BRANCO);
        panelCard.setBorder(new EmptyBorder(25, 25, 25, 25));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);

       
        JPanel pNome = new JPanel(new BorderLayout(0, 5)); pNome.setBackground(BRANCO);
        pNome.add(new JLabel("Nome:") {{ setFont(fontLabel); }}, BorderLayout.NORTH);
        txtNome = new JTextField(); txtNome.setFont(fontText); pNome.add(txtNome, BorderLayout.CENTER);
        panelCard.add(pNome);

       
        JPanel pApelido = new JPanel(new BorderLayout(0, 5)); pApelido.setBackground(BRANCO);
        pApelido.add(new JLabel("Apelido:") {{ setFont(fontLabel); }}, BorderLayout.NORTH);
        txtApelido = new JTextField(); txtApelido.setFont(fontText); pApelido.add(txtApelido, BorderLayout.CENTER);
        panelCard.add(pApelido);

       
        JPanel pSexo = new JPanel(new BorderLayout(0, 5)); pSexo.setBackground(BRANCO);
        pSexo.add(new JLabel("Sexo:") {{ setFont(fontLabel); }}, BorderLayout.NORTH);
        cbSexo = new JComboBox<>(new String[]{"Masculino", "Feminino"}); cbSexo.setFont(fontText); pSexo.add(cbSexo, BorderLayout.CENTER);
        panelCard.add(pSexo);


        JPanel pEmail = new JPanel(new BorderLayout(0, 5)); pEmail.setBackground(BRANCO);
        pEmail.add(new JLabel("Email:") {{ setFont(fontLabel); }}, BorderLayout.NORTH);
        txtEmail = new JTextField(); txtEmail.setFont(fontText); pEmail.add(txtEmail, BorderLayout.CENTER);
        panelCard.add(pEmail);


        JPanel pTelefone = new JPanel(new BorderLayout(0, 5)); pTelefone.setBackground(BRANCO);
        pTelefone.add(new JLabel("Telefone:") {{ setFont(fontLabel); }}, BorderLayout.NORTH);
        txtTelefone = new JTextField(); txtTelefone.setFont(fontText); pTelefone.add(txtTelefone, BorderLayout.CENTER);
        panelCard.add(pTelefone);

        
        JPanel pArea = new JPanel(new BorderLayout(0, 5)); pArea.setBackground(BRANCO);
        pArea.add(new JLabel("Área de Atuação:") {{ setFont(fontLabel); }}, BorderLayout.NORTH);
        txtArea = new JTextField(); txtArea.setFont(fontText); pArea.add(txtArea, BorderLayout.CENTER);
        panelCard.add(pArea);


        JPanel barraBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        barraBotoes.setBackground(BRANCO);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(fontLabel);
        btnCancelar.setBackground(BRANCO);
        btnCancelar.addActionListener(e -> {
            limparCampos();
            if (listener != null) listener.onCancelar();
        });

        btnConfirmar = new JButton("Cadastrar Professor");
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

    private void acaoSalvar() {
        String nome = txtNome.getText().trim();
        String apelido = txtApelido.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String area = txtArea.getText().trim();
        String sexo = cbSexo.getSelectedItem().toString();

        if (nome.isEmpty() || apelido.isEmpty() || email.isEmpty() || telefone.isEmpty() || area.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos do professor.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Professor cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        if (listener != null) {
        
            listener.onProfessorCadastrado("PRF-TMP", nome, apelido, sexo, email, telefone, area, "Ativo");
        }

        limparCampos();
    }

    private void limparCampos() {
        txtNome.setText("");
        txtApelido.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtArea.setText("");
        cbSexo.setSelectedIndex(0);
    }
}