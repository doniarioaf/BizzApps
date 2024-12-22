package com.servlet.stockadjusment.service;

import com.servlet.shared.ReturnData;
import com.servlet.stockadjusment.entity.BodyStockAdjusment;
import com.servlet.stockadjusment.entity.StockAdjsumentDataDetail;
import com.servlet.stockadjusment.entity.StockAdjusmentDataList;
import com.servlet.stockadjusment.entity.StockAdjusmentTemplate;

import java.util.List;

public interface StockAdjusmentService {
    List<StockAdjusmentDataList> getListAll(Long idcompany, Long idbranch, Long from, Long to);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyStockAdjusment body);
    ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyStockAdjusment body);
    ReturnData delete(Long id, Long idcompany, Long idbranch, Long iduser);
    StockAdjusmentTemplate getTemplate(Long idcompany, Long idbranch);
    StockAdjsumentDataDetail getDetail(Long idcompany, Long idbranch,Long id);
}
