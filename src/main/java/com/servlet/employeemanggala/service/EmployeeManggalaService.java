package com.servlet.employeemanggala.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.servlet.employeemanggala.entity.BodyEmployeeManggala;
import com.servlet.employeemanggala.entity.DetailEmployeeManggalaInfoFamilyData;
import com.servlet.employeemanggala.entity.EmployeManggalaData;
import com.servlet.employeemanggala.entity.EmployeManggalaDataList;
import com.servlet.employeemanggala.entity.EmployeManggalaDataListParam;
import com.servlet.employeemanggala.entity.EmployeeManggalaTemplate;
import com.servlet.shared.ReturnData;

public interface EmployeeManggalaService {
	EmployeeManggalaTemplate employeeManggalaTemplate(long idcompany,long idbranch);
	EmployeManggalaDataListParam getListAll(Long idcompany,Long idbranch);
	EmployeManggalaDataListParam getListActive(Long idcompany,Long idbranch);
	EmployeManggalaData getById(Long idcompany,Long idbranch,Long id,Long iduser);
	ReturnData saveEmployeeManggala(Long idcompany,Long idbranch,Long iduser,BodyEmployeeManggala body);
	ReturnData updateEmployeeManggala(Long idcompany,Long idbranch,Long iduser,Long id,BodyEmployeeManggala body);
	ReturnData deleteEmployeeManggala(Long idcompany,Long idbranch,Long iduser,Long id);
	List<DetailEmployeeManggalaInfoFamilyData> getListDetailInfoFamily(Long idcompany,Long idbranch,Long id);
	ReturnData uploadImageEmployeeManggala(Long idcompany,Long idbranch,Long iduser,Long id,MultipartFile file);
	List<EmployeManggalaDataList> getListEmployeeSupir(Long idcompany,Long idbranch);
	
}
