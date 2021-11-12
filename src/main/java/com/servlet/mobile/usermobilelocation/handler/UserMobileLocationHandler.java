package com.servlet.mobile.usermobilelocation.handler;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.mobile.usermobilelocation.entity.BodyUserMobileLocation;
import com.servlet.mobile.usermobilelocation.entity.UserMobileLocation;
import com.servlet.mobile.usermobilelocation.repo.UserMobileLocationRepo;
import com.servlet.mobile.usermobilelocation.service.UserMobileLocationService;
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
		ReturnData data = new ReturnData();
		data.setId(returndata.getId());
		return data;
	}

}
