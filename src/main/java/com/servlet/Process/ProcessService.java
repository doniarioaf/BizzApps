package com.servlet.Process;

import com.servlet.shared.ProcessReturn;

public interface ProcessService {
	ProcessReturn ProcessingFunction(String codepermission,Object data,String authorization);
	ProcessReturn ProcessingReadFunction(String codepermission,Object data,String authorization);
}
