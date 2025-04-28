package com.EcomOrderMicroservice.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepo extends JpaRepository<OrderModel,Integer>
{

	OrderModel findByOrderQuantity(int orderQuantity);
	
	 @Query("SELECT o FROM OrderModel o WHERE o.orderQuantity = (SELECT MAX(o2.orderQuantity) FROM OrderModel o2)")
	  OrderModel findByHighOrders();
//	 
//	 @Query("SELECT DISTINCT o FROM OrderModel o JOIN o.products p WHERE p.category = :category")
//		List<OrderModel> findByProductsCategory(@Param("category") String category);

	void save(OrderUpdateDTO dto);


	//String deleteAllById(List<Integer> orderid);

}
