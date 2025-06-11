package com.servlet.pinjaman.service;

import com.servlet.filedocument.entity.FileDocumentData;
import com.servlet.pinjaman.entity.ParameterPinjaman;
import com.servlet.pinjaman.entity.PinjamanDetail;
import com.servlet.pinjaman.entity.PinjamanList;
import com.servlet.pinjaman.entity.PinjamanTemplate;
import com.servlet.shared.ReturnData;

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
}
