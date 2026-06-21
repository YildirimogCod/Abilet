package org.yildirimog.abilet.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yildirimog.abilet.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
