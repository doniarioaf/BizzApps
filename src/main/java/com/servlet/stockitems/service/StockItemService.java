package com.servlet.stockitems.service;

import com.servlet.shared.ReturnData;

public interface StockItemService {
    ReturnData tambah(Long idcompany, Long idbranch, Long idproduct,Long idcategoryproduct,String type, Long qty);
    ReturnData kurang(Long idcompany, Long idbranch,Long idproduct,Long idcategoryproduct,String type, Long qty);
}
