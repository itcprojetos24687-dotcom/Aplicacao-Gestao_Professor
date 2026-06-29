package dao;
import java.sql.*;
import java.util.ArrayList;
import model.Turma;
public class TurmaDao {
	public void cadastrarTurma(Turma turma) {
		String sql = "insert into(nome,ano_ingresso,turno) values(?,?,?)";
		Connection con = null;
		PreparedStatement inserir = null;
		try {
			con = new Conexao().getConnection();
			inserir = con.prepareStatement(sql);
			inserir.setString(1, turma.getNome());
			inserir.setInt(2, turma.getAno_ingresso());
			inserir.setString(3, turma.getTurno());
			inserir.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(inserir != null) {
					inserir.close();
				}
			}catch(SQLException ql) {
				ql.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			}catch(SQLException sq) {
				new ExceptionDao("Erro ao fechar conexao" +sq);
			}
		}
		
	}
		
	}

