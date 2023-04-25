package hotel.management.v1.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.client.book.dao.BookDao;
import hotel.management.v1.client.book.dto.BookDto;
import hotel.management.v1.client.book.dto.BookDto.book;
import hotel.management.v1.client.book.dto.BookDto.dinner;
import hotel.management.v1.client.book.dto.BookDto.dinnerbook;

@Service
public class BookService {
	@Autowired
	private BookDao dao;

	public List<BookDto.findRoom> findRoom(String from, String to) {
		List<BookDto.findRoom> list = dao.findRoomFromAndTo(from, to);

		return list;
	}

	public void add(book book, String pal) {
		// TODO Auto-generated method stub
		BookDto.addRoomBook rb = new BookDto.addRoomBook(book.getTo(), book.getBfcheckbox(), book.getGradename(), pal);
		BookDto.addbook ab = new BookDto.addbook(pal, book.getFrom(), book.getTotalcnt(), book.getBooktel(),
				book.getBooker());

		dao.addBook(ab);
		dao.addRoomBooking(rb);
		BookDto.whtyouname name = new BookDto.whtyouname(pal, book.getBooktel(), book.getBooker(), book.getFrom(),
				book.getTo());
		BookDto.data data = dao.findBookdata(name);
		if (book.getDicheckbox()) {
			System.out.println(data.toString());
			dao.addDinner(data);
		}
	}

	public Integer addDinner(dinnerbook book, String name) {
		// 여기서 다른 dto로 합쳐서 보낸다? book테이블 dinner테이블 두게에 보내야 한다 그럼 dto를 합쳐서 다시 갈라치기를 한다?
		BookDto.dinner dinner = new dinner(name, book.getBookpeople(), book.getBookdate(), book.getBooktel(),
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
