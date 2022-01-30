package com.servlet.admin.product.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.admin.product.entity.ProductDetailData;

public class GetDetailProduct implements RowMapper<ProductDetailData>{
	
	private String schemaSql;
	
	public GetDetailProduct() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mp.id as id ,mp.nama as namaprod ,mp.description as descprod, ");
		sqlBuilder.append("mp.productcode as productcode ,mp.shortname as shortname ,mp.uom1 as uom1, ");
		sqlBuilder.append("mp.uom2 as uom2 ,mp.uom3 as uom3 ,mp.uom4 as uom4, ");
		sqlBuilder.append("mp.pricebuy as pricebuy ,mp.pricesell as pricesell ,mp.conversion1to4 as conversion1to4, ");
		sqlBuilder.append("mp.conversion2to4 as conversion2to4 ,mp.conversion3to4 as conversion3to4, ");
		sqlBuilder.append("mp.priceselluom2 as priceselluom2 ,mp.priceselluom3 as priceselluom3, ");
		sqlBuilder.append("mp.priceselluom4 as priceselluom4 ,mp.barcode1 as barcode1, ");
		sqlBuilder.append("mp.barcode2 as barcode2 ,mp.barcode3 as barcode3, ");
		sqlBuilder.append("mp.barcode4 as barcode4, ");
		sqlBuilder.append("mpt.id as prodtypeid ,mpt.nama as prodtypename ");
		sqlBuilder.append("from m_product as mp ");
		sqlBuilder.append("left join m_product_type as mpt on mpt.id = mp.idproducttype ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public ProductDetailData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long id = rs.getLong("id");
		final String namaprod = rs.getString("namaprod");
		final String descprod = rs.getString("descprod");
		final long prodtypeid = rs.getLong("prodtypeid");
		final String prodtypename = rs.getString("prodtypename");
		final String productcode = rs.getString("productcode");
		final String shortname = rs.getString("shortname");
		final String uom1 = rs.getString("uom1");
		final String uom2 = rs.getString("uom2");
		final String uom3 = rs.getString("uom3");
		final String uom4 = rs.getString("uom4");
		final double pricebuy = rs.getDouble("pricebuy");
		final double pricesell = rs.getDouble("pricesell");
		final int conversion1to4 = rs.getInt("conversion1to4");
		final int conversion2to4 = rs.getInt("conversion2to4");
		final int conversion3to4 = rs.getInt("conversion3to4");
		final double priceselluom2 = rs.getDouble("priceselluom2");
		final double priceselluom3 = rs.getDouble("priceselluom3");
		final double priceselluom4 = rs.getDouble("priceselluom4");
		final String barcode1 = rs.getString("barcode1");
		final String barcode2 = rs.getString("barcode2");
		final String barcode3 = rs.getString("barcode3");
		final String barcode4 = rs.getString("barcode4");
		
		
		ProductDetailData data = new ProductDetailData();
		data.setId(id);
		data.setNama(namaprod);
		data.setDescription(descprod);
		data.setIdproducttype(prodtypeid);
		data.setNameproducttype(prodtypename);
		data.setProductcode(productcode);
		data.setShortname(shortname);
		data.setUom1(uom1);
		data.setUom2(uom2);
		data.setUom3(uom3);
		data.setUom4(uom4);
		data.setPricebuy(pricebuy);
		data.setPricesell(pricesell);
		data.setConversion1to4(conversion1to4);
		data.setConversion2to4(conversion2to4);
		data.setConversion3to4(conversion3to4);
		data.setPriceselluom2(priceselluom2);
		data.setPriceselluom3(priceselluom3);
		data.setPriceselluom4(priceselluom4);
		data.setBarcode1(barcode1);
		data.setBarcode2(barcode2);
		data.setBarcode3(barcode3);
		data.setBarcode4(barcode4);
		return data;
	}

}
