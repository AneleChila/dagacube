package com.rankinteractive.repository;

import com.rankinteractive.model.Promotion;
import com.rankinteractive.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>  {

    @Query("select promo from Promotion promo where promo.isActive = :isActive")
    Promotion findByActivity(@Param("isActive") boolean isActive);
}
