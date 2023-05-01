package hotel.management.v1.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.mall.dao.OrderDao;
import hotel.management.v1.mall.dto.OrdersDto.order;

@Service
public class OrderService {
	@Autowired
	OrderDao dao;

	public void order(order order, String username) {
		dao.order(order.getItemName(),username);
//		dao.orderdetail();
	}
	
}
