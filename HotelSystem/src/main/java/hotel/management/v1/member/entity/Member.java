//관리자 : 주성
package hotel.management.v1.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Member {
	private String username;
	private String name;
	private String tel;
	private String password;
	private String email;
	private String personalId;
}
	
	
