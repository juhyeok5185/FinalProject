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
import hotel.management.v1.board.service.BoardService;

// 관리자 = 김동욱

@Controller
public class BoardController {
	@Autowired
	private BoardService service;

	@GetMapping("/hotel/board/write")
	public ModelAndView write(Principal principal) {
		return new ModelAndView("/hotel/board/write").addObject("name", principal.getName());
	}

	@PostMapping("/hotel/board/write")
	public ModelAndView write(BoardDto.Write dto, Principal principal) {
		service.write(dto, principal.getName());
		return new ModelAndView("redirect:/hotel/board/list");
	}

	@GetMapping("/hotel/board/list")
	public ModelAndView list(@RequestParam(defaultValue = "1") Integer pageno) {
		BoardDto.Pagination list = service.pagination(pageno);
		return new ModelAndView("/hotel/board/list").addObject("board", list.getBoard())
				.addObject("prev", list.getPrev()).addObject("start", list.getStart()).addObject("end", list.getEnd())
				.addObject("next", list.getNext()).addObject("pageno", pageno);
	}

	@GetMapping("/hotel/board/read")
	public void read(Integer boardNo, Model model) {
		service.findByNo(boardNo);
		model.addAttribute("board", service.findByNo(boardNo));
	}

	@PostMapping("/hotel/board/read")
	public ModelAndView reply(Integer boardNo, String replyContent, String username) {
		service.replyUpdate(boardNo, replyContent, username);
		return new ModelAndView("redirect:/hotel/board/read?boardNo=" + boardNo);
	}

	@PostMapping("/hotel/board/delete")
	public ModelAndView delete(String boardNo, String username, Principal principal) {
		String pusername = principal.getName();
		service.delete(boardNo);
		return new ModelAndView("redirect:/hotel/board/list").addObject("msg","삭제가 완료되었습니다.");
	}
}