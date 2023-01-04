package com.servlet.asset.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.asset.entity.AssetData;

public class GetAssetDataDetail implements RowMapper<AssetData>{
private String schemaSql;
	
	public GetAssetDataDetail() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id,");
		sqlBuilder.append("data.kodeasset as kodeasset,");
		sqlBuilder.append("data.assettype as assettype,");
		sqlBuilder.append("data.kepala_nama as kepala_nama,");
		sqlBuilder.append("data.kepala_nopolisi as kepala_nopolisi,");
		sqlBuilder.append("data.kepala_jeniskendaraan as kepala_jeniskendaraan,");
		sqlBuilder.append("data.kepala_merk as kepala_merk,");
		sqlBuilder.append("data.kepala_nomesin as kepala_nomesin,");
		sqlBuilder.append("data.kepala_norangka as kepala_norangka,");
		sqlBuilder.append("data.kepala_nostnk as kepala_nostnk,");
		sqlBuilder.append("data.kepala_masaberlakustnk as kepala_masaberlakustnk,");
		sqlBuilder.append("data.kepala_kir as kepala_kir,");
		sqlBuilder.append("data.kepala_masaberlakukir as kepala_masaberlakukir,");
		sqlBuilder.append("data.kepala_lunastanggal as kepala_lunastanggal,");
		sqlBuilder.append("data.kepala_keterangan as kepala_keterangan,");
		sqlBuilder.append("data.buntut_nama as buntut_nama,");
		sqlBuilder.append("data.buntut_nobuntut as buntut_nobuntut,");
		sqlBuilder.append("data.buntut_nobearingluar as buntut_nobearingluar,");
		sqlBuilder.append("data.buntut_nobearingdalam as buntut_nobearingdalam,");
		sqlBuilder.append("data.buntut_nokir as buntut_nokir,");
		sqlBuilder.append("data.buntut_masaberlakukir as buntut_masaberlakukir,");
		sqlBuilder.append("data.buntut_rangka as buntut_rangka,");
		sqlBuilder.append("data.buntut_merkaxel as buntut_merkaxel,");
		sqlBuilder.append("data.buntut_jenisaxel as buntut_jenisaxel,");
		sqlBuilder.append("data.buntut_jenishole as buntut_jenishole,");
		sqlBuilder.append("data.buntut_lunastanggal as buntut_lunastanggal,");
		sqlBuilder.append("data.sparepartkepala_nama as sparepartkepala_nama,");
		sqlBuilder.append("data.sparepartkepala_jenis as sparepartkepala_jenis,");
		sqlBuilder.append("data.sparepartkepala_keterangan as sparepartkepala_keterangan,");
		sqlBuilder.append("data.sparepartkepala_bearing_nobearing as sparepartkepala_bearing_nobearing,");
		sqlBuilder.append("data.sparepartkepala_bearing_posisibearing as sparepartkepala_bearing_posisibearing,");
		sqlBuilder.append("data.sparepartkepala_bearing_merk as sparepartkepala_bearing_merk,");
		sqlBuilder.append("data.sparepartkepala_bearing_jenishole as sparepartkepala_bearing_jenishole,");
		sqlBuilder.append("data.sparepartkepala_bearing_kotakbulat as sparepartkepala_bearing_kotakbulat,");
		sqlBuilder.append("data.sparepartkepala_ban_nama as sparepartkepala_ban_nama,");
		sqlBuilder.append("data.sparepartkepala_ban_keterangan as sparepartkepala_ban_keterangan,");
		sqlBuilder.append("data.sparepartkepala_ban_posisi as sparepartkepala_ban_posisi,");
		sqlBuilder.append("data.sparepartkepala_ban_jenis as sparepartkepala_ban_jenis,");
		sqlBuilder.append("data.sparepartkepala_ban_ukuran as sparepartkepala_ban_ukuran,");
		sqlBuilder.append("data.sparepartkepala_ban_status as sparepartkepala_ban_status,");
		sqlBuilder.append("data.sparepartkepala_lainnya_nama as sparepartkepala_lainnya_nama,");
		sqlBuilder.append("data.sparepartkepala_lainnya_keterangan as sparepartkepala_lainnya_keterangan,");
		sqlBuilder.append("data.sparepartbuntut_nama as sparepartbuntut_nama,");
		sqlBuilder.append("data.sparepartbuntut_jenis as sparepartbuntut_jenis,");
		sqlBuilder.append("data.sparepartbuntut_keterangan as sparepartbuntut_keterangan,");
		sqlBuilder.append("data.sparepartbuntut_bearing_nobearing as sparepartbuntut_bearing_nobearing,");
		sqlBuilder.append("data.sparepartbuntut_bearing_posisi as sparepartbuntut_bearing_posisi,");
		sqlBuilder.append("data.sparepartbuntut_bearing_merk as sparepartbuntut_bearing_merk,");
		sqlBuilder.append("data.sparepartbuntut_bearing_jenishole as sparepartbuntut_bearing_jenishole,");
		sqlBuilder.append("data.sparepartbuntut_bearing_kotakbulat as sparepartbuntut_bearing_kotakbulat,");
		sqlBuilder.append("data.sparepartbuntut_ban_nama as sparepartbuntut_ban_nama,");
		sqlBuilder.append("data.sparepartbuntut_ban_keterangan as sparepartbuntut_ban_keterangan,");
		sqlBuilder.append("data.sparepartbuntut_ban_posisi as sparepartbuntut_ban_posisi,");
		sqlBuilder.append("data.sparepartbuntut_ban_jenis as sparepartbuntut_ban_jenis,");
		sqlBuilder.append("data.sparepartbuntut_ban_ukuran as sparepartbuntut_ban_ukuran,");
		sqlBuilder.append("data.sparepartbuntut_ban_status as sparepartbuntut_ban_status,");
		sqlBuilder.append("data.sparepartbuntut_lainnya_nama as sparepartbuntut_lainnya_nama,");
		sqlBuilder.append("data.sparepartbuntut_lainnya_keterangan as sparepartbuntut_lainnya_keterangan,");
		sqlBuilder.append("data.isactive as isactive,");
		sqlBuilder.append("param.codename as assettypename,");
		sqlBuilder.append("paramkepalajeniskendaraan.codename as kepalajeniskendaraaname, ");
		sqlBuilder.append("parambuntutrangkaplat.codename as buntutrangkaplat, ");
		sqlBuilder.append("parambuntutjenisaxel.codename as buntutjenisaxel, ");
		sqlBuilder.append("parambuntutjenishole.codename as buntutjenishole, ");
		sqlBuilder.append("paramspkepalabearingjenishole.codename as spkepalabearingjenishole, ");
		sqlBuilder.append("paramspkepalabearingkotakbulat.codename as spkepalabearingkotakbulat, ");
		sqlBuilder.append("paramspkepalabanposisi.codename as spkepalabanposisi, ");
		sqlBuilder.append("paramspkepalabanjenis.codename as spkepalabanjenis, ");
		sqlBuilder.append("paramspkepalabanukuran.codename as spkepalabanukuran, ");
		sqlBuilder.append("paramspkepalabanstatus.codename as spkepalabanstatus, ");
		sqlBuilder.append("paramspkepalajenissparepart.codename as spkepalajenissparepart, ");
		sqlBuilder.append("paramspkepalabearingposisi.codename as spkepalabearingposisi, ");
		
