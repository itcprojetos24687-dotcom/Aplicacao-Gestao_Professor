package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;

// Comentado para evitar erros de compilação sem a classe no projeto
// import controller.FormadorController;

public class Tela_cadastroProfessor extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // Componentes de Entrada de Dados
    private JTextField txtNome, txtApelido, txtEmail, txtContacto;
    private JComboBox<String> comboGenero, comboEstadoCivil;
    private JSpinner spinnerHoras, spinnerSalarioHora;
    private JButton btnSalvar, btnCancelar;
    private String perfilUsuario;

    // Paleta AcademiaPro
    private final Color AZUL_ESCURO_NAV = new Color(15, 38, 70);
    private final Color AZUL_DESTAQUE  = new Color(13, 110, 253);
    private final Color FUNDO_CLARO     = new Color(244, 246, 249);
    private final Color BRANCO          = Color.WHITE;

    // --- MÉTODO MAIN ADICIONADO PARA EXECUÇÃO INDEPENDENTE ---
    public static void main(String[] args) {
        // Executa a GUI na thread de eventos do Swing (boa prática)
        SwingUtilities.invokeLater(() -> {
            try {
                // Instancia e exibe a tela como "Administrador" para testar os campos abertos
                Tela_cadastroProfessor tela = new Tela_cadastroProfessor("Administrador");
                tela.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Tela_cadastroProfessor() {
        this("Administrador"); // Padrão
    }

    public Tela_cadastroProfessor(String perfil) {
        this.perfilUsuario = perfil;
        setTitle("AcademiaPro - Cadastrar Professor");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1024, 650));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // --- Menu Lateral Unificado ---
        JPanel panelMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelMenu.setBackground(new Color(0, 0, 64));
        panelMenu.setPreferredSize(new Dimension(220, 720));
        
        JButton btnVoltar = new JButton("⬅ Voltar ao Menu");
        btnVoltar.setPreferredSize(new Dimension(190, 40));
        btnVoltar.setBackground(AZUL_DESTAQUE);
        btnVoltar.setForeground(BRANCO);
        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnVoltar.setBorder(null);
        btnVoltar.addActionListener(e -> dispose());
        panelMenu.add(btnVoltar);
        getContentPane().add(panelMenu, BorderLayout.WEST);

        // --- Área Central (Formulário) ---
        JPanel panelConteudo = new JPanel(new BorderLayout());
        panelConteudo.setBackground(new Color(0, 0, 64));
        panelConteudo.setBorder(new EmptyBorder(40, 50, 40, 50));

        // Mudado para 9 linhas e 2 colunas para comportar todos os campos e botões
        JPanel panelCard = new JPanel(new GridLayout(9, 2, 15, 20));
        panelCard.setBackground(BRANCO);
        panelCard.setBorder(new EmptyBorder(30, 30, 30, 30));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 14);

        // 1. Campo: Nome
        panelCard.add(new JLabel("Nome:") {{ setFont(fontLabel); }});
        txtNome = new JTextField(); txtNome.setFont(fontText); panelCard.add(txtNome);

        // 2. Campo: Apelido
        panelCard.add(new JLabel("Apelido:") {{ setFont(fontLabel); }});
        txtApelido = new JTextField(); txtApelido.setFont(fontText); panelCard.add(txtApelido);

        // 3. Campo: E-mail
        panelCard.add(new JLabel("E-mail:") {{ setFont(fontLabel); }});
        txtEmail = new JTextField(); txtEmail.setFont(fontText); panelCard.add(txtEmail);

        // 4. Campo: Contacto
        panelCard.add(new JLabel("Contacto:") {{ setFont(fontLabel); }});
        txtContacto = new JTextField(); txtContacto.setFont(fontText); panelCard.add(txtContacto);

        // 5. Campo: Gênero
        panelCard.add(new JLabel("Gênero:") {{ setFont(fontLabel); }});
        comboGenero = new JComboBox<>();
        comboGenero.setModel(new DefaultComboBoxModel<>(new String[] {"masculino", "feminino"}));
        comboGenero.setFont(fontText);
        panelCard.add(comboGenero);

        // 6. Campo: Estado Civil
        panelCard.add(new JLabel("Estado Civil:") {{ setFont(fontLabel); }});
        comboEstadoCivil = new JComboBox<>();
        comboEstadoCivil.setModel(new DefaultComboBoxModel<>(new String[] {"solteiro", "casado"}));
        comboEstadoCivil.setFont(fontText);
        panelCard.add(comboEstadoCivil);

        // 7. Campo: Horas de Trabalho
        panelCard.add(new JLabel("Horas de Trabalho:") {{ setFont(fontLabel); }});
        spinnerHoras = new JSpinner(new SpinnerNumberModel(0, 0, 500, 1));
        spinnerHoras.setFont(fontText);
        panelCard.add(spinnerHoras);

        // 8. Campo: Salário Hora
        panelCard.add(new JLabel("Salário Hora:") {{ setFont(fontLabel); }});
        spinnerSalarioHora = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 1));
        spinnerSalarioHora.setFont(fontText);
        panelCard.add(spinnerSalarioHora);

        // 9. Linha: Botões de Ação
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(BRANCO);
        btnCancelar.setFont(fontLabel);
        btnCancelar.addActionListener(e -> dispose());

        btnSalvar = new JButton("Salvar Registro");
        btnSalvar.setBackground(AZUL_DESTAQUE);
        btnSalvar.setForeground(BRANCO);
        btnSalvar.setFont(fontLabel);
        btnSalvar.addActionListener(e -> acaoSalvar());

        panelCard.add(btnCancelar);
        panelCard.add(btnSalvar);

        panelConteudo.add(panelCard, BorderLayout.NORTH);
        getContentPane().add(panelConteudo, BorderLayout.CENTER);

        aplicarPermissoes();
    }

    /**
     * Lógica responsável por coletar, validar e enviar os dados para a controller.
     */
    private void acaoSalvar() {
        try {
            String nome = txtNome.getText();
            String apelido = txtApelido.getText();
            String email = txtEmail.getText();
            
            // Validação de número de telefone
            int contacto = Integer.parseInt(txtContacto.getText());
            
            String genero = comboGenero.getSelectedItem().toString();
            String estadoCivil = comboEstadoCivil.getSelectedItem().toString();
            
            int hora = (int) spinnerHoras.getValue();
            int valor_hora = (int) spinnerSalarioHora.getValue();
            int salario = valor_hora * hora;
            
            // --- SIMULAÇÃO DA CONTROLLER ---
            // Substituído para rodar de forma independente
            // FormadorController formadorController = new FormadorController();
            // boolean sucesso = formadorController.cadastrarFormador(nome, apelido, email, genero, estadoCivil, contacto, salario); 
            
            boolean sucesso = true; // Simula que salvou com sucesso
            
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Dados guardados com sucesso! (Simulado)");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Introdução inválida, Tente Novamente.");
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Introduza todos os dados numéricos corretamente (Ex: Contacto).");
            ex.printStackTrace();
        } catch (Exception el) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar os dados: " + el.getMessage());
        }
    }

    /**
     * Método auxiliar para limpar os campos após um cadastro com sucesso.
     */
    private void limparCampos() {
        txtNome.setText("");
        txtApelido.setText("");
        txtEmail.setText("");
        txtContacto.setText("");
        comboGenero.setSelectedIndex(0);
        comboEstadoCivil.setSelectedIndex(0);
        spinnerHoras.setValue(0);
        spinnerSalarioHora.setValue(0);
    }

    private void aplicarPermissoes() {
        if (perfilUsuario.equalsIgnoreCase("Professor")) {
            txtNome.setEditable(false);
            txtApelido.setEditable(false);
            txtEmail.setEditable(false);
            txtContacto.setEditable(false);
            comboGenero.setEnabled(false);
            comboEstadoCivil.setEnabled(false);
            spinnerHoras.setEnabled(false);
            spinnerSalarioHora.setEnabled(false);
            btnSalvar.setEnabled(false);
            btnSalvar.setToolTipText("Professores não possuem permissão para criar registros.");
        }
    }
}