package com.servlet.invoice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.invoice.entity.Invoice;

@Repository("InvoiceRepo")
public interface InvoiceRepo extends JpaRepository<Invoice, Long>{

}
