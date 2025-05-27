package com.EcomOrderMicroservice.order;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name ="productmicroservice") 
public interface ProductFeignClient 
{

	@PostMapping("/saveproducts")  
	public void saveProductsToOrder(@RequestBody List<ProductDTO> products);
	
	@GetMapping("/products/byorder/{orderid}")
	public List<ProductModel> getProductsForOrder(@PathVariable("orderid")int orderid);
	
	@GetMapping(value="/order-details/{orderId}")
	ResponseEntity<OrderWithProductsDTO> getOrderWithProducts(@PathVariable("orderId") int orderId); 
	
	@PutMapping("/update-by-order")
    void updateProductFromOrder(@RequestBody OrderUpdateDTO dto);
	
	
//	@getmapping("/getproducts/{id}")
//	void getPro
	
	
	
	
	
	
	
	
	
	
	
}
