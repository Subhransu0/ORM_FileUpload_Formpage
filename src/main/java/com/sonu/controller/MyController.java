package com.sonu.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sonu.entites.User;
import com.sonu.service.UserService;

@Controller
public class MyController {

	@Autowired
	private UserService userService;

	@RequestMapping("/myHome")
	public String homepage() {
		return "home";
	}

	@RequestMapping("/Myregister")
	public String register() {
		return "register";
	}

	@RequestMapping(path = "/createuser", method = RequestMethod.POST)
	public String formData(@ModelAttribute User user, @RequestParam("img") CommonsMultipartFile file, Model model,
			HttpSession session) {
		
		

//		System.out.println(file.getSize());
//		System.out.println(file.getContentType());
//		System.out.println(file.getName());
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getStorageDescription());

		byte[] data = file.getBytes();
		String path = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"
				+ File.separator + "image" + File.separator + file.getOriginalFilename();

		System.out.println(path);
		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();
			System.out.println("success");
			model.addAttribute("msg" , "uploadsuccessfull");
			model.addAttribute("image" , file.getOriginalFilename());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");

		}

		userService.saveUser(user);
		return "success";

	}

}
