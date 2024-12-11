package com.servlet.deposit.service;

import com.servlet.deposit.entity.*;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface DepositService {
    List<DepositDataNotJoin> getDepositNotJoinByIdVendor(Long idcompany, Long idbranch, Long idvendor);
    List<DepositList> getList(Long idcompany, Long idbranch, ParamList param);
    DepositDetail getDetail(Long id,Long idcompany, Long idbranch);
    Double calculateAmountByIdVendor(Long idcompany, Long idbranch, Long idvendor);
    Double calculateSisaDepositByIdVendor(Long idcompany, Long idbranch, Long idvendor);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyDeposit body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyDeposit body);
    ReturnData delete(Long id,Long idcompany, Long idbranch, Long iduser);
    DepositTemplate getTemplate(Long idcompany, Long idbranch);
    ReturnData deleteRollBack(Long id);
    Double calculateSaldoDepositByIdVendorAndBeforeDateCreated(Long idcompany, Long idbranch, Long idvendor,Long date);
}
