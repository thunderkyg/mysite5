package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;

	//LISTING
	public List<GalleryVo> galleryList() {
		System.out.println("[GalleryService.galleryList()]");

		return galleryDao.getList();
	}

	//INSERTING
	public int galleryInsert(Map<String, Object> hashMap) {
		System.out.println("GalleryService.galleryInsert()");

		MultipartFile file = (MultipartFile) hashMap.get("file");
		int user_no = (int) hashMap.get("no");
		String content = (String) hashMap.get("content");

		String saveDir = "C:\\javaStudy\\upload";
		System.out.println(saveDir);
		// 원파일이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName:" + orgName);

		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName:" + exName);

		// 저장파일이름(관리 떄문에 겹치지 않는 새이름 부여)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName:" + saveName);

		// 파일패스
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath:" + filePath);

		// 파일사이즈
		long fileSize = file.getSize();
		System.out.println("orgSize:" + fileSize);

		// 파일 서버하드디스크에 저장
		try {

			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		GalleryVo galleryVo = new GalleryVo(user_no, content, filePath, orgName, saveName, fileSize);

		System.out.println(galleryVo);

		int count = galleryDao.galleryInsert(galleryVo);
		
		return count;
		
	}
	
	public GalleryVo galleryView(int no) {
		System.out.println("GalleryService.galleryView()");
		
		return galleryDao.galleryView(no);
	}
	
	public int galleryDelete(int no) {
		System.out.println("GalleryService.galleryDelete()");
		
		int count = galleryDao.galleryDelete(no);
		
		return count;
	}

}
