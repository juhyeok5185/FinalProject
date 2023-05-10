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
public class BookRestController {
	@Autowired
	private BookService service;
	
	@PostMapping("/client/chekin")
	public ResponseEntity<?> chekin(BookDto.book book, Principal pal,HttpSession httpSession) {
		service.add(book, pal.getName());
		httpSession.setAttribute("gradename",book.getGradename());
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
