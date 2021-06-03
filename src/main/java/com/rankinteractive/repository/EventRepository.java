package com.rankinteractive.repository;

import com.rankinteractive.model.TonicEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository  extends JpaRepository<TonicEvent, Long> {
}
