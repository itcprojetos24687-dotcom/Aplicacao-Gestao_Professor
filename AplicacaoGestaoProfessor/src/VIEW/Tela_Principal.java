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
import dao.ExceptionDao;
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
    private DefaultTableModel modeloModulos;
    private DefaultTableModel modeloSalas;
    private DefaultTableModel modeloQualificacoes;
    private DefaultTableModel modeloFormandos;
    
    private JTextField txtPesquisar;
    
    private JTable tabelaQualificacao;
    private JTable tabelaFormador;
    private JTable tabelaTurma;
    private JTable tabelaFormando;
   private  JTable tabelaMatricula;
   private JTable tabelaSala;
   private JTable tabelaModulo;
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
            new String[] { "Nº Matrícula", "Formando", "Qualificacao", "Nivel", "Data Matrícula" }
        );

        modeloTurmas = new DefaultTableModel(
            new Object[][] {
                
            },
            new String[] { "Código", "Nome Turma", "Ano_Lectivo", "turno","Diretor Turma", "Qualificacao", "Nivel" }
        );

        modeloFormadores = new DefaultTableModel(
            new Object[][] {
                
            },
            new String[] { "Código", "Nome", "Apelido", "Genero", "Email","Estado Civil", "Contacto", "Salario" }
        );

        modeloModulos = new DefaultTableModel(
                new Object[][] {
                    
                },
                new String[] { "Nome do Módulo", "Carga Horária", "Semestres", "Qualificação", "Nível da Qualificação" }
            );
        modeloSalas = new DefaultTableModel(
            new Object[][] {
                
            },
            new String[] { "Identificação", "Localização", "Capacidade", "Tipo de Sala" }
        );
        modeloQualificacoes = new DefaultTableModel(
        	    new Object[][] {
        	        
        	    },
        	    new String[] { "Codigo","Nome da Qualificacao","Coordenador", "Nivel da Qualificacao", "Campo Pertencente" }
        	);
        modeloFormandos = new DefaultTableModel(
        	    new Object[][] {
        	        
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
        btnLogs.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cardLayout.show(panelConteudoDinamico, "Logs");
                    
				}catch(Exception s) {
				
				}
				
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

       //panelConteudoDinamico.add(criarPainelModulos(), "Modulos");

        panelConteudoDinamico.add(criarPainelFormadores(), "Formadores");
        //panelConteudoDinamico.add(criarPainelListaInscricoes(), "Inscrições"); 
        //panelConteudoDinamico.add(new Tela_Incrição(), "FormularioInscricao");
        panelConteudoDinamico.add(criarPainelMatriculas(), "Matrículas");
        //panelConteudoDinamico.add(criarPainelSalas(), "Salas");
        panelConteudoDinamico.add(criarPainelTurmas(), "Turmas");
        panelConteudoDinamico.add(criarPainelQualificacoes(), "Qualificações");
        //panelConteudoDinamico.add(new Tela_cadastroQualificação(), "FormularioQualificacao");
        panelConteudoDinamico.add(criarPainelFormandos(), "Formandos");
        //panelConteudoDinamico.add(new Tela_cadastroFormando(), "FormularioCadastroFormando");
        panelConteudoDinamico.add(new Tela_Logs(), "Logs");
        
        
        
        /*Tela_Logs painelCadastradaLogs = new Tela_Logs(new Tela_Logs.OnLogsCadastradaListener() {
			@Override
			public void onLogsCadastrada() {
				cardLayout.show(panelConteudoDinamico, "Logs");
				lblTituloPagina.setText("Logs");
				lblSubtituloPagina.setText("Gestao de Logs do Sistema");
			}
			@Override
			public void oncancelar() {
				cardLayout.show(painelCadastradaLogs, null);
				
			}
		});
        panelConteudoDinamico.add(painelCadastradaLogs, "Logs");*/
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
      
        

        String[] menus = {"Dashboard", "Formadores", "Turmas", "Qualificações","Formandos", "Cadastros ▾"};
        
        for (String menu : menus) {
        	if (nivelAcesso.equalsIgnoreCase("Formador") && (menu.equals("Cadastros ▾") || menu.equals("Formadores") || menu.equals("Formandos") || menu.equals("Inscrições") || menu.equals("Matrículas") || menu.equals("Modulo") || menu.equals("Qualificações"))) {
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
                
                JMenuItem itemSala = new JMenuItem("Sala");
                JMenuItem itemCampo = new JMenuItem("Campo");
                JMenuItem itemNiveis = new JMenuItem("Níveis");
                
                itemSala.addActionListener(e -> abrirJanelaLegada("Tela_cadastroSala"));
                itemCampo.addActionListener(e -> abrirJanelaLegada("Tela_cadastroCampo"));
                
                
                itemNiveis.addActionListener(e -> abrirJanelaLegada("Tela_cadastroNivel"));
              
                
                if (!nivelAcesso.equalsIgnoreCase("Secretaria")) {
                    menuPopupCadastros.add(itemSala);
                    menuPopupCadastros.add(itemNiveis);
                }
                menuPopupCadastros.add(itemCampo);
//                if (!nivelAcesso.equalsIgnoreCase("Secretaria")) {
//                    menuPopupCadastros.add(itemProfessores);
//                    menuPopupCadastros.add(itemNiveis);
//                }
             
                menuPopupCadastros.addSeparator();
                
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

//        painelCardsIndicadores.add(criarCardMetrica("Formadores Ativos", "3", new Color(13, 110, 253)));
//        painelCardsIndicadores.add(criarCardMetrica("Turmas em Andamento", "5", new Color(25, 135, 84)));
//        painelCardsIndicadores.add(criarCardMetrica("Total de Inscrições", "3", new Color(241, 196, 15)));
//        painelCardsIndicadores.add(criarCardMetrica("Cursos Ofertados", "8", new Color(111, 66, 193)));

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
        		if(nomeCardView.equals("Turmas")) {
        			
        			DefaultTableModel modelo = (DefaultTableModel) tabelaTurma.getModel();
        			String texto = txtPesquisar.getText();
        			try {
        				TurmaController tc = new TurmaController();
        				ArrayList<Turma> turmas = tc.listarTurma(texto);
        				modelo.setRowCount(0);
        				for(Turma t: turmas) {
        					modelo.addRow(new Object[] {
        							      t.getCodigo(),
        							      t.getNome(),
        							      t.getAno_ingresso(),
        							      t.getTurno(),
        							      t.getDiretor_turma().getFomador().getNome(),
        							      t.getQuali_nivel().getQualificacao().getTitulo(),
        							      t.getQuali_nivel().getNivel().getNome()
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
        		if(nomeCardView.equals("Formandos")) {
        		    DefaultTableModel modelo = (DefaultTableModel) tabelaFormando.getModel();
        		    String texto = txtPesquisar.getText();
        		    try {
        		        FormandoController fc = new FormandoController();
        		        ArrayList<Formando> formandos = fc.listarFormando(texto);
        		        modelo.setRowCount(0);
        		        for(Formando f : formandos) {
        		            modelo.addRow(new Object[] {
        		                f.getCodigo(),
        		                f.getNome(),
        		                f.getApelido(),
        		                f.getContacto(),
        		                f.getEmail(),
        		                f.getBi()
        		            });
        		        }
        		    }catch(Exception s) {
        		        s.printStackTrace();
        		    }
        		}
        		if(nomeCardView.equals("Matrículas")) {
        			DefaultTableModel modelo =(DefaultTableModel) tabelaMatricula.getModel();
        			String texto = txtPesquisar.getText();
        			try {
        				MatriculaController mc = new MatriculaController();
        				
        				ArrayList<Matricula> matriculas = mc.listarMatricula(texto);
        				modelo.setRowCount(0);
        				for(Matricula m : matriculas) {
        					modelo.addRow(new Object[] {
        							     m.getCodigo(),
        							     m.getFormando(),
        							     m.getQualificacao(),
        							    m.getNivel(),
        							    m.getData_matricula()
        					});
        				}
        			}catch(Exception s) {
        				s.printStackTrace();
        			}
        		}
        		if(nomeCardView.equals("Modulos")) {
        			DefaultTableModel modelo =(DefaultTableModel) tabelaModulo.getModel();
        			String texto = txtPesquisar.getText();
        			try {
        				ModuloController mc = new ModuloController();
        				
        				ArrayList<Modulo> modulos = mc.listarModulo(texto);
        				modelo.setRowCount(0);
        				for(Modulo m : modulos) {
        					modelo.addRow(new Object[] {
        							     m.getCodigo(),
        							     m.getCarga_horaria(),
        							     m.getQuali_Nivel().getNivel(),
        							    m.getQuali_Nivel().getQualificacao(),
        							    m.getQuali_Nivel().getNivel()
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
        	
           if (nomeCardView.equals("Modulos")) {
           Tela_cadastroModulo tela = new Tela_cadastroModulo();
                    tela.setVisible(true);
            } else if(nomeCardView.equals("Inscrições")) {
                abrirEmJanela(new Tela_Incrição(), "Nova Inscrição", 550, 320);
            } else if(nomeCardView.equals("Turmas")) {
                new Cadastro_Turma().frame.setVisible(true);
            } else if(nomeCardView.equals("Matrículas")) {
                new Tela_Matricula().setVisible(true);
            } else if(nomeCardView.equals("Formadores")) {
               new Cadastro_Formador().setVisible(true); 
            } else if(nomeCardView.equals("Qualificações")) {

            	new Tela_cadastroQualificação().setVisible(true);
                cardLayout.show(panelConteudoDinamico, "FormularioQualificacao");

                //abrirEmJanela(new Tela_cadastroQualificação(), "Cadastro de Qualificação", 800, 400);

            } else if(nomeCardView.equals("Formandos")) {
                abrirEmJanela(new Tela_cadastroFormando(), "Cadastro de Formando", 800, 480);
            } else {
                Object[] novaLinha = new Object[modeloReferencia.getColumnCount()];
                novaLinha[0] = "NOVO";
                modeloReferencia.addRow(novaLinha);
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
						Tela_cadastroQualificação tq = new Tela_cadastroQualificação();
						tq.buscarQualificacao(codigo, titulo, coordenador, nivel, campo);
						tq.setVisible(true);
						//cardLayout.show(panelConteudoDinamico, "FormularioQualificacao");
						
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
				if(nomeCardView.equals("Turmas")) {
				    int linhaSelecionada = tabelaTurma.getSelectedRow();
				    if(linhaSelecionada == -1) {
				        JOptionPane.showMessageDialog(null, "Seleccione um formando para editar.");
				        return;
				    }
				    Integer codigo = (Integer) tabelaTurma.getModel().getValueAt(linhaSelecionada, 0);
				    String nome = (String) tabelaTurma.getModel().getValueAt(linhaSelecionada, 1);
				    Integer Ano_Lectivo = (Integer) tabelaTurma.getModel().getValueAt(linhaSelecionada, 2);
				    String turno = (String) tabelaTurma.getModel().getValueAt(linhaSelecionada, 3);
				    String Diretor_Turma = (String) tabelaTurma.getModel().getValueAt(linhaSelecionada, 4);
				    String Qualificacao = (String) tabelaTurma.getModel().getValueAt(linhaSelecionada, 5);
				    String nivel = (String) tabelaTurma.getModel().getValueAt(linhaSelecionada, 6);
				    try {
				        Cadastro_Turma cd = new Cadastro_Turma();
				        cd.buscarTurma(codigo, nome, Ano_Lectivo,turno,Diretor_Turma,Qualificacao,nivel);
				       cd.frame.setVisible(true);
				    }catch(Exception s) {
				        s.printStackTrace();
				    }
				}
				if(nomeCardView.equals("Formandos")) {
				    int linhaSelecionada = tabelaFormando.getSelectedRow();
				    if(linhaSelecionada == -1) {
				        JOptionPane.showMessageDialog(null, "Seleccione uma Turma para editar.");
				        return;
				    }
				    Integer codigo = (Integer) tabelaFormando.getModel().getValueAt(linhaSelecionada, 0);
				    String nome = (String) tabelaFormando.getModel().getValueAt(linhaSelecionada, 1);
				    String apelido = (String) tabelaFormando.getModel().getValueAt(linhaSelecionada, 2);
				    Integer contacto = (Integer) tabelaFormando.getModel().getValueAt(linhaSelecionada, 3);
				    String email = (String) tabelaFormando.getModel().getValueAt(linhaSelecionada, 4);
				    String bi = (String) tabelaFormando.getModel().getValueAt(linhaSelecionada, 5);
				    try {
				        Tela_cadastroFormando tela = new Tela_cadastroFormando();
				        tela.buscarFormando(codigo, nome, apelido, contacto, email, bi);
				        abrirEmJanela(tela, "Editar Formando", 800, 480);
				    }catch(Exception s) {
				        s.printStackTrace();
				    }
				}
				if(nomeCardView.equals("Matrículas")) {
				    int linhaSelecionada = tabelaMatricula.getSelectedRow();
				    if(linhaSelecionada == -1) {
				        JOptionPane.showMessageDialog(null, "Seleccione uma Matricula para editar.");
				        return;
				    }
				    Integer codigo = (Integer) tabelaMatricula.getModel().getValueAt(linhaSelecionada, 0);
				    Formando formando = (Formando) tabelaMatricula.getModel().getValueAt(linhaSelecionada, 1);
				    Qualificacao qualificacao = (Qualificacao) tabelaMatricula.getModel().getValueAt(linhaSelecionada, 2);
				    Nivel nivel = (Nivel) tabelaMatricula.getModel().getValueAt(linhaSelecionada, 3);
				    String data = (String) tabelaMatricula.getModel().getValueAt(linhaSelecionada, 4);
				   
				    try {
				        Tela_Matricula tm = new Tela_Matricula();
				        tm.buscarMatricula(codigo,formando, qualificacao, nivel, data);
				        tm.setVisible(true);
				    }catch(Exception s) {
				        s.printStackTrace();
				    }
				}
				if(nomeCardView.equals("Modulos")) {
				    int linhaSelecionada = tabelaModulo.getSelectedRow();
				    if(linhaSelecionada == -1) {
				        JOptionPane.showMessageDialog(null, "Seleccione um Modulo para editar.");
				        return;
				    }
				    Integer codigo = (Integer) tabelaModulo.getModel().getValueAt(linhaSelecionada, 0);
				    String nome = (String) tabelaModulo.getModel().getValueAt(linhaSelecionada, 1);
				    Integer carga_horaria = (Integer) tabelaModulo.getModel().getValueAt(linhaSelecionada, 2);
				    String semestre = (String) tabelaModulo.getModel().getValueAt(linhaSelecionada, 3);
				    Qualificacao q = (Qualificacao) tabelaModulo.getModel().getValueAt(linhaSelecionada, 4);
				    Nivel nivel = (Nivel) tabelaModulo.getModel().getValueAt(linhaSelecionada, 5);
				    try {
				    	Tela_cadastroModulo tela = new Tela_cadastroModulo();
				    	tela.buscarModulo(codigo, nome, carga_horaria, semestre, q, nivel);
				    	tela.setVisible(true);
				       
				        
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
        						listarFormadores();
        					}else {
        						JOptionPane.showMessageDialog(null, "falha ao apagar");
        					}
        				}
        			}catch(Exception s) {
        				s.printStackTrace();
        			}
        		}
        		if(nomeCardView.equals("Formandos")) {
        		    int linhaSelecionada = tabelaFormando.getSelectedRow();
        		    if(linhaSelecionada == -1) {
        		        JOptionPane.showMessageDialog(null, "Seleccione um formando para eliminar.");
        		        return;
        		    }
        		    Integer codigo = (Integer) tabelaFormando.getModel().getValueAt(linhaSelecionada, 0);
        		    try {
        		        int confirm = JOptionPane.showConfirmDialog(null, "Tens a certeza que deseja eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);
        		        if(confirm == JOptionPane.YES_OPTION) {
        		            FormandoController fc = new FormandoController();
        		            boolean sucesso = fc.apagarFormando(codigo);
        		            if(sucesso) {
        		                JOptionPane.showMessageDialog(null, "Formando eliminado com sucesso");
        		                listarFormando();
        		                ((DefaultTableModel) tabelaFormando.getModel()).removeRow(linhaSelecionada);
        		            } else {
        		                JOptionPane.showMessageDialog(null, "Falha ao eliminar");
        		            }
        		        }
        		    }catch(Exception s) {
        		        s.printStackTrace();
        		    }
        		}
        		if(nomeCardView.equals("Turmas")) {
        		    int linhaSelecionada = tabelaTurma.getSelectedRow();
        		    if(linhaSelecionada == -1) {
        		        JOptionPane.showMessageDialog(null, "Seleccione uma Turma para eliminar.");
        		        return;
        		    }
        		    Integer codigo = (Integer) tabelaTurma.getModel().getValueAt(linhaSelecionada, 0);
        		    try {
        		        int confirm = JOptionPane.showConfirmDialog(null, "Tens a certeza que deseja eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);
        		        if(confirm == JOptionPane.YES_OPTION) {
        		            TurmaController tc= new TurmaController();
        		       
        		            boolean sucesso = tc.apagarTurma(codigo);
        		            if(sucesso) {
        		                JOptionPane.showMessageDialog(null, "Turma eliminada com sucesso");
        		                listarTurma();
        		                ((DefaultTableModel) tabelaTurma.getModel()).removeRow(linhaSelecionada);
        		            } else {
        		                JOptionPane.showMessageDialog(null, "Falha ao eliminar");
        		            }
        		        }
        		    }catch(Exception s) {
        		        s.printStackTrace();
        		    }
        		}
        		if(nomeCardView.equals("Matrículas")) {
        		    int linhaSelecionada = tabelaMatricula.getSelectedRow();
        		    if(linhaSelecionada == -1) {
        		        JOptionPane.showMessageDialog(null, "Seleccione uma Matricula para eliminar.");
        		        return;
        		    }
        		    Integer codigo = (Integer) tabelaMatricula.getModel().getValueAt(linhaSelecionada, 0);
        		    try {
        		        int confirm = JOptionPane.showConfirmDialog(null, "Tens a certeza que deseja eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);
        		        if(confirm == JOptionPane.YES_OPTION) {
        		            MatriculaController mc = new MatriculaController();
        		       
        		            boolean sucesso = mc.apagarMatricula(codigo);
        		            if(sucesso) {
        		                JOptionPane.showMessageDialog(null, "Matricula eliminada com sucesso");
        		                listarMatriculas();
        		                ((DefaultTableModel) tabelaModulo.getModel()).removeRow(linhaSelecionada);
        		            } else {
        		                JOptionPane.showMessageDialog(null, "Falha ao eliminar");
        		            }
        		        }
        		    }catch(Exception s) {
        		        s.printStackTrace();
        		    }
        		}
        		if(nomeCardView.equals("Modulos")) {
        		    int linhaSelecionada = tabelaModulo.getSelectedRow();
        		    if(linhaSelecionada == -1) {
        		        JOptionPane.showMessageDialog(null, "Seleccione uma Modulo para eliminar.");
        		        return;
        		    }
        		    Integer codigo = (Integer) tabelaModulo.getModel().getValueAt(linhaSelecionada, 0);
        		    try {
        		        int confirm = JOptionPane.showConfirmDialog(null, "Tens a certeza que deseja eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);
        		        if(confirm == JOptionPane.YES_OPTION) {
        		            ModuloController mc = new ModuloController();
        		       
        		            boolean sucesso = mc.apagarModulo(codigo);
        		            if(sucesso) {
        		                JOptionPane.showMessageDialog(null, "Modulo eliminada com sucesso");
        		                ((DefaultTableModel) tabelaModulo.getModel()).removeRow(linhaSelecionada);
        		            } else {
        		                JOptionPane.showMessageDialog(null, "Falha ao eliminar");
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

    private JPanel criarPainelModulos() {                        
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(criarPainelBarraFerramentas("+ Novo Módulo", modeloModulos, "Modulos"), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        tabelaModulo = new JTable(modeloModulos);
        tabelaModulo.setRowHeight(35);
        scrollPane.setViewportView(tabelaModulo);
        painel.add(scrollPane, BorderLayout.CENTER);
        return painel;
    }
    private JPanel criarPainelLogs() {
    	JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(criarPainelBarraFerramentas("Log", modeloFormadores, "Formadores"), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        tabelaFormador= new JTable(modeloFormadores);
        tabelaFormador.setRowHeight(35);
        scrollPane.setViewportView(tabelaFormador);
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
        tabelaMatricula = new JTable(modeloMatriculas);
        tabelaMatricula.setRowHeight(35);
        scrollPane.setViewportView(tabelaMatricula);
        painel.add(scrollPane, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarPainelSalas() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(criarPainelBarraFerramentas("+ Nova Sala", modeloSalas, "Salas"), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        tabelaSala = new JTable(modeloSalas);
        tabelaSala.setRowHeight(35);
        scrollPane.setViewportView(tabelaSala);
        painel.add(scrollPane, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarPainelTurmas() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(BRANCO);
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.add(criarPainelBarraFerramentas("+ Nova Turma", modeloTurmas, "Turmas"), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        tabelaTurma = new JTable(modeloTurmas);
        tabelaTurma.setRowHeight(35);
        scrollPane.setViewportView(tabelaTurma);
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
        tabelaFormando = new JTable(modeloFormandos);
        tabelaFormando.setRowHeight(35);
        scrollPane.setViewportView(tabelaFormando);
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
            if (nomeClasse.equals("Tela_cadastroQualificação")) new Tela_cadastroQualificação().setVisible(true);
            else if (nomeClasse.equals("Tela_cadastroNivel")) new Tela_cadastroNivel().setVisible(true);
            else if (nomeClasse.equals("Tela_cadastroSala")) new Tela_cadastroSala().setVisible(true);
            else if (nomeClasse.equals("Tela_cadastroCampo")) new Tela_cadastroCampo().setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao abrir a janela.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void abrirEmJanela(JPanel painel, String titulo, int largura, int altura) {
        JFrame janela = new JFrame(titulo);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setSize(largura, altura);
        janela.setLocationRelativeTo(frame);
        janela.getContentPane().add(painel);
        janela.setVisible(true);
    }
    private void logOut() {
    	Tela_login tl = new Tela_login();
    	tl.setVisible(true);
    	frame.dispose();
    	try {
    		new UsuarioController().logout(Seccao.obterUtilizador());
    	} catch (ExceptionDao e) {
    		JOptionPane.showMessageDialog(null, "Erro ao registar log de saída: " + e.getMessage());
    	}
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
   public void listarFormando() throws Exception{
	   DefaultTableModel modelo = (DefaultTableModel) tabelaFormando.getModel();
	    String texto = txtPesquisar.getText();
	    try {
	        FormandoController fc = new FormandoController();
	        ArrayList<Formando> formandos = fc.listarFormando(texto);
	        modelo.setRowCount(0);
	        for(Formando f : formandos) {
	            modelo.addRow(new Object[] {
	                f.getCodigo(),
	                f.getNome(),
	                f.getApelido(),
	                f.getContacto(),
	                f.getEmail(),
	                f.getBi()
	            });
	        }
	    }catch(Exception s) {
	        s.printStackTrace();
	    }
	    
    }
   public void listarTurma() throws Exception{
	   int linhaSelecionada = tabelaTurma.getSelectedRow();
	    if(linhaSelecionada == -1) {
	        JOptionPane.showMessageDialog(null, "Seleccione um formando para editar.");
	        return;
	    }
	    Integer codigo = (Integer) tabelaTurma.getModel().getValueAt(linhaSelecionada, 0);
	    String nome = (String) tabelaTurma.getModel().getValueAt(linhaSelecionada, 1);
	    Integer Ano_Lectivo = (Integer) tabelaTurma.getModel().getValueAt(linhaSelecionada, 2);
	    String turno = (String) tabelaTurma.getModel().getValueAt(linhaSelecionada, 3);
	    String Diretor_Turma = (String) tabelaTurma.getModel().getValueAt(linhaSelecionada, 4);
	    String Qualificacao = (String) tabelaTurma.getModel().getValueAt(linhaSelecionada, 5);
	    String nivel = (String) tabelaTurma.getModel().getValueAt(linhaSelecionada, 6);
	    try {
	        Cadastro_Turma cd = new Cadastro_Turma();
	        cd.buscarTurma(codigo, nome, Ano_Lectivo,turno,Diretor_Turma,Qualificacao,nivel);
	       cd.frame.setVisible(true);
	    }catch(Exception s) {
	        s.printStackTrace();
	    }
   }
   public void listarFormadores()throws Exception{
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
   public void listarMatriculas() throws Exception {
	   DefaultTableModel modelo =(DefaultTableModel) tabelaMatricula.getModel();
		String texto = txtPesquisar.getText();
		try {
			MatriculaController mc = new MatriculaController();
			
			ArrayList<Matricula> matriculas = mc.listarMatricula(texto);
			modelo.setRowCount(0);
			for(Matricula m : matriculas) {
				modelo.addRow(new Object[] {
						     m.getCodigo(),
						     m.getFormando(),
						     m.getQualificacao(),
						    m.getNivel(),
						    m.getData_matricula()
				});
			}
		}catch(Exception s) {
			s.printStackTrace();
		}
   }
}