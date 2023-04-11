package hotel.management.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    private Integer no;
    private String writer;
    private String title;
    private String content;
    private Integer viewCnt;
    private Integer likeCnt;
}
