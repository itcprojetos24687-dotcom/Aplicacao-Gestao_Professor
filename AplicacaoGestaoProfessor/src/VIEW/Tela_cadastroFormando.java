package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Tela_cadastroFormando extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtNome;
    private JTextField txtApelido;
    private JTextField txtContacto;
    private JTextField txtEmail;
    private JTextField txtBi;
    private JButton btnSalvar, btnLimpar;

    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color FUNDO_CLARO     = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

    public Tela_cadastroFormando() {
        setLayout(new BorderLayout(0, 15));
        setBackground(BRANCO);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel panelAcoes = new JPanel(new BorderLayout());
        panelAcoes.setBackground(BRANCO);

        JPanel panelTituloInterno = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelTituloInterno.setBackground(BRANCO);
        JLabel lblInfo = new JLabel("Preencha os dados abaixo para registar um novo formando no sistema.");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(TEXTO_MUTED);
        panelTituloInterno.add(lblInfo);
        panelAcoes.add(panelTituloInterno, BorderLayout.WEST);
        
        add(panelAcoes, BorderLayout.NORTH);

        JPanel panelFormContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelFormContainer.setBackground(BRANCO);

        JPanel panelGridCampos = new JPanel(new GridLayout(5, 2, 20, 20));
        panelGridCampos.setBackground(BRANCO);
        panelGridCampos.setPreferredSize(new Dimension(750, 270));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);

        // 1. Nome
        panelGridCampos.add(new JLabel("Nome *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtNome = new JTextField();
        txtNome.setFont(fontText);
        panelGridCampos.add(txtNome);

        // 2. Apelido
        panelGridCampos.add(new JLabel("Apelido *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtApelido = new JTextField();
        txtApelido.setFont(fontText);
        panelGridCampos.add(txtApelido);

        // 3. Contacto
        panelGridCampos.add(new JLabel("Contacto") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtContacto = new JTextField();
        txtContacto.setFont(fontText);
        panelGridCampos.add(txtContacto);

        // 4. Email
        panelGridCampos.add(new JLabel("Email") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtEmail = new JTextField();
        txtEmail.setFont(fontText);
        panelGridCampos.add(txtEmail);

        // 5. BI
        panelGridCampos.add(new JLabel("BI *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtBi = new JTextField();
        txtBi.setFont(fontText);
        panelGridCampos.add(txtBi);

        panelFormContainer.add(panelGridCampos);
        add(panelFormContainer, BorderLayout.CENTER);

        JPanel panelBotoesRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        panelBotoesRodape.setBackground(BRANCO);
        panelBotoesRodape.setBorder(new EmptyBorder(10, 0, 0, 0));

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(BRANCO);
        btnLimpar.setFont(fontLabel);
        btnLimpar.setPreferredSize(new Dimension(150, 40));
        btnLimpar.setBorder(new LineBorder(new Color(220, 224, 230)));
        btnLimpar.addActionListener(e -> limparCampos());

        btnSalvar = new JButton("Guardar Formando");
        btnSalvar.setBackground(AZUL_DESTAQUE);
        btnSalvar.setForeground(BRANCO);
        btnSalvar.setFont(fontLabel);
        btnSalvar.setPreferredSize(new Dimension(165, 40));
        btnSalvar.setBorder(null);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acaoSalvar();
            }
        });

        panelBotoesRodape.add(btnLimpar);
        panelBotoesRodape.add(btnSalvar);
        
        add(panelBotoesRodape, BorderLayout.SOUTH);
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

        if (!contacto.isEmpty()) {
            try {
                Integer.parseInt(contacto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "O campo Contacto deve conter apenas números.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        String resumo = "Nome: " + nome + " " + apelido + "\nBI: " + bi;
        JOptionPane.showMessageDialog(this, "Formando guardado com sucesso!\n\n" + resumo, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
        limparCampos();
    }

    private void limparCampos() {
        txtNome.setText("");
        txtApelido.setText("");
        txtContacto.setText("");
        txtEmail.setText("");
        txtBi.setText("");
    }
}