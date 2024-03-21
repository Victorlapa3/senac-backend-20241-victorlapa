package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.entity.Pais;

public class PaisRepository {

// metodo salvar
	public Pais salvar(Pais novoPais) {
		String query = "INSERT INTO pais (nome, sigla) VALUES (?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			// Preencher as interrogações
			pstmt.setString(1, novoPais.getNome());
			pstmt.setString(2, novoPais.getSigla());

			// Executar a query
			pstmt.execute();

			// Preencher a PK gerada
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novoPais.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar nova pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novoPais;
	}

////	metodo consultar por id
//	public Pais consultarPorId(int id) {
//	Connection conn = Banco.getConnection();
//	Statement stmt = Banco.getStatement(conn);
//	
//	ResultSet resultado = null;
//	Pais pais = new Pais();
//	
//	String query = " SELECT * FROM pais WHERE id = " + id;
//	try {
//		resultado = stmt.executeQuery(query);
//		if(resultado.next()) {
//			pais.setId(Integer.parseInt(resultado.getString("ID")));
//			
//		}
//		
//	} catch (SQLException erro) {
//		System.out.println("Erro ao executar consultar pais com id (" + id + ")");
//		System.out.println("Erro: " + erro.getMessage());
//	} finally {  
//		Banco.closeResultSet(resultado);
//		Banco.closeStatement(stmt);
//		Banco.closeConnection(conn);
//	}
//	return pais;
//	}
}
