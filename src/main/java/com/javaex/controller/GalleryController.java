package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/gallery", method = { RequestMethod.GET, RequestMethod.POST })
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String galleryList(HttpSession session, Model model) {
		System.out.println("[GalleryController.galleryList()]");

		List<GalleryVo> getList = galleryService.galleryList();
		System.out.println(getList);

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser != null) {
			System.out.println(authUser.getNo());
			model.addAttribute("no", authUser.getNo());
		}

		// 보내기
		model.addAttribute("getList", getList);

		return "/gallery/list";
	}

	@RequestMapping(value="/insert", method = { RequestMethod.GET, RequestMethod.POST } )
	public String galleryInsert(@RequestParam ("file") MultipartFile file,
								@RequestParam ("no") int no,
								@RequestParam ("content") String content) {
		
		System.out.println("[GalleryController.galleryInsert()]");
		
		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("file", file);
		hashMap.put("no", no);
		hashMap.put("content", content);
		
		galleryService.galleryInsert(hashMap);
		
		return "redirect:/gallery/list";
	}

	@ResponseBody
	@RequestMapping(value="/view", method = { RequestMethod.GET, RequestMethod.POST } )
	public GalleryVo galleryView(@RequestParam("no") int no) {
		
		GalleryVo galleryVo = galleryService.galleryView(no);
		System.out.println("CONTROLLER: " + galleryVo);
		
		return galleryVo;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete", method = { RequestMethod.GET, RequestMethod.POST } )
	public int galleryDelete(@RequestParam("no") int no) {
		
		int count = galleryService.galleryDelete(no);
		System.out.println("삭제: " + count);
		
		return count;
	}
	
}
