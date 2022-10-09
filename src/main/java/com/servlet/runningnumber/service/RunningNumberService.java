package com.servlet.runningnumber.service;

import java.sql.Timestamp;

public interface RunningNumberService {
	String getDocNumber(Long idcompany,Long idbranch,String code,Timestamp currDate);
}
