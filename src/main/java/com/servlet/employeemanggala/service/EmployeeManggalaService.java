package com.servlet.employeemanggala.service;

import java.util.List;

import com.servlet.employeemanggala.entity.BodyEmployeeManggala;
import com.servlet.employeemanggala.entity.DetailEmployeeManggalaInfoFamilyData;
import com.servlet.employeemanggala.entity.EmployeManggalaData;
import com.servlet.employeemanggala.entity.EmployeeManggalaTemplate;
import com.servlet.shared.ReturnData;

public interface EmployeeManggalaService {
	EmployeeManggalaTemplate employeeManggalaTemplate(long idcompany,long idbranch);
	List<EmployeManggalaData> getListAll(Long idcompany,Long idbranch);
	List<EmployeManggalaData> getListActive(Long idcompany,Long idbranch);
	EmployeManggalaData getById(Long idcompany,Long idbranch,Long id);
	ReturnData saveEmployeeManggala(Long idcompany,Long idbranch,Long iduser,BodyEmployeeManggala body);
	ReturnData updateEmployeeManggala(Long idcompany,Long idbranch,Long iduser,Long id,BodyEmployeeManggala body);
	ReturnData deleteEmployeeManggala(Long idcompany,Long idbranch,Long iduser,Long id);
	List<DetailEmployeeManggalaInfoFamilyData> getListDetailInfoFamily(Long idcompany,Long idbranch,Long id);
	
}
