package com.javaex.service;

import java.util.HashMap;
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
	
	// 게시판 페이징 연습용 리스트
	public Map<String, Object> getList2(int crtPage, String keyword){
		System.out.println("[BoardService.getList2()]");
		System.out.println(crtPage);
		
		////////////////////////////////////////////////
		//리스트 가져오기
		////////////////////////////////////////////////
		
		//crtPage 계산(- 값일때 1 page 처리)
		//if(!(crtPage > 0)) {crtPage = 1;}
		crtPage = (crtPage > 0) ? crtPage : (crtPage=1);
		
		int listCnt = 10;
		
		//시작번호 계산하기
		int startRnum = ((crtPage-1)*listCnt+1);
				
		//끝번 계산하기
		int endRnum = (crtPage*listCnt);
		
		List<BoardVo> boardList = boardDao.selectList2(startRnum, endRnum, keyword);
		
		////////////////////////////////////////////////
		// 페이징 게산
		////////////////////////////////////////////////
		
		//전체글 갯수
		int totalCount = boardDao.selectTotalCnt(keyword);
		System.out.println(totalCount);
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호
		int endPageBtnNo = (int) (Math.ceil((crtPage/(double)pageBtnCount)) * pageBtnCount);
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - pageBtnCount + 1;
		
		//다음 화살표 표현 유무
		boolean next = false;
		if((endPageBtnNo * listCnt) < totalCount){
			next = true;
		} else {
			//다음 화살표 버튼이 없을때 endPageBtnNo를 다시 계산해야된다
			//전체글갯수 / 한폐이지의 글갯수
			
			
			endPageBtnNo = (int) Math.ceil(totalCount/(double)listCnt);
		}
		
		//이전 화살표 표현 유무
		boolean prev = false;
		if((startPageBtnNo) != 1) {
			prev = true;
		}
		
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("boardList", boardList);
		listMap.put("prev", prev);
		listMap.put("startPageBtnNo", startPageBtnNo);
		listMap.put("endPageBtnNo", endPageBtnNo);
		listMap.put("next", next);
		
		return listMap;
	}
	
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
		
		for(int i = 0; i<127; i++) {
			boardVo.setTitle(i + "번쨰 재목입니다.");
			boardVo.setContent(i + "번쨰 내용입니다.");
			boardDao.boardInsert(boardVo);
		}
		
		//return boardDao.boardInsert(boardVo);
		return 1;
	}
	
	//Update
	public int boardModify(BoardVo boardVo) {
		System.out.println("[BoardService.boardModify()]");
		
		return boardDao.boardModify(boardVo);
	}
	
	
	

}
