package hotel.management.v1.member.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotel.management.v1.member.dto.MemberDto;
import hotel.management.v1.member.entity.Member;
import hotel.management.v1.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	public void login(HttpSession session,Model model) {
		if(session.getAttribute("msg")!=null) {
			model.addAttribute("msg",session.getAttribute("msg"));
			session.removeAttribute("msg");
		}
	}
    
    
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
		public ModelAndView myPage(Principal principal) {
		MemberDto.Read dto = service.read(principal.getName());
		return new ModelAndView("/hotel/member/myPage").addObject("member", dto);
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
	@GetMapping("/member/changepassword")
	public void changePassword(HttpSession session, Model model) {
		if (session.getAttribute("msg")!=null) {
			model.addAttribute("msg", session.getAttribute("msg"));
			session.removeAttribute("msg");
		}
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/member/changepassword")
	public String changePassword(String newpassword, Principal principal) {
		service.changePassword(newpassword, principal.getName());
		return "redirect:/hotel/member/myPage";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/member/delete")
	public String delete(SecurityContextLogoutHandler handler, HttpServletRequest req, HttpServletResponse res, Authentication auth, RedirectAttributes ra) {
			service.delete(auth.getName());
			handler.logout(req, res, auth);
			ra.addFlashAttribute("msg", "감사합니다. 꼭 다시 한번 뵙고 싶습니다.");
			return "redirect:/hotel/main";
	}
	
		@PreAuthorize("isAuthenticated()")
		@GetMapping("/member/profileupdate")
		public ModelAndView checkPassword(HttpSession session) {
			if(session.getAttribute("isPasswordCheck")!=null)
				return new ModelAndView("redirect:/hotel/member/read");
			return new ModelAndView("/hotel/member/profileupdate");
		}
		
		@PreAuthorize("isAuthenticated()")
		@PostMapping("/member/profileupdate")
		public String checkPassword(String password, Principal principal, HttpSession session, RedirectAttributes ra) {
			if(session.getAttribute("isPasswordCheck")!=null)
				return "redirect:/hotel/member/read";
			Boolean result = service.checkPassword(password, principal.getName());
			if(result==true) {
				session.setAttribute("isPasswordCheck", true);
				return "redirect:/hotel/member/read";
			} else {
				ra.addFlashAttribute("msg", "비밀번호를 잘못 입력하셨습니다.");
				return "redirect:/hotel/member/profileupdate";
			}
		}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/member/read")
	public ModelAndView read(Principal principal, HttpSession session) {
		if (session.getAttribute("isPasswordCheck")==null) {
			return new ModelAndView("redirect:/hotel/member/profileupdate");
		}
		MemberDto.Read dto = service.read(principal.getName());
		return new ModelAndView("/hotel/member/read").addObject("member", dto);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/member/update")
	public ResponseEntity<Void> update(String email, String tel, Principal principal) {
		Boolean result = service.update(email, tel, principal.getName());
		return result? ResponseEntity.ok(null):
			ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/member/reservation")
	public void reservation() {
		
	}
	
	
}
