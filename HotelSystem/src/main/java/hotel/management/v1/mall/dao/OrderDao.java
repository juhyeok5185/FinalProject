package hotel.management.v1.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hotel.management.v1.mall.dto.OrdersDto;
import hotel.management.v1.mall.dto.OrdersDto.OrderList;
import hotel.management.v1.mall.entity.Item;

@Mapper
public interface OrderDao {
	
	public Integer addOrder(String name, String username);
	
	public Integer addOrderDetail(Integer price, String pickupDay, Integer count);

	public List<OrderList> findAllOrder(String username);
	

}