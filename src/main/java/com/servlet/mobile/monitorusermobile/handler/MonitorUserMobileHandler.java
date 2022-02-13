package com.servlet.mobile.monitorusermobile.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.customer.entity.Customer;
import com.servlet.admin.customer.service.CustomerService;
import com.servlet.mobile.monitorusermobile.entity.BodyInfoDetail;
import com.servlet.mobile.monitorusermobile.entity.BodyListMonitorUserMobile;
import com.servlet.mobile.monitorusermobile.entity.BodyListPhoto;
import com.servlet.mobile.monitorusermobile.entity.BodyMonitorUserMobile;
import com.servlet.mobile.monitorusermobile.entity.BodyPhoto;
import com.servlet.mobile.monitorusermobile.entity.DataMonitorForMaps;
import com.servlet.mobile.monitorusermobile.entity.MonitorUserMobile;
import com.servlet.mobile.monitorusermobile.entity.ReturnListData;
import com.servlet.mobile.monitorusermobile.mapper.GetDataIdMonitorUserMobile;
import com.servlet.mobile.monitorusermobile.mapper.GetDataMonitorForMaps;
import com.servlet.mobile.monitorusermobile.repo.MonitorUserMobileRepo;
import com.servlet.mobile.monitorusermobile.service.MonitorUserMobileService;
import com.servlet.mobile.monitorusermobileinfo.entity.MonitorUserMobileInfo;
import com.servlet.mobile.monitorusermobileinfo.entity.MonitorUserMobileInfoPK;
import com.servlet.mobile.monitorusermobileinfo.service.MonitorUserMobileInfoService;
import com.servlet.shared.ReturnData;

