package hotel.management.v1.member.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hotel.management.v1.member.entity.Member;

@Mapper
public interface MemberDao {
	@Select("select count(*) from users where username=#{username} and rownum=1")
	public Boolean existsByUsername(String username);
	
	@Select("select count(*) from users where email=#{email} and rownum=1")
	public Boolean existsByEmail(String email);
	
	public Integer save(Member member);

	@Select("select * from users where username=#{username} and rownum=1")
	public Optional<Member> findByUsername(String username);

	@Update("update users set loginFailCount=0 where username=#{username} and rownum=1")
	public Integer resetLoginCnt(String username);
	
	@Update("update users set loginFailCount=loginFailCount+1 where username=#{username} and rownum=1")
	public Integer increaseLoginFailCnt(String username);
	
	@Update("update users set enabled=0 where username=#{username} and rownum=1")
	public Integer disabled(String username);
}
