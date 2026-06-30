package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
        contentPane.setBackground(new Color(0, 0, 128));
        contentPane.setBorder(new EmptyBorder(30, 40, 30, 40));
        contentPane.setLayout(new BorderLayout(0, 25));
        setContentPane(contentPane);


        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(0, 0, 128));

        JLabel lblTitulo = new JLabel("Gestão de Utilizadores");
        lblTitulo.setBackground(new Color(0, 0, 128));
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
}