package hotel.management.v1.pay.controller;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hotel.management.v1.mall.service.OrderService;
import hotel.management.v1.pay.entity.KakaoPayApproveVO;
import hotel.management.v1.pay.entity.KakaoPayReadyVo;
import hotel.management.v1.pay.service.PayService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PayController {
	@Autowired
	private PayService payService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/pay")
	public String ready() {
		return "pay/ready";
	}

	@GetMapping("/pay/start")
	public @ResponseBody KakaoPayReadyVo kakaoPay(@RequestParam Map<String, Object> params, HttpSession session,Principal pal) {
		String uuid = UUID.randomUUID().toString();
		KakaoPayReadyVo res = payService.kakaoPay(params, uuid,pal.getName());
		session.setAttribute("partner_order_id", uuid);
		session.setAttribute("tid", res.getTid());
		return res;
	}
	
	
	@GetMapping(value="/pay/success",produces = MediaType.APPLICATION_JSON_VALUE)
	public String Success(@RequestParam("pg_token") String pgToken, HttpSession session,Principal principal) {
		String pickupDay = (String) session.getAttribute("pickupDay");
		String[] tbodyArray = (String[])session.getAttribute("tbodyArray");
		orderService.mallOrder(tbodyArray, pickupDay, principal.getName());
		
		KakaoPayApproveVO res = payService.kakaoPayApprove(pgToken, session,principal.getName());
		session.removeAttribute("tid");
		session.removeAttribute("partner_order_id");
		return "/pay/success";
	}
	
	@GetMapping("/pay/toss_success")
	public String tosssuccess(@RequestParam("orderId") String orderId,@RequestParam("paymentKey") String paymentKey,@RequestParam("amount") Integer amount,Principal principal) {
		payService.tossPayApprove(orderId,paymentKey,amount,principal.getName());
		
		return "/pay/toss_success";
	}
	
	
	@GetMapping("/pay/cancel")
	public String Cancel() {
		return "redirect:/hotel/main";
	}

	@GetMapping("/pay/fail")
	public String Fail() {
		return "/pay/fail";
	}
}
