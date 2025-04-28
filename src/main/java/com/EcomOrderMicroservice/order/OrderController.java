package com.EcomOrderMicroservice.order;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController 
{
	@Autowired
	private OrderService orderservice;
	//@Autowired
   // Environment environment;
	
	@PostMapping("/addproductstoorder/{orderid}")       // linked
	public OrderWithProductsDTO addProductstoOrder(@PathVariable("orderid") int orderid,@RequestBody List<ProductDTO> products)
	{
		return orderservice.addProductstoOrder(orderid,products);
	}
	
	@PutMapping("/order/orderquantity/{orderid}")   // linked - method from OrderFeignClient of ProductMicroservice
	 public void updateOrderQuantity(@PathVariable("orderid") int orderid,@RequestParam int orderquantity)
	 {
		  System.out.println("Updating quantity for OrderID: " + orderid + " to " + orderquantity);
		 orderservice.updateOrderQuantity(orderid,orderquantity);
	 }

	@GetMapping("/orders/{orderId}")
	 public ResponseEntity<OrderWithProductsDTO> getOrderWithProducts(@PathVariable("orderId") int orderId)
	 {
		return orderservice.getOrderWithProducts(orderId);        // linked
	 }
	 
	@GetMapping("/gettingorder/{orderId}") //linked - method from OrderFeignClient of ProductMicroservice
    public OrderModel getOrderById(@PathVariable("orderId") int orderId) 
	{
        return orderservice.getOrderById(orderId);                
    }
	
	 @PostMapping("/update")        //linked - method from OrderFeignClient of ProductMicroservice
	public void updateOrderAndProduct(@RequestBody OrderUpdateDTO dto) 
	{
		 orderservice.updateOrderAndProduct(dto);               
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<OrderModel>> getOrders()
	{
		return orderservice.getOrders();
	}
	
	@GetMapping("/highestorders/{orderQuantity}")
	public OrderModel gethighrorders(@PathVariable Integer orderQuantity)
	{
		return orderservice.gethighrorders(orderQuantity);
	}
	
	@GetMapping("/top-order")        // order with highest no of order quantity
	public ResponseEntity<OrderModel> getTopOrder() {
	    return ResponseEntity.ok(orderservice.getTopOrder());
	}

	 @PostMapping("/createorder")
	 public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel order) 
	   {
	        return ResponseEntity.ok(orderservice.createOrder(order));
	    }

	
	@PutMapping("/updateorder")
	public OrderModel updateOrder(@RequestBody OrderModel order) 
	{
		return orderservice.updateorders(order);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteOrder(@RequestBody List<Integer> orderid)
	{
		return orderservice.deleteOrder(orderid);
	}
	
	// Total order amount per order
	@GetMapping("/total amount/{id}")
	public Integer totalOrderAmount(@PathVariable("id") int orderid)
	{
		return orderservice.totalOrderAmount(orderid);
	}
	
	
	
	
	
	
	
	
	
}
