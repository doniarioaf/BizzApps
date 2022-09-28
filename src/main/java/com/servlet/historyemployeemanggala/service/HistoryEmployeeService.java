package com.servlet.historyemployeemanggala.service;

import com.servlet.customermanggala.entity.BodyCustomerManggala;
import com.servlet.historyemployeemanggala.entity.BodyHistoryEmployee;
import com.servlet.shared.ReturnData;

public interface HistoryEmployeeService {
	ReturnData saveHistoryEmployeeManggala(BodyHistoryEmployee body);
}
