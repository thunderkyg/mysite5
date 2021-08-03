package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	// SelectAll
	public List<GuestbookVo> getList() {

		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.selectList");

		return guestbookList;
	}

	// Insert

	public int guestbookInsert(GuestbookVo guestbookVo) {

		int count = sqlSession.insert("guestbook.guestbookInsert", guestbookVo);

		return count;

	}

	// Delete

	public int guestbookDelete(GuestbookVo g) {

		int count = sqlSession.delete("guestbook.guestbookDelete", g);

		return count;
	}
	
	
	//방명록 글 저장
	public int guestbookInsertKey(GuestbookVo guestbookVo) {
		System.out.println("[guestbookDao.guestbookInsertKey]");
		
		return sqlSession.insert("guestbook.guestbookInsertKey", guestbookVo);
	}
	
	//방명록 글 저장 - ajax
	public GuestbookVo selectGuestbook(int no) {
		System.out.println("[guestbookDao.guestbookInsertKey]");
		
		return sqlSession.selectOne("guestbook.selectGuestbook", no);
	}
	
}