package hotel.management.v1.mall.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotel.management.v1.mall.service.OrderService;

@RequestMapping("/hotel")
//@Secured("ROLE_USER")
@Controller
public class OrderController {
//	@Autowired
//	private OrderService orderService;
	
	@GetMapping("/mall/orderList")
	public void order() {
		
	}
	
	@GetMapping("/mall/orderDetail")
	public void orderDetail() {
		// TODO Auto-generated method stub

	}
	
	
//	@GetMapping("/mall/orderList")
//	public String list(Model model, Principal principal, RedirectAttributes re) {
//		if (principal == null) {
//			re.addFlashAttribute("msg","회원전용 페이지입니다. 로그인을 해주세요.");
//			return "redirect:/hotel/member/login";
//		}
//		return "redirect:/hotel/mall/orderList";
//	}
}
