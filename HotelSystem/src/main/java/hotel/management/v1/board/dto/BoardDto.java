package hotel.management.v1.board.dto;
import java.time.LocalDate;

import hotel.management.v1.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 관리자 = 김동욱

public class BoardDto {

	// 게시판 작성 dto
	@Data
	@Builder
	@AllArgsConstructor
	public static class Write {
		private String title;
		private String content;
		
		public Board toEntity() {
			return Board.builder().title(title).content(content).build();

		}
	}
}