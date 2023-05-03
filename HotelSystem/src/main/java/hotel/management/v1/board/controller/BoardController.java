package hotel.management.v1.board.controller;

import java.security.Principal;

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
	public ModelAndView write(Principal principal) {
		return new ModelAndView("/hotel/board/write").addObject("name",principal.getName());
	}

	@PostMapping("/hotel/board/write")
	public ModelAndView write(BoardDto.Write dto ,Principal principal) {
		boardService.write(dto, principal.getName());
		 return new ModelAndView("redirect:/hotel/board/list");
	}

	@GetMapping("/hotel/board/list")
	public ModelAndView list(@RequestParam(defaultValue = "1") Integer pageno) {
		BoardDto.Pagination list = boardService.pagination(pageno);
		return new ModelAndView("/hotel/board/list")
				.addObject("board", list.getBoard())
				.addObject("prev",	list.getPrev())
				.addObject("start",  list.getStart())
				.addObject("end",	list.getEnd())
				.addObject("next",	list.getNext())
				.addObject("pageno", pageno);
	}

	@GetMapping("/hotel/board/read")
	public void read(Integer boardNo, Model model) {
		Board board = boardService.findByNo(boardNo);
		model.addAttribute("board", boardService.findByNo(boardNo));
	}

	@PostMapping("/hotel/board/read")
	public ModelAndView reply(Integer boardNo, String replyContent, String username) {
		boardService.replyUpdate(boardNo, replyContent, username);
		return new ModelAndView("redirect:/hotel/board/read?boardNo=" + boardNo);
	}
	

	@PostMapping("/hotel/board/delete")
	public ModelAndView deleteBoard(String boardNo) {
	    boardService.delete(boardNo);
	        return new ModelAndView("redirect:/hotel/board/list");
	}
	
	
}