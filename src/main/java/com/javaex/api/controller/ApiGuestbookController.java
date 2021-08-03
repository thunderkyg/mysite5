package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="api/guestbook", method = { RequestMethod.GET, RequestMethod.POST } )
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	//ajax 리스트 가져오기
	@ResponseBody
	@RequestMapping(value="/list", method = { RequestMethod.GET, RequestMethod.POST } )
	public List<GuestbookVo> list() {
		System.out.println("[ApiGuestbookController.List()]");
		
		List<GuestbookVo> guestbookList = guestbookService.getList();
		System.out.println(guestbookList);
		 
		return guestbookList;
		
		
	}
	
	// ajax 방명록 저장
	@ResponseBody
	@RequestMapping(value="/write", method = { RequestMethod.GET, RequestMethod.POST } )
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.write()]");
		System.out.println(guestbookVo);
		
		GuestbookVo resultVo= guestbookService.writeResultVo(guestbookVo);
		System.out.println(resultVo + "(APICONTROLLER)");
		return resultVo;
	}
	

}
