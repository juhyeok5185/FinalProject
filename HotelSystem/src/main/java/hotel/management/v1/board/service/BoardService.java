package hotel.management.v1.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.board.dao.BoardDao;
import hotel.management.v1.board.dto.BoardDto;
import hotel.management.v1.board.dto.BoardDto.FindAll;
import hotel.management.v1.board.entity.Board;

//관리자 = 김동욱

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	private final static Integer PAGESIZE = 10;
	private final static Integer BLOCKSIZE = 5;
	

	public void write(BoardDto.Write dto) {
		Board board = dto.toEntity(dto.getTitle(),dto.getContent());
		boardDao.write(dto);
	}

	public List<Board> list() {
		List<Board> list = boardDao.list();
		return list;
	}

	public Board findByNo(Integer boardNo) {
		return boardDao.findByNo(boardNo);
	}
	
	public BoardDto.Pagination pagination(Integer pageno) {
		Integer boardCnt = boardDao.count();
		Integer boardPageCnt = (boardCnt - 1) / PAGESIZE + 1;
		
		pageno = Math.abs(pageno);
		if(pageno>boardPageCnt)
			pageno = boardPageCnt;
		Integer startRownum = (pageno-1) * PAGESIZE + 1;
		Integer endRownum = startRownum + PAGESIZE - 1; 
		List<BoardDto.FindAll> board = boardDao.findAll(startRownum, endRownum);
		
		Integer prev = (pageno - 1) / BLOCKSIZE * BLOCKSIZE;
		Integer start = prev + 1;
		Integer end = prev + BLOCKSIZE;
		Integer next = end + 1;
		if (end >= boardPageCnt) {
			end = boardPageCnt;
			next = 0;
		}
		
		prev = prev == 0 ? start : prev;
		next = next == 0 ? end : next;
		return new BoardDto.Pagination(prev, start, end, next, board);
	}

	public void replyUpdate(Integer boardNo, String replyContent) {
			boardDao.update(boardNo , replyContent);
	}
	
}
