package VIEW;

import model.*;
import dao.ExceptionDao;
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
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;

public class Tela_Utilizadores extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableUtilizadores;
    private JTextField txtPesquisar;

    private final Color AZUL_PRIMARY    = new Color(13, 110, 253);  
    private final Color VERDE_SUCCESS   = new Color(40, 167, 69);   
    private final Color VERMELHO_DANGER = new Color(220, 53, 69); 
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_DARK      = new Color(33, 37, 41);     
    private final Color BORDA_CARD      = new Color(225, 228, 232); 
    
    private final Color CINZA_FUNDO     = new Color(240, 240, 240);
    private final Color AZUL_NORMAL     = new Color(41, 98, 189);
    private final Color AZUL_HOVER      = new Color(28, 74, 150);
    private final Color CINZA_HOVER     = new Color(225, 225, 225);

    private JTextField campoNomeOperador;
    private JTextField campoNomeCompleto;
    private JTextField campoSenha;
    private JComboBox<Perfil> comboPerfil;
    private JTextField campoEmail;
    private ArrayList<Perfil> perfis;
    private UsuarioController uc = new UsuarioController();
    private JDialog d;
    private int idUser = 0;
    private JButton botaoGerarSenha;

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
        setTitle("Gestão de Utilizadores e Permissões");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setMinimumSize(new Dimension(1100, 680));
        setSize(1100, 700);
        setLocationRelativeTo(null); 

        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 64));
        contentPane.setBorder(new EmptyBorder(30, 40, 30, 40));
        contentPane.setLayout(new BorderLayout(0, 25));
        setContentPane(contentPane);

        campoNomeOperador = new JTextField();
        campoNomeCompleto = new JTextField();
        campoSenha = new JTextField();
        campoEmail = new JTextField();
        
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

        JButton btnEditar = new JButton("Editar");
        estilizarBotao(btnEditar, AZUL_PRIMARY, BRANCO, 110, 36);
        panelAcoesEsquerda.add(btnEditar);

        JButton btnExcluir = new JButton("Eliminar");
        estilizarBotao(btnExcluir, VERMELHO_DANGER, BRANCO, 110, 36);
        panelAcoesEsquerda.add(btnExcluir);

        panelBarraSuperior.add(panelAcoesEsquerda, BorderLayout.WEST);

        JPanel panelPesquisaDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelPesquisaDireita.setBackground(BRANCO);

        txtPesquisar = new JTextField();
        txtPesquisar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPesquisar.setPreferredSize(new Dimension(220, 36));
        panelPesquisaDireita.add(txtPesquisar);

        JButton btnPesquisar = new JButton("Procurar");
        estilizarBotao(btnPesquisar, new Color(11, 29, 58), BRANCO, 120, 36);
        panelPesquisaDireita.add(btnPesquisar);

        panelBarraSuperior.add(panelPesquisaDireita, BorderLayout.EAST);
        panelCardInterno.add(panelBarraSuperior, BorderLayout.NORTH);

        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtPesquisar.getText();
                DefaultTableModel model = (DefaultTableModel) tableUtilizadores.getModel();
                model.setRowCount(0);
                try {
                    ArrayList<Usuario> usuariosList = new UsuarioController().listarUsuario(nome);
                    for(Usuario u : usuariosList) {
                        model.addRow(new Object[] {
                            u.getCodigo(),
                            u.getUsername(),
                            u.getNome_completo(),
                            u.getEmail(),
                            u.getPerfil().getNome()
                        });
                    }
                } catch(Exception s) {
                    JOptionPane.showMessageDialog(null, "Erro ao listar utilizadores.");
                    s.printStackTrace();
                }
            }
        });

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
            new Object[][] {},
            new String[] { "ID", "Username", "Nome Completo", "Email", "Perfil" }
        ));
        scrollPane.setViewportView(tableUtilizadores);

        panelCard.add(panelCardInterno, BorderLayout.CENTER);
        contentPane.add(panelCard, BorderLayout.CENTER);

        btnNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 try {
                    idUser = 0; // Garante que reajusta para modo inserção
                    d = criarDialog();
                    campoNomeCompleto.setText("");
                    campoNomeOperador.setText("");
                    campoEmail.setText("");
                    campoSenha.setText("");
                    campoSenha.setEditable(true);
                    botaoGerarSenha.setEnabled(true);
                    d.setVisible(true);
                 } catch (Exception e1) {
                    e1.printStackTrace();
                 }
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linha = tableUtilizadores.getSelectedRow();
                if (linha == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um utilizador na tabela para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                try {
                    Integer id = (Integer) tableUtilizadores.getModel().getValueAt(linha, 0);
                    String username = (String) tableUtilizadores.getModel().getValueAt(linha, 1);
                    String nome = (String) tableUtilizadores.getModel().getValueAt(linha, 2);
                    String email = (String) tableUtilizadores.getModel().getValueAt(linha, 3);
                    String perfilStr = (String) tableUtilizadores.getModel().getValueAt(linha, 4);

                    d = criarDialog();
                    buscarUsuario(id, username, nome, email, perfilStr);
                    d.setVisible(true);
                } catch(ExceptionDao a) {
                    new ExceptionDao("Erro ao editar " + a);
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linha = tableUtilizadores.getSelectedRow();
                if (linha == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um utilizador para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                try {
                    Integer codigo = (Integer) tableUtilizadores.getModel().getValueAt(linha, 0);
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja remover esta conta de acesso?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        boolean sucesso = uc.apagarUsuario(codigo);
                        if(sucesso) {
                            JOptionPane.showMessageDialog(null, "Usuário eliminado com sucesso");
                            // Atualiza a tabela limpando a linha removida
                            ((DefaultTableModel)tableUtilizadores.getModel()).removeRow(linha);
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao eliminar usuário");
                        }
                    }
                } catch(Exception s) {
                    s.printStackTrace();
                }
            }
        });
    }

    public void buscarUsuario(Integer id, String username, String nome, String email, String perfil) throws ExceptionDao {
        this.idUser = id;
        campoNomeCompleto.setText(nome);
        campoNomeOperador.setText(username);
        campoEmail.setText(email);
        campoSenha.setText("********");
        campoSenha.setEditable(false);
        botaoGerarSenha.setEnabled(false);
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

    private JDialog criarDialog() throws ExceptionDao {
        d = new JDialog();
        d.setTitle("Cadastro de Utilizador");
        d.setSize(650, 400);
        d.setLocationRelativeTo(null);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setModal(true); // Evita cliques na janela de trás enquanto gerencia dados
        d.setResizable(true);

        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBackground(CINZA_FUNDO);
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        d.setContentPane(painelPrincipal);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome Completo
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        painelPrincipal.add(criarLabel("Nome:"), gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.5;
        campoNomeCompleto = new JTextField();
        painelPrincipal.add(campoNomeCompleto, gbc);

        // Perfil
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0;
        painelPrincipal.add(criarLabel("Perfil:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.5;
        comboPerfil = new JComboBox<Perfil>();
        try {
            PerfilController pc = new PerfilController();
            perfis = pc.listarPerfil();
            for(Perfil p: perfis) {
                comboPerfil.addItem(p);
            }
        } catch(Exception q) {
            q.printStackTrace();
        }
        painelPrincipal.add(comboPerfil, gbc);
        
        // Nome do Operador
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        painelPrincipal.add(criarLabel("Nome do operador:"), gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.5;
        campoNomeOperador = new JTextField();
        painelPrincipal.add(campoNomeOperador, gbc);

        // Email
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 0;
        painelPrincipal.add(criarLabel("Email:"), gbc);

        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 0.5;
        campoEmail = new JTextField();
        painelPrincipal.add(campoEmail, gbc);

        // Senha
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        painelPrincipal.add(criarLabel("Senha:"), gbc);

        JPanel painelSenha = new JPanel(new BorderLayout(8, 0));
        painelSenha.setOpaque(false);

        campoSenha = new JTextField();
        painelSenha.add(campoSenha, BorderLayout.CENTER);

        botaoGerarSenha = criarBotaoNeutro("Gerar senha");
        botaoGerarSenha.addActionListener(e -> campoSenha.setText(gerarSenhaAleatoria(10)));
        painelSenha.add(botaoGerarSenha, BorderLayout.EAST);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2; 
        gbc.weightx = 1.0;
        painelPrincipal.add(painelSenha, gbc);

        gbc.gridwidth = 1;

        // Espaço Vertical Fake
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        painelPrincipal.add(Box.createGlue(), gbc);

        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;

        // Painel Botões Finais
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.setOpaque(false);

        JButton botaoSair = criarBotaoSair();
        botaoSair.addActionListener(e -> d.dispose());

        JButton botaoGuardar = criarBotaoGuardar();
        botaoGuardar.addActionListener(e -> {
            boolean sucesso;
            try {
                String nome = campoNomeCompleto.getText().trim();
                String username = campoNomeOperador.getText().trim();
                String senha = campoSenha.getText();
                String email = campoEmail.getText().trim();
                Perfil p = (Perfil) comboPerfil.getSelectedItem();
                
                if (nome.isEmpty() || username.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(d, "Preencha todos os campos obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if(this.idUser == 0) {
                    if(senha.isEmpty()) {
                        JOptionPane.showMessageDialog(d, "Defina ou gere uma senha para o novo usuário!", "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    sucesso = uc.cadastrarUsuario(nome, username, senha, email, p);
                } else {
                    sucesso = uc.atualizarUsuario(this.idUser, nome, username, email, p);
                    this.idUser = 0;
                }
                
                if(sucesso) {
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
                    d.dispose(); // Fecha o diálogo se tudo deu certo
                } else {
                    JOptionPane.showMessageDialog(null, "Introdução inválida, Tente Novamente");
                }
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Introduza todos os dados corretamente");
            } catch(Exception x) {
                JOptionPane.showMessageDialog(null, "Erro técnico do sistema ao salvar os dados.");
                x.printStackTrace();
            }
        });

        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoGuardar);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        painelPrincipal.add(painelBotoes, gbc);
        
        return d;
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return label;
    }

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
        String baseNome = campoNomeCompleto.getText().trim().replaceAll("\\s+", "");
        if (baseNome.isEmpty()) {
            baseNome = "User";
        }
        return baseNome + "123";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implementação obrigatória da interface ActionListener
    }
}