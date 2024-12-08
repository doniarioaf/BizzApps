package com.servlet.inventori.service;

import com.servlet.inventori.entity.BodyInventori;
import com.servlet.inventori.entity.InventoriDataDetail;
import com.servlet.inventori.entity.ListDropdownData;
import com.servlet.inventori.entity.ListInventoriData;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface InventoriService {
    List<ListInventoriData> getListAll(Long idcompany, Long idbranch);
    InventoriDataDetail getDetail(Long id, Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyInventori body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyInventori body);
    ReturnData delete(Long id,Long iduser);
    List<ListDropdownData> getListDropDown(Long idcompany, Long idbranch);
}
