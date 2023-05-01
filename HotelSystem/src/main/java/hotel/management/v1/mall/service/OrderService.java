package hotel.management.v1.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.mall.dao.OrderDao;
import hotel.management.v1.mall.dto.OrdersDto.Order;

@Service
public class OrderService {
	@Autowired
	OrderDao dao;

	public void order(Order order, String username) {
		dao.addOrder(order.getItemName(),username);
		dao.addOrderDetail(order.getItemPrice(),order.getPickupDay());
	}
	
}
