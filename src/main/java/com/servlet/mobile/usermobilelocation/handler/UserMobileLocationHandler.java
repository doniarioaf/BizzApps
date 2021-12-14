package com.servlet.mobile.usermobilelocation.handler;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.mobile.monitorusermobile.entity.MonitorUserMobile;
import com.servlet.mobile.monitorusermobile.mapper.GetDataIdMonitorUserMobile;
import com.servlet.mobile.usermobilelocation.entity.BodyUserMobileLocation;
import com.servlet.mobile.usermobilelocation.entity.UserMobileLocation;
import com.servlet.mobile.usermobilelocation.mapper.GetListLocation;
import com.servlet.mobile.usermobilelocation.repo.UserMobileLocationRepo;
import com.servlet.mobile.usermobilelocation.service.UserMobileLocationService;
import com.servlet.shared.GlobalFunc;
import com.servlet.shared.ReturnData;

@Service
public class UserMobileLocationHandler implements UserMobileLocationService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserMobileLocationRepo repository;
	
	@Override
	public ReturnData saveUserMobileLocation(BodyUserMobileLocation body,long idusermobile, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(new Date().getTime());
		UserMobileLocation table = new UserMobileLocation();
		table.setIdcompany(idcompany);
		table.setIdbranch(idbranch);
		table.setIdusermobile(idusermobile);
		table.setTanggal(ts);
		table.setLatitude(body.getLatitude());
		table.setLongitude(body.getLongitude());
		
		UserMobileLocation returndata = repository.saveAndFlush(table);
		
		try {
			Timestamp setFormatTS = GlobalFunc.setFormatDate(ts, "yyyy-MM-dd");
			String strDate = setFormatTS.toString().substring(0, 10);
			List<Long> listIdLocation = getListLocation(idusermobile,strDate,idcompany,idbranch);
			
			if(listIdLocation != null) {
				//untuk lokasi sales dibatasi hanya 1 sales perhari hanya bisa simpan 10 data
				repository.deleteAllById(listIdLocation);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReturnData data = new ReturnData();
		data.setId(returndata.getId());
		return data;
	}
	
	//Get Data, di mulai dari yg ke 11, dan dibatasi sampai 100 data
	private List<Long> getListLocation(long idusermobile,String date,long idcompany, long idbranch){
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetListLocation().schema());
		sqlBuilder.append(" where data.idusermobile = ? and data.idcompany = ? and data.idbranch = ? and to_char(data.tanggal,'YYYY-MM-DD') = '"+date+"' ");
		sqlBuilder.append(" order by tanggal desc offset 10 limit 100 ");
		final Object[] queryParameters = new Object[] {idusermobile, idcompany,idbranch};
		List<Long> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetListLocation(), queryParameters);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
