package hotel.management.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hotel.management.v1.dao.BoardDao;
import hotel.management.v1.entity.Board;

@SpringBootTest
class HotelSystemApplicationTests {

	@Autowired
    private BoardDao dao;

    @Test
    public void ddd() {
        Board board = new Board(null, "11", "test", "test", 0, 0);
        for (int i = 0; i < 10; i++) {
            dao.write(board);
        }
    }

}
