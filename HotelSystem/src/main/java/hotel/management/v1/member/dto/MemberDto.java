package hotel.management.v1.member.dto;

import hotel.management.v1.member.entity.Member;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class MemberDto {
	
	@Data
	public static class Join {
		private String username;
		private String name;
		private String tel;
		private String password;
		private String email;
		private String personalId;
		
		public Member toEntity(String encodedPassword) {
			return Member.builder().username(username).name(name).tel(tel)
					.password(encodedPassword).email(email).personalId(personalId)
					.build();
		}
	}
}