		sqlBuilder.append("paramspbuntutbearingjenishole.codename as spbuntutbearingjenishole, ");
		sqlBuilder.append("paramspbuntutbearingkotakbulat.codename as spbuntutbearingkotakbulat, ");
		sqlBuilder.append("paramspbuntutbanposisi.codename as spbuntutbanposisi, ");
		sqlBuilder.append("paramspbuntutbanjenis.codename as spbuntutbanjenis, ");
		sqlBuilder.append("paramspbuntutbanukuran.codename as spbuntutbanukuran, ");
		sqlBuilder.append("paramspbuntutbanstatus.codename as spbuntutbanstatus, ");
		sqlBuilder.append("paramspbuntutjenissparepart.codename as spbuntutjenissparepart, ");
		sqlBuilder.append("paramspbuntutbearingposisi.codename as spbuntutbearingposisi, ");
		
		sqlBuilder.append("data.sparepartkepala_filter_type as sparepartkepala_filter_type,");
		sqlBuilder.append("data.sparepartkepala_filter_posisi as sparepartkepala_filter_posisi,");
		sqlBuilder.append("data.sparepartkepala_bohlam_type as sparepartkepala_bohlam_type,");
		sqlBuilder.append("data.sparepartkepala_selang_type as sparepartkepala_selang_type,");
		
