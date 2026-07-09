package VIEW;
import  model.*;
import controller.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Tela_cadastroQualificação extends JPanel {

    private static final long serialVersionUID = 1L;
    
    // Componentes de Entrada de Dados
    private JTextField txtNomeQualificacao;
    private JComboBox<Coordenador> cbCoordenador;
    private JComboBox<Nivel> cbNivelQualificacao;
    private JComboBox<Campo> cbCampoPertencente;
    private JButton btnSalvar, btnLimpar, btnCancelar;
    private CoordenadorController cc = new CoordenadorController();
    private NivelController nv = new NivelController();
    private CampoController cac = new CampoController();
    private ArrayList<Coordenador> coordenadores;
    private ArrayList<Nivel> niveis;
    private ArrayList<Campo> campos;
    
  
    // Paleta de Cores unificada AcademiaPro
    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color FUNDO_CLARO     = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

    
    public Tela_cadastroQualificação() {
    	
        // Alinhado ao plano de design do painel central
        setLayout(new BorderLayout(0, 15));
        setBackground(BRANCO);
        setBorder(new EmptyBorder(20, 20, 20, 20));

 
        JPanel panelAcoes = new JPanel(new BorderLayout());
        panelAcoes.setBackground(BRANCO);

        JPanel panelTituloInterno = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelTituloInterno.setBackground(BRANCO);
        JLabel lblInfo = new JLabel("Preencha os dados abaixo para registar uma nova qualificação no sistema.");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(TEXTO_MUTED);
        panelTituloInterno.add(lblInfo);
        panelAcoes.add(panelTituloInterno, BorderLayout.WEST);
        
        add(panelAcoes, BorderLayout.NORTH);

        // --- Formulário Centralizado Customizado ---
        JPanel panelFormContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelFormContainer.setBackground(BRANCO);

        // Grid Layout para organizar os 4 campos (4 linhas, 2 colunas)
        JPanel panelGridCampos = new JPanel(new GridLayout(4, 2, 20, 20));
        panelGridCampos.setBackground(BRANCO);
        panelGridCampos.setPreferredSize(new Dimension(750, 230));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);

        // 1. Nome da Qualificação
        panelGridCampos.add(new JLabel("Nome da Qualificação *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtNomeQualificacao = new JTextField();
        txtNomeQualificacao.setFont(fontText);
        panelGridCampos.add(txtNomeQualificacao);

        // 2. Coordenador da Qualificação (ComboBox)
        panelGridCampos.add(new JLabel("Coordenador") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        try {
        	
        	coordenadores = cc.listarCoordenador();
        	cbCoordenador = new JComboBox<>();
        	for(Coordenador c : coordenadores) {
        		cbCoordenador.addItem(c);
        	}
        }catch(Exception s) {
        	
        	s.printStackTrace();
        }
        cbCoordenador.setFont(fontText);
        panelGridCampos.add(cbCoordenador);

        // 3. Nível da Qualificação (ComboBox)
        panelGridCampos.add(new JLabel("Nível da Qualificação *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        try {
        	niveis = nv.listarNivel();
        	cbNivelQualificacao = new JComboBox<>();
        	for(Nivel n: niveis) {
        		cbNivelQualificacao.addItem(n);
        		
        	}
        	
        	
        }catch(Exception s) {
        	s.printStackTrace();
        }
        cbNivelQualificacao.setFont(fontText);
        panelGridCampos.add(cbNivelQualificacao);

        // 4. Campo Pertencente (ComboBox)
        panelGridCampos.add(new JLabel("Campo Pertencente") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        try {
        	campos = cac.listarCampo();
        	cbCampoPertencente = new JComboBox<>();
        	for(Campo c: campos) {
        		cbCampoPertencente.addItem(c);
        	}
        }catch(Exception s) {
        	s.printStackTrace();
        }
        cbCampoPertencente.setFont(fontText);
        panelGridCampos.add(cbCampoPertencente);

        panelFormContainer.add(panelGridCampos);
        add(panelFormContainer, BorderLayout.CENTER);

        // --- Barra de Comando Inferior (Botões) ---
        JPanel panelBotoesRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        panelBotoesRodape.setBackground(BRANCO);
        panelBotoesRodape.setBorder(new EmptyBorder(10, 0, 0, 0));

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(BRANCO);
        btnLimpar.setFont(fontLabel);
        btnLimpar.setPreferredSize(new Dimension(150, 40));
        btnLimpar.setBorder(new LineBorder(new Color(220, 224, 230)));
        btnLimpar.addActionListener(e -> limparCampos());

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(BRANCO);
        btnCancelar.setFont(fontLabel);
        btnCancelar.setPreferredSize(new Dimension(150, 40));
        btnCancelar.setBorder(new LineBorder(new Color(220, 224, 230)));
        btnCancelar.addActionListener(e -> {
        	cancelar();
        	
        });
        
        btnSalvar = new JButton("Guardar Qualificação");
        btnSalvar.setBackground(AZUL_DESTAQUE);
        btnSalvar.setForeground(BRANCO);
        btnSalvar.setFont(fontLabel);
        btnSalvar.setPreferredSize(new Dimension(185, 40));
        btnSalvar.setBorder(null);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acaoSalvar();
            }
        });
        panelBotoesRodape.add(btnCancelar);
        panelBotoesRodape.add(btnLimpar);
        panelBotoesRodape.add(btnSalvar);
        
        
        add(panelBotoesRodape, BorderLayout.SOUTH);
    }

    /**
     * Validação básica e persistência fictícia
     */
    private void acaoSalvar() {
        String nome = txtNomeQualificacao.getText().trim();
        Coordenador coordenador = (Coordenador) cbCoordenador.getSelectedItem();
        Nivel nivel = (Nivel) cbNivelQualificacao.getSelectedItem();
        Campo campo = (Campo) cbCampoPertencente.getSelectedItem();
        boolean sucesso;
        try{

			QualificacaoController qc = new QualificacaoController();
			sucesso = qc.cadastrarQualificacao(nome, coordenador, campo, nivel);
        	if(sucesso) {
        		JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        	}
        	else {
        		JOptionPane.showMessageDialog(null,"Falha no cadastro");
        	}
        }catch(Exception e) {
        	JOptionPane.showMessageDialog(null,"Erro ao salvar");
        	e.printStackTrace();
        }
        limparCampos();
    }
    private void cancelar() {
    	setVisible(false);
    	Tela_Principal tl = new Tela_Principal(Seccao.obterUtilizador());
    	tl.criarPainelQualificacoes();
    	
    }
    private void limparCampos() {
        txtNomeQualificacao.setText("");
        cbCoordenador.setSelectedIndex(0);
        cbNivelQualificacao.setSelectedIndex(0);
        cbCampoPertencente.setSelectedIndex(0);
    }
}