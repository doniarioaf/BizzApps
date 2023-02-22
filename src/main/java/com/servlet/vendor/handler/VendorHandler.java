package com.servlet.vendor.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.address.service.CityService;
import com.servlet.address.service.DistrictService;
import com.servlet.address.service.PostalCodeService;
import com.servlet.address.service.ProvinceService;
import com.servlet.parameter.service.ParameterService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.vendor.entity.BodyDetailVendorBank;
import com.servlet.vendor.entity.BodyDetailVendorContact;
import com.servlet.vendor.entity.BodySearchVendor;
import com.servlet.vendor.entity.BodyVendor;
import com.servlet.vendor.entity.DetailVendorBank;
import com.servlet.vendor.entity.DetailVendorBankData;
import com.servlet.vendor.entity.DetailVendorBankPK;
import com.servlet.vendor.entity.DetailVendorContact;
import com.servlet.vendor.entity.DetailVendorContactData;
import com.servlet.vendor.entity.DetailVendorContactPK;
import com.servlet.vendor.entity.Vendor;
import com.servlet.vendor.entity.VendorData;
import com.servlet.vendor.entity.VendorListData;
import com.servlet.vendor.entity.VendorTemplate;
import com.servlet.vendor.mapper.GetDetailVendorBankData;
import com.servlet.vendor.mapper.GetDetailVendorContactData;
import com.servlet.vendor.mapper.GetVendorData;
import com.servlet.vendor.mapper.GetVendorListData;
import com.servlet.vendor.repo.DetailVendorBankRepo;
import com.servlet.vendor.repo.DetailVendorContactRepo;
import com.servlet.vendor.repo.VendorRepo;
import com.servlet.vendor.service.VendorService;
import com.servlet.vendorcategory.service.VendorCategoryService;

