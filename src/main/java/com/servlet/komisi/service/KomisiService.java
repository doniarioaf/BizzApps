package com.servlet.komisi.service;

import com.servlet.komisi.entity.*;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface KomisiService {
    List<KomisiList> getList(Long idcompany, Long idbranch, ParamKomisi param);
    KomisiTabData getAll(Long idcompany, Long idbranch, ParamKomisi param);
    KomisiTemplate getTemplate(Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyKomisi body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyKomisi body);
    ReturnData delete(Long id,Long idcompany, Long idbranch, Long iduser);

    KomisiDetail getDetail(Long idcompany, Long idbranch, Long id);
    PrintNotaKomisi getPrint(Long id,Long idcompany, Long idbranch,Long iduser, ParamPrintKomisi param);
    KomisiItemJoinHeader getDetailItemByIdPR(Long idcompany, Long idbranch, Long idpr);}
