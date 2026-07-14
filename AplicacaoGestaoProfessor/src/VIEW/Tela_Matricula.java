package VIEW;
import model.*;
import controller.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
public class Tela_Matricula extends JPanel {
    private static final long serialVersionUID = 1L;

    private JComboBox<Formando> comboFormando;
    private JComboBox<Qualificacao> comboQualificacao;
    private JComboBox<Nivel> comboNivel;
    private ArrayList<Formando> formandos;
    private ArrayList<Qualificacao> qualificacoes;
    private ArrayList<Nivel> niveis;
    private FormandoController fc = null;
    private JTextField txtDataMatricula;
    private JButton btnConfirmar, btnCancelar;
    private JFormattedTextField campoData ;
    
    private int idUser = 0;
    private final Color AZUL_DESTAQUE = new Color(13, 110, 253);
    private final Color FUNDO_CLARO   = new Color(244, 246, 249);
    private final Color BRANCO        = Color.WHITE;

    public interface OnMatriculaCadastradaListener {
        void onMatriculaCadastrada(String numMatricula, String aluno, String turma, String sala, String data);
        void onCancelar();
    }

    private OnMatriculaCadastradaListener listener;

    public Tela_Matricula(OnMatriculaCadastradaListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());
        setBackground(FUNDO_CLARO);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelCard = new JPanel(new GridLayout(4, 2, 10, 15));
        panelCard.setBackground(BRANCO);
        panelCard.setBorder(new EmptyBorder(25, 25, 25, 25));

        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontText  = new Font("Segoe UI", Font.PLAIN, 14);

        // Nome do Formando
        panelCard.add(new JLabel("Nome do Formando *") {{ setFont(fontLabel); }});
        comboFormando = new JComboBox<>();
        comboFormando.setFont(fontText);
        try {
        	fc =new FormandoController();
        	formandos = fc.comboFormando();
        	for(Formando f: formandos) {
        		comboFormando.addItem(f);
        	}
        	
        }catch(Exception s) {
        	s.printStackTrace();
        }
        panelCard.add(comboFormando);
        

        // Qualificação
        panelCard.add(new JLabel("Qualificação *") {{ setFont(fontLabel); }});
        comboQualificacao = new JComboBox<>();
        comboQualificacao.setFont(fontText);
        panelCard.add(comboQualificacao);
        try {
    		QualificacaoController qc = new QualificacaoController();
    		qualificacoes = qc.comboQualificacao();
    		for(Qualificacao q: qualificacoes) {
    			comboQualificacao.addItem(q);
    		}
        }catch(Exception e) {
        	e.printStackTrace();
        }

        // Nível
        panelCard.add(new JLabel("Nível *") {{ setFont(fontLabel); }});
        comboNivel = new JComboBox<>();
        comboNivel.setFont(fontText);
        comboQualificacao.addActionListener(e -> {
        	try {
        		
        		Qualificacao q= (Qualificacao) comboQualificacao.getSelectedItem();
        		comboNivel.removeAllItems();
        		Quali_Nivel qn = new Quali_Nivel();
        		niveis = qn.getQualificacao_Nivel(q);
        		for(Nivel n : niveis) {
        			comboNivel.addItem(n);
        		}
        		
        		
        	}catch(Exception s) {
        		s.printStackTrace();
        	}
        });
        panelCard.add(comboNivel);

        // Data da Matrícula
        panelCard.add(new JLabel("Data da Matrícula *") {{ setFont(fontLabel); }});
        txtDataMatricula = new JTextField();
        txtDataMatricula.setFont(fontText);
        txtDataMatricula.setToolTipText("dd/mm/aaaa");
        try {
        	
        	MaskFormatter mascaraData = new MaskFormatter("##/##/####");
        	
        	mascaraData.setPlaceholder("_");
        	 campoData = new JFormattedTextField(mascaraData);
        	campoData.setColumns(10);
        }catch(Exception s) {
        	s.printStackTrace();
        }
        panelCard.add(campoData);
        //panelCard.add(txtDataMatricula);

