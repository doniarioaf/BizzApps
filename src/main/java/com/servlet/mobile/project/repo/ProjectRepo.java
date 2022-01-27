package com.servlet.mobile.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.mobile.project.entity.Project;

@Repository("ProjectRepo")
public interface ProjectRepo extends JpaRepository<Project, Long>{

}
