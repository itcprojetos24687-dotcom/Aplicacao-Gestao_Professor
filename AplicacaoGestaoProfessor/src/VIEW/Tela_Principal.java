package VIEW;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

public class Tela_Principal {

    private JFrame frame;
    private JTable table;
    private JTextField txtPesquisarProfessor;

    private JTextField txtTituloQualificacao;
    private JComboBox<String> cbCampoQualificacao;
    private JComboBox<String> cbNivelQualificacao;
    private JComboBox<String> cbCoordenadorQualificacao;

    private CardLayout cardLayout;
    private JPanel panelCardContainer;
    
    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE  = new Color(13, 110, 253);
    private final Color FUNDO_CLARO     = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tela_Principal window = new Tela_Principal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Tela_Principal() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("AcademiaPro - Sistema de Gestão de Formação");
        frame.setBounds(100, 100, 1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        

        JPanel panelTopo = new JPanel();
        panelTopo.setBackground(AZUL_ESCURO_NAV);
        panelTopo.setPreferredSize(new Dimension(1280, 50));
        panelTopo.setLayout(new BorderLayout());
        panelTopo.setBorder(new EmptyBorder(0, 20, 0, 20));
        
        JLabel lblTituloApp = new JLabel(" Sistema de Gestão de Professores");
        lblTituloApp.setForeground(BRANCO);
        lblTituloApp.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelTopo.add(lblTituloApp, BorderLayout.WEST);
        
        JLabel lblAdmin = new JLabel("Administrador");
        lblAdmin.setForeground(BRANCO);
        lblAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelTopo.add(lblAdmin, BorderLayout.EAST);
        
        frame.getContentPane().add(panelTopo, BorderLayout.NORTH);


        JPanel panelMenuLateral = new JPanel();
        panelMenuLateral.setBackground(AZUL_ESCURO_NAV);
        panelMenuLateral.setPreferredSize(new Dimension(220, 670));
        panelMenuLateral.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        
  
        String[] menus = {"Dashboard", "Formações", "Participantes", "Formadores", "Turmas", "Configurações"};
        for (String menu : menus) {
            JButton btnMenu = new JButton(menu);
            btnMenu.setPreferredSize(new Dimension(200, 40));
            btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btnMenu.setForeground(BRANCO);
            
        
            if (menu.equals("Formadores")) {
                btnMenu.setBackground(AZUL_DESTAQUE);
                btnMenu.setBorder(new LineBorder(AZUL_DESTAQUE));
            } else {
                btnMenu.setBackground(AZUL_ESCURO_NAV);
                btnMenu.setBorder(null);
            }
            btnMenu.setFocusPainted(false);
            btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelMenuLateral.add(btnMenu);
        }

        JButton btnNewButton = new JButton("Gestao_Qualificacao");
        btnNewButton.setPreferredSize(new Dimension(200, 40));
        btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnNewButton.setForeground(BRANCO);
        btnNewButton.setBackground(AZUL_ESCURO_NAV);
        btnNewButton.setBorder(null);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelMenuLateral.add(btnNewButton);
        

        JButton btnSairLateral = new JButton("Sair do Sistema");
        btnSairLateral.setPreferredSize(new Dimension(200, 40));
        btnSairLateral.setBackground(new Color(217, 83, 79));
        btnSairLateral.setForeground(BRANCO);
        btnSairLateral.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSairLateral.setBorder(null);
        btnSairLateral.addActionListener(e -> fecharAplicacao());
        panelMenuLateral.add(btnSairLateral);
        
        frame.getContentPane().add(panelMenuLateral, BorderLayout.WEST);


        JPanel panelConteudo = new JPanel();
        panelConteudo.setBackground(FUNDO_CLARO);
        panelConteudo.setBorder(new EmptyBorder(25, 30, 25, 30));
        panelConteudo.setLayout(new BorderLayout(0, 20));
        frame.getContentPane().add(panelConteudo, BorderLayout.CENTER);


        JPanel panelHeaderConteudo = new JPanel();
        panelHeaderConteudo.setBackground(FUNDO_CLARO);
        panelHeaderConteudo.setLayout(new BorderLayout());
        
        JLabel lblTituloPagina = new JLabel("Formadores");
        lblTituloPagina.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTituloPagina.setForeground(AZUL_ESCURO_NAV);
        
        JLabel lblSubtituloPagina = new JLabel("Gestão de Formadores");
        lblSubtituloPagina.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtituloPagina.setForeground(TEXTO_MUTED);
        
        JPanel agrupador = new JPanel();
        agrupador.setBackground(FUNDO_CLARO);
        agrupador.setLayout(new BorderLayout());
        agrupador.add(lblTituloPagina, BorderLayout.NORTH);
        agrupador.add(lblSubtituloPagina, BorderLayout.SOUTH);
        panelHeaderConteudo.add(agrupador, BorderLayout.WEST);
        panelConteudo.add(panelHeaderConteudo, BorderLayout.NORTH);


        cardLayout = new CardLayout();
        panelCardContainer = new JPanel(cardLayout);
        panelConteudo.add(panelCardContainer, BorderLayout.CENTER);


        JPanel panelCard = new JPanel();
        panelCard.setBackground(BRANCO);
        panelCard.setBorder(new LineBorder(new Color(230, 233, 237), 1, true));
        panelCard.setLayout(new BorderLayout(0, 15));
        panelCard.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelCardContainer.add(panelCard, "lista");


        JPanel panelAcoes = new JPanel();
        panelAcoes.setBackground(BRANCO);
        panelAcoes.setLayout(new BorderLayout());

      
        JPanel panelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelPesquisa.setBackground(BRANCO);
        
        txtPesquisarProfessor = new JTextField();
        txtPesquisarProfessor.setPreferredSize(new Dimension(250, 35));
        txtPesquisarProfessor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelPesquisa.add(txtPesquisarProfessor);
        
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setPreferredSize(new Dimension(90, 35));
        btnFiltrar.setBackground(BRANCO);
        btnFiltrar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelPesquisa.add(btnFiltrar);
        
        panelAcoes.add(panelPesquisa, BorderLayout.WEST);

     
        JPanel panelBotoesCrud = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panelBotoesCrud.setBackground(BRANCO);

        JButton btnNovo = new JButton("+ Novo");
        btnNovo.setBackground(AZUL_DESTAQUE);
        btnNovo.setForeground(BRANCO);
        btnNovo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnNovo.setPreferredSize(new Dimension(100, 35));
        btnNovo.addActionListener(e -> new Tela_cadastroProfessor().setVisible(true));
        panelBotoesCrud.add(btnNovo);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(BRANCO);
        btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnEditar.setPreferredSize(new Dimension(90, 35));
        panelBotoesCrud.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(248, 215, 218));
        btnEliminar.setForeground(new Color(114, 28, 36));
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEliminar.setPreferredSize(new Dimension(100, 35));
        panelBotoesCrud.add(btnEliminar);

