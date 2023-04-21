package hotel.management.v1.client.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.client.book.dao.bookDao;
import hotel.management.v1.client.book.dto.bookDto;
import hotel.management.v1.client.book.dto.bookDto.book;
import hotel.management.v1.client.book.dto.bookDto.dinner;
import hotel.management.v1.client.book.dto.bookDto.dinnerbook;

@Service
public class bookService {
	@Autowired
	private bookDao dao;

	public List<bookDto.findRoom> findRoom(String from, String to) {
		List<bookDto.findRoom> list = dao.findRoomFromAndTo(from, to);

		return list;
	}

	public void add(book book,String pal) {
		// TODO Auto-generated method stub
		bookDto.addbook adbo = new bookDto.addbook(pal, book.getFrom(), book.getTotalcnt(), book.getBooktel(),
				book.getBooker());
		bookDto.addRoomBook adrb = new bookDto.addRoomBook(book.getTo(), book.getBfcheckbox(), book.getGradename(),
				pal);
		dao.addBook(adbo);
		dao.addRoomBooking(adrb);
	}

	public Integer addDinner(dinnerbook book, String name) {
		// 여기서 다른 dto로 합쳐서 보낸다? book테이블 dinner테이블 두게에 보내야 한다 그럼 dto를 합쳐서 다시 갈라치기를 한다?
		bookDto.dinner dinner = new dinner(name, book.getBookpeople(), book.getBookdate(), book.getBooktel(), book.getBooker());
		Integer resno = dao.findByUsernameAndBookdate(dinner.getUsername(),dinner.getBookdate());
		
//		dao.addDinner(dinner);
		
//		dao.addDinnerbook(dinner.getUsername(),resno);
		return resno;
	}

}
