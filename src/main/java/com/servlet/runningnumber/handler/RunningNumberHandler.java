package com.servlet.runningnumber.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
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
	public String getDocNumber(Long idcompany,Long idbranch, String code,Timestamp currDate) {
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
			return generateDocNumber(code,idbranch,runningNumber,currDate);
			}catch (Exception e) {
				return "";
			}
		}
		return "";
	}
	
	private String generateDocNumber(String code,Long idbranch,int number,Timestamp currDate) {
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
		}else {
			runningNumber = number+"";
		}
		
		String valNumber = "";
		if(!runningNumber.equals("")) {
//			String s = new SimpleDateFormat("yyMMdd").format(currDate);
			valNumber = code+"-"+idbranch+runningNumber;//+"-"+s;
		}
		return valNumber;
	}

	@Override
	public String rollBackDocNumber(Long idcompany,Long idbranch, String code) {
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
			table.setValue(table.getValue().longValue() - 1);
			repository.saveAndFlush(table);
			return "RollBack";
			}catch (Exception e) {
				return "";
			}
			
			
			
		}
		return "";
	}

	@Override
	public ReturnData saveList(Long idcompany,Long idbranch) {
		List<String> arr = new ArrayList<>();
		arr.add("PRC");
		arr.add("DPRC");
		arr.add("SA");
		arr.add("PL");
		arr.add("INV");
		arr.add("PH");
		List<RunningNumber> list = new ArrayList<>();
		for(String code : arr){
			RunningNumberPK pk = new RunningNumberPK();
			pk.setIdcompany(idcompany);
			pk.setIdbranch(idbranch);
			pk.setCode(code);
			RunningNumber table = new RunningNumber();
			table.setRunningNumberPK(pk);
			table.setValue(1L);
			list.add(table);
		}
		List<ValidationDataMessage> validations = new ArrayList<>();
		try{
			if(list.size() > 0){
				repository.saveAllAndFlush(list);
			}

		}catch (Exception e) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
			validations.add(msg);
		}
		ReturnData data = new ReturnData();
		data.setId(0L);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

}
