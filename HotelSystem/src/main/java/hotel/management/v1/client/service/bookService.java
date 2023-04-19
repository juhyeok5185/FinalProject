package hotel.management.v1.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.client.book.dao.bookDao;
import hotel.management.v1.client.book.dto.bookDto;
import hotel.management.v1.client.book.dto.bookDto.book;

@Service
public class bookService {
	@Autowired
	private bookDao dao;
	
	public List<bookDto.findRoom> findRoom(String from, String to) {
		List<bookDto.findRoom> list =  dao.findRoomFromAndTo(from,to);
		
		return list;
	}

	public void add(book book) {
		// TODO Auto-generated method stub
		bookDto.addbook adbo = new bookDto.addbook(book.getUsername(),book.getFrom(),book.getTotalcnt(),book.getBooktel(),book.getBooker());
		bookDto.addRoomBook adrb = new bookDto.addRoomBook(book.getTo(), book.getBfcheckbox(),book.getGradename(),book.getUsername());
		dao.addBook(adbo);
		dao.addRoomBooking(adrb);
		}

}
