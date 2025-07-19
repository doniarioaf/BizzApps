package com.servlet.vendor.mapper;

import com.servlet.vendor.entity.VendorData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryVendorDetail implements RowMapper<VendorData> {
    private String schemaSql;

    public QueryVendorDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.alias as alias,data.type as type, ");
        sqlBuilder.append("data.bank as bank, data.accountnobank as accountnobank, data.accountnamebank as accountnamebank, ");
        sqlBuilder.append("data.pricebox as pricebox, data.priceongkos as priceongkos, data.isparent as isparent, data.idvendorparent as idvendorparent, ");
        sqlBuilder.append("data.packing as packing, data.kurir as kurir,data.address1 as address1, data.address2 as address2, data.npwp as npwp,data.phone as phone, ");
        sqlBuilder.append("data.komisi as komisi, data.profit as profit,data.value1 as value1, ");
        sqlBuilder.append("ven.nama as vennama, ven.alias as venalias, ");
        sqlBuilder.append("area.id as idarea, area.nama as areaname, ");
        sqlBuilder.append("data.idvendorbroker as idvendorbroker, venbroker.nama as venbrokernama, venbroker.alias as venbrokeralias, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, data.deletedate as deletedate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama, userdelete.nama as deletenama ");
        sqlBuilder.append("from m_vendor as data ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");
        sqlBuilder.append("left join m_user_apps as userdelete on userdelete.id = data.deleteby ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendorparent ");
        sqlBuilder.append("left join m_vendor as venbroker on venbroker.id = data.idvendorbroker ");
        sqlBuilder.append("left join m_area as area on area.id = data.idarea ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public VendorData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String alias = rs.getString("alias");
        final String type = rs.getString("type");
        final String bank = rs.getString("bank");
        final String accountnobank = rs.getString("accountnobank");
        final String accountnamebank = rs.getString("accountnamebank");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final Timestamp deletedate = rs.getTimestamp("deletedate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");
        final String deletenama = rs.getString("deletenama");
        final Double pricebox = rs.getDouble("pricebox");
        final Double priceongkos = rs.getDouble("priceongkos");
        final Double packing = rs.getDouble("packing");
        final Double kurir = rs.getDouble("kurir");
        final Double komisi = rs.getDouble("komisi");
        final Double profit = rs.getDouble("profit");
        final Double value1 = rs.getDouble("value1");
        final Boolean isparent = rs.getBoolean("isparent");
        final Long idvendorparent = rs.getLong("idvendorparent");
        final String vennama = rs.getString("vennama");
        final String venalias = rs.getString("venalias");
        final Long idvendorbroker = rs.getLong("idvendorbroker");
        final String venbrokernama = rs.getString("venbrokernama");
        final String venbrokeralias = rs.getString("venbrokeralias");
        final Long idarea = rs.getLong("idarea");
        final String areaname = rs.getString("areaname");
        final String address1 = rs.getString("address1");
        final String address2 = rs.getString("address2");
        final String npwp = rs.getString("npwp");
        final String phone = rs.getString("phone");

        VendorData data = new VendorData();
        data.setId(id);
        data.setNama(nama);
        data.setAlias(alias);
        data.setType(type);
        data.setBank(bank);
        data.setAccountnobank(accountnobank);
        data.setAccountnamebank(accountnamebank);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setDeletedate(deletedate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        data.setDeletebyName(deletenama);
        data.setPricebox(pricebox);
        data.setPriceongkos(priceongkos);
        data.setPacking(packing);
        data.setKurir(kurir);
        data.setKomisi(komisi);
        data.setProfit(profit);
        data.setValue1(value1);
        data.setIsparent(isparent);
        data.setIdvendorparent(idvendorparent);
        data.setVendorParentName(vennama);
        data.setVendorParentAlias(venalias);
        data.setIdvendorbroker(idvendorbroker);
        data.setVendorBrokerName(venbrokernama);
        data.setVendorBrokerAlias(venbrokeralias);
        data.setIdarea(idarea);
        data.setAreaName(areaname);
        data.setAddress1(address1);
        data.setAddress2(address2);
        data.setNpwp(npwp);
        data.setPhone(phone);
        return data;
    }
}
