package com.servlet.admin.logs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.admin.logs.entity.LogsActivity;

@Repository("LogsRepo")
public interface LogsRepo extends JpaRepository<LogsActivity, Long>{

}
