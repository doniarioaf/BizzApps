package com.servlet.registrasiapps.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.registrasiapps.entity.RegistrasiApps;

@Repository("RegistrasiAppsRepo")
public interface RegistrasiAppsRepo extends JpaRepository<RegistrasiApps, Long>{

}
