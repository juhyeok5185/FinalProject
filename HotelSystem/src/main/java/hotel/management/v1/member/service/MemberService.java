package hotel.management.v1.member.service;

import java.util.NoSuchElementException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hotel.management.v1.member.dao.MemberDao;
import hotel.management.v1.member.dto.MemberDto;
import hotel.management.v1.member.entity.Member;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.NotEmpty;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private JavaMailSender mailSender;
	
	private void sendMail(String from, String to, String title, String text) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(title);
			helper.setText(text, true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
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

	public void resetPassword(String name, String username, String email) {
		try {
			Member member = dao.findByPassword(name, username, email).get();
			String newPassword = RandomStringUtils.randomAlphanumeric(20);
			String newEncodedPassword = encoder.encode(newPassword);
			dao.changePassword(newEncodedPassword, username);
			sendMail("cpsua97@naver.com",member.getEmail(), "임시비밀번호", newPassword);
		} catch(NoSuchElementException e) {
			throw e;
		}
	}

	public void delete(String username) {
		try {
			dao.delete(username);
		} catch(NoSuchElementException e) {
			throw e;
		}
	}

	public void changePassword(String newpassword, String username) {
		String encodedPassword = encoder.encode(newpassword);
		dao.changePassword(encodedPassword, username);
	}
}
