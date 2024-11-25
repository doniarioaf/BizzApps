package com.servlet.admin.branch.mapper;

import com.servlet.admin.branch.entity.Branch;
import com.servlet.admin.branch.entity.UserBranchAllData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserBranchAll implements RowMapper<UserBranchAllData> {
    private String schemaSql;

    public GetUserBranchAll(){
        final StringBuilder sqlBuilder = new StringBuilder(400);
        sqlBuilder.append("* ");
        sqlBuilder.append("from m_branch as b ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public UserBranchAllData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String displayname = rs.getString("displayname");
        UserBranchAllData data = new UserBranchAllData();
        data.setIdbranch(id);
        data.setBranchName(nama);
        data.setDisplayName(displayname);

        return data;
    }
}