		sqlBuilder.append("data.sparepartbuntut_filter_type as sparepartbuntut_filter_type,");
		sqlBuilder.append("data.sparepartbuntut_filter_posisi as sparepartbuntut_filter_posisi,");
		sqlBuilder.append("data.sparepartbuntut_bohlam_type as sparepartbuntut_bohlam_type,");
		sqlBuilder.append("data.sparepartbuntut_selang_type as sparepartbuntut_selang_type,");
		
		sqlBuilder.append("paramspkepalafiltertype.codename as spkepalafiltertype, ");
		sqlBuilder.append("paramspkepalafilterposisi.codename as spkepalafilterposisi, ");
		sqlBuilder.append("paramspkepalabohlamtype.codename as spkepalabohlamtype, ");
		sqlBuilder.append("paramspkepalaselangtype.codename as spkepalaselangtype, ");
		
		sqlBuilder.append("paramspbuntutfiltertype.codename as spbuntutfiltertype, ");
		sqlBuilder.append("paramspbuntutfilterposisi.codename as spbuntutfilterposisi, ");
		sqlBuilder.append("paramspbuntutbohlamtype.codename as spbuntutbohlamtype, ");
		sqlBuilder.append("paramspbuntutselangtype.codename as spbuntutselangtype ");

		
		sqlBuilder.append(" from m_asset as data ");
		sqlBuilder.append(" left join m_parameter as param on param.code = data.assettype and param.grup = 'ASSET_TYPE' ");
		sqlBuilder.append(" left join m_parameter as paramkepalajeniskendaraan on paramkepalajeniskendaraan.code = data.kepala_jeniskendaraan and paramkepalajeniskendaraan.grup = 'JENIS_KENDARAAN' ");
		sqlBuilder.append(" left join m_parameter as parambuntutrangkaplat on parambuntutrangkaplat.code = data.buntut_rangka and parambuntutrangkaplat.grup = 'JENIS_BUNTUT_ASSET' ");
		sqlBuilder.append(" left join m_parameter as parambuntutjenisaxel on parambuntutjenisaxel.code = data.buntut_jenisaxel and parambuntutjenisaxel.grup = 'JENIS_AXEL' ");
		sqlBuilder.append(" left join m_parameter as parambuntutjenishole on parambuntutjenishole.code = data.buntut_jenishole and parambuntutjenishole.grup = 'JENIS_HOLE' ");
		
		sqlBuilder.append(" left join m_parameter as paramspkepalajenissparepart on paramspkepalajenissparepart.code = data.sparepartkepala_jenis and paramspkepalajenissparepart.grup = 'JENIS_SPAREPART' ");
		sqlBuilder.append(" left join m_parameter as paramspkepalabearingposisi on paramspkepalabearingposisi.code = data.sparepartkepala_bearing_posisibearing and paramspkepalabearingposisi.grup = 'POSISI_BEARING' ");
		sqlBuilder.append(" left join m_parameter as paramspkepalabearingjenishole on paramspkepalabearingjenishole.code = data.sparepartkepala_bearing_jenishole and paramspkepalabearingjenishole.grup = 'JENIS_HOLE' ");
		sqlBuilder.append(" left join m_parameter as paramspkepalabearingkotakbulat on paramspkepalabearingkotakbulat.code = data.sparepartkepala_bearing_kotakbulat and paramspkepalabearingkotakbulat.grup = 'BENTUKBEARING' ");
		sqlBuilder.append(" left join m_parameter as paramspkepalabanposisi on paramspkepalabanposisi.code = data.sparepartkepala_ban_posisi and paramspkepalabanposisi.grup = 'POSISI_BAN' ");
		sqlBuilder.append(" left join m_parameter as paramspkepalabanjenis on paramspkepalabanjenis.code = data.sparepartkepala_ban_jenis and paramspkepalabanjenis.grup = 'JENIS_BAN' ");
		sqlBuilder.append(" left join m_parameter as paramspkepalabanukuran on paramspkepalabanukuran.code = data.sparepartkepala_ban_ukuran and paramspkepalabanukuran.grup = 'UKURAN_BAN' ");
		sqlBuilder.append(" left join m_parameter as paramspkepalabanstatus on paramspkepalabanstatus.code = data.sparepartkepala_ban_status and paramspkepalabanstatus.grup = 'STATUS_BAN' ");
		
