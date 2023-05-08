package hotel.management.v1.mall.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotel.management.v1.mall.dto.OrdersDto;
import hotel.management.v1.mall.service.OrderService;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/hotel")
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/mall/order")
	public ResponseEntity<?> order(OrdersDto.Order order, Principal principal, HttpSession session) {
		session.setAttribute("tbodyArray", order.getTbodyArray());
		session.setAttribute("pickupDay", order.getPickupDay());
		return ResponseEntity.ok(null);
	} //가은아 이거 restcontroller로 빼라

	@GetMapping("/mall/orderDetail")
	public ModelAndView orderDetail(Integer orderNo) {
		System.out.println(orderNo);
		return new ModelAndView( "/hotel//mall/orderDetail").addObject("orderDetail", orderService.findByOrderNo(orderNo));
	}
		
	@GetMapping("/mall/orderList")
	public String list(Model model, Principal principal, RedirectAttributes re) {
		if (principal == null) {
			re.addFlashAttribute("msg","회원전용 페이지입니다. 로그인을 해주세요.");
			return "redirect:/hotel/member/login";
		}
		model.addAttribute("orderlist", orderService.findAllOrder(principal.getName()));
		return "/hotel/mall/orderList";
	}
	
	@DeleteMapping("/mall/orderDelete")
	public ResponseEntity<?> orderDelete(Integer orderNo) {
		orderService.orderDelete(orderNo);
		return ResponseEntity.ok(null);
	}

}
