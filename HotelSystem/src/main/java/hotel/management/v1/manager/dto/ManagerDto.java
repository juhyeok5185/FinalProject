package hotel.management.v1.manager.dto;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class ManagerDto {
    
    @Data
    @ToString
    @Builder
	public static class bookList {
        private Boolean isStay;
        private Boolean isRestaurant;
        private String fromDate;
        private String toDate;
        private Integer roomNum;
        private String name;
        private Integer listType;
    }

    @Data
    @ToString
    @Builder
	public static class test {
        private Integer bookNo;
        private String userName;
        private Integer totalCount;
        private LocalDate bookDate;
        private String bookTel;
        private String booker;
    }
}
