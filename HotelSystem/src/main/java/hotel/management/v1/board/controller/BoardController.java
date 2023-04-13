package hotel.management.v1.board.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hotel.management.v1.board.dto.BoardDto;
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
	public void write(BoardDto.Write dto) {
		System.out.println(dto);
		boardService.write(dto);
	}
	
//	@GetMapping("/hotel/board/list")
//	public ModelAndView list(BoardDto.list dto) {
//		boardService.list(dto);
//	}
	
}