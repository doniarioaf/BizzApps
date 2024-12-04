package com.servlet.charge.handler;

import com.servlet.charge.entity.ChargeList;
import com.servlet.charge.mapper.QueryDataList;
import com.servlet.charge.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargeHandler implements ChargeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ChargeList> getListCharge(Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }
}
