package com.servlet.employeemanggala.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.employeemanggala.entity.EmployeManggalaData;

public class GetEmployeeManggalaData implements RowMapper<EmployeManggalaData>{
	
	private String schemaSql;
	
	public GetEmployeeManggalaData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.code as code, data.statuskaryawan as statuskaryawan, data.jabatan as jabatan, data.nama as nama, data.noidentitas as noidentitas, data.alamat as alamat, data.tanggallahir as tanggallahir, data.status as status, data.namapasangan as namapasangan, data.tanggallahirpasangan as tanggallahirpasangan, data.namabank as namabank, ");
		sqlBuilder.append("data.norekening as norekening, data.atasnama as atasnama, data.tanggalmulai as tanggalmulai, data.tanggalresign as tanggalresign, data.gaji as gaji, data.isactive as isactive, data.jeniskelamin as jeniskelamin, data.photo as photo ");
		sqlBuilder.append("from m_employee_manggala as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public EmployeManggalaData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String code = rs.getString("code");
		final String statuskaryawan = rs.getString("statuskaryawan");
		final String jabatan = rs.getString("jabatan");
		final String nama = rs.getString("nama");
		final String noidentitas = rs.getString("noidentitas");
		final String alamat = rs.getString("alamat");
		final Date tanggallahir = rs.getDate("tanggallahir");
		final String status = rs.getString("status");
		final String namapasangan = rs.getString("namapasangan");
		final Date tanggallahirpasangan = rs.getDate("tanggallahirpasangan");
		final String namabank = rs.getString("namabank");
		final String norekening = rs.getString("norekening");
		final String atasnama = rs.getString("atasnama");
		final Date tanggalmulai = rs.getDate("tanggalmulai");
		final Date tanggalresign = rs.getDate("tanggalresign");
		final String gaji = rs.getString("gaji");
		final boolean isactive = rs.getBoolean("isactive");
		final String jeniskelamin = rs.getString("jeniskelamin");
		final String photo = rs.getString("photo");
		
		EmployeManggalaData data = new EmployeManggalaData();
		data.setId(id);
		data.setCode(code);
		data.setStatuskaryawan(statuskaryawan);
		data.setJabatan(jabatan);
		data.setNama(nama);
		data.setNoidentitas(noidentitas);
		data.setAlamat(alamat);
		data.setTanggallahir(tanggallahir);
		data.setStatus(status);
		data.setNamapasangan(namapasangan);
		data.setTanggallahirpasangan(tanggallahirpasangan);
		data.setNamabank(namabank);
		data.setNorekening(norekening);
		data.setAtasnama(atasnama);
		data.setTanggalmulai(tanggalmulai);
		data.setTanggalresign(tanggalresign);
		data.setGaji(gaji);
		data.setJeniskelamin(jeniskelamin);
		data.setIsactive(isactive);
		data.setPhoto(photo);
		return data;
	}

}
