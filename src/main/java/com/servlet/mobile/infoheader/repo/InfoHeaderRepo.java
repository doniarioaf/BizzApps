package com.servlet.mobile.infoheader.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.mobile.infoheader.entity.InfoHeader;

@Repository("InfoHeaderRepo")
public interface InfoHeaderRepo extends JpaRepository<InfoHeader, Long>{

}
