package hotel.management.v1.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hotel.management.v1.member.dto.MemberDto;
import hotel.management.v1.member.service.MemberService;
import jakarta.validation.Valid;

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
    public void login() {}
    
    
    @GetMapping("/member/joincomplete")
    public void joinComplete() {
    	
    }
    
    @PreAuthorize("isAnonymous()")
	@GetMapping("/member/check/username")
	public ResponseEntity<Void> checkUsername(String username) {
		Boolean result = service.checkUsername(username);
		return result? ResponseEntity.ok(null) :ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}
	
	@PreAuthorize("isAnonymous()")
	@GetMapping("/member/check/email")
	public ResponseEntity<Void> emailUsername(String email) {
		Boolean result = service.checkEmail(email);
		return result? ResponseEntity.ok(null) :ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/member/myPage")
	public void myPage() {
		
	}
	
	@PreAuthorize("isAnonymous()")
	@GetMapping("/member/find_id")
	public ResponseEntity<String> findId(String name, String email) {
		return ResponseEntity.ok(service.findUsername(name,email));
	}
	
	@PreAuthorize("isAnonymous()")
	@PatchMapping("/member/find_password")
	public ResponseEntity<String> findPassword(String name, String username, String email) {
		service.resetPassword(name, username, email);
		return ResponseEntity.ok("임시비밀번호를 이메일로 보냈습니다");
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/member/profileupdate")
	public void profileUpdate() {
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/member/changepassword")
	public void changePassword() {
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/member/delete")
	public void delete() {
		
	}
	
}