		sqlBuilder.append(" left join m_parameter as paramspbuntutjenissparepart on paramspbuntutjenissparepart.code = data.sparepartbuntut_jenis and paramspbuntutjenissparepart.grup = 'JENIS_SPAREPART' ");
		sqlBuilder.append(" left join m_parameter as paramspbuntutbearingposisi on paramspbuntutbearingposisi.code = data.sparepartbuntut_bearing_posisi and paramspbuntutbearingposisi.grup = 'POSISI_BEARING' ");
		sqlBuilder.append(" left join m_parameter as paramspbuntutbearingjenishole on paramspbuntutbearingjenishole.code = data.sparepartbuntut_bearing_jenishole and paramspbuntutbearingjenishole.grup = 'JENIS_HOLE' ");
		sqlBuilder.append(" left join m_parameter as paramspbuntutbearingkotakbulat on paramspbuntutbearingkotakbulat.code = data.sparepartbuntut_bearing_kotakbulat and paramspbuntutbearingkotakbulat.grup = 'BENTUKBEARING' ");
		sqlBuilder.append(" left join m_parameter as paramspbuntutbanposisi on paramspbuntutbanposisi.code = data.sparepartbuntut_ban_posisi and paramspbuntutbanposisi.grup = 'POSISI_BAN' ");
		sqlBuilder.append(" left join m_parameter as paramspbuntutbanjenis on paramspbuntutbanjenis.code = data.sparepartbuntut_ban_jenis and paramspbuntutbanjenis.grup = 'JENIS_BAN' ");
		sqlBuilder.append(" left join m_parameter as paramspbuntutbanukuran on paramspbuntutbanukuran.code = data.sparepartbuntut_ban_ukuran and paramspbuntutbanukuran.grup = 'UKURAN_BAN' ");
		sqlBuilder.append(" left join m_parameter as paramspbuntutbanstatus on paramspbuntutbanstatus.code = data.sparepartbuntut_ban_status and paramspbuntutbanstatus.grup = 'STATUS_BAN' ");
		
		sqlBuilder.append(" left join m_parameter as paramspkepalafiltertype on paramspkepalafiltertype.code = data.sparepartkepala_filter_type and paramspkepalafiltertype.grup = 'SPAREPART_FILTER_TIPE' ");
		sqlBuilder.append(" left join m_parameter as paramspkepalafilterposisi on paramspkepalafilterposisi.code = data.sparepartkepala_filter_posisi and paramspkepalafilterposisi.grup = 'SPAREPART_POSISI_FILTER' ");
		sqlBuilder.append(" left join m_parameter as paramspkepalabohlamtype on paramspkepalabohlamtype.code = data.sparepartkepala_bohlam_type and paramspkepalabohlamtype.grup = 'SPAREPART_BOHLAM_TIPE' ");
		sqlBuilder.append(" left join m_parameter as paramspkepalaselangtype on paramspkepalaselangtype.code = data.sparepartkepala_selang_type and paramspkepalaselangtype.grup = 'SPAREPART_SELANG_TIPE' ");

