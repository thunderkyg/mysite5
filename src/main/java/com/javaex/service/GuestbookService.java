package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookDao guestbookDao;

	// SelectAll
	public List<GuestbookVo> getList() {

		return guestbookDao.getList();
	}

	// Insert

	public int guestbookInsert(GuestbookVo guestbookVo) {

		return guestbookDao.guestbookInsert(guestbookVo);

	}

	// Delete

	public int guestbookDelete(GuestbookVo g) {

		return guestbookDao.guestbookDelete(g);
	}
	
	// Ajax
	public GuestbookVo writeResultVo(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.writeResultVo");
		//글저장
		System.out.println();
		int count = guestbookDao.guestbookInsertKey(guestbookVo);
		System.out.println(guestbookVo);
		
		int no = guestbookVo.getGuestbook_no();
		
		//글 가져오기(방금등록한 번호)
		GuestbookVo resultVo = guestbookDao.selectGuestbook(no);
		
		return resultVo;
	}
}
