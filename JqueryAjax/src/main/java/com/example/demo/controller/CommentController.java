package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;
import com.example.demo.service.ExcelGenerator;

@RestController
public class CommentController {
	@Autowired
	private CommentService service;
	@Autowired
	private CommentRepository repository;
	@Autowired
	private ExcelGenerator excelGenerator;

	@GetMapping(value = "/list-comment")
	public List<Comment> fillAll() {
		return service.fillAll();
	}

	@DeleteMapping(value = "/delete/child/{id}/user/{userId}")
	public void deleteCommentChild(@PathVariable("id") Integer id, @PathVariable("userId") Integer userId) {
		service.deleteCommentChild(id, userId);
	}

	@DeleteMapping(value = "/delete/{id}/user/{userId}")
	public void deleteComment(@PathVariable("id") Integer id, @PathVariable("userId") Integer userId) {
		service.deleteComment(id, userId);
	}

	@GetMapping(value = "/check/{userId}")
	public String check(@PathVariable("userId") Integer userId) {
		if (service.checkButtonComment(userId)) {
			return "";
		} else {
			return "Khong duoc";
		}
	}

	@GetMapping(value = "/download/customers.xlsx")
	public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {
		List<Comment> customers = repository.findAll();
		ByteArrayInputStream in = ExcelGenerator.customersToExcel(customers);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=comments.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@PostMapping(value = "/upload-excel")
	public ResponseEntity<String> upload(@RequestParam(value = "file", required = false) MultipartFile file) {
		StringBuilder builder = new StringBuilder();
		try {
			File file1 = new File("D://upload/" + file.getOriginalFilename());
			FileOutputStream outputStream = new FileOutputStream(file1);
			outputStream.write(file.getBytes());
			outputStream.close();
			builder.append(file1);
			int index = builder.indexOf(".");
			String str = builder.substring(index + 1, builder.length());
			if (str.equals("xlsx") || str.equals("jpg")) {
				excelGenerator.readExcel(builder.toString());
				return new ResponseEntity<String>(builder.toString(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("file khong hop le", HttpStatus.OK);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
