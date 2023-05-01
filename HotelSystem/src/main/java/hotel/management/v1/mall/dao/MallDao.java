package hotel.management.v1.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hotel.management.v1.mall.dto.OrdersDto.order;
import hotel.management.v1.mall.entity.Item;

@Mapper
public interface MallDao {
	public List<Item> findByUsername(String username);

	public Integer order(order order);
}
