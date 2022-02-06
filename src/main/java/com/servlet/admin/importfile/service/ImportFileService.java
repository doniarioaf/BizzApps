package com.servlet.admin.importfile.service;

import com.servlet.shared.ReturnData;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public interface ImportFileService {
	ReturnData importFileExcelCustomerCallPlan(InputStream is,MultipartFile file);
}
