package hotel.management.v1.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.joran.conditional.IfAction;
import hotel.management.v1.member.dto.MemberDto;
import hotel.management.v1.member.service.MemberService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/hotel")
public class MemberController {
	@Autowired
	private MemberService service;
	
	@PreAuthorize("isAnonymous()")
    @GetMapping("/member/join")
    public void join() {
    	
    }
    
    @PreAuthorize("isAnonymous()")
	@PostMapping("/member/join")
	public String join(MemberDto.Join dto) {
    	service.join(dto);
		return "redirect:/hotel/member/joincomplete";
    }
    
    @PreAuthorize("isAnonymous()")
    @GetMapping("/member/login")
    public void login(HttpSession session, Model model) {
    	if (session.getAttribute("msg")!=null) {
			model.addAttribute("msg", session.getAttribute("msg"));
			session.removeAttribute("msg");
		}
    }
    	
    
    @GetMapping("/member/find")
    public void find() {
    	
    }
    
    @GetMapping("/member/joincomplete")
    public void joinComplete() {
    	
    }
	
}
