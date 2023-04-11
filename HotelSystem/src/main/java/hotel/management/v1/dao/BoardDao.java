package hotel.management.v1.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import hotel.management.v1.entity.Board;

@Mapper
public interface BoardDao {
    @Insert("insert into board values(board_seq.nextval , #{writer} , #{title} , #{content}, #{viewCnt}, #{likeCnt})")
    public Integer write(Board board);
}
