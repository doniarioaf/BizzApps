package com.servlet.admin.userbranch.handler;

import com.servlet.admin.userbranch.entity.UserBranch;
import com.servlet.admin.userbranch.entity.UserBranchData;
import com.servlet.admin.userbranch.entity.UserBranchPK;
import com.servlet.admin.userbranch.mapper.GetDataUserBranch;
import com.servlet.admin.userbranch.mapper.GetDataUserBranchJoinBranch;
import com.servlet.admin.userbranch.repo.UserBranchRepo;
import com.servlet.admin.userbranch.service.UserBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBranchHandler implements UserBranchService {
    @Autowired
    private UserBranchRepo repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object saveUserBranch(UserBranchPK userbranch) {
        UserBranch table = new UserBranch();
        table.setUserBranchPK(userbranch);
        return repository.saveAndFlush(table);
    }

    @Override
    public Object saveUserBranchList(List<UserBranch> list) {
        if(list.size() > 0) {
            repository.saveAllAndFlush(list);
        }
        return null;
    }

    @Override
    public Object deleteAllUserBranchByListPK(List<UserBranchPK> listPK) {
        if(listPK.size() > 0) {
            repository.deleteAllById(listPK);
        }
        return null;
    }

    @Override
    public List<UserBranchData> getListUserBranchByIdUser(long iduser) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataUserBranch().schema());
        sqlBuilder.append(" where ub.iduser = ? ");
        final Object[] queryParameters = new Object[] { iduser };
        return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataUserBranch(), queryParameters);
    }

    @Override
    public List<UserBranchData> getListUserBranchByIdUserJoinBranch(long iduser) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataUserBranchJoinBranch().schema());
        sqlBuilder.append(" where ub.iduser = ? and b.isdelete = false ");
        final Object[] queryParameters = new Object[] { iduser };
        return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataUserBranchJoinBranch(), queryParameters);
    }
}
