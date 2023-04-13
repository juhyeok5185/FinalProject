package hotel.management.v1.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import hotel.management.v1.board.dto.BoardDto;
import hotel.management.v1.board.entity.Board;

@Mapper
public interface BoardDao {
	
	@Insert("insert into board(boardno, title, content) values(#{boardno}, #{title}, #{content})")
	public void write(BoardDto.BoardDtoWrite boardDtoWrite);
	
	@Select("select * from board")
	public List<Board> findAll(BoardDto.FindAll findall);
}
