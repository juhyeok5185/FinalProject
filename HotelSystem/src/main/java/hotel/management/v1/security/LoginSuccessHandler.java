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
		response.sendRedirect("/hotel/main");
		// 로그인에 성공하면 로그인 실패횟수 리셋
		memberDao.resetLoginCnt(authentication.getName());
		
		// 가려던 곳이 있는 경우 그곳을 얻어오자
		// write를 선택해서 로그인 화면이 나타났다면 로그인 후 /write로 이동해야한다
		// 이동해야 할 주소를 가지는 객체가 savedRequest
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






