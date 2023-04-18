package hotel.management.v1.client.cotroller;

import java.util.List;
import java.util.Map;

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
	
	@PostMapping(value="/client/roombook", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<findRoom>> roomBook(String from, String to) {
		System.out.println("컨트롤러호출");
		List<bookDto.findRoom> dto =   service.findRoom(from,to);
		
		return ResponseEntity.ok(dto);
	}
		
}