		sqlBuilder.append(" left join m_parameter as paramspbuntutfiltertype on paramspbuntutfiltertype.code = data.sparepartbuntut_filter_type and paramspbuntutfiltertype.grup = 'SPAREPART_FILTER_TIPE' ");
		sqlBuilder.append(" left join m_parameter as paramspbuntutfilterposisi on paramspbuntutfilterposisi.code = data.sparepartbuntut_filter_posisi and paramspbuntutfilterposisi.grup = 'SPAREPART_POSISI_FILTER' ");
		sqlBuilder.append(" left join m_parameter as paramspbuntutbohlamtype on paramspbuntutbohlamtype.code = data.sparepartbuntut_bohlam_type and paramspbuntutbohlamtype.grup = 'SPAREPART_BOHLAM_TIPE' ");
		sqlBuilder.append(" left join m_parameter as paramspbuntutselangtype on paramspbuntutselangtype.code = data.sparepartbuntut_selang_type and paramspbuntutselangtype.grup = 'SPAREPART_SELANG_TIPE' ");

		
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public AssetData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String kodeasset = rs.getString("kodeasset");
		final String assettype = rs.getString("assettype");
		final String assettypename = rs.getString("assettypename");
		final String kepala_nama = rs.getString("kepala_nama");
		final String kepala_nopolisi = rs.getString("kepala_nopolisi");
		final String kepala_jeniskendaraan = rs.getString("kepala_jeniskendaraan");
		final String kepala_merk = rs.getString("kepala_merk");
		final String kepala_nomesin = rs.getString("kepala_nomesin");
		final String kepala_norangka = rs.getString("kepala_norangka");
		final String kepala_nostnk = rs.getString("kepala_nostnk");
		final Date kepala_masaberlakustnk = rs.getDate("kepala_masaberlakustnk");
		final String kepala_kir = rs.getString("kepala_kir");
		final Date kepala_masaberlakukir = rs.getDate("kepala_masaberlakukir");
		final Date kepala_lunastanggal = rs.getDate("kepala_lunastanggal");
		final String kepala_keterangan = rs.getString("kepala_keterangan");
		final String buntut_nama = rs.getString("buntut_nama");
		final String buntut_nobuntut = rs.getString("buntut_nobuntut");
		final String buntut_nobearingluar = rs.getString("buntut_nobearingluar");
		final String buntut_nobearingdalam = rs.getString("buntut_nobearingdalam");
		final String buntut_nokir = rs.getString("buntut_nokir");
		
		final Date buntut_masaberlakukir = rs.getDate("buntut_masaberlakukir");
		final String buntut_rangka = rs.getString("buntut_rangka");
		final String buntut_merkaxel = rs.getString("buntut_merkaxel");
		final String buntut_jenisaxel = rs.getString("buntut_jenisaxel");
		final String buntut_jenishole = rs.getString("buntut_jenishole");
		final Date buntut_lunastanggal = rs.getDate("buntut_lunastanggal");
		final String sparepartkepala_nama = rs.getString("sparepartkepala_nama");
		final String sparepartkepala_jenis = rs.getString("sparepartkepala_jenis");
		final String sparepartkepala_keterangan = rs.getString("sparepartkepala_keterangan");
		final String sparepartkepala_bearing_nobearing = rs.getString("sparepartkepala_bearing_nobearing");
		final String sparepartkepala_bearing_posisibearing = rs.getString("sparepartkepala_bearing_posisibearing");
		final String sparepartkepala_bearing_merk = rs.getString("sparepartkepala_bearing_merk");
		final String sparepartkepala_bearing_jenishole = rs.getString("sparepartkepala_bearing_jenishole");
		final String sparepartkepala_bearing_kotakbulat = rs.getString("sparepartkepala_bearing_kotakbulat");
		final String sparepartkepala_ban_nama = rs.getString("sparepartkepala_ban_nama");
		final String sparepartkepala_ban_keterangan = rs.getString("sparepartkepala_ban_keterangan");
		final String sparepartkepala_ban_posisi = rs.getString("sparepartkepala_ban_posisi");
		final String sparepartkepala_ban_jenis = rs.getString("sparepartkepala_ban_jenis");
		final String sparepartkepala_ban_ukuran = rs.getString("sparepartkepala_ban_ukuran");
		final String sparepartkepala_ban_status = rs.getString("sparepartkepala_ban_status");
		final String sparepartkepala_lainnya_nama = rs.getString("sparepartkepala_lainnya_nama");
		final String sparepartkepala_lainnya_keterangan = rs.getString("sparepartkepala_lainnya_keterangan");
		final String sparepartbuntut_nama = rs.getString("sparepartbuntut_nama");
		
