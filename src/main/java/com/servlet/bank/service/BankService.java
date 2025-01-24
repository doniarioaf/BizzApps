package com.servlet.bank.service;

import com.servlet.bank.entity.BankDataList;
import com.servlet.bank.entity.BodyBank;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface BankService {
    List<BankDataList> getList(Long idcompany, Long idbranch, Long iduser);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyBank body);
}