@Service
public class MonitorUserMobileHandler implements MonitorUserMobileService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MonitorUserMobileRepo repository;
	@Autowired
	private MonitorUserMobileInfoService monitorUserMobileInfoService;
	@Autowired
	private CustomerService customerService;
	
	@Override
	public List<ReturnListData> saveMonitorUserMobile(BodyMonitorUserMobile listbody, long iduser, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		List<ReturnListData> listdonesave = new ArrayList<ReturnListData>();
		if(listbody.getMonitorusermobiles() != null && listbody.getMonitorusermobiles().size() > 0) {
		for(BodyListMonitorUserMobile body : listbody.getMonitorusermobiles()) {
			MonitorUserMobile table = getDataById(iduser,body.getIdproject(),body.getIdcustomer(),idcompany,idbranch,new Timestamp(body.getTanggal()));
			if(table == null) {
				try {
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
//					table.setPhoto1(body.getPhoto1());
//					table.setPhoto2(body.getPhoto2());
//					table.setPhoto3(body.getPhoto3());
//					table.setPhoto4(body.getPhoto4());
//					table.setPhoto5(body.getPhoto5());
//					table.setPhoto6(body.getPhoto6());
//					table.setPhoto7(body.getPhoto7());
//					table.setPhoto8(body.getPhoto8());
					table.setCreated(ts);
					table.setModified(ts);
					table.setIdcallplan(body.getIdcallplan());
					
					MonitorUserMobile returndata = repository.saveAndFlush(table);
					
					
					
					List<MonitorUserMobileInfo> listsave = new ArrayList<MonitorUserMobileInfo>();
					if(body.getInfodetails().size() > 0) {
						for(BodyInfoDetail infodetail : body.getInfodetails()) {
							MonitorUserMobileInfoPK pk = new MonitorUserMobileInfoPK();
							pk.setIdmonitorusermobile(returndata.getId());
							pk.setInfoid(infodetail.getInfoid());
							pk.setIdinfodetail(infodetail.getIdinfodetail());
							MonitorUserMobileInfo info = new MonitorUserMobileInfo();
							info.setMonitorUserMobileInfoPK(pk);
							info.setInfoanswer(infodetail.getInfoanswer());
							listsave.add(info);
						}
						
						monitorUserMobileInfoService.saveMonitorUserMobileInfoList(listsave);
					}
					//update Lat Long Customer
					ReturnData returnCust = customerService.updateLatLong(body.getIdcustomer(), body.getLatitudein(), body.getLongitudein());
					
					if(returndata != null && new Long(returndata.getId()) != null) {
						ReturnListData data = new ReturnListData();
						data.setIdcustomer(body.getIdcustomer());
						data.setIdproject(body.getIdproject());
						data.setTanggal(body.getTanggal());
						data.setIdmonitoruser(returndata.getId());
						listdonesave.add(data);
					}
					
					
					
				}catch (Exception e) {
					// TODO: handle exception
				}
			}else {
				try {
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
//				table.setPhoto1(body.getPhoto1());
//				table.setPhoto2(body.getPhoto2());
//				table.setPhoto3(body.getPhoto3());
//				table.setPhoto4(body.getPhoto4());
//				table.setPhoto5(body.getPhoto5());
//				table.setPhoto6(body.getPhoto6());
//				table.setPhoto7(body.getPhoto7());
//				table.setPhoto8(body.getPhoto8());
				table.setModified(ts);
				
				MonitorUserMobile returndata = repository.saveAndFlush(table);
				
				List<MonitorUserMobileInfo> listinfo = monitorUserMobileInfoService.getListData(table.getId());
				List<MonitorUserMobileInfoPK> listdelete = new ArrayList<MonitorUserMobileInfoPK>();
				if(listinfo.size() > 0) {
					for(MonitorUserMobileInfo info : listinfo) {
						listdelete.add(info.getMonitorUserMobileInfoPK());
					}
					monitorUserMobileInfoService.deleteAllMonitorUserMobileInfoByListPK(listdelete);
				}
				
				List<MonitorUserMobileInfo> listsave = new ArrayList<MonitorUserMobileInfo>();
				if(body.getInfodetails().size() > 0) {
					for(BodyInfoDetail infodetail : body.getInfodetails()) {
						MonitorUserMobileInfoPK pk = new MonitorUserMobileInfoPK();
						pk.setIdmonitorusermobile(returndata.getId());
						pk.setInfoid(infodetail.getInfoid());
						pk.setIdinfodetail(infodetail.getIdinfodetail());
						MonitorUserMobileInfo info = new MonitorUserMobileInfo();
						info.setMonitorUserMobileInfoPK(pk);
						info.setInfoanswer(infodetail.getInfoanswer());
						listsave.add(info);
					}
					
					monitorUserMobileInfoService.saveMonitorUserMobileInfoList(listsave);
				}
				
				if(returndata != null && new Long(returndata.getId()) != null) {
					ReturnListData data = new ReturnListData();
					data.setIdcustomer(body.getIdcustomer());
					data.setIdproject(body.getIdproject());
					data.setTanggal(body.getTanggal());
					data.setIdmonitoruser(returndata.getId());
					listdonesave.add(data);
				}
				}catch (Exception e) {
					// TODO: handle exception
				}
			}	
		}
		}
		
		return listdonesave.size() > 0?listdonesave:null;
	}
	
	private MonitorUserMobile getDataById(long iduser,long idproject,long idcustomer,long idcompany, long idbranch,Timestamp date) {
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
	
	private MonitorUserMobile getDataByIdUserMonitor(long idusermonitor,long iduser,long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		//PRIMARY KEY (`companyID`,`branchID`,`projectID`,`salesmanID`,`tanggal`,`customerID`)
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataIdMonitorUserMobile().schema());
		sqlBuilder.append(" where data.id = ? and data.idusermobile = ? and data.idcompany = ? and data.idbranch = ? ");
		final Object[] queryParameters = new Object[] {idusermonitor,iduser, idcompany,idbranch};
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

	@Override
	public List<ReturnListData> savePhotoMonitorUserMobile(BodyListPhoto listbody,long iduser, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		List<ReturnListData> listdonesave = new ArrayList<ReturnListData>();
		if(listbody.getListphoto() != null && listbody.getListphoto().size() > 0) {
			for(BodyPhoto body : listbody.getListphoto()) {
				MonitorUserMobile table = getDataByIdUserMonitor(body.getIdusermonitor(),iduser, idcompany, idbranch);
				if(table != null) {
					try {
						for(int i=0; i < body.getTophoto().length; i++) {
							long tophoto = body.getTophoto()[i];
							if(tophoto == 1) {
								table.setPhoto1(body.getPhoto1());
								continue;
							}
							if(tophoto == 2) {
								table.setPhoto2(body.getPhoto2());
								continue;
							}
							if(tophoto == 3) {
								table.setPhoto3(body.getPhoto3());
								continue;
							}
							if(tophoto == 4) {
								table.setPhoto4(body.getPhoto4());
								continue;
							}
							if(tophoto == 5) {
								table.setPhoto5(body.getPhoto5());
								continue;
							}
							if(tophoto == 6) {
								table.setPhoto6(body.getPhoto6());
								continue;
							}
							if(tophoto == 7) {
								table.setPhoto7(body.getPhoto7());
								continue;
							}
							if(tophoto == 8) {
								table.setPhoto8(body.getPhoto8());
								continue;
							}
						}
						table.setModified(ts);
						MonitorUserMobile returndata = repository.saveAndFlush(table);
						if(returndata != null && new Long(returndata.getId()) != null) {
							ReturnListData data = new ReturnListData();
							data.setIdcustomer(returndata.getIdcustomer());
							data.setIdproject(returndata.getIdproject());
							data.setTanggal(returndata.getTanggal().getTime());
							data.setIdmonitoruser(returndata.getId());
							listdonesave.add(data);
						}
						
					}catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
		return listdonesave.size() > 0?listdonesave:null;
	}

	@Override
	public List<DataMonitorForMaps> getListDataMonitorMaps(long iduser, String tangal, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataMonitorForMaps().schema());
		sqlBuilder.append(" where monitor.idusermobile = ? and monitor.idcompany = ? and monitor.idbranch = ? and to_date(monitor.checkintime,'YYYY-MM-DD') = '"+tangal+"' ");
		sqlBuilder.append(" order by monitor.checkintime desc ");
		final Object[] queryParameters = new Object[] {iduser, idcompany,idbranch};
		List<DataMonitorForMaps> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataMonitorForMaps(), queryParameters);
		return list;
	}

}
