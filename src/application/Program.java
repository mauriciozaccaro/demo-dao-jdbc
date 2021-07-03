package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import bd.DB;
import bd.DbException;
import bd.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);// Espera uma confirmação do programador, tipo enviar o COMMIT
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2590.0 "
					+ "WHERE DepartmentId = 1");
			
			/*int x = 1;
			if(x < 2) {
				throw new SQLException("Erro de teste falso");
			}*/

			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090.0 "
					+ "WHERE DepartmentId = 4");
			
			
			conn.commit(); // colocando o setAutoComit(false) e deixando o "comit()" aqui ele só vai comitar 
			// se tudo chegar até aqui, caso contrário não comita nada
			
			System.out.println("Rows1: " + rows1);
			System.out.println("Rows2: " + rows2);
			
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transação não foi concluída. Devido ao seguinte erro: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro ao tentar fazer RollBack: " + e.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
