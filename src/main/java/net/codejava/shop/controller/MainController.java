package net.codejava.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.codejava.shop.dao.ProductDAO;
import net.codejava.shop.model.Product;

@Controller
public class MainController {

	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value="/")
	public ModelAndView listProduct(ModelAndView model) {
		List<Product> listProduct = productDAO.list();
		model.addObject("listProduct",listProduct);
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value="/new",method=RequestMethod.GET)
	public ModelAndView newProduct(ModelAndView model) {
		Product newProduct = new Product();
		model.addObject("product",newProduct);
		model.setViewName("product_form");
		return model;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveProduct(@ModelAttribute Product product) {
		if(product.getProductCode() == null) {
			productDAO.save(product);
		}else {
			productDAO.update(product);
		}
		
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView editProduct(HttpServletRequest request) {
		Integer productCode = Integer.parseInt(request.getParameter("productCode"));
		Product product = productDAO.get(productCode);
		
		ModelAndView model = new ModelAndView("product_form");
		
		model.addObject("product",product);
		
		return model;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public ModelAndView deleteProduct(@RequestParam Integer productCode) {
		productDAO.delete(productCode);
		
		return new ModelAndView("redirect:/");
	}
}
