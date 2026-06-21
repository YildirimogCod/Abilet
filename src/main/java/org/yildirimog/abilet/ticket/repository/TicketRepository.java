package org.yildirimog.abilet.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yildirimog.abilet.ticket.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
