package com.servlet.bankaccount.service;

import java.util.List;

import com.servlet.bankaccount.entity.BankAccountData;
import com.servlet.bankaccount.entity.BodyBankAccount;
import com.servlet.shared.ReturnData;

public interface BankAccountService {
	List<BankAccountData> getListAll(Long idcompany,Long idbranch);
	BankAccountData getById(Long idcompany,Long idbranch,Long id);
	ReturnData saveBankAccount(Long idcompany,Long idbranch,Long iduser,BodyBankAccount body);
	ReturnData updateBankAccount(Long idcompany,Long idbranch,Long iduser,Long id,BodyBankAccount body);
	ReturnData deleteBankAccount(Long idcompany,Long idbranch,Long iduser,Long id);
	List<BankAccountData> getListActiveBankAccount(Long idcompany,Long idbranch);
}
