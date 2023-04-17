package hotel.management.v1.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import hotel.management.v1.member.entity.Member;

@Mapper
public interface MemberDao {
	@Select("select count(*) from users where username=#{username} and rownum=1")
	public Boolean existsByUsername(String username);
	
	@Select("select count(*) from users where email=#{email} and rownum=1")
	public Boolean existsByEmail(String email);
	
	public Integer save(Member member);
}
