package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/gbc")
public class GuestbookController {
	
	//Field
	@Autowired
	private GuestbookService guestbookService = new GuestbookService();

	//Method Start
	//------------------------------ AddList ------------------------------//
	@RequestMapping(value="/addlist", method = { RequestMethod.GET, RequestMethod.POST } )
	public String addlist(Model model) {
		
		//List Array from Database
		List<GuestbookVo> guestList = guestbookService.getList();
		
		//Attribute
		model.addAttribute("guestList", guestList);
		
		return "guestbook/addlist";
	}
	
	//------------------------------ Add(Insert Function) ------------------------------//
	@RequestMapping(value="/add", method = { RequestMethod.GET, RequestMethod.POST } )
	public String add(//@RequestParam("name") String name,
					  //@RequestParam("password") String password,
					  //@RequestParam("content") String content
					  @ModelAttribute GuestbookVo guestbookVo) {
		
		//Insert Dao Method
		guestbookService.guestbookInsert(guestbookVo);
		
		return "redirect:./addlist";
	}
	
	//------------------------------ Delete Form ------------------------------//
	@RequestMapping(value="/deleteForm", method = { RequestMethod.GET, RequestMethod.POST } )
	public String deleteForm(@RequestParam ("no") int no, Model model) {
		System.out.println(no);
		model.addAttribute("no", no);
		
		return "guestbook/deleteForm";
	}
	
	//------------------------------ Delete Function ------------------------------//
	@RequestMapping(value="/delete", method = { RequestMethod.GET, RequestMethod.POST } )
	public String delete(//@RequestParam("no") int no,
						 //@RequestParam("password") String password
						 @ModelAttribute GuestbookVo deletepw) {
		System.out.println("Delete Function");
		System.out.println(deletepw);
		//Vo
//		GuestbookVo deletepw = new GuestbookVo(no, password);
		System.out.println(deletepw);
		
		//Delete
		guestbookService.guestbookDelete(deletepw);
		
		return "redirect:./addlist";
	}
	
}