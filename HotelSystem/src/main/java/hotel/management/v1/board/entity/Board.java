package hotel.management.v1.board.entity;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {
	private Integer boardNo;
	private String title; 
	private String content;	
	private String username;
	@JsonFormat(pattern = "yyyy년 MM월 dd일 hh - mm ")
	private LocalDateTime writeDay;
	private String replyContent;
	@JsonFormat(pattern = "yyyy년 MM월 dd일 hh - mm ")
	private LocalDateTime replyWriteDay;

}
