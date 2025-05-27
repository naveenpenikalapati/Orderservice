package com.EcomOrderMicroservice.order;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@jakarta.persistence.Entity
@Data
//@Builder
@NoArgsConstructor
public class OrderModel
{
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int orderId;
      private String Customer;
      private int   orderQuantity;
      private long  mobno;
      
      @Transient
      private List<ProductModel> products = new ArrayList<>();

	public OrderModel(int orderId, String customer, int orderQuantity, long mobno, List<ProductModel> products) {
		super();
		this.orderId = orderId;
		Customer = customer;
		this.orderQuantity = orderQuantity;
		this.mobno = mobno;
	}

      
//  @Builder.Default
  // @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//   @JsonManagedReference
      
//	public OrderModel() {
//		
//	}
      
      
      
//      public static void main(String[] args) {
//		 
//    	  OrderModel order =  OrderModel.builder().orderId(123).Customer("a").build();
//    	  
//    	  System.out.println(order.getOrderId()+" "+order.getCustomer());
//	}
      
}
