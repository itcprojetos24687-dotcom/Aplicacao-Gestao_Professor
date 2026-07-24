package VIEW;

import model.*;
import controller.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Tela_cadastroQualificacao extends JFrame implements OnDadosAlteradosListener {
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
    private int codigo = 0;
   
    // Paleta de Cores Moderna Clean e Minimalista (Fundo Branco)
    private final Color COLOR_PANEL        = new Color(255, 255, 255); 
    private final Color COLOR_TEXT         = new Color(29, 29, 31); 
    private final Color COLOR_TEXT_MUTED   = new Color(134, 134, 139); 
    private final Color COLOR_FIELD_BG     = new Color(255, 255, 255); 
    private final Color COLOR_FIELD_BORDER = new Color(210, 210, 215); 
    private final Color COLOR_ACCENT       = new Color(0, 122, 255); 
    private final Color COLOR_BUTTON_BG    = new Color(232, 232, 237); 
    
    private Tela_Principal tela_principal;
    private OnDadosAlteradosListener listener;
   
    public Tela_cadastroQualificacao() {
        // Uniformização global para renderizar combos e inputs no estilo clean
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.put("ComboBox.background", COLOR_FIELD_BG);
            UIManager.put("ComboBox.foreground", COLOR_TEXT);
            UIManager.put("ComboBox.selectionBackground", COLOR_ACCENT);
            UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        } catch (Exception e) {
            // Fallback silencioso
        }
    
        setLayout(new BorderLayout(0, 15));
        getContentPane().setBackground(COLOR_PANEL);
        ((JPanel)getContentPane()).setBorder(new EmptyBorder(20, 25, 20, 25));
        setLocationRelativeTo(null);
        setSize(800, 450);

        EventoCadastro.registrar(this);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                EventoCadastro.remover(Tela_cadastroQualificacao.this);
            }
        });
       
        // --- TOPO: Subtítulo Informativo ---
        JPanel panelAcoes = new JPanel(new BorderLayout());
        panelAcoes.setBackground(COLOR_PANEL);
        JPanel panelTituloInterno = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelTituloInterno.setBackground(COLOR_PANEL);
        JLabel lblInfo = new JLabel("Preencha os dados abaixo para registar uma nova qualificação no sistema.");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(COLOR_TEXT_MUTED);
        panelTituloInterno.add(lblInfo);
        panelAcoes.add(panelTituloInterno, BorderLayout.WEST);
       
        add(panelAcoes, BorderLayout.NORTH);
        
        // --- CORPO: Formulário Responsivo com GridBagLayout ---
        JPanel panelFormContainer = new JPanel(new GridBagLayout());
        panelFormContainer.setBackground(COLOR_PANEL);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.weightx = 0.5;

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);
        
        // Linha 0: Nome da Qualificação vs Coordenador (Labels)
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblNomeTitle = new JLabel("Nome da Qualificação *");
        lblNomeTitle.setFont(fontLabel);
        lblNomeTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblNomeTitle, gbc);

        gbc.gridx = 1;
        JLabel lblCoordenadorTitle = new JLabel("Coordenador");
        lblCoordenadorTitle.setFont(fontLabel);
        lblCoordenadorTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblCoordenadorTitle, gbc);
        
        // Linha 1: Nome da Qualificação vs Coordenador (Inputs)
        gbc.gridx = 0; gbc.gridy = 1;
        txtNomeQualificacao = new JTextField();
        styleTextField(txtNomeQualificacao, fontText);
        panelFormContainer.add(txtNomeQualificacao, gbc);
        
        gbc.gridx = 1;
        cbCoordenador = new JComboBox<>();
        try {
            coordenadores = cc.listarCoordenador();
            for(Coordenador c : coordenadores) {
                cbCoordenador.addItem(c);
            }
        } catch(Exception s) {
            s.printStackTrace();
        }
        styleComboBox(cbCoordenador, fontText);
        panelFormContainer.add(cbCoordenador, gbc);
        
        // Linha 2: Nível vs Campo Pertencente (Labels)
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblNivelTitle = new JLabel("Nível da Qualificação *");
        lblNivelTitle.setFont(fontLabel);
        lblNivelTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblNivelTitle, gbc);

        gbc.gridx = 1;
        JLabel lblCampoTitle = new JLabel("Campo Pertencente");
        lblCampoTitle.setFont(fontLabel);
        lblCampoTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblCampoTitle, gbc);
        
        // Linha 3: Nível vs Campo Pertencente (Inputs)
        gbc.gridx = 0; gbc.gridy = 3;
        cbNivelQualificacao = new JComboBox<>();
        try {
            niveis = nv.listarNivel();
            for(Nivel n: niveis) {
                cbNivelQualificacao.addItem(n);
            }
        } catch(Exception s) {
            s.printStackTrace();
        }
        styleComboBox(cbNivelQualificacao, fontText);
        panelFormContainer.add(cbNivelQualificacao, gbc);
        
        gbc.gridx = 1;
        cbCampoPertencente = new JComboBox<>();
        try {
            campos = cac.listarCampo();
            for(Campo c: campos) {
                cbCampoPertencente.addItem(c);
            }
        } catch(Exception s) {
            s.printStackTrace();
        }
        styleComboBox(cbCampoPertencente, fontText);
        panelFormContainer.add(cbCampoPertencente, gbc);
        
        add(panelFormContainer, BorderLayout.CENTER);
        
        // --- RODAPÉ: Botões de Ação ---
        JPanel panelBotoesRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        panelBotoesRodape.setBackground(COLOR_PANEL);
        panelBotoesRodape.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        btnCancelar = new JButton("Cancelar");
        styleButton(btnCancelar, COLOR_BUTTON_BG, COLOR_TEXT);
        btnCancelar.addActionListener(e -> {
            cancelar();
        });
        
        btnLimpar = new JButton("Limpar");
        styleButton(btnLimpar, COLOR_BUTTON_BG, COLOR_TEXT);
        btnLimpar.addActionListener(e -> limparCampos());
       
        btnSalvar = new JButton("Guardar Qualificação");
        styleButton(btnSalvar, COLOR_ACCENT, Color.WHITE);
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
    
    // --- MÉTODOS AUXILIARES DE ESTILIZAÇÃO (UI) ---
    private void styleTextField(JTextField field, Font font) {
        field.setFont(font);
        field.setBackground(COLOR_FIELD_BG);
        field.setForeground(COLOR_TEXT);
        field.setCaretColor(COLOR_TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_FIELD_BORDER, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 38));
    }

    private void styleComboBox(JComboBox<?> combo, Font font) {
        combo.setFont(font);
        combo.setBackground(COLOR_FIELD_BG);
        combo.setForeground(COLOR_TEXT);
        combo.setBorder(BorderFactory.createLineBorder(COLOR_FIELD_BORDER, 1));
        combo.setPreferredSize(new Dimension(combo.getPreferredSize().width, 38));
    }

    private void styleButton(JButton button, Color background, Color foreground) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void acaoSalvar() {
        String nome = txtNomeQualificacao.getText().trim();
        Coordenador coordenador = (Coordenador) cbCoordenador.getSelectedItem();
        Nivel nivel = (Nivel) cbNivelQualificacao.getSelectedItem();
        Campo campo = (Campo) cbCampoPertencente.getSelectedItem();
        boolean sucesso;
        try {
            QualificacaoController qc = new QualificacaoController();
            if(codigo == 0) {
                sucesso = qc.cadastrarQualificacao(nome, coordenador, campo, nivel);
                if(sucesso) {
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
                    Tela_Principal.getInstancia().listarQualificacoes();
                } else {
                    JOptionPane.showMessageDialog(null,"Falha no cadastro");
                }
            } else {
                sucesso = qc.atualizarQualificacao(codigo, nome);
                if(sucesso) {
                    JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
                    Tela_Principal.getInstancia().listarQualificacoes();
                } else {
                    JOptionPane.showMessageDialog(null,"Falha ao atualizar");
                }
            }
        } catch(Exception e) {
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
    
    public void buscarQualificacao(int cod, String titulo, String coordenador, String nivel, String campo) {
        codigo = cod;
        txtNomeQualificacao.setText(titulo);
        
        for(int i = 0 ; i < cbCoordenador.getItemCount(); i++) {
            if(String.valueOf(cbCoordenador.getItemAt(i)).equals(coordenador)) {
                cbCoordenador.setSelectedIndex(i);
                break;
            }
        }
        for(int i = 0 ; i < cbNivelQualificacao.getItemCount(); i++) {
            if(String.valueOf(cbNivelQualificacao.getItemAt(i)).equals(nivel)) {
                cbNivelQualificacao.setSelectedIndex(i);
                break;
            }
        }
        for(int i = 0 ; i < cbCampoPertencente.getItemCount(); i++) {
            if(String.valueOf(cbCampoPertencente.getItemAt(i)).equals(campo)) {
                cbCampoPertencente.setSelectedIndex(i);
                break;
            }
        }
    }
    
    @Override
    public void ListarDadosAoAlterar() {
        try {
            niveis = nv.listarNivel();
            cbNivelQualificacao.removeAllItems();
            for (Nivel n : niveis) cbNivelQualificacao.addItem(n);

            campos = cac.listarCampo();
            cbCampoPertencente.removeAllItems();
            for (Campo c : campos) cbCampoPertencente.addItem(c);

            coordenadores = cc.listarCoordenador();
            cbCoordenador.removeAllItems();
            for (Coordenador c : coordenadores) cbCoordenador.addItem(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void limparCampos() {
        txtNomeQualificacao.setText("");
        if(cbCoordenador.getItemCount() > 0) cbCoordenador.setSelectedIndex(0);
        if(cbNivelQualificacao.getItemCount() > 0) cbNivelQualificacao.setSelectedIndex(0);
        if(cbCampoPertencente.getItemCount() > 0) cbCampoPertencente.setSelectedIndex(0);
    }
}