package hotel.management.v1.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import hotel.management.v1.mall.dto.OrdersDto;
import hotel.management.v1.mall.dto.OrdersDto.OrderList;

@Mapper
public interface OrderDao {
	
	public Integer addOrder(String name, String username);
	
	public Integer addOrderDetail(Integer price, String pickupDay, Integer count);

	public List<OrderList> findAllOrder(String username);

	public OrdersDto.OrderDetail findByOrderNo(Integer orderNo);
	
	public Boolean orderDelete(Integer orderNo);
	
	public Boolean orderDetailDelete(Integer orderNo);

	public void minusEA(String name, int count);
	

}