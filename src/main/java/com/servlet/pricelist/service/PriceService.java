package com.servlet.pricelist.service;

import com.servlet.pricelist.entity.PriceListData;

import java.util.List;

public interface PriceService {

    List<PriceListData> getListAll(Long idcompany, Long idbranch, Long from, Long to);
}
