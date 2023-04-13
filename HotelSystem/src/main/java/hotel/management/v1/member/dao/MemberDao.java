package hotel.management.v1.member.dao;

import org.apache.ibatis.annotations.Mapper;

import hotel.management.v1.member.entity.Member;

@Mapper
public interface MemberDao {
	public Integer save(Member member);
}
