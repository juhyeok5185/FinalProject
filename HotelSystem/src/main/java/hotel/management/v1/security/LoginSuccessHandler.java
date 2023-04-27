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
		
	}
}






