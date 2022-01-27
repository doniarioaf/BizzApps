package com.servlet.transaction.productstock.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.servlet.mobile.customercallplan.entity.CustomerCallPlanPK;

@Entity
@Table(name = "t_product_stock", schema = "public")
public class ProductStock {
	@EmbeddedId
    private ProductStockPK productStockPK;
	private long stock;
	public ProductStockPK getProductStockPK() {
		return productStockPK;
	}
	public void setProductStockPK(ProductStockPK productStockPK) {
		this.productStockPK = productStockPK;
	}
	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}

}
