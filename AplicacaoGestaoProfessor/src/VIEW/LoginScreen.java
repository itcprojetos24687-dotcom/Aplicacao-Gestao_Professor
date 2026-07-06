package VIEW;
import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("Gestão de Formação Profissional");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(Color.WHITE);

        // Título
        JLabel titulo = new JLabel("Gestão de Formação Profissional");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBounds(40, 30, 320, 30);
        painel.add(titulo);

        // Subtítulo
        JLabel subtitulo = new JLabel("Sistema de Gestão de Professores e Formação");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitulo.setBounds(40, 60, 320, 20);
        painel.add(subtitulo);

        // Label Usuário
        JLabel labelUsuario = new JLabel("Usuário");
        labelUsuario.setFont(new Font("Arial", Font.BOLD, 13));
        labelUsuario.setBounds(40, 110, 100, 25);
        painel.add(labelUsuario);

        // Campo Usuário
        JTextField campoUsuario = new JTextField("Digite seu usuário");
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 13));
        campoUsuario.setForeground(Color.GRAY);
        campoUsuario.setBounds(40, 135, 320, 35);
        painel.add(campoUsuario);

        // Label Senha
        JLabel labelSenha = new JLabel("Senha");
        labelSenha.setFont(new Font("Arial", Font.BOLD, 13));
        labelSenha.setBounds(40, 190, 100, 25);
        painel.add(labelSenha);

        // Campo Senha
        JPasswordField campoSenha = new JPasswordField("Digite sua senha");
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 13));
        campoSenha.setForeground(Color.GRAY);
        campoSenha.setEchoChar((char) 0);
        campoSenha.setBounds(40, 215, 320, 35);
        painel.add(campoSenha);

        // Checkbox Mostrar senha
        JCheckBox mostrarSenha = new JCheckBox("Mostrar senha");
        mostrarSenha.setFont(new Font("Arial", Font.PLAIN, 12));
        mostrarSenha.setBounds(40, 255, 120, 25);
        mostrarSenha.setBackground(Color.WHITE);
        painel.add(mostrarSenha);

        // Botão Entrar
        JButton entrar = new JButton("Entrar");
        entrar.setFont(new Font("Arial", Font.BOLD, 13));
        entrar.setBackground(new Color(0, 102, 204));
        entrar.setForeground(Color.WHITE);
        entrar.setBounds(70, 310, 110, 40);
        painel.add(entrar);

        // Botão Sair
        JButton sair = new JButton("Sair");
        sair.setFont(new Font("Arial", Font.BOLD, 13));
        sair.setBackground(Color.LIGHT_GRAY);
        sair.setBounds(210, 310, 110, 40);
        painel.add(sair);

        add(painel);
    }

    public static void main(String[] args) {
        new LoginScreen().setVisible(true);
    }
}
