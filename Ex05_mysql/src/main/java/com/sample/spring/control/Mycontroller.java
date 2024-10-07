package com.sample.spring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.sample.spring.dao.ISimpleBbsDao;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class Mycontroller {
	@Autowired
	ISimpleBbsDao dao;
	
	@RequestMapping("/")
	public String root() {
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String listPage(Model model) {
		model.addAttribute("list",dao.listDao());
		model.addAttribute("count", dao.countDao());
		return "list";
	}
	
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Model model) {
		String sId = request.getParameter("id");
		model.addAttribute("dto",dao.viewDao(sId));
		return "view";
	}
	
	@RequestMapping("/writeForm")
	public String writer() {
		return "writeForm";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request) {
		dao.writeDao(request.getParameter("writer"), request.getParameter("title"), request.getParameter("content"));
	return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request) {
		dao.delete(request.getParameter("id"));
	return "redirect:list";
	}
	
}
