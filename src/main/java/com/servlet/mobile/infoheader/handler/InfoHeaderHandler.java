package com.servlet.mobile.infoheader.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.mobile.infodetail.entity.InfoHeaderDetail;
import com.servlet.mobile.infodetail.service.InfoHeaderDetailService;
import com.servlet.mobile.infoheader.entity.BodyInfoHeader;
import com.servlet.mobile.infoheader.entity.InfoHeader;
import com.servlet.mobile.infoheader.entity.InfoHeaderData;
import com.servlet.mobile.infoheader.entity.InfoHeaderDetailData;
import com.servlet.mobile.infoheader.mapper.GetInfoHeader;
import com.servlet.mobile.infoheader.repo.InfoHeaderRepo;
import com.servlet.mobile.infoheader.service.InfoHeaderService;
import com.servlet.shared.ReturnData;

@Service
public class InfoHeaderHandler implements InfoHeaderService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private InfoHeaderRepo repository;
	@Autowired
	private InfoHeaderDetailService infoHeaderDetailService;
	
	@Override
	public ReturnData saveInfoHeader(BodyInfoHeader body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		InfoHeader table = new InfoHeader();
		table.setIdcompany(idcompany);
		table.setIdbranch(idbranch);
		table.setIdcustomertype(body.getIdcustomertype());
		table.setIsdelete(false);
		table.setIsactive(true);
		table.setQuestion(body.getQuestion());
		table.setSequence(body.getSequence());
		table.setType(body.getType());
		
		InfoHeader returntable = repository.saveAndFlush(table);
		
		List<InfoHeaderDetail> listinfoHeaderDetail = new ArrayList<InfoHeaderDetail>();
		if(body.getAnswer().length > 0) {
			for(int i=0; i < body.getAnswer().length; i++) {
				InfoHeaderDetail tabledetail = new InfoHeaderDetail();
				tabledetail.setIdcompany(idcompany);
				tabledetail.setIdbranch(idbranch);
				tabledetail.setIdinfoheader(returntable.getId());
				tabledetail.setAnswer(body.getAnswer()[i]);
				listinfoHeaderDetail.add(tabledetail);
			}
			infoHeaderDetailService.saveDataList(listinfoHeaderDetail);
		}
		
		ReturnData data = new ReturnData();
		data.setId(returntable.getId());
		return data;
	}
	
	private InfoHeaderData checkInfoHeaderById(long id,long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInfoHeader().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id, idcompany,idbranch};
		List<InfoHeaderData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetInfoHeader(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ReturnData updateInfoHeader(long id, BodyInfoHeader body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		InfoHeaderData checkData = checkInfoHeaderById(id,idcompany,idbranch);
		if(checkData != null) {
			InfoHeader table = repository.getById(id);
			table.setQuestion(body.getQuestion());
			table.setSequence(body.getSequence());
			table.setIsactive(body.isIsactive());
			table.setType(body.getType());
			table.setIdcustomertype(body.getIdcustomertype());
			
			InfoHeader returntable = repository.saveAndFlush(table);
			
			List<com.servlet.mobile.infodetail.entity.InfoHeaderDetailData> listdetail = infoHeaderDetailService.getListData(id);
			List<Long> listDetailPK = new ArrayList<Long>();
			if(listdetail != null && listdetail.size() > 0) {
				for(com.servlet.mobile.infodetail.entity.InfoHeaderDetailData data : listdetail) {
					listDetailPK.add(data.getId());
				}
				infoHeaderDetailService.deleteAllDataListPK(listDetailPK);
			}
			
			List<InfoHeaderDetail> listinfoHeaderDetail = new ArrayList<InfoHeaderDetail>();
			if(body.getAnswer().length > 0) {
				for(int i=0; i < body.getAnswer().length; i++) {
					InfoHeaderDetail tabledetail = new InfoHeaderDetail();
					tabledetail.setIdcompany(idcompany);
					tabledetail.setIdbranch(idbranch);
					tabledetail.setIdinfoheader(returntable.getId());
					tabledetail.setAnswer(body.getAnswer()[i]);
					listinfoHeaderDetail.add(tabledetail);
				}
				infoHeaderDetailService.saveDataList(listinfoHeaderDetail);
			}
			
			ReturnData data = new ReturnData();
			data.setId(returntable.getId());
			return data;
		}
		return null;
	}

	@Override
	public List<InfoHeaderData> getAllListData(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInfoHeader().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetInfoHeader(), queryParameters);
	}

	@Override
	public InfoHeaderDetailData getDetailById(long id, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		InfoHeaderData header = checkInfoHeaderById(id,idcompany,idbranch);
		List<com.servlet.mobile.infodetail.entity.InfoHeaderDetailData> listdetail = infoHeaderDetailService.getListData(id);
		
		InfoHeaderDetailData data = new InfoHeaderDetailData();
		data.setInfoheader(header);
		data.setDetail(listdetail);
		return data;
	}

	@Override
	public List<InfoHeaderDetailData> getAllListDataMobile(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetInfoHeader().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false and data.isactive = true ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<InfoHeaderData> listheader = this.jdbcTemplate.query(sqlBuilder.toString(), new GetInfoHeader(), queryParameters);
		
		List<InfoHeaderDetailData> listinfomobile = new ArrayList<InfoHeaderDetailData>();
		for(InfoHeaderData header : listheader) {
			List<com.servlet.mobile.infodetail.entity.InfoHeaderDetailData> listdetail = infoHeaderDetailService.getListData(header.getId());
			
			InfoHeaderDetailData infomobile = new InfoHeaderDetailData();
			infomobile.setInfoheader(header);
			infomobile.setDetail(listdetail);
			listinfomobile.add(infomobile);
		}
		return listinfomobile;
	}

}
