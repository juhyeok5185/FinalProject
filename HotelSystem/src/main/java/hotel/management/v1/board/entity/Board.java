package hotel.management.v1.board.entity;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import hotel.management.v1.board.dto.BoardDto.Write;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 관리자 = 김동욱

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Board {
	private Integer boardNo;
	private String title; 
	private String content;	
	private String username;
	@JsonFormat(pattern = "yyyy년 MM월 dd일 hh - mm ")
	private LocalDateTime writeDay;
	
	
	
}
