package VIEW;

import javax.swing.*;
import java.awt.*;

public class TelaResetSenha extends JFrame {

    public TelaResetSenha() {
        
        setTitle("Sistema de Professores - Reset de Senha");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Alterado para não fechar a app inteira caso venha de outra tela
        setLocationRelativeTo(null);
        
     
        JPanel painelFundo = new JPanel();
        painelFundo.setBackground(new Color(0, 51, 102)); 
        painelFundo.setLayout(null);
        getContentPane().add(painelFundo);

       
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBounds(38, 50, 400, 400); 
        card.setLayout(null);
        painelFundo.add(card);

  
        JLabel lblTitulo = new JLabel("Redefinir Senha", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 41, 59));
        lblTitulo.setBounds(0, 30, 400, 30);
        card.add(lblTitulo);

 
        JLabel lblUtilizador = new JLabel("Nome do Utilizador");
        lblUtilizador.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        lblUtilizador.setBounds(40, 90, 200, 20);
        card.add(lblUtilizador);

        JTextField txtUtilizador = new JTextField();
        txtUtilizador.setBounds(40, 115, 320, 35);
        card.add(txtUtilizador);

        JLabel lblNovaSenha = new JLabel("Nova Senha");
        lblNovaSenha.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        lblNovaSenha.setBounds(40, 160, 200, 20);
        card.add(lblNovaSenha);

        JPasswordField txtNovaSenha = new JPasswordField();
        txtNovaSenha.setBounds(40, 185, 320, 35);
        card.add(txtNovaSenha);

    
        JLabel lblConfirmarSenha = new JLabel("Confirmar Senha");
        lblConfirmarSenha.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        lblConfirmarSenha.setBounds(40, 230, 200, 20);
        card.add(lblConfirmarSenha);

        JPasswordField txtConfirmarSenha = new JPasswordField();
        txtConfirmarSenha.setBounds(40, 255, 320, 35);
        card.add(txtConfirmarSenha);

     
        JButton btnConfirmar = new JButton("ATUALIZAR SENHA");
        btnConfirmar.setBounds(40, 320, 320, 45);
        btnConfirmar.setBackground(new Color(59, 130, 246)); 
        btnConfirmar.setForeground(Color.WHITE); // Texto branco para melhor contraste com o fundo azul
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnConfirmar.setFocusPainted(false); 
        card.add(btnConfirmar);

     
        btnConfirmar.addActionListener(e -> {
            String utilizador = txtUtilizador.getText().trim();
            String novaSenha = new String(txtNovaSenha.getPassword());
            String confirmarSenha = new String(txtConfirmarSenha.getPassword());

           
            if (utilizador.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, digite o nome do utilizador!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

          
            if (novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha os campos de senha!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

       
            if (!novaSenha.equals(confirmarSenha)) {
                JOptionPane.showMessageDialog(this, "As senhas digitadas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

           
            JOptionPane.showMessageDialog(this, "Senha do utilizador '" + utilizador + "' alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
    }

    public static void main(String[] args) {
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new TelaResetSenha().setVisible(true);
        });
    }
}