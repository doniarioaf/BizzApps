package com.servlet.user.mapper;

import com.servlet.user.entity.UserBasicData;
import com.servlet.user.entity.UserListData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryBasicData implements RowMapper<UserBasicData> {
    private String schemaSql;

    public QueryBasicData() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(400);
        sqlBuilder.append("data.nama as nama from m_user_apps data ");

        this.schemaSql = sqlBuilder.toString();
    }

    @Override
    public UserBasicData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String nama = rs.getString("nama");
        UserBasicData data = new UserBasicData();

        return data;
    }
}
