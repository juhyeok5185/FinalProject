package hotel.management.v1.pay.dto;

import hotel.management.v1.pay.entity.PayType;
import lombok.AllArgsConstructor;
import lombok.Data;

public class PayDto {

	
	@Data
	@AllArgsConstructor
	public static class bookAddPayment{
		String tid;
		String orderid;
		String itemname;
		Integer amount;
		PayType type;
	}
}
