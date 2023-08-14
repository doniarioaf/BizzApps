package com.servlet.invoicetype.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.invoicetype.entity.InvoiceType;

@Repository("InvoiceTypeRepo")
public interface InvoiceTypeRepo extends JpaRepository<InvoiceType, Long>{

}
