package com.servlet.workorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.workorder.entity.ListDocumentWorkOrder;

@Repository("DocumentWorkOrderRepo")
public interface DocumentWorkOrderRepo extends JpaRepository<ListDocumentWorkOrder, Long>{

}
