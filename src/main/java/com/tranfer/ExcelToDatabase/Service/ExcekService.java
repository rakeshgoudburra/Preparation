package com.tranfer.ExcelToDatabase.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tranfer.ExcelToDatabase.Repository.ExcelRepository;
import com.tranfer.ExcelToDatabase.helper.Helper;
import com.tranfer.ExcelToDatabase.model.Excel;

@Service
public class ExcekService {

	private ExcelRepository repository;
	
	
	public ExcekService(ExcelRepository repository) {
		super();
		this.repository = repository;
	}


	public void save(MultipartFile file)  {
		
	try {
		List<Excel> excels = Helper.convertExcelFileToExcelPOJO(file.getInputStream());
		repository.saveAll(excels);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public List<Excel> getAllExcels(){
		return repository.findAll();
		
	}
	
}
