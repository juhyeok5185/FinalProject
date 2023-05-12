package hotel.management.v1.pay.dto;

import hotel.management.v1.pay.entity.PayType;
import lombok.AllArgsConstructor;
import lombok.Data;

public class PayDto {
	@Data
	public static class payment{
		String tid;
		String partner_order_id;
		Integer total_amount;
		Integer bookno;

	}

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
