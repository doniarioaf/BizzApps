package com.servlet.deposit.service;

import com.servlet.deposit.entity.BodyDeposit;
import com.servlet.deposit.entity.DepositDataNotJoin;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface DepositService {
    List<DepositDataNotJoin> getDepositNotJoinByIdVendor(Long idcompany, Long idbranch, Long idvendor);
    Double calculateAmountByIdVendor(Long idcompany, Long idbranch, Long idvendor);
    Double calculateSisaDepositByIdVendor(Long idcompany, Long idbranch, Long idvendor);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyDeposit body);
    ReturnData deleteRollBack(Long id);
    Double calculateSaldoDepositByIdVendorAndBeforeDateCreated(Long idcompany, Long idbranch, Long idvendor,Long date);
}
