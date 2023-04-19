package hotel.management.v1.client.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

public class bookDto {
	
	@Data
	@AllArgsConstructor
	public static class addRoomBook {
		String checkOut;
		Boolean bfcheckbox;
		String gradename;
		String username;
	}
	
	@Data
	@AllArgsConstructor
	public static class addbook {
		String username;
		String from;
		Integer totalcnt;
		String booktel;
		String booker;
	}
	
	
	@Data
	@ToString
	public static class book {
		String username;
		String from;
		String to;
		Integer totalcnt;
		String gradename;
		Boolean bfcheckbox;
		String booker;
		String booktel;
		
	}
	
	@Data
	@ToString
	public static class findRoom{
		String gradeName;
		Boolean reservableOrNot;
	}
}
