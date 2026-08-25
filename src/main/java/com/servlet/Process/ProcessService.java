package com.servlet.Process;

import com.servlet.shared.ProcessReturn;

import java.io.IOException;

public interface ProcessService {
	ProcessReturn ProcessingFunction(String codepermission,Object data,String authorization) throws IOException, InterruptedException;
	ProcessReturn ProcessingReadFunction(String codepermission,Object data,String authorization);
}
