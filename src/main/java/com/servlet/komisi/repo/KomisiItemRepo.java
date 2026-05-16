package com.servlet.komisi.repo;

import com.servlet.komisi.entity.KomisiItem;
import com.servlet.komisi.entity.KomisiItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("KomisiItemRepo")
public interface KomisiItemRepo extends JpaRepository<KomisiItem, KomisiItemPK> {
    @Transactional
    @Modifying
    @Query(value ="delete from komisi_item where idkomisi = :idkomisi ",nativeQuery = true)
    void deleteAllDetailByIdKomisi(@Param("idkomisi") long idkomisi);

}
