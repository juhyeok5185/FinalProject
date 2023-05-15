package hotel.management.v1.client.cotroller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotel.management.v1.client.book.dto.BookDto;
import hotel.management.v1.client.service.BookService;

@Controller
@RequestMapping("/hotel")
public class BookMVCController {
	@Autowired
	BookService service;

	@GetMapping("/manager/book")
	public void managerbook() {

	}

	@GetMapping("/client/mydinnerbook")
	public ModelAndView mydinnerbook(Principal pal) {

		return new ModelAndView().addObject("list",service.findMydinnerByusername(pal.getName()));
	}

	@GetMapping("/client/myroombook")
	public ModelAndView myroombook(Principal pal) {
		List<BookDto.mypagedinner> list = service.findMyBookByUsername(pal.getName());
		return new ModelAndView().addObject("list", list);
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
	public ModelAndView roomdetail(String grandname, Integer price, String from, String to) {

		return new ModelAndView().addObject("gradename", grandname).addObject("totalprice", price)
				.addObject("from", from).addObject("to", to);
	}

	@GetMapping("/client/dinnerbook")
	public String dinnerPage(Principal pal, Model model, RedirectAttributes ra) {
		if (pal == null) {
			ra.addFlashAttribute("msg", "해당 상품은 멤버십 회원에게만 제공됩니다.");
			return "redirect:/hotel/member/login";
		}
		return "/hotel/client/dinnerbook";
	}

}
