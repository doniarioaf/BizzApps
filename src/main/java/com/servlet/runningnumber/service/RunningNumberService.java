package com.servlet.runningnumber.service;

import java.sql.Timestamp;

public interface RunningNumberService {
	String getDocNumber(Long idcompany,String code,Timestamp currDate);
	String rollBackDocNumber(Long idcompany,String code);
}
