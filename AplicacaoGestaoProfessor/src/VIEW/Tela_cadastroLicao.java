package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.FormadorController;
import controller.ModuloController;
import controller.SalaController;
import controller.TurmaController;
import model.Formador;
import model.Modulo;
import model.Sala;
import model.Turma;

public class Tela_cadastroLicao extends JFrame {

    private static final long serialVersionUID = 1L;

    private JComboBox<Modulo> cbModulo;
    private JComboBox<Formador> cbFormador;
    private JComboBox<Sala> cbSala;
    private JComboBox<Turma> cbTurma;
    private JTextField txtData;
    private JTextField txtHoraInicio;
    private JTextField txtHoraFim;
    private JButton btnSalvar, btnLimpar;

    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE   = new Color(13, 110, 253);
    private final Color BRANCO          = Color.WHITE;
    private final Color TEXTO_MUTED     = new Color(108, 117, 125);

   // public static void main(String[] args) {
        //Tela_cadastroLicao tl = new Tela_cadastroLicao();
        //tl.setVisible(true);
   // }

    public Tela_cadastroLicao() {
        setTitle("Cadastro de Lição");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(950, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BRANCO);
        setLayout(new BorderLayout(0, 15));
        getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20));

        // --- Título ---
        JPanel panelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelTopo.setBackground(BRANCO);
        JLabel lblInfo = new JLabel("Preencha os dados abaixo para registar uma nova lição no sistema.");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(TEXTO_MUTED);
        panelTopo.add(lblInfo);
        add(panelTopo, BorderLayout.NORTH);

        // --- Formulário ---
        JPanel panelFormContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelFormContainer.setBackground(BRANCO);

        JPanel panelGridCampos = new JPanel(new GridLayout(7, 2, 20, 15));
        panelGridCampos.setBackground(BRANCO);
        panelGridCampos.setPreferredSize(new Dimension(800, 380));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText  = new Font("Segoe UI", Font.PLAIN, 14);

        // 1. Módulo
        panelGridCampos.add(new JLabel("Módulo *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        cbModulo = new JComboBox<>();
        cbModulo.setFont(fontText);
        try {
            ModuloController mc = new ModuloController();
            ArrayList<Modulo> modulos = mc.listarModulo("");
            for(Modulo m : modulos) cbModulo.addItem(m);
        } catch(Exception e) { e.printStackTrace(); }
        panelGridCampos.add(cbModulo);

        // 2. Formador
        panelGridCampos.add(new JLabel("Formador *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        cbFormador = new JComboBox<>();
        cbFormador.setFont(fontText);
        try {
            FormadorController fc = new FormadorController();
            ArrayList<Formador> formadores = fc.listarFormador("");
            for(Formador f : formadores) cbFormador.addItem(f);
        } catch(Exception e) { e.printStackTrace(); }
        panelGridCampos.add(cbFormador);

        // 3. Sala
        panelGridCampos.add(new JLabel("Sala *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        cbSala = new JComboBox<>();
        cbSala.setFont(fontText);
        try {
            SalaController sc = new SalaController();
            ArrayList<Sala> salas = sc.listarSala("");
            for(Sala s : salas) cbSala.addItem(s);
        } catch(Exception e) { e.printStackTrace(); }
        panelGridCampos.add(cbSala);

        // 4. Turma
        panelGridCampos.add(new JLabel("Turma *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        cbTurma = new JComboBox<>();
        cbTurma.setFont(fontText);
        try {
            TurmaController tc = new TurmaController();
            ArrayList<Turma> turmas = tc.listarTurma("");
            for(Turma t : turmas) cbTurma.addItem(t);
        } catch(Exception e) { e.printStackTrace(); }
        panelGridCampos.add(cbTurma);

        // 5. Data
        panelGridCampos.add(new JLabel("Data *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtData = new JTextField();
        txtData.setFont(fontText);
        txtData.setToolTipText("dd/mm/aaaa");
        panelGridCampos.add(txtData);

        // 6. Hora de Início
        panelGridCampos.add(new JLabel("Hora de Início *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtHoraInicio = new JTextField();
        txtHoraInicio.setFont(fontText);
        txtHoraInicio.setToolTipText("hh:mm");
        panelGridCampos.add(txtHoraInicio);

        // 7. Hora de Fim
        panelGridCampos.add(new JLabel("Hora de Fim *") {{ setFont(fontLabel); setForeground(AZUL_ESCURO_NAV); }});
        txtHoraFim = new JTextField();
        txtHoraFim.setFont(fontText);
        txtHoraFim.setToolTipText("hh:mm");
        panelGridCampos.add(txtHoraFim);

        panelFormContainer.add(panelGridCampos);
        add(panelFormContainer, BorderLayout.CENTER);

        // --- Botões ---
        JPanel panelBotoesRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        panelBotoesRodape.setBackground(BRANCO);
        panelBotoesRodape.setBorder(new EmptyBorder(10, 0, 0, 0));

        btnLimpar = new JButton("Limpar Campos");
        btnLimpar.setBackground(BRANCO);
        btnLimpar.setFont(fontLabel);
        btnLimpar.setPreferredSize(new Dimension(150, 40));
        btnLimpar.setBorder(new LineBorder(new Color(220, 224, 230)));
        btnLimpar.addActionListener(e -> limparCampos());

        btnSalvar = new JButton("Guardar Lição");
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
        String data      = txtData.getText().trim();
        String horaInicio = txtHoraInicio.getText().trim();
        String horaFim    = txtHoraFim.getText().trim();

        if(cbModulo.getSelectedItem() == null || cbFormador.getSelectedItem() == null ||
           cbSala.getSelectedItem() == null   || cbTurma.getSelectedItem() == null ||
           data.isEmpty() || horaInicio.isEmpty() || horaFim.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios (*).", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Lição guardada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        limparCampos();
    }

    private void limparCampos() {
        cbModulo.setSelectedIndex(0);
        cbFormador.setSelectedIndex(0);
        cbSala.setSelectedIndex(0);
        cbTurma.setSelectedIndex(0);
        txtData.setText("");
        txtHoraInicio.setText("");
        txtHoraFim.setText("");
    }
}