package com.servlet.historyemployeemanggala.service;

import java.util.List;

import com.servlet.historyemployeemanggala.entity.BodyHistoryEmployee;
import com.servlet.historyemployeemanggala.entity.HistoryEmployeeData;
import com.servlet.shared.ReturnData;

public interface HistoryEmployeeService {
	ReturnData saveHistoryEmployeeManggala(BodyHistoryEmployee body);
	List<HistoryEmployeeData> listHistoryEmployeeManggala(Long idcompany,Long idbranch,Long idemployee);
}
