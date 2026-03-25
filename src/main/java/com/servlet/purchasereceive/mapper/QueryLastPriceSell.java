package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.QueryLastPriceSellData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryLastPriceSell implements RowMapper<QueryLastPriceSellData> {
    private String schemaSql;

    public QueryLastPriceSell(Long idcompany, Long idbranch, Long idCustomer) {
        // TODO Auto-generated constructor stub
        //kenapa idcategoryambil dari mappingstock, karena. categoryproduct di packinglist / invoice itu dari category CUSTOMER
        //sedangkan di notepembelian / purchasereceive itu category VENDOR. maka cek di mapping stock,
        //categoryproductid = VENDOR, categoryproductidmapping = CUSTOMER
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("SELECT * " +
                "FROM ( " +
                "    SELECT  " +
                "        pli.idproduct, " +
                "        mps.categoryproductid, " +
                "        pli.price, " +
                "        pl.nodocument, " +
                "        pl.\"date\", " +
                "        pli.idpackinglist, " +
                "        ROW_NUMBER() OVER ( " +
                "            PARTITION BY pli.idproduct, mps.categoryproductid " +
                "            ORDER BY pl.\"date\" DESC, pli.idpackinglist DESC " +
                "        ) AS rn " +
                "    FROM packinglist_item pli " +
                "    JOIN packinglist pl  " +
                "        ON pli.idpackinglist = pl.id " +
                "    JOIN mapping_stock mps  " +
                "        ON mps.categoryproductidmapping = pli.idcategoryproduct " +
                "    WHERE pl.idcompany = "+idcompany+" and pl.idbranch = "+idbranch+" and pl.idcustomer = "+idCustomer+" and pli.price <> 0 and pl.isdelete = false " +
                "      AND EXISTS ( " +
                "          SELECT 1  " +
                "          FROM invoice inv " +
                "          WHERE inv.idpackinglist = pl.id " +
                "      ) " +
                ") t " +
                "WHERE rn = 1;");
        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public QueryLastPriceSellData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idproduct = rs.getLong("idproduct");
        final Long idcategoryproduct = rs.getLong("categoryproductid");
        final Long idpackinglist = rs.getLong("idpackinglist");
        final String nodocument = rs.getString("nodocument");
        final Date transactiondate = rs.getDate("date");
        final Double price = rs.getDouble("price");

        QueryLastPriceSellData data = new QueryLastPriceSellData();
        data.setIdproduct(idproduct);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setIdpackinglist(idpackinglist);
        data.setNodocument(nodocument);
        data.setTransactiondate(transactiondate);
        data.setHargajual(price);
        return data;
    }
}
