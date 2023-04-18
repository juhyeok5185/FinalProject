package hotel.management.v1.client.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hotel.management.v1.client.book.dto.bookDto;

@Mapper
public interface bookDao {
	
	public List<bookDto.findRoom> findRoomFromAndTo(String from, String to);

}
