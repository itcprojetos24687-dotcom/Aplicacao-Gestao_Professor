package VIEW;
import javax.swing.*;
import java.awt.*;

public class TelaResetSenha extends JFrame {

    public TelaResetSenha() {
       
        setTitle("Sistema de Professores - Reset");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    
        JPanel painelFundo = new JPanel();
        painelFundo.setBackground(Color.BLUE); 
        painelFundo.setLayout(null);
        getContentPane().add(painelFundo);

     
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBounds(50, 50, 400, 400); 
        card.setLayout(null);
        painelFundo.add(card);

      
        JLabel lblTitulo = new JLabel("Redefinir Senha", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 41, 59));
        lblTitulo.setBounds(0, 30, 400, 30);
        card.add(lblTitulo);


        JLabel lblEmail = new JLabel("E-mail do Professor:");
        lblEmail.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        lblEmail.setBounds(40, 90, 200, 20);
        card.add(lblEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(40, 115, 320, 35);
        card.add(txtEmail);


        JLabel lblCodigo = new JLabel("Código de Recuperação:");
        lblCodigo.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        lblCodigo.setBounds(40, 160, 200, 20);
        card.add(lblCodigo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(40, 185, 320, 35);
        card.add(txtCodigo);

 
        JLabel lblSenha = new JLabel("Nova Senha:");
        lblSenha.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        lblSenha.setBounds(40, 230, 200, 20);
        card.add(lblSenha);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(40, 255, 320, 35);
        card.add(txtSenha);


        JButton btnConfirmar = new JButton("ATUALIZAR SENHA");
        btnConfirmar.setBounds(40, 320, 320, 45);
        btnConfirmar.setBackground(new Color(59, 130, 246)); 
        btnConfirmar.setForeground(Color.BLACK); 
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnConfirmar.setFocusPainted(false); 
        card.add(btnConfirmar);


        btnConfirmar.addActionListener(e -> {
            String email = txtEmail.getText();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite o e-mail do professor!");
            } else {
                JOptionPane.showMessageDialog(null, "Senha de " + email + " alterada com sucesso!");
                dispose();
            }
        });
    }

    public static void main(String[] args) {

        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        
        SwingUtilities.invokeLater(() -> {
            new TelaResetSenha().setVisible(true);
        });
    }
}