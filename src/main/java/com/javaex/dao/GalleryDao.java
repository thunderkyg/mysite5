package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;
import com.javaex.vo.GuestbookVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int galleryInsert(GalleryVo galleryVo) {
		System.out.println("GalleryDao.galleryInsert()");
		
		int count = sqlSession.insert("gallery.galleryInsert", galleryVo);

		return count;
		
	}
	
	public List<GalleryVo> getList(){
		System.out.println("[GalleryService.getList()]");
		
		List<GalleryVo> galleryVo = sqlSession.selectList("gallery.getList");
		System.out.println("DAO" + galleryVo);
		

		return galleryVo;
	}
	
	public GalleryVo galleryView(int no){
		System.out.println("[GalleryService.galleryView()]");
		
		GalleryVo galleryVo = sqlSession.selectOne("gallery.galleryView", no);
		System.out.println("DAO" + galleryVo);
		

		return galleryVo;
	}
	
	public int galleryDelete(int no){
		System.out.println("[GalleryService.galleryDelete()]");
		
		int count = sqlSession.delete("gallery.galleryDelete", no);
		
		return count;
	}
	
	

}
