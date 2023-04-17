package hotel.management.v1.board.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hotel.management.v1.board.dto.BoardDto;
import hotel.management.v1.board.dto.BoardDto.Write;
import hotel.management.v1.board.entity.Board;

	// 관리자 = 김동욱

@Mapper
public interface BoardDao {
	
	//게시물 작성 dao
	@Insert("insert into board(boardNo, username, writeDay, title, content) values(BOARD_SEQ_BOARDNO.nextval, #{username} , sysdate , #{title}, #{content})")
	public void write(Write dto);
	
	//게시물 리스트 dao
	@Select("select * from board order by boardNo desc")
	public List<Board> list();

	@Select("select * from board where boardNo=#{boardNo}")
	public Board findByNo(Integer boardNo);
	
	@Select("select count(*) from board")
	public Integer count();
	
	@Update("update board b set b.replycontent = #{replyContent}, replywriteday = sysdate where b.boardno=#{boardNo}")
	public void update(Integer boardNo, String replyContent);
	
//	@Select("select * from board order by boardNo desc")
	@Select("select rownum as rnum, A.boardNo , A.username , A.writeDay , A.title from (select rownum as rnum , boardNo, username, writeDay, title from board order by boardNo) A where rownum between 1 and 10")
	public List<BoardDto.FindAll> findAll(Integer startRownum, Integer endRownum);
	
	}