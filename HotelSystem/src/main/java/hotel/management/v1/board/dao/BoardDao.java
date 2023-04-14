package hotel.management.v1.board.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import hotel.management.v1.board.dto.BoardDto;
import hotel.management.v1.board.dto.BoardDto.Write;
import hotel.management.v1.board.entity.Board;

	// 관리자 = 김동욱

@Mapper
public interface BoardDao {
	
	//게시물 작성 dao
	@Insert("insert into board(boardNo, username, writeDay, title, content) values( BOARD_SEQ_BOARDNO.nextval, 'spring1' , sysdate , #{title}, #{content})")
	public void write(Write dto);
	
	//게시물 리스트 dao
	@Select("select * from board")
	public List<Board> list();

	@Select("select * from board where boardNo=#{boardNo}")
	public Board findByNo(Integer boardNo);
	
}