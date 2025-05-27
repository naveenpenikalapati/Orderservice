package com.orderProject.Orderdetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService ProductService;
	
	// add products to existing order
	@PostMapping("/{id}/products")
	public OrderModel addProductstoOrder(@PathVariable("id") int orderid,@RequestBody List<ProductModel> products)
	{
		return ProductService.addProductstoOrder(orderid,products);
	}
	
	// Remove a Product from an Order
	@DeleteMapping("/{id}/deleteproduct")
	public OrderModel deleteProductFromOrder(@PathVariable("id") int productID)
	{
		return ProductService.deleteProductFromOrder(productID);
	}
	
	// Search Orders by Product Category
	@GetMapping("/search")
	public List<OrderModel> searchProducts(@RequestParam("category") String category)
	{
		return ProductService.searchProducts(category);
	}
	
}
