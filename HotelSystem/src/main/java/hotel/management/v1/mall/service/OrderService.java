package hotel.management.v1.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.mall.dao.OrderDao;
import hotel.management.v1.mall.dto.OrdersDto;

@Service
public class OrderService {
	@Autowired
	OrderDao orderDao;
	
	public void mallOrder(String[] tbodyArray, String pickupDay, String username) {
		String name = "";
		int count = 0;
		int price = 0;
		
		for (int i = 0; i<tbodyArray.length; i++) {
			int arrayIdx = tbodyArray[i].indexOf(":")+ 1;
	
			if (i%3==0) {
				name = tbodyArray[i].substring(arrayIdx).trim().replaceAll("\"", "");
			} else if (i%3==1) {
				count = Integer.parseInt(tbodyArray[i].substring(arrayIdx).trim().replaceAll("\"", ""));
			} else if (i%3==2) {
				price = Integer.parseInt(tbodyArray[i].substring(arrayIdx).trim().replaceAll("\"|[{}\\[\\]]", ""));
				orderDao.addOrder(name, username);
				orderDao.addOrderDetail(price, pickupDay, count);
			}
		}

		
	}

	public List<OrdersDto.OrderList> findAllOrder(String username) {
		return orderDao.findAllOrder(username);
	}

	public OrdersDto.OrderDetail findByOrderNo(Integer orderNo) {
		return orderDao.findByOrderNo(orderNo);
	}

	public Integer orderDelete(Integer orderNo) {
		orderDao.orderDetailDelete(orderNo);
		orderDao.orderDelete(orderNo);
		return 1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}