package hotel.management.v1.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hotel.management.v1.mall.dto.OrdersDto.order;
import hotel.management.v1.mall.entity.Item;

@Mapper
public interface OrderDao {
	public List<Item> findByUsername(String username);

	public Integer order(String itemname,String username);
}
