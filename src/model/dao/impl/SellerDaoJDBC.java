package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bd.DB;
import bd.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn; // isso mais o construtor abaixo criam uma dependência da classe SellerDaoJDBC com
	// a conexão do banco de dados
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn; 
	}
	
	@Override
	public void insert(Seller obj) {
		
	}

	@Override
	public void update(Seller obj) {
		
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(/*
					"SELECT sl.*, dp.Name as Department_Name FROM seller sl INNER JOIN Department dp"
					+ "ON sl.DepartmentId = dp.Id "
				    + "Where sl.Id = ?" */
					
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?"
					
					);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = instanteateDepartment(rs);
				Seller obj = instanteateSeller(rs, dep);
				return obj;
			}
			return null;
			
		}
		catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally {
			
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}


	}

	private Seller instanteateSeller(ResultSet rs, Department dep) throws SQLException {

		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep); // aqui não pega o "id" da coluna e sim o objeto "dep" do Department
		return obj;
	}

	private Department instanteateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		return null;
	}

}
