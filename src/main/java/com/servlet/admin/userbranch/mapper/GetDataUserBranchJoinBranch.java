package com.servlet.admin.userbranch.mapper;

import com.servlet.admin.userbranch.entity.UserBranchData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetDataUserBranchJoinBranch implements RowMapper<UserBranchData> {

    private String schemaSql;

    public GetDataUserBranchJoinBranch(){
        final StringBuilder sqlBuilder = new StringBuilder(400);
        sqlBuilder.append("ub.iduser as iduser, ub.idbranch as idbranch, b.nama as branchname, b.displayname as displayname from user_branch as ub ");
        sqlBuilder.append("left join m_branch as b on b.id = ub.idbranch ");
        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public UserBranchData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long iduser = rs.getLong("iduser");
        final Long idbranch = rs.getLong("idbranch");
        final String branchname = rs.getString("branchname");
        final String displayname = rs.getString("displayname");

        UserBranchData data = new UserBranchData();
        data.setIduser(iduser);
        data.setIdbranch(idbranch);
        data.setBranchName(branchname);
        data.setDisplayName(displayname);

        return data;
    }
}
