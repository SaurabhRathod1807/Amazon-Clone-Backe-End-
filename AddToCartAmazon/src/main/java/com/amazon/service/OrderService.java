package com.amazon.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.entity.Cart;
import com.amazon.entity.CartDetails;
import com.amazon.entity.Order;
import com.amazon.entity.OrderDetails;
import com.amazon.entity.Product;
import com.amazon.repo.OrderRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private ProductDetailsProxy productDetailsProxy;
	
	public void addtoOrder(Order order) {
		orderRepo.save(order);
	}
	
	public OrderDetails displayAllProductInorder(String userId) {

		
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setUserId(userId);

		ArrayList<Order> orderList = (ArrayList<Order>) orderRepo.findByUserId(userId);
		ArrayList<Product> proList = new ArrayList<Product>();
		System.out.println(orderList.get(0).getQuantity());
		for (int i = 0; i < orderList.size(); i++) {
			Product product = productDetailsProxy.getProductDetails(orderList.get(i).getProductId());
//			product =orderList.get(i).getQuantity();
			proList.add(product);
		}

		orderDetails.setList(proList);
		return orderDetails;

	}
	
	@Transactional
	public void remove(String userId, String productId) {
		orderRepo.deleteByUserIdAndProductId(userId, productId);
	}
	
	public Optional<Order> getOrder(Long id) {
	return	orderRepo.findById(id);
	}
}
