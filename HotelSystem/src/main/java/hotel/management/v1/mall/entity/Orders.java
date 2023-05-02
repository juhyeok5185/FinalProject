package hotel.management.v1.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class Orders {
	private Integer orderNo;
	private String username;
	private Integer itemNo;
}
