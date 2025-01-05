package com.servlet.cargo.service;

import com.servlet.cargo.entity.*;
import com.servlet.filedocument.entity.FileDocumentData;
import com.servlet.pelunasanhutang.entity.ReportPelunasanHutangDocumentHutang;
import com.servlet.shared.ReturnData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CargoService {
    List<CargoDataList> getList(Long idcompany, Long idbranch, ParamCargoSearch param);
    CargoTemplate getTemplate(Long idcompany, Long idbranch);
    CargoDetail getDetail(Long id,Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyCargo body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyCargo body);
    ReturnData delete(Long id,Long idcompany, Long idbranch, Long iduser);
    ReturnData uploadFileDoc(Long id, MultipartFile file, Long idcompany, Long idbranch, Long iduser);
    FileDocumentData downloadFile(Long id,Long idcompany, Long idbranch);

    ReturnData updateOustandingTambah(Long id,Double bayar);
    ReturnData updateOustandingKurang(Long id,Double bayar);

    List<CargoDataNotJoin> getListCargoPelunasanHutang(Long idcompany, Long idbranch, ParamCargoSearch param);
    List<CargoDataReportStatusTagihanCargo> getListCargoReportStatusTagihanCargo(Long idcompany, Long idbranch, ParamCargoSearch param);
    List<ReportPelunasanHutangDocumentHutang> getListCargoReportHutang(Long idcompany, Long idbranch, ParamCargoSearch param);
}
