package com.servlet.admin.company.handler;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.servlet.admin.company.entity.BodyCompany;
import com.servlet.admin.company.entity.BodyCompanyy;
import com.servlet.admin.company.entity.Company;
import com.servlet.admin.company.entity.CompanyData;
import com.servlet.admin.company.entity.CompanyDataDetail;
import com.servlet.admin.company.repo.CompanyRepo;
import com.servlet.admin.company.service.CompanyService;
import com.servlet.admin.companybranch.entity.CompanyBranch;
import com.servlet.admin.companybranch.entity.CompanyBranchData;
import com.servlet.admin.companybranch.entity.CompanyBranchPK;
import com.servlet.admin.companybranch.service.CompanyBranchService;
import com.servlet.login.api.LoginApi;
import com.servlet.security.entity.LicenseData;
import com.servlet.shared.AESEncryptionDecryption;
import com.servlet.shared.AESEncryptionDecryptionLicense;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConvertJson;
import com.servlet.shared.GlobalFunc;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.user.entity.UserPermissionData;

@Service
public class CompanyHandler implements CompanyService{
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyHandler.class);
	@Autowired
	private CompanyRepo repository;
	@Autowired
	private CompanyBranchService companyBranchService;
	
	@Override
	public List<Company> getListCompanyActive() {
		// TODO Auto-generated method stub
		return repository.getListCompanyActive();
	}

	@Override
	public List<Company> getAllListCompany() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Company getCompanyByID(long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public CompanyData saveCompany(BodyCompany company) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		Company table = new Company();
		table.setNama(company.getNama());
		table.setCode(company.getCode());
		table.setContactnumber(company.getContactnumber());
		table.setDisplayname(company.getDisplayname());
		table.setAddress(company.getAddress());
		table.setIsactive(true);
		table.setEmail(company.getEmail());
		table.setCreated(ts);
		table.setModified(ts);
		table.setIsdelete(false);
		
		Company returntable = repository.saveAndFlush(table);
		if(company.getBranches().length > 0) {
			for (int i = 0; i < company.getBranches().length; i++) {
				CompanyBranchPK pk = new CompanyBranchPK();
				pk.setIdcompany(returntable.getId());
				pk.setIdbranch(company.getBranches()[i]);
				companyBranchService.saveCompanyBranch(pk);
			}
		}
		
		CompanyData data = new CompanyData();
		data.setId(returntable.getId());
		data.setName(company.getNama());
		return data;
	}

	@Override
	public CompanyData updateCompany(long id, BodyCompany company) {
		// // TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		Company table = repository.getById(id);
		table.setNama(company.getNama());
		table.setContactnumber(company.getContactnumber());
		table.setDisplayname(company.getDisplayname());
		table.setAddress(company.getAddress());
//		table.setIsactive(company.isIsactive());
		table.setEmail(company.getEmail());
		table.setModified(ts);
		Company returntable = repository.saveAndFlush(table);
		CompanyData data = new CompanyData();
		data.setId(returntable.getId());
		data.setName(company.getNama());
		
		List<CompanyBranchPK> listdelete = new ArrayList<CompanyBranchPK>();
		List<CompanyBranchData> listcompaybranch = new ArrayList<CompanyBranchData>(companyBranchService.getListCompanyBranch(id));
		if(listcompaybranch.size() > 0) {
			for(CompanyBranchData companyBranchData : listcompaybranch) {
				CompanyBranchPK pk = new CompanyBranchPK();
				pk.setIdcompany(returntable.getId());
				pk.setIdbranch(companyBranchData.getId());
				listdelete.add(pk);
			}
			companyBranchService.deleteAllCompanyBranchByListPK(listdelete);
		}
		
		
		List<CompanyBranch> listsave = new ArrayList<CompanyBranch>();
		if(company.getBranches().length > 0) {
			for (int i = 0; i < company.getBranches().length; i++) {
				CompanyBranchPK pk = new CompanyBranchPK();
				pk.setIdcompany(returntable.getId());
				pk.setIdbranch(company.getBranches()[i]);
				CompanyBranch cb = new CompanyBranch();
				cb.setCompanyBranchPK(pk);
				listsave.add(cb);
			}
			companyBranchService.saveCompanyBranchList(listsave);
		}
		
		
		return data;
	}

	@Override
	public CompanyDataDetail getCompanyAndCompanyBranchByID(long id) {
		// TODO Auto-generated method stub
		CompanyDataDetail data = new CompanyDataDetail();
		Optional<Company> value = repository.findById(id);
		if(value.isPresent()) {
			List<CompanyBranchData> listcompaybranch = new ArrayList<CompanyBranchData>(companyBranchService.getListCompanyBranch(id));
			data.setCompany(value.get());
			data.setListbranches(listcompaybranch);
		}else {
			data.setCompany(null);
			data.setListbranches(null);
		}
		return data;
	}

	@Override
	public ReturnData activatedCompany(long id) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		Company table = repository.getById(id);
		table.setIsactive(true);
		table.setModified(ts);
		Company returntable = repository.saveAndFlush(table);
		
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		return data;
	}

	@Override
	public ReturnData unActivatedCompany(long id) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		Company table = repository.getById(id);
		table.setIsactive(false);
		table.setModified(ts);
		Company returntable = repository.saveAndFlush(table);
		
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		return data;
	}

	@Override
	public ReturnData deleteCompany(long id) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		Company table = repository.getById(id);
		table.setIsdelete(true);
		table.setModified(ts);
		Company returntable = repository.saveAndFlush(table);
		
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		return data;
	}

	@Override
	public ReturnData updateCompanyy(BodyCompanyy company) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		AESEncryptionDecryptionLicense aesEncryptionDecryption = new AESEncryptionDecryptionLicense();
		Company table = repository.getById(company.getIdcompany());
		int idreturn = 0;
		if(table != null) {
			try {
				Timestamp ts = GlobalFunc.addDaysByType(new Timestamp(new Date().getTime()), company.getWaktu(), company.getType()) ;
				Timestamp timeexp = GlobalFunc.setFormatDate(ts, "yyyy-MM-dd");
				LicenseData lic = new LicenseData();
				lic.setIdcompany(company.getIdcompany());
				lic.setExpired(timeexp);
				lic.setLimituserweb(company.getJumlahweb());
				lic.setLimitusermobile(company.getJumlahmobile());
				String encryptedPassToken = aesEncryptionDecryption.encrypt(new ConvertJson().toJsonString(lic));
				table.setLicense(encryptedPassToken);
				repository.saveAndFlush(table);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.DATA_NOT_FOUND,"Company Tidak Ditemukan");
			validations.add(msg);
		}
		ReturnData data = new ReturnData();
		data.setId(idreturn);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

}
