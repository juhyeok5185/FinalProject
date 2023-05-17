package hotel.management.v1.main;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/hotel")
public class MainController {
    
    @GetMapping("/main")
    public ModelAndView viewMain(HttpSession session,Model model){
    	if(session.getAttribute("msg")!=null) {
			String msg= (String)session.getAttribute("msg");
			session.removeAttribute("msg");
			return new ModelAndView().addObject("msg", msg);
		}
    	return new ModelAndView();
    }

    @GetMapping("/contact")
    public void contact(){}
    
    @GetMapping("/hotelintroduce")
    public void hotelIntroduce(){}
    
    @GetMapping("/hotelstay")
    public void hotelStay(){}
    
    @GetMapping("/internalfacilities")
    public void internalFacilities(){}
    
    @GetMapping("/report")
    public void report(){}
    
    @GetMapping("/luxuryhall")
    public void luxuryHall(){}
    
    @GetMapping("/howtocome")
    public void howToCome(){}
    
    @GetMapping("/test")
    public void index(){}
    
    // 예약완료 페이지 불러오는 메소드
 	@PreAuthorize("isAuthenticated()")
 	@GetMapping("/reservationcomplete")
 	public void reservationComplete() {}
 	
 	// 다이닝완료 페이지 불러오는 메소드
 	@PreAuthorize("isAuthenticated()")
 	@GetMapping("/diningreservationcomplete")
 	public void diningReservationComplete() {}
 	
 	// 명품관주문완료 페이지 불러오는 메소드
 	@PreAuthorize("isAuthenticated()")
 	@GetMapping("/luxurymallcomplete")
 	public void luxuryMallComplete() {}
}
