package hotel.management.v1.mall.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

public class OrdersDto {
	@Data
	public static class OrderList {
		private Integer orderNo;
		private String itemName;
		private String orderDate;
	}
	
	@Data
	public static class Read {
		private String username;
	}
	
	@Data
	@ToString
	public static class Order {
		private String pickupDay;
		private String[] tbodyArray;
		
	}
	
}

