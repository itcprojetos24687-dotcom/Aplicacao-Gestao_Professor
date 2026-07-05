package VIEW;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class Tela_Principal {

    private JFrame frame;
    private JPanel panelConteudoDinamico;
    private CardLayout cardLayout;
    
    private JLabel lblTituloPagina;
    private JLabel lblSubtituloPagina;
    
    private String nivelAcesso;

    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color FUNDO_CLARO     = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Tela_Principal window = new Tela_Principal("Administrador");
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Tela_Principal(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso != null ? nivelAcesso : "Secretaria";
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("AcademiaPro - Sistema de Gestão de Formação");
        frame.setBounds(100, 100, 1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel panelTopo = new JPanel(new BorderLayout());
        panelTopo.setBackground(AZUL_ESCURO_NAV);
        panelTopo.setPreferredSize(new Dimension(1280, 50));
        panelTopo.setBorder(new EmptyBorder(0, 20, 0, 20));

        JLabel lblTituloApp = new JLabel("SGP - Painel de Controle");
        lblTituloApp.setForeground(BRANCO);
        lblTituloApp.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelTopo.add(lblTituloApp, BorderLayout.WEST);

        JPanel panelTopoDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        panelTopoDireita.setBackground(AZUL_ESCURO_NAV);

        JLabel lblAdmin = new JLabel("Olá, " + nivelAcesso);
        lblAdmin.setForeground(BRANCO);
        lblAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelTopoDireita.add(lblAdmin);

        JButton btnUtilizadores = new JButton("⚙ Gerir Utilizadores");
        btnUtilizadores.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnUtilizadores.setForeground(BRANCO);
        btnUtilizadores.setBackground(new Color(25, 52, 88));
        btnUtilizadores.setBorder(new LineBorder(new Color(40, 75, 120), 1, true));
        btnUtilizadores.setPreferredSize(new Dimension(150, 30));
        btnUtilizadores.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUtilizadores.setFocusPainted(false);
        
        btnUtilizadores.addActionListener(e -> {
            try {
                Tela_Utilizadores telaUtilizadores = new Tela_Utilizadores();
                telaUtilizadores.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao abrir a Gestão de Utilizadores.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUtilizadores.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btnUtilizadores.setBackground(new Color(35, 68, 110)); }
            @Override public void mouseExited(MouseEvent e) { btnUtilizadores.setBackground(new Color(25, 52, 88)); }
        });
        
        if (nivelAcesso.equalsIgnoreCase("Administrador")) {
            panelTopoDireita.add(btnUtilizadores);
        }
        
        panelTopo.add(panelTopoDireita, BorderLayout.EAST);
        frame.getContentPane().add(panelTopo, BorderLayout.NORTH);

        JPanel panelMenuLateral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 8));
        panelMenuLateral.setBackground(AZUL_ESCURO_NAV);
        panelMenuLateral.setPreferredSize(new Dimension(220, 670));

        cardLayout = new CardLayout();
        panelConteudoDinamico = new JPanel(cardLayout);

        JPanel painelDashboard = criarPainelDashboard();
        JPanel painelFormadores = criarPainelFormadores();
        Tela_Incrição painelInscricoes = new Tela_Incrição(); 

        panelConteudoDinamico.add(painelDashboard, "Dashboard");
        panelConteudoDinamico.add(painelFormadores, "Formadores");
        panelConteudoDinamico.add(criarPainelFormacoes(), "Formações");
        panelConteudoDinamico.add(painelInscricoes, "Inscrições");

        String[] menus = {"Dashboard", "Formações", "Inscrições", "Formadores", "Cadastros ▾"};
        
        for (String menu : menus) {
            if (nivelAcesso.equalsIgnoreCase("Formador")) {
                if (menu.equals("Cadastros ▾") || menu.equals("Formadores")) {
                    continue;
                }
            }

            JButton btnMenu = new JButton(menu);
            btnMenu.setPreferredSize(new Dimension(200, 40));
            btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btnMenu.setForeground(BRANCO);
            btnMenu.setFocusPainted(false);
            btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

            final Color corPadraoFundo;
            final Color corHoverFundo;

            if (menu.equals("Dashboard")) {
                corPadraoFundo = AZUL_DESTAQUE; 
                corHoverFundo = new Color(11, 94, 215);
                btnMenu.setBackground(corPadraoFundo);
                btnMenu.setBorder(new LineBorder(corPadraoFundo));
            } else if (menu.equals("Cadastros ▾")) {
                corPadraoFundo = new Color(25, 52, 88);
                corHoverFundo = new Color(35, 68, 110);
                btnMenu.setBackground(corPadraoFundo);
                btnMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
                btnMenu.setBorder(new LineBorder(new Color(40, 75, 120)));

                JPopupMenu menuPopupCadastros = new JPopupMenu();
                String[][] itensDropdown = {
                    {"Professores", "Tela_cadastroProfessor"},
                    {"Turmas", "Tela_cadastoTurma"},
                    {"Qualificações", "Tela_cadastroQualificação"},
                    {"Inscrições", "Tela_Incrição"}
                };

                for (String[] item : itensDropdown) {
                    if (nivelAcesso.equalsIgnoreCase("Secretaria") && item[0].equals("Professores")) {
                        continue;
                    }

                    JMenuItem menuItem = new JMenuItem(item[0]);
                    menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    menuItem.addActionListener(e -> {
                        if (item[0].equals("Inscrições")) {
                            cardLayout.show(panelConteudoDinamico, "Inscrições");
                            lblTituloPagina.setText("Inscrições");
                            lblSubtituloPagina.setText("Gestão de Inscrições do Sistema");
                        } else {
                            abrirJanelaLegada(item[1]);
                        }
                    });
                    menuPopupCadastros.add(menuItem);
                }
                btnMenu.addActionListener(e -> menuPopupCadastros.show(btnMenu, btnMenu.getWidth(), 0));
            } else {
                corPadraoFundo = AZUL_ESCURO_NAV;
                corHoverFundo = new Color(25, 52, 88);
                btnMenu.setBackground(corPadraoFundo);
                btnMenu.setBorder(null);
            }

            if (menu.equals("Dashboard") || menu.equals("Formadores") || menu.equals("Inscrições") || menu.equals("Formações")) {
                btnMenu.addActionListener(e -> {
                    cardLayout.show(panelConteudoDinamico, menu);
                    lblTituloPagina.setText(menu);
                    lblSubtituloPagina.setText("Gestão de " + menu + " do Sistema");
                });
            }

            btnMenu.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { btnMenu.setBackground(corHoverFundo); }
                @Override public void mouseExited(MouseEvent e) { btnMenu.setBackground(corPadraoFundo); }
            });

            panelMenuLateral.add(btnMenu);
        }

        JButton btnSairLateral = new JButton("Sair do Sistema");
        btnSairLateral.setPreferredSize(new Dimension(200, 40));
        btnSairLateral.setBackground(new Color(217, 83, 79));
        btnSairLateral.setForeground(BRANCO);
        btnSairLateral.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSairLateral.setBorder(null);
        btnSairLateral.setFocusPainted(false);
        btnSairLateral.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSairLateral.addActionListener(e -> fecharAplicacao());
        panelMenuLateral.add(btnSairLateral);
        frame.getContentPane().add(panelMenuLateral, BorderLayout.WEST);

        JPanel panelPrincipalContainer = new JPanel(new BorderLayout(0, 20));
        panelPrincipalContainer.setBackground(FUNDO_CLARO);
        panelPrincipalContainer.setBorder(new EmptyBorder(25, 30, 25, 30));

        JPanel panelHeaderConteudo = new JPanel(new BorderLayout());
        panelHeaderConteudo.setBackground(FUNDO_CLARO);

        JPanel agrupadorTextoHeader = new JPanel(new BorderLayout());
        agrupadorTextoHeader.setBackground(FUNDO_CLARO);

        lblTituloPagina = new JLabel("Dashboard");
        lblTituloPagina.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTituloPagina.setForeground(AZUL_ESCURO_NAV);

        lblSubtituloPagina = new JLabel("Visão Geral do Sistema");
        lblSubtituloPagina.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtituloPagina.setForeground(TEXTO_MUTED);

        agrupadorTextoHeader.add(lblTituloPagina, BorderLayout.NORTH);
        agrupadorTextoHeader.add(lblSubtituloPagina, BorderLayout.SOUTH);
        panelHeaderConteudo.add(agrupadorTextoHeader, BorderLayout.WEST);

        panelPrincipalContainer.add(panelHeaderConteudo, BorderLayout.NORTH);
        panelPrincipalContainer.add(panelConteudoDinamico, BorderLayout.CENTER);

        frame.getContentPane().add(panelPrincipalContainer, BorderLayout.CENTER);
    }

    private JPanel criarPainelDashboard() {
        JPanel painel = new JPanel(new BorderLayout(0, 20));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel painelCardsIndicadores = new JPanel(new GridLayout(1, 4, 15, 0));
        painelCardsIndicadores.setBackground(BRANCO);

        painelCardsIndicadores.add(criarCardMetrica("Formadores Ativos", "3", new Color(13, 110, 253)));
        painelCardsIndicadores.add(criarCardMetrica("Turmas em Andamento", "5", new Color(25, 135, 84)));
        painelCardsIndicadores.add(criarCardMetrica("Total de Inscrições", "3", new Color(241, 196, 15)));
        painelCardsIndicadores.add(criarCardMetrica("Cursos Ofertados", "8", new Color(111, 66, 193)));

        painel.add(painelCardsIndicadores, BorderLayout.NORTH);

        JLabel lblBemVindo = new JLabel("Seja bem-vindo ao painel de controle da AcademiaPro.", SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblBemVindo.setForeground(TEXTO_MUTED);
        painel.add(lblBemVindo, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelFormacoes() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel panelAcoes = new JPanel(new BorderLayout());
        panelAcoes.setBackground(BRANCO);

        JPanel panelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelPesquisa.setBackground(BRANCO);

        JTextField txtPesquisarCurso = new JTextField();
        txtPesquisarCurso.setPreferredSize(new Dimension(250, 35));
        panelPesquisa.add(txtPesquisarCurso);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setPreferredSize(new Dimension(90, 35));
        btnFiltrar.setBackground(BRANCO);
        panelPesquisa.add(btnFiltrar);
        panelAcoes.add(panelPesquisa, BorderLayout.WEST);

        JPanel panelBotoesCrud = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panelBotoesCrud.setBackground(BRANCO);

        JButton btnNovo = new JButton("+ Nova Formação");
        btnNovo.setBackground(AZUL_DESTAQUE);
        btnNovo.setForeground(BRANCO);
        btnNovo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnNovo.setPreferredSize(new Dimension(150, 35));

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(BRANCO);
        btnEditar.setPreferredSize(new Dimension(90, 35));

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(248, 215, 218));
        btnEliminar.setForeground(new Color(114, 28, 36));
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEliminar.setPreferredSize(new Dimension(100, 35));

        if (nivelAcesso.equalsIgnoreCase("Administrador")) {
            panelBotoesCrud.add(btnNovo);
            panelBotoesCrud.add(btnEditar);
            panelBotoesCrud.add(btnEliminar);
        } else if (nivelAcesso.equalsIgnoreCase("Secretaria")) {
            panelBotoesCrud.add(btnEditar);
        }

        panelAcoes.add(panelBotoesCrud, BorderLayout.EAST);
        painel.add(panelAcoes, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable();
        table.setRowHeight(35);
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {"FOR-001", "Programação Web Full Stack", "240 horas", "Profissionalizante", "Ativo"},
                {"FOR-002", "Suporte Informático e Hardware", "120 horas", "Técnico", "Ativo"},
            },
            new String[] { "Código", "Nome da Formação", "Carga Horária", "Tipo de Curso", "Estado" }
        ));
        scrollPane.setViewportView(table);
        painel.add(scrollPane, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelFormadores() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable();
        table.setRowHeight(35);
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {"12345", "Malik", "Mangue", "Masculino", "Malikmang@gmail.com", "876543211", "Programação web", "Ativo"}
            },
            new String[] { "Código", "Nome", "Apelido", "Sexo", "Email", "Telefone", "Área de Atuação", "Estado" }
        ));
        scrollPane.setViewportView(table);
        painel.add(scrollPane, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarCardMetrica(String titulo, String valor, Color corDestaque) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(FUNDO_CLARO);
        card.setBorder(new LineBorder(new Color(220, 224, 230), 1, true));
        card.setPreferredSize(new Dimension(180, 90));

        JPanel linhaDecorativa = new JPanel();
        linhaDecorativa.setBackground(corDestaque);
        linhaDecorativa.setPreferredSize(new Dimension(5, 90));
        card.add(linhaDecorativa, BorderLayout.WEST);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        infoPanel.setBackground(FUNDO_CLARO);
        infoPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTitulo.setForeground(TEXTO_MUTED);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblValor.setForeground(AZUL_ESCURO_NAV);

        infoPanel.add(lblTitulo);
        infoPanel.add(lblValor);
        card.add(infoPanel, BorderLayout.CENTER);

        return card;
    }

    private void abrirJanelaLegada(String nomeClasse) {
        try {
            if(nomeClasse.equals("Tela_cadastroProfessor")) new Tela_cadastroProfessor().setVisible(true);
            else if(nomeClasse.equals("Tela_cadastoTurma")) new Tela_cadastoTurma().setVisible(true);
            else if(nomeClasse.equals("Tela_cadastroQualificação")) new Tela_cadastroQualificação().setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao abrir a janela.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fecharAplicacao() {
        int resposta = JOptionPane.showConfirmDialog(null, "Tens a certeza que queres sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}