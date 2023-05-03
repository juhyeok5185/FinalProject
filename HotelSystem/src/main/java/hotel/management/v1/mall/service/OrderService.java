package hotel.management.v1.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import hotel.management.v1.mall.dao.OrderDao;

@Service
public class OrderService {
	@Autowired
	OrderDao orderDao;

	public void mallOrder(String[] tbodyArray, String pickupDay, String username) {
	}

	
	


}