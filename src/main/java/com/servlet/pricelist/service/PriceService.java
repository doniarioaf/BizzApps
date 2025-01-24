package com.servlet.pricelist.service;

import com.servlet.pricelist.entity.*;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface PriceService {

    List<PriceListData> getListAll(Long idcompany, Long idbranch, Long from, Long to);
    PriceListTemplate getTemplateData(Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPriceList body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyPriceList body);
    ReturnData delete(Long id,Long idcompany, Long idbranch,Long iduser);
    PriceListDetail getDetail(Long id,Long idcompany, Long idbranch);
    PriceItemsDataForTemplate getDataPriceByDate(Long idcompany, Long idbranch,Long priceDate);
}
