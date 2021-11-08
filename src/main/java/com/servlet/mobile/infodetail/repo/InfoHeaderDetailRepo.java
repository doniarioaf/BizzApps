package com.servlet.mobile.infodetail.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.mobile.infodetail.entity.InfoHeaderDetail;

@Repository("InfoHeaderDetailRepo")
public interface InfoHeaderDetailRepo extends JpaRepository<InfoHeaderDetail, Long>{

}
