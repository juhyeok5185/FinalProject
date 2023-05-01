package hotel.management.v1.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.mall.dao.MallDao;
import hotel.management.v1.mall.dto.OrdersDto.order;

@Service
public class OrderService {
	@Autowired
	MallDao dao;

	public void order(order order) {
		dao.order(order);
		
	}
}
