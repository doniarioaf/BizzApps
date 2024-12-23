package com.servlet.packinglist.repo;

import com.servlet.packinglist.entity.PackingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PakcingListRepo")
public interface PakcingListRepo extends JpaRepository<PackingList, Long> {
}
