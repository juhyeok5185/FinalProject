package hotel.management.v1.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class BoardDto {

	@Data
	@Builder
	@AllArgsConstructor
	public static class BoardDtoWrite{
		private Integer boardNo;
		private String title;
		private String content;		
		private String username;
		private String writer;
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	public static class FindAll{
		private Integer boardNo;
		private String title;
		private String content;
	}
	
}