package hotel.management.v1.member.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hotel.management.v1.member.dao.MemberDao;
import hotel.management.v1.member.dto.MemberDto;
import hotel.management.v1.member.entity.Member;
import jakarta.validation.constraints.NotEmpty;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	@Autowired
	private PasswordEncoder encoder;
	
	public void join(MemberDto.Join dto) {
		String encodedPassword = encoder.encode(dto.getPassword());
		Member member = dto.toEntity(encodedPassword);
		dao.save(member);
	}

	public Boolean checkUsername(String username) {
		return !dao.existsByUsername(username);
	}

	public Boolean checkEmail(String email) {
		return !dao.existsByEmail(email);
	}

	public String findUsername(String name, String email) {
		try {
			return dao.findByEmail(name, email).get().getUsername();
		} catch(NoSuchElementException e) {
			throw e;
		}
	}
}
