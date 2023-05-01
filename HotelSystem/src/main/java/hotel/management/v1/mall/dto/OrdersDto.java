package hotel.management.v1.mall.dto;

import java.util.List;

import lombok.Data;

public class OrdersDto {
	@Data
	public static class OrderList {
		List<Integer> list;
	}
	
	@Data
	public static class Read {
		private String username;
	}
	
	@Data
	public static class Order {
		private String pickupDay;
		private Integer itemPrice;
		private String itemName;
	}
	
}

