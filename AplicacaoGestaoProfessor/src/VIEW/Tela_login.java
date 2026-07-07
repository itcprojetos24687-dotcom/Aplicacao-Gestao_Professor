package VIEW;
import javax.swing.*;

import controller.*;
import model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela_login extends JFrame implements ActionListener{
    private CardLayout cardLayout;
    private JPanel cardPanel;      
    private JPanel leftPanel;
    private JLabel leftTitle, leftSub, leftDesc;

  
    private JTextField usuarioField;
    private JPasswordField senhaField;
    private JCheckBox mostrarSenhaCheckBox;


 
    private JPasswordField senhaAtualField;
    private JPasswordField novaSenhaField;
    private JPasswordField confirmarSenhaField;
    
  

	   
	    private Usuario utilizadorlogado;
	    public static void main(String[]args) {
	    	Tela_login tl = new Tela_login();
	    	tl.setVisible(true);
	    	
	    }
	   


       
    


    public Tela_login() {
        setTitle("Sistema de Gestão Acadêmica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(Color.WHITE);

        // ========== PAINEL LATERAL ESQUERDO ==========
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0, 70, 140));
        leftPanel.setPreferredSize(new Dimension(350, 0));
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(15, 15, 15, 15);
        gbcLeft.gridx = 0;
        gbcLeft.anchor = GridBagConstraints.CENTER;

        JLabel iconLabel = new JLabel("🎓");
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 100));
        iconLabel.setForeground(Color.WHITE);
        gbcLeft.gridy = 0;
        leftPanel.add(iconLabel, gbcLeft);

        leftTitle = new JLabel("Sistema de Gestão");
        leftTitle.setFont(new Font("Segoe UI", Font.BOLD, 34));
        leftTitle.setForeground(Color.WHITE);
        gbcLeft.gridy = 1;
        leftPanel.add(leftTitle, gbcLeft);

        leftSub = new JLabel("Acadêmica");
        leftSub.setFont(new Font("Segoe UI", Font.BOLD, 34));
        leftSub.setForeground(Color.WHITE);
        gbcLeft.gridy = 2;
        leftPanel.add(leftSub, gbcLeft);

        leftDesc = new JLabel("Login");
        leftDesc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        leftDesc.setForeground(new Color(200, 220, 255));
        gbcLeft.gridy = 3;
        leftPanel.add(leftDesc, gbcLeft);


        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(Color.WHITE);

        JPanel loginPanel = criarLoginPanel();
        cardPanel.add(loginPanel, "login");

        JPanel resetPanel = criarResetPanel();
        cardPanel.add(resetPanel, "reset");

        rootPanel.add(leftPanel, BorderLayout.WEST);
        rootPanel.add(cardPanel, BorderLayout.CENTER);

        add(rootPanel);
        atualizarLeftPanel("login");
    }

    private JPanel criarLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 25, 10, 25);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel formTitle = new JLabel("Login do Sistema");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(50, 25, 40, 25);
        panel.add(formTitle, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(12, 25, 12, 25);

        JLabel usuarioLabel = new JLabel("Operador");
        usuarioLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(usuarioLabel, gbc);

        usuarioField = new JTextField(30);
        usuarioField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(usuarioField, gbc);

        JLabel senhaLabel = new JLabel("Senha");
        senhaLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panel.add(senhaLabel, gbc);

        senhaField = new JPasswordField(30);
        senhaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        senhaField.setEchoChar('*');
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(senhaField, gbc);

        mostrarSenhaCheckBox = new JCheckBox("Mostrar senha");
        mostrarSenhaCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mostrarSenhaCheckBox.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.insets = new Insets(5, 25, 25, 25);
        panel.add(mostrarSenhaCheckBox, gbc);

        mostrarSenhaCheckBox.addActionListener(e -> {
            if (mostrarSenhaCheckBox.isSelected()) {
                senhaField.setEchoChar((char) 0);
            } else {
                senhaField.setEchoChar('*');
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBackground(Color.WHITE);
        
	        JButton entrarButton = new JButton("Entrar");
	        entrarButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
	        entrarButton.setBackground(new Color(0, 120, 215));
	        entrarButton.setForeground(Color.WHITE);
	        entrarButton.setFocusPainted(false);
	        entrarButton.setPreferredSize(new Dimension(140, 55));
	        entrarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	boolean sucesso;
	            	PerfilController pc = new PerfilController();
	            	try{
	            		String username = usuarioField.getText();
	            		String senha = senhaField.getText();
	            		UsuarioController uc = new UsuarioController();
	            		Perfil p = null;
	            		utilizadorlogado = uc.login(username, senha);
	            		Seccao.iniciarSeccao(utilizadorlogado);
	            		
	            		if(utilizadorlogado != null) {
	            			if(utilizadorlogado.isPrimeiroAcesso()) {
	            				cardLayout.show(cardPanel, "reset");
	        	                atualizarLeftPanel("reset");
	            			}
	            			else {
	            				p = pc.getPerfil(username);
	            				Tela_Principal tl = new Tela_Principal(p,Tela_login.this);
	            				tl.abrir();
	            				Tela_login.this.setVisible(false);
	            				JOptionPane.showMessageDialog(Tela_login.this, "Login efetuado!");
	            			}
	            		}
	            		else {
	            			JOptionPane.showMessageDialog(Tela_login.this, "Falha no login, Tente Novamente!");
	            		}
	            	}catch(Exception x) {
	            		x.printStackTrace();
	            		JOptionPane.showMessageDialog(Tela_login.this, "Introduca todos os dados corretamente!");
	            	}
	               // JOptionPane.showMessageDialog(Tela_login.this, "Login efetuado!");
	            }
	        });

         entrarButton = new JButton("Entrar");
        entrarButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        entrarButton.setBackground(new Color(0, 120, 215));
        entrarButton.setForeground(Color.WHITE);
        entrarButton.setFocusPainted(false);
        entrarButton.setPreferredSize(new Dimension(140, 55));
        entrarButton.addActionListener(e -> {
            PerfilController pc = new PerfilController();
            try {
                String username = usuarioField.getText().trim();
                String senha = new String(senhaField.getPassword()); // Correção do getText()

                if (username.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(Tela_login.this, "Por favor, preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                UsuarioController uc = new UsuarioController();
                utilizadorlogado = uc.login(username, senha);
                
                if (utilizadorlogado != null) {
                    Perfil p = pc.getPerfil(username);
                    Tela_Principal tl = new Tela_Principal(p, Tela_login.this);
                    tl.abrir();
                    Tela_login.this.setVisible(false);
                    JOptionPane.showMessageDialog(Tela_login.this, "Login efetuado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(Tela_login.this, "Falha no login. Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception x) {
                x.printStackTrace();
                JOptionPane.showMessageDialog(Tela_login.this, "Erro ao processar login: " + x.getMessage(), "Erro Técnico", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton sairButton = new JButton("Sair");
        sairButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sairButton.setPreferredSize(new Dimension(140, 55));
        sairButton.addActionListener(e -> System.exit(0));

        JButton redefinirButton = new JButton("Redefinir Senha");
        redefinirButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        redefinirButton.setBorderPainted(false);
        redefinirButton.setContentAreaFilled(false);
        redefinirButton.setForeground(new Color(0, 120, 215));
        redefinirButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        redefinirButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "reset");
            atualizarLeftPanel("reset");
        });

        buttonPanel.add(entrarButton);
        buttonPanel.add(sairButton);
        buttonPanel.add(redefinirButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 25, 50, 25);
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private JPanel criarResetPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 25, 10, 25);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel formTitle = new JLabel("Redefinir Senha");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(40, 25, 10, 25);
        panel.add(formTitle, gbc);

        JLabel subtitle = new JLabel("Por favor, defina uma nova senha segura");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitle.setForeground(Color.GRAY);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 25, 30, 25);
        panel.add(subtitle, gbc);

	        senhaAtualField = new JPasswordField(30);
	        senhaAtualField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	        senhaAtualField.setEchoChar((char) 0);
	        
	       
	        gbc.gridx = 1;
	        gbc.gridy = 2;
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        gbc.weightx = 1.0;
	        panel.add(senhaAtualField, gbc);
	        
//	        mostrarSenhaCheckBox = new JCheckBox("Mostrar senha");
//	        mostrarSenhaCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//	        mostrarSenhaCheckBox.setBackground(Color.WHITE);
//	        gbc.gridx = 1;
//	        gbc.gridy = 3;
//	        gbc.fill = GridBagConstraints.NONE;
//	        gbc.weightx = 0;
//	        gbc.insets = new Insets(5, 25, 25, 25);
//	        panel.add(mostrarSenhaCheckBox, gbc);
//	        
//	        mostrarSenhaCheckBox.addActionListener(new ActionListener() {
//	        	@Override
//	        	public void actionPerformed(ActionEvent e) {
//	        		if(mostrarSenhaCheckBox.isSelected()) {
//	        			senhaAtualField.setEchoChar((char) 0);
//	        		}
//	        		else {
//	        			senhaAtualField.setEchoChar('*');
//	        		}
//	        	}
//	        });

        gbc.gridwidth = 1;
        gbc.insets = new Insets(12, 25, 12, 25);


        JLabel atualLabel = new JLabel("Senha Atual");
        atualLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(atualLabel, gbc);
	        novaSenhaField = new JPasswordField(30);
	        novaSenhaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	        novaSenhaField.setEchoChar((char) 0);
	        gbc.gridx = 1;
	        gbc.gridy = 3;
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        gbc.weightx = 1.0;
	        panel.add(novaSenhaField, gbc);
	        
//	        mostrarSenhaCheckBox = new JCheckBox("Mostrar senha");
//	        mostrarSenhaCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//	        mostrarSenhaCheckBox.setBackground(Color.WHITE);
//	        gbc.gridx = 1;
//	        gbc.gridy = 3;
//	        gbc.fill = GridBagConstraints.NONE;
//	        gbc.weightx = 0;
//	        gbc.insets = new Insets(5, 25, 25, 25);
//	        panel.add(mostrarSenhaCheckBox, gbc);
//	        
//	        mostrarSenhaCheckBox.addActionListener(new ActionListener() {
//	        	@Override
//	        	public void actionPerformed(ActionEvent e) {
//	        		if(mostrarSenhaCheckBox.isSelected()) {
//	        			novaSenhaField.setEchoChar((char) 0);
//	        		}
//	        		else {
//	        			novaSenhaField.setEchoChar('*');
//	        		}
//	        	}
//	        });
	        

        senhaAtualField = new JPasswordField(30);
        senhaAtualField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        senhaAtualField.setEchoChar('*');
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(senhaAtualField, gbc);

        JLabel novaLabel = new JLabel("Nova Senha");
        novaLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panel.add(novaLabel, gbc);


        novaSenhaField = new JPasswordField(30);
        novaSenhaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        novaSenhaField.setEchoChar('*');
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(novaSenhaField, gbc);
	        confirmarSenhaField = new JPasswordField(30);
	        confirmarSenhaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	        confirmarSenhaField.setEchoChar((char) 0);
	        gbc.gridx = 1;
	        gbc.gridy = 5;
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        gbc.weightx = 1.0;
	        panel.add(confirmarSenhaField, gbc);
	        
//	        mostrarSenhaCheckBox = new JCheckBox("Mostrar senha");
//	        mostrarSenhaCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//	        mostrarSenhaCheckBox.setBackground(Color.WHITE);
//	        gbc.gridx = 1;
//	        gbc.gridy = 3;
//	        gbc.fill = GridBagConstraints.NONE;
//	        gbc.weightx = 0;
//	        gbc.insets = new Insets(5, 25, 25, 25);
//	        panel.add(mostrarSenhaCheckBox, gbc);
//	        mostrarSenhaCheckBox.addActionListener(new ActionListener() {
//	        	@Override
//	        	public void actionPerformed(ActionEvent e) {
//	        		if(mostrarSenhaCheckBox.isSelected()) {
//	        			confirmarSenhaField.setEchoChar((char) 0);
//	        		}
//	        		else {
//	        			confirmarSenhaField.setEchoChar('*');
//	        		}
//	        	}
//	        });


        JLabel requisitosLabel = new JLabel("<html>• Pelo menos 8 caracteres<br>• Contém pelo menos uma letra maiúscula<br>• Contém pelo menos uma letra minúscula<br>• Contém pelo menos um número</html>");
        requisitosLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        requisitosLabel.setForeground(new Color(80, 80, 80));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 25, 10, 25);
        panel.add(requisitosLabel, gbc);


        JLabel confirmarLabel = new JLabel("Confirmar Senha");
        confirmarLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(12, 25, 12, 25);
        panel.add(confirmarLabel, gbc);

	        JButton confirmarButton = new JButton("Confirmar");
	        confirmarButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
	        confirmarButton.setBackground(new Color(0, 120, 215));
	        confirmarButton.setForeground(Color.WHITE);
	        confirmarButton.setFocusPainted(false);
	        confirmarButton.setPreferredSize(new Dimension(140, 55));
	        confirmarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	boolean sucesso;
	                try {
	                	String senhaAtual = senhaAtualField.getText();
	                	String nova = new String(novaSenhaField.getPassword());
	                	String confirm = new String(confirmarSenhaField.getPassword());
	                	UsuarioController uc = new UsuarioController();
	                	
	                	utilizadorlogado = uc.autenticar(senhaAtual);
	                	
	                	if(utilizadorlogado != null) {
	                		 
	                		if (!nova.equals(confirm)) {
	                			JOptionPane.showMessageDialog(Tela_login.this, "As senhas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
	                			return;
	                		}
	                		if (nova.length() < 8) {
	                			JOptionPane.showMessageDialog(Tela_login.this, "A senha deve ter pelo menos 8 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
	                			return;
	                		}
	                		
	                		uc.redifinirSenha(confirm, senhaAtual);
	                		uc.alterarPrimeiroAcesso(utilizadorlogado);
	                		JOptionPane.showMessageDialog(Tela_login.this, "Senha alterada com sucesso!");
	                		cardLayout.show(cardPanel, "login");
	                		atualizarLeftPanel("login");
	                	}
	                	else {
	                		JOptionPane.showMessageDialog(null, "Senha atual errada");
	                	}
	                	// Voltar ao login
	                }catch(Exception s) {
	                	s.printStackTrace();
	                }
	            }
	        });


        confirmarSenhaField = new JPasswordField(30);
        confirmarSenhaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        confirmarSenhaField.setEchoChar('*');
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(confirmarSenhaField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBackground(Color.WHITE);

        confirmarButton = new JButton("Confirmar");
        confirmarButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        confirmarButton.setBackground(new Color(0, 120, 215));
        confirmarButton.setForeground(Color.WHITE);
        confirmarButton.setFocusPainted(false);
        confirmarButton.setPreferredSize(new Dimension(140, 55));
        confirmarButton.addActionListener(e -> {
            try {
                String senhaAtual = new String(senhaAtualField.getPassword());
                String nova = new String(novaSenhaField.getPassword());
                String confirm = new String(confirmarSenhaField.getPassword());
                
                UsuarioController uc = new UsuarioController();
                utilizadorlogado = uc.autenticar(senhaAtual);
                
                if (utilizadorlogado != null) {
                    if (!nova.equals(confirm)) {
                        JOptionPane.showMessageDialog(Tela_login.this, "As senhas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return; 
                    }
                    if (nova.length() < 8) {
                        JOptionPane.showMessageDialog(Tela_login.this, "A senha deve ter pelo menos 8 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    uc.redifinirSenha(senhaAtual, confirm); 
                    JOptionPane.showMessageDialog(Tela_login.this, "Senha alterada com sucesso!");
                    
                    senhaAtualField.setText("");
                    novaSenhaField.setText("");
                    confirmarSenhaField.setText("");
                    
                    // Só volta para o login em caso de SUCESSO completo
                    cardLayout.show(cardPanel, "login");
                    atualizarLeftPanel("login");
                } else {
                    JOptionPane.showMessageDialog(Tela_login.this, "Senha atual incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception s) {
                s.printStackTrace();
                JOptionPane.showMessageDialog(Tela_login.this, "Erro ao redefinir: " + s.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        cancelarButton.setPreferredSize(new Dimension(140, 55));
        cancelarButton.addActionListener(e -> {
            // Limpa os campos antes de voltar
            senhaAtualField.setText("");
            novaSenhaField.setText("");
            confirmarSenhaField.setText("");
            cardLayout.show(cardPanel, "login");
            atualizarLeftPanel("login");
        });

        buttonPanel.add(confirmarButton);
        buttonPanel.add(cancelarButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 25, 50, 25);
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private void atualizarLeftPanel(String card) {
        if (card.equals("login")) {
            leftTitle.setText("Sistema de Gestão");
            leftSub.setText("Acadêmica");
            leftDesc.setText("Acesse sua conta");
        } else if (card.equals("reset")) {
            leftTitle.setText("Redefinir");
            leftSub.setText("Senha");
            leftDesc.setText("Crie uma nova senha segura");
        }
        leftPanel.revalidate();
        leftPanel.repaint();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}