package hotel.management.v1.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public void write(BoardDto.Write dto) {
		boardService.write(dto);
	}

	@GetMapping("/hotel/board/list")
	public ModelAndView list(@RequestParam(defaultValue = "1") Integer pageno) {
		List<Board> list = boardService.list();
		return new ModelAndView("/hotel/board/list").addObject("board", list).addObject("pagination",
				boardService.pagination(pageno));
	}

	@GetMapping("/hotel/board/read")
	public void read(Integer boardNo, Model model) {
		Board board = boardService.findByNo(boardNo);
		System.out.println(board);
		model.addAttribute("board", boardService.findByNo(boardNo));
	}

	@PostMapping("/hotel/board/read")
	public ModelAndView reply(Integer boardNo, String replyContent) {
		boardService.replyUpdate(boardNo, replyContent);
		return new ModelAndView("redirect:/hotel/board/read?boardNo=" + boardNo);
	}

}