package hotel.management.v1.memberTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hotel.management.v1.member.dao.MemberDao;
import hotel.management.v1.member.entity.Level;
import hotel.management.v1.member.entity.Member;
import hotel.management.v1.member.entity.Role;
import lombok.Builder;

@SpringBootTest
public class MemberTest {
    @Autowired
    private MemberDao dao;
    
    @Test
    public void test() {
    	Member member = new Member("admin","박주혁","01066805185","1234","juhyeok.park@gmail.com","781203",0,false,Level.BRONZE,Role.ADMIN,0,false,false);
    	dao.save(member);
    }
}



