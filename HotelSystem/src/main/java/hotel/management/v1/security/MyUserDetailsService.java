package hotel.management.v1.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hotel.management.v1.member.dao.MemberDao;
import hotel.management.v1.member.entity.Member;

// 빈을 주입하려고 스프링이 찾는 방법 : by type -> 같은 타입이 여러개면 어떤 것을 주입해야 할지 결정을 못한다 -> exception
// 타입이 겹칠 경우 이름으로 주입할 빈을 지정할 수 있다 : @Qualifier()

// 인터페이스는 설계도다 -> BoardService 인터페이스가 있다고 하자
//		그러면 BoardServiceImp1을 만드는 담당자가 있겠지...근데 내 작업이 BoardService에 의존해
//		이 상황에서 나는 가짜 BoardService를 만들 수 밖에 없다(mock 객체) -> 이름 주입을 사용할 수 있다
//		이런식으로 가짜 구현 객체가 돌아다니는 것을 관리자가 싫어할 것이다

// mock객체를 만들지 않고도 사용가능한 프레임워크 : mockito


@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Value("${admin.username}")
	private String adminUsername;
	@Value("${admin.password}")
	private String adminPassword;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.equals(adminUsername)) {
			return new MyUserDetails(username, passwordEncoder.encode(adminPassword), true, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
		}
		try {
			Member member = memberDao.findByUsername(username).get();
			String role = "ROLE_" + member.getRole().name();
			Collection<GrantedAuthority> list = new ArrayList<>();
			list.add(new SimpleGrantedAuthority(role));
			
			return new MyUserDetails(username,member.getPassword(),!member.getDisabled(),list);
		} catch(NoSuchElementException e) {
			// 예외 전환
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
		}
	}

}




