package com.servlet.admin.userbranch.mapper;

import com.servlet.admin.userbranch.entity.UserBranchData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class GetDataUserBranch implements RowMapper<UserBranchData> {
    private String schemaSql;

    public GetDataUserBranch(){
        final StringBuilder sqlBuilder = new StringBuilder(400);
        sqlBuilder.append("* from user_branch as ub ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public UserBranchData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long iduser = rs.getLong("iduser");
        final Long idbranch = rs.getLong("idbranch");

        UserBranchData data = new UserBranchData();
        data.setIduser(iduser);
        data.setIdbranch(idbranch);

        return data;
    }
}
