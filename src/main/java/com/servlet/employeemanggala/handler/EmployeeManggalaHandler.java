package com.servlet.employeemanggala.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.servlet.employeemanggala.entity.BodyDetailEmployeeManggalaInfoFamily;
import com.servlet.employeemanggala.entity.BodyEmployeeManggala;
import com.servlet.employeemanggala.entity.DetailEmployeeManggalaInfoFamily;
import com.servlet.employeemanggala.entity.DetailEmployeeManggalaInfoFamilyData;
import com.servlet.employeemanggala.entity.DetailEmployeeManggalaInfoFamilyPK;
import com.servlet.employeemanggala.entity.EmployeManggalaData;
import com.servlet.employeemanggala.entity.EmployeManggalaDataList;
import com.servlet.employeemanggala.entity.EmployeManggalaDataListParam;
import com.servlet.employeemanggala.entity.EmployeeManggala;
import com.servlet.employeemanggala.entity.EmployeeManggalaTemplate;
import com.servlet.employeemanggala.mapper.GetDetailEmployeeManggalaInfoFamilyData;
import com.servlet.employeemanggala.mapper.GetEmployeeManggalaData;
import com.servlet.employeemanggala.mapper.GetEmployeeManggalaDataList;
import com.servlet.employeemanggala.repo.DetailEmployeeManggalaInfoFamilyRepo;
import com.servlet.employeemanggala.repo.EmployeeManggalaRepo;
import com.servlet.employeemanggala.service.EmployeeManggalaService;
import com.servlet.historyemployeemanggala.entity.BodyHistoryEmployee;
import com.servlet.historyemployeemanggala.service.HistoryEmployeeService;
import com.servlet.parameter.service.ParameterService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
//import com.servlet.upload.image.FileStorageService;
import com.servlet.user.entity.UserPermissionData;
import com.servlet.user.service.UserAppsService;

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
	@Autowired
	private UserAppsService userAppsService;
//	@Autowired
//    private FileStorageService fileStorageService;
	@Autowired
	private HistoryEmployeeService historyEmployeeService;
	
	@Override
	public EmployeeManggalaTemplate employeeManggalaTemplate(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		return getEmployeeTemplate(idcompany,idbranch);
	}

	@Override
	public EmployeManggalaDataListParam getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetEmployeeManggalaDataList().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false order by data.id ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<EmployeManggalaDataList> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetEmployeeManggalaDataList(), queryParameters);
		EmployeManggalaDataListParam data = new EmployeManggalaDataListParam();
		data.setItems(list);
		data.setTemplate(getEmployeeTemplate(idcompany,idbranch));
		return data;
	}

	@Override
	public EmployeManggalaDataListParam getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetEmployeeManggalaDataList().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false order by data.id ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<EmployeManggalaDataList> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetEmployeeManggalaDataList(), queryParameters);
		EmployeManggalaDataListParam data = new EmployeManggalaDataListParam();
		data.setItems(list);
		data.setTemplate(getEmployeeTemplate(idcompany,idbranch));
		return data;
	}

	@Override
	public EmployeManggalaData getById(Long idcompany, Long idbranch, Long id,Long iduser) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetEmployeeManggalaData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<EmployeManggalaData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetEmployeeManggalaData(), queryParameters);
		if(list != null && list.size() > 0) {
			EmployeManggalaData val = list.get(0);
			val.setDetailsFamily(getListDetailInfoFamily(idcompany, idbranch, id));
			val.setTemplate(getEmployeeTemplate(idcompany,idbranch));
			if(!checkInputGaji(iduser,"")) {
				val.setGaji("");
			}else {
				val.setHistoryEmployee(historyEmployeeService.listHistoryEmployeeManggala(idcompany, idbranch, id));
			}
			
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
			return val;
		}
		return null;
	}
	
	private EmployeManggalaData getCheckByNoIdentitas(Long idcompany, Long idbranch, String noidentitas) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetEmployeeManggalaData().schema());
		sqlBuilder.append(" where data.noidentitas = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {noidentitas,idcompany,idbranch};
		List<EmployeManggalaData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetEmployeeManggalaData(), queryParameters);
		if(list != null && list.size() > 0) {
			EmployeManggalaData val = list.get(0);
			return val;
		}
		return null;
	}

	@Override
	public ReturnData saveEmployeeManggala(Long idcompany, Long idbranch, Long iduser, BodyEmployeeManggala body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		
		if(getCheckByNoIdentitas(idcompany,idbranch,body.getNoidentitas()) != null) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_EMPLOYEE_MANGGALA_NO_IDENTITAS_EXIST,"No Identitas Sudah Terdaftar");
			validations.add(msg);
		}
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
				if(body.getStatus().equals("MENIKAH")) {
					table.setNamapasangan(body.getNamapasangan());
					if(body.getTanggallahirpasangan() != null) {
						table.setTanggallahirpasangan(new java.sql.Date(body.getTanggallahirpasangan()));
					}
				}else {
					table.setNamapasangan("");
					table.setTanggallahirpasangan(null);
				}
				
				table.setNamabank(body.getNamabank());
				table.setNorekening(body.getNorekening());
				table.setAtasnama(body.getAtasnama());
				table.setTanggalmulai(new java.sql.Date(body.getTanggalmulai()));
				if(body.getTanggalresign() != null) {
					table.setTanggalresign(new java.sql.Date(body.getTanggalresign()));
				}
				table.setJeniskelamin(body.getJeniskelamin());
				table.setIsactive(body.isIsactive());
				
				if(checkInputGaji(iduser,"CREATE")) {
					table.setGaji(body.getGaji());
				}

				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setCreateddate(ts);
				
				
				
				idsave = repository.saveAndFlush(table).getId();
				
				putDetailInforFamily(body.getDetailsInfoFamily(),idcompany,idbranch,idsave,"ADD");
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
			if(!value.getNoidentitas().equals(body.getNoidentitas())) {
				if(getCheckByNoIdentitas(idcompany,idbranch,body.getNoidentitas()) != null) {
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_EMPLOYEE_MANGGALA_NO_IDENTITAS_EXIST,"No Identitas Sudah Terdaftar");
					validations.add(msg);
				}
			}
			
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					EmployeeManggala table = repository.getById(id);
					BodyEmployeeManggala tabletemp = new BodyEmployeeManggala();
					tabletemp.setJabatan(table.getJabatan());
					tabletemp.setStatuskaryawan(table.getStatuskaryawan());
					tabletemp.setGaji(table.getGaji());
					
					table.setStatuskaryawan(body.getStatuskaryawan());
					table.setJabatan(body.getJabatan());
					table.setNama(body.getNama());
					table.setNoidentitas(body.getNoidentitas());
					table.setAlamat(body.getAlamat());
					table.setTanggallahir(new java.sql.Date(body.getTanggallahir()));
					table.setStatus(body.getStatus());
					if(body.getStatus().equals("MENIKAH")) {
						table.setNamapasangan(body.getNamapasangan());
						if(body.getTanggallahirpasangan() != null) {
							table.setTanggallahirpasangan(new java.sql.Date(body.getTanggallahirpasangan()));
						}
					}else {
						table.setNamapasangan("");
						table.setTanggallahirpasangan(null);
					}
