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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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

import model.*;
import controller.*;
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

    private DefaultTableModel modeloInscricoes;
    private DefaultTableModel modeloMatriculas;
    private DefaultTableModel modeloTurmas;
    private DefaultTableModel modeloFormadores;
    private DefaultTableModel modeloFormacoes;
    private DefaultTableModel modeloSalas;
    private DefaultTableModel modeloQualificacoes;
    private DefaultTableModel modeloFormandos;
    
    private JTextField txtPesquisar;
    
    private JTable tabelaQualificacao;
    private JTable tabelaFormador;
    
    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color FUNDO_CLARO      = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                Tela_Principal window = new Tela_Principal("Administrador");
//                window.frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    public Tela_Principal(Usuario u) {
         this.nivelAcesso = u.getPerfil().getNome();
         inicializarModelos();
         initialize();
    }

//    public Tela_Principal(Perfil p, Tela_login tl) {
//		// TODO Auto-generated constructor stub
//	}

	private void inicializarModelos() {
        modeloInscricoes = new DefaultTableModel(
            new Object[][] {
                {"INC-001", "Lucas Silva", "Bases de Dados", "02/07/2026", "Primeiro semestre", "Confirmada"},
                {"INC-002", "Maria Santos", "Desenvolvimento Web", "01/07/2026", "Segundo semestre", "Pendente"}
            },
            new String[] { "Nº Inscrição", "Formando", "Módulo / Curso", "Data", "Semestre", "Estado" }
        );

        modeloMatriculas = new DefaultTableModel(
            new Object[][] {
                {"MAT-101", "Ana Martins", "Turma A", "Sala 03", "06/07/2026"}
            },
            new String[] { "Nº Matrícula", "Aluno", "Turma", "Sala Alocada", "Data Matrícula" }
        );

        modeloTurmas = new DefaultTableModel(
            new Object[][] {
                {"TURMA-A", "Programação Web", "Laboral", "Malik Mangue"}
            },
            new String[] { "Código Turma", "Curso/Formação", "Regime", "Formador Alocado" }
        );

        modeloFormadores = new DefaultTableModel(
            new Object[][] {
                
            },
            new String[] { "Código", "Nome", "Apelido", "Genero", "Email","Estado Civil", "Contacto", "Salario" }
        );

        modeloFormacoes = new DefaultTableModel(
            new Object[][] {
                {"FOR-001", "Programação Web Full Stack", "240 horas", "Profissionalizante", "Ativo"}
            },
            new String[] { "Código", "Nome da Formação", "Carga Horária", "Tipo de Curso", "Estado" }
        );

        modeloSalas = new DefaultTableModel(
            new Object[][] {
                {"Sala 01", "Bloco A", "30 Lugares", "Laboratório de Informática"}
            },
            new String[] { "Identificação", "Localização", "Capacidade", "Tipo de Sala" }
        );
        modeloQualificacoes = new DefaultTableModel(
        	    new Object[][] {
        	        {"QUAL-001", "Programação Web","Edmundo Mapotere", "CV4", "Informatica"}
        	    },
        	    new String[] { "Codigo","Nome da Qualificacao","Coordenador", "Nivel da Qualificacao", "Campo Pertencente" }
        	);
        modeloFormandos = new DefaultTableModel(
        	    new Object[][] {
        	        {"FORM-001", "Edmundo", "Mapotere", "876543211", "edmundo@gmail.com", "123456789A"}
        	    },
        	    new String[] { "Código", "Nome", "Apelido", "Contacto", "Email", "BI" }
        	);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("SGP - Sistema de Gestão de Formação");
        frame.setBounds(100, 100, 1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel panelTopo = new JPanel(new BorderLayout());
        panelTopo.setBackground(AZUL_ESCURO_NAV);
        panelTopo.setPreferredSize(new Dimension(1280, 50));
        panelTopo.setBorder(new EmptyBorder(0, 20, 0, 20));

        JLabel lblTituloApp = new JLabel("Sistema de Gestao de Formadores - Painel de Controle");
        lblTituloApp.setForeground(BRANCO);
        lblTituloApp.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelTopo.add(lblTituloApp, BorderLayout.WEST);

        JPanel panelTopoDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        panelTopoDireita.setBackground(AZUL_ESCURO_NAV);

        JLabel lblAdmin = new JLabel("Olá, " + nivelAcesso);
        lblAdmin.setForeground(BRANCO);
        lblAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelTopoDireita.add(lblAdmin);

        JButton btnUtilizadores = new JButton(" Gerir Utilizadores");
        btnUtilizadores.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnUtilizadores.setForeground(BRANCO);
        btnUtilizadores.setBackground(new Color(25, 52, 88));
        btnUtilizadores.setBorder(new LineBorder(new Color(40, 75, 120), 1, true));
        btnUtilizadores.setPreferredSize(new Dimension(150, 30));
        btnUtilizadores.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUtilizadores.setFocusPainted(false);
        
        JButton btnLogs = new JButton(" Acessar Logs");
        btnLogs.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLogs.setForeground(BRANCO);
        btnLogs.setBackground(new Color(25, 52, 88));
        btnLogs.setBorder(new LineBorder(new Color(40, 75, 120), 1, true));
        btnLogs.setPreferredSize(new Dimension(150, 30));
        btnLogs.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogs.setFocusPainted(false);
        
        
        btnUtilizadores.addActionListener(e -> {
            try {
                new Tela_Utilizadores().setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Não foi possível abrir a gestão de utilizadores.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        if (nivelAcesso.equalsIgnoreCase("Administrador")) {
            panelTopoDireita.add(btnUtilizadores);
            panelTopoDireita.add(btnLogs);
        }
        if (nivelAcesso.equalsIgnoreCase("Auditor")) {
        	panelTopoDireita.add(btnLogs);
        }
        
        panelTopo.add(panelTopoDireita, BorderLayout.EAST);
        frame.getContentPane().add(panelTopo, BorderLayout.NORTH);

        JPanel panelMenuLateral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 8));
        panelMenuLateral.setBackground(AZUL_ESCURO_NAV);
        panelMenuLateral.setPreferredSize(new Dimension(220, 670));

        cardLayout = new CardLayout();
        panelConteudoDinamico = new JPanel(cardLayout);

        panelConteudoDinamico.add(criarPainelDashboard(), "Dashboard");
        panelConteudoDinamico.add(criarPainelFormacoes(), "Formações");
        panelConteudoDinamico.add(criarPainelFormadores(), "Formadores");
        panelConteudoDinamico.add(criarPainelListaInscricoes(), "Inscrições"); 
        panelConteudoDinamico.add(new Tela_Incrição(), "FormularioInscricao");
        panelConteudoDinamico.add(criarPainelMatriculas(), "Matrículas");
        panelConteudoDinamico.add(criarPainelSalas(), "Salas");
        panelConteudoDinamico.add(criarPainelTurmas(), "Turmas");
        panelConteudoDinamico.add(criarPainelQualificacoes(), "Qualificações");
        panelConteudoDinamico.add(new Tela_cadastroQualificação(), "FormularioQualificacao");
        panelConteudoDinamico.add(criarPainelFormandos(), "Formandos");
        panelConteudoDinamico.add(new Tela_cadastroFormando(), "FormularioCadastroFormando");
        
        
        
        //Painel de cadastro de Qualificacao
        
        // Painel Embutido de Cadastro de Turma
        Tela_cadastoTurma painelCadastroTurma = new Tela_cadastoTurma(new Tela_cadastoTurma.OnTurmaCadastradaListener() {
            @Override
            public void onTurmaCadastrada(String codigo, String curso, String regime, String formador) {
                modeloTurmas.addRow(new Object[]{codigo, curso, regime, formador});
                cardLayout.show(panelConteudoDinamico, "Turmas");
                lblTituloPagina.setText("Turmas");
                lblSubtituloPagina.setText("Gestão de Turmas do Sistema");
            }
            @Override public void onCancelar() { cardLayout.show(panelConteudoDinamico, "Turmas"); }
        });
        panelConteudoDinamico.add(painelCadastroTurma, "FormularioCadastroTurma");

        // Painel Embutido de Cadastro de Matrícula
        Tela_Matricula painelCadastroMatricula = new Tela_Matricula(new Tela_Matricula.OnMatriculaCadastradaListener() {
            @Override
            public void onMatriculaCadastrada(String numMatricula, String aluno, String turma, String sala, String data) {
                String idGerado = "MAT-" + (100 + modeloMatriculas.getRowCount() + 1);
                modeloMatriculas.addRow(new Object[]{idGerado, aluno, turma, sala, data});
                cardLayout.show(panelConteudoDinamico, "Matrículas");
                lblTituloPagina.setText("Matrículas");
                lblSubtituloPagina.setText("Gestão de Matrículas do Sistema");
            }
            @Override public void onCancelar() { cardLayout.show(panelConteudoDinamico, "Matrículas"); }
        });
        panelConteudoDinamico.add(painelCadastroMatricula, "FormularioCadastroMatricula");

        // Painel Embutido de Cadastro de Professor
        Tela_cadastroProfessor painelCadastroProfessor = new Tela_cadastroProfessor(new Tela_cadastroProfessor.OnProfessorCadastradoListener() {
            @Override
            public void onProfessorCadastrado(String codigo, String nome, String apelido, String sexo, String email, String telefone, String area, String estado) {
                String idGerado = "PRF-" + (10000 + modeloFormadores.getRowCount() + 1);
                modeloFormadores.addRow(new Object[]{idGerado, nome, apelido, sexo, email, telefone, area, estado});
                cardLayout.show(panelConteudoDinamico, "Formadores");
                lblTituloPagina.setText("Formadores");
                lblSubtituloPagina.setText("Gestão de Formadores do Sistema");
            }
            @Override public void onCancelar() { cardLayout.show(panelConteudoDinamico, "Formadores"); }
        });
        // Painel
        panelConteudoDinamico.add(painelCadastroProfessor, "FormularioCadastroProfessor");
        
        String[] menus = {"Dashboard", "Formações", "Formadores", "Inscrições", "Matrículas", "Salas", "Turmas", "Qualificações","Formandos", "Cadastros ▾"};
        
        for (String menu : menus) {
        	if (nivelAcesso.equalsIgnoreCase("Formador") && (menu.equals("Cadastros ▾") || menu.equals("Formadores") || menu.equals("Formandos") || menu.equals("Inscrições") || menu.equals("Matrículas") || menu.equals("Salas") || menu.equals("Qualificações"))) {
                continue;
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
                
                JMenuItem itemProfessores = new JMenuItem("Professores");
                JMenuItem itemQualificacoes = new JMenuItem("Qualificações");
                JMenuItem itemNiveis = new JMenuItem("Níveis");
                
                JMenuItem itemDropdownInscricao = new JMenuItem("Inscrição");
                JMenuItem itemDropdownMatricula = new JMenuItem("Matrícula");
                JMenuItem itemDropdownTurma = new JMenuItem("Turma");
                JMenuItem itemFormandos = new JMenuItem("Formandos");

                itemFormandos.addActionListener(e -> {
                    cardLayout.show(panelConteudoDinamico, "FormularioCadastroFormando");
                    lblTituloPagina.setText("Novo Formando");
                    lblSubtituloPagina.setText("Preencha o formulário para registar um novo formando");
                });

                itemProfessores.addActionListener(e -> {
                    cardLayout.show(panelConteudoDinamico, "FormularioCadastroProfessor");
                    lblTituloPagina.setText("Cadastrar Professor / Formador");
                    lblSubtituloPagina.setText("Insira os dados do novo docente para listagem automática");
                });

                itemQualificacoes.addActionListener(e -> {
                    cardLayout.show(panelConteudoDinamico, "FormularioQualificacao");
                    lblTituloPagina.setText("Nova Qualificação");
                    lblSubtituloPagina.setText("Preencha o formulário para registar uma nova qualificação");
                });
                
                
                itemNiveis.addActionListener(e -> abrirJanelaLegada("Tela_cadastroNivel"));
                // ATUALIZADO: Abre o formulário diretamente
                itemDropdownInscricao.addActionListener(e -> {
                    cardLayout.show(panelConteudoDinamico, "FormularioInscricao");
                    lblTituloPagina.setText("Nova Inscrição");
                    lblSubtituloPagina.setText("Preencha o formulário para registar uma nova inscrição");
                });

                itemDropdownMatricula.addActionListener(e -> {
                    cardLayout.show(panelConteudoDinamico, "FormularioCadastroMatricula");
                    lblTituloPagina.setText("Efetivar Matrícula");
                    lblSubtituloPagina.setText("Insira as credenciais para realizar uma nova matrícula");
                });

                itemDropdownTurma.addActionListener(e -> {
                    cardLayout.show(panelConteudoDinamico, "FormularioCadastroTurma");
                    lblTituloPagina.setText("Cadastrar Turma");
                    lblSubtituloPagina.setText("Insira os dados da nova turma para listagem automática");
                });
                
                
//                if (!nivelAcesso.equalsIgnoreCase("Secretaria")) {
//                    menuPopupCadastros.add(itemProfessores);
//                    menuPopupCadastros.add(itemNiveis);
//                }
                menuPopupCadastros.add(itemQualificacoes);
                menuPopupCadastros.addSeparator();
                menuPopupCadastros.add(itemDropdownInscricao);
                menuPopupCadastros.add(itemDropdownMatricula);
                menuPopupCadastros.add(itemDropdownTurma);
                menuPopupCadastros.add(itemFormandos);

                btnMenu.addActionListener(e -> menuPopupCadastros.show(btnMenu, btnMenu.getWidth(), 0));
            } else {
                corPadraoFundo = AZUL_ESCURO_NAV;
                corHoverFundo = new Color(25, 52, 88);
                btnMenu.setBackground(corPadraoFundo);
                btnMenu.setBorder(null);
            }

            if (!menu.equals("Cadastros ▾")) {
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

        JButton btnLogOut = new JButton("Sign out");
        btnLogOut.setPreferredSize(new Dimension(200, 40));
        btnLogOut.setBackground(new Color(217, 83, 79));
        btnLogOut.setForeground(BRANCO);
        btnLogOut.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLogOut.setBorder(null);
        btnLogOut.setFocusPainted(false);
        btnLogOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogOut.addActionListener(e -> logOut());
        panelMenuLateral.add(btnLogOut);
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

        JLabel lblBemVindo = new JLabel("Seja bem-vindo ao painel de controle da Sistema de Gestao de Formadores", SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblBemVindo.setForeground(TEXTO_MUTED);
        painel.add(lblBemVindo, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelBarraFerramentas(String rotuloBotaoNovo, DefaultTableModel modeloReferencia, String nomeCardView) {
        JPanel panelAcoes = new JPanel(new BorderLayout());
        panelAcoes.setBackground(BRANCO);

        JPanel panelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelPesquisa.setBackground(BRANCO);

        txtPesquisar = new JTextField();
        txtPesquisar.setPreferredSize(new Dimension(250, 35));
        panelPesquisa.add(txtPesquisar);

        JButton btnFiltrar = new JButton("Pesquisar");
        btnFiltrar.setPreferredSize(new Dimension(90, 35));
        btnFiltrar.setBackground(BRANCO);
        btnFiltrar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnFiltrar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        panelPesquisa.add(btnFiltrar);
        panelAcoes.add(panelPesquisa, BorderLayout.WEST);
        
        btnFiltrar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(nomeCardView.equals("Qualificações")) {
        			
        			DefaultTableModel modelo = (DefaultTableModel) tabelaQualificacao.getModel();
        			String texto = txtPesquisar.getText();
        			try {
        				QualificacaoController qc = new QualificacaoController();
        				ArrayList<Qualificacao> qualificacoes = qc.listarQualificacao(texto);
        				modelo.setRowCount(0);
        				for(Qualificacao q: qualificacoes) {
        					modelo.addRow(new Object[] {
        							      q.getCodigo(),
        							      q.getTitulo(),
        							      q.getCoordenador().getFormador().getNome(),
        							      q.getNivel().getNome(),
        							      q.getCampo().getNome(),
        					});
        				}
        			}catch(Exception s) {
        				s.printStackTrace();
        			}
        		}
        		if(nomeCardView.equals("Formadores")) {
        			DefaultTableModel modelo = (DefaultTableModel) tabelaFormador.getModel();
        			String texto = txtPesquisar.getText();
        			try {
        				
        				FormadorController fc = new FormadorController();
        				ArrayList<Formador> formadores = fc.listarFormador(texto);
        				modelo.setRowCount(0);
        				for(Formador f: formadores) {
        					modelo.addRow(new Object[] {
        							          f.getCodigo(),
        							          f.getNome(),
        							          f.getApelido(),
        							          f.getGenero(),
        							          f.getEmail(),
        							          f.getEstadoCivil(),
        							          f.getContacto(),
        							          f.getSalario(),
        					});
        				}
        			}catch(Exception s) {
        				s.printStackTrace();
        			}
        		}
        		
        		
        	}
        });
        JPanel panelBotoesCrud = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panelBotoesCrud.setBackground(BRANCO);

        JButton btnNovo = new JButton(rotuloBotaoNovo);
        btnNovo.setBackground(AZUL_DESTAQUE);
        btnNovo.setForeground(BRANCO);
        btnNovo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnNovo.setPreferredSize(new Dimension(150, 35));
        btnNovo.setBorder(null);

        btnNovo.addActionListener(e -> {
            if(nomeCardView.equals("Inscrições")) {
                cardLayout.show(panelConteudoDinamico, "FormularioInscricao");
            } else if(nomeCardView.equals("Turmas")) {
                cardLayout.show(panelConteudoDinamico, "FormularioCadastroTurma");
            } else if(nomeCardView.equals("Matrículas")) {
                cardLayout.show(panelConteudoDinamico, "FormularioCadastroMatricula");
            } else if(nomeCardView.equals("Formadores")) {
               new Cadastro_Formador().setVisible(true); 
            } else if(nomeCardView.equals("Qualificações")) {
                cardLayout.show(panelConteudoDinamico, "FormularioQualificacao");
            } else if(nomeCardView.equals("Formandos")) {
                cardLayout.show(panelConteudoDinamico, "FormularioCadastroFormando");
            } else {
                
            }
        });

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(BRANCO);
        btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnEditar.setPreferredSize(new Dimension(90, 35));
        btnEditar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nomeCardView.equals("Qualificações")) {
					Integer codigo = (Integer)tabelaQualificacao.getModel().getValueAt(tabelaQualificacao.getSelectedRow(), 0);
					String titulo = (String)tabelaQualificacao.getModel().getValueAt(tabelaQualificacao.getSelectedRow(), 1);
					String coordenador = (String)tabelaQualificacao.getModel().getValueAt(tabelaQualificacao.getSelectedRow(), 2);
					String nivel = (String)tabelaQualificacao.getModel().getValueAt(tabelaQualificacao.getSelectedRow(), 3);
					String campo = (String)tabelaQualificacao.getModel().getValueAt(tabelaQualificacao.getSelectedRow(), 4);
					
					try {
						new Tela_cadastroQualificação().buscarQualificacao(codigo, titulo, coordenador, nivel, campo);
						cardLayout.show(panelConteudoDinamico, "FormularioQualificacao");
						
					}catch(Exception s) {
						s.printStackTrace();
					}
					
				
				};
				if(nomeCardView.equals("Formadores")) {
					Integer codigo=(Integer)tabelaFormador.getModel().getValueAt(tabelaFormador.getSelectedRow(), 0);
					String nome=(String)tabelaFormador.getModel().getValueAt(tabelaFormador.getSelectedRow(), 1);
					String apelido=(String)tabelaFormador.getModel().getValueAt(tabelaFormador.getSelectedRow(), 2);
					String genero=(String)tabelaFormador.getModel().getValueAt(tabelaFormador.getSelectedRow(), 3);
					String estadoCivil=(String)tabelaFormador.getModel().getValueAt(tabelaFormador.getSelectedRow(), 5);
					String email=(String)tabelaFormador.getModel().getValueAt(tabelaFormador.getSelectedRow(), 4);
					Integer contacto=(Integer)tabelaFormador.getModel().getValueAt(tabelaFormador.getSelectedRow(), 6);
					Double Salario=(Double)tabelaFormador.getModel().getValueAt(tabelaFormador.getSelectedRow(), 7);
					try {
						Cadastro_Formador cf = new Cadastro_Formador();
						cf.setVisible(true);
						cf.buscarFormador(codigo, nome, apelido, email, genero, estadoCivil, contacto, 0);
						
					}catch(Exception s) {
						s.printStackTrace();
					}
				}
				
			}
        });

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(248, 215, 218));
        btnEliminar.setForeground(new Color(114, 28, 36));
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEliminar.setPreferredSize(new Dimension(100, 35));
        btnEliminar.setBorder(new LineBorder(new Color(245, 198, 203), 1));

        btnEliminar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(nomeCardView.equals("Formadores")) {
        			Integer codigo = (Integer) tabelaFormador.getModel().getValueAt(tabelaFormador.getSelectedRow(), 0);
        			boolean sucesso;
        			try {
        				FormadorController fc = new FormadorController();        			
        				int confirm = JOptionPane.showConfirmDialog(null,"Tens a certeza que deseja deletar","Confirmar",JOptionPane.YES_NO_OPTION);
        				if(confirm == JOptionPane.YES_OPTION) {
        					sucesso = fc.apagarFormador(codigo);
        					if(sucesso) {
        						JOptionPane.showMessageDialog(null, "Formador apagado com sucesso");
        					}else {
        						JOptionPane.showMessageDialog(null, "falha ao apagar");
        					}
        				}
        			}catch(Exception s) {
        				s.printStackTrace();
        			}
        		}
        	}
        });
        if(nivelAcesso.equalsIgnoreCase("Operador")) {
        	btnEditar.setEnabled(false);
        	btnEliminar.setEnabled(false);
        }
        panelBotoesCrud.add(btnNovo);
        panelBotoesCrud.add(btnEditar);
        
        panelBotoesCrud.add(btnEliminar);

        panelAcoes.add(panelBotoesCrud, BorderLayout.EAST);
        return panelAcoes;
    }

    private JPanel criarPainelFormacoes() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(criarPainelBarraFerramentas("+ Nova Formação", modeloFormacoes, "Formações"), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable(modeloFormacoes);
        table.setRowHeight(35);
        scrollPane.setViewportView(table);
        painel.add(scrollPane, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarPainelFormadores() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(criarPainelBarraFerramentas("+ Novo Formador", modeloFormadores, "Formadores"), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        tabelaFormador= new JTable(modeloFormadores);
        tabelaFormador.setRowHeight(35);
        scrollPane.setViewportView(tabelaFormador);
        painel.add(scrollPane, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarPainelListaInscricoes() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(criarPainelBarraFerramentas("+ Nova Inscrição", modeloInscricoes, "Inscrições"), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable(modeloInscricoes);
        table.setRowHeight(35);
        scrollPane.setViewportView(table);
        painel.add(scrollPane, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarPainelMatriculas() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(criarPainelBarraFerramentas("+ Nova Matrícula", modeloMatriculas, "Matrículas"), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable(modeloMatriculas);
        table.setRowHeight(35);
        scrollPane.setViewportView(table);
        painel.add(scrollPane, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarPainelSalas() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(criarPainelBarraFerramentas("+ Nova Sala", modeloSalas, "Salas"), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable(modeloSalas);
        table.setRowHeight(35);
        scrollPane.setViewportView(table);
        painel.add(scrollPane, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarPainelTurmas() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(criarPainelBarraFerramentas("+ Nova Turma", modeloTurmas, "Turmas"), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable(modeloTurmas);
        table.setRowHeight(35);
        scrollPane.setViewportView(table);
        painel.add(scrollPane, BorderLayout.CENTER);
        return painel;
    }
    
    public JPanel criarPainelQualificacoes() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(criarPainelBarraFerramentas("+ Nova Qualificação", modeloQualificacoes, "Qualificações"), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        tabelaQualificacao = new JTable(modeloQualificacoes);
        tabelaQualificacao.setRowHeight(35);
        scrollPane.setViewportView(tabelaQualificacao);
        painel.add(scrollPane, BorderLayout.CENTER);
        return painel;
    }
    private JPanel criarPainelFormandos() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(criarPainelBarraFerramentas("+ Novo Formando", modeloFormandos, "Formandos"), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable(modeloFormandos);
        table.setRowHeight(35);
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
            if(nomeClasse.equals("Tela_cadastroQualificação")) new Tela_cadastroQualificação().setVisible(true);
            else if(nomeClasse.equals("Tela_cadastroNivel")) new Tela_cadastroNivel().setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao abrir a janela.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void logOut() {
    	Tela_login tl = new Tela_login();
    	tl.setVisible(true);
    	frame.dispose();
    	Seccao.terminarSeccao();
    }

    private void fecharAplicacao() {
        int resposta = JOptionPane.showConfirmDialog(null, "Tens a certeza que queres sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    public void abrir() {
    	frame.setVisible(true);
    }
    public void listar() throws Exception{
    	DefaultTableModel modelo = (DefaultTableModel) tabelaFormador.getModel();
		String texto = txtPesquisar.getText();
		
			
			FormadorController fc = new FormadorController();
			ArrayList<Formador> formadores = fc.listarFormador(texto);
			modelo.setRowCount(0);
			for(Formador f: formadores) {
				modelo.addRow(new Object[] {
						          f.getCodigo(),
						          f.getNome(),
						          f.getApelido(),
						          f.getGenero(),
						          f.getEmail(),
						          f.getEstadoCivil(),
						          f.getContacto(),
						          f.getSalario(),
				});
			}
		
    }
}