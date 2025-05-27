package com.EcomOrderMicroservice.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderrepo;
	
	@Autowired
    private ProductFeignClient productFeignClient;
	
    public OrderWithProductsDTO addProductstoOrder(int orderid, List<ProductDTO> products)    // linked
    {
	     OrderModel  order = orderrepo.findById(orderid)
		                              .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderid));
    	
	     for(ProductDTO product : products)
	     {
	    	 product.setOrderId(orderid);
	     }
    	                         
	     productFeignClient.saveProductsToOrder(products);      // method in productFeignClient
	     
	     // fetch updated products to this order
	     List<ProductModel> products1 = productFeignClient.getProductsForOrder(orderid);    // method in productFeignClient - Notify ProductController
    	
	     //  Return combined DTO
	     OrderWithProductsDTO setupdated = new OrderWithProductsDTO();
	     setupdated.setOrder(order);
	     setupdated.setProducts(products1);
    	
		return setupdated;
	}

    public void updateOrderQuantity(int orderid, int orderquantity)      // linked
	{ 
		    OrderModel   order =  orderrepo.findById(orderid).orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderid));
		    System.out.println("updated quantity "+order.getOrderQuantity());
		    order.setOrderQuantity(orderquantity);
		    System.out.println("updated quantity "+order.getOrderQuantity());
		    orderrepo.save(order);	
	}
	
	public ResponseEntity<OrderWithProductsDTO> getOrderWithProducts(int orderId) {   // linked
		
	   return   productFeignClient.getOrderWithProducts(orderId);       // method in productFeignClient - Notify ProductController
	}
	
	 public OrderModel getOrderById(int orderId)           // linked
	 {
	        return orderrepo.findById(orderId)
	                        .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
	 }
	
	public void updateOrderAndProduct(OrderUpdateDTO dto)       // linked
	{
        productFeignClient.updateProductFromOrder(dto);   //method in productFeignClient -Notify ProductController
	}
	
	public Integer totalOrderAmount(int orderid)     // linked
	{
		System.out.println("orderid is "+orderid);
		
	List<ProductModel> products2  =   productFeignClient.getProductsForOrder(orderid);  //method in productFeignClient -Notify ProductController
		
		 System.out.println("called feign");                  
		 int totalamount = 0 ;
		 
		 for(ProductModel product : products2)
		 {
			 System.out.println("amount before sum ");
			 totalamount = totalamount + product.getAmount();
		 }
		System.out.println("amount after sum "+totalamount);
		return totalamount;
	}

	
	public ResponseEntity<List<OrderModel>> getOrders() 
	{
		try 
		{
			return new ResponseEntity<>(orderrepo.findAll(), HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public OrderModel gethighrorders(int orderQuantity) 
	{
		return orderrepo.findByOrderQuantity(orderQuantity);
	}

	public OrderModel  getTopOrder() 
	{
		return orderrepo.findByHighOrders();
	}

	public OrderModel createOrder(OrderModel order) 
	{
                                 // Set the parent reference in each product
        for (ProductModel product : order.getProducts()) 
        {
            product.setOrder(order);
        }
        return orderrepo.save(order);
    }
    
	public OrderModel updateorders(OrderModel order) {
		
		return orderrepo.save(order);
	}

	public ResponseEntity<String> deleteOrder(List<Integer> orderid) 
	{
		orderrepo.deleteAllById(orderid);
		return new ResponseEntity<>("orders deleted",HttpStatus.OK);
	}
	
	

	

	

	
}
