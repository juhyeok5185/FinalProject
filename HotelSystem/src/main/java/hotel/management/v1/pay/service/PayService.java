package hotel.management.v1.pay.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import hotel.management.v1.pay.dao.PayDao;
import hotel.management.v1.pay.entity.KakaoPayApproveVO;
import hotel.management.v1.pay.entity.KakaoPayReadyVo;
import jakarta.servlet.http.HttpSession;

@Service
public class PayService {
	@Autowired
	private PayDao dao;
	
	public KakaoPayReadyVo kakaoPay(Map<String, Object> params, String uuid,String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK a9f2ff79213b981cd3e1ade179808e25");

		MultiValueMap<String, Object> payParams = new LinkedMultiValueMap<String, Object>();

		payParams.add("cid", "TC0ONETIME");
		payParams.add("partner_order_id", uuid);
		payParams.add("partner_user_id", "kakaopayTest");
		payParams.add("item_name", params.get("item_name"));
		payParams.add("quantity", params.get("quantity"));
		payParams.add("total_amount", params.get("total_amount"));
		payParams.add("tax_free_amount", params.get("tax_free_amount"));
		payParams.add("approval_url", "http://localhost:8081/pay/success"); // 성공하면 예약성공페이지로
		payParams.add("cancel_url", "http://localhost:8081/pay/cancel"); // 취소시 예약페이지
		payParams.add("fail_url", "http://localhost:8081/pay/fail"); // 실패시 예약페이지

		HttpEntity<Map> request1 = new HttpEntity<Map>(payParams, headers);
		
		RestTemplate template = new RestTemplate();
		String url = "https://kapi.kakao.com/v1/payment/ready";
		KakaoPayReadyVo res = template.postForObject(url, request1, KakaoPayReadyVo.class);
		//여기에다가 db에 저장하는 문법 만들기
		dao.bookadd(res.getTid(),uuid,username);
		return res;
	}

	public KakaoPayApproveVO kakaoPayApprove(String pgToken, HttpSession session) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK a9f2ff79213b981cd3e1ade179808e25");
		headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, Object> payParams = new LinkedMultiValueMap<String, Object>();
		/* tid 불러오는 로직 추가 및 결제번호는 준비 api와 일치해야함 */
		payParams.add("cid", "TC0ONETIME");
		payParams.add("tid", session.getAttribute("tid"));
		payParams.add("partner_order_id", session.getAttribute("partner_order_id"));
		payParams.add("partner_user_id", "kakaopayTest");
		payParams.add("pg_token", pgToken);

		HttpEntity<Map> request = new HttpEntity<Map>(payParams, headers);

		RestTemplate template = new RestTemplate();
		String url = "https://kapi.kakao.com/v1/payment/approve";

		KakaoPayApproveVO res = template.postForObject(url, request, KakaoPayApproveVO.class);
		System.out.println(res.getApproved_at());
		System.out.println(res.getAid());
		
		return res;
	}

}