package com.servlet.runningnumber.handler;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import com.servlet.runningnumber.entity.RunningNumber;
import com.servlet.runningnumber.entity.RunningNumberPK;
import com.servlet.runningnumber.repo.RunningNumberRepo;
import com.servlet.runningnumber.service.RunningNumberService;

@Service
public class RunningNumberHandler implements RunningNumberService{
	@Autowired
	private RunningNumberRepo repository;
	
	@Override
	public String getDocNumber(Long idcompany, Long idbranch, String code,Timestamp currDate) {
		// TODO Auto-generated method stub
		RunningNumberPK pk = new RunningNumberPK();
		pk.setIdcompany(idcompany);
		pk.setIdbranch(idbranch);
		pk.setCode(code);
		
		Optional<RunningNumber> tableOpt = repository.findById(pk);
		if(tableOpt.isPresent()) {
			try {
			RunningNumber table = tableOpt.get();
			int runningNumber = table.getValue().intValue();
			table.setValue(table.getValue().longValue() + 1);
			repository.saveAndFlush(table);
			return generateDocNumber(code,runningNumber,currDate);
			}catch (Exception e) {
				return "";
			}
			
			
			
		}
		return "";
	}
	
	private String generateDocNumber(String code,int number,Timestamp currDate) {
		String runningNumber = "";
		if(number > 0 && number < 10) {
			runningNumber = "00000"+number;
		}else if(number > 9 && number < 100) {
			runningNumber = "0000"+number;
		}else if(number > 99 && number < 1000) {
			runningNumber = "000"+number;
		}else if(number > 999 && number < 10000) {
			runningNumber = "00"+number;
		}else if(number > 9999 && number < 100000) {
			runningNumber = "0"+number;
		}else if(number > 99999 && number < 1000000) {
			runningNumber = number+"";
		}
		
		String valNumber = "";
		if(!runningNumber.equals("")) {
			String s = new SimpleDateFormat("yyMMdd").format(currDate);
			valNumber = code+"-"+runningNumber+"-"+s;
		}
		return valNumber;
	}

}
