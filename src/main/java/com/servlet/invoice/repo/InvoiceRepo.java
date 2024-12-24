package com.servlet.invoice.repo;

import com.servlet.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("InvoiceRepo")
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
}
