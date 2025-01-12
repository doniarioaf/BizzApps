package com.servlet.filedocument.service;

import com.servlet.filedocument.entity.BodyFileDocument;
import com.servlet.filedocument.entity.FileDocumentData;
import com.servlet.filedocument.entity.FileDocumentDataList;
import com.servlet.shared.ReturnData;

import java.sql.Timestamp;
import java.util.List;

public interface FileDocumentService {
    ReturnData save(Long idcompany, Long idbranch, Long iduser, Timestamp ts, BodyFileDocument body);
    ReturnData update(Long idcompany, Long idbranch, Long iduser, Timestamp ts, BodyFileDocument body);
    ReturnData uploadDoc(Long idcompany, Long idbranch, Long iduser, Timestamp ts, BodyFileDocument body);
    FileDocumentData getDetail(Long iddata,String menu,Long idcompany, Long idbranch);
    ReturnData uploadDocMany(Long idcompany, Long idbranch, Long iduser, Timestamp ts, BodyFileDocument body);
    List<FileDocumentDataList> getListDoc(Long iddata, String menu, Long idcompany, Long idbranch);
    ReturnData deleteByIdDoc(Long iddoc, Long idcompany, Long idbranch, Long iduser,String menu);
    FileDocumentData getDetailByIdDoc(Long iddoc,String menu,Long idcompany, Long idbranch);
}
