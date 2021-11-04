package com.servlet.Process;

public interface ProcessService {
	Object ProcessingFunction(String codepermission,Object data,String authorization);
	Object ProcessingReadFunction(String codepermission,Object data,String authorization);
}
