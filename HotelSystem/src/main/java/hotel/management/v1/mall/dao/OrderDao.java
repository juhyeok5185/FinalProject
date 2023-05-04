package hotel.management.v1.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import hotel.management.v1.mall.dto.OrdersDto;
import hotel.management.v1.mall.dto.OrdersDto.OrderDetail;
import hotel.management.v1.mall.dto.OrdersDto.OrderList;
import hotel.management.v1.mall.entity.Item;

@Mapper
public interface OrderDao {
	
	public Integer addOrder(String name, String username);
	
	public Integer addOrderDetail(Integer price, String pickupDay, Integer count);

	public List<OrderList> findAllOrder(String username);

	public OrdersDto.OrderDetail findByOrderNo(Integer orderNo);
	
	public Boolean orderDelete(Integer orderNo);
	
	public Boolean orderDetailDelete(Integer orderNo);
	

}