package com.servlet.pelunasanpiutang.service;

import com.servlet.filedocument.entity.FileDocumentData;
import com.servlet.pelunasanpiutang.entity.*;
import com.servlet.shared.ReturnData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PelunasanPiutangService {
    PelunasanPiutangTemplate getTemplate(Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPelunasanPiutang body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyPelunasanPiutang body);
    ReturnData delete(Long id,Long idcompany, Long idbranch, Long iduser);
    List<PelunasanPiutangList> getPelunasanPiutangList(Long idcompany, Long idbranch, FilterParamPelunasanPiutang param);
    PelunasanPiutangDetail getDetail(Long id, Long idcompany, Long idbranch);
    List<ReportPelunasanPiutang> getReportPelunasanPiutang(Long idcompany, Long idbranch, FilterParamPelunasanPiutang param);
    PelunasanPiutangItemJoinHeader getPelunasanPiutangItemByIdInvoice(Long idcompany, Long idbranch, Long idinvoice);
    ReturnData uploadFileDoc(Long id, MultipartFile file, Long idcompany, Long idbranch, Long iduser);
    FileDocumentData downloadFile(Long id, Long idcompany, Long idbranch);
}
