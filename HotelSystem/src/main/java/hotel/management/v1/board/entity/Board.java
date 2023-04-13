package hotel.management.v1.board.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {
	private Integer boardnNo;
	private String title; 
	private String content;
	
	
//	private Integer boardnNo;
//	private String username;
//	@JsonFormat(pattern = "yyyy년 MM월 dd일 hh - mm ")
//	private LocalDateTime writeDay;
//	private String replyContent;
//	@JsonFormat(pattern = "yyyy년 MM월 dd일 hh - mm ")
//	private LocalDateTime replyWriteDay;
//	private String title; 
//	private String content;
}
