package com.servlet.pelunasanhutang.service;

import com.servlet.filedocument.entity.FileDocumentData;
import com.servlet.pelunasanhutang.entity.*;
import com.servlet.shared.ReturnData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PelunasanHutangService {
    List<PelunasanHutangDataList> getList(Long idcompany, Long idbranch, FilterParamPelunasanHutang param);
    HutangDataList getListHutang(Long idcompany, Long idbranch, FilterParamPelunasanHutang param);
    DetailHutangPR getDetailHutangPR(Long idcompany, Long idbranch,Long idpurchasereceive);
    DetailHutangCargo getDetailHutangCargo(Long idcompany, Long idbranch,Long idcargo);
    PelunasanHutangDataDetail getDetail(Long id, Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPelunasanHutang body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyPelunasanHutang body);
    ReturnData delete(Long id,Long idcompany, Long idbranch, Long iduser);
    List<PelunasanHutangReportStatusTagihanCargo> getListReportStatusTagihanCargo(Long idcompany, Long idbranch, FilterParamPelunasanHutang param);
    List<PelunasanHutangReportHutang> getListReportHutang(Long idcompany, Long idbranch, FilterParamPelunasanHutang param);
    List<PelunasanHutangDataNotJoin> getDataByIdCargo(Long idcompany, Long idbranch,Long idcargo);
    List<PelunasanHutangDataNotJoin> getDataByIdPr(Long idcompany, Long idbranch,Long idPr);
    ReturnData uploadFileDoc(Long id, MultipartFile file, Long idcompany, Long idbranch, Long iduser);
    FileDocumentData downloadFile(Long id, Long idcompany, Long idbranch);
}