//					table.setNamapasangan(body.getNamapasangan());
//					table.setTanggallahirpasangan(new java.sql.Date(body.getTanggallahirpasangan()));
					table.setNamabank(body.getNamabank());
					table.setNorekening(body.getNorekening());
					table.setAtasnama(body.getAtasnama());
					table.setTanggalmulai(new java.sql.Date(body.getTanggalmulai()));
					if(body.getTanggalresign() != null) {
						table.setTanggalresign(new java.sql.Date(body.getTanggalresign()));
					}else {
						table.setTanggalresign(null);
					}
					
					
					//Hanya Permission Tertentu yg boleh update gaji
					boolean gaji = checkInputGaji(iduser,"EDIT"); 
					if(gaji) {
						table.setGaji(body.getGaji());
					}
					
					table.setJeniskelamin(body.getJeniskelamin());
					table.setIsactive(body.isIsactive());
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					idsave = repository.saveAndFlush(table).getId();
					
					putHistory(tabletemp, body, idcompany, idbranch, iduser, id, gaji);
					putDetailInforFamily(body.getDetailsInfoFamily(),idcompany,idbranch,idsave,"EDIT");
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
	
	private String putHistory(BodyEmployeeManggala table,BodyEmployeeManggala bodyemp,Long idcompany, Long idbranch, Long iduser, Long id, boolean gaji) {
		boolean flag = false;
		String jabatan = "";
		String statuskaryawan = "";
		String gajiemp = "";
		//Old > New
		if(!table.getJabatan().equals(bodyemp.getJabatan())) {
			flag = true;
			jabatan = table.getJabatan()+"|"+bodyemp.getJabatan();
		}else {
			jabatan = table.getJabatan();
		}
		
		if(!table.getStatuskaryawan().equals(bodyemp.getStatuskaryawan())) {
			flag = true;
			statuskaryawan = table.getStatuskaryawan()+"|"+bodyemp.getStatuskaryawan();
		}else {
			statuskaryawan = table.getStatuskaryawan();
		}
		
		if(!table.getGaji().equals(bodyemp.getGaji())) {
			if(gaji) {
				flag = true;
				gajiemp = table.getGaji()+"|"+bodyemp.getGaji();//bodyemp.getGaji();
			}else {
				gajiemp = table.getGaji();
			}
			
		}else {
			gajiemp = table.getGaji();
		}
		if(flag) {
			BodyHistoryEmployee body = new BodyHistoryEmployee();
			body.setIdcompany(idcompany);
			body.setIdbranch(idbranch);
			body.setIdemployee(id);
			body.setIduser(iduser);
			body.setJabatan(jabatan);
			body.setStatusemployee(statuskaryawan);
			body.setGaji(gajiemp);
			historyEmployeeService.saveHistoryEmployeeManggala(body);
		}
		return "";
	}
	
	private String putDetailInforFamily(BodyDetailEmployeeManggalaInfoFamily[] detailsinfofamily,Long idcompany, Long idbranch,long idsave,String action) {
		if(action.equals("EDIT")) {
			detailEmployeeManggalaInfoFamilyRepo.deleteAllInfoFamily(idsave, idcompany, idbranch);
		}
		if(detailsinfofamily != null) {
			if(detailsinfofamily.length > 0) {
				int counting = 1;
				for(int i=0; i < detailsinfofamily.length; i++) {
					BodyDetailEmployeeManggalaInfoFamily detail = detailsinfofamily[i]; 
					DetailEmployeeManggalaInfoFamilyPK detailEmployeeManggalaInfoFamilyPK = new DetailEmployeeManggalaInfoFamilyPK();
					detailEmployeeManggalaInfoFamilyPK.setIdemployeemanggala(idsave);
					detailEmployeeManggalaInfoFamilyPK.setCountdetail(counting);
					detailEmployeeManggalaInfoFamilyPK.setIdcompany(idcompany);
					detailEmployeeManggalaInfoFamilyPK.setIdbranch(idbranch);
					
					DetailEmployeeManggalaInfoFamily detailEmployeeManggalaInfoFamily = new DetailEmployeeManggalaInfoFamily();
					detailEmployeeManggalaInfoFamily.setDetailEmployeeManggalaInfoFamilyPK(detailEmployeeManggalaInfoFamilyPK);
					detailEmployeeManggalaInfoFamily.setJeniskelamin(detail.getJeniskelamin());
					detailEmployeeManggalaInfoFamily.setNamaanak(detail.getNamaanak());
					detailEmployeeManggalaInfoFamily.setStatus(detail.getStatus());
					detailEmployeeManggalaInfoFamily.setTanggallahir(new java.sql.Date(detail.getTanggallahir()));
					detailEmployeeManggalaInfoFamilyRepo.saveAndFlush(detailEmployeeManggalaInfoFamily);
					counting++;
				}
			}
		}
		return "";
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
	
	private boolean checkInputGaji(Long iduser,String action) {
		boolean flagpermission = false;
		List<UserPermissionData> listPermission =  new ArrayList<UserPermissionData>(userAppsService.getListUserPermission(iduser));
		if(listPermission != null && listPermission.size() > 0) {
			for(UserPermissionData permissiondata : listPermission) {
				if(permissiondata.getPermissioncode().equals("SUPERUSER")) {
					flagpermission = true;
					break;
				}else if(permissiondata.getPermissioncode().equals(ConstansPermission.CREATE_SALARY_EMPLOYEE_MANGGALA) && (action.equals("CREATE") || action.equals("")) ) {
					flagpermission = true;
					break;
				}else if(permissiondata.getPermissioncode().equals(ConstansPermission.EDIT_SALARY_EMPLOYEE_MANGGALA) && (action.equals("EDIT") || action.equals(""))) {
					flagpermission = true;
					break;
				}else if(permissiondata.getPermissioncode().equals(ConstansPermission.READ_SALARY_EMPLOYEE_MANGGALA) && (action.equals("READ") || action.equals(""))) {
					flagpermission = true;
					break;
				}
			}
		}
		return flagpermission;
	}

	@Override
	public ReturnData uploadImageEmployeeManggala(Long idcompany, Long idbranch, Long iduser,Long id, MultipartFile file) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		EmployeManggalaData value = getCheckById(idcompany,idbranch,id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					byte[] image = Base64.encodeBase64(file.getBytes());
			        String result = new String(image);
			        
//					Timestamp ts = new Timestamp(new Date().getTime());
					EmployeeManggala table = repository.getById(id);
					table.setPhoto(result);
					idsave = repository.saveAndFlush(table).getId();
					
				}catch (Exception e) {
					// TODO: handle exception
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
					validations.add(msg);
					e.printStackTrace();
				}
			}
			
		}
//		String fileName = fileStorageService.storeFile(file);
//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
//		UploadFileResponse upload = new UploadFileResponse(fileName, fileDownloadUri,
//                file.getContentType(), file.getSize());
		
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public List<EmployeManggalaDataList> getListEmployeeSupir(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetEmployeeManggalaDataList().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false and data.jabatan != 'STAF' and data.statuskaryawan != 'BERHENTI' order by data.id ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetEmployeeManggalaDataList(), queryParameters);
	}

}
