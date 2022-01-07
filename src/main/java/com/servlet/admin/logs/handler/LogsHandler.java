package com.servlet.admin.logs.handler;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servlet.admin.logs.entity.LogsActivity;
import com.servlet.admin.logs.repo.LogsRepo;
import com.servlet.admin.logs.service.LogsService;
import com.servlet.shared.ReturnData;

@Service
public class LogsHandler implements LogsService{
	@Autowired
	private LogsRepo repository;

	@Override
	public ReturnData saveLogs(LogsActivity logs) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		LogsActivity table = new LogsActivity();
		table.setIdcompany(logs.getIdcompany());
		table.setIdbranch(logs.getIdbranch());
		table.setUsername(logs.getUsername());
		table.setTanggal(ts);
		table.setActivity(logs.getActivity());
		table.setMessage(logs.getMessage());
		repository.saveAndFlush(table);
		return null;
	}

}
