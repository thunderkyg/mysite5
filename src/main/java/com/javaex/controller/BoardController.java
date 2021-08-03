package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/board", method = { RequestMethod.GET, RequestMethod.POST } )
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//READ
	@RequestMapping(value="/read", method = { RequestMethod.GET, RequestMethod.POST } )
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("[BoardController.read()]");
		
		BoardVo boardVo = boardService.getBoard(no);
		
		model.addAttribute("boardVo", boardVo);
		
		return "/board/read";
	}
	
	//LIST
	@RequestMapping(value="/list", method = { RequestMethod.GET, RequestMethod.POST } )
	public String list(@RequestParam(required = false) String keyword, Model model) {
		System.out.println("[BoardController.list()]");
		
		Map<String, Object> searchvalue= new HashMap<String, Object>();
		searchvalue.put("search", keyword);
		
		List<BoardVo> boardList  = boardService.getList(searchvalue);
		
		model.addAttribute("boardList", boardList);
		System.out.println(boardList);
		
		return "/board/list";
	}
	
	//DELETE
	@RequestMapping(value="/delete", method = { RequestMethod.GET, RequestMethod.POST } )
	public String delete(@RequestParam("no") int no) {
		System.out.println("[BoardController.delete()]");
		
		boardService.boardDelete(no);
		
		return "redirect:/board/list";
	}
	
	//WRITE FORM
	@RequestMapping(value="/writeForm", method = { RequestMethod.GET, RequestMethod.POST } )
	public String writeForm(HttpSession session) {
		System.out.println("[BoardController.writeForm()]");
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			return "/board/writeForm";
		} else {
			return "/user/loginForm";
		}
	}
	
	//WRITE
	@RequestMapping(value="/write", method = { RequestMethod.GET, RequestMethod.POST } )
	public String write(HttpSession session, 
						@RequestParam("title") String title,
						@RequestParam("content") String content) {
		
		System.out.println("[BoardController.write()]");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		
		BoardVo boardVo = new BoardVo(title, content, userNo);
		System.out.println(boardVo);
		
		boardService.boardInsert(boardVo);
		
		return "redirect:/board/list";
	}
	
	//MODIFYFORM
	@RequestMapping(value="/modifyForm", method = { RequestMethod.GET, RequestMethod.POST } )
	public String modifyForm(@RequestParam("no") int no, Model model, HttpSession session) {
		
		System.out.println("[BoardController.modifyForm()]");
		BoardVo boardVo = boardService.getBoardModify(no);
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser.getNo() == boardVo.getUserNo()) {
			model.addAttribute("modifyBoardVo", boardVo);
			return "/board/modifyForm";
		} else {
			return "redirect:/board/list";
		}
		
		
	}
	
	//MODIFY
	@RequestMapping(value="/modify", method = { RequestMethod.GET, RequestMethod.POST } )
	public String modify(@ModelAttribute BoardVo boardVo, 
						 @RequestParam("userno") int userno,
						 HttpSession session) {
		
		System.out.println("[BoardController.modify()]");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser.getNo() == userno) {
			boardService.boardModify(boardVo);
			return "redirect:/board/list";
		} else {
			return "redirect:/board/list";
		}
	}
}