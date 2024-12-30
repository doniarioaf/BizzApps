package com.servlet.cargo.service;

import com.servlet.cargo.entity.*;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface CargoService {
    List<CargoDataList> getList(Long idcompany, Long idbranch, ParamCargoSearch param);
    CargoTemplate getTemplate(Long idcompany, Long idbranch);
    CargoDetail getDetail(Long id,Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyCargo body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyCargo body);
    ReturnData delete(Long id,Long idcompany, Long idbranch, Long iduser);
}
