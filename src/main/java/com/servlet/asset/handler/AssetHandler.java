package com.servlet.asset.handler;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.asset.entity.Asset;
import com.servlet.asset.entity.AssetData;
import com.servlet.asset.entity.AssetMapping;
import com.servlet.asset.entity.AssetMappingData;
import com.servlet.asset.entity.AssetTemplate;
import com.servlet.asset.entity.BodyAsset;
import com.servlet.asset.entity.BodyAssetMapping;
import com.servlet.asset.entity.HistoryAssetMapping;
import com.servlet.asset.entity.HistoryAssetMappingData;
import com.servlet.asset.mapper.GetAssetData;
import com.servlet.asset.mapper.GetAssetDataDetail;
import com.servlet.asset.mapper.GetAssetMapping;
import com.servlet.asset.mapper.GetHistoryMappingAsset;
import com.servlet.asset.repo.AssetMappingRepo;
import com.servlet.asset.repo.AssetRepo;
import com.servlet.asset.repo.HistoryAssetMappingRepo;
import com.servlet.asset.service.AssetService;
import com.servlet.parameter.service.ParameterService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.GlobalFunc;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class AssetHandler implements AssetService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private AssetRepo repository;
	@Autowired
	private AssetMappingRepo assetMappingRepo;
	@Autowired
	private HistoryAssetMappingRepo historyAssetMappingRepo;
	@Autowired
	private ParameterService parameterService;
	
	@Override
	public List<AssetData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetAssetData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetAssetData(), queryParameters);
	}

	@Override
	public List<AssetData> getListActive(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetAssetData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false and data.isactive = true ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetAssetData(), queryParameters);
	}

	@Override
	public AssetData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetAssetDataDetail().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<AssetData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetAssetDataDetail(), queryParameters);
		if(list != null && list.size() > 0) {
			AssetData val = list.get(0);
			val.setHistorymapping(getListHistoryMapping(idcompany, idbranch, id));
			val.setAssetmapping(getListAssetMapping(idcompany, idbranch, id));
			return val;
		}
		return null;
	}
	
	private AssetData checkById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetAssetData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<AssetData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetAssetData(), queryParameters);
		if(list != null && list.size() > 0) {
			AssetData val = list.get(0);
			return val;
		}
		return null;
	}
	
	private AssetData checkByKodeAsset(Long idcompany, Long idbranch, String kodeAsset) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetAssetData().schema());
		sqlBuilder.append(" where data.kodeasset = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {kodeAsset,idcompany,idbranch};
		List<AssetData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetAssetData(), queryParameters);
		if(list != null && list.size() > 0) {
			AssetData val = list.get(0);
			return val;
		}
		return null;
	}

	@Override
	public ReturnData saveAsset(Long idcompany, Long idbranch, Long iduser, BodyAsset body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		AssetData value = checkByKodeAsset(idcompany, idbranch, body.getKodeasset());
		if(value != null) {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_ASSET_KODEASSET_ISEXIST,"Kode Asset sudah ada");
			validations.add(msg);
		}
		if(validations.size() == 0) {
			try {
				Timestamp ts = new Timestamp(new Date().getTime());
				Asset table = new Asset();
				
				if(body.getAssettype().equals("KEPALA")) {
					table = inventarisasiKepala(body,null);
				}else if(body.getAssettype().equals("BUNTUT")) {
					table = inventarisasiBuntut(body,null);
				}else if(body.getAssettype().equals("SP_KEPALA")) {
					table = inventarisasiSparePartKepala(body,null);
				}else if(body.getAssettype().equals("SP_BUNTUT")) {
					table = inventarisasiSparePartBuntut(body,null);
					
				}
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setKodeasset(body.getKodeasset());
				table.setAssettype(body.getAssettype());
				table.setIsactive(body.isIsactive());
				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setCreateddate(ts);
				idsave = repository.saveAndFlush(table).getId();
			}catch (Exception e) {
				// TODO: handle exception
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
				validations.add(msg);
			}
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}
	
	@Override
	public ReturnData updateAsset(Long idcompany, Long idbranch, Long iduser, Long id, BodyAsset body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		AssetData value = checkById(idcompany, idbranch, id);
		if(value != null) {
			if(validations.size() == 0) {
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					Asset table = repository.getById(id);
					
					if(body.getAssettype().equals("KEPALA")) {
						table = inventarisasiKepala(body,table);
					}else if(body.getAssettype().equals("BUNTUT")) {
						table = inventarisasiBuntut(body,table);
					}else if(body.getAssettype().equals("SP_KEPALA")) {
						table = inventarisasiSparePartKepala(body,table);
					}else if(body.getAssettype().equals("SP_BUNTUT")) {
						table = inventarisasiSparePartBuntut(body,table);
					}
					
					table.setUpdateby(iduser.toString());
					table.setUpdatedate(ts);
					
					idsave = repository.saveAndFlush(table).getId();
				}catch (Exception e) {
					// TODO: handle exception
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
					validations.add(msg);
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
	public ReturnData deleteAsset(Long idcompany, Long idbranch, Long iduser, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		AssetData value = checkById(idcompany, idbranch, id);
		if(value != null) {
			Timestamp ts = new Timestamp(new Date().getTime());
			Asset table = repository.getById(id);
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
	
	private Asset inventarisasiSparePartBuntut(BodyAsset body,Asset tabledb) {
		Asset table = new Asset();
		if(tabledb != null) {
			table = tabledb;
		}
		
		table.setSparepartbuntut_nama(body.getSparepartbuntut_nama());
		table.setSparepartbuntut_jenis(body.getSparepartbuntut_jenis());
		table.setSparepartbuntut_keterangan(body.getSparepartbuntut_keterangan());
		table.setSparepartbuntut_bearing_nobearing("");
		table.setSparepartbuntut_bearing_posisi("");
		table.setSparepartbuntut_bearing_merk("");
		table.setSparepartbuntut_bearing_jenishole("");
		table.setSparepartbuntut_bearing_kotakbulat("");
		table.setSparepartbuntut_ban_nama("");
		table.setSparepartbuntut_ban_keterangan("");
		table.setSparepartbuntut_ban_posisi("");
		table.setSparepartbuntut_ban_jenis("");
		table.setSparepartbuntut_ban_ukuran("");
		table.setSparepartbuntut_ban_status("");
		table.setSparepartbuntut_lainnya_nama("");
		table.setSparepartbuntut_lainnya_keterangan("");
		
		if(body.getSparepartbuntut_jenis().equals("BAN")) {
			table.setSparepartbuntut_ban_nama(body.getSparepartbuntut_ban_nama());
			table.setSparepartbuntut_ban_keterangan(body.getSparepartbuntut_ban_keterangan());
			table.setSparepartbuntut_ban_posisi(body.getSparepartbuntut_ban_posisi());
			table.setSparepartbuntut_ban_jenis(body.getSparepartbuntut_ban_jenis());
			table.setSparepartbuntut_ban_ukuran(body.getSparepartbuntut_ban_ukuran());
			table.setSparepartbuntut_ban_status(body.getSparepartbuntut_ban_status());
		}else if(body.getSparepartbuntut_jenis().equals("BEARING")) {
			table.setSparepartbuntut_bearing_nobearing(body.getSparepartbuntut_bearing_nobearing());
			table.setSparepartbuntut_bearing_posisi(body.getSparepartbuntut_bearing_posisi());
			table.setSparepartbuntut_bearing_merk(body.getSparepartbuntut_bearing_merk());
			table.setSparepartbuntut_bearing_jenishole(body.getSparepartbuntut_bearing_jenishole());
			table.setSparepartbuntut_bearing_kotakbulat(body.getSparepartbuntut_bearing_kotakbulat());
		}else if(body.getSparepartbuntut_jenis().equals("LAINNYA")) {
			table.setSparepartbuntut_lainnya_nama(body.getSparepartbuntut_lainnya_nama());
			table.setSparepartbuntut_lainnya_keterangan(body.getSparepartbuntut_lainnya_keterangan());
		}
		
		
		table.setSparepartkepala_nama("");
		table.setSparepartkepala_jenis("");
		table.setSparepartkepala_keterangan("");
		table.setSparepartkepala_bearing_nobearing("");
		table.setSparepartkepala_bearing_posisibearing("");
		table.setSparepartkepala_bearing_merk("");
		table.setSparepartkepala_bearing_jenishole("");
		table.setSparepartkepala_bearing_kotakbulat("");
		table.setSparepartkepala_ban_nama("");
		table.setSparepartkepala_ban_keterangan("");
		table.setSparepartkepala_ban_posisi("");
		table.setSparepartkepala_ban_jenis("");
		table.setSparepartkepala_ban_ukuran("");
		table.setSparepartkepala_ban_status("");
		table.setSparepartkepala_lainnya_nama("");
		table.setSparepartkepala_lainnya_keterangan("");
		
		table.setBuntut_nama("");
		table.setBuntut_nobuntut("");
		table.setBuntut_nobearingluar("");
		table.setBuntut_nobearingdalam("");
		table.setBuntut_nokir("");
		table.setBuntut_masaberlakukir(null);
		table.setBuntut_rangka("");
		table.setBuntut_merkaxel("");
		table.setBuntut_jenisaxel("");
		table.setBuntut_jenishole("");
		table.setBuntut_lunastanggal(null);
		
		
		table.setKepala_nama("");
		table.setKepala_nopolisi("");
		table.setKepala_jeniskendaraan("");
		table.setKepala_merk("");
		table.setKepala_nomesin("");
		table.setKepala_norangka("");
		table.setKepala_nostnk("");
		table.setKepala_masaberlakustnk(null);
		table.setKepala_kir("");
		table.setKepala_masaberlakukir(null);
		table.setKepala_lunastanggal(null);
		table.setKepala_keterangan("");
		
		

		return table;
	}
	
	private Asset inventarisasiSparePartKepala(BodyAsset body,Asset tabledb) {
		Asset table = new Asset();
		if(tabledb != null) {
			table = tabledb;
		}
		
		table.setSparepartkepala_nama(body.getSparepartkepala_nama());
		table.setSparepartkepala_jenis(body.getSparepartkepala_jenis());
		table.setSparepartkepala_keterangan(body.getSparepartkepala_keterangan());
		table.setSparepartkepala_bearing_nobearing("");
		table.setSparepartkepala_bearing_posisibearing("");
		table.setSparepartkepala_bearing_merk("");
		table.setSparepartkepala_bearing_jenishole("");
		table.setSparepartkepala_bearing_kotakbulat("");
		table.setSparepartkepala_ban_nama("");
		table.setSparepartkepala_ban_keterangan("");
		table.setSparepartkepala_ban_posisi("");
		table.setSparepartkepala_ban_jenis("");
		table.setSparepartkepala_ban_ukuran("");
		table.setSparepartkepala_ban_status("");
		table.setSparepartkepala_lainnya_nama("");
		table.setSparepartkepala_lainnya_keterangan("");
		
		if(body.getSparepartkepala_jenis().equals("BAN")) {
			table.setSparepartkepala_ban_nama(body.getSparepartkepala_ban_nama());
			table.setSparepartkepala_ban_keterangan(body.getSparepartkepala_ban_keterangan());
			table.setSparepartkepala_ban_posisi(body.getSparepartkepala_ban_posisi());
			table.setSparepartkepala_ban_jenis(body.getSparepartkepala_ban_jenis());
			table.setSparepartkepala_ban_ukuran(body.getSparepartkepala_ban_ukuran());
			table.setSparepartkepala_ban_status(body.getSparepartkepala_ban_status());
		}else if(body.getSparepartkepala_jenis().equals("BEARING")) {
			table.setSparepartkepala_bearing_nobearing(body.getSparepartkepala_bearing_nobearing());
			table.setSparepartkepala_bearing_posisibearing(body.getSparepartkepala_bearing_posisibearing());
			table.setSparepartkepala_bearing_merk(body.getSparepartkepala_bearing_merk());
			table.setSparepartkepala_bearing_jenishole(body.getSparepartkepala_bearing_jenishole());
			table.setSparepartkepala_bearing_kotakbulat(body.getSparepartkepala_bearing_kotakbulat());
		}else if(body.getSparepartkepala_jenis().equals("LAINNYA")) {
			table.setSparepartkepala_lainnya_nama(body.getSparepartkepala_lainnya_nama());
			table.setSparepartkepala_lainnya_keterangan(body.getSparepartkepala_lainnya_keterangan());
		}
		
		
		table.setSparepartbuntut_nama("");
		table.setSparepartbuntut_jenis("");
		table.setSparepartbuntut_keterangan("");
		table.setSparepartbuntut_bearing_nobearing("");
		table.setSparepartbuntut_bearing_posisi("");
		table.setSparepartbuntut_bearing_merk("");
		table.setSparepartbuntut_bearing_jenishole("");
		table.setSparepartbuntut_bearing_kotakbulat("");
		table.setSparepartbuntut_ban_nama("");
		table.setSparepartbuntut_ban_keterangan("");
		table.setSparepartbuntut_ban_posisi("");
		table.setSparepartbuntut_ban_jenis("");
		table.setSparepartbuntut_ban_ukuran("");
		table.setSparepartbuntut_ban_status("");
		table.setSparepartbuntut_lainnya_nama("");
		table.setSparepartbuntut_lainnya_keterangan("");
		
		table.setBuntut_nama("");
		table.setBuntut_nobuntut("");
		table.setBuntut_nobearingluar("");
		table.setBuntut_nobearingdalam("");
		table.setBuntut_nokir("");
		table.setBuntut_masaberlakukir(null);
		table.setBuntut_rangka("");
		table.setBuntut_merkaxel("");
		table.setBuntut_jenisaxel("");
		table.setBuntut_jenishole("");
		table.setBuntut_lunastanggal(null);
		
		
		table.setKepala_nama("");
		table.setKepala_nopolisi("");
		table.setKepala_jeniskendaraan("");
		table.setKepala_merk("");
		table.setKepala_nomesin("");
		table.setKepala_norangka("");
		table.setKepala_nostnk("");
		table.setKepala_masaberlakustnk(null);
		table.setKepala_kir("");
		table.setKepala_masaberlakukir(null);
		table.setKepala_lunastanggal(null);
		table.setKepala_keterangan("");
		
		

		return table;
	}
	private Asset inventarisasiBuntut(BodyAsset body,Asset tabledb) {
		Asset table = new Asset();
		if(tabledb != null) {
			table = tabledb;
		}
		
		table.setBuntut_nama(body.getBuntut_nama());
		table.setBuntut_nobuntut(body.getBuntut_nobuntut());
		table.setBuntut_nobearingluar(body.getBuntut_nobearingluar());
		table.setBuntut_nobearingdalam(body.getBuntut_nobearingdalam());
		table.setBuntut_nokir(body.getBuntut_nokir());
		table.setBuntut_masaberlakukir(body.getBuntut_masaberlakukir() != null ? new java.sql.Date(body.getBuntut_masaberlakukir()):null);
		table.setBuntut_rangka(body.getBuntut_rangka());
		table.setBuntut_merkaxel(body.getBuntut_merkaxel());
		table.setBuntut_jenisaxel(body.getBuntut_jenisaxel());
		table.setBuntut_jenishole(body.getBuntut_jenishole());
		table.setBuntut_lunastanggal(body.getBuntut_lunastanggal() != null ? new java.sql.Date(body.getBuntut_lunastanggal()):null);
		
		
		table.setKepala_nama("");
		table.setKepala_nopolisi("");
		table.setKepala_jeniskendaraan("");
		table.setKepala_merk("");
		table.setKepala_nomesin("");
		table.setKepala_norangka("");
		table.setKepala_nostnk("");
		table.setKepala_masaberlakustnk(null);
		table.setKepala_kir("");
		table.setKepala_masaberlakukir(null);
		table.setKepala_lunastanggal(null);
		table.setKepala_keterangan("");
		
		table.setSparepartkepala_nama("");
		table.setSparepartkepala_jenis("");
		table.setSparepartkepala_keterangan("");
		table.setSparepartkepala_bearing_nobearing("");
		table.setSparepartkepala_bearing_posisibearing("");
		table.setSparepartkepala_bearing_merk("");
		table.setSparepartkepala_bearing_jenishole("");
		table.setSparepartkepala_bearing_kotakbulat("");
		table.setSparepartkepala_ban_nama("");
		table.setSparepartkepala_ban_keterangan("");
		table.setSparepartkepala_ban_posisi("");
		table.setSparepartkepala_ban_jenis("");
		table.setSparepartkepala_ban_ukuran("");
		table.setSparepartkepala_ban_status("");
		table.setSparepartkepala_lainnya_nama("");
		table.setSparepartkepala_lainnya_keterangan("");
		table.setSparepartbuntut_nama("");
		table.setSparepartbuntut_jenis("");
		table.setSparepartbuntut_keterangan("");
		table.setSparepartbuntut_bearing_nobearing("");
		table.setSparepartbuntut_bearing_posisi("");
		table.setSparepartbuntut_bearing_merk("");
		table.setSparepartbuntut_bearing_jenishole("");
		table.setSparepartbuntut_bearing_kotakbulat("");
		table.setSparepartbuntut_ban_nama("");
		table.setSparepartbuntut_ban_keterangan("");
		table.setSparepartbuntut_ban_posisi("");
		table.setSparepartbuntut_ban_jenis("");
		table.setSparepartbuntut_ban_ukuran("");
		table.setSparepartbuntut_ban_status("");
		table.setSparepartbuntut_lainnya_nama("");
		table.setSparepartbuntut_lainnya_keterangan("");

		return table;
	}
	
	private Asset inventarisasiKepala(BodyAsset body,Asset tabledb) {
		Asset table = new Asset();
		if(tabledb != null) {
			table = tabledb;
		}
		
		table.setKepala_nama(body.getKepala_nama());
		table.setKepala_nopolisi(body.getKepala_nopolisi());
		table.setKepala_jeniskendaraan(body.getKepala_jeniskendaraan());
		table.setKepala_merk(body.getKepala_merk());
		table.setKepala_nomesin(body.getKepala_nomesin());
		table.setKepala_norangka(body.getKepala_norangka());
		table.setKepala_nostnk(body.getKepala_nostnk());
		table.setKepala_masaberlakustnk(body.getKepala_masaberlakustnk() != null?new java.sql.Date(body.getKepala_masaberlakustnk()):null);
		table.setKepala_kir(body.getKepala_kir());
		table.setKepala_masaberlakukir(body.getKepala_masaberlakukir() != null ? new java.sql.Date(body.getKepala_masaberlakukir()):null);
		table.setKepala_lunastanggal(body.getKepala_lunastanggal() != null? new java.sql.Date(body.getKepala_lunastanggal()):null);
		table.setKepala_keterangan(body.getKepala_keterangan());
		
		table.setBuntut_nama("");
		table.setBuntut_nobuntut("");
		table.setBuntut_nobearingluar("");
		table.setBuntut_nobearingdalam("");
		table.setBuntut_nokir("");
		table.setBuntut_masaberlakukir(null);
		table.setBuntut_rangka("");
		table.setBuntut_merkaxel("");
		table.setBuntut_jenisaxel("");
		table.setBuntut_jenishole("");
		table.setBuntut_lunastanggal(null);
		
		table.setSparepartkepala_nama("");
		table.setSparepartkepala_jenis("");
		table.setSparepartkepala_keterangan("");
		table.setSparepartkepala_bearing_nobearing("");
		table.setSparepartkepala_bearing_posisibearing("");
		table.setSparepartkepala_bearing_merk("");
		table.setSparepartkepala_bearing_jenishole("");
		table.setSparepartkepala_bearing_kotakbulat("");
		table.setSparepartkepala_ban_nama("");
		table.setSparepartkepala_ban_keterangan("");
		table.setSparepartkepala_ban_posisi("");
		table.setSparepartkepala_ban_jenis("");
		table.setSparepartkepala_ban_ukuran("");
		table.setSparepartkepala_ban_status("");
		table.setSparepartkepala_lainnya_nama("");
		table.setSparepartkepala_lainnya_keterangan("");
		table.setSparepartbuntut_nama("");
		table.setSparepartbuntut_jenis("");
		table.setSparepartbuntut_keterangan("");
		table.setSparepartbuntut_bearing_nobearing("");
		table.setSparepartbuntut_bearing_posisi("");
		table.setSparepartbuntut_bearing_merk("");
		table.setSparepartbuntut_bearing_jenishole("");
		table.setSparepartbuntut_bearing_kotakbulat("");
		table.setSparepartbuntut_ban_nama("");
		table.setSparepartbuntut_ban_keterangan("");
		table.setSparepartbuntut_ban_posisi("");
		table.setSparepartbuntut_ban_jenis("");
		table.setSparepartbuntut_ban_ukuran("");
		table.setSparepartbuntut_ban_status("");
		table.setSparepartbuntut_lainnya_nama("");
		table.setSparepartbuntut_lainnya_keterangan("");

		return table;
	}

	private AssetTemplate setAssetTemplate(long idcompany, long idbranch) {
		AssetTemplate data = new AssetTemplate();
		data.setAssetTypeOptions(parameterService.getListParameterByGrup("ASSET_TYPE"));
		data.setJenisKendaraanOptions(parameterService.getListParameterByGrup("JENIS_KENDARAAN"));
		data.setJenisBuntutOptions(parameterService.getListParameterByGrup("JENIS_BUNTUT_ASSET"));
		data.setJenisAxelOptions(parameterService.getListParameterByGrup("JENIS_AXEL"));
		data.setJenisHoleOptions(parameterService.getListParameterByGrup("JENIS_HOLE"));
		data.setJenisSparepartOptions(parameterService.getListParameterByGrup("JENIS_SPAREPART"));
		data.setPosisiBearingOptions(parameterService.getListParameterByGrup("POSISI_BEARING"));
		data.setPosisiBanOptions(parameterService.getListParameterByGrup("POSISI_BAN"));
		data.setJenisBanOptions(parameterService.getListParameterByGrup("JENIS_BAN"));
		data.setUkuranBanOptions(parameterService.getListParameterByGrup("UKURAN_BAN"));
		data.setStatusBanOptions(parameterService.getListParameterByGrup("STATUS_BAN"));
		data.setBentukBearingOptions(parameterService.getListParameterByGrup("BENTUKBEARING"));
		return data;
	}

	@Override
	public AssetTemplate getTemplate(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		return setAssetTemplate(idcompany,idbranch);
	}

	@Override
	public ReturnData saveAssetMapping(Long idcompany, Long idbranch, Long iduser, BodyAssetMapping body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				Timestamp ts = new Timestamp(new Date().getTime());
				AssetMapping table = new AssetMapping();
				HistoryAssetMapping tableHistory = new HistoryAssetMapping();
				boolean isChangeMapping = true;
				boolean isCreateHistory = true;
				if(body.getId() != null) {
					Optional<AssetMapping> optAssetMapping = assetMappingRepo.findById(body.getId().longValue());
					if(optAssetMapping.isPresent()) {
						isChangeMapping = false;
						AssetMapping data = optAssetMapping.get();
						if(data.getIdasset_mapping().longValue() != body.getIdasset_mapping().longValue()) {
							isChangeMapping = true;
							tableHistory.setBefore(data.getIdasset_mapping() != null?data.getIdasset_mapping():0);
							tableHistory.setAfter(body.getIdasset_mapping());
							assetMappingRepo.deleteById(body.getId().longValue());
						}else {
							isCreateHistory = false;
						}
					}
					
					
				}
				
				if(isChangeMapping){
					table.setIdcompany(idcompany);
					table.setIdbranch(idbranch);
					table.setIdasset(body.getIdasset());
					table.setIdasset_mapping(body.getIdasset_mapping());
					table.setType(body.getType());
					idsave = assetMappingRepo.saveAndFlush(table).getId();
					
					tableHistory.setBefore(0L);
					tableHistory.setAfter(body.getIdasset_mapping());
				}
				
				if(isCreateHistory) {
					tableHistory.setIdasset(body.getIdasset());
					tableHistory.setIdcompany(idcompany);
					tableHistory.setIdbranch(idbranch);
					tableHistory.setIduser(iduser);
					tableHistory.setType(body.getType());
					tableHistory.setTanggal(ts);
					historyAssetMappingRepo.saveAndFlush(tableHistory);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
				validations.add(msg);
			}
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	

	@Override
	public ReturnData deleteHistoryAssetMapping(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				historyAssetMappingRepo.deleteById(id);
			}catch (Exception e) {
				// TODO: handle exception
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
				validations.add(msg);
			}
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public List<AssetData> getListAssetForPenandaanSuratJalan(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetAssetData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false and data.isactive = true ");
		sqlBuilder.append(" and data.assettype = 'KEPALA' ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetAssetData(), queryParameters);
	}

	@Override
	public List<AssetData> getListAssetForMapping(Long idcompany, Long idbranch, Long id,String jenisasset) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetAssetData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false and data.isactive = true ");
		sqlBuilder.append(" and data.assettype = '"+jenisasset+"' ");
		if(jenisasset.equals("BUNTUT")) {
			sqlBuilder.append(" and ( ");
			sqlBuilder.append(" data.id not in (select am.idasset_mapping from m_asset_mapping as am) ");
			sqlBuilder.append(" or data.id in (select am.idasset_mapping from m_asset_mapping as am where am.idasset = "+id+") ");
			sqlBuilder.append(" ) ");
		}else {
			sqlBuilder.append(" and ");
			sqlBuilder.append(" data.id not in (select am.idasset_mapping from m_asset_mapping as am) ");
		}
		System.out.println("sqlBuilder "+sqlBuilder.toString());
		
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetAssetData(), queryParameters);
	}

	@Override
	public List<HistoryAssetMappingData> getListHistoryMapping(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetHistoryMappingAsset().schema());
		sqlBuilder.append(" where data.idasset = ? and data.idcompany = ? and data.idbranch = ? order by data.id desc ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetHistoryMappingAsset(), queryParameters);
	}
	
	private List<AssetMappingData> getListAssetMapping(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetAssetMapping().schema());
		sqlBuilder.append(" where data.idasset = ? and data.idcompany = ? and data.idbranch = ?  ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetAssetMapping(), queryParameters);
	}

	@Override
	public List<AssetData> getListAssetReminder(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		Date dt = new Date();
		long time = dt.getTime();
		try {
			Timestamp tsThru = GlobalFunc.addDays(new Timestamp(time), 30);
			java.sql.Date dtFrom = new java.sql.Date(time);
			java.sql.Date dtThru = new java.sql.Date(tsThru.getTime());
			
			final StringBuilder sqlBuilder = new StringBuilder("select " + new GetAssetData().schema());
			sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false and data.isactive = true ");
			sqlBuilder.append(" and data.assettype = 'KEPALA'  ");
			sqlBuilder.append(" and ( data.kepala_masaberlakustnk <= '"+dtFrom+"' or (data.kepala_masaberlakustnk >= '"+dtFrom+"' and data.kepala_masaberlakustnk <= '"+dtThru+"' )   )");
			sqlBuilder.append(" or ( data.kepala_masaberlakukir <= '"+dtFrom+"' or (data.kepala_masaberlakukir >= '"+dtFrom+"' and data.kepala_masaberlakukir <= '"+dtThru+"' )   )");
			final Object[] queryParameters = new Object[] {idcompany,idbranch};
			return this.jdbcTemplate.query(sqlBuilder.toString(), new GetAssetData(), queryParameters);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return new ArrayList<AssetData>();
		}
		
		
	}

}
