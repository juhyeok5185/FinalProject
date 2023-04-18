package hotel.management.v1.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.client.book.dao.bookDao;
import hotel.management.v1.client.book.dto.bookDto;

@Service
public class bookService {
	@Autowired
	private bookDao dao;
	
	public List<bookDto.findRoom> findRoom(String from, String to) {
		System.out.println("서비스호출");
		List<bookDto.findRoom> list =  dao.findRoomFromAndTo();
		
		return list;
	}

}
