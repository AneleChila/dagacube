package com.rankinteractive.repository;

import com.rankinteractive.model.Player;
import com.rankinteractive.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select trans from Transaction trans where trans.transactionId = :transactionId")
    Transaction findByTransactionId(@Param("transactionId") Long transactionId);


    @Query("select trans from Transaction trans where trans.playerId = :playerId")
    List<Transaction> findByPlayerId(@Param("playerId") Long playerId);

}
