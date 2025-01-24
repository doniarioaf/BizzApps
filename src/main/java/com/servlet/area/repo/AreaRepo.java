package com.servlet.area.repo;

import com.servlet.area.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("AreaRepo")
public interface AreaRepo extends JpaRepository<Area, Long> {
}
