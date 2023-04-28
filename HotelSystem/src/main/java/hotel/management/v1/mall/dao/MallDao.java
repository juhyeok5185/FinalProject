package hotel.management.v1.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import hotel.management.v1.mall.entity.Item;

@Mapper
public interface MallDao {
	@Select("select * from orders where username=#{username}")
	public List<Item> findByUsername(String username);
}
