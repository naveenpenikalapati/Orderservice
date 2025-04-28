package com.EcomOrderMicroservice.order;

import lombok.Data;


@Data
public class ProductModel 
{
	private int productID;
	private String category;
	private int amount;
	private int orderId;
//	@ManyToOne
//  @JoinColumn(name = "order_id", nullable = false)
//	@JsonBackReference
    private OrderModel order;
}
