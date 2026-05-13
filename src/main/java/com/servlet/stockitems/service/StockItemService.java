package com.servlet.stockitems.service;

import com.servlet.shared.ReturnData;
import com.servlet.stockitems.entity.ParamCalculateQty;

public interface StockItemService {
    ReturnData tambah(Long idcompany, Long idbranch, Long idproduct,Long idcategoryproduct,String type, Long qty);
    ReturnData kurang(Long idcompany, Long idbranch,Long idproduct,Long idcategoryproduct,String type, Long qty);
    Long calculateQty(Long idcompany, Long idbranch, ParamCalculateQty param);
    Long calculateQtyUdangMasuk(Long idcompany, Long idbranch, ParamCalculateQty param);
}
