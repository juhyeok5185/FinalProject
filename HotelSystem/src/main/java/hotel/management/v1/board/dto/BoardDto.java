package hotel.management.v1.board.dto;


import java.util.List;

import hotel.management.v1.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class BoardDto {

	@Data
	@Builder
	@AllArgsConstructor
	public static class BoardDtoWrite{
		private Integer boarddnNo;
		private String title;
		private String content;		
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	public static class FindAll{
		private Integer boarddnNo;
		private String title;
		private String content;
	}
	
}
