package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pessoa;
import model.entity.vemnox1.Jogador;

public class PessoaRepository implements BaseRepository<Pessoa> {

//	public Pessoa verificarCpf(Pessoa novaPessoa) {
//		
//	}
	
	@Override
	public Pessoa salvar(Pessoa novaPessoa) {
		String query = "INSERT INTO pessoa (nome, dtNascimento, sexo, CPF) VALUES (?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			// TODO este bloco repete-se no alterar().... refatorar!
			preencherParametrosParaInsertOuUpdate(pstmt, novaPessoa);

			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novaPessoa.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar novo jogador");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novaPessoa;
	}

	private void preencherParametrosParaInsertOuUpdate(PreparedStatement pstmt, Pessoa novaPessoa) throws SQLException {
		pstmt.setString(1, novaPessoa.getNome());
		pstmt.setDate(2, Date.valueOf(novaPessoa.getDtNascimento()));
		pstmt.setString(3, novaPessoa.getSexo() + "");
		pstmt.setString(4, novaPessoa.getCpf());
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM pessoa WHERE id = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Pessoa novaPessoa) {
		boolean alterou = false;
		String query = " UPDATE pessoa " + " SET nome=? , dt_nascimento=?, sexo=?, cpf=?, tipo=?" + " WHERE id=?";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {
			pstmt.setString(1, novaPessoa.getNome());
			pstmt.setDate(2, Date.valueOf(novaPessoa.getDtNascimento()));
			pstmt.setString(3, novaPessoa.getSexo() + "");
			pstmt.setString(4, novaPessoa.getCpf());
			pstmt.setString(5, novaPessoa.getTipo());

			pstmt.setInt(6, novaPessoa.getId());
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	@Override
	public Pessoa consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		Pessoa pessoa = new Pessoa();
		String query = " SELECT * FROM pessoa WHERE id = " + id;
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				pessoa.setId(Integer.parseInt(resultado.getString("ID")));
				pessoa.setNome(resultado.getString("NOME"));
				pessoa.setDtNascimento(resultado.getDate("DtNascimento").toLocalDate());
				pessoa.setSexo(resultado.getString("SEXO").charAt(0));
				pessoa.setCpf(resultado.getString("CPF"));
				pessoa.setTipo(resultado.getString("TIPO"));
			}

		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar jogador com id (" + id + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoa;
	}

	@Override
	public ArrayList<Pessoa> consultarTodos() {
	ArrayList<Pessoa> pessoas = new ArrayList<>();
	Connection conn = Banco.getConnection();
	Statement stmt = Banco.getStatement(conn);
	
	ResultSet resultado = null;
	String query = " SELECT * FROM pessoa";
	
	try{
		resultado = stmt.executeQuery(query);
		while(resultado.next()){
			Pessoa pessoa = new Pessoa();
			
			//TODO este bloco repete-se no consultarTodos().... refatorar!
			pessoa.setId(Integer.parseInt(resultado.getString("ID")));
			pessoa.setNome(resultado.getString("NOME"));
			pessoa.setDtNascimento(resultado.getDate("DtNascimento").toLocalDate());
			pessoa.setSexo(resultado.getString("SEXO").charAt(0));
			pessoa.setCpf(resultado.getString("CPF"));
			pessoa.setTipo(resultado.getString("TIPO"));
			pessoas.add(pessoa);
		}
	} catch (SQLException erro){
		System.out.println("Erro ao executar consultar todas as pessoas");
		System.out.println("Erro: " + erro.getMessage());
	} finally {
		Banco.closeResultSet(resultado);
		Banco.closeStatement(stmt);
		Banco.closeConnection(conn);
	}
	return pessoas;
}

}
