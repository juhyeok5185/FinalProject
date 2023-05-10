package hotel.management.v1.pay.dao;

import org.apache.ibatis.annotations.Mapper;

import hotel.management.v1.pay.dto.PayDto.bookAddPayment;
import hotel.management.v1.pay.entity.TossPayVo;

@Mapper
public interface PayDao {
	

	public Integer kakaobookadd(bookAddPayment bookpayment);

	public Integer paymenttoss(bookAddPayment bookpayment);

}
