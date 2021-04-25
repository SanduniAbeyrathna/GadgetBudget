package com;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.nodes.Document;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Order;


@Path("/order")
public class OrderService {

	Order orderObj = new Order();
	
	@GET
	@Path("/")	
	@Produces(MediaType.TEXT_PLAIN) 
	public String readOrders() 
	{ 
		return orderObj.readOrders();
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertOrder(
	 @FormParam("orderCode") String orderCode, 
	 @FormParam("orderName") String orderName, 
	 @FormParam("orderPrice") String orderPrice, 
	 @FormParam("orderDesc") String orderDesc,
	 @FormParam("quantity")int quantity) 
	{ 
	  System.out.println("Hiruni--------------------");
		String output = orderObj.insertItem(orderCode, orderName, orderPrice, orderDesc,quantity); 
		return "hello:" + output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateOrder(String orderData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String orderID = orderObject .get("orderID").getAsString(); 
	 String orderCode = orderObject .get("orderCode").getAsString(); 
	 String orderName = orderObject .get("orderName").getAsString(); 
	 String orderPrice =orderObject .get("orderPrice").getAsString(); 
	 String orderDesc =orderObject .get("orderDesc").getAsString();
	 String quantity = orderObject.get("quantity").getAsString();
	 String output = orderObj.updateOrder(orderID, orderCode, orderName, orderPrice, orderDesc,quantity); 
	 return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteOrder(String orderData) 
	{ 
	//Convert the input string to an XML document
		Document doc = Jsoup.parse(orderData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <orderID>
	 String orderID = ((Element) doc).select("orderID").text(); 
	 String output = orderObj.deleteOrder(orderID); 
	 System.out.println(output);
	 System.out.println(orderID);
	 return output; 
	}
}