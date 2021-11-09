package com.servlet.mobile.monitorusermobile.handler;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.mobile.monitorusermobile.entity.BodyMonitorUserMobile;
import com.servlet.mobile.monitorusermobile.entity.MonitorUserMobile;
import com.servlet.mobile.monitorusermobile.mapper.GetDataIdMonitorUserMobile;
import com.servlet.mobile.monitorusermobile.repo.MonitorUserMobileRepo;
import com.servlet.mobile.monitorusermobile.service.MonitorUserMobileService;
import com.servlet.shared.ReturnData;

@Service
public class MonitorUserMobileHandler implements MonitorUserMobileService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MonitorUserMobileRepo repository;
	
	@Override
	public ReturnData saveMonitorUserMobile(BodyMonitorUserMobile body, long iduser, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		
		MonitorUserMobile table = getDataById(iduser,body.getIdproject(),body.getIdcustomer(),idcompany,idbranch,new Timestamp(body.getTanggal()));
		if(table == null) {
			table = new MonitorUserMobile();
			table.setIdcompany(idcompany);
			table.setIdbranch(idbranch);
			table.setIdproject(body.getIdproject());
			table.setIdusermobile(iduser);
			table.setIdcustomer(body.getIdcustomer());
			table.setTanggal(new Timestamp(body.getTanggal()));
			if(body.getChekintime() != null) {
				table.setCheckintime(new Timestamp(body.getChekintime()).toString());
			}
			if(body.getChekouttime() != null) {
				table.setCheckouttime(new Timestamp(body.getChekouttime()).toString());
			}
			table.setLatitudein(body.getLatitudein());
			table.setLongitudein(body.getLongitudein());
			table.setLatitudeout(body.getLatitudeout());
			table.setLongitudeout(body.getLongitudeout());
			table.setPhoto1(body.getPhoto1());
			table.setPhoto2(body.getPhoto2());
			table.setPhoto3(body.getPhoto3());
			table.setPhoto4(body.getPhoto4());
			table.setPhoto5(body.getPhoto5());
			table.setPhoto6(body.getPhoto6());
			table.setPhoto7(body.getPhoto7());
			table.setPhoto8(body.getPhoto8());
			table.setCreated(ts);
			table.setModified(ts);
		}else {
			if(body.getChekintime() != null) {
				table.setCheckintime(new Timestamp(body.getChekintime()).toString());
			}
			if(body.getChekouttime() != null) {
				table.setCheckouttime(new Timestamp(body.getChekouttime()).toString());
			}
			table.setLatitudein(body.getLatitudein());
			table.setLongitudein(body.getLongitudein());
			table.setLatitudeout(body.getLatitudeout());
			table.setLongitudeout(body.getLongitudeout());
			table.setPhoto1(body.getPhoto1());
			table.setPhoto2(body.getPhoto2());
			table.setPhoto3(body.getPhoto3());
			table.setPhoto4(body.getPhoto4());
			table.setPhoto5(body.getPhoto5());
			table.setPhoto6(body.getPhoto6());
			table.setPhoto7(body.getPhoto7());
			table.setPhoto8(body.getPhoto8());
			table.setModified(ts);
		}
		
		
		MonitorUserMobile returndata = repository.saveAndFlush(table);
		ReturnData data = new ReturnData();
		data.setId(returndata.getId());
		
		return data;
	}
	
	public MonitorUserMobile getDataById(long iduser,long idproject,long idcustomer,long idcompany, long idbranch,Timestamp date) {
		// TODO Auto-generated method stub
		//PRIMARY KEY (`companyID`,`branchID`,`projectID`,`salesmanID`,`tanggal`,`customerID`)
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataIdMonitorUserMobile().schema());
		sqlBuilder.append(" where data.idusermobile = ? and data.idcompany = ? and data.idbranch = ? and data.idproject = ? and data.idcustomer = ? and data.tanggal = ? ");
		final Object[] queryParameters = new Object[] {iduser, idcompany,idbranch,idproject,idcustomer,date};
		List<MonitorUserMobile> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataIdMonitorUserMobile(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ReturnData editMonitorUserMobile(BodyMonitorUserMobile body, long idmonitor,long iduser, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
//		Timestamp ts = new Timestamp(new Date().getTime());
//		
//		MonitorUserMobile table = repository.getById(idmonitor);
//		if(table != null) {
//			if(table.getIdusermobile() == iduser && table.getid()) {
//				table.setIdproject(body.getIdproject());
//			}
//			
//		}
		return null;
	}

}
