package com.servlet.pinjaman.service;

import com.servlet.filedocument.entity.FileDocumentData;
import com.servlet.pinjaman.entity.*;
import com.servlet.shared.ReturnData;

import java.sql.Date;
import java.util.List;

public interface PinjamanService {
    List<PinjamanList> getList(ParameterPinjaman param);
    PinjamanDetail getDetail(ParameterPinjaman param);
    ReturnData save(ParameterPinjaman param);
    ReturnData update(ParameterPinjaman param);
    ReturnData delete(ParameterPinjaman param);
    PinjamanTemplate getTemplate(ParameterPinjaman param);
    ReturnData uploadFileDoc(ParameterPinjaman param);
    FileDocumentData downloadFile(ParameterPinjaman param);
    Double calculateAmountByIdVendor(Long idcompany, Long idbranch, ParameterPinjaman param);
    Double calculateSisaPinjamanByIdVendor(Long idcompany, Long idbranch, Long idvendor,Date date);
    List<ReportKartuPinjaman> getListReportKartuPinjaman(Long idcompany, Long idbranch, ParamReportKartuPinjamanList param);
}
