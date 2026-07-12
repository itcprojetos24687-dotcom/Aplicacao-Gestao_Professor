package dao;

import model.Formando;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import dao.Conexao; 

public class FormandoDao {

    Connection con = null;

    public void cadastrarFormando(Formando formando) throws ExceptionDao {
        // CORRIGIDO: Nomes das colunas ajustados para bater com o banco (nome_formando, apelido_formando, etc)
        String sql = "insert into Formando(nome_formando, apelido_formando, contacto_formando, email, BI) values(?,?,?,?,?)";
        PreparedStatement insertFormando = null;
        try {
            con = new Conexao().getConnection();
            insertFormando = con.prepareStatement(sql);
            insertFormando.setString(1, formando.getNome());
            insertFormando.setString(2, formando.getApelido());
            insertFormando.setInt(3, formando.getContacto());
            insertFormando.setString(4, formando.getEmail());
            insertFormando.setString(5, formando.getBi());
            insertFormando.execute();
        } catch (SQLException e) {
            throw new ExceptionDao("Erro ao inserir dados: " + e);
        } finally {
            try {
                if (insertFormando != null) {
                    insertFormando.close();
                }
            } catch (SQLException sq) {
                throw new ExceptionDao("Erro ao fechar o statement: " + sq);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException f) {
                throw new ExceptionDao("Erro ao fechar a conexao: " + f);
            }
        }
    }

    public ArrayList<Formando> listarFormando(String nome) throws ExceptionDao {
        // CORRIGIDO: O filtro agora busca na coluna 'nome_formando'
        String sql = "select * from Formando where nome_formando like '%" + nome + "%'";
        PreparedStatement listarFormando = null;
        ArrayList<Formando> formandos = null;
        
        try {
            con = new Conexao().getConnection();
            listarFormando = con.prepareStatement(sql);
            ResultSet rs = listarFormando.executeQuery();
            
            if (rs != null) {
                formandos = new ArrayList<>();
                while (rs.next()) {
                    Formando formando = new Formando(); 
                    // CORRIGIDO: rs.get de acordo com os nomes reais da tabela SQL
                    formando.setCodigo(rs.getInt("codigo_formando"));
                    formando.setNome(rs.getString("nome_formando"));
                    formando.setApelido(rs.getString("apelido_formando"));
                    formando.setContacto(rs.getInt("contacto_formando"));
                    formando.setEmail(rs.getString("email"));
                    formando.setBi(rs.getString("BI"));
                    formandos.add(formando);
                }
            }
        } catch (SQLException ex) {
            throw new ExceptionDao("Erro ao selecionar dados: " + ex);
        } finally {
            try {
                if (listarFormando != null) {
                    listarFormando.close();
                }
            } catch (SQLException es) {
                throw new ExceptionDao("Erro ao fechar o statement: " + es);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sq) {
                throw new ExceptionDao("Erro ao fechar conexao: " + sq);
            }
        }
        return formandos;
    }

    public void atualizarFormando(Formando formando) throws ExceptionDao {
        // CORRIGIDO: Mapeamento de colunas atualizado para o UPDATE
        String sql = "update Formando set nome_formando = ?, apelido_formando = ?, contacto_formando = ?, email = ?, BI = ? where codigo_formando = ?";
        PreparedStatement alterarFormando = null;
        
        try {
            con = new Conexao().getConnection();
            alterarFormando = con.prepareStatement(sql);
            alterarFormando.setString(1, formando.getNome());
            alterarFormando.setString(2, formando.getApelido());
            alterarFormando.setInt(3, formando.getContacto());
            alterarFormando.setString(4, formando.getEmail()); 
            alterarFormando.setString(5, formando.getBi());    
            alterarFormando.setInt(6, formando.getCodigo());   
            
            alterarFormando.executeUpdate(); 
        } catch (SQLException e) {
            throw new ExceptionDao("Erro ao alterar dados: " + e);
        } finally {
            try {
                if (alterarFormando != null) {
                    alterarFormando.close();
                }
            } catch (SQLException sq) {
                throw new ExceptionDao("Erro ao fechar o statement: " + sq);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException l) {
                throw new ExceptionDao("Erro ao fechar a conexao: " + l);
            }
        }
    }

    public void apagarFormando(Formando formando) throws ExceptionDao {
        // CORRIGIDO: Chave primária é 'codigo_formando'
        String sql = "delete from Formando where codigo_formando=?"; 
        PreparedStatement apagarFormando = null;
        try {
            con = new Conexao().getConnection();
            apagarFormando = con.prepareStatement(sql);
            apagarFormando.setInt(1, formando.getCodigo());
            apagarFormando.executeUpdate();
        } catch (SQLException e) {
             throw new ExceptionDao("Erro ao apagar dados: " + e);
        } finally {
            try {
                if (apagarFormando != null) {
                    apagarFormando.close();
                }
            } catch (SQLException sq) {
                throw new ExceptionDao("Erro ao fechar o statement: " + sq);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException f) {
                throw new ExceptionDao("Erro ao fechar a conexao: " + f);
            }
        }
    }
}