package com.servlet.deposit.service;

import com.servlet.deposit.entity.*;
import com.servlet.filedocument.entity.FileDocumentData;
import com.servlet.shared.ReturnData;
import org.springframework.web.multipart.MultipartFile;

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
    Double calculateSaldoDepositByIdVendorAndBeforeDate(Long idcompany, Long idbranch, Long idvendor,Long date);
    List<ReportKartuDeposit> getListReportKartuDeposit(Long idcompany, Long idbranch, ParamList param);
    ReturnData uploadFileDoc(Long id, MultipartFile file, Long idcompany, Long idbranch, Long iduser);
    FileDocumentData downloadFile(Long id, Long idcompany, Long idbranch);
}
