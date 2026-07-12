package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;

public class Tela_Incrição extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTextField txtNomeFormando, txtDataInscricao;
    private JComboBox<String> comboModulo, comboSemestre;
    private JButton btnGravar, btnLimpar;

    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color BRANCO          = Color.WHITE;

    public Tela_Incrição() {
   
        setLayout(new BorderLayout(0, 15));
        setBackground(BRANCO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 13);

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));
        panelForm.setBackground(BRANCO);

        panelForm.add(criarLinhaFormulario("Nome do Formando:", txtNomeFormando = new JTextField(), fontLabel, fontText));
        
        comboModulo = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "Programação Orientada a Objetos", "Bases de Dados", "Desenvolvimento Web", "Redes de Computadores"
        }));
        comboModulo.setBackground(BRANCO);
        panelForm.add(criarLinhaFormulario("Módulo:", comboModulo, fontLabel, fontText));

        txtDataInscricao = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        panelForm.add(criarLinhaFormulario("Data de Inscrição:", txtDataInscricao, fontLabel, fontText));

        comboSemestre = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {
            "Primeiro semestre", "Segundo semestre"
        }));
        comboSemestre.setBackground(BRANCO);
        panelForm.add(criarLinhaFormulario("Semestre:", comboSemestre, fontLabel, fontText));

        JPanel panelBotoesContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panelBotoesContainer.setBackground(BRANCO);
        panelBotoesContainer.setBorder(new EmptyBorder(0, 0, 0, 0));

        btnLimpar = new JButton("Limpar Campos");
        btnLimpar.setBackground(BRANCO);
        btnLimpar.setForeground(AZUL_ESCURO_NAV);
        btnLimpar.setFont(fontLabel);
        btnLimpar.setPreferredSize(new Dimension(225, 38));
        btnLimpar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        btnLimpar.addActionListener(e -> limparCampos());

        btnGravar = new JButton("Gravar Inscrição");
        btnGravar.setBackground(AZUL_DESTAQUE);
        btnGravar.setForeground(BRANCO);
        btnGravar.setFont(fontLabel);
        btnGravar.setPreferredSize(new Dimension(225, 38));
        btnGravar.setBorder(null);
        btnGravar.addActionListener(e -> acaoSalvar());

        panelBotoesContainer.add(btnLimpar);
        panelBotoesContainer.add(btnGravar);
        
        panelForm.add(panelBotoesContainer);
        add(panelForm, BorderLayout.NORTH);
    }

    private JPanel criarLinhaFormulario(String textoLabel, javax.swing.JComponent componente, Font fontLabel, Font fontComponente) {
        JPanel linha = new JPanel(new BorderLayout(10, 0));
        linha.setBackground(BRANCO);
        linha.setBorder(new EmptyBorder(4, 0, 4, 0));

        JLabel label = new JLabel(textoLabel);
        label.setFont(fontLabel);
        label.setForeground(AZUL_ESCURO_NAV);
        label.setPreferredSize(new Dimension(150, 35));

        componente.setFont(fontComponente);
        componente.setPreferredSize(new Dimension(componente.getPreferredSize().width, 35));

        linha.add(label, BorderLayout.WEST);
        linha.add(componente, BorderLayout.CENTER);

        return linha;
    }

    private void acaoSalvar() {
        String nome = txtNomeFormando.getText().trim();
        String modulo = comboModulo.getSelectedItem().toString();
        String data = txtDataInscricao.getText().trim();
        String semestre = comboSemestre.getSelectedItem().toString();

        if (nome.isEmpty() || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Inscrição efetuada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        limparCampos();
    }

    private void limparCampos() {
        txtNomeFormando.setText("");
        comboModulo.setSelectedIndex(0);
        comboSemestre.setSelectedIndex(0);
        txtDataInscricao.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    }
}