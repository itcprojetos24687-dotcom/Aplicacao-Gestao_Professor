package VIEW;

import model.*;
import controller.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Tela_Utilizadores extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableUtilizadores;
    private JTextField txtPesquisar;

 
    private final Color AZUL_PRIMARY   = new Color(13, 110, 253);  
    private final Color VERDE_SUCCESS  = new Color(40, 167, 69);   
    private final Color VERMELHO_DANGER = new Color(220, 53, 69); 
    private final Color BG_LIGHT       = new Color(245, 247, 250);  
    private final Color BRANCO         = Color.WHITE;
    private final Color TEXTO_DARK     = new Color(33, 37, 41);     
    private final Color TEXTO_MUTED    = new Color(108, 117, 125);  
    private final Color BORDA_CARD     = new Color(225, 228, 232); 
    
    private final Color CINZA_FUNDO   = new Color(240, 240, 240);
    private final Color AZUL_NORMAL   = new Color(41, 98, 189);
    private final Color AZUL_HOVER    = new Color(28, 74, 150);
    private final Color BRANCO1        = Color.WHITE;
    private final Color CINZA_HOVER   = new Color(225, 225, 225);

    private JTextField campoNomeCompleto;
    private JTextField campoNomeOperador;
    private JTextField campoSenha;
    private JComboBox<String> comboPerfil;
    private JTextField campoEmail;
    
    private JDialog d;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tela_Utilizadores frame = new Tela_Utilizadores();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Tela_Utilizadores() {
        setTitle("AcademiaPro - Gestão de Utilizadores e Permissões");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setMinimumSize(new Dimension(1100, 680));
        setSize(1100, 700);
        setLocationRelativeTo(null); 

        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 64));
        contentPane.setBorder(new EmptyBorder(30, 40, 30, 40));
        contentPane.setLayout(new BorderLayout(0, 25));
        setContentPane(contentPane);


        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(0, 0, 64));

        JLabel lblTitulo = new JLabel("Gestão de Utilizadores");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo, BorderLayout.NORTH);

        JLabel lblSubtitulo = new JLabel("Gerencie as contas de acesso, perfis de utilizador (RBAC) e estado do sistema.");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(Color.WHITE);
        panelHeader.add(lblSubtitulo, BorderLayout.SOUTH);

        contentPane.add(panelHeader, BorderLayout.NORTH);


        JPanel panelCard = new JPanel(new BorderLayout(0, 20));
        panelCard.setBackground(BRANCO);
        panelCard.setBorder(new LineBorder(BORDA_CARD, 1));
        

        JPanel panelCardInterno = new JPanel(new BorderLayout(0, 15));
        panelCardInterno.setBackground(Color.WHITE);
        panelCardInterno.setBorder(new EmptyBorder(20, 20, 20, 20));

       
        JPanel panelBarraSuperior = new JPanel(new BorderLayout());
        panelBarraSuperior.setBackground(BRANCO);

     
        JPanel panelAcoesEsquerda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelAcoesEsquerda.setBackground(BRANCO);

        JButton btnNovo = new JButton("➕ Novo Utilizador");
        estilizarBotao(btnNovo, VERDE_SUCCESS, BRANCO, 160, 36);
        panelAcoesEsquerda.add(btnNovo);

        JButton btnEditar = new JButton(" Editar");
        estilizarBotao(btnEditar, AZUL_PRIMARY, BRANCO, 110, 36);
        panelAcoesEsquerda.add(btnEditar);

        JButton btnExcluir = new JButton(" Eliminar");
        estilizarBotao(btnExcluir, VERMELHO_DANGER, BRANCO, 110, 36);
        panelAcoesEsquerda.add(btnExcluir);

        panelBarraSuperior.add(panelAcoesEsquerda, BorderLayout.WEST);

        JPanel panelPesquisaDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelPesquisaDireita.setBackground(BRANCO);

        txtPesquisar = new JTextField();
        txtPesquisar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPesquisar.setPreferredSize(new Dimension(220, 36));
        panelPesquisaDireita.add(txtPesquisar);

        JButton btnPesquisar = new JButton(" Procurar");
        estilizarBotao(btnPesquisar, new Color(11, 29, 58), BRANCO, 120, 36);
        panelPesquisaDireita.add(btnPesquisar);

        panelBarraSuperior.add(panelPesquisaDireita, BorderLayout.EAST);
        panelCardInterno.add(panelBarraSuperior, BorderLayout.NORTH);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new LineBorder(BORDA_CARD, 1));
        scrollPane.setBackground(BRANCO);
        panelCardInterno.add(scrollPane, BorderLayout.CENTER);

        tableUtilizadores = new JTable();
        tableUtilizadores.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableUtilizadores.setRowHeight(34);
        tableUtilizadores.setGridColor(new Color(240, 242, 245));
        tableUtilizadores.setFillsViewportHeight(true);
        tableUtilizadores.setSelectionBackground(new Color(230, 242, 255));
        tableUtilizadores.setSelectionForeground(TEXTO_DARK);

        JTableHeader header = tableUtilizadores.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(248, 249, 250));
        header.setForeground(TEXTO_DARK);
        header.setPreferredSize(new Dimension(100, 38));

        tableUtilizadores.setModel(new DefaultTableModel(
            new Object[][] {
                {"1", "admin.malik", "Malik Mangue", "Malikmang@gmail.com", "Administrador", "Ativo"},
                {"2", "KPessula", "Keany Pessula", "Keanypessul@gmail.com", "Gestor", "Ativo"},
                {"3", "ED Mapotere", "Edmundo Mapotere", "EDMapotere@gmail.com", "Professor", "Ativo"},
            },
            new String[] {
                "ID", "Username", "Nome Completo", "Email", "Perfil / Função", "Estado"
            }
        ));
        scrollPane.setViewportView(tableUtilizadores);

        panelCard.add(panelCardInterno, BorderLayout.CENTER);
        contentPane.add(panelCard, BorderLayout.CENTER);


        btnNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	 d = criarDialog();
            	 d.setVisible(true);
            	
                JOptionPane.showMessageDialog(null, "Abrir formulário de criação de utilizador.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linha = tableUtilizadores.getSelectedRow();
                if (linha == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um utilizador na tabela para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                } else {
                    String username = tableUtilizadores.getValueAt(linha, 1).toString();
                    JOptionPane.showMessageDialog(null, "A editar as permissões de: " + username, "Editar", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linha = tableUtilizadores.getSelectedRow();
                if (linha == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um utilizador para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                } else {
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja remover esta conta de acesso?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
                    if (confirmacao == JOptionPane.YES_OPTION) {
                       
                    }
                }
            }
        });
    }

   
    private void estilizarBotao(JButton botao, Color fundo, Color texto, int largura, int altura) {
        botao.setPreferredSize(new Dimension(largura, altura));
        botao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        botao.setBackground(fundo);
        botao.setForeground(texto);
        botao.setBorder(null);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));


        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(fundo.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(fundo);
            }
        });
    }
    private JDialog criarDialog() {
    	d = new JDialog();
    	d.setTitle("Cadastro de Utilizador");
        d.setSize(650, 400);
        d.setLocationRelativeTo(null);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setResizable(true);

        // Painel principal com fundo cinzento e layout responsivo
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBackground(CINZA_FUNDO);
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        d.setContentPane(painelPrincipal);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ================= LINHA 0: Nome Completo | Perfil =================
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        painelPrincipal.add(criarLabel("Nome completo:"), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0.5; // coluna esquerda cresce
        campoNomeCompleto = new JTextField();
        painelPrincipal.add(campoNomeCompleto, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 0;
        painelPrincipal.add(criarLabel("Perfil:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 0.5; // coluna direita também cresce
        comboPerfil = new JComboBox<>(new String[]{"Administrador", "Operador", "Supervisor"});
        painelPrincipal.add(comboPerfil, gbc);

        // ================= LINHA 2: Nome do Operador | Email =================
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.weightx = 0;
        painelPrincipal.add(criarLabel("Nome do operador:"), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.weightx = 0.5;
        campoNomeOperador = new JTextField();
        painelPrincipal.add(campoNomeOperador, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.weightx = 0;
        painelPrincipal.add(criarLabel("Email:"), gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        gbc.weightx = 0.5;
        campoEmail = new JTextField();
        painelPrincipal.add(campoEmail, gbc);

        // ================= LINHA 4: Senha + botão Gerar Senha =================
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.weightx = 0;
        painelPrincipal.add(criarLabel("Senha:"), gbc);

        // Painel interno só para juntar campo + botão "Gerar Senha" numa célula só
        JPanel painelSenha = new JPanel(new BorderLayout(8, 0));
        painelSenha.setOpaque(false);

        campoSenha = new JTextField();
        painelSenha.add(campoSenha, BorderLayout.CENTER);

        JButton botaoGerarSenha = criarBotaoNeutro("Gerar senha");
        botaoGerarSenha.addActionListener(e -> campoSenha.setText(gerarSenhaAleatoria(10)));
        painelSenha.add(botaoGerarSenha, BorderLayout.EAST);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2; // ocupa as duas colunas para dar espaço ao botão
        gbc.weightx = 1.0;
        painelPrincipal.add(painelSenha, gbc);

        // reset dos campos "especiais" antes de continuar
        gbc.gridwidth = 1;

        // ================= Espaço que empurra os botões para baixo =================
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0; // absorve o espaço vertical extra ao redimensionar
        gbc.fill = GridBagConstraints.BOTH;
        painelPrincipal.add(Box.createGlue(), gbc);

        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;

        // ================= Linha final: botões Sair | Guardar =================
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.setOpaque(false);

        JButton botaoSair = criarBotaoSair();
        botaoSair.addActionListener(e -> d.dispose());

        JButton botaoGuardar = criarBotaoGuardar();
        botaoGuardar.addActionListener(e -> {
        	boolean sucesso;
            try {
            	
            	String nome = campoNomeCompleto.getText();
            	String username = campoNomeOperador.getText();
            	String senha =  campoSenha.getText();
            	String email = campoEmail.getText();
            	
            	
            	UsuarioController uc = new UsuarioController();
            	sucesso = uc.cadastrarUsuario(nome,username,senha,email);
            	if(sucesso) {
            		JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "Introducao invalida, Tente Novamente");
            	}
            }catch(NumberFormatException ex) {
            	JOptionPane.showMessageDialog(null, "Introduca todos os dados corretamente");
            }catch(Exception x) {
            	JOptionPane.showMessageDialog(null, "Introduca todos os dados");
            }
        	// aqui entra a lógica de gravação
        	
        	
            JOptionPane.showMessageDialog(d, "Registo guardado com sucesso!");
        });

        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoGuardar);

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        painelPrincipal.add(painelBotoes, gbc);
        
        return d;
    }

    // ---------- Métodos auxiliares de criação de componentes ----------

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return label;
    }

    /** Botão "Gerar senha" — neutro, cinzento, com hover */
    private JButton criarBotaoNeutro(String texto) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setBackground(CINZA_FUNDO);
        botao.setForeground(Color.DARK_GRAY);
        botao.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { botao.setBackground(CINZA_HOVER); }
            public void mouseExited(MouseEvent e)  { botao.setBackground(CINZA_FUNDO); }
        });
        return botao;
    }

    /** Botão "Guardar" — fundo azul, texto branco, hover em azul mais escuro */
    private JButton criarBotaoGuardar() {
        JButton botao = new JButton("Guardar");
        botao.setFocusPainted(false);
        botao.setOpaque(true);
        botao.setBorderPainted(false);
        botao.setBackground(AZUL_NORMAL);
        botao.setForeground(BRANCO);
        botao.setFont(new Font("SansSerif", Font.BOLD, 13));
        botao.setPreferredSize(new Dimension(110, 34));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { botao.setBackground(AZUL_HOVER); }
            public void mouseExited(MouseEvent e)  { botao.setBackground(AZUL_NORMAL); }
        });
        return botao;
    }

    /** Botão "Sair" — fundo branco, texto azul, hover muda para cinza claro */
    private JButton criarBotaoSair() {
        JButton botao = new JButton("Sair");
        botao.setFocusPainted(false);
        botao.setOpaque(true);
        botao.setBackground(BRANCO);
        botao.setForeground(AZUL_NORMAL);
        botao.setFont(new Font("SansSerif", Font.BOLD, 13));
        botao.setBorder(BorderFactory.createLineBorder(AZUL_NORMAL));
        botao.setPreferredSize(new Dimension(110, 34));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { botao.setBackground(CINZA_HOVER); }
            public void mouseExited(MouseEvent e)  { botao.setBackground(BRANCO); }
        });
        return botao;
    }

    private String gerarSenhaAleatoria(int tamanho) {
        String caracteres = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789!@#$%";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return sb.toString();
    }
    	
    	
    }

