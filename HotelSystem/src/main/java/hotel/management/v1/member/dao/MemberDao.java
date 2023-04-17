package hotel.management.v1.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import hotel.management.v1.member.entity.Member;

@Mapper
public interface MemberDao {
	@Select("select count(*) from member where username=#{username} and rownum=1")
	public Boolean existsByUsername(String username);
	
	public Boolean existsByEmail(String email);
	
	public Integer save(Member member);
}
