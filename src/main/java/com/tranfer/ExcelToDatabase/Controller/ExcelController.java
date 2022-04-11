package com.tranfer.ExcelToDatabase.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.multipart.MultipartFile;

import com.tranfer.ExcelToDatabase.Service.ExcekService;
import com.tranfer.ExcelToDatabase.helper.Helper;
import com.tranfer.ExcelToDatabase.model.Excel;

@RestController
@CrossOrigin("*")
public class ExcelController {

	private ExcekService service;

	public ExcelController(ExcekService service) {
		super();
		this.service = service;
	}

	@PostMapping("/excel/file")
	public ResponseEntity<?> upload(@RequestParam MultipartFile file) {

		if (Helper.checkExcelFormat(file)) {
			service.save(file);
			return ResponseEntity.ok("successfully uploaded");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("file is not a excel file ");

	}
	@GetMapping("/getData")
	public List<Excel> getAllExcelData(){
		return service.getAllExcels();
		
	}
	@PostMapping("/insert")
	public Excel insertExcelRow(@RequestBody Excel excel) {
		return service.save(excel);
		
	}
}
