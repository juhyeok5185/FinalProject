package hotel.management.v1.board.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import hotel.management.v1.board.dto.BoardDto;
import hotel.management.v1.board.entity.Board;

	// 관리자 = 김동욱

@Mapper
public interface BoardDao {
	
	//게시물 작성 dao
	@Insert("insert into board(title, content, username) values(#{title}, #{content}, #{username})")
	public void write(Board board);
	
	//게시물 리스트 dao
	@Select("select * from board")
	public List<Board> list();
	
//	//게시물 리드 dao
//	@Select("select * from baord where boardNo=#{boardNo}")
//	public List<Board> findByNo(Integer boardNo);
//	

}
