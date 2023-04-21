package hotel.management.v1.client.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hotel.management.v1.client.book.dto.bookDto;
import hotel.management.v1.client.book.dto.bookDto.addRoomBook;
import hotel.management.v1.client.book.dto.bookDto.addbook;
import hotel.management.v1.client.book.dto.bookDto.book;
import hotel.management.v1.client.book.dto.bookDto.dinner;

@Mapper
public interface bookDao {
	
	public List<bookDto.findRoom> findRoomFromAndTo(String from, String to);

	public Integer addBook(addbook adbo);

	public Integer addRoomBooking(addRoomBook adrb);

	public Integer addDinner(dinner dinner);

	public Integer addDinnerbook(String username, Integer resno);

	public Integer findByUsernameAndBookdate(String username, String bookdate);
	




}
