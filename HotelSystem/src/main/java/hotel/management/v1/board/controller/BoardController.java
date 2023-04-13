package hotel.management.v1.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hotel.management.v1.board.dao.BoardDao;
import hotel.management.v1.board.dto.BoardDto;
import hotel.management.v1.board.entity.Board;
import hotel.management.v1.board.service.BoardService;

	// 관리자 = 김동욱

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/hotel/board/write")
	public void write() {
		
	}
	
	@PostMapping("/hotel/board/write")
	public ModelAndView write(BoardDto.BoardDtoWrite dto) {
		Board board = boardService.write(dto);
		return new ModelAndView("")
	}
	

}
