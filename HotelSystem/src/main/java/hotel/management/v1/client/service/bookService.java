package hotel.management.v1.client.service;

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

	public void add(book book, String pal) {
		// TODO Auto-generated method stub
		bookDto.addRoomBook rb = new bookDto.addRoomBook(book.getTo(), book.getBfcheckbox(), book.getGradename(), pal);
		bookDto.addbook ab = new bookDto.addbook(pal, book.getFrom(), book.getTotalcnt(), book.getBooktel(),
				book.getBooker());
		System.out.println(rb.toString());
		System.out.println(ab.toString());

		dao.addBook(ab);
		dao.addRoomBooking(rb);
		bookDto.whtyouname name = new bookDto.whtyouname(pal, book.getBooktel(), book.getBooker(), book.getFrom(),
				book.getTo());
		bookDto.data data = dao.findBookdata(name);
		if (book.getDicheckbox()) {
			System.out.println(data.toString());
			dao.addDinner(data);
		}
	}

	public Integer addDinner(dinnerbook book, String name) {
		// 여기서 다른 dto로 합쳐서 보낸다? book테이블 dinner테이블 두게에 보내야 한다 그럼 dto를 합쳐서 다시 갈라치기를 한다?
		bookDto.dinner dinner = new dinner(name, book.getBookpeople(), book.getBookdate(), book.getBooktel(),
				book.getBooker());
		Integer resno = dao.findByUsernameAndBookdate(dinner.getUsername(), dinner.getBookdate());

//		dao.addDinner(dinner);

//		dao.addDinnerbook(dinner.getUsername(),resno);
		return resno;
	}

	public void findByusername(String name) {
		// TODO Auto-generated method stub
		dao.findByusername(name);
	}

}
