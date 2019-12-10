package com.example.demo;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/test")
	public String hello() {
		return "Hello world! Welcome to start SpringBoot!";
	}

	/**
	 * 预览pdf文件
	 * 
	 * @param fileName
	 */
	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	public void pdfStreamHandler(String fileName, HttpServletRequest request, HttpServletResponse response) {
		File file = new File("D:\\test\\test.pdf");
		// File file = new File("D:\\test\\test.pdf" + fileName);
		if (file.exists()) {
			byte[] data = null;
			try {
				FileInputStream input = new FileInputStream(file);
				data = new byte[input.available()];
				input.read(data);
				response.getOutputStream().write(data);
				input.close();
			} catch (Exception e) {
				System.out.println("系统处理异常");
			}

		} else {
			return;
		}
	}
}