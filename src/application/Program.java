package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import bd.DB;
import bd.DbException;
import bd.DbIntegrityException;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		Statement st = null;
		
		try {
			
			Department obj = new Department(1, "Books");
			System.out.println(obj);
			
			
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
