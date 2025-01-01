package com.servlet.pelunasanpiutang.service;

import com.servlet.pelunasanpiutang.entity.BodyPelunasanPiutang;
import com.servlet.pelunasanpiutang.entity.PelunasanPiutangTemplate;
import com.servlet.shared.ReturnData;

public interface PelunasanPiutangService {
    PelunasanPiutangTemplate getTemplate(Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPelunasanPiutang body);
}
