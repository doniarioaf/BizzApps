package com.servlet.stockadjusment.repo;

import com.servlet.stockadjusment.entity.StockAdjusmentItem;
import com.servlet.stockadjusment.entity.StockAdjusmentItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("StockAdjusmentItemRepo")
public interface StockAdjusmentItemRepo extends JpaRepository<StockAdjusmentItem, StockAdjusmentItemPK> {

    @Transactional
    @Modifying
    @Query(value ="delete from stock_adjusment_item where idstockadjusment = :idstockadjusment ",nativeQuery = true)
    void deleteAllDetailByIdStockAdjusment(@Param("idstockadjusment") long idstockadjusment);

//    @Transactional
//    @Modifying
//    @Query(value ="delete from stock_adjusment_item where idstockadjusment = :idstockadjusment and idproduct = :idproduct and idcategoryproduct = :idcategoryproduct and type = :type ",nativeQuery = true)
//    void deleteDetailByPK(@Param("idstockadjusment") long idstockadjusment, @Param("idproduct") long idproduct, @Param("idcategoryproduct") long idcategoryproduct, @Param("type") String type);
}
