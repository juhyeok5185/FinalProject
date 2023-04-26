package hotel.management.v1.pay.controller;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hotel.management.v1.pay.entity.KakaoPayApproveVO;
import hotel.management.v1.pay.entity.KakaoPayReadyVo;
import hotel.management.v1.pay.entity.TossPayVo;
import hotel.management.v1.pay.service.PayService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PayController {
	@Autowired
	private PayService payService;

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

	@GetMapping("/pay/success")
	public String Success(@RequestParam("pg_token") String pgToken, HttpSession session) {
		KakaoPayApproveVO res = payService.kakaoPayApprove(pgToken, session);
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
		return "redirect:/pay";
	}

	@GetMapping("/pay/fail")
	public String Fail() {
		return "/pay/fail";
	}
}
