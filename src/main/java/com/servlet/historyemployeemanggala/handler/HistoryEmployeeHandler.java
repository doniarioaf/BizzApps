package com.servlet.historyemployeemanggala.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servlet.employeemanggala.entity.EmployeeManggala;
import com.servlet.employeemanggala.repo.EmployeeManggalaRepo;
import com.servlet.historyemployeemanggala.entity.BodyHistoryEmployee;
import com.servlet.historyemployeemanggala.entity.HistoryEmployeeManggala;
import com.servlet.historyemployeemanggala.repo.HistoryEmployeeManggalaRepo;
import com.servlet.historyemployeemanggala.service.HistoryEmployeeService;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class HistoryEmployeeHandler implements HistoryEmployeeService{
	
	@Autowired
	private HistoryEmployeeManggalaRepo repository;
	
	@Override
	public ReturnData saveHistoryEmployeeManggala(BodyHistoryEmployee body) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		HistoryEmployeeManggala table = new HistoryEmployeeManggala();
		table.setIdcompany(body.getIdcompany());
		table.setIdbranch(body.getIdbranch());
		table.setIdemployee(body.getIdemployee());
		table.setJabatan(body.getJabatan());
		table.setStatusemployee(body.getStatusemployee());
		table.setGaji(body.getGaji());
		table.setIduser(body.getIduser());
		table.setTanggal(ts);
		long idsave = repository.saveAndFlush(table).getId();
		
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(true);
		data.setValidations(new ArrayList<ValidationDataMessage>());
		return data;
	}

}
