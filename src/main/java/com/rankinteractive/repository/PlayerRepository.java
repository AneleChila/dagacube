package com.rankinteractive.repository;

import com.rankinteractive.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select player from Player player where player.username = :username and player.password = :password")
    Player findByCredentials(@Param("username") String username, @Param("password") String password);

}
