package hotel.management.v1.client.cotroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hotel.management.v1.client.book.dto.bookDto;
import hotel.management.v1.client.book.dto.bookDto.findRoom;
import hotel.management.v1.client.service.bookService;

@Controller
@RequestMapping("/hotel")
public class bookController {
	@Autowired
	private bookService service;

	@GetMapping("/client/roombook")
	public void bookPage() {
	}

	@PostMapping("/client/chekin")
	public ResponseEntity<?> chekin(bookDto.book book) {
		service.add(book);
		return ResponseEntity.ok(null);
	}

	@PostMapping(value = "/client/roombook", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<findRoom>> roomBook(String from, String to) {
		System.out.println(from + "+" + to);
		List<bookDto.findRoom> dto = null;
		if (from.equals("") || to.equals("")) {

		} else {
			dto = service.findRoom(from, to);
		}
		return ResponseEntity.ok(dto);
	}

}
