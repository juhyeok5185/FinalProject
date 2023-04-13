//관리자 : 주성
package hotel.management.v1.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	private String username;
	private String irum;
	private Integer phoneNumber;
	private String password;
	private String nationality;
	private String email;
	private Integer residentRegistrationNumber;
	private Integer loginFailCnt;
	private Boolean enabled;
	@Builder.Default
	private Level level = Level.BRONZE;
	
	
	
}
