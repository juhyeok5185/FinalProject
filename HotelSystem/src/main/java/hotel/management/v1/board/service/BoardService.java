package hotel.management.v1.board.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.board.dao.BoardDao;
import hotel.management.v1.board.dto.BoardDto;
import hotel.management.v1.board.entity.Board;

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
	
	public BoardDto.Pagination pagination(int pageno) {
		Integer boardCnt = boardDao.count();
		Integer boardPageCnt = (boardCnt - 1) / PAGESIZE + 1;
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
		return new BoardDto.Pagination(prev, start, end, next);
	}

	public void replyUpdate(Integer boardNo, String replyContent) {
			boardDao.update(boardNo , replyContent);
	}
	
}
