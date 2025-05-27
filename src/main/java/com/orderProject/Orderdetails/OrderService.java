package com.orderProject.Orderdetails;

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
	
//	@Autowired
//	private ProductRepo productrepo;
	
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

//	public OrderModel saveOrder(OrderModel order) 
//	{
//                                 // Set the parent reference in each product
//        for (ProductModel product : order.getProducts()) 
//        {
//            product.setOrder(order);
//        }
//        return orderrepo.save(order);
//    }

    public List<OrderModel> getAllOrders() {
        return orderrepo.findAll();
    }

	public OrderModel updateorders(OrderModel order) {
		
		return orderrepo.save(order);
	}

	public ResponseEntity<String> deleteOrder(List<Integer> orderid) 
	{
		orderrepo.deleteAllById(orderid);
		return new ResponseEntity<>("orders deleted",HttpStatus.OK);
	}
	
//	public Integer totalOrderAmount(int orderid) 
//	{
//		OrderModel order = orderrepo.findById(orderid).orElseThrow(() -> new RuntimeException("Order not found"));
//		
//		 List<ProductModel> products = order.getProducts();
//		 
//		 int totalamount =0;
//		 
//		 for(ProductModel product : products)
//		 {
//			 totalamount =totalamount+product.getAmount();
//		 }
//		
//		return totalamount;
//	}

	
}