		final String sparepartbuntut_jenis = rs.getString("sparepartbuntut_jenis");
		final String sparepartbuntut_keterangan = rs.getString("sparepartbuntut_keterangan");
		final String sparepartbuntut_bearing_nobearing = rs.getString("sparepartbuntut_bearing_nobearing");
		final String sparepartbuntut_bearing_posisi = rs.getString("sparepartbuntut_bearing_posisi");
		final String sparepartbuntut_bearing_merk = rs.getString("sparepartbuntut_bearing_merk");
		final String sparepartbuntut_bearing_jenishole = rs.getString("sparepartbuntut_bearing_jenishole");
		final String sparepartbuntut_bearing_kotakbulat = rs.getString("sparepartbuntut_bearing_kotakbulat");
		final String sparepartbuntut_ban_nama = rs.getString("sparepartbuntut_ban_nama");
		final String sparepartbuntut_ban_keterangan = rs.getString("sparepartbuntut_ban_keterangan");
		final String sparepartbuntut_ban_posisi = rs.getString("sparepartbuntut_ban_posisi");
		
		final String sparepartbuntut_ban_jenis = rs.getString("sparepartbuntut_ban_jenis");
		final String sparepartbuntut_ban_ukuran = rs.getString("sparepartbuntut_ban_ukuran");
		final String sparepartbuntut_ban_status = rs.getString("sparepartbuntut_ban_status");
		final String sparepartbuntut_lainnya_nama = rs.getString("sparepartbuntut_lainnya_nama");
		final String sparepartbuntut_lainnya_keterangan = rs.getString("sparepartbuntut_lainnya_keterangan");
		final boolean isactive = rs.getBoolean("isactive");
		
		final String kepalajeniskendaraaname = rs.getString("kepalajeniskendaraaname");
		final String buntutrangkaplat = rs.getString("buntutrangkaplat");
		final String buntutjenisaxel = rs.getString("buntutjenisaxel");
		final String buntutjenishole = rs.getString("buntutjenishole");
		
		final String spkepalabearingjenishole = rs.getString("spkepalabearingjenishole");
		final String spkepalabearingkotakbulat = rs.getString("spkepalabearingkotakbulat");
		final String spkepalabanposisi = rs.getString("spkepalabanposisi");
		final String spkepalabanjenis = rs.getString("spkepalabanjenis");
		final String spkepalabanukuran = rs.getString("spkepalabanukuran");
		final String spkepalabanstatus = rs.getString("spkepalabanstatus");
		final String spkepalajenissparepart = rs.getString("spkepalajenissparepart");
		final String spkepalabearingposisi = rs.getString("spkepalabearingposisi");
		
		final String spbuntutbearingjenishole = rs.getString("spbuntutbearingjenishole");
		final String spbuntutbearingkotakbulat = rs.getString("spbuntutbearingkotakbulat");
		final String spbuntutbanposisi = rs.getString("spbuntutbanposisi");
		final String spbuntutbanjenis = rs.getString("spbuntutbanjenis");
		final String spbuntutbanukuran = rs.getString("spbuntutbanukuran");
		final String spbuntutbanstatus = rs.getString("spbuntutbanstatus");
		final String spbuntutjenissparepart = rs.getString("spbuntutjenissparepart");
		final String spbuntutbearingposisi = rs.getString("spbuntutbearingposisi");
		
		final String sparepartkepala_filter_type = rs.getString("sparepartkepala_filter_type");
		final String sparepartkepala_filter_posisi = rs.getString("sparepartkepala_filter_posisi");
		final String sparepartkepala_bohlam_type = rs.getString("sparepartkepala_bohlam_type");
		final String sparepartkepala_selang_type = rs.getString("sparepartkepala_selang_type");
		
		final String sparepartbuntut_filter_type = rs.getString("sparepartbuntut_filter_type");
		final String sparepartbuntut_filter_posisi = rs.getString("sparepartbuntut_filter_posisi");
		final String sparepartbuntut_bohlam_type = rs.getString("sparepartbuntut_bohlam_type");
		final String sparepartbuntut_selang_type = rs.getString("sparepartbuntut_selang_type");
		