@Service
public class VendorHandler implements VendorService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private VendorRepo repository;
	@Autowired
	private CityService cityService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private PostalCodeService postalCodeService;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private VendorCategoryService vendorCategoryService;
	@Autowired
	private DetailVendorBankRepo detailVendorBankRepo;
	@Autowired
	private DetailVendorContactRepo detailVendorContactRepo;
	@Autowired
	private DistrictService districtService;
	
	@Override
	public List<VendorListData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetVendorListData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetVendorListData(), queryParameters);
	}

	@Override
	public List<VendorListData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetVendorListData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetVendorListData(), queryParameters);
	}

	@Override
	public VendorTemplate getTemplate(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		return getVendorTemplate(idcompany,idbranch);
	}
	
	private VendorTemplate getVendorTemplate(long idcompany, long idbranch) {
		VendorTemplate data = new VendorTemplate();
		data.setCityOptions(cityService.getListCity());
		data.setDistrictOptions(districtService.getListDistrict());
		data.setProvinceOptions(provinceService.getListProvince());
		data.setPanggilanOptions(parameterService.getListParameterByGrup("PANGGILAN"));
		data.setBadanUsahaOptions(parameterService.getListParameterByGrup("LEVEL_PERUSAHAAN"));
		data.setVendorCategoryOptions(vendorCategoryService.getListActive(idcompany, idbranch));
		return data;
	}

	@Override
	public VendorData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetVendorData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<VendorData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetVendorData(), queryParameters);
		if(list != null && list.size() > 0) {
			VendorData val = list.get(0);
			val.setTemplate(getVendorTemplate(idcompany, idbranch));
			val.setDetailsbank(getListBank(id,idcompany,idbranch));
			val.setDetailscontact(getListContact(id, idcompany, idbranch));
			return val;
		}
		return null;
	}
	
	private VendorData getCheckById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetVendorData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<VendorData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetVendorData(), queryParameters);
		if(list != null && list.size() > 0) {
			VendorData val = list.get(0);			
			return val;
		}
		return null;
	}

	@Override
	public ReturnData saveVendor(Long idcompany, Long idbranch, Long iduser, BodyVendor body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				Timestamp ts = new Timestamp(new Date().getTime());
				Vendor table = new Vendor();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setIdvendorcategory(body.getIdvendorcategory());
				table.setCode(body.getCode());
				table.setJenisbadanusaha(body.getJenisbadanusaha());
				table.setNama(body.getNama());
				table.setAlias(body.getAlias());
				table.setNpwp(body.getNpwp());
				table.setAddress(body.getAddress());
				table.setProvinsi(body.getProvinsi());
				table.setKota(body.getKota());
				table.setKodepos(body.getKodepos());
				table.setDistrict(body.getDistrict());
				table.setIsactive(body.isIsactive());
				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setCreateddate(ts);
				idsave = repository.saveAndFlush(table).getId();
				
				putDetailBank(body.getDetailsbank(),idcompany,idbranch,idsave,"ADD");
				putDetailContact(body.getDetailscontact(),idcompany,idbranch,idsave,"ADD");
				
			}catch (Exception e) {
				// TODO: handle exception
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
				validations.add(msg);
				e.printStackTrace();
			}
			
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public ReturnData updateVendor(Long idcompany, Long idbranch, Long iduser, Long id, BodyVendor body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		VendorData value = getCheckById(idcompany,idbranch,id);
		if(value != null ) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					Vendor table = repository.getById(id);
					table.setIdvendorcategory(body.getIdvendorcategory());
					table.setCode(body.getCode());
					table.setJenisbadanusaha(body.getJenisbadanusaha());
					table.setNama(body.getNama());
					table.setAlias(body.getAlias());
					table.setNpwp(body.getNpwp());
					table.setAddress(body.getAddress());
					table.setProvinsi(body.getProvinsi());
					table.setKota(body.getKota());
					table.setKodepos(body.getKodepos());
					table.setDistrict(body.getDistrict());
					table.setIsactive(body.isIsactive());
					
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					idsave = repository.saveAndFlush(table).getId();
					
					putDetailBank(body.getDetailsbank(),idcompany,idbranch,idsave,"EDIT");
					putDetailContact(body.getDetailscontact(),idcompany,idbranch,idsave,"EDIT");
				}catch (Exception e) {
					// TODO: handle exception
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
					validations.add(msg);
					e.printStackTrace();
				}
			}
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public ReturnData deleteVendor(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		VendorData value = getCheckById(idcompany,idbranch,id);
		if(value != null) {
			Timestamp ts = new Timestamp(new Date().getTime());
			Vendor table = repository.getById(id);
			table.setIsdelete(true);
			table.setDeleteby(iduser.toString());
			table.setDeletedate(ts);
			idsave = repository.saveAndFlush(table).getId();
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}
	
	private String putDetailBank(BodyDetailVendorBank[] details,Long idcompany, Long idbranch,long idsave,String action) {
		if(action.equals("EDIT")) {
			detailVendorBankRepo.deleteAllVendorBank(idsave, idcompany, idbranch);
		}
		
		if(details != null) {
			if(details.length > 0) {
				int counting = 1;
				for(int i=0; i < details.length; i++) {
					BodyDetailVendorBank det = details[i];
					DetailVendorBankPK pk = new DetailVendorBankPK();
					pk.setIdvendor(idsave);
					pk.setIdcompany(idcompany);
					pk.setIdbranch(idbranch);
					pk.setCountdetail(counting);
					
					DetailVendorBank table = new DetailVendorBank();
					table.setDetailVendorBankPK(pk);
					table.setNorek(det.getNorek());
					table.setAtasnama(det.getAtasnama());
					table.setBank(det.getBank());
					detailVendorBankRepo.saveAndFlush(table);
					counting++;
				}
			}
			
		}
		
		return "";
	}
	
	private String putDetailContact(BodyDetailVendorContact[] details,Long idcompany, Long idbranch,long idsave,String action) {
		if(action.equals("EDIT")) {
			detailVendorContactRepo.deleteAllVendorContact(idsave, idcompany, idbranch);
		}
		
		if(details != null) {
			if(details.length > 0) {
				int counting = 1;
				for(int i=0; i < details.length; i++) {
					BodyDetailVendorContact det = details[i];
					DetailVendorContactPK pk = new DetailVendorContactPK();
					pk.setIdvendor(idsave);
					pk.setIdcompany(idcompany);
					pk.setIdbranch(idbranch);
					pk.setCountdetail(counting);
					
					DetailVendorContact table = new DetailVendorContact();
					table.setDetailVendorContactPK(pk);
					table.setPanggilan(det.getPanggilan());
					table.setNamakontak(det.getNamakontak());
					table.setNocontacthp(det.getNocontacthp());
					table.setEmail(det.getEmail());
					table.setContactofficenumber(det.getContactofficenumber());
					table.setExtention(det.getExtention());
					detailVendorContactRepo.saveAndFlush(table);
					counting++;
				}
			}
			
		}
		
		return "";
	}
	
	private List<DetailVendorBankData> getListBank(Long id,Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailVendorBankData().schema());
		sqlBuilder.append(" where data.idvendor = ? and data.idcompany = ? and data.idbranch = ?  ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailVendorBankData(), queryParameters);
	}
	
	private List<DetailVendorContactData> getListContact(Long id,Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailVendorContactData().schema());
		sqlBuilder.append(" where data.idvendor = ? and data.idcompany = ? and data.idbranch = ?  ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailVendorContactData(), queryParameters);
	}

	@Override
	public List<VendorData> checkVendorCategory(Long idcompany, Long idbranch, Long idvendorcategory) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetVendorData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.idvendorcategory = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch,idvendorcategory};
		List<VendorData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetVendorData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public List<VendorListData> getListSearchVendor(Long idcompany, Long idbranch, BodySearchVendor body) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetVendorListData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		sqlBuilder.append(" and lower(data.nama) like '%"+body.getName().toLowerCase()+"%' ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetVendorListData(), queryParameters);
	}

}
