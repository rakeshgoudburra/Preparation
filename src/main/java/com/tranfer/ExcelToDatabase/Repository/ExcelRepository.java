package com.tranfer.ExcelToDatabase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranfer.ExcelToDatabase.model.Excel;

public interface ExcelRepository extends JpaRepository<Excel, Long> {

}
