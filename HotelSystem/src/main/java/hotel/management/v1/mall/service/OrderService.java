package hotel.management.v1.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.mall.dao.OrderDao;
import hotel.management.v1.mall.dto.OrdersDto;
import hotel.management.v1.mall.dto.OrdersDto.OrderList;

@Service
public class OrderService {
	@Autowired
	OrderDao orderDao;

	public void mallOrder(String[] tbodyArray, String pickupDay, String username) {
		String itemName = "";
		int orderEA = 0;
		int price = 0;
		System.out.println("===============");
		for (int i = 0; i < tbodyArray.length; i++) {
			int arrayIdx = tbodyArray[i].indexOf(":") + 1;

			if (i % 3 == 0) {
				itemName = tbodyArray[i].substring(arrayIdx).trim().replaceAll("\"", "");
				System.out.println(itemName);
			} else if (i % 3 == 1) {
				orderEA = Integer.parseInt(tbodyArray[i].substring(arrayIdx).trim().replaceAll("\"", ""));
			} else if (i % 3 == 2) {
				price = Integer.parseInt(tbodyArray[i].substring(arrayIdx).trim().replaceAll("\"|[{}\\[\\]]", ""));
				orderDao.addOrder(itemName, username);
				orderDao.addOrderDetail(price, pickupDay, orderEA);
				orderDao.minusEA(itemName, orderEA);
			}
		}

	}

	public List<OrdersDto.OrderList> findAllOrder(String username) {
		List<OrdersDto.OrderList> list = orderDao.findAllOrder(username);
		return list;
	}

	public OrdersDto.OrderDetail findByOrderNo(Integer orderNo) {
		return orderDao.findByOrderNo(orderNo);
	}

	public Integer orderDelete(Integer orderNo, String itemName, Integer orderEA) {
		orderDao.orderDetailDelete(orderNo);
		orderDao.orderDelete(orderNo);
		orderDao.plusEA(itemName, orderEA);
		return 1;
	}

}