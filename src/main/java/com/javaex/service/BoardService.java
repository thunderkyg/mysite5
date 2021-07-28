package com.javaex.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//Read
	public BoardVo getBoard(int no) {
		System.out.println("[BoardService.getBoard()]");
		System.out.println(no);
		
		//Hit + 1
		boardDao.updateHit(no);
		
		//Get Board DB
		BoardVo boardVo = boardDao.getBoard(no);
		return boardVo;
	}
	
	//Modify(Select)
	public BoardVo getBoardModify(int no) {
		System.out.println("[BoardService.getBoardModify()]");
		
		return boardDao.getBoard(no);
	}
	
	//List
	public List<BoardVo> getList(Map<String, Object> searchvalue){
		System.out.println("[BoardService.getList()]");
		System.out.println(searchvalue);
		return boardDao.getList(searchvalue);
	}
	
	//Delete
	public int boardDelete(int no) {
		System.out.println("[BoardService.boardDelete()]");
		
		return boardDao.boardDelete(no);
	}
	
	//Write
	public int boardInsert(BoardVo boardVo) {
		System.out.println("[BoardService.boardInsert()]");
		
		return boardDao.boardInsert(boardVo);
	}
	
	//Update
	public int boardModify(BoardVo boardVo) {
		System.out.println("[BoardService.boardModify()]");
		
		return boardDao.boardModify(boardVo);
	}
	
	
	

}
