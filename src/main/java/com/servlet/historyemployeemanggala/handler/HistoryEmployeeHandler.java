package com.servlet.historyemployeemanggala.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.historyemployeemanggala.entity.BodyHistoryEmployee;
import com.servlet.historyemployeemanggala.entity.HistoryEmployeeData;
import com.servlet.historyemployeemanggala.entity.HistoryEmployeeManggala;
import com.servlet.historyemployeemanggala.mapper.GetHistoryEmployeeManggala;
import com.servlet.historyemployeemanggala.repo.HistoryEmployeeManggalaRepo;
import com.servlet.historyemployeemanggala.service.HistoryEmployeeService;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class HistoryEmployeeHandler implements HistoryEmployeeService{
	
	@Autowired
	private HistoryEmployeeManggalaRepo repository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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

	@Override
	public List<HistoryEmployeeData> listHistoryEmployeeManggala(Long idcompany, Long idbranch, Long idemployee) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetHistoryEmployeeManggala().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.idemployee = ? order by tanggal desc ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch,idemployee};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetHistoryEmployeeManggala(), queryParameters);
	}

}
