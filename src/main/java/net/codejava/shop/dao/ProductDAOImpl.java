package net.codejava.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.*;

import com.mysql.cj.protocol.Resultset;

import net.codejava.shop.model.Product;

public class ProductDAOImpl implements ProductDAO {

	private JdbcTemplate jdbcTemplate;

	public ProductDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Product p) {
		String sql = "INSERT INTO PRODUCTS (productName,barcode,unitePrice) VALUES (?,?,?)";
		jdbcTemplate.update(sql, p.getProductName(), p.getBarcode(), p.getUnitePrice());
		return 0;
	}

	@Override
	public int update(Product p) {
		String sql = "UPDATE PRODUCTS SET productName=?, barcode=?, unitePrice=? WHERE productCode=?";
		jdbcTemplate.update(sql, p.getProductName(), p.getBarcode(), p.getUnitePrice(), p.getProductCode());
		return 0;
	}

	@Override
	public Product get(Integer productCode) {
		String sql = "SELECT * FROM PRODUCTS WHERE productCode="+productCode;
		
		ResultSetExtractor<Product> extractor = new ResultSetExtractor<Product>() {

			@Override
			public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					String productName = rs.getString("productName");
					String barcode = rs.getString("barcode");
					Double unitePrice = rs.getDouble("unitePrice");
					
					return new Product(productCode,productName,barcode,unitePrice);
				}
				return null;
			}
		};
		
		return jdbcTemplate.query(sql,extractor);
	}

	@Override
	public int delete(Integer productCode) {
		String sql = "DELETE FROM PRODUCTS WHERE productCode="+ productCode;
		
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<Product> list() {
		String sql = "SELECT * FROM PRODUCTS";
		
		org.springframework.jdbc.core.RowMapper<Product> rowMapper = new org.springframework.jdbc.core.RowMapper<Product>() {
			
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer productCode = rs.getInt("productCode");
				String productName = rs.getString("productName");
				String barcode = rs.getString("barcode");
				Double unitePrice = rs.getDouble("unitePrice");
				
				return new Product(productCode,productName,barcode,unitePrice);
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}
	
}
