package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1: seller findById =====");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2: seller findByDepartment =====");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n=== TEST 3: seller findAll =====");
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n=== TEST 4: seller insert =====");
		Seller newSeller = new Seller(1, "Gohan Zaccaro", "greg@gmail.com", new Date(), 4000.0, department);
		/* Durante a aula o professor deixa o argunto do "Id" como "nulo" e o Eclipse aceita normalmente, 
		   Não sei porque motivo aqui no meu Eclipse sou obrigado a informar um valor inteiro, pois o 
		   compilador gera erro dizendo que o argumento não pode ser nulo. Estou deixando o numero "1"...
		   Mas ao enviar o INSERT o banco de dados utiliza o AUTO_INCREMENT e coloca a numeração correta.
		*/
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());

	}

}
