package hotel.management.v1.board.service;


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
	
	public void write(BoardDto.Write dto) {
		Board board = new Board(null, dto.getTitle(), dto.getContent(), dto.getUsername(), null);
		boardDao.write(board);
	}
	
	
	public List<Board> list() {
		List<Board> list = boardDao.list();
		return null;
	}

}
