package com.servlet.historyapps.repo;

import com.servlet.historyapps.entity.HistoryApps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("HistoryAppsRepo")
public interface HistoryAppsRepo extends JpaRepository<HistoryApps, Long> {
}
