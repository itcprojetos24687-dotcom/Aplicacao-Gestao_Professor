package view;
import java.awt.EventQueue;
import javax.swing.JFrame;
import model.Turma;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class ViewTurma1 implements ActionListener, MouseListener {

    private JFrame frmGestaoTurmas;
    private JTable listagem;
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtAnoIngresso;
    private JTextField txtCodigoCurso;

    private JButton btnAdicionar, btnListar, btnEditar, btnRemover, btnLimpar;
    private JButton btnNovo;

    private ArrayList<Turma> listaTurmas = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewTurma1 window = new ViewTurma1();
                    window.frmGestaoTurmas.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewTurma1() {
        initialize();
    }

    private void initialize() {
        frmGestaoTurmas = new JFrame();
        frmGestaoTurmas.setFont(new Font("Dialog", Font.BOLD, 14));
        frmGestaoTurmas.setTitle("GESTAO DE TURMAS");
        frmGestaoTurmas.setBounds(100, 100, 992, 571);
        frmGestaoTurmas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmGestaoTurmas.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "DADOS DA TURMA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(15, 16, 654, 270);
        frmGestaoTurmas.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblCodigo = new JLabel("CODIGO");
        lblCodigo.setBounds(15, 31, 100, 20);
        panel.add(lblCodigo);

        JLabel lblNome = new JLabel("NOME");
        lblNome.setBounds(25, 73, 100, 20);
        panel.add(lblNome);

        JLabel lblAno = new JLabel("ANO INGRESSO");
        lblAno.setBounds(15, 119, 110, 20);
        panel.add(lblAno);

        JLabel lblCurso = new JLabel("COD. CURSO");
        lblCurso.setBounds(25, 169, 110, 20);
        panel.add(lblCurso);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(140, 23, 184, 36);
        txtCodigo.setColumns(10);
        panel.add(txtCodigo);

        txtNome = new JTextField();
        txtNome.setBounds(140, 65, 356, 36);
        txtNome.setColumns(10);
        panel.add(txtNome);

        txtAnoIngresso = new JTextField();
        txtAnoIngresso.setBounds(140, 111, 184, 36);
        txtAnoIngresso.setColumns(10);
        panel.add(txtAnoIngresso);

        txtCodigoCurso = new JTextField();
        txtCodigoCurso.setBounds(140, 159, 184, 36);
        txtCodigoCurso.setColumns(10);
        panel.add(txtCodigoCurso);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "OPERACOES", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(671, 21, 284, 265);
        frmGestaoTurmas.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        btnAdicionar = new JButton("ADICIONAR");
        btnAdicionar.setBounds(53, 16, 192, 43);
        btnAdicionar.addActionListener(this);
        panel_1.add(btnAdicionar);

        btnListar = new JButton("LISTAR");
        btnListar.setBounds(53, 53, 192, 43);
        btnListar.addActionListener(this);
        panel_1.add(btnListar);

        btnEditar = new JButton("EDITAR");
        btnEditar.setBounds(53, 94, 192, 35);
        btnEditar.addActionListener(this);
        panel_1.add(btnEditar);

        btnRemover = new JButton("REMOVER");
        btnRemover.setBounds(53, 129, 192, 35);
        btnRemover.addActionListener(this);
        panel_1.add(btnRemover);

        btnLimpar = new JButton("LIMPAR");
        btnLimpar.setBounds(53, 166, 192, 35);
        btnLimpar.addActionListener(this);
        panel_1.add(btnLimpar);

        btnNovo = new JButton("NOVO");
        btnNovo.setBounds(53, 206, 192, 43);
        btnNovo.addActionListener(this);
        panel_1.add(btnNovo);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(null, "LISTAGEM DE TURMAS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(15, 284, 940, 215);
        frmGestaoTurmas.getContentPane().add(panel_2);
        panel_2.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel_2.add(scrollPane, BorderLayout.CENTER);

        listagem = new JTable();
        listagem.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"CODIGO", "NOME TURMA", "ANO INGRESSO", "COD. CURSO"}
        ));
        scrollPane.setViewportView(listagem);
        listagem.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdicionar) {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                String nome = txtNome.getText();
                int ano = Integer.parseInt(txtAnoIngresso.getText());
                int codigoCurso = Integer.parseInt(txtCodigoCurso.getText());

                Turma t = new Turma(codigo, nome, ano, codigoCurso);
                listaTurmas.add(t);
                JOptionPane.showMessageDialog(null, "ADICIONADO COM SUCESSO");
                limparTabela();
                listar();
                limparCaixas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        }

        if (e.getSource() == btnListar) {
            limparTabela();
            listar();
        }

        if (e.getSource() == btnEditar) {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                for (Turma t : listaTurmas) {
                    if (t.getCodigo_turma() == codigo) {
                        t.setNome_turma(txtNome.getText());
                        t.setAno_ingresso(Integer.parseInt(txtAnoIngresso.getText()));
                        t.setcodigo_curso(Integer.parseInt(txtCodigoCurso.getText()));
                        break;
                    }
                }
                JOptionPane.showMessageDialog(null, "ACTUALIZADO COM SUCESSO");
                limparTabela();
                listar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        }

        if (e.getSource() == btnRemover) {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                listaTurmas.removeIf(t -> t.getCodigo_turma() == codigo);
                JOptionPane.showMessageDialog(null, "REMOVIDO COM SUCESSO");
                limparTabela();
                listar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        }

        if (e.getSource() == btnLimpar) {
            limparCaixas();
        }

        if (e.getSource() == btnNovo) {
            if (!listaTurmas.isEmpty()) {
                Turma ultima = listaTurmas.get(listaTurmas.size() - 1);
                txtCodigo.setText((ultima.getCodigo_turma() + 1) + "");
                txtCodigo.setEnabled(false);
            } else {
                txtCodigo.setText("1");
                txtCodigo.setEnabled(false);
            }
        }
    }

    public void listar() {
        Collections.sort(listaTurmas);
        DefaultTableModel modelo = (DefaultTableModel) listagem.getModel();
        for (Turma t : listaTurmas) {
            modelo.addRow(new Object[]{
                t.getCodigo_turma(),
                t.getNome_turma(),
                t.getAno_ingresso(),
                t.getcodigo_curso()
            });
        }
    }

    public void limparTabela() {
        while (listagem.getRowCount() > 0) {
            DefaultTableModel modelo = (DefaultTableModel) listagem.getModel();
            modelo.removeRow(0);
        }
    }

    public void limparCaixas() {
        txtCodigo.setText("");
        txtNome.setText("");
        txtAnoIngresso.setText("");
        txtCodigoCurso.setText("");
        txtCodigo.setEnabled(true);
        txtCodigo.requestFocus();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (listagem.getSelectedRow() != -1) {
            txtCodigo.setEnabled(false);
            int indice = listagem.getSelectedRow();
            DefaultTableModel linhaSelecionada = (DefaultTableModel) listagem.getModel();
            txtCodigo.setText(linhaSelecionada.getValueAt(indice, 0).toString());
            txtNome.setText(linhaSelecionada.getValueAt(indice, 1).toString());
            txtAnoIngresso.setText(linhaSelecionada.getValueAt(indice, 2).toString());
            txtCodigoCurso.setText(linhaSelecionada.getValueAt(indice, 3).toString());
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}