package com.servlet.admin.customerproject.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.servlet.admin.rolepermissions.entity.RolePermissionsPK;

@Entity
@Table(name = "m_customer_project", schema = "public")
public class CustomerProject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private CustomerProjectPK customerProjectPK;

	public CustomerProjectPK getCustomerProjectPK() {
		return customerProjectPK;
	}

	public void setCustomerProjectPK(CustomerProjectPK customerProjectPK) {
		this.customerProjectPK = customerProjectPK;
	}
}
