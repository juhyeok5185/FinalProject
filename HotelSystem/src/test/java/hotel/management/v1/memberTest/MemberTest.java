package hotel.management.v1.memberTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import hotel.management.v1.member.dao.MemberDao;
import hotel.management.v1.member.entity.Level;
import hotel.management.v1.member.entity.Member;
import hotel.management.v1.member.entity.Role;
import lombok.Builder;

@SpringBootTest
public class MemberTest {
    @Autowired
    private MemberDao dao;
    @Autowired
    private PasswordEncoder encoder;
    
    @Test
    public void test() {
    	String password = "1234";
    	Member member = new Member("admin","관리자","01066805185",encoder.encode(password),"juhyeok.park@gmail.com","781203",0,false,Level.BRONZE,Role.ADMIN,0,false,false);
    	dao.save(member);
    }
}



