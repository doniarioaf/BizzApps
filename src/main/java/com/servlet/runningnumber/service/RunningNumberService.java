package com.servlet.runningnumber.service;

import com.servlet.runningnumber.entity.RunningNumber;
import com.servlet.shared.ReturnData;

import java.sql.Timestamp;
import java.util.List;

public interface RunningNumberService {
	String getDocNumber(Long idcompany,Long idbranch,String code,Timestamp currDate);
	String rollBackDocNumber(Long idcompany,Long idbranch,String code);
	ReturnData saveList(Long idcompany,Long idbranch);
}
