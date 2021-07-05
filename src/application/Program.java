package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = new DaoFactory().createSellerDao();
		System.out.println("<<TESTE NUMERO 1: Seller findById>>");
		Seller seller1 = sellerDao.findById(8);		
		System.out.println(seller1);
		
		System.out.println("");
		
		System.out.println("<<TESTE NUMERO 2: Seller findByDepartmentId>>");
		Department dep = new Department(1, null);
		List<Seller> list = sellerDao.findByDepartment(dep);		
	
		for(Seller obj : list) {
			
			System.out.println(obj);
			
		}

		System.out.println("\n<<TESTE NUMERO 3: Seller findAll>>");
		list = sellerDao.findAll();	
	
		for(Seller obj : list) {
			
			System.out.println(obj);
			
		}
		
	}

}
