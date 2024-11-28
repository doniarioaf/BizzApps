package com.servlet.mappingstock.service;

import com.servlet.mappingstock.entity.BodyMappingStock;
import com.servlet.mappingstock.entity.MappingStockDetail;
import com.servlet.mappingstock.entity.MappingStockList;
import com.servlet.mappingstock.entity.MappingStockTemplateData;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface MappingStockService {
    List<MappingStockList> getListAll(Long idcompany, Long idbranch);
    MappingStockDetail getDetail(Long id, Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyMappingStock body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyMappingStock body);
    ReturnData delete(Long id,Long iduser);
    MappingStockTemplateData getTemplate(Long idcompany, Long idbranch);
}
