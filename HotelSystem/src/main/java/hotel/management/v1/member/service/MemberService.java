package hotel.management.v1.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.member.dao.MemberDao;
import hotel.management.v1.member.dto.MemberDto;
import hotel.management.v1.member.entity.Member;
import jakarta.validation.constraints.NotEmpty;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	
	public void join(MemberDto.Join dto) {
		Member member = dto.toEntity();
		dao.save(member);
	}

	public Boolean checkUsername(String username) {
		return !dao.existsByUsername(username);
	}
}
