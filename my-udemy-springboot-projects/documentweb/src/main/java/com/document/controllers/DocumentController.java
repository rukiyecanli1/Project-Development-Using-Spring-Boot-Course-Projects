package com.document.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.document.entities.Document;
import com.document.repos.DocumentRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class DocumentController {

	@Autowired
	DocumentRepository repository;

	// when the user search for "http://localhost:8085/documentweb/displayUpload",
	// documentUpload.jsp page will be displayed to him
	@RequestMapping("/displayUpload")
	public String displayUpload(ModelMap modelMap) {
		System.out.println("inside of displayUpload method");
		List<Document> documents = repository.findAll();
		modelMap.addAttribute("documents", documents);
		return "documentUpload";
	}

	// when the user click the submit button this method will be invoked
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadDocument(@RequestParam("document") MultipartFile multipartFile, @RequestParam("id") long id,
			ModelMap modelMap) {
		System.out.println("before creating document");
		Document document = new Document();
		document.setId(id);
		document.setName(multipartFile.getOriginalFilename());
		try {
			document.setData(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		repository.save(document);
		List<Document> documents = repository.findAll();
		modelMap.addAttribute("documents", documents);
		return "documentUpload";
	}

	//when the user click the download button this method will be invoked
	//in this method the chooosen document will be downloaded to the "Downloads" part in the computer
	@RequestMapping(value = "/download")
	public StreamingResponseBody downloadDocument(@RequestParam("id") long documentId, 
			HttpServletResponse response) {
		Document document = repository.findById(documentId).get();
		byte[] data = document.getData();
		response.setHeader("Content-Disposition", String.format("attachment; filename=%s",document.getName()));
		// or response.setHeader("Content-Disposition", "attachment; filename="+document.getName());

		return outputStream -> {
			outputStream.write(data);
		};
	}
}
