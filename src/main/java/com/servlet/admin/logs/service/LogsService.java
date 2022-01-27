package com.servlet.admin.logs.service;

import com.servlet.admin.logs.entity.LogsActivity;
import com.servlet.shared.ReturnData;

public interface LogsService {
	ReturnData saveLogs(LogsActivity logs);
}
