package com.servlet.mobile.infodetail.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.branch.entity.Branch;
import com.servlet.mobile.infodetail.entity.InfoHeaderDetail;
import com.servlet.mobile.infodetail.repo.InfoHeaderDetailRepo;
import com.servlet.mobile.infodetail.service.InfoHeaderDetailService;
import com.servlet.mobile.infoheader.entity.InfoHeader;
import com.servlet.mobile.infoheader.repo.InfoHeaderRepo;
import com.servlet.mobile.infodetail.entity.InfoHeaderDetailData;
import com.servlet.mobile.infodetail.mapper.GetInfoHeaderDetail;

@Service
public class InfoHeaderDetailHandler implements InfoHeaderDetailService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private InfoHeaderDetailRepo repository;
	@Autowired
	private InfoHeaderRepo repositoryHeader;
	
	@Override
	public Object saveDataList(List<InfoHeaderDetail> list) {
		// TODO Auto-generated method stub
		if(list.size() > 0) {
			repository.saveAllAndFlush(list);
		}
		return null;
	}

	@Override
	public List<InfoHeaderDetailData> getListData(long idinfoheader) {
		// TODO Auto-generated method stub
		InfoHeader header = repositoryHeader.getById(idinfoheader);
		if(!header.isIsdelete()) {
			final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInfoHeaderDetail().schema());
			sqlBuilder.append(" where data.idinfoheader = ? and data.isdelete = false ");
			final Object[] queryParameters = new Object[] {idinfoheader};
			return this.jdbcTemplate.query(sqlBuilder.toString(), new GetInfoHeaderDetail(), queryParameters);
		}
		return new ArrayList<InfoHeaderDetailData>();
	}

	@Override
	public Object deleteAllDataListPK(List<Long> listPK) {
		// TODO Auto-generated method stub
		if(listPK.size() > 0) {
			repository.deleteAllById(listPK);
		}
		return null;
	}

	@Override
	public Object updateDeleteInfoDetail(long id, boolean isdelete) {
		// TODO Auto-generated method stub
		Optional<InfoHeaderDetail> value = repository.findById(id);
		if(value.isPresent()) {
			InfoHeaderDetail table = value.get();
			table.setIsdelete(isdelete);
			InfoHeaderDetail returntable = repository.saveAndFlush(table);
			return returntable.getId();
		}
		return null;
	}

	@Override
	public void deleteAllInfoDetailByIdInfoHeader(long idinfoheader) {
		// TODO Auto-generated method stub
		repository.deleteAllInfoDetailByInfoHeader(idinfoheader);
		
	}

	

}
