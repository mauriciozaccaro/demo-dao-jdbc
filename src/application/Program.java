package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = new DaoFactory().createSellerDao();
		System.out.println("<<TESTE NUMERO 1: Seller findById>>");
		Seller seller = sellerDao.findById(8);
		
		System.out.println(seller);
	}

}