		final String spkepalafiltertype = rs.getString("spkepalafiltertype");
		final String spkepalafilterposisi = rs.getString("spkepalafilterposisi");
		final String spkepalabohlamtype = rs.getString("spkepalabohlamtype");
		final String spkepalaselangtype = rs.getString("spkepalaselangtype");
		
		final String spbuntutfiltertype = rs.getString("spbuntutfiltertype");
		final String spbuntutfilterposisi = rs.getString("spbuntutfilterposisi");
		final String spbuntutbohlamtype = rs.getString("spbuntutbohlamtype");
		final String spbuntutselangtype = rs.getString("spbuntutselangtype");
		
		
		AssetData data = new AssetData();
		data.setId(id);
		data.setKodeasset(kodeasset);
		data.setAssettype(assettype);
		data.setAssettypeName(assettypename);
		data.setKepala_nama(kepala_nama);
		data.setKepala_nopolisi(kepala_nopolisi);
		data.setKepala_jeniskendaraan(kepala_jeniskendaraan);
		data.setKepala_jeniskendaraanname(kepalajeniskendaraaname);
		data.setKepala_merk(kepala_merk);
		data.setKepala_nomesin(kepala_nomesin);
		data.setKepala_norangka(kepala_norangka);
		data.setKepala_nostnk(kepala_nostnk);
		data.setKepala_masaberlakustnk(kepala_masaberlakustnk);
		data.setKepala_kir(kepala_kir);
		data.setKepala_masaberlakukir(kepala_masaberlakukir);
		data.setKepala_lunastanggal(kepala_lunastanggal);
		data.setKepala_keterangan(kepala_keterangan);
		data.setBuntut_nama(buntut_nama);
		data.setBuntut_nobuntut(buntut_nobuntut);
		data.setBuntut_nobearingluar(buntut_nobearingluar);
		data.setBuntut_nobearingdalam(buntut_nobearingdalam);
		data.setBuntut_nokir(buntut_nokir);
		data.setBuntut_masaberlakukir(buntut_masaberlakukir);
		data.setBuntut_rangka(buntut_rangka);
		data.setBuntut_merkaxel(buntut_merkaxel);
		data.setBuntut_jenisaxel(buntut_jenisaxel);
		data.setBuntut_jenishole(buntut_jenishole);
		data.setBuntut_lunastanggal(buntut_lunastanggal);
		data.setSparepartkepala_nama(sparepartkepala_nama);
		data.setSparepartkepala_jenis(sparepartkepala_jenis);
		data.setSparepartkepala_keterangan(sparepartkepala_keterangan);
		data.setSparepartkepala_bearing_nobearing(sparepartkepala_bearing_nobearing);
		data.setSparepartkepala_bearing_posisibearing(sparepartkepala_bearing_posisibearing);
		data.setSparepartkepala_bearing_merk(sparepartkepala_bearing_merk);
		data.setSparepartkepala_bearing_jenishole(sparepartkepala_bearing_jenishole);
		data.setSparepartkepala_bearing_kotakbulat(sparepartkepala_bearing_kotakbulat);
		data.setSparepartkepala_ban_nama(sparepartkepala_ban_nama);
		data.setSparepartkepala_ban_keterangan(sparepartkepala_ban_keterangan);
		data.setSparepartkepala_ban_posisi(sparepartkepala_ban_posisi);
		data.setSparepartkepala_ban_jenis(sparepartkepala_ban_jenis);
		data.setSparepartkepala_ban_ukuran(sparepartkepala_ban_ukuran);
		data.setSparepartkepala_ban_status(sparepartkepala_ban_status);
		data.setSparepartkepala_lainnya_nama(sparepartkepala_lainnya_nama);
		data.setSparepartkepala_lainnya_keterangan(sparepartkepala_lainnya_keterangan);
		data.setSparepartbuntut_nama(sparepartbuntut_nama);
		data.setSparepartbuntut_jenis(sparepartbuntut_jenis);
		data.setSparepartbuntut_keterangan(sparepartbuntut_keterangan);
		data.setSparepartbuntut_bearing_nobearing(sparepartbuntut_bearing_nobearing);
		data.setSparepartbuntut_bearing_posisi(sparepartbuntut_bearing_posisi);
		data.setSparepartbuntut_bearing_merk(sparepartbuntut_bearing_merk);
		data.setSparepartbuntut_bearing_jenishole(sparepartbuntut_bearing_jenishole);
		data.setSparepartbuntut_bearing_kotakbulat(sparepartbuntut_bearing_kotakbulat);
		data.setSparepartbuntut_ban_nama(sparepartbuntut_ban_nama);
		data.setSparepartbuntut_ban_keterangan(sparepartbuntut_ban_keterangan);
		data.setSparepartbuntut_ban_posisi(sparepartbuntut_ban_posisi);
		data.setSparepartbuntut_ban_jenis(sparepartbuntut_ban_jenis);
		data.setSparepartbuntut_ban_ukuran(sparepartbuntut_ban_ukuran);
		data.setSparepartbuntut_ban_status(sparepartbuntut_ban_status);
		data.setSparepartbuntut_lainnya_nama(sparepartbuntut_lainnya_nama);
		data.setSparepartbuntut_lainnya_keterangan(sparepartbuntut_lainnya_keterangan);
		data.setIsactive(isactive);
		
