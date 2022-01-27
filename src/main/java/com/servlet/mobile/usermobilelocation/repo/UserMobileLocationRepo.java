package com.servlet.mobile.usermobilelocation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.mobile.usermobilelocation.entity.UserMobileLocation;

@Repository("UserMobileLocationRepo")
public interface UserMobileLocationRepo extends JpaRepository<UserMobileLocation, Long>{

}
