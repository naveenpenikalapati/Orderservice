package com.EcomOrderMicroservice.order;

import lombok.Data;

@Data
public class ProductDTO {
	private int productID;
    private String category;
    private int amount;
    private int orderId;
    
	public ProductDTO() 
	{
		
	}
    
    
}
