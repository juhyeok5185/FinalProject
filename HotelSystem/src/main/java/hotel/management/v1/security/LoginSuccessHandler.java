package hotel.management.v1.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import hotel.management.v1.member.dao.MemberDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		if(authentication.getAuthorities().stream()
		.anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))){
			String redirectUrl = request.getContextPath() + "/hotel/manager/bookList";
   			response.sendRedirect(redirectUrl);
    		return;
		}

		
		
		
		response.sendRedirect("/hotel/main");
		memberDao.resetLoginCnt(authentication.getName());
		
		String redirectUrl = "/";
		
		SavedRequest sr = new HttpSessionRequestCache().getRequest(request, response);
		HttpSession session = request.getSession();
	
		// 임시비밀번호인 경우 비밀번호 변경으로 보내자
//		if (sr!=null) {
//			redirectUrl = sr.getRedirectUrl();
//		} else if(session.getAttribute("referer")!=null) {
//			redirectUrl = (String)session.getAttribute("referer");
//			session.removeAttribute("referer");
//		}
//		
//		String password = request.getParameter("password");
//		if (password.length()<=10) {
//			response.sendRedirect(redirectUrl);
//		} else {
//			session.setAttribute("msg", "임시비밀번호로 로그인했습니다. 비밀번호를 변경하세요");
//			response.sendRedirect("/member/change_password");
//		}
//		if(password.length()<=10) {
//			if(sr!=null) 
//				response.sendRedirect(sr.getRedirectUrl());
//			else
//				response.sendRedirect("/");
//		} else {
//			HttpSession session = request.getSession();
//			session.setAttribute("msg", "임시비밀번호로 로그인했습니다. 비밀번호를 변경하세요");
//			response.sendRedirect("/member/change_password");
//		}
	}
}






