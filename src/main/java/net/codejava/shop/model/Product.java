package net.codejava.shop.model;

public class Product {

	public Product() {
		
	}
	
	public Product(Integer productCode, String productName, String barcode, double unitePrice) {
		this(productName, barcode, unitePrice);
		this.productCode = productCode;
	}

	public Product(String productName, String barcode, double unitePrice) {
		this.productName = productName;
		this.barcode = barcode;
		this.unitePrice = unitePrice;
	}

	private Integer productCode;
	private String productName;
	private String barcode;
	private double unitePrice;

	public Integer getProductCode() {
		return productCode;
	}

	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public double getUnitePrice() {
		return unitePrice;
	}

	public void setUnitePrice(double unitePrice) {
		this.unitePrice = unitePrice;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productName=" + productName + ", barcode=" + barcode
				+ ", unitePrice=" + unitePrice + "]";
	}
	
	
}
