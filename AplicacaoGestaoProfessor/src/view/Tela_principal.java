package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Panel;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Tela_principal {

    private JFrame frame;
    private JTable table;
    private JTextField txtPesquisarProfessor;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tela_principal window = new Tela_principal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
   
    
    /**
     * Create the application.
     */
    public Tela_principal() {
        initialize(); // ✅ inicializa o frame PRIMEIRO
    }

    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	 frame = new JFrame();
         frame.setBounds(100, 100, 786, 452);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.getContentPane().setLayout(null);
         
         Panel panel = new Panel();
         panel.setBounds(0, 72, 786, 32);
         frame.getContentPane().add(panel);
         panel.setLayout(null);
         
         JMenuBar menuBar = new JMenuBar();
         menuBar.setBackground(Color.WHITE);
         menuBar.setBounds(0, 0, 786, 32);
         panel.add(menuBar);
         
         JMenu mnNovoProfessor = new JMenu("Novo Professor");
         menuBar.add(mnNovoProfessor);
         
         JMenu mnEditar = new JMenu("Editar");
         menuBar.add(mnEditar);
         
         JMenu mnExcluir = new JMenu("Excluir");
         menuBar.add(mnExcluir);
         
         JMenu mnPesquisar = new JMenu("Pesquisar");
         menuBar.add(mnPesquisar);
         
         Panel panel_1 = new Panel();
         panel_1.setBounds(0, 43, 786, 23);
         frame.getContentPane().add(panel_1);
         panel_1.setLayout(null);
         
         JMenuBar menuPrincipal_fixo = new JMenuBar();
         menuPrincipal_fixo.setBounds(0, 0, 786, 23);
         panel_1.add(menuPrincipal_fixo);
         
         JMenu mnFile = new JMenu("File");
         menuPrincipal_fixo.add(mnFile);
         mnFile.setMnemonic(KeyEvent.VK_F);
         
         JMenuItem mntmSair = new JMenuItem("Sair");
         mntmSair.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		//int respota = JOptionPane.showMessageDialog(this, "Tens a certeza que queres sair?", "Confirmacao",JOptionPane.YES_NO_OPTION);
         		int resposta = JOptionPane.showConfirmDialog(null,"Tens a certeza que queres sair?", "Confirmacao",JOptionPane.YES_NO_OPTION);
         		if(resposta == JOptionPane.YES_NO_OPTION) {
         			System.exit(0);
         		}
         		
         	}
         });
         mntmSair.setMnemonic(KeyEvent.VK_S);
         mnFile.add(mntmSair);
         
         JMenu mnCadastros = new JMenu("Cadastros");
         menuPrincipal_fixo.add(mnCadastros);
         
         JMenuItem mntmProfessores = new JMenuItem("Professores");
         mnCadastros.add(mntmProfessores);
         mntmProfessores.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {
        	        new Formulario_ProfessorTela();
        	    }
        	});
         
         JMenuItem mntmTurmas = new JMenuItem("Turmas");
         mnCadastros.add(mntmTurmas);
         mntmTurmas.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {

        	        ViewTurma1 turma = new ViewTurma1();

        	        try {
        	            java.lang.reflect.Field frameField =
        	                    ViewTurma1.class.getDeclaredField("frmGestaoTurmas");

        	            frameField.setAccessible(true);

        	            JFrame frameTurma = (JFrame) frameField.get(turma);
        	            frameTurma.setVisible(true);

        	        } catch (Exception ex) {
        	            ex.printStackTrace();
        	        }
        	    }
        	});
         
         JMenuItem mntmCursos = new JMenuItem("Cursos");
         mnCadastros.add(mntmCursos);
      
         JMenuItem mntmModulocursos = new JMenuItem("Modulo_Cursos");
         mnCadastros.add(mntmModulocursos);
         
         JMenuItem mntmModulos = new JMenuItem("Modulos");
         mnCadastros.add(mntmModulos);
         
         JMenuItem mntmSalario = new JMenuItem("Salario");
         mnCadastros.add(mntmSalario);
         
         JMenu mnConfiguracoes = new JMenu("Configuracoes");
         menuPrincipal_fixo.add(mnConfiguracoes);
         
         JMenuItem mntmUsuarios = new JMenuItem("Usuarios");
         mnConfiguracoes.add(mntmUsuarios);
         
         JMenu mnHelp = new JMenu("Help");
         menuPrincipal_fixo.add(mnHelp);
         
         JMenuItem mntmManual = new JMenuItem("Manual");
         mnHelp.add(mntmManual);
         
         JMenuItem mntmSobre = new JMenuItem("Sobre");
         mnHelp.add(mntmSobre);
         
         JSeparator separator = new JSeparator();
         separator.setBackground(new Color(0, 0, 0));
         separator.setBounds(0, 67, 786, 10);
         frame.getContentPane().add(separator);
         
         Panel panel_2 = new Panel();
         panel_2.setBounds(0, 0, 786, 32);
         frame.getContentPane().add(panel_2);
         panel_2.setLayout(null);
         
         JLabel lblNewLabel = new JLabel("Sistema de Gestao de Professores");
         lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
         lblNewLabel.setIcon(new ImageIcon("/home/malik-dev/git/Aplicacao-Gestao_Professor/AplicacaoGestaoProfessor/src/img/find-icon.png"));
         lblNewLabel.setBounds(0, 12, 294, 17);
         panel_2.add(lblNewLabel);
         
         JPanel panel_3 = new JPanel();
         panel_3.setBackground(new Color(246, 245, 244));
         panel_3.setBounds(0, 104, 160, 348);
         frame.getContentPane().add(panel_3);
         panel_3.setLayout(null);
         
         JButton btnNewButton = new JButton("Professor");
         btnNewButton.setBounds(0, 30, 160, 27);
         panel_3.add(btnNewButton);
         btnNewButton.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {

        	        new Formulario_ProfessorTela();

        	    }
        	});
             
         JButton btnNewButton_1 = new JButton("Turmas");
         btnNewButton_1.setBounds(0, 56, 160, 27);
         panel_3.add(btnNewButton_1);
         btnNewButton_1.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {

        	        ViewTurma1 turma = new ViewTurma1();

        	        try {
        	            java.lang.reflect.Field frameField =
        	                    ViewTurma1.class.getDeclaredField("frmGestaoTurmas");

        	            frameField.setAccessible(true);

        	            JFrame frameTurma = (JFrame) frameField.get(turma);
        	            frameTurma.setVisible(true);

        	        } catch (Exception ex) {
        	            ex.printStackTrace();
        	        }
        	    }
        	});
         
         JButton btnNewButton_2 = new JButton("Modulos");
         btnNewButton_2.setBounds(0, 84, 160, 27);
         panel_3.add(btnNewButton_2);
         
         JLabel lblNavegacao = new JLabel("Navegacao");
         lblNavegacao.setBackground(UIManager.getColor("Button.select"));
         lblNavegacao.setFont(new Font("Montserrat", Font.BOLD, 12));
         lblNavegacao.setForeground(new Color(0, 0, 0));
         lblNavegacao.setBounds(12, 0, 148, 27);
         panel_3.add(lblNavegacao);
         
         JButton btnNewButton_3 = new JButton("Cursos");
         btnNewButton_3.setBounds(0, 111, 160, 27);
         panel_3.add(btnNewButton_3);
         btnNewButton_3.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {

                 Cadastro_Curso telaCurso = new Cadastro_Curso();
                 telaCurso.getFrame().setVisible(true);

             }
         });
         
         JButton btnNewButton_4 = new JButton("Cursos");
         btnNewButton_4.setBounds(0, 139, 160, 27);
         panel_3.add(btnNewButton_4);
         
         JPanel panel_4 = new JPanel();
         panel_4.setBounds(157, 105, 617, 335);
         frame.getContentPane().add(panel_4);
         panel_4.setLayout(null);
         
         JScrollPane scrollPane = new JScrollPane();
         scrollPane.setBounds(12, 50, 593, 273);
         panel_4.add(scrollPane);
         
         table = new JTable();
         table.setCellSelectionEnabled(true);
         table.setFillsViewportHeight(true);
         table.setColumnSelectionAllowed(true);
         table.setModel(new DefaultTableModel(
         	new Object[][] {
         		{null, null, null, null, null},
         	},
         	new String[] {
         		"ID", "Nome Completo", "Contacto", "Curso", "Modulo"
         	}
         ));
         scrollPane.setViewportView(table);
         
         txtPesquisarProfessor = new JTextField();
         txtPesquisarProfessor.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		
         	}
         });
         txtPesquisarProfessor.setBounds(12, 12, 142, 26);
         panel_4.add(txtPesquisarProfessor);
         txtPesquisarProfessor.setColumns(10);
         
         JButton btnNewButton_5 = new JButton("  Pesquisar professor");
         btnNewButton_5.setIcon(new ImageIcon("/home/malik-dev/git/Aplicacao-Gestao_Professor/AplicacaoGestaoProfessor/src/img/find-icon.png"));
         btnNewButton_5.setBounds(175, 11, 203, 27);
         panel_4.add(btnNewButton_5);
     
    
     mntmCursos.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

             Cadastro_Curso telaCurso = new Cadastro_Curso();
             telaCurso.mostrar();
         }
     });
     }
    }
