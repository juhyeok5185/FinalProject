package hotel.management.v1.board.entity;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String username;
	@JsonFormat(pattern = "yy/MM/dd")
	private LocalDate writeDay;
	private String title; 
	private String content;	
	
	
	
}
