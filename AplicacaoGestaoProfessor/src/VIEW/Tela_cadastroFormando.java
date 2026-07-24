package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import controller.FormandoController;
import model.Seccao;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Tela_cadastroFormando extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtNome;
    private JTextField txtApelido;
    private JTextField txtContacto;
    private JTextField txtEmail;
    private JTextField txtBi;
    private JButton btnSalvar, btnLimpar;
    private int codigoEdicao = 0;
    private boolean modoEdicao = false;

    // Paleta de Cores Moderna Clean e Minimalista
    private final Color COLOR_PANEL        = new Color(255, 255, 255); 
    private final Color COLOR_TEXT         = new Color(29, 29, 31); 
    private final Color COLOR_TEXT_MUTED   = new Color(134, 134, 139); 
    private final Color COLOR_FIELD_BG     = new Color(255, 255, 255); 
    private final Color COLOR_FIELD_BORDER = new Color(210, 210, 215); 
    private final Color COLOR_ACCENT       = new Color(0, 122, 255); 
    private final Color COLOR_BUTTON_BG    = new Color(232, 232, 237); 

    private Tela_Principal tela_principal;
    private OnDadosAlteradosListener listener;
    
    public Tela_cadastroFormando() {
        // Tenta uniformizar comportamentos nativos do look and feel básico
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            // Fallback silencioso
        }

        setLayout(new BorderLayout(0, 15));
        setBackground(COLOR_PANEL);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        // --- TOPO: Subtítulo Informativo ---
        JPanel panelAcoes = new JPanel(new BorderLayout());
        panelAcoes.setBackground(COLOR_PANEL);

        JPanel panelTituloInterno = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelTituloInterno.setBackground(COLOR_PANEL);
        JLabel lblInfo = new JLabel("Preencha os dados abaixo para registar um novo formando no sistema.");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(COLOR_TEXT_MUTED);
        panelTituloInterno.add(lblInfo);
        panelAcoes.add(panelTituloInterno, BorderLayout.WEST);
        
        add(panelAcoes, BorderLayout.NORTH);

        // --- CORPO: Formulário com GridBagLayout (Fluido e Responsivo) ---
        JPanel panelFormContainer = new JPanel(new GridBagLayout());
        panelFormContainer.setBackground(COLOR_PANEL);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.weightx = 0.5;

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);

        // Linha 0: Nome vs Apelido (Labels)
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblNomeTitle = new JLabel("Nome *");
        lblNomeTitle.setFont(fontLabel);
        lblNomeTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblNomeTitle, gbc);

        gbc.gridx = 1;
        JLabel lblApelidoTitle = new JLabel("Apelido *");
        lblApelidoTitle.setFont(fontLabel);
        lblApelidoTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblApelidoTitle, gbc);

        // Linha 1: Nome vs Apelido (Inputs)
        gbc.gridx = 0; gbc.gridy = 1;
        txtNome = new JTextField();
        styleTextField(txtNome, fontText);
        panelFormContainer.add(txtNome, gbc);

        gbc.gridx = 1;
        txtApelido = new JTextField();
        styleTextField(txtApelido, fontText);
        panelFormContainer.add(txtApelido, gbc);

        // Linha 2: Contacto vs Email (Labels)
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblContactoTitle = new JLabel("Contacto");
        lblContactoTitle.setFont(fontLabel);
        lblContactoTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblContactoTitle, gbc);

        gbc.gridx = 1;
        JLabel lblEmailTitle = new JLabel("Email");
        lblEmailTitle.setFont(fontLabel);
        lblEmailTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblEmailTitle, gbc);

        // Linha 3: Contacto vs Email (Inputs)
        gbc.gridx = 0; gbc.gridy = 3;
        txtContacto = new JTextField();
        styleTextField(txtContacto, fontText);
        panelFormContainer.add(txtContacto, gbc);

        gbc.gridx = 1;
        txtEmail = new JTextField();
        styleTextField(txtEmail, fontText);
        panelFormContainer.add(txtEmail, gbc);

        // Linha 4: BI (Label ocupando a linha toda ou meia tela)
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel lblBiTitle = new JLabel("BI *");
        lblBiTitle.setFont(fontLabel);
        lblBiTitle.setForeground(COLOR_TEXT_MUTED);
        panelFormContainer.add(lblBiTitle, gbc);

        // Linha 5: BI (Input)
        gbc.gridx = 0; gbc.gridy = 5;
        txtBi = new JTextField();
        styleTextField(txtBi, fontText);
        panelFormContainer.add(txtBi, gbc);

        add(panelFormContainer, BorderLayout.CENTER);

        // --- RODAPÉ: Botões de Ação ---
        JPanel panelBotoesRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        panelBotoesRodape.setBackground(COLOR_PANEL);
        panelBotoesRodape.setBorder(new EmptyBorder(10, 0, 0, 0));

        btnLimpar = new JButton("Limpar");
        styleButton(btnLimpar, COLOR_BUTTON_BG, COLOR_TEXT);
        btnLimpar.addActionListener(e -> limparCampos());

        btnSalvar = new JButton("Guardar Formando");
        styleButton(btnSalvar, COLOR_ACCENT, Color.WHITE);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acaoSalvar();
            }
        });

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

    private void styleButton(JButton button, Color background, Color foreground) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void acaoSalvar() {
        String nome = txtNome.getText().trim();
        String apelido = txtApelido.getText().trim();
        String contacto = txtContacto.getText().trim();
        String email = txtEmail.getText().trim();
        String bi = txtBi.getText().trim();

        if (nome.isEmpty() || apelido.isEmpty() || bi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha os campos obrigatórios (*).", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            FormandoController fc = new FormandoController();
            boolean sucesso;
            if(modoEdicao) {
                sucesso = fc.atualizarFormando(
                    nome, apelido,
                    contacto.isEmpty() ? 0 : Integer.parseInt(contacto),
                    email, bi, codigoEdicao
                );
                if(sucesso) {
                    JOptionPane.showMessageDialog(this, "Formando actualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    new Tela_Principal(Seccao.obterUtilizador()).listarFormando();
                    modoEdicao = false;
                    codigoEdicao = 0;
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Dados inválidos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                sucesso = fc.cadastrarFormando(
                    nome, apelido,
                    contacto.isEmpty() ? 0 : Integer.parseInt(contacto),
                    email, bi
                );
                if(sucesso) {
                    JOptionPane.showMessageDialog(this, "Formando guardado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    new Tela_Principal(Seccao.obterUtilizador()).listarFormando();
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Dados inválidos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarFormando(int codigo, String nome, String apelido, int contacto, String email, String bi) {
        this.codigoEdicao = codigo;
        this.modoEdicao = true;
        txtNome.setText(nome);
        txtApelido.setText(apelido);
        txtContacto.setText(String.valueOf(contacto));
        txtEmail.setText(email);
        txtBi.setText(bi);
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtApelido.setText("");
        txtContacto.setText("");
        txtEmail.setText("");
        txtBi.setText("");
    }
}