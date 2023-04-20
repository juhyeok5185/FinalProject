package hotel.management.v1.member.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
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
	
	@Select("select * from users where name=#{name} and email=#{email} and rownum=1")
	public  Optional<Member> findByEmail(String name, String email);

	@Select("select * from users where username=#{username} and rownum=1")
	public Optional<Member> findByUsername(String username);
	
	@Select("select * from users where name=#{name} and username=#{username} and email=#{email} and rownum=1")
	public Optional<Member> findByPassword(String name, String username, String email);

	@Update("update users set loginFailCount=0 where username=#{username} and rownum=1")
	public Integer resetLoginCnt(String username);
	
	@Update("update users set loginFailCount=loginFailCount+1 where username=#{username} and rownum=1")
	public Integer increaseLoginFailCnt(String username);
	
	@Update("update users set enabled=0 where username=#{username} and rownum=1")
	public Integer disabled(String username);
	
	@Update("update users set password=#{newpassword} where username=#{username} and rownum=1")
	public Integer changePassword(String newpassword, String username);
	
	@Delete("delete from users where username=#{username} and rownum=1")
	public Integer delete(String username);

	public Integer update(String email, String tel, String username);
	
}
