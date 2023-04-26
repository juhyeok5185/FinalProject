package hotel.management.v1.pay.dao;

import org.apache.ibatis.annotations.Mapper;

import hotel.management.v1.pay.entity.TossPayVo;

@Mapper
public interface PayDao {
	
	public Integer test();

	public void bookadd(String tid, String uuid,String username);

	public void tossbookAdd(TossPayVo tp);
}
