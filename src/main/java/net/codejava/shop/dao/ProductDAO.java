package net.codejava.shop.dao;

import java.util.List;

import net.codejava.shop.model.Product;

public interface ProductDAO {
	public int save(Product product);
	
	public int update(Product product);
	
	public Product get(Integer productCode);
	
	public int delete(Integer productCode);
	
	public List<Product> list();
}
