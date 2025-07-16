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
		pk.setYear(0);
		pk.setMonth(0);
		
		Optional<RunningNumber> tableOpt = repository.findById(pk);
		if(tableOpt.isPresent()) {
			try {
			RunningNumber table = tableOpt.get();
			int runningNumber = table.getValue().intValue();
			table.setValue(table.getValue().longValue() + 1);
			repository.saveAndFlush(table);
			return generateDocNumber(code,idbranch,runningNumber,currDate,0,0);
			}catch (Exception e) {
				return "";
			}
		}
		return "";
	}

	@Override
	public String getDocNumberWithYearMonth(Long idcompany, Long idbranch, String code, Timestamp currDate, Integer year, Integer month) {
		// TODO Auto-generated method stub
		RunningNumberPK pk = new RunningNumberPK();
		pk.setIdcompany(idcompany);
		pk.setIdbranch(idbranch);
		pk.setCode(code);
		pk.setYear(year.intValue());
		pk.setMonth(month.intValue());

		Optional<RunningNumber> tableOpt = repository.findById(pk);
		if(tableOpt.isPresent()) {
			try {
				RunningNumber table = tableOpt.get();
				int runningNumber = table.getValue().intValue();
				table.setValue(table.getValue().longValue() + 1);
				repository.saveAndFlush(table);
				return generateDocNumber(code,idbranch,runningNumber,currDate,year,month);
			}catch (Exception e) {
				return "";
			}
		}else{
			int runningNumber = 1;
			RunningNumber table = new RunningNumber();
			table.setRunningNumberPK(pk);
			table.setValue(2L);
			repository.saveAndFlush(table);
			return generateDocNumber(code,idbranch,runningNumber,currDate,year,month);
		}
//		return "";
	}

	private String generateDocNumber(String code,Long idbranch,int number,Timestamp currDate, int year, int month) {
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
			if(code.equals(ConstantCodeDocument.DOC_INVOICE)){
				String bulan = "";
				if(month > 9){
					bulan = month+"";
				}else{
					bulan = "0"+month;
				}
				valNumber = code+"-"+bulan+"-"+year+"/"+idbranch+runningNumber;
			}
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
		pk.setYear(0);
		pk.setMonth(0);
		
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
	public String rollBackDocNumberWithYearMonth(Long idcompany,Long idbranch, String code,Integer year, Integer month) {
		// TODO Auto-generated method stub
		RunningNumberPK pk = new RunningNumberPK();
		pk.setIdcompany(idcompany);
		pk.setIdbranch(idbranch);
		pk.setCode(code);
		pk.setYear(year.intValue());
		pk.setMonth(month.intValue());

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
		arr.add(ConstantCodeDocument.DOC_PURCHASERECEIVE);
		arr.add(ConstantCodeDocument.DOC_DRAFTPURCHASERECEIVE);
		arr.add(ConstantCodeDocument.DOC_STOCKADJUSMENT);
		arr.add(ConstantCodeDocument.DOC_PACKINGLIST);
//		arr.add(ConstantCodeDocument.DOC_INVOICE); //jangan dibuka remarknya
		arr.add(ConstantCodeDocument.DOC_PELUNASANHUTANG);
		arr.add(ConstantCodeDocument.DOC_PELUNASANPIUTANG);
		arr.add(ConstantCodeDocument.DOC_DEPOSIT);
		arr.add(ConstantCodeDocument.DOC_KOMISI);
		arr.add(ConstantCodeDocument.DOC_PINJAMAN);
		List<RunningNumber> list = new ArrayList<>();
		for(String code : arr){
			RunningNumberPK pk = new RunningNumberPK();
			pk.setIdcompany(idcompany);
			pk.setIdbranch(idbranch);
			pk.setCode(code);
			pk.setYear(0);
			pk.setMonth(0);
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
