package hotel.management.v1.board.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import hotel.management.v1.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

	// 관리자 = 김동욱

public class BoardDto {
	
	//게시판 작성 dto
	@Data
	@Builder
	@AllArgsConstructor
	public static class BoardDtoWrite {
		private Integer boardNo;
		private String title; 
		private String content;	
		private String username;
		@JsonFormat(pattern = "yyyy년 MM월 dd일 hh - mm ")
		private LocalDateTime writeDay;
		
		public Board toEntity() {
			return Board.builder().title(title).content(content).username(username).writeDay(writeDay).build();
		}
	}
		
		
	

}