		data.setBuntut_rangkaname(buntutrangkaplat);
		data.setBuntut_jenisaxelname(buntutjenisaxel);
		data.setBuntut_jenisholename(buntutjenishole);
		
		data.setSparepartkepala_bearing_posisibearingname(spkepalabearingposisi);
		data.setSparepartkepala_bearing_jenisholename(spkepalabearingjenishole);
		data.setSparepartkepala_bearing_kotakbulatname(spkepalabearingkotakbulat);
		data.setSparepartkepala_ban_posisiname(spkepalabanposisi);
		data.setSparepartkepala_ban_jenisname(spkepalabanjenis);
		data.setSparepartkepala_ban_ukuranname(spkepalabanukuran);
		data.setSparepartkepala_ban_statusname(spkepalabanstatus);
		data.setSparepartkepala_jenisname(spkepalajenissparepart);
		
		data.setSparepartbuntut_bearing_posisiname(spbuntutbearingposisi);
		data.setSparepartbuntut_bearing_jenisholename(spbuntutbearingjenishole);
		data.setSparepartbuntut_bearing_kotakbulatname(spbuntutbearingkotakbulat);
		data.setSparepartbuntut_ban_posisiname(spbuntutbanposisi);
		data.setSparepartbuntut_ban_jenisname(spbuntutbanjenis);
		data.setSparepartbuntut_ban_ukuranname(spbuntutbanukuran);
		data.setSparepartbuntut_ban_statusname(spbuntutbanstatus);
		data.setSparepartbuntut_jenisname(spbuntutjenissparepart);
		
		data.setSparepartkepala_filter_type(sparepartkepala_filter_type);
		data.setSparepartkepala_filter_posisi(sparepartkepala_filter_posisi);
		data.setSparepartkepala_bohlam_type(sparepartkepala_bohlam_type);
		data.setSparepartkepala_selang_type(sparepartkepala_selang_type);
		
		data.setSparepartkepala_filter_type_name(spkepalafiltertype);
		data.setSparepartkepala_filter_posisi_name(spkepalafilterposisi);
		data.setSparepartkepala_bohlam_type_name(spkepalabohlamtype);
		data.setSparepartkepala_selang_type_name(spkepalaselangtype);
		
		data.setSparepartbuntut_filter_type(sparepartbuntut_filter_type);
		data.setSparepartbuntut_filter_posisi(sparepartbuntut_filter_posisi);
		data.setSparepartbuntut_bohlam_type(sparepartbuntut_bohlam_type);
		data.setSparepartbuntut_selang_type(sparepartbuntut_selang_type);
		
		data.setSparepartbuntut_filter_type_name(spbuntutfiltertype);
		data.setSparepartbuntut_filter_posisi_name(spbuntutfilterposisi);
		data.setSparepartbuntut_bohlam_type_name(spbuntutbohlamtype);
		data.setSparepartbuntut_selang_type_name(spbuntutselangtype);
		
		return data;
	}
}
