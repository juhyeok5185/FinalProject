package hotel.management.v1.board.dto;

import hotel.management.v1.board.entity.Board;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

// 관리자 = 김동욱

public class BoardDto {

	@Data
	@Builder
	@AllArgsConstructor
	public static class Write {
		private String title;
		private String content;
		
		public Board toEntity(String title, String content) {
			return Board.builder().title(title).content(content).build();
		}
	}
	
	@Data
	@Builder
	public static class Update{
		@NotNull
		private Integer boardNo;
		@NotEmpty
		private String title;
		@NotEmpty
		private String content;
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	@ToString
	public static class Pagination{
		private Integer prev; 
		private Integer start;
		private Integer end;
		private Integer next;
	}
	
}