        // Barra de botões
        JPanel barraBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        barraBotoes.setBackground(BRANCO);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(fontLabel);
        btnCancelar.setBackground(BRANCO);
        btnCancelar.addActionListener(e -> {
            limparCampos();
            setVisible(false);
        });

        btnConfirmar = new JButton("Efectuar Matrícula");
        btnConfirmar.setBackground(AZUL_DESTAQUE);
        btnConfirmar.setForeground(BRANCO);
        btnConfirmar.setFont(fontLabel);
        btnConfirmar.addActionListener(e -> acaoSalvar());

        barraBotoes.add(btnCancelar);
        barraBotoes.add(btnConfirmar);

        JPanel containerCentral = new JPanel(new BorderLayout(0, 20));
        containerCentral.setBackground(BRANCO);
        containerCentral.setBorder(new EmptyBorder(10, 10, 10, 10));
        containerCentral.add(panelCard, BorderLayout.CENTER);
        containerCentral.add(barraBotoes, BorderLayout.SOUTH);

        add(containerCentral, BorderLayout.CENTER);
    }

    public Tela_Matricula() {
        this(null);
        JFrame janela = new JFrame("Efetuar Matrícula");
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setSize(500, 350);
        janela.setLocationRelativeTo(null);
        janela.getContentPane().add(this);
        this.listener = new OnMatriculaCadastradaListener() {
            @Override
            public void onMatriculaCadastrada(String numMatricula, String aluno, String turma, String sala, String data) {
                janela.dispose();
            }
            @Override
            public void onCancelar() {
                janela.dispose();
            }
        };
        janela.setVisible(true);
    }

    private void acaoSalvar() {
    	boolean sucesso;
    	
    	try {
    		
    		Formando formando = (Formando) comboFormando.getSelectedItem();
    		//String data     = txtDataMatricula.getText().trim();
    		Qualificacao q = (Qualificacao) comboQualificacao.getSelectedItem();
    		Nivel n = (Nivel) comboNivel.getSelectedItem();
    		String data = campoData.getText();
    		
    		
    		if (data.isEmpty()) {
    			JOptionPane.showMessageDialog(this, "Por favor, preencha a data da matrícula.", "Aviso", JOptionPane.WARNING_MESSAGE);
    			return;
    		}
    		if(idUser == 0) {
    			MatriculaController mc = new MatriculaController();
    			sucesso = mc.cadastrarMatricula(formando,q,n,data);
    			if(sucesso) {
    				JOptionPane.showMessageDialog(null, "Matricula Efectuada com sucesso");
    			}
    			else {
    				JOptionPane.showMessageDialog(null, "falha ao efectuar Matricula");
    			}
    			limparCampos();
    		}
    		else {
    			MatriculaController mc = new MatriculaController();
    			sucesso = mc.atualizarMatricula(idUser,(Formando)comboFormando.getSelectedItem(), (Qualificacao)comboQualificacao.getSelectedItem(), (Nivel)comboNivel.getSelectedItem(),data);
    		}if(sucesso) {
				JOptionPane.showMessageDialog(null, "Matricula atualizada com sucesso");
				
			}
			else {
				JOptionPane.showMessageDialog(null, "falha ao atualizar Matricula");
			}
			limparCampos();
    		
    		
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    public void buscarMatricula(int codigo,Formando f, Qualificacao q, Nivel n,String data) {
    	idUser = codigo;
    	for(int i = 0; i<comboFormando.getItemCount();i++) {
    		if(comboFormando.getItemAt(i).equals(f)) {
    			comboFormando.setSelectedIndex(i);
    		}
    	}
    	for(int i = 0; i<comboQualificacao.getItemCount();i++) {
    		if(comboQualificacao.getItemAt(i).equals(q)) {
    			comboQualificacao.setSelectedIndex(i);
    		}
    	}
    	for(int i = 0; i<comboNivel.getItemCount();i++) {
    		if(comboNivel.getItemAt(i).equals(n)) {
    			comboNivel.setSelectedIndex(i);
    		}
    	}
    	campoData.setText(data);
    }

    private void limparCampos() {
        comboFormando.setSelectedIndex(0);
        comboQualificacao.setSelectedIndex(0);
        comboNivel.setSelectedIndex(0);
        txtDataMatricula.setText("");
    }
}