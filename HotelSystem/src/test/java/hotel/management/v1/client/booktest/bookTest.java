package hotel.management.v1.client.booktest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hotel.management.v1.client.book.dao.bookDao;
import hotel.management.v1.client.book.dto.bookDto;
import hotel.management.v1.client.book.dto.bookDto.addRoomBook;
import hotel.management.v1.client.book.dto.bookDto.addbook;

@SpringBootTest
public class bookTest {
	@Autowired
	bookDao dao;
	String[] username = { "cpsua97", "juhyeok518", "kkk0001", "spring11", "spring111", "spring51" };
	String[] booker = { "이가은", "찢주성", "개주성", "유혹주성", "미르행님", "리카른"};
	String[] booktel = { "01083001234", "01055220585", "01045672258", "01055889977", "01022331692", "01030462259" };

	@Test
	public void name() {
		
		for (int i = 0; i < username.length; i++) {
			bookDto.addbook book = new addbook(username[i], "04/2"+i+"/2023", 5, booktel[i], booker[i]);
			System.out.println(book.toString());
			dao.addBook(book);
			bookDto.addRoomBook rb = new addRoomBook("04/2"+(i+5)+"/2023", true, "스탠다드", book.getUsername());
			System.out.println(rb.toString());
			dao.addRoomBooking(rb);
		}
	}
}
