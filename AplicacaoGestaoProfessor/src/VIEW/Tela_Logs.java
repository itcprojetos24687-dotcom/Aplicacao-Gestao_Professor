package VIEW;

import java.awt.BorderLayout;
import dao.*;
import model.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import controller.LogController;

public class Tela_Logs extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTable tabelaLogs;
    private DefaultTableModel modeloLogs;
    private JTextField txtPesquisar;
    private JComboBox<String> comboNivel;

    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color FUNDO_CLARO      = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

//    public interface OnLogsCadastradaListener {
//    	public void onLogsCadastrada();
//    	public void oncancelar();
//    }
    //private OnLogsCadastradaListener listener;
    
    public Tela_Logs() {
    	
    	setLayout(new BorderLayout(0, 20));
    	setBackground(new Color(0, 0, 128));
    	setBorder(new EmptyBorder(25, 30, 25, 30));
    	
    	inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(0, 0, 128));

        JPanel agrupadorTexto = new JPanel(new GridLayout(2, 1, 0, 5));
        agrupadorTexto.setBackground(new Color(0, 0, 128));

        JLabel lblTitulo = new JLabel("Logs de Auditoria do Sistema");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(255, 255, 255));

        JLabel lblSubtitulo = new JLabel("Histórico detalhado de ações, acessos e modificações de dados");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(new Color(255, 255, 255));

        agrupadorTexto.add(lblTitulo);
        agrupadorTexto.add(lblSubtitulo);
        panelHeader.add(agrupadorTexto, BorderLayout.WEST);
        add(panelHeader, BorderLayout.NORTH);

        JPanel panelCardPrincipal = new JPanel(new BorderLayout(0, 15));
        panelCardPrincipal.setBackground(BRANCO);
        panelCardPrincipal.setBorder(new LineBorder(new Color(230, 233, 237), 1, true));
        panelCardPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel panelFiltrosAcoes = new JPanel(new BorderLayout());
        panelFiltrosAcoes.setBackground(BRANCO);

        JPanel panelFiltrosEsquerda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelFiltrosEsquerda.setBackground(BRANCO);

        txtPesquisar = new JTextField();
        txtPesquisar.setPreferredSize(new Dimension(250, 35));
        txtPesquisar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelFiltrosEsquerda.add(txtPesquisar);

        /*comboNivel = new JComboBox<>(new String[]{"Todos os Perfis", "Administrador", "Secretaria", "operador"});
        comboNivel.setPreferredSize(new Dimension(160, 35));
        comboNivel.setBackground(BRANCO);
        comboNivel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelFiltrosEsquerda.add(comboNivel);*/

        JButton btnFiltrar = new JButton("Pesquisar");
        btnFiltrar.setPreferredSize(new Dimension(90, 35));
        btnFiltrar.setBackground(BRANCO);
        btnFiltrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnFiltrar.setForeground(AZUL_ESCURO_NAV);
        btnFiltrar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        btnFiltrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFiltrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = txtPesquisar.getText();
				try {
					LogController lc = new LogController();
					ArrayList<Logs> logs = lc.listarlogs();
					
					DefaultTableModel modelo = (DefaultTableModel) tabelaLogs.getModel();
					modelo.setRowCount(0);
					for(Logs l: logs) {
						modelo.addRow(new Object[] {
								l.getUsuario().getUsername(),
								l.getUsuario().getPerfil().getNome(),
								l.getAcao(),
								l.getDescricao(),
								l.getData()
						});
						
					}
					
				}catch(Exception s) {
					s.printStackTrace();
				}
				
			}
        	
        });
        panelFiltrosEsquerda.add(btnFiltrar);

        panelFiltrosAcoes.add(panelFiltrosEsquerda, BorderLayout.WEST);

        JPanel panelAcoesDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelAcoesDireita.setBackground(BRANCO);

//        JButton btnLimparLogs = new JButton("Limpar Filtros");
//        btnLimparLogs.setPreferredSize(new Dimension(130, 35));
//        btnLimparLogs.setBackground(BRANCO);
//        btnLimparLogs.setFont(new Font("Segoe UI", Font.BOLD, 13));
//        btnLimparLogs.setForeground(TEXTO_MUTED);
//        btnLimparLogs.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
//        btnLimparLogs.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        btnLimparLogs.addActionListener(e -> {
//            txtPesquisar.setText("");
//            comboNivel.setSelectedIndex(0);
//        });
//       panelAcoesDireita.add(btnLimparLogs);

        panelFiltrosAcoes.add(panelAcoesDireita, BorderLayout.EAST);
        panelCardPrincipal.add(panelFiltrosAcoes, BorderLayout.NORTH);

        modeloLogs = new DefaultTableModel(
            new Object[][] {
                
            },
            new String[] {"Nome do Usuário", "Perfil", "Ação", "Descrição da Atividade","Data"}
        ) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaLogs = new JTable(modeloLogs);
        tabelaLogs.setRowHeight(38);
        tabelaLogs.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabelaLogs.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabelaLogs.getTableHeader().setBackground(new Color(248, 249, 250));
        tabelaLogs.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tabelaLogs.setShowGrid(false);
        tabelaLogs.setIntercellSpacing(new Dimension(0, 0));

        tabelaLogs.getColumnModel().getColumn(0).setPreferredWidth(160);
        tabelaLogs.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabelaLogs.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabelaLogs.getColumnModel().getColumn(3).setPreferredWidth(550);

        JScrollPane scrollPane = new JScrollPane(tabelaLogs);
        scrollPane.setBorder(new LineBorder(new Color(230, 233, 237), 1));
        panelCardPrincipal.add(scrollPane, BorderLayout.CENTER);

        add(panelCardPrincipal, BorderLayout.CENTER);
    }

    public static void exibirEmPopup(JFrame framePai) {
        JDialog popup = new JDialog(framePai, "SGP - Logs de Auditoria", true);
        popup.setSize(1000, 600);
        popup.setLocationRelativeTo(framePai);
        popup.getContentPane().add( new Tela_Logs());
        popup.setVisible(true);
    }

//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(() -> {
//            JFrame frameTeste = new JFrame();
//            frameTeste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frameTeste.setSize(1100, 650);
//            frameTeste.setLocationRelativeTo(null);
//            frameTeste.getContentPane().add(new Tela_Logs());
//            frameTeste.setVisible(true);
//        });
//    }
}