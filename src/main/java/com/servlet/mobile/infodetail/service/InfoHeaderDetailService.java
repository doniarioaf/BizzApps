package com.servlet.mobile.infodetail.service;

import java.util.List;
import com.servlet.mobile.infodetail.entity.InfoHeaderDetail;
import com.servlet.mobile.infodetail.entity.InfoHeaderDetailData;
import com.servlet.shared.ReturnData;

public interface InfoHeaderDetailService {
	Object saveDataList(List<InfoHeaderDetail> list);
	List<InfoHeaderDetailData> getListData(long idinfoheader);
	Object deleteAllDataListPK(List<Long> listPK);
	Object updateDeleteInfoDetail(long id,boolean isdelete);
	void deleteAllInfoDetailByIdInfoHeader(long idinfoheader);
}
