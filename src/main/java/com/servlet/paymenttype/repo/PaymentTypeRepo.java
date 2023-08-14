package com.servlet.paymenttype.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.paymenttype.entity.PaymentType;

@Repository("PaymentTypeRepo")
public interface PaymentTypeRepo extends JpaRepository<PaymentType, Long>{

}
