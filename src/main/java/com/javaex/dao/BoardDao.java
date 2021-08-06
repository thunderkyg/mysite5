package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 전체 게시물 갯수 구하기
	public int selectTotalCnt(String keyword) {
		System.out.println("[BoardDao.selectTotalCnt()]");
		
		Map<String, String> keyMap = new HashMap<String, String>();
		keyMap.put("keyword", keyword);
		
		return sqlSession.selectOne("board.selectTotalCnt", keyMap);
	}
	
	
	// 게시판 페이징 연습용 리스트
	public List<BoardVo> selectList2(int startRnum, int endRnum, String keyword){
		System.out.println("[BoardDao.selectList2()]");
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("startRnum", startRnum);
		pMap.put("endRnum", endRnum);
		pMap.put("keyword", keyword);
		System.out.println(pMap);
		
		return sqlSession.selectList("board.selectList2", pMap);
	}

	// GetBoard (READ) + HIT
	public int updateHit(int no) {
		System.out.println("[BoardDao.updateHit]");
		System.out.println(no);

		int count = sqlSession.update("board.updateHit", no);
		System.out.println("count: " + count);
		return count;
	}

	// GetBoard (SELECT READ)
	public BoardVo getBoard(int no) {
		System.out.println("[BoardDao.getBoard]");

		BoardVo boardVo = sqlSession.selectOne("board.selectBoard", no);
		System.out.println(boardVo);

		return boardVo;
	}

	// GetList (LIST (BOARD MAIN PAGE))
	public List<BoardVo> getList(Map<String, Object> searchvalue) {
		System.out.println("[BoardDao.getList]");
		System.out.println(searchvalue);
		return sqlSession.selectList("board.selectAll", searchvalue);
	}
	
	// Delete (DELETE ROW IN DB)
	public int boardDelete(int no) {
		System.out.println("[BoardDao.boardDelete]");
		
		return sqlSession.delete("board.boardDelete", no);
	}
	
	// Insert (INSERTING NEW ROW IN DB)
	public int boardInsert(BoardVo boardVo) {
		System.out.println("[BoardDao.boardInsert]");
		System.out.println(boardVo);
		return sqlSession.insert("board.boardInsert", boardVo);
	}
	
	// Update (UPDATING NEW ROW IN DB)
	public int boardModify(BoardVo boardVo) {
		System.out.println("[BoardDao.boardModify]");
		
		return sqlSession.update("board.boardModify", boardVo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
