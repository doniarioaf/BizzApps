package com.servlet.employeemanggala.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import com.servlet.employeemanggala.entity.BodyEmployeeManggala;
import com.servlet.employeemanggala.entity.DetailEmployeeManggalaInfoFamilyData;
import com.servlet.employeemanggala.entity.EmployeManggalaData;
import com.servlet.employeemanggala.entity.EmployeeManggala;
import com.servlet.employeemanggala.entity.EmployeeManggalaTemplate;
import com.servlet.employeemanggala.mapper.GetDetailEmployeeManggalaInfoFamilyData;
import com.servlet.employeemanggala.mapper.GetEmployeeManggalaData;
import com.servlet.employeemanggala.repo.DetailEmployeeManggalaInfoFamilyRepo;
import com.servlet.employeemanggala.repo.EmployeeManggalaRepo;
import com.servlet.employeemanggala.service.EmployeeManggalaService;
import com.servlet.parameter.service.ParameterService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class EmployeeManggalaHandler implements EmployeeManggalaService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private EmployeeManggalaRepo repository;
	@Autowired
	private DetailEmployeeManggalaInfoFamilyRepo detailEmployeeManggalaInfoFamilyRepo;
	
	@Override
	public EmployeeManggalaTemplate employeeManggalaTemplate(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		return getEmployeeTemplate(idcompany,idbranch);
	}

	@Override
	public List<EmployeManggalaData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetEmployeeManggalaData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetEmployeeManggalaData(), queryParameters);
	}

	@Override
	public List<EmployeManggalaData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetEmployeeManggalaData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetEmployeeManggalaData(), queryParameters);
	}

	@Override
	public EmployeManggalaData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetEmployeeManggalaData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<EmployeManggalaData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetEmployeeManggalaData(), queryParameters);
		if(list != null && list.size() > 0) {
			EmployeManggalaData val = list.get(0);
			val.setDetailsFamily(getListDetailInfoFamily(idcompany, idbranch, id));
			val.setTemplate(getEmployeeTemplate(idcompany,idbranch));
			return val;
		}
		return null;
	}
	
	private EmployeManggalaData getCheckById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetEmployeeManggalaData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<EmployeManggalaData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetEmployeeManggalaData(), queryParameters);
		if(list != null && list.size() > 0) {
			EmployeManggalaData val = list.get(0);
//			val.setDetailsFamily(getListDetailInfoFamily(idcompany, idbranch, id));
//			val.setTemplate(getEmployeeTemplate(idcompany,idbranch));
			return val;
		}
		return null;
	}

	@Override
	public ReturnData saveEmployeeManggala(Long idcompany, Long idbranch, Long iduser, BodyEmployeeManggala body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				Timestamp ts = new Timestamp(new Date().getTime());
				EmployeeManggala table = new EmployeeManggala();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setCode("");
				table.setStatuskaryawan(body.getStatuskaryawan());
				table.setJabatan(body.getJabatan());
				table.setNama(body.getNama());
				table.setNoidentitas(body.getNoidentitas());
				table.setAlamat(body.getAlamat());
				table.setTanggallahir(new java.sql.Date(body.getTanggallahir()));
				table.setStatus(body.getStatus());
				table.setNamapasangan(body.getNamapasangan());
				table.setTanggallahirpasangan(new java.sql.Date(body.getTanggallahirpasangan()));
				table.setNamabank(body.getNamabank());
				table.setNorekening(body.getNorekening());
				table.setAtasnama(body.getAtasnama());
				table.setTanggalmulai(new java.sql.Date(body.getTanggalmulai()));
				table.setTanggalresign(new java.sql.Date(body.getTanggalresign()));
				table.setGaji(body.getGaji());
				table.setJeniskelamin(body.getJeniskelamin());
				table.setIsactive(body.isIsactive());
				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setCreateddate(ts);
				idsave = repository.saveAndFlush(table).getId();
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
	public ReturnData updateEmployeeManggala(Long idcompany, Long idbranch, Long iduser, Long id,
			BodyEmployeeManggala body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		EmployeManggalaData value = getCheckById(idcompany,idbranch,id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					EmployeeManggala table = repository.getById(id);
					table.setStatuskaryawan(body.getStatuskaryawan());
					table.setJabatan(body.getJabatan());
					table.setNama(body.getNama());
					table.setNoidentitas(body.getNoidentitas());
					table.setAlamat(body.getAlamat());
					table.setTanggallahir(new java.sql.Date(body.getTanggallahir()));
					table.setStatus(body.getStatus());
					table.setNamapasangan(body.getNamapasangan());
					table.setTanggallahirpasangan(new java.sql.Date(body.getTanggallahirpasangan()));
					table.setNamabank(body.getNamabank());
					table.setNorekening(body.getNorekening());
					table.setAtasnama(body.getAtasnama());
					table.setTanggalmulai(new java.sql.Date(body.getTanggalmulai()));
					table.setTanggalresign(new java.sql.Date(body.getTanggalresign()));
					table.setGaji(body.getGaji());
					table.setJeniskelamin(body.getJeniskelamin());
					table.setIsactive(body.isIsactive());
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					idsave = repository.saveAndFlush(table).getId();
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
	public ReturnData deleteEmployeeManggala(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		EmployeManggalaData value = getCheckById(idcompany,idbranch,id);
		if(value != null) {
			Timestamp ts = new Timestamp(new Date().getTime());
			EmployeeManggala table = repository.getById(id);
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

	@Override
	public List<DetailEmployeeManggalaInfoFamilyData> getListDetailInfoFamily(Long idcompany, Long idbranch,
			Long id) {
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDetailEmployeeManggalaInfoFamilyData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.idemployeemanggala = ? ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch,id};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDetailEmployeeManggalaInfoFamilyData(), queryParameters);
	}
	
	private EmployeeManggalaTemplate getEmployeeTemplate(Long idcompany, Long idbranch) {
		EmployeeManggalaTemplate template = new EmployeeManggalaTemplate();
		template.setStatusKaryawanOptions(parameterService.getListParameterByGrup("STATUS_KARYAWAN"));
		template.setJabatanOptions(parameterService.getListParameterByGrup("POSITION"));
		template.setJeniskelaminOptions(parameterService.getListParameterByGrup("GENDER"));
		template.setStatusOptions(parameterService.getListParameterByGrup("STATUS"));
		return template;
	}

}
