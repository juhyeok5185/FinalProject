package hotel.management.v1.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.mall.dao.OrderDao;

@Service
public class OrderService {
	@Autowired
	OrderDao dao;

	public void mallOrder(String[] tbodyArray, String pickupDay, String username) {
		System.out.println("=========================================");
		for (int i = 0; i < tbodyArray.length; i++) {
			String name = "";
			String count = "";
			String price = "";
			
			if(i%3 == 0) { //이름
				name = tbodyArray[i];
			} else if(i%3 == 1) { // 수
				count = tbodyArray[i];
			} else if(i%3 == 2) { // 가격
				price = tbodyArray[i];
				//db에 업데이트
				
			}
 
		}
	}


}