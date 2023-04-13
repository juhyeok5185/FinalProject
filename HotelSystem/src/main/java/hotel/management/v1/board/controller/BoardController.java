package hotel.management.v1.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hotel.management.v1.board.dao.BoardDao;
import hotel.management.v1.board.dto.BoardDto;
import hotel.management.v1.board.entity.Board;

@Controller
public class BoardController {
	@Autowired
	private BoardDao boarddao;
	
	@GetMapping("/hotel/board/write")
	public void write() {
		
	}
	
	@PostMapping("/hotel/board/write")
	public void write(BoardDto.BoardDtoWrite dto) {
		System.out.println(dto);
		boarddao.write(dto);
	}
	
	@GetMapping("/hotel/board/list")
	public ModelAndView findAll(BoardDto.FindAll dto) {
		List<Board> list = boarddao.findAll(dto);
		System.out.println(dto);
		return new ModelAndView("board").addObject("board", list);
	}
}
