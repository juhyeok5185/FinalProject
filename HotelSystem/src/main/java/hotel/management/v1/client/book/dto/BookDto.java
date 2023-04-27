package hotel.management.v1.client.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class BookDto {
	@Data
	 public static class addbookfromDinner {
		Integer totalcnt;
		String from;
		String booktel;
		String booker;
	}
	@Data
	@AllArgsConstructor
	public static class adddinner{
		Integer resno;
		Integer rescnt;
		Integer resprice;
	}
	@Data
	@AllArgsConstructor
	public static class whtyouname {
		String username;
		String booktel;
		String booker;
		String from;
		String to;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class data {
		String username;
		Integer bookno;
	}
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
		String from;
		String to;
		Integer totalcnt;
		String gradename;
		Boolean bfcheckbox;
		Boolean dicheckbox;
		String booker;
		String booktel;
		
	}
	@Data
	@ToString
	public static class dinnerbook{
		String bookdate;
		String booktel;
		Integer bookpeople;
		String booker;
	}
	
	@Data
	@ToString
	public static class findRoom{
		String gradeName;
		Boolean reservableOrNot;
		Integer breakfastprice;
		Integer gradeprice;
	}

	@Data
	@AllArgsConstructor
	public static class dinner{
		String username;
		Integer bookno;
	};
	@Data
	public static class finduser {
		String username;
		String userlevel;
	}
}
