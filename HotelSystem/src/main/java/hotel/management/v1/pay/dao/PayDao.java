package hotel.management.v1.pay.dao;

import org.apache.ibatis.annotations.Mapper;

import hotel.management.v1.client.book.dto.BookDto.book;
import hotel.management.v1.pay.dto.PayDto.bookAddPayment;

@Mapper
public interface PayDao {
	

	public Integer kakaobookadd(bookAddPayment bookpayment);

	public Integer paymenttoss(bookAddPayment bookpayment);

	public Integer mangeradd(book book);

}
