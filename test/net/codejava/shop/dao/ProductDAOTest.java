package net.codejava.shop.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import net.codejava.shop.model.Product;

class ProductDAOTest {

	private DriverManagerDataSource dataSource;
	private ProductDAO dao;
	
	@BeforeEach
	void setupBeforeEach() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/shop");
		dataSource.setUsername("root");
		dataSource.setPassword("password");
		
		dao = new ProductDAOImpl(dataSource);
	}
	
	@Test
	void testSave() {
		Product product = new Product("hammer","VNHG45g",12.36);
		int result = dao.save(product);
		
		assertTrue(result>0);
	}

	@Test
	void testUpdate() {
		Product product = new Product(1,"screw driver","dfgf5560",4530);
		int result = dao.update(product);
		
		assertTrue(result>0);
	}

	@Test
	void testGet() {
		Integer productCode = 0;
		Product product = dao.get(productCode);
		if(product != null) {
			System.out.println(product);
		}
		assertNotNull(product);
	}

	@Test
	void testDelete() {
		Integer productCode = 2;
		int result = dao.delete(productCode);
		
		assertTrue(result>0);
	}

	@Test
	void testList() {
		List <Product> listProducts = dao.list();
		
		for(Product p : listProducts) {
			System.out.println(p);
		}
		assertTrue(listProducts.isEmpty());
	}

}
