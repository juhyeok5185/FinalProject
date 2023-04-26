package hotel.management.v1.client.cotroller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotel.management.v1.client.book.dto.BookDto;
import hotel.management.v1.client.book.dto.BookDto.findRoom;
import hotel.management.v1.client.service.BookService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/hotel")
public class BookController {
	@Autowired
	private BookService service;

	@GetMapping("/client/roombook")
	public String bookPage(Principal pal,RedirectAttributes ra,Model model) {
		if(pal==null) {
			ra.addFlashAttribute("msg", "앙 기모띠");
			return "redirect:/hotel/member/login";
		}
		BookDto.finduser user= service.findByusername(pal.getName());
		model.addAttribute("user", user);
		return "/hotel/client/roombook";
	}
	

	@GetMapping("/client/dinnerbook")
	public void dinnerPage() {
	}

	@PostMapping("/client/chekin")
	public ResponseEntity<?> chekin(BookDto.book book, Principal pal) {
		service.add(book, pal.getName());
		return ResponseEntity.ok(null);
	}

	@PostMapping(value = "/client/roombook", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<findRoom>> roomBook(String from, String to) {
		List<BookDto.findRoom> dto = null;
		if (from.equals("") || to.equals("")) {

		} else {
			dto = service.findRoom(from, to);
		}

		return ResponseEntity.ok(dto);
	}

	@PostMapping(value = "/client/dinnerbook", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> dinnerbook(BookDto.dinnerbook book, Principal pal) {

		service.addDinner(book, pal.getName());
		return ResponseEntity.ok(null);
	}

	@PostMapping(value = "/client/ckinfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> ckinfo(Principal pal) {
		service.findByusername(pal.getName());
		return ResponseEntity.ok(null);
	}

}