        panelAcoes.add(panelBotoesCrud, BorderLayout.EAST);
        panelCard.add(panelAcoes, BorderLayout.NORTH);

   
        JScrollPane scrollPane = new JScrollPane();
        panelCard.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setRowHeight(35); 
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(248, 249, 250));
        
     
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {"12345", "Malik", " Mangue", "Maculino", "Malikmang@gmail.com", "876543211", "Programação web", "Ativo"},
                {"24788", "Keany", " Pessula", "Maculino", "Keanypessul@gmail.com", "844425235","Suporte Informático", "Ativo"},
                {"54321", "Edmundo"," Mapotere", "Maculino", "EDMapotere@gmail.com","854328716", "Redes", "Ativo"},
            },
            new String[] {
                "Código", "Nome", "Apelido", "Sexo", "Email", "Telefone", "Área de Atuação", "Estado"
            }
        ));
        scrollPane.setViewportView(table);
        
        JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
        panelCard.add(tglbtnNewToggleButton, BorderLayout.SOUTH);

        JPanel panelCadastroQualificacao = new JPanel();
        panelCadastroQualificacao.setBackground(BRANCO);
        panelCadastroQualificacao.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelCadastroQualificacao.setLayout(new BorderLayout(0, 15));
        panelCardContainer.add(panelCadastroQualificacao, "cadastro");

        JPanel panelFormQualificacao = new JPanel();
        panelFormQualificacao.setBackground(BRANCO);
        panelFormQualificacao.setLayout(new GridLayout(4, 2, 10, 15));
        panelFormQualificacao.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Título da Qualificação:");
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelFormQualificacao.add(lblTitulo);

        txtTituloQualificacao = new JTextField();
        txtTituloQualificacao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelFormQualificacao.add(txtTituloQualificacao);

        JLabel lblCampo = new JLabel("Campo:");
        lblCampo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelFormQualificacao.add(lblCampo);

        cbCampoQualificacao = new JComboBox<>();
        cbCampoQualificacao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelFormQualificacao.add(cbCampoQualificacao);

        JLabel lblNivel = new JLabel("Nível:");
        lblNivel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelFormQualificacao.add(lblNivel);

        cbNivelQualificacao = new JComboBox<>();
        cbNivelQualificacao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelFormQualificacao.add(cbNivelQualificacao);

        JLabel lblCoordenador = new JLabel("Coordenador:");
        lblCoordenador.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelFormQualificacao.add(lblCoordenador);

        cbCoordenadorQualificacao = new JComboBox<>();
        cbCoordenadorQualificacao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelFormQualificacao.add(cbCoordenadorQualificacao);

        panelCadastroQualificacao.add(panelFormQualificacao, BorderLayout.NORTH);

        JPanel panelBotoesCadastro = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panelBotoesCadastro.setBackground(BRANCO);

        JButton btnSalvarQualificacao = new JButton("Salvar");
        btnSalvarQualificacao.setBackground(AZUL_DESTAQUE);
        btnSalvarQualificacao.setForeground(BRANCO);
        btnSalvarQualificacao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSalvarQualificacao.setPreferredSize(new Dimension(100, 35));
        panelBotoesCadastro.add(btnSalvarQualificacao);

        JButton btnCancelarQualificacao = new JButton("Cancelar");
        btnCancelarQualificacao.setBackground(BRANCO);
        btnCancelarQualificacao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnCancelarQualificacao.setPreferredSize(new Dimension(100, 35));
        btnCancelarQualificacao.addActionListener(e -> cardLayout.show(panelCardContainer, "lista"));
        panelBotoesCadastro.add(btnCancelarQualificacao);

        panelCadastroQualificacao.add(panelBotoesCadastro, BorderLayout.SOUTH);


        btnNewButton.addActionListener(e -> cardLayout.show(panelCardContainer, "cadastro"));


        JMenuBar menuBarFixo = new JMenuBar();
        frame.setJMenuBar(menuBarFixo);

        JMenu mnFile = new JMenu("Ficheiro");
        menuBarFixo.add(mnFile);

        JMenuItem mntmSair = new JMenuItem("Sair");
        mntmSair.setMnemonic(KeyEvent.VK_S);
        mntmSair.addActionListener(e -> fecharAplicacao());
        mnFile.add(mntmSair);

        JMenu mnCadastros = new JMenu("Cadastros");
        menuBarFixo.add(mnCadastros);

        JMenuItem mntmProfessores = new JMenuItem("Professores");
        mntmProfessores.addActionListener(e -> new Tela_cadastroProfessor().setVisible(true));
        mnCadastros.add(mntmProfessores);

        JMenuItem mntmTurmas = new JMenuItem("Turmas");
        mntmTurmas.addActionListener(e -> new Tela_cadastoTurma().setVisible(true));
        mnCadastros.add(mntmTurmas);
    }

    private void fecharAplicacao() {
        int resposta = JOptionPane.showConfirmDialog(null, "Tens a certeza que queres sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}