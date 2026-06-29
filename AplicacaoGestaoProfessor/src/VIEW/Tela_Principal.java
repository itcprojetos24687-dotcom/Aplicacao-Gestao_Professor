package VIEW;

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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Panel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Tela_Principal {

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
                    Tela_Principal window = new Tela_Principal();
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
    public Tela_Principal() {
        initialize(); // ✅ inicializa o frame PRIMEIRO
    }

    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	 frame = new JFrame();
    	 frame.getContentPane().setBackground(Color.BLUE);
         frame.setBounds(100, 100, 995, 544);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.getContentPane().setLayout(null);
         
         Panel panel = new Panel();
         panel.setBackground(Color.BLUE);
         panel.setBounds(10, 72, 835, 32);
         frame.getContentPane().add(panel);
         panel.setLayout(null);
         
         JMenuBar menuBar = new JMenuBar();
         menuBar.setBackground(Color.WHITE);
         menuBar.setBounds(0, 0, 870, 32);
         panel.add(menuBar);
         
         JMenu mnNovoProfessor = new JMenu("Novo Professor");
         mnNovoProfessor.setForeground(Color.BLACK);
         menuBar.add(mnNovoProfessor);
         
         JMenu mnEditar = new JMenu("Editar");
         mnEditar.setForeground(Color.BLACK);
         menuBar.add(mnEditar);
         
         JMenu mnExcluir = new JMenu("Excluir");
         mnExcluir.setForeground(Color.BLACK);
         menuBar.add(mnExcluir);
         
         JMenu mnPesquisar = new JMenu("Pesquisar");
         mnPesquisar.setForeground(Color.BLACK);
         menuBar.add(mnPesquisar);
         
         Panel panel_1 = new Panel();
         panel_1.setBackground(Color.BLUE);
         panel_1.setBounds(0, 43, 786, 23);
         frame.getContentPane().add(panel_1);
         panel_1.setLayout(null);
         
         JMenuBar menuPrincipal_fixo = new JMenuBar();
         menuPrincipal_fixo.setBackground(Color.WHITE);
         menuPrincipal_fixo.setBounds(10, 0, 866, 23);
         panel_1.add(menuPrincipal_fixo);
         
         JMenu mnFile = new JMenu("File");
         mnFile.setForeground(Color.BLACK);
         mnFile.setFont(new Font("Times New Roman", Font.PLAIN, 12));
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
         mnCadastros.setFont(new Font("Times New Roman", Font.PLAIN, 13));
         mnCadastros.setForeground(Color.BLACK);
         menuPrincipal_fixo.add(mnCadastros);
         
         JMenuItem mntmProfessores = new JMenuItem("Professores");
         mnCadastros.add(mntmProfessores);
         mntmProfessores.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {
        	        new Tela_cadastroProfessor();
        	    }
        	});
         
         JMenuItem mntmTurmas = new JMenuItem("Turmas");
         mnCadastros.add(mntmTurmas);
         mntmTurmas.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {

        	    }
        	});
         
         JMenuItem mntmCursos = new JMenuItem("Cursos");
         mnCadastros.add(mntmCursos);
      
         JMenuItem mntmModulocursos = new JMenuItem("Inscrições");
         mnCadastros.add(mntmModulocursos);
         
         JMenuItem mntmModulos = new JMenuItem("Modulos");
         mnCadastros.add(mntmModulos);
         
         JMenuItem mntmQualificações = new JMenuItem("Qualificações");
         mnCadastros.add(mntmQualificações);
         
         JMenuItem mntmMatrículas = new JMenuItem("Matrículas");
         mnCadastros.add(mntmMatrículas);
         
         JMenu mnConfiguracoes = new JMenu("Configuracoes");
         mnConfiguracoes.setFont(new Font("Times New Roman", Font.PLAIN, 13));
         mnConfiguracoes.setForeground(Color.BLACK);
         menuPrincipal_fixo.add(mnConfiguracoes);
         
         JMenuItem mntmUsuarios = new JMenuItem("Usuarios");
         mnConfiguracoes.add(mntmUsuarios);
         
         JMenu mnHelp = new JMenu("Help");
         mnHelp.setForeground(Color.BLACK);
         mnHelp.setFont(new Font("Times New Roman", Font.PLAIN, 13));
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
         lblNewLabel.setForeground(Color.WHITE);
         lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 22));
         lblNewLabel.setIcon(new ImageIcon("/home/malik-dev/git/Aplicacao-Gestao_Professor/AplicacaoGestaoProfessor/src/img/find-icon.png"));
         lblNewLabel.setBounds(24, 0, 513, 29);
         panel_2.add(lblNewLabel);
         
         JPanel panel_3 = new JPanel();
         panel_3.setBackground(Color.BLUE);
         panel_3.setBounds(10, 104, 150, 407);
         frame.getContentPane().add(panel_3);
         panel_3.setLayout(null);
         
         JButton btnNewButton = new JButton("Professor");
         btnNewButton.setBackground(Color.WHITE);
         btnNewButton.setBounds(10, 38, 140, 27);
         panel_3.add(btnNewButton);
         btnNewButton.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {

        	        new Tela_cadastroProfessor();

        	    }
        	});
             
         JButton btnNewButton_1 = new JButton("Turmas");
         btnNewButton_1.setBackground(Color.WHITE);
         btnNewButton_1.setBounds(10, 86, 140, 27);
         panel_3.add(btnNewButton_1);
         
         
         JButton btnNewButton_2 = new JButton("Modulos");
         btnNewButton_2.setBackground(Color.WHITE);
         btnNewButton_2.setBounds(10, 132, 140, 27);
         panel_3.add(btnNewButton_2);
         
         JLabel lblNavegacao = new JLabel("Navegacao");
         lblNavegacao.setBackground(UIManager.getColor("Button.select"));
         lblNavegacao.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
         lblNavegacao.setForeground(Color.WHITE);
         lblNavegacao.setBounds(41, 0, 84, 27);
         panel_3.add(lblNavegacao);
         
         JButton btnNewButton_3 = new JButton("Qualificações");
         btnNewButton_3.setBackground(Color.WHITE);
         btnNewButton_3.setBounds(10, 173, 140, 27);
         panel_3.add(btnNewButton_3);
         btnNewButton_3.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {

                 

             }
         });
         
         JButton btnNewButton_4 = new JButton("Cursos");
         btnNewButton_4.setBackground(Color.WHITE);
         btnNewButton_4.setBounds(10, 219, 140, 27);
         panel_3.add(btnNewButton_4);
         
         JPanel panel_4 = new JPanel();
         panel_4.setBounds(170, 104, 711, 390);
         frame.getContentPane().add(panel_4);
         panel_4.setLayout(null);
         
         JScrollPane scrollPane = new JScrollPane();
         scrollPane.setBounds(55, 49, 646, 315);
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
         txtPesquisarProfessor.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
         txtPesquisarProfessor.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		
         	}
         });
         txtPesquisarProfessor.setBounds(397, 12, 150, 20);
         panel_4.add(txtPesquisarProfessor);
         txtPesquisarProfessor.setColumns(10);
         
         JButton btnNewButton_5 = new JButton("  Pesquisar professor");
         btnNewButton_5.setIcon(new ImageIcon("/home/malik-dev/git/Aplicacao-Gestao_Professor/AplicacaoGestaoProfessor/src/img/find-icon.png"));
         btnNewButton_5.setBounds(556, 11, 145, 23);
         panel_4.add(btnNewButton_5);
     
    
     mntmCursos.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

             
         }
     });
     }
    }