package com.pranavkapoorr.gdriveupload.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.pranavkapoorr.gdriveupload.service.*;

@Controller
public class MainController {

	private Logger logger = LoggerFactory.getLogger(MainController.class);


	@Autowired
	DriveService driveService;

	/**
	 * Handles the root request. Checks if user is already authenticated via SSO.
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/")
	public String showHomePage() throws Exception {
		
			return "redirect:/home";
	
	}

	
	@GetMapping("/login")
	public String goToLogin() {
		return "index.html";
	}

	
	@GetMapping("/home")
	public String goToHome() {
		return "home.html";
	}


	@PostMapping("/upload")
	public String uploadFile(HttpServletRequest request,@RequestParam("multipartFile") MultipartFile file) throws Exception {
		
        com.google.api.services.drive.model.File file2  = driveService.upLoadFile(
        		file.getName(), 
        		file.getOriginalFilename(),"image/jpg");
		try {
			System.err.println(file2.toPrettyString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home.html";
	}
}