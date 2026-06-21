package org.yildirimog.abilet.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yildirimog.abilet.event.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
