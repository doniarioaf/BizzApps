package com.servlet.address.handler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.address.entity.PostalCode;
import com.servlet.address.entity.PostalCodeData;
import com.servlet.address.mapper.GetDataPostalCode;
import com.servlet.address.repo.PostalCodeRepo;
import com.servlet.address.service.PostalCodeService;

@Service
public class PostalCodeHandler implements PostalCodeService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PostalCodeRepo repository;
	
	@Override
	public List<PostalCodeData> getListPostalCode() {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataPostalCode().schema());
		final Object[] queryParameters = new Object[] {};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataPostalCode(), queryParameters);
	}

	@Override
	public List<PostalCodeData> getListPostalCodeByPostalCode(long postalcode) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataPostalCode().schema());
		sqlBuilder.append(" where data.postal_code = ? ");
		final Object[] queryParameters = new Object[] {postalcode};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataPostalCode(), queryParameters);
	}

	@Override
	public List<PostalCodeData> getListPostalCodeByPostalCodeBySubDistrictId(long subdistrictid) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataPostalCode().schema());
		sqlBuilder.append(" where data.subdis_id = ? ");
		final Object[] queryParameters = new Object[] {subdistrictid};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataPostalCode(), queryParameters);
	}

	@Override
	public PostalCode getById(long id) {
		// TODO Auto-generated method stub
		Optional<PostalCode> obj = repository.findById(id);
		if(obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	public List<PostalCodeData> getListPostalCodeByPostalCodeByCityAndProvince(long cityid, long provid) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataPostalCode().schema());
		sqlBuilder.append(" where data.city_id = ? and data.prov_id = ? ");
		final Object[] queryParameters = new Object[] {cityid,provid};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataPostalCode(), queryParameters);
	}

	@Override
	public List<PostalCodeData> getListPostalCodeByPostalCodeByDistrictId(long districtid) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataPostalCode().schema());
		sqlBuilder.append(" where data.subdis_id in (select subdis_id from subdistricts where dis_id = "+districtid+") ");
		final Object[] queryParameters = new Object[] {};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataPostalCode(), queryParameters);
	}

}
