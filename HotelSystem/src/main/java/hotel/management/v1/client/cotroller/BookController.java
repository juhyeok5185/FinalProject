package hotel.management.v1.client.cotroller;

import java.security.Principal;
import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@GetMapping("/manager/book")
	public void managerbook() {
		
	}
	@GetMapping("/client/mydinnerbook")
	public ModelAndView mydinnerbook(Principal pal, ModelAndView mav) {
		List<BookDto.mypagedinner> list = service.findMydinnerByusername(pal.getName());
		return new ModelAndView().addObject("list", list);
	}
	@GetMapping("/client/myroombook")
	public void myroombook() {
	}
	@GetMapping("/client/roombook")
	public String bookPage(Principal pal, RedirectAttributes ra, Model model) {
		if (pal == null) {
			ra.addFlashAttribute("msg", "해당 상품은 멤버십 회원에게만 제공됩니다.");
			return "redirect:/hotel/member/login";
		}
		BookDto.finduser user = service.findByusername(pal.getName());
		model.addAttribute("user", user);
		return "/hotel/client/roombook";
	}

	@GetMapping("/client/roomdetail")
	public ModelAndView roomdetail(String grandname,Integer price,String from,String to) {
		
		return new ModelAndView().addObject("gradename", grandname).addObject("totalprice", price)
				.addObject("from", from).addObject("to",to);
	}
	@GetMapping("/client/dinnerbook")
	public String dinnerPage(Principal pal, Model model, RedirectAttributes ra) {
		if (pal == null) {
			ra.addFlashAttribute("msg", "해당 상품은 멤버십 회원에게만 제공됩니다.");
			return "redirect:/hotel/member/login";
		}
		return "/hotel/client/dinnerbook";
	}

	
	
	
	
	
	@PostMapping("/client/chekin")
	public ResponseEntity<?> chekin(BookDto.book book, Principal pal) {
		service.add(book, pal.getName());
		return ResponseEntity.ok(null);

	}

	@PostMapping(value = "/client/roombook", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<findRoom>> roomBook(String from, String to, HttpSession session, Principal pal) {
		if (service.chekbook(pal.getName(), from, to) != 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		} else {
			List<BookDto.findRoom> dto = null;
			if (session.getAttribute("from") == null && session.getAttribute("to") == null) {
				if (from.equals("") || to.equals("")) {

				} else {
					dto = service.findRoom(from, to);
				}

			} else {
				dto = service.findRoom(session.getAttribute("from").toString(), session.getAttribute("to").toString());
				System.out.println("세션있음");
			}
			return ResponseEntity.ok(dto);
		}
	}

	@PostMapping(value = "/client/dinnerbook", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> dinnerbook(BookDto.addbookfromDinner book, Principal pal) {
		service.addDinner(book, pal.getName());
		return ResponseEntity.ok(null);
	}

	@PostMapping(value = "/client/ckinfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> ckinfo(Principal pal) {
		service.findByusername(pal.getName());
		return ResponseEntity.ok(null);
	}
	

	@PostMapping(value = "/manager/checkroom", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checkroom(BookDto.managercheckroom room){
		List<BookDto.findRoom> roomlist = service.findRoom(room.getFrom(), room.getTo());
		 
		return roomlist!=null? ResponseEntity.ok(roomlist):ResponseEntity.status(HttpStatus.CONFLICT).body(null);

	}
	
	@PostMapping("/manager/checkbookbyusername")
	public ResponseEntity<?> checkbookbyusername(BookDto.checkbookbyusername check){
		try {
			System.out.println(check.toString());
			if (service.chekbook(check.getUsername(),check.getFrom(),check.getTo())!=0) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}
			return ResponseEntity.ok(null);
		} catch (TooManyResultsException e) {
			System.out.println(e.getStackTrace());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
	}
	
	@PostMapping(value = "/client/myinfo",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> myinfo(Principal pal,String username) {
		BookDto.myInFo info = null;
		if (username !=null) {
			 info = service.myinfoByUsername(username);
		}
		if (pal!=null) {
			info = service.myinfoByUsername(pal.getName());
		}
		return ResponseEntity.ok(info);
	}
	
	
	@PostMapping("/manager/checkin")
	public ResponseEntity<?> managercheckin(BookDto.managerbook book){
		System.out.println(book.toString());
		BookDto.book bo = new BookDto.book(book.getFrom(), book.getTo(), book.getTotalcnt(), book.getGradename(), book.getBfcheckbox(),
				book.getDicheckbox(), book.getBooker(),book.getBooktel());
		service.add(bo, book.getUsername());
		return ResponseEntity.ok(null);
		
	}
}
