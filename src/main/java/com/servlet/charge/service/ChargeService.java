package com.servlet.charge.service;

import com.servlet.charge.entity.ChargeList;

import java.util.List;

public interface ChargeService {
    List<ChargeList> getListCharge(Long idcompany,Long idbranch);
}
