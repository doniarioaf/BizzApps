package com.servlet.area.service;

import com.servlet.area.entity.AreaDetail;
import com.servlet.area.entity.AreaList;
import com.servlet.area.entity.BodyArea;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface AreaService {
    List<AreaList> getList(Long idcompany, Long idbranch);
    AreaDetail getDetail(Long id,Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyArea body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyArea body);
    ReturnData delete(Long id,Long idcompany, Long idbranch, Long iduser);
}
