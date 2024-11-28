package com.servlet.pricelist.handler;

import com.servlet.pricelist.entity.PriceListData;
import com.servlet.pricelist.mapper.QueryDataList;
import com.servlet.pricelist.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PriceHandler implements PriceService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<PriceListData> getListAll(Long idcompany, Long idbranch, Long from, Long to) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  ");
        if(from != null){
            Date dt = new Date(from);
            sqlBuilder.append(" and data.pricedate >= '"+dt.toString()+"'");
        }
        if(to != null){
            Date dt = new Date(to);
            sqlBuilder.append(" and data.pricedate <= '"+dt.toString()+"'");
        }
        System.out.println("Query PriceListData > "+sqlBuilder.toString());
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }
}
