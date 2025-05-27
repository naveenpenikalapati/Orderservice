package com.orderProject.Orderdetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService 
{
		@Autowired
		private OrderRepo orderrepo;
		
		@Autowired
		private ProductRepo productrepo;

		public OrderModel addProductstoOrder(int orderid, List<ProductModel> products) 
		{
			OrderModel  order =  orderrepo.findById(orderid)
					                     .orElseThrow(() -> new RuntimeException("Order not found"));
			
			for(ProductModel product :products)
			{
				product.setOrder(order);       // set parent reference
			}
			
			productrepo.saveAll(products);
			
			return  orderrepo.findById(orderid).get();   // return updated order with new products
		}

		public OrderModel deleteProductFromOrder(int productID) 
		{
			ProductModel product  =  productrepo.findById(productID).orElseThrow(() -> new RuntimeException("Product not found"));
			        
			OrderModel  order = product.getOrder();
			
			   int orderid = order.getOrderId();
			
			   productrepo.deleteById(productID);
					
		   return orderrepo.findById(orderid).get();
		}

		public List<OrderModel> searchProducts(String category) 
		{
			return orderrepo.findByProductsCategory(category);
		}	
	}

