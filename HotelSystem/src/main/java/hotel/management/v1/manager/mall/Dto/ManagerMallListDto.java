package hotel.management.v1.manager.mall.Dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

public class ManagerMallListDto {

	@Data
	@AllArgsConstructor
	@Builder
	@ToString
	public static class MallListSearch {
		String username; 
		String tel;
		String name;
		Integer orderNo; 
		LocalDate orderDate; 
		LocalDate contactDate;
		Integer orderPrice; 
		Integer orderEa;
	}
	
	@Data
	@AllArgsConstructor
	@Builder
	@ToString
	public static class FindMallList{
		String name; 
		String tel;
		Integer orderNo; 
	}
	

}
