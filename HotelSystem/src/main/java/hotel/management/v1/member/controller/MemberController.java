package hotel.management.v1.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hotel.management.v1.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService service;
	
	@PreAuthorize("isAnonymous()")
    @GetMapping("/join")
    public void join() {
    	
    }
    
    @PreAuthorize("isAnonymous()")
	@PostMapping("/member/join")
	public void postjoin() {
    	
    }
    
    @GetMapping("/login")
    public void login() {}
	
}
