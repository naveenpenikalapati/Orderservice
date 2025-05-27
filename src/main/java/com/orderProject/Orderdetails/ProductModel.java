package com.orderProject.Orderdetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ProductModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productID;
	private String category;
	private int amount;
	
	@ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
	@JsonBackReference
    private OrderModel order;
